package com.darkmetaknight.multiplicative_enchantment_damage.mixins;

import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Enchantments.class)
public class EnchantmentsRegisterMixin {

    // TODO
    //  shadow Enchantments.register method
    //  Use e.g. Enchantments.SHARPNESS for the second argument to check against if it is equal.
    //  call, at HEAD target, builder.withEffect(new MultiplyValue(LevelBasedValue...))
    public void overrideSharpnessRegisterMixin() {

    }
}
