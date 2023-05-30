package com.shiroroku.theaurorian;

import com.shiroroku.theaurorian.Compat.Conarm.ConstructsArmoryCompat;
import com.shiroroku.theaurorian.Compat.TinkersConstruct.TinkersConstructCompat;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AurorianCompatibility {

	@SideOnly(Side.CLIENT)
	public static void clientPreInit(FMLPreInitializationEvent event) {
		if (Loader.isModLoaded("tconstruct") && AurorianConfig.Config_EnableTinkersConstructCompatibility) {
			TinkersConstructCompat.preInitSetMaterialRender();
		}
	}

	public static void preInit(FMLPreInitializationEvent event) {
		if (Loader.isModLoaded("tconstruct") && AurorianConfig.Config_EnableTinkersConstructCompatibility) {
			TinkersConstructCompat.preinitMetalsAndMaterials();
			if (Loader.isModLoaded("conarm") && AurorianConfig.Config_EnableConstructsArmoryCompatibility) {
				ConstructsArmoryCompat.preinitArmorTraits();
			}
		}
	}

	public static void init() {
		if (Loader.isModLoaded("tconstruct") && AurorianConfig.Config_EnableTinkersConstructCompatibility) {
			TinkersConstructCompat.initMaterials();
			if (Loader.isModLoaded("conarm") && AurorianConfig.Config_EnableConstructsArmoryCompatibility) {
				ConstructsArmoryCompat.initArmorMaterials();
			}
		}
	}

	public static void postInit(FMLPostInitializationEvent event) {
	}
}
