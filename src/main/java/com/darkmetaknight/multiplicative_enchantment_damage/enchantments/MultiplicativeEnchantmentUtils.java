package com.darkmetaknight.multiplicative_enchantment_damage.enchantments;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
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

}
