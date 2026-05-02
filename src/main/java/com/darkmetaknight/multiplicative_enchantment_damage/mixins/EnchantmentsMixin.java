//package com.darkmetaknight.multiplicative_enchantment_damage.mixins;
//
//import com.darkmetaknight.multiplicative_enchantment_damage.common.PreConfig;
//import net.minecraft.data.worldgen.BootstrapContext;
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.world.item.enchantment.Enchantment;
//import net.minecraft.world.item.enchantment.Enchantments;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import static com.darkmetaknight.multiplicative_enchantment_damage.enchantments.MultiplicativeEnchantmentUtils.applySharpnessDisableDefault;
//import static com.darkmetaknight.multiplicative_enchantment_damage.enchantments.MultiplicativeEnchantmentUtils.applySharpnessMultiplier;
//
//@Mixin(Enchantments.class)
//public class EnchantmentsMixin {
//
//    @Inject(method = "register", at = @At("HEAD"))
//    private static void registerMixin(BootstrapContext<Enchantment> context,
//                                      ResourceKey<Enchantment> key,
//                                      Enchantment.Builder builder,
//                                      CallbackInfo callbackInfo) {
//        PreConfig preConfig = new PreConfig(
//                true, true,
//                1.68f, 0.083f
//        );
/// /        try {
/// /            Path path = Path.of("/data/multiplicativeenchantmentdamage.json");
/// /            try (InputStream inputStream = Files.newInputStream(path)) {
/// /                Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
/// /                preConfig = new Gson().fromJson(reader, PreConfig.class);
/// /            }
/// /        } catch (IOException e) {
/// /            throw new RuntimeException(e);
/// /        }
//        if (preConfig.sharpnessOverwrite) {
//            System.out.println(String.format("THE MIXIN HAS RUN: OVERWRITE"));
//            applySharpnessDisableDefault(builder);
//        }
//        if (preConfig.sharpnessEnabled && Enchantments.SHARPNESS.equals(key)) {
//            System.out.println(String.format("THE MIXIN HAS RUN: ENABLED"));
//            applySharpnessMultiplier(preConfig, builder);
//        }
//        System.out.println(String.format("THE MIXIN HAS RUN"));
//    }
//
//}
