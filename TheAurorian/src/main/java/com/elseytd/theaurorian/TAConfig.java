package com.elseytd.theaurorian;

import net.minecraftforge.common.config.Configuration;

public class TAConfig {

	//Compat
	public static boolean Config_EnableTinkersConstructCompatibility = true;

	//Blocks
	public static int Config_MaximumChimneys = 10;
	public static float Config_ChimneySpeedMuliplier = 0.5F;

	//Dimensions
	public static int Config_AurorianDimID = 24;

	//Structures
	public static boolean Config_GenerateRunestoneDungeon = true;
	public static int Config_DungeonDensity = 32;
	public static int Config_RunestoneDungeonFloors = 4;
	public static boolean Config_GenerateRuins = true;
	public static boolean Config_GenerateMoonTemple = true;
	public static boolean Config_GenerateMoonTemplePath = true;
	public static boolean Config_GenerateUmbraTower = false;

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
	public static int Config_AurorianiteAxeMaxChopSize = 256;
	public static float Config_LightningEnchantmentMulitplier = 0.20F;
	public static int Config_UmbraShieldTimeUntilOverheat = 60;
	public static int Config_UmbraShieldOverheatCooldown = 60;
	public static int Config_OrbOfAbsorptionWhitelistBlacklist = 0;
	public static String[] Config_OrbOfAbsorptionList = new String[] {};
	public static int Config_UmbraSwordCooldown = 900;

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
		Config_AurorianiteAxeMaxChopSize = cfg.getInt("AurorianiteAxeMaxChopSize", name, Config_AurorianiteAxeMaxChopSize, 0, 72000, "How many total connected log blocks can the Aurorianite Axe chop at once");
		Config_LightningEnchantmentMulitplier = cfg.getFloat("LightningEnchantmentMulitplier", name, Config_LightningEnchantmentMulitplier, 0F, 10F, "How much damage per armor piece the lightning enchantment should add (this multiplied by # of worn armor)");
		Config_UmbraShieldTimeUntilOverheat = cfg.getInt("UmbraShieldTimeUntilOverheat", name, Config_UmbraShieldTimeUntilOverheat, 0, 72000, "How long can the player use the shields fire ability until it overheats");
		Config_UmbraShieldOverheatCooldown = cfg.getInt("UmbraShieldOverheatCooldown", name, Config_UmbraShieldOverheatCooldown, 0, 72000, "Cooldown in ticks for the player to be able to use the shield again after it overheats");
		Config_OrbOfAbsorptionWhitelistBlacklist = cfg.getInt("OrbOfAbsorptionWhitelistBlacklist", name, Config_OrbOfAbsorptionWhitelistBlacklist, 0, 2, "Decides how to treat OrbOfAbsorptionList, 0 - ignored (Orb of Absorption can repair any damaged object), 1 - whitelist (can only repair items in the list), 2 - blacklist (repairs everything but items in the list)");
		Config_OrbOfAbsorptionList = cfg.getStringList("OrbOfAbsorptionList", name, Config_OrbOfAbsorptionList, "List of items, use is decided by OrbOfAbsorptionWhitelistBlacklist, you can also specify mod ids to whitelist or blacklist whole mods, ex: (tconstruct, minecraft:elytra)");
		Config_UmbraSwordCooldown = cfg.getInt("UmbraSwordCooldown", name, Config_UmbraSwordCooldown, 0, 72000, "Cooldown in ticks for the Umbra Swords ability");

	}

	private static void initCompatConfig(Configuration cfg) {
		String name = "compatibility";
		cfg.addCustomCategoryComment(name, "Compatibility configuration");

		Config_EnableTinkersConstructCompatibility = cfg.getBoolean("EnableTinkersConstructCompatibility", name, Config_EnableTinkersConstructCompatibility, "Set to false to disable Tinkers Construct integration");
	}

	private static void initBlocksConfig(Configuration cfg) {
		String name = "blocks";
		cfg.addCustomCategoryComment(name, "Blocks configuration");

		Config_MaximumChimneys = cfg.getInt("MaximumChimneys", name, Config_MaximumChimneys, 0, 255, "Maximum number of chimneys able to be stacked on Aurorian Furnace");
		Config_ChimneySpeedMuliplier = cfg.getFloat("ChimneySpeedMuliplier", name, Config_ChimneySpeedMuliplier, 0, 1, "Changes how much chimneys speed up the Aurorian Furnace when at maximum number (0.5 is + 50%)");
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

		Config_GenerateRunestoneDungeon = cfg.getBoolean("GenerateRunestoneDungeon", name, Config_GenerateRunestoneDungeon, "Set to false to disable Runestone Dungeons (Why would anyone do this? :c )");
		Config_DungeonDensity = cfg.getInt("DungeonDensity", name, Config_DungeonDensity, 16, 256, "How many chunks away until another Runestone Dungeons can generate, also affects Moon Temple generation");
		Config_RunestoneDungeonFloors = cfg.getInt("RunestoneDungeonFloors", name, Config_RunestoneDungeonFloors, 1, 17, "How many floors each Runestone Dungeon has, including double sized floors, code only accepts odd numbers! Evens will have +1 added");
		Config_GenerateRuins = cfg.getBoolean("GenerateRuins", name, Config_GenerateRuins, "Set to false to disable ruin structures (like destroyed houses or small underground structures)");
		Config_GenerateMoonTemple = cfg.getBoolean("GenerateMoonTemple", name, Config_GenerateMoonTemple, "Set to false to disable Moon Temples");
		Config_GenerateMoonTemplePath = cfg.getBoolean("GenerateMoonTemplePath", name, Config_GenerateMoonTemplePath, "Set to false to disable Moon Temple's spiral path up");
		Config_GenerateUmbraTower = cfg.getBoolean("GenerateUmbraTower", name, Config_GenerateUmbraTower, "Set to false to disable Umbra Towers");

	}

	private static void initDimensionConfig(Configuration cfg) {
		cfg.addCustomCategoryComment("dimensions", "Dimension configuration");
		Config_AurorianDimID = cfg.getInt("AurorianDimID", "dimensions", Config_AurorianDimID, -1000, 1000, "ID to use for the aurorian dimension");
	}
}
