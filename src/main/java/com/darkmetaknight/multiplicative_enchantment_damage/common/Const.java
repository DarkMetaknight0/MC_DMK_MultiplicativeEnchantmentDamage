package com.darkmetaknight.multiplicative_enchantment_damage.common;

public class Const {
    // variable key formats
    public static final String CONFIG_KEY_ENABLED = "%s_multiply_override";
    public static final String CONFIG_DESC_ENABLED
            = " %s: Whether to override (multiply) the enchantment at all.";
    public static final String CONFIG_KEY_OVERWRITE_DEFAULT_ENABLED = "%s_overwrite_default";
    public static final String CONFIG_DESC_OVERWRITE_DEFAULT
            = " %s: Whether to add an additional effect that is reversed from the default, effectively removing it.";
    public static final String CONFIG_KEY_LEVEL_ONE_SCALING = "%s_multiply_initial";
    public static final String CONFIG_DESC_MULTIPLY_FIRST_LEVEL
            = " %s: What the multiply factor is of the first level. (e.g. 0.168 = +16.8 percent damage). Default %s, min 0.01, max 99999.99";
    public static final String CONFIG_KEY_ADDITIONAL_SCALING = "%s_multiply_additional";
    public static final String CONFIG_DESC_ADDITIONAL_SCALING
            = " %s: What the multiply factor is of additional levels. (e.g. 0.083 = +8.3 percent base damage per level). Default %s, min 0.01, max 99999.99";

    // enchantment names
    public static final String SHARPNESS = "sharpness";
    public static final String BANE_OF_ARTHROPODS = "bane_of_arthropods";
    public static final String SMITE = "smite";
    public static final String IMPALING = "impaling";

    // LOGGING
    public static final String INFO = "INFO";
    public static final String WARN = "WARN";
    public static final String LOG_FORMAT = "{}: %s | source_mod={}";
    public static final String LOG_FORMAT_ERROR = "ERROR: {} | source_mod={} | stack_trace={}";

}
