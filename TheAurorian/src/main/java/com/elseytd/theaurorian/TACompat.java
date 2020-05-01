package com.elseytd.theaurorian;

import com.elseytd.theaurorian.Compat.TinkersConstruct.TACompat_Tinkers;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class TACompat {

	public static void preInit(FMLPreInitializationEvent event) {
		if (Loader.isModLoaded("tconstruct") && TAConfig.Config_EnableTinkersConstructCompatibility) {
			TACompat_Tinkers.preinitMetalsAndMaterials();
		}
	}

	public static void init(FMLInitializationEvent event) {
		if (Loader.isModLoaded("tconstruct") && TAConfig.Config_EnableTinkersConstructCompatibility) {
			TACompat_Tinkers.initMaterials();
		}
	}

	public static void postInit(FMLPostInitializationEvent event) {
	}
}
