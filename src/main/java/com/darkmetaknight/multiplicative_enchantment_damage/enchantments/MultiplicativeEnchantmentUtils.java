package com.darkmetaknight.multiplicative_enchantment_damage.enchantments;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.Optional;

public class MultiplicativeEnchantmentUtils {

    public static Optional<Holder.Reference<Enchantment>> getHolderForEnchantment(
            LivingIncomingDamageEvent livingIncomingDamageEvent,
            ResourceKey<Enchantment> enchantmentResourceKey
    ) {
        Level level = livingIncomingDamageEvent.getEntity().level();
        return level.registryAccess().holder(enchantmentResourceKey);
    }

    public static int getEnchantmentLevelGivenIncomingDamageEvent(
            LivingIncomingDamageEvent livingIncomingDamageEvent,
            ResourceKey<Enchantment> enchantmentResourceKey
    ) {
        return getHolderForEnchantment(livingIncomingDamageEvent, enchantmentResourceKey)
                .map(sharpnessRegistry ->
                        Optional.of(livingIncomingDamageEvent.getContainer())
                                .map(DamageContainer::getSource)
                                .map(DamageSource::getWeaponItem)
                                .map(itemStack ->
                                        itemStack.getEnchantmentLevel(sharpnessRegistry))
                                .orElse(0))
                .orElse(0);
    }

    /**
     * Util to apply given enchantment enhancement to damage multiplicatively regardless of EntityType.
     * (E.g. Sharpness)
     *
     * @param livingIncomingDamageEvent           Event itself that is called when an entity is hit.
     * @param checkForEnchantment                 Enchantment to check for to apply this multiplier.
     * @param configOverwriteDefault              Config value whether to undo vanilla/original implementation.
     * @param configEnabled                       Config value for whether this multiplier is enabled at all.
     * @param multiplyFirstLevelValue             Config value for the multiplier for level 1.
     * @param multiplyAdditionalLevels            Config value for the multiplier added per level following.
     * @param overwriteSubtractFirstLevel         How much to subtract for the first level of disabled original impl.
     * @param overwriteSubtractPerAdditionalLevel How much to subtract per additional level of disabled original impl.
     */
    public static void applyMultiplicativeDamageAnyEntity(
            LivingIncomingDamageEvent livingIncomingDamageEvent,
            ResourceKey<Enchantment> checkForEnchantment,
            ModConfigSpec.BooleanValue configOverwriteDefault,
            ModConfigSpec.BooleanValue configEnabled,
            ModConfigSpec.DoubleValue multiplyFirstLevelValue,
            ModConfigSpec.DoubleValue multiplyAdditionalLevels,
            float overwriteSubtractFirstLevel,
            float overwriteSubtractPerAdditionalLevel
    ) {
        if (!livingIncomingDamageEvent.isCanceled()) {
            applyMultiplicativeDamage(
                    livingIncomingDamageEvent,
                    checkForEnchantment,
                    configOverwriteDefault,
                    configEnabled,
                    multiplyFirstLevelValue,
                    multiplyAdditionalLevels,
                    overwriteSubtractFirstLevel,
                    overwriteSubtractPerAdditionalLevel);
        }
    }

    /**
     * Util to apply given enchantment enhancement to damage multiplicatively for specific EntityType.
     * (E.g. Smite, Bane of Arthropods)
     *
     * @param livingIncomingDamageEvent           Event itself that is called when an entity is hit.
     * @param checkForEnchantment                 Enchantment to check for to apply this multiplier.
     * @param configOverwriteDefault              Config value whether to undo vanilla/original implementation.
     * @param configEnabled                       Config value for whether this multiplier is enabled at all.
     * @param multiplyFirstLevelValue             Config value for the multiplier for level 1.
     * @param multiplyAdditionalLevels            Config value for the multiplier added per level following.
     * @param overwriteSubtractFirstLevel         How much to subtract for the first level of disabled original impl.
     * @param overwriteSubtractPerAdditionalLevel How much to subtract per additional level of disabled original impl.
     * @param applicableEntityType                E.g. EntityTypeTags.SENSITIVE_TO_SMITE
     */
    public static void applyMultiplicativeDamageForEntityType(
            LivingIncomingDamageEvent livingIncomingDamageEvent,
            ResourceKey<Enchantment> checkForEnchantment,
            ModConfigSpec.BooleanValue configOverwriteDefault,
            ModConfigSpec.BooleanValue configEnabled,
            ModConfigSpec.DoubleValue multiplyFirstLevelValue,
            ModConfigSpec.DoubleValue multiplyAdditionalLevels,
            float overwriteSubtractFirstLevel,
            float overwriteSubtractPerAdditionalLevel,
            TagKey<EntityType<?>> applicableEntityType
    ) {
        if (!livingIncomingDamageEvent.isCanceled()
                && livingIncomingDamageEvent.getEntity()
                .getType()
                .is(applicableEntityType)
        ) {
            applyMultiplicativeDamage(
                    livingIncomingDamageEvent,
                    checkForEnchantment,
                    configOverwriteDefault,
                    configEnabled, multiplyFirstLevelValue,
                    multiplyAdditionalLevels,
                    overwriteSubtractFirstLevel,
                    overwriteSubtractPerAdditionalLevel);
        }
    }

    /**
     * Util to apply multiplicative damage to a livingIncomingDamageEvent (when an entity takes damage).
     * Given the event, enchantment scaling configuration, and whether to overwrite the flat scaling default or not.
     *
     * @param livingIncomingDamageEvent           Event itself that is called when an entity is hit.
     * @param checkForEnchantment                 Enchantment to check for to apply this multiplier.
     * @param configOverwriteDefault              Config value whether to undo vanilla/original implementation.
     * @param configEnabled                       Config value for whether this multiplier is enabled at all.
     * @param multiplyFirstLevelValue             Config value for the multiplier for level 1.
     * @param multiplyAdditionalLevels            Config value for the multiplier added per level following.
     * @param overwriteSubtractFirstLevel         How much to subtract for the first level of disabled original impl.
     * @param overwriteSubtractPerAdditionalLevel How much to subtract per additional level of disabled original impl.
     */
    private static void applyMultiplicativeDamage(
            LivingIncomingDamageEvent livingIncomingDamageEvent,
            ResourceKey<Enchantment> checkForEnchantment,
            ModConfigSpec.BooleanValue configOverwriteDefault,
            ModConfigSpec.BooleanValue configEnabled,
            ModConfigSpec.DoubleValue multiplyFirstLevelValue,
            ModConfigSpec.DoubleValue multiplyAdditionalLevels,
            float overwriteSubtractFirstLevel,
            float overwriteSubtractPerAdditionalLevel
    ) {
        int enchantLevel = MultiplicativeEnchantmentUtils.getEnchantmentLevelGivenIncomingDamageEvent(
                livingIncomingDamageEvent,
                checkForEnchantment);
        System.out.println("Enchant level = " + enchantLevel);
        if (enchantLevel > 0) {
            float baseDamage = livingIncomingDamageEvent.getContainer()
                    .getOriginalDamage();
            float newDamage = livingIncomingDamageEvent.getContainer()
                    .getNewDamage();

            System.out.println("newDamage before addition = " + newDamage);
            if (enchantLevel > 1) {
                enchantLevel--; // Effective additional multiplier
            }
            if (configOverwriteDefault.isTrue()) {
                float vanillaSmiteSubtract = overwriteSubtractFirstLevel
                        + overwriteSubtractPerAdditionalLevel * (enchantLevel);
                baseDamage = baseDamage - vanillaSmiteSubtract; // baseDamage has Smite added too
                newDamage = newDamage - vanillaSmiteSubtract;
                System.out.println("Overwrite smite = " + baseDamage);
            }
            if (configEnabled.isTrue()) {
                System.out.println("Total multiplier bonus applied = "
                        + (multiplyFirstLevelValue.getAsDouble()
                        + multiplyAdditionalLevels.getAsDouble()
                        * enchantLevel) * baseDamage);

                newDamage = (float) (newDamage + baseDamage * (multiplyFirstLevelValue.getAsDouble()
                        + multiplyAdditionalLevels.getAsDouble()
                        * enchantLevel));
                System.out.println("Smite multiplier applied = " + newDamage);
            }
            livingIncomingDamageEvent
                    .getContainer()
                    .setNewDamage(Math.max(0.0f, newDamage));
        }
    }

}
