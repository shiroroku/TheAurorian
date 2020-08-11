package com.elseytd.theaurorian;

import com.elseytd.theaurorian.Misc.TACommandBase_TPTA;
import com.elseytd.theaurorian.Misc.TACreativeTab;
import com.elseytd.theaurorian.Network.CommonProxy;

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

@Mod(modid = TAMod.MODID, name = TAMod.NAME, version = TAMod.VERSION, dependencies = TAMod.VERSION_FORGE, acceptedMinecraftVersions = TAMod.VERSION_MINECRAFT)
public class TAMod {
	public static final String MODID = "theaurorian";
	public static final String NAME = "The Aurorian";
	public static final String VERSION = "1.12.2-Release";
	public static final String VERSION_MINECRAFT = "[1.12.2]";
	public static final String VERSION_FORGE = "after:tconstruct;after:conarm;required-after:forge@[14.23.5.2847,)";

	@Instance
	public static TAMod INSTANCE;
	public static final TACreativeTab CREATIVE_TAB = new TACreativeTab(MODID);
	public static Configuration CONFIG;

	@SidedProxy(clientSide = "com.elseytd.theaurorian.Network.ClientProxy", serverSide = "com.elseytd.theaurorian.Network.ServerProxy")
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
		event.registerServerCommand(new TACommandBase_TPTA());
	}
}
