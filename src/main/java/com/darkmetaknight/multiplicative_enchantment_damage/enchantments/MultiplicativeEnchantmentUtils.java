package com.darkmetaknight.multiplicative_enchantment_damage.enchantments;

import com.darkmetaknight.multiplicative_enchantment_damage.common.Config;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.item.enchantment.effects.MultiplyValue;

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

}
