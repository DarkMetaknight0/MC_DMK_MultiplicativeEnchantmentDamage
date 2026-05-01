package com.darkmetaknight.multiplicative_enchantment_damage.mixins;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.darkmetaknight.multiplicative_enchantment_damage.common.Config.SHARPNESS_ENABLED;
import static com.darkmetaknight.multiplicative_enchantment_damage.common.Config.SHARPNESS_OVERWRITE;
import static com.darkmetaknight.multiplicative_enchantment_damage.enchantments.MultiplicativeEnchantmentUtils.applySharpnessDisableDefault;
import static com.darkmetaknight.multiplicative_enchantment_damage.enchantments.MultiplicativeEnchantmentUtils.applySharpnessMultiplier;

@Mixin(Enchantments.class)
public class EnchantmentsMixin {

    @Inject(method = "register", at = @At("HEAD"))
    private static void registerMixin(BootstrapContext<Enchantment> context,
                                      ResourceKey<Enchantment> key,
                                      Enchantment.Builder builder,
                                      CallbackInfo callbackInfo) {
        if (SHARPNESS_OVERWRITE.isTrue()) {
            applySharpnessDisableDefault(builder);
        }
        if (SHARPNESS_ENABLED.isTrue() && Enchantments.SHARPNESS.equals(key)) {
            applySharpnessMultiplier(builder);
        }
    }

}
