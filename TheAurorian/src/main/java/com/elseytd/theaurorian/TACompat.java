package com.elseytd.theaurorian;

import com.elseytd.theaurorian.Compat.Conarm.TACompat_Conarm;
import com.elseytd.theaurorian.Compat.TinkersConstruct.TACompat_Tinkers;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TACompat {

	@SideOnly(Side.CLIENT)
	public static void clientPreInit(FMLPreInitializationEvent event) {
		if (Loader.isModLoaded("tconstruct") && TAConfig.Config_EnableTinkersConstructCompatibility) {
			TACompat_Tinkers.preInitSetMaterialRender();
		}
	}

	public static void preInit(FMLPreInitializationEvent event) {
		if (Loader.isModLoaded("tconstruct") && TAConfig.Config_EnableTinkersConstructCompatibility) {
			TACompat_Tinkers.preinitMetalsAndMaterials();
			if (Loader.isModLoaded("conarm") && TAConfig.Config_EnableConstructsArmoryCompatibility) {
				TACompat_Conarm.preinitArmorTraits();
			}
		}
	}

	public static void init() {
		if (Loader.isModLoaded("tconstruct") && TAConfig.Config_EnableTinkersConstructCompatibility) {
			TACompat_Tinkers.initMaterials();
			if (Loader.isModLoaded("conarm") && TAConfig.Config_EnableConstructsArmoryCompatibility) {
				TACompat_Conarm.initArmorMaterials();
			}
		}
	}

	public static void postInit(FMLPostInitializationEvent event) {
	}
}
