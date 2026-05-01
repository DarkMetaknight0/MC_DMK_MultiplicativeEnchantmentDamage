package com.darkmetaknight.multiplicative_enchantment_damage;

public class Const {
    // variable key formats
    public static final String CONFIG_KEY_ENABLED = "%s_multiply_override";
    public static final String CONFIG_DESC_ENABLED
            = "%s: Whether to override (multiply) the enchantment at all.";
    public static final String CONFIG_KEY_LEVEL_ONE_SCALING = "%s_multiply_initial";
    public static final String CONFIG_DESC_MULTIPLY_FIRST_LEVEL
            = "%s: What the multiply factor is of the first level. (e.g. 1.168 = 1.168 times damage). Default %s, min 0.01, max 99999.99";
    public static final String CONFIG_KEY_ADDITIONAL_SCALING = "%s_multiply_additional";
    public static final String CONFIG_DESC_ADDITIONAL_SCALING
            = "%s: What the multiply factor is of additional levels. (e.g. 0.083 = +0.083 times damage per level). Default %s, min 0.01, max 99999.99";

    // enchantment names
    public static final String SHARPNESS = "sharpness";
    public static final String BANE_OF_ARTHROPODS = "bane_of_arthropods";
    public static final String SMITE = "smite";

}
