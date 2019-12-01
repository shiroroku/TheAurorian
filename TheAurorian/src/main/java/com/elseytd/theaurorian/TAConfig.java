package com.elseytd.theaurorian;

import net.minecraftforge.common.config.Configuration;

public class TAConfig {

	//Compat
	public static boolean Config_EnableTinkersConstructCompatibility = true;

	//Blocks
	public static int Config_MaximumChimneys = 10;
	public static int Config_ChimneySpeedDiscount = 25;

	//Dimensions
	public static int Config_AurorianDimId = 24;

	//Structures
	public static boolean Config_GenerateRunestoneTower = true;
	public static int Config_DungeonDensity = 32;
	public static int Config_RunestoneTowerFloors = 4;
	public static boolean Config_GenerateRuins = true;
	public static boolean Config_GenerateMoonTemple = true;
	public static boolean Config_GenerateMoonTemplePath = true;

	//Generation
	public static int Config_AurorianCoalOre_Size = 12;
	public static int Config_AurorianCoalOre_Count = 13;
	public static int Config_AurorianCoalOre_HeightMin = 40;
	public static int Config_AurorianCoalOre_HeightMax = 128;
	public static int Config_CeruleanOre_Size = 7;
	public static int Config_CeruleanOre_Count = 13;
	public static int Config_CeruleanOre_HeightMin = 5;
	public static int Config_CeruleanOre_HeightMax = 60;
	public static int Config_MoonstoneOre_Size = 5;
	public static int Config_MoonstoneOre_Count = 10;
	public static int Config_MoonstoneOre_HeightMin = 5;
	public static int Config_MoonstoneOre_HeightMax = 30;
	public static int Config_GeodeOre_Size = 5;
	public static int Config_GeodeOre_Count = 8;
	public static int Config_GeodeOre_HeightMin = 5;
	public static int Config_GeodeOre_HeightMax = 128;

	//Misc
	public static String[] Config_PortalLighter = new String[] { "minecraft:flint_and_steel" };
	public static boolean Config_SticksMakeFire = true;
	public static int Config_AurorianiteSwordCooldown = 600;
	public static int Config_StrangeMeatUses = 10;

	public static void readConfig() {
		Configuration cfg = TAMod.CONFIG;
		try {
			cfg.load();
			initCompatConfig(cfg);
			initBlocksConfig(cfg);
			initDimensionConfig(cfg);
			initStructureConfig(cfg);
			initGenerationConfig(cfg);
			initMiscConfig(cfg);
		} catch (Exception e1) {
		} finally {
			if (cfg.hasChanged()) {
				cfg.save();
			}
		}
	}

	private static void initMiscConfig(Configuration cfg) {
		String name = "misc";
		cfg.addCustomCategoryComment(name, "Misc configuration");

		Config_PortalLighter = cfg.getStringList("PortalLighter", name, Config_PortalLighter, "Can change the item(s) used to light the portal here (Uses item's unlocalized name)");
		Config_SticksMakeFire = cfg.getBoolean("SticksMakeFire", name, Config_SticksMakeFire, "Set to false to disable Silentwood Sticks making Fire or lighting Portal");		
		Config_AurorianiteSwordCooldown = cfg.getInt("AurorianiteSwordCooldown", name, Config_AurorianiteSwordCooldown, 0, 72000, "Cooldown in ticks for the swords levitate ability. 600 ticks = 30 seconds");
		Config_StrangeMeatUses = cfg.getInt("StrangeMeatUses", name, Config_StrangeMeatUses, 1, 72000, "How many uses Strange Meat has");

	}

	private static void initCompatConfig(Configuration cfg) {
		String name = "compatibility";
		cfg.addCustomCategoryComment(name, "Compatibility configuration");

		Config_EnableTinkersConstructCompatibility = cfg.getBoolean("EnableTinkersConstructCompatibility", name, Config_EnableTinkersConstructCompatibility, "Set to false to disable Tinkers Construct integration");
	}

	private static void initBlocksConfig(Configuration cfg) {
		String name = "blocks";
		cfg.addCustomCategoryComment(name, "Blocks configuration");

		Config_MaximumChimneys = cfg.getInt("MaximumChimneys", name, Config_MaximumChimneys, 0, 255, "Maximum number of chimneys able to be stacked on Aurorian Furnace that enable the discount");
		Config_ChimneySpeedDiscount = cfg.getInt("ChimneySpeedDiscount", name, Config_ChimneySpeedDiscount, 0, 2000, "Changes how much a chimney speeds up the Aurorian Furnace (Every chimney is 5% less effective)");
	}

	private static void initGenerationConfig(Configuration cfg) {
		String name = "generation";
		cfg.addCustomCategoryComment(name, "Generation configuration");

		Config_AurorianCoalOre_Size = cfg.getInt("AurorianCoalOreSize", name, Config_AurorianCoalOre_Size, 0, 50, "Changes the size of ore vein");
		Config_AurorianCoalOre_Count = cfg.getInt("AurorianCoalOreCount", name, Config_AurorianCoalOre_Count, 0, 50, "Changes the size of ore vein");
		Config_AurorianCoalOre_HeightMin = cfg.getInt("AurorianCoalOreHeightMin", name, Config_AurorianCoalOre_HeightMin, 0, 255, "Changes the min height of ore vein");
		Config_AurorianCoalOre_HeightMax = cfg.getInt("AurorianCoalOreHeightMax", name, Config_AurorianCoalOre_HeightMax, 0, 255, "Changes the max height of ore vein");
		Config_CeruleanOre_Size = cfg.getInt("CeruleanOreSize", name, Config_CeruleanOre_Size, 0, 50, "Changes the size of ore vein");
		Config_CeruleanOre_Count = cfg.getInt("CeruleanOreCount", name, Config_CeruleanOre_Count, 0, 50, "Changes the size of ore vein");
		Config_CeruleanOre_HeightMin = cfg.getInt("CeruleanOreHeightMin", name, Config_CeruleanOre_HeightMin, 0, 255, "Changes the min height of ore vein");
		Config_CeruleanOre_HeightMax = cfg.getInt("CeruleanOreHeightMax", name, Config_CeruleanOre_HeightMax, 0, 255, "Changes the max height of ore vein");
		Config_MoonstoneOre_Size = cfg.getInt("MoonstoneOreSize", name, Config_MoonstoneOre_Size, 0, 50, "Changes the size of ore vein");
		Config_MoonstoneOre_Count = cfg.getInt("MoonstoneOreCount", name, Config_MoonstoneOre_Count, 0, 50, "Changes the size of ore vein");
		Config_MoonstoneOre_HeightMin = cfg.getInt("MoonstoneOreHeightMin", name, Config_MoonstoneOre_HeightMin, 0, 255, "Changes the min height of ore vein");
		Config_MoonstoneOre_HeightMax = cfg.getInt("MoonstoneOreHeightMax", name, Config_MoonstoneOre_HeightMax, 0, 255, "Changes the max height of ore vein");
		Config_GeodeOre_Size = cfg.getInt("GeodeOreSize", name, Config_GeodeOre_Size, 0, 50, "Changes the size of ore vein");
		Config_GeodeOre_Count = cfg.getInt("GeodeOreCount", name, Config_GeodeOre_Count, 0, 50, "Changes the size of ore vein");
		Config_GeodeOre_HeightMin = cfg.getInt("GeodeOreHeightMin", name, Config_GeodeOre_HeightMin, 0, 255, "Changes the min height of ore vein");
		Config_GeodeOre_HeightMax = cfg.getInt("GeodeOreHeightMax", name, Config_GeodeOre_HeightMax, 0, 255, "Changes the max height of ore vein");
	}

	private static void initStructureConfig(Configuration cfg) {
		String name = "structures";
		cfg.addCustomCategoryComment(name, "Structure configuration");

		Config_GenerateRunestoneTower = cfg.getBoolean("GenerateRunestoneTower", name, Config_GenerateRunestoneTower, "Set to false to disable ruinstonetowers (Why would anyone do this? :c )");
		Config_DungeonDensity = cfg.getInt("DungeonDensity", name, Config_DungeonDensity, 16, 256, "How many chunks away until another ruinstonetower can generate, also affects moontemple generation");
		Config_RunestoneTowerFloors = cfg.getInt("RunestoneTowerFloors", name, Config_RunestoneTowerFloors, 1, 17, "How many floors each ruinstonetower has, including double sized floors, code only accepts odd numbers! Evens will have +1 added");
		Config_GenerateRuins = cfg.getBoolean("GenerateRuins", name, Config_GenerateRuins, "Set to false to disable ruin structures (like destroyed houses or small underground structures)");
		Config_GenerateMoonTemple = cfg.getBoolean("GenerateMoonTemple", name, Config_GenerateMoonTemple, "Set to false to disable moon temples");
		Config_GenerateMoonTemplePath = cfg.getBoolean("GenerateMoonTemplePath", name, Config_GenerateMoonTemplePath, "Set to false to disable moon temple's spiral path up");
	}

	private static void initDimensionConfig(Configuration cfg) {
		cfg.addCustomCategoryComment("dimensions", "Dimension configuration");
		Config_AurorianDimId = cfg.getInt("AurorianDimId", "dimensions", Config_AurorianDimId, -1000, 1000, "Id to use for the aurorian dimension");
	}
}
