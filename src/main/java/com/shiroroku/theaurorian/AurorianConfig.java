package com.shiroroku.theaurorian;

import net.minecraftforge.common.config.Configuration;

public class AurorianConfig {

	//TODO: fix multiplier mispelling sometime

	//Compat
	public static boolean Config_EnableTinkersConstructCompatibility = true;
	public static boolean Config_EnableConstructsArmoryCompatibility = true;

	//Blocks
	public static int Config_MaximumChimneys = 10;
	public static float Config_ChimneySpeedMuliplier = 0.5F;
	public static boolean Config_CrystalsSpeedUpMachines = true;
	public static float Config_CrystalsChanceOfBreaking = 0.25F;
	public static float Config_CrystalsSpeedReduction = 0.5F;
	public static int Config_ScrapperTickInterval = 4; //TODO make this more user friendly

	//Dimensions
	public static int Config_AurorianDimID = 424;

	//Structures
	public static boolean Config_GenerateRunestoneDungeon = true;
	public static int Config_DungeonDensity = 32;
	public static int Config_RunestoneDungeonFloors = 4;
	public static boolean Config_GenerateRuins = true;
	public static boolean Config_GenerateMoonTemple = true;
	public static boolean Config_GenerateMoonTemplePath = true;
	public static boolean Config_GenerateDarkstoneDungeon = true;
	public static boolean Config_GenerateUmbraTower = true;
	public static boolean Config_GenerateMushroomCaves = false;
	public static boolean Config_GenerateGraveyards = true;

	//Entities

	public static boolean Config_NIGHTMAREMODE = false;
	public static float Config_NIGHTMAREMODE_Multiplier = 1;
	public static int Config_RunestoneDungeonMobDensity = 1;
	public static int Config_MoonTempleMobDensity = 1;
	public static int Config_DarkstoneDungeonMobDensity = 1;
	public static float Config_RunestoneKeeperHealthMuliplier = 1;
	public static float Config_MoonQueenHealthMuliplier = 1;
	public static float Config_SpiderMotherHealthMuliplier = 1;
	public static float Config_RunestoneKeeperDamageMuliplier = 1;
	public static float Config_MoonQueenDamageMuliplier = 1;
	public static float Config_SpiderMotherDamageMuliplier = 1;

	//Generation, vanilla values found in net.minecraft.world.gen.ChunkGeneratorSettings.ChunkGeneratorSettings(Factory)
	public static int Config_AurorianCoalOre_Size = 12;
	public static int Config_AurorianCoalOre_Count = 13;
	public static int Config_AurorianCoalOre_HeightMin = 40;
	public static int Config_AurorianCoalOre_HeightMax = 128;
	public static int Config_CeruleanOre_Size = 7;
	public static int Config_CeruleanOre_Count = 13;
	public static int Config_CeruleanOre_HeightMin = 5;
	public static int Config_CeruleanOre_HeightMax = 60;
	public static int Config_MoonstoneOre_Size = 9;
	public static int Config_MoonstoneOre_Count = 2;
	public static int Config_MoonstoneOre_HeightMin = 5;
	public static int Config_MoonstoneOre_HeightMax = 30;
	public static int Config_GeodeOre_Size = 5;
	public static int Config_GeodeOre_Count = 8;
	public static int Config_GeodeOre_HeightMin = 5;
	public static int Config_GeodeOre_HeightMax = 128;
	public static int Config_Peridotite_Size = 33;
	public static int Config_Peridotite_Count = 10;
	public static int Config_Peridotite_HeightMin = 5;
	public static int Config_Peridotite_HeightMax = 80;
	public static int Config_Dirt_Size = 33;
	public static int Config_Dirt_Count = 10;
	public static int Config_Dirt_HeightMin = 5;
	public static int Config_Dirt_HeightMax = 256;
	public static boolean Config_GenerateUrns = true;

	//Misc
	public static String[] Config_PortalLighter = new String[] { "minecraft:flint_and_steel" };
	public static boolean Config_SticksMakeFire = true;
	public static int Config_AurorianiteSwordCooldown = 600;
	public static int Config_StrangeMeatUses = 10;
	public static int Config_AurorianiteAxeMaxChopSize = 256;
	public static float Config_LightningEnchantmentMulitplier = 0.20F;
	public static int Config_UmbraShieldTimeUntilOverheat = 60;
	public static int Config_UmbraShieldOverheatCooldown = 60;
	public static int Config_OrbOfAbsorptionDurability = 250;
	public static int Config_OrbOfAbsorptionWhitelistBlacklist = 2;
	public static String[] Config_OrbOfAbsorptionList = new String[] { "theaurorian:absorptionorb", "theaurorian:strangemeat" };
	public static int Config_UmbraSwordCooldown = 900;
	public static int Config_SlimeBootsCooldown = 100;
	public static float Config_UmbraPickaxeMiningSpeedMultiplier = 2.0f;
	public static boolean Config_MoonlightForgeTransfersEnchants = true;
	public static int Config_AurorianSteel_BaseMaxLevel = 100;
	public static float Config_AurorianSteel_BaseMaxLevelMultiplier = 1.75f;
	public static String[] Config_AurorianSteel_Enchants = new String[] {};
	public static int Config_AurorianSteel_Enchants_WhitelistBlacklist = 0;
	public static int Config_CrystalStackSize = 16;
	public static float Config_Spectral_Armor_CleanseChance = 0.06f;

	//Multipliers
	public static float Config_Silentwood_Multiplier_Speed = 1F;
	public static float Config_Silentwood_Multiplier_Durability = 1F;
	public static int Config_Silentwood_HarvestLevel = 0;
	public static float Config_AurorianStone_Multiplier_Damage = 1F;
	public static float Config_AurorianStone_Multiplier_Speed = 1F;
	public static float Config_AurorianStone_Multiplier_Durability = 1F;
	public static int Config_AurorianStone_HarvestLevel = 1;
	public static float Config_Moonstone_Multiplier_Damage = 1F;
	public static float Config_Moonstone_Multiplier_Speed = 1F;
	public static float Config_Moonstone_Multiplier_Durability = 1F;
	public static int Config_Moonstone_HarvestLevel = 2;
	public static float Config_Special_Multiplier_Damage = 1F;
	public static float Config_Special_Multiplier_Speed = 1F;
	public static float Config_Special_Multiplier_Durability = 1F;
	public static int Config_Special_HarvestLevel = 3;
	public static float Config_AurorianSteel_Multiplier_Damage = 1F;
	public static float Config_AurorianSteel_Multiplier_Speed = 1F;
	public static float Config_AurorianSteel_Multiplier_Durability = 1F;
	public static float Config_AurorianSteel_Multiplier_Armor = 1F;
	public static int Config_AurorianSteel_HarvestLevel = 3;
	public static float Config_Cerulean_Multiplier_Durability = 1F;
	public static float Config_Cerulean_Multiplier_Armor = 1F;
	public static float Config_Tea_EffectDuration_Muliplier = 1F;
	public static float Config_Spectral_Multiplier_Durability = 1F;
	public static float Config_Spectral_Multiplier_Armor = 1F;

	public static void readConfig() {
		Configuration cfg = AurorianMod.CONFIG;
		try {
			cfg.load();
			initCompatConfig(cfg);
			initBlocksConfig(cfg);
			initDimensionConfig(cfg);
			initStructureConfig(cfg);
			initEntityConfig(cfg);
			initGenerationConfig(cfg);
			initMiscConfig(cfg);
			initMultiplierConfig(cfg);
		} catch (Exception e1) {
		} finally {
			if (cfg.hasChanged()) {
				cfg.save();
			}
		}
	}

	private static void initMultiplierConfig(Configuration cfg) {
		String name = "multipliers";
		cfg.addCustomCategoryComment(name, "Tool and Armor multipliers");

		Config_Silentwood_Multiplier_Speed = cfg.getFloat("Silentwood_Multiplier_Speed", name, Config_Silentwood_Multiplier_Speed, 0F, 1000F, "Multiplier for mining speed");
		Config_Silentwood_Multiplier_Durability = cfg.getFloat("Silentwood_Multiplier_Durability", name, Config_Silentwood_Multiplier_Durability, 0F, 1000F, "Multiplier for tool/armor durability");
		Config_Silentwood_HarvestLevel = cfg.getInt("Silentwood_HarvestLevel", name, Config_Silentwood_HarvestLevel, 0, 500, "Harvest level for these tools");
		Config_AurorianStone_Multiplier_Damage = cfg.getFloat("AurorianStone_Multiplier_Damage", name, Config_AurorianStone_Multiplier_Damage, 0F, 1000F, "Multiplier for damage");
		Config_AurorianStone_Multiplier_Speed = cfg.getFloat("AurorianStone_Multiplier_Speed", name, Config_AurorianStone_Multiplier_Speed, 0F, 1000F, "Multiplier for mining speed");
		Config_AurorianStone_Multiplier_Durability = cfg.getFloat("AurorianStone_Multiplier_Durability", name, Config_AurorianStone_Multiplier_Durability, 0F, 1000F, "Multiplier for tool/armor durability");
		Config_AurorianStone_HarvestLevel = cfg.getInt("AurorianStone_HarvestLevel", name, Config_AurorianStone_HarvestLevel, 0, 500, "Harvest level for these tools");
		Config_Moonstone_Multiplier_Damage = cfg.getFloat("Moonstone_Multiplier_Damage", name, Config_Moonstone_Multiplier_Damage, 0F, 1000F, "Multiplier for damage");
		Config_Moonstone_Multiplier_Speed = cfg.getFloat("Moonstone_Multiplier_Speed", name, Config_Moonstone_Multiplier_Speed, 0F, 1000F, "Multiplier for mining speed");
		Config_Moonstone_Multiplier_Durability = cfg.getFloat("Moonstone_Multiplier_Durability", name, Config_Moonstone_Multiplier_Durability, 0F, 1000F, "Multiplier for tool/armor durability");
		Config_Moonstone_HarvestLevel = cfg.getInt("Moonstone_HarvestLevel", name, Config_Moonstone_HarvestLevel, 0, 500, "Harvest level for these tools");
		Config_Special_Multiplier_Damage = cfg.getFloat("Special_Multiplier_Damage", name, Config_Special_Multiplier_Damage, 0F, 1000F, "Multiplier for damage");
		Config_Special_Multiplier_Speed = cfg.getFloat("Special_Multiplier_Speed", name, Config_Special_Multiplier_Speed, 0F, 1000F, "Multiplier for mining speed");
		Config_Special_Multiplier_Durability = cfg.getFloat("Special_Multiplier_Durability", name, Config_Special_Multiplier_Durability, 0F, 1000F, "Multiplier for tool/armor durability");
		Config_Special_HarvestLevel = cfg.getInt("Special_HarvestLevel", name, Config_Special_HarvestLevel, 0, 500, "Harvest level for these tools");
		Config_AurorianSteel_Multiplier_Damage = cfg.getFloat("AurorianSteel_Multiplier_Damage", name, Config_AurorianSteel_Multiplier_Damage, 0F, 1000F, "Multiplier for damage");
		Config_AurorianSteel_Multiplier_Speed = cfg.getFloat("AurorianSteel_Multiplier_Speed", name, Config_AurorianSteel_Multiplier_Speed, 0F, 1000F, "Multiplier for mining speed");
		Config_AurorianSteel_Multiplier_Durability = cfg.getFloat("AurorianSteel_Multiplier_Durability", name, Config_AurorianSteel_Multiplier_Durability, 0F, 1000F, "Multiplier for tool/armor durability");
		Config_AurorianSteel_Multiplier_Armor = cfg.getFloat("AurorianSteel_Multiplier_Armor", name, Config_AurorianSteel_Multiplier_Armor, 0F, 1000F, "Multiplier for armor strength");
		Config_AurorianSteel_HarvestLevel = cfg.getInt("AurorianSteel_HarvestLevel", name, Config_AurorianSteel_HarvestLevel, 0, 500, "Harvest level for these tools");
		Config_Cerulean_Multiplier_Durability = cfg.getFloat("Cerulean_Multiplier_Durability", name, Config_Cerulean_Multiplier_Durability, 0F, 1000F, "Multiplier for tool/armor durability");
		Config_Cerulean_Multiplier_Armor = cfg.getFloat("Cerulean_Multiplier_Armor", name, Config_Cerulean_Multiplier_Armor, 0F, 1000F, "Multiplier for armor strength");
		Config_Tea_EffectDuration_Muliplier = cfg.getFloat("Tea_EffectDuration_Muliplier", name, Config_Tea_EffectDuration_Muliplier, 0F, 1000F, "Multiplier for tea potion effect duration");
		Config_Spectral_Multiplier_Durability = cfg.getFloat("Spectral_Multiplier_Durability", name, Config_Spectral_Multiplier_Durability, 0F, 1000F, "Multiplier for tool/armor durability");
		Config_Spectral_Multiplier_Armor = cfg.getFloat("Spectral_Multiplier_Armor", name, Config_Spectral_Multiplier_Armor, 0F, 1000F, "Multiplier for armor strength");

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
		Config_OrbOfAbsorptionDurability = cfg.getInt("OrbOfAbsorptionDurability", name, Config_OrbOfAbsorptionDurability, 1, 72000, "Max durability of the Orb of Absorption.");
		Config_OrbOfAbsorptionWhitelistBlacklist = cfg.getInt("OrbOfAbsorptionWhitelistBlacklist", name, Config_OrbOfAbsorptionWhitelistBlacklist, 0, 2, "Decides how to treat OrbOfAbsorptionList, 0 - ignored (Orb of Absorption can repair any damaged object), 1 - whitelist (can only repair items in the list), 2 - blacklist (repairs everything but items in the list)");
		Config_OrbOfAbsorptionList = cfg.getStringList("OrbOfAbsorptionList", name, Config_OrbOfAbsorptionList, "List of items, use is decided by OrbOfAbsorptionWhitelistBlacklist, you can also specify mod ids to whitelist or blacklist whole mods, ex: (tconstruct, minecraft:elytra)");
		Config_UmbraSwordCooldown = cfg.getInt("UmbraSwordCooldown", name, Config_UmbraSwordCooldown, 0, 72000, "Cooldown in ticks for the Umbra Swords ability");
		Config_SlimeBootsCooldown = cfg.getInt("SlimeBootsCooldown", name, Config_SlimeBootsCooldown, 0, 72000, "Cooldown in ticks for the Aurorian Slime Boots jump ability");
		Config_UmbraPickaxeMiningSpeedMultiplier = cfg.getFloat("UmbraPickaxeMiningSpeedMultiplier", name, Config_UmbraPickaxeMiningSpeedMultiplier, 1F, 100F, "Speed multiplier for Umbra Pickaxe, 2F is twice as fast as it would mine regular blocks");
		Config_MoonlightForgeTransfersEnchants = cfg.getBoolean("MoonlightForgeTransfersEnchants", name, Config_MoonlightForgeTransfersEnchants, "Set to false to disable enchantments from carrying over when crafting tools with the Moonlight Forge");
		Config_AurorianSteel_BaseMaxLevel = cfg.getInt("AurorianSteel_BaseMaxLevel", name, Config_AurorianSteel_BaseMaxLevel, 0, 72000, "Base Max level for Aurorian Steel items. After used this many times an enchantment will level up, next level cost depends on multiplier");
		Config_AurorianSteel_BaseMaxLevelMultiplier = cfg.getFloat("AurorianSteel_BaseMaxLevelMultiplier", name, Config_AurorianSteel_BaseMaxLevelMultiplier, 1F, 100F, "Max Level multiplier for Aurorian Steel items, every time they level up the max level is multiplied by this");
		Config_CrystalStackSize = cfg.getInt("CrystalStackSize", name, Config_CrystalStackSize, 1, 64, "Stack size of Crystals");
		Config_AurorianSteel_Enchants = cfg.getStringList("AurorianSteel_Enchants", name, Config_AurorianSteel_Enchants, "List of enchantments, use is decided by AurorianSteel_Enchants_WhitelistBlacklist, you can also specify mod ids to whitelist or blacklist whole mods, ex: (draconicevolution, minecraft:sharpness)");
		Config_AurorianSteel_Enchants_WhitelistBlacklist = cfg.getInt("AurorianSteel_Enchants_WhitelistBlacklist", name, Config_AurorianSteel_Enchants_WhitelistBlacklist, 0, 2, "Decides how to treat AurorianSteel_Enchants, 0 - ignored (Aurorian Steel can upgrade any enchantment), 1 - whitelist (can only upgrade enchantments specified), 2 - blacklist (upgrades all but the enchantments specified)");
		Config_Spectral_Armor_CleanseChance = cfg.getFloat("Spectral_Armor_CleanseChance", name, Config_Spectral_Armor_CleanseChance, 0.01F, 1F, "Percent for one armor piece to cleanse the players negative effects, stacks with other armor parts (full spectral armor with 6% is a 24% chance with full set)");

	}

	private static void initCompatConfig(Configuration cfg) {
		String name = "compatibility";
		cfg.addCustomCategoryComment(name, "Compatibility configuration");

		Config_EnableTinkersConstructCompatibility = cfg.getBoolean("EnableTinkersConstructCompatibility", name, Config_EnableTinkersConstructCompatibility, "Set to false to disable Tinkers Construct integration");
		Config_EnableConstructsArmoryCompatibility = cfg.getBoolean("EnableConstructsArmoryCompatibility", name, Config_EnableConstructsArmoryCompatibility, "Set to false to disable Constructs Armory integration");
	}

	private static void initBlocksConfig(Configuration cfg) {
		String name = "blocks";
		cfg.addCustomCategoryComment(name, "Blocks configuration");

		Config_MaximumChimneys = cfg.getInt("MaximumChimneys", name, Config_MaximumChimneys, 0, 255, "Maximum number of chimneys able to be stacked on Aurorian Furnace");
		Config_ChimneySpeedMuliplier = cfg.getFloat("ChimneySpeedMuliplier", name, Config_ChimneySpeedMuliplier, 0, 0.99f, "Changes how much chimneys speed up the Aurorian Furnace when at maximum number (0.5 is + 50%)");
		Config_CrystalsSpeedUpMachines = cfg.getBoolean("CrystalsSpeedUpMachines", name, Config_CrystalsSpeedUpMachines, "Set to false to disable Crystals speeding up machines when placed on top");
		Config_CrystalsChanceOfBreaking = cfg.getFloat("CrystalsChanceOfBreaking", name, Config_CrystalsChanceOfBreaking, 0, 1f, "The chance for a Crystal to break when the machine finishes a recipe. (0.5 is 50%, 0 is no breaking)");
		Config_CrystalsSpeedReduction = cfg.getFloat("CrystalsSpeedReduction", name, Config_CrystalsSpeedReduction, 0.01f, 1f, "How much a Crystal will speed up the machine below it (LOWER percentage = FASTER machine, yes I know its backwards)");
		Config_ScrapperTickInterval = cfg.getInt("ScrapperTickInterval", name, Config_ScrapperTickInterval, 0, 72000, "How many ticks until the scrapper will perform 1 update, Scrapper requires 100 updates to do 1 craft. (Meaning default is 400 ticks(20 seconds) for 1 craft)");

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
		Config_Peridotite_Size = cfg.getInt("PeridotiteSize", name, Config_Peridotite_Size, 0, 50, "Changes the size of ore vein");
		Config_Peridotite_Count = cfg.getInt("PeridotiteCount", name, Config_Peridotite_Count, 0, 50, "Changes the size of ore vein");
		Config_Peridotite_HeightMin = cfg.getInt("PeridotiteHeightMin", name, Config_Peridotite_HeightMin, 0, 255, "Changes the min height of ore vein");
		Config_Peridotite_HeightMax = cfg.getInt("PeridotiteHeightMax", name, Config_Peridotite_HeightMax, 0, 255, "Changes the max height of ore vein");
		Config_Dirt_Size = cfg.getInt("DirtSize", name, Config_Dirt_Size, 0, 50, "Changes the size of ore vein");
		Config_Dirt_Count = cfg.getInt("DirtCount", name, Config_Dirt_Count, 0, 50, "Changes the size of ore vein");
		Config_Dirt_HeightMin = cfg.getInt("DirtHeightMin", name, Config_Dirt_HeightMin, 0, 255, "Changes the min height of ore vein");
		Config_Dirt_HeightMax = cfg.getInt("DirtHeightMax", name, Config_Dirt_HeightMax, 0, 255, "Changes the max height of ore vein");
		Config_GenerateUrns = cfg.getBoolean("GenerateUrns", name, Config_GenerateUrns, "Set to false to disable Urns that spawn underground");

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
		Config_GenerateMushroomCaves = cfg.getBoolean("GenerateMushroomCaves", name, Config_GenerateMushroomCaves, "Set to false to disable Mushroom Caves");
		Config_GenerateGraveyards = cfg.getBoolean("GenerateGraveyards", name, Config_GenerateGraveyards, "Set to false to disable Graveyards");
		Config_GenerateDarkstoneDungeon = cfg.getBoolean("GenerateDarkstoneDungeon", name, Config_GenerateDarkstoneDungeon, "Set to false to disable Darkstone Dungeons");
	}

	private static void initEntityConfig(Configuration cfg) {
		String name = "entity";
		cfg.addCustomCategoryComment(name, "Entity configuration");

		Config_NIGHTMAREMODE = cfg.getBoolean("NIGHTMAREMODE", name, Config_NIGHTMAREMODE, "Enable to make Aurorian mobs(not bosses) fast and strong, change multiplier to adjust the strength of these effects");
		Config_NIGHTMAREMODE_Multiplier = cfg.getFloat("NIGHTMAREMODEMultiplier", name, Config_NIGHTMAREMODE_Multiplier, 0, 50, "Use at your own risk!");
		Config_RunestoneDungeonMobDensity = cfg.getInt("RunestoneDungeonMobDensity", name, Config_RunestoneDungeonMobDensity, 0, 10, "Density of mobs spawning in the Runestone Dungeon, 2 for twice as many mobs, etc");
		Config_MoonTempleMobDensity = cfg.getInt("MoonTempleMobDensity", name, Config_MoonTempleMobDensity, 0, 10, "Density of mobs spawning in the Moon Temple, 2 for twice as many mobs, etc");
		Config_DarkstoneDungeonMobDensity = cfg.getInt("DarkstoneDungeonMobDensity", name, Config_DarkstoneDungeonMobDensity, 0, 10, "Density of mobs spawning in the Darkstone Dungeon, 2 for twice as many mobs, etc");
		Config_RunestoneKeeperHealthMuliplier = cfg.getFloat("RunestoneKeeperHealthMuliplier", name, Config_RunestoneKeeperHealthMuliplier, 0, 100, "");
		Config_MoonQueenHealthMuliplier = cfg.getFloat("MoonQueenHealthMuliplier", name, Config_MoonQueenHealthMuliplier, 0, 100, "");
		Config_SpiderMotherHealthMuliplier = cfg.getFloat("SpiderMotherHealthMuliplier", name, Config_SpiderMotherHealthMuliplier, 0, 100, "");
		Config_RunestoneKeeperDamageMuliplier = cfg.getFloat("RunestoneKeeperDamageMuliplier", name, Config_RunestoneKeeperDamageMuliplier, 0, 100, "");
		Config_MoonQueenDamageMuliplier = cfg.getFloat("MoonQueenDamageMuliplier", name, Config_MoonQueenDamageMuliplier, 0, 100, "");
		Config_SpiderMotherDamageMuliplier = cfg.getFloat("SpiderMotherDamageMuliplier", name, Config_SpiderMotherDamageMuliplier, 0, 100, "");
	}

	private static void initDimensionConfig(Configuration cfg) {
		cfg.addCustomCategoryComment("dimensions", "Dimension configuration");
		Config_AurorianDimID = cfg.getInt("AurorianDimID", "dimensions", Config_AurorianDimID, -1000, 1000, "ID to use for the aurorian dimension");
	}
}