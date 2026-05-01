//package com.darkmetaknight.multiplicative_enchantment_damage.enchantments;
//
//import net.minecraft.core.HolderSet;
//import net.minecraft.core.component.DataComponentMap;
//import net.minecraft.network.chat.Component;
//import net.minecraft.world.entity.EquipmentSlotGroup;
//import net.minecraft.world.item.enchantment.Enchantment;
//import net.minecraft.world.item.enchantment.Enchantments;
//import net.neoforged.neoforge.registries.DeferredHolder;
//
//import java.util.List;
//import java.util.Optional;
//
//import static com.darkmetaknight.multiplicative_enchantment_damage.MultiplicativeEnchantmentDamage.ENCHANTMENTS;
//
//public class Sharpness extends Enchantments {
//
//
//    public static final DeferredHolder<Enchantment, Enchantment> YOUR_ENCHANTMENT =
//            ENCHANTMENTS.register("your_enchantment_name", () -> new Enchantment(
//                    Component.literal("Your Enchantment Name"),
//                    new Enchantment.EnchantmentDefinition(
//                            HolderSet.direct(/* Items compatible with this enchantment */),
//                            Optional.empty(), // Primary items
//                            30,              // Weight
//                            3,               // Max level
//                            Enchantment.dynamicCost(3, 1), // Min cost (base, per level)
//                            Enchantment.dynamicCost(4, 2), // Max cost (base, per level)
//                            2,               // Anvil cost
//                            List.of(EquipmentSlotGroup.MAINHAND), // Applicable slots
//                            HolderSet.empty(), // Incompatible enchantments
//                            DataComponentMap.builder().build() // Custom effect components
//                    )
//            ));
//}
