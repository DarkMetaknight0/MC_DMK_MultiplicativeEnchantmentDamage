package com.darkmetaknight.multiplicative_enchantment_damage;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

import static com.darkmetaknight.multiplicative_enchantment_damage.Const.*;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    /**
     * Util to generate Enabled flag config for given enchantment
     */
    private static ModConfigSpec.BooleanValue buildEnabledConfig(String enchantmentName) {
        return BUILDER
                .comment(String.format(CONFIG_DESC_ENABLED, enchantmentName))
                .define(String.format(CONFIG_KEY_ENABLED, enchantmentName), true);
    }

    /**
     * Util to generate first level scaling config for given enchantment
     */
    private static ModConfigSpec.DoubleValue buildFirstLevelConfig(
            String enchantmentName,
            double defaultVal
    ) {
        return BUILDER
                .comment(String.format(CONFIG_DESC_MULTIPLY_FIRST_LEVEL, enchantmentName, defaultVal))
                .defineInRange(String.format(CONFIG_KEY_LEVEL_ONE_SCALING, enchantmentName), defaultVal, 0.01, 99999.99);
    }

    /**
     * Util to generate additional level scaling per level for given enchantment
     */
    private static ModConfigSpec.DoubleValue buildAdditionalLevelConfig(
            String enchantmentName,
            double defaultVal
    ) {
        return BUILDER
                .comment(String.format(CONFIG_DESC_ADDITIONAL_SCALING, enchantmentName, defaultVal))
                .defineInRange(String.format(CONFIG_KEY_ADDITIONAL_SCALING, enchantmentName), defaultVal, 0.01, 99999.99);
    }

    public static final ModConfigSpec.BooleanValue SHARPNESS_ENABLED
            = buildEnabledConfig(SHARPNESS);
    public static final ModConfigSpec.DoubleValue SHARPNESS_MULTIPLY_FIRST_LEVEL
            = buildFirstLevelConfig(SHARPNESS, 1.168);
    public static final ModConfigSpec.DoubleValue SHARPNESS_MULTIPLY_ADDITIONAL_LEVELS
            = buildAdditionalLevelConfig(SHARPNESS, 0.083);

    public static final ModConfigSpec.BooleanValue BANE_OF_ARTHROPODS_ENABLED
            = buildEnabledConfig(BANE_OF_ARTHROPODS);
    public static final ModConfigSpec.DoubleValue BANE_OF_ARTHROPODS_MULTIPLY_FIRST_LEVEL
            = buildFirstLevelConfig(BANE_OF_ARTHROPODS, 0.53);
    public static final ModConfigSpec.DoubleValue BANE_OF_ARTHROPODS_MULTIPLY_ADDITIONAL_LEVELS = BUILDER
            .comment("Bane of Arthropods: What the multiply factor is of additional levels. (e.g. 0.53 = +0.53x damage per level). Min 0.0, max 99999.99")
            .defineInRange(String.format(CONFIG_KEY_ADDITIONAL_SCALING, BANE_OF_ARTHROPODS), 0.53, 0.0, 99999.99);

    public static final ModConfigSpec.BooleanValue SMITE_ENABLED
            = buildEnabledConfig(SMITE);
    public static final ModConfigSpec.DoubleValue SMITE_MULTIPLY_FIRST_LEVEL
            = buildFirstLevelConfig(SMITE, 0.53);
    public static final ModConfigSpec.DoubleValue SMITE_MULTIPLY_ADDITIONAL_LEVELS = BUILDER
            .comment("Bane of Arthropods: What the multiply factor is of additional levels. (e.g. 0.53 = +0.53x damage per level). Min 0.0, max 99999.99")
            .defineInRange(String.format(CONFIG_KEY_ADDITIONAL_SCALING, SMITE), 0.53, 0.0, 99999.99);

    // Examples
    public static final ModConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);

    public static final ModConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

    public static final ModConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");

    // a list of strings that are treated as resource locations for items
    public static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), () -> "", Config::validateItemName);

    static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }
}
