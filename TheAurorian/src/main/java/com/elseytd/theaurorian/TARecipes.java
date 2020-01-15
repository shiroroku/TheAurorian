package com.elseytd.theaurorian;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class TARecipes {

	public static void registerFurnaceRecipes() {
		// BASIC
		GameRegistry.addSmelting(TABlocks.auroriancobblestone, new ItemStack(TABlocks.aurorianstone), 0.1F);
		GameRegistry.addSmelting(TABlocks.ceruleanore, new ItemStack(TAItems.ceruleaningot), 1F);
		GameRegistry.addSmelting(TABlocks.moonstoneore, new ItemStack(TAItems.moonstoneingot), 1F);
		GameRegistry.addSmelting(TAItems.silkberry, new ItemStack(TAItems.silkberryrasin), 0.1F);
		GameRegistry.addSmelting(TAItems.aurorianpork, new ItemStack(TAItems.aurorianporkcooked), 0.1F);
		GameRegistry.addSmelting(TABlocks.moonsand, new ItemStack(TABlocks.moonglass), 0.1F);

		// RECYCLING
		GameRegistry.addSmelting(TAItems.moonstonesword, new ItemStack(TAItems.moonstonenugget, 4), 0F);
		GameRegistry.addSmelting(TAItems.moonstonehoe, new ItemStack(TAItems.moonstonenugget, 4), 0F);
		GameRegistry.addSmelting(TAItems.moonstonepickaxe, new ItemStack(TAItems.moonstonenugget, 6), 0F);
		GameRegistry.addSmelting(TAItems.moonstoneshovel, new ItemStack(TAItems.moonstonenugget, 2), 0F);
		GameRegistry.addSmelting(TAItems.moonstonesickle, new ItemStack(TAItems.moonstonenugget, 6), 0F);
		GameRegistry.addSmelting(TAItems.moonstoneaxe, new ItemStack(TAItems.moonstonenugget, 6), 0F);
		GameRegistry.addSmelting(TAItems.ceruleanboots, new ItemStack(TAItems.ceruleannugget, 8), 0F);
		GameRegistry.addSmelting(TAItems.ceruleanchestplate, new ItemStack(TAItems.ceruleannugget, 16), 0F);
		GameRegistry.addSmelting(TAItems.ceruleanleggings, new ItemStack(TAItems.ceruleannugget, 14), 0F);
		GameRegistry.addSmelting(TAItems.ceruleanhelmet, new ItemStack(TAItems.ceruleannugget, 10), 0F);
		GameRegistry.addSmelting(TAItems.ceruleanshield, new ItemStack(TAItems.ceruleannugget, 12), 0F);

		// SPECIAL RECYCLING
		GameRegistry.addSmelting(TAItems.aurorianitesword, new ItemStack(TAItems.scrapaurorianite, 2), 2F);
		GameRegistry.addSmelting(TAItems.aurorianitepickaxe, new ItemStack(TAItems.scrapaurorianite, 3), 2F);
		GameRegistry.addSmelting(TAItems.aurorianiteaxe, new ItemStack(TAItems.scrapaurorianite, 3), 2F);
		GameRegistry.addSmelting(TAItems.crystallinepickaxe, new ItemStack(TAItems.scrapcrystalline, 3), 2F);
		GameRegistry.addSmelting(TAItems.crystallineshield, new ItemStack(TAItems.scrapcrystalline, 5), 2F);
		GameRegistry.addSmelting(TAItems.absorptionorb, new ItemStack(TAItems.scrapcrystalline, 4), 2F);
		GameRegistry.addSmelting(TAItems.livingdiviningrod, new ItemStack(TAItems.scrapcrystalline, 2), 2F);
		GameRegistry.addSmelting(TAItems.spikedchestplate, new ItemStack(TAItems.scrapumbra, 8), 2F);
		GameRegistry.addSmelting(TAItems.umbrashield, new ItemStack(TAItems.scrapumbra, 5), 2F);
		GameRegistry.addSmelting(TAItems.umbrasword, new ItemStack(TAItems.scrapumbra, 2), 2F);
	}

	public static void registerOreDictionary() {
		OreDictionary.registerOre("treeLeaves", TABlocks.silentwoodleaves);
		OreDictionary.registerOre("stickWood", TAItems.silentwoodstick);
		OreDictionary.registerOre("stairWood", TABlocks.silentwoodstairs);
		OreDictionary.registerOre("treeSapling", TABlocks.silentwoodsapling);
		OreDictionary.registerOre("paneGlass", TABlocks.moonglasspane);
		OreDictionary.registerOre("paneGlass", TABlocks.aurorianglasspane);
		OreDictionary.registerOre("slimeball", TAItems.aurorianslimeball);
		OreDictionary.registerOre("string", TAItems.plantfiber);
		OreDictionary.registerOre("dirt", TABlocks.auroriandirt);
		OreDictionary.registerOre("grass", TABlocks.auroriangrass);
		OreDictionary.registerOre("torch", TABlocks.silentwoodtorch);
		OreDictionary.registerOre("workbench", TABlocks.silentwoodworkbench);

		OreDictionary.registerOre("oreAurorianCoal", TABlocks.auroriancoalore);
		OreDictionary.registerOre("oreAurorianGeode", TABlocks.geodeore);

		OreDictionary.registerOre("oreCerulean", TABlocks.ceruleanore);
		OreDictionary.registerOre("blockCerulean", TABlocks.ceruleanblock);
		OreDictionary.registerOre("ingotCerulean", TAItems.ceruleaningot);
		OreDictionary.registerOre("nuggetCerulean", TAItems.ceruleannugget);

		OreDictionary.registerOre("oreMoonstone", TABlocks.moonstoneore);
		OreDictionary.registerOre("blockMoonstone", TABlocks.moonstoneblock);
		OreDictionary.registerOre("ingotMoonstone", TAItems.moonstoneingot);
		OreDictionary.registerOre("nuggetMoonstone", TAItems.moonstonenugget);
	}
}
