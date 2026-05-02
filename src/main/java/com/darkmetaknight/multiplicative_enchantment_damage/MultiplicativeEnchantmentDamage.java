package com.darkmetaknight.multiplicative_enchantment_damage;

import com.darkmetaknight.multiplicative_enchantment_damage.common.Config;
import com.darkmetaknight.multiplicative_enchantment_damage.enchantments.MultiplicativeEnchantmentUtils;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

import static com.darkmetaknight.multiplicative_enchantment_damage.common.Const.LOG_FORMAT;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MultiplicativeEnchantmentDamage.MOD_ID)
public class MultiplicativeEnchantmentDamage {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "multiplicativeenchantmentdamage";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public MultiplicativeEnchantmentDamage(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (MultiplicativeEnchantmentDamage) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info(LOG_FORMAT, "Init complete for multiplicativeEnchantmentDamage!", MOD_ID);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @SubscribeEvent
    public static void multiplicativeSharpness(LivingIncomingDamageEvent livingIncomingDamageEvent) {
        int enchantLevel = MultiplicativeEnchantmentUtils.getEnchantmentLevelGivenIncomingDamageEvent(
                livingIncomingDamageEvent,
                Enchantments.SHARPNESS);

    }

}
