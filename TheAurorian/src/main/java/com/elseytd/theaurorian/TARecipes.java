package com.elseytd.theaurorian;

import java.util.HashMap;
import java.util.Map;

import com.elseytd.theaurorian.TABlocks.Registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class TARecipes {

	public static void registerBlockBurntime(FurnaceFuelBurnTimeEvent event) {
		Map<Block, Integer> burnableBlocks = new HashMap<>();

		burnableBlocks.put(Registry.MATERIALCOAL.getBlock(), 16200);
		burnableBlocks.put(Registry.SILENTWOODPLANKS.getBlock(), 200);
		burnableBlocks.put(Registry.WEEPINGWILLOWPLANKS.getBlock(), 200);
		burnableBlocks.put(Registry.SILENTWOODLOG.getBlock(), 300);
		burnableBlocks.put(Registry.WEEPINGWILLOWLOG.getBlock(), 300);
		burnableBlocks.put(Registry.SILENTWOODPLANKSSTAIRS.getBlock(), 300);
		burnableBlocks.put(Registry.MUSHROOM.getBlock(), 0);
		burnableBlocks.put(Registry.MUSHROOMSTEM.getBlock(), 0);

		for (Map.Entry<Block, Integer> entry : burnableBlocks.entrySet()) {
			if (event.getItemStack().getItem() == Item.getItemFromBlock(entry.getKey())) {
				event.setBurnTime(entry.getValue());
			}
		}
	}

	public static void registerFurnaceRecipes() {
		// BASIC
		GameRegistry.addSmelting(TABlocks.Registry.AURORIANCOBBLESTONE.getBlock(), new ItemStack(TABlocks.Registry.AURORIANSTONE.getBlock()), 0.1F);
		GameRegistry.addSmelting(TABlocks.Registry.ORECERULEAN.getBlock(), new ItemStack(TAItems.Registry.INGOTCERULEAN.getItem()), 1F);
		GameRegistry.addSmelting(TABlocks.Registry.OREMOONSTONE.getBlock(), new ItemStack(TAItems.Registry.INGOTMOONSTONE.getItem()), 1F);
		GameRegistry.addSmelting(TAItems.Registry.AURORIANPORK.getItem(), new ItemStack(TAItems.Registry.AURORIANPORKCOOKED.getItem()), 0.1F);
		GameRegistry.addSmelting(TABlocks.Registry.MOONSAND.getBlock(), new ItemStack(TABlocks.Registry.GLASSMOON.getBlock()), 0.1F);

		// RECYCLING
		GameRegistry.addSmelting(TAItems.Registry.MOONSTONESWORD.getItem(), new ItemStack(TAItems.Registry.MOONSTONENUGGET.getItem(), 4), 0F);
		GameRegistry.addSmelting(TAItems.Registry.MOONSTONEHOE.getItem(), new ItemStack(TAItems.Registry.MOONSTONENUGGET.getItem(), 4), 0F);
		GameRegistry.addSmelting(TAItems.Registry.MOONSTONEPICKAXE.getItem(), new ItemStack(TAItems.Registry.MOONSTONENUGGET.getItem(), 6), 0F);
		GameRegistry.addSmelting(TAItems.Registry.MOONSTONESHOVEL.getItem(), new ItemStack(TAItems.Registry.MOONSTONENUGGET.getItem(), 2), 0F);
		GameRegistry.addSmelting(TAItems.Registry.MOONSTONESICKLE.getItem(), new ItemStack(TAItems.Registry.MOONSTONENUGGET.getItem(), 6), 0F);
		GameRegistry.addSmelting(TAItems.Registry.MOONSTONEAXE.getItem(), new ItemStack(TAItems.Registry.MOONSTONENUGGET.getItem(), 6), 0F);
		GameRegistry.addSmelting(TAItems.Registry.CERULEANARMORBOOTS.getItem(), new ItemStack(TAItems.Registry.CERULEANNUGGET.getItem(), 8), 0F);
		GameRegistry.addSmelting(TAItems.Registry.CERULEANARMORCHESTPLATE.getItem(), new ItemStack(TAItems.Registry.CERULEANNUGGET.getItem(), 16), 0F);
		GameRegistry.addSmelting(TAItems.Registry.CERULEANARMORLEGGINGS.getItem(), new ItemStack(TAItems.Registry.CERULEANNUGGET.getItem(), 14), 0F);
		GameRegistry.addSmelting(TAItems.Registry.CERULEANARMORHELMET.getItem(), new ItemStack(TAItems.Registry.CERULEANNUGGET.getItem(), 10), 0F);
		GameRegistry.addSmelting(TAItems.Registry.CERULEANSHIELD.getItem(), new ItemStack(TAItems.Registry.CERULEANNUGGET.getItem(), 12), 0F);
		GameRegistry.addSmelting(TAItems.Registry.AURORIANSTEELARMORCHESTPLATE.getItem(), new ItemStack(TAItems.Registry.AURORIANSTEELNUGGET.getItem(), 16), 0F);
		GameRegistry.addSmelting(TAItems.Registry.AURORIANSTEELARMORLEGGINGS.getItem(), new ItemStack(TAItems.Registry.AURORIANSTEELNUGGET.getItem(), 14), 0F);
		GameRegistry.addSmelting(TAItems.Registry.AURORIANSTEELARMORHELMET.getItem(), new ItemStack(TAItems.Registry.AURORIANSTEELNUGGET.getItem(), 10), 0F);
		GameRegistry.addSmelting(TAItems.Registry.AURORIANSTEELARMORBOOTS.getItem(), new ItemStack(TAItems.Registry.AURORIANSTEELNUGGET.getItem(), 8), 0F);

		// SPECIAL RECYCLING
		GameRegistry.addSmelting(TAItems.Registry.AURORIANITESWORD.getItem(), new ItemStack(TAItems.Registry.SCRAPAURORIANITE.getItem(), 2), 0F);
		GameRegistry.addSmelting(TAItems.Registry.AURORIANITEPICKAXE.getItem(), new ItemStack(TAItems.Registry.SCRAPAURORIANITE.getItem(), 3), 0F);
		GameRegistry.addSmelting(TAItems.Registry.AURORIANITEAXE.getItem(), new ItemStack(TAItems.Registry.SCRAPAURORIANITE.getItem(), 3), 0F);
		GameRegistry.addSmelting(TAItems.Registry.CRYSTALLINESWORD.getItem(), new ItemStack(TAItems.Registry.SCRAPCRYSTALLINE.getItem(), 2), 0F);
		GameRegistry.addSmelting(TAItems.Registry.CRYSTALLINEPICKAXE.getItem(), new ItemStack(TAItems.Registry.SCRAPCRYSTALLINE.getItem(), 3), 0F);
		GameRegistry.addSmelting(TAItems.Registry.CRYSTALLINESHIELD.getItem(), new ItemStack(TAItems.Registry.SCRAPCRYSTALLINE.getItem(), 5), 0F);
		GameRegistry.addSmelting(TAItems.Registry.ABSORPTIONORB.getItem(), new ItemStack(TAItems.Registry.SCRAPCRYSTALLINE.getItem(), 4), 0F);
		GameRegistry.addSmelting(TAItems.Registry.LIVINGDIVININGROD.getItem(), new ItemStack(TAItems.Registry.SCRAPCRYSTALLINE.getItem(), 2), 0F);
		GameRegistry.addSmelting(TAItems.Registry.SPIKEDCHESTPLATE.getItem(), new ItemStack(TAItems.Registry.SCRAPUMBRA.getItem(), 8), 0F);
		GameRegistry.addSmelting(TAItems.Registry.UMBRASHIELD.getItem(), new ItemStack(TAItems.Registry.SCRAPUMBRA.getItem(), 5), 0F);
		GameRegistry.addSmelting(TAItems.Registry.UMBRASWORD.getItem(), new ItemStack(TAItems.Registry.SCRAPUMBRA.getItem(), 2), 0F);
		GameRegistry.addSmelting(TAItems.Registry.UMBRAPICKAXE.getItem(), new ItemStack(TAItems.Registry.SCRAPUMBRA.getItem(), 3), 0F);
	}

	public static void registerOreDictionary() {
		OreDictionary.registerOre("treeLeaves", TABlocks.Registry.SILENTWOODLEAVES.getBlock());
		OreDictionary.registerOre("treeLeaves", TABlocks.Registry.WEEPINGWILLOWLEAVES.getBlock());
		OreDictionary.registerOre("stairWood", TABlocks.Registry.SILENTWOODPLANKSSTAIRS.getBlock());
		OreDictionary.registerOre("stairWood", TABlocks.Registry.WEEPINGWILLOWPLANKSSTAIRS.getBlock());
		OreDictionary.registerOre("treeSapling", TABlocks.Registry.PLANTSILENTWOODSAPLING.getBlock());
		OreDictionary.registerOre("treeSapling", TABlocks.Registry.WEEPINGWILLOWSAPLING.getBlock());
		OreDictionary.registerOre("paneGlass", TABlocks.Registry.GLASSPANEMOON.getBlock());
		OreDictionary.registerOre("paneGlass", TABlocks.Registry.GLASSPANEAURORIAN.getBlock());
		OreDictionary.registerOre("slimeball", TAItems.Registry.AURORIANSLIMEBALL.getItem());
		OreDictionary.registerOre("string", TAItems.Registry.PLANTFIBER.getItem());
		OreDictionary.registerOre("dirt", TABlocks.Registry.AURORIANDIRT.getBlock());
		OreDictionary.registerOre("grass", TABlocks.Registry.AURORIANGRASS.getBlock());
		OreDictionary.registerOre("grass", TABlocks.Registry.AURORIANGRASSLIGHT.getBlock());
		OreDictionary.registerOre("torch", TABlocks.Registry.SILENTWOODTORCH.getBlock());
		OreDictionary.registerOre("workbench", TABlocks.Registry.SILENTWOODWORKBENCH.getBlock());

		OreDictionary.registerOre("plankSilentwood", TABlocks.Registry.SILENTWOODPLANKS.getBlock());
		OreDictionary.registerOre("logSilentwood", TABlocks.Registry.SILENTWOODLOG.getBlock());
		OreDictionary.registerOre("stickSilentwood", TAItems.Registry.SILENTWOODSTICK.getItem());

		OreDictionary.registerOre("plankWood", TABlocks.Registry.WEEPINGWILLOWPLANKS.getBlock());
		OreDictionary.registerOre("logWood", TABlocks.Registry.WEEPINGWILLOWLOG.getBlock());

		OreDictionary.registerOre("stoneAurorian", TABlocks.Registry.AURORIANSTONE.getBlock());
		OreDictionary.registerOre("cobblestoneAurorian", TABlocks.Registry.AURORIANCOBBLESTONE.getBlock());

		OreDictionary.registerOre("oreAurorianCoal", TABlocks.Registry.OREAURORIANCOAL.getBlock());
		OreDictionary.registerOre("oreAurorianGeode", TABlocks.Registry.OREGEODE.getBlock());

		OreDictionary.registerOre("oreCerulean", TABlocks.Registry.ORECERULEAN.getBlock());
		OreDictionary.registerOre("blockCerulean", TABlocks.Registry.MATERIALCERULEAN.getBlock());
		OreDictionary.registerOre("ingotCerulean", TAItems.Registry.INGOTCERULEAN.getItem());
		OreDictionary.registerOre("nuggetCerulean", TAItems.Registry.CERULEANNUGGET.getItem());

		OreDictionary.registerOre("oreMoonstone", TABlocks.Registry.OREMOONSTONE.getBlock());
		OreDictionary.registerOre("blockMoonstone", TABlocks.Registry.MATERIALMOONSTONE.getBlock());
		OreDictionary.registerOre("ingotMoonstone", TAItems.Registry.INGOTMOONSTONE.getItem());
		OreDictionary.registerOre("nuggetMoonstone", TAItems.Registry.MOONSTONENUGGET.getItem());

		OreDictionary.registerOre("blockAurorianSteel", TABlocks.Registry.MATERIALAURORIANSTEEL.getBlock());
		OreDictionary.registerOre("ingotAurorianSteel", TAItems.Registry.INGOTAURORIANSTEEL.getItem());
		OreDictionary.registerOre("nuggetAurorianSteel", TAItems.Registry.AURORIANSTEELNUGGET.getItem());
	}

}
