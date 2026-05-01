package com.darkmetaknight.multiplicative_enchantment_damage.common;

import net.neoforged.neoforge.common.ModConfigSpec;

import static com.darkmetaknight.multiplicative_enchantment_damage.common.Const.*;

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
     * Util to generate Enabled flag config for given enchantment
     */
    private static ModConfigSpec.BooleanValue buildOverwriteConfig(String enchantmentName) {
        return BUILDER
                .comment(String.format(CONFIG_DESC_OVERWRITE_DEFAULT, enchantmentName))
                .define(String.format(CONFIG_KEY_OVERWRITE_DEFAULT_ENABLED, enchantmentName), true);
    }

    /**
     * Util to generate first level scaling config for given enchantment
     */
    private static ModConfigSpec.ConfigValue<Float> buildFirstLevelConfig(
            String enchantmentName,
            float defaultVal
    ) {
        return BUILDER
                .comment(String.format(CONFIG_DESC_MULTIPLY_FIRST_LEVEL, enchantmentName, defaultVal))
                .defineInRange(String.format(CONFIG_KEY_LEVEL_ONE_SCALING, enchantmentName),
                        defaultVal, 0.01f, 99999.99f, Float.class);
    }

    /**
     * Util to generate additional level scaling per level for given enchantment
     */
    private static ModConfigSpec.ConfigValue<Float> buildAdditionalLevelConfig(
            String enchantmentName,
            float defaultVal
    ) {
        return BUILDER
                .comment(String.format(CONFIG_DESC_ADDITIONAL_SCALING, enchantmentName, defaultVal))
                .defineInRange(String.format(CONFIG_KEY_ADDITIONAL_SCALING, enchantmentName),
                        defaultVal, 0.01f, 99999.99f, Float.class);
    }

    public static final ModConfigSpec.BooleanValue SHARPNESS_ENABLED
            = buildEnabledConfig(SHARPNESS);
    public static final ModConfigSpec.BooleanValue SHARPNESS_OVERWRITE
            = buildOverwriteConfig(SHARPNESS);
    public static final ModConfigSpec.ConfigValue<Float> SHARPNESS_MULTIPLY_FIRST_LEVEL
            = buildFirstLevelConfig(SHARPNESS, 1.168f);
    public static final ModConfigSpec.ConfigValue<Float> SHARPNESS_MULTIPLY_ADDITIONAL_LEVELS
            = buildAdditionalLevelConfig(SHARPNESS, 0.083f);

    public static final ModConfigSpec.BooleanValue BANE_OF_ARTHROPODS_ENABLED
            = buildEnabledConfig(BANE_OF_ARTHROPODS);
    public static final ModConfigSpec.BooleanValue BANE_OF_ARTHROPODS_OVERWRITE
            = buildOverwriteConfig(BANE_OF_ARTHROPODS);
    public static final ModConfigSpec.ConfigValue<Float> BANE_OF_ARTHROPODS_MULTIPLY_FIRST_LEVEL
            = buildFirstLevelConfig(BANE_OF_ARTHROPODS, 0.53f);
    public static final ModConfigSpec.ConfigValue<Float> BANE_OF_ARTHROPODS_MULTIPLY_ADDITIONAL_LEVELS
            = buildAdditionalLevelConfig(BANE_OF_ARTHROPODS, 0.053f);

    public static final ModConfigSpec.BooleanValue SMITE_ENABLED
            = buildEnabledConfig(SMITE);
    public static final ModConfigSpec.BooleanValue SMITE_OVERWRITE
            = buildOverwriteConfig(SMITE);
    public static final ModConfigSpec.ConfigValue<Float> SMITE_MULTIPLY_FIRST_LEVEL
            = buildFirstLevelConfig(SMITE, 0.53f);
    public static final ModConfigSpec.ConfigValue<Float> SMITE_MULTIPLY_ADDITIONAL_LEVELS
            = buildAdditionalLevelConfig(SMITE, 0.053f);

    public static final ModConfigSpec SPEC = BUILDER.build();
}
