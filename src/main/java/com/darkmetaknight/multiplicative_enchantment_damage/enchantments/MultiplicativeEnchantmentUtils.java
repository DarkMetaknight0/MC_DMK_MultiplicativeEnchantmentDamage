package com.darkmetaknight.multiplicative_enchantment_damage.enchantments;

import com.darkmetaknight.multiplicative_enchantment_damage.common.Config;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.item.enchantment.effects.MultiplyValue;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.Optional;

public class MultiplicativeEnchantmentUtils {

    public static void applySharpnessMultiplier(
            Enchantment.Builder builder
    ) {
        builder.withEffect(EnchantmentEffectComponents.DAMAGE,
                new MultiplyValue(LevelBasedValue.perLevel(
                        Config.SHARPNESS_MULTIPLY_FIRST_LEVEL.get(), Config.SHARPNESS_MULTIPLY_ADDITIONAL_LEVELS.get()))
        );
    }

    public static void applySharpnessDisableDefault(
            Enchantment.Builder builder
    ) {
        builder.withEffect(EnchantmentEffectComponents.DAMAGE,
                new AddValue(LevelBasedValue.perLevel(-1.0F, -0.5F)));
    }

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

}
