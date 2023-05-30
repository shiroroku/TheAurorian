package com.shiroroku.theaurorian;

import com.shiroroku.theaurorian.Misc.AurorianCreativeTab;
import com.shiroroku.theaurorian.Misc.TPTACommand;
import com.shiroroku.theaurorian.Network.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = AurorianMod.MODID, name = AurorianMod.NAME, version = AurorianMod.VERSION, dependencies = AurorianMod.VERSION_FORGE, acceptedMinecraftVersions = AurorianMod.VERSION_MINECRAFT)
public class AurorianMod {
    public static final String MODID = "theaurorian";
    public static final String NAME = "The Aurorian";
    public static final String VERSION = "1.12.2-Release";
    public static final String VERSION_MINECRAFT = "[1.12.2]";
    public static final String VERSION_FORGE = "after:tconstruct;after:conarm;required-after:forge@[14.23.5.2859,)";

    @Instance
    public static AurorianMod INSTANCE;
    public static final AurorianCreativeTab CREATIVE_TAB = new AurorianCreativeTab(MODID);
    public static Configuration CONFIG;

    @SidedProxy(clientSide = "com.shiroroku.theaurorian.Network.ClientProxy", serverSide = "com.shiroroku.theaurorian.Network.ServerProxy")
    public static CommonProxy proxy;

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new TPTACommand());
    }
}
