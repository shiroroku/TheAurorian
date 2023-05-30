package com.shiroroku.theaurorian.Registry;

import com.shiroroku.theaurorian.Registry.BlockRegistry.Registry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.Map;

public class RecipeRegistry {

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
		GameRegistry.addSmelting(BlockRegistry.Registry.AURORIANCOBBLESTONE.getBlock(), new ItemStack(BlockRegistry.Registry.AURORIANSTONE.getBlock()), 0.1F);
		GameRegistry.addSmelting(BlockRegistry.Registry.ORECERULEAN.getBlock(), new ItemStack(ItemRegistry.Registry.INGOTCERULEAN.getItem()), 1F);
		GameRegistry.addSmelting(BlockRegistry.Registry.OREMOONSTONE.getBlock(), new ItemStack(ItemRegistry.Registry.INGOTMOONSTONE.getItem()), 1F);
		GameRegistry.addSmelting(ItemRegistry.Registry.AURORIANPORK.getItem(), new ItemStack(ItemRegistry.Registry.AURORIANPORKCOOKED.getItem()), 0.1F);
		GameRegistry.addSmelting(BlockRegistry.Registry.MOONSAND.getBlock(), new ItemStack(BlockRegistry.Registry.GLASSMOON.getBlock()), 0.1F);

		// RECYCLING
		GameRegistry.addSmelting(ItemRegistry.Registry.MOONSTONESWORD.getItem(), new ItemStack(ItemRegistry.Registry.MOONSTONENUGGET.getItem(), 4), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.MOONSTONEHOE.getItem(), new ItemStack(ItemRegistry.Registry.MOONSTONENUGGET.getItem(), 4), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.MOONSTONEPICKAXE.getItem(), new ItemStack(ItemRegistry.Registry.MOONSTONENUGGET.getItem(), 6), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.MOONSTONESHOVEL.getItem(), new ItemStack(ItemRegistry.Registry.MOONSTONENUGGET.getItem(), 2), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.MOONSTONESICKLE.getItem(), new ItemStack(ItemRegistry.Registry.MOONSTONENUGGET.getItem(), 6), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.MOONSTONEAXE.getItem(), new ItemStack(ItemRegistry.Registry.MOONSTONENUGGET.getItem(), 6), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.CERULEANARMORBOOTS.getItem(), new ItemStack(ItemRegistry.Registry.CERULEANNUGGET.getItem(), 8), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.CERULEANARMORCHESTPLATE.getItem(), new ItemStack(ItemRegistry.Registry.CERULEANNUGGET.getItem(), 16), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.CERULEANARMORLEGGINGS.getItem(), new ItemStack(ItemRegistry.Registry.CERULEANNUGGET.getItem(), 14), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.CERULEANARMORHELMET.getItem(), new ItemStack(ItemRegistry.Registry.CERULEANNUGGET.getItem(), 10), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.CERULEANSHIELD.getItem(), new ItemStack(ItemRegistry.Registry.CERULEANNUGGET.getItem(), 12), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.AURORIANSTEELARMORCHESTPLATE.getItem(), new ItemStack(ItemRegistry.Registry.AURORIANSTEELNUGGET.getItem(), 16), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.AURORIANSTEELARMORLEGGINGS.getItem(), new ItemStack(ItemRegistry.Registry.AURORIANSTEELNUGGET.getItem(), 14), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.AURORIANSTEELARMORHELMET.getItem(), new ItemStack(ItemRegistry.Registry.AURORIANSTEELNUGGET.getItem(), 10), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.AURORIANSTEELARMORBOOTS.getItem(), new ItemStack(ItemRegistry.Registry.AURORIANSTEELNUGGET.getItem(), 8), 0F);

		// SPECIAL RECYCLING
		GameRegistry.addSmelting(ItemRegistry.Registry.AURORIANITESWORD.getItem(), new ItemStack(ItemRegistry.Registry.SCRAPAURORIANITE.getItem(), 2), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.AURORIANITEPICKAXE.getItem(), new ItemStack(ItemRegistry.Registry.SCRAPAURORIANITE.getItem(), 3), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.AURORIANITEAXE.getItem(), new ItemStack(ItemRegistry.Registry.SCRAPAURORIANITE.getItem(), 3), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.CRYSTALLINESWORD.getItem(), new ItemStack(ItemRegistry.Registry.SCRAPCRYSTALLINE.getItem(), 2), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.CRYSTALLINEPICKAXE.getItem(), new ItemStack(ItemRegistry.Registry.SCRAPCRYSTALLINE.getItem(), 3), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.CRYSTALLINESHIELD.getItem(), new ItemStack(ItemRegistry.Registry.SCRAPCRYSTALLINE.getItem(), 5), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.ABSORPTIONORB.getItem(), new ItemStack(ItemRegistry.Registry.SCRAPCRYSTALLINE.getItem(), 4), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.LIVINGDIVININGROD.getItem(), new ItemStack(ItemRegistry.Registry.SCRAPCRYSTALLINE.getItem(), 2), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.SPIKEDCHESTPLATE.getItem(), new ItemStack(ItemRegistry.Registry.SCRAPUMBRA.getItem(), 8), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.UMBRASHIELD.getItem(), new ItemStack(ItemRegistry.Registry.SCRAPUMBRA.getItem(), 5), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.UMBRASWORD.getItem(), new ItemStack(ItemRegistry.Registry.SCRAPUMBRA.getItem(), 2), 0F);
		GameRegistry.addSmelting(ItemRegistry.Registry.UMBRAPICKAXE.getItem(), new ItemStack(ItemRegistry.Registry.SCRAPUMBRA.getItem(), 3), 0F);
	}

	public static void registerOreDictionary() {
		OreDictionary.registerOre("treeLeaves", BlockRegistry.Registry.SILENTWOODLEAVES.getBlock());
		OreDictionary.registerOre("treeLeaves", BlockRegistry.Registry.WEEPINGWILLOWLEAVES.getBlock());
		OreDictionary.registerOre("stairWood", BlockRegistry.Registry.SILENTWOODPLANKSSTAIRS.getBlock());
		OreDictionary.registerOre("stairWood", BlockRegistry.Registry.WEEPINGWILLOWPLANKSSTAIRS.getBlock());
		OreDictionary.registerOre("treeSapling", BlockRegistry.Registry.PLANTSILENTWOODSAPLING.getBlock());
		OreDictionary.registerOre("treeSapling", BlockRegistry.Registry.WEEPINGWILLOWSAPLING.getBlock());
		OreDictionary.registerOre("paneGlass", BlockRegistry.Registry.GLASSPANEMOON.getBlock());
		OreDictionary.registerOre("paneGlass", BlockRegistry.Registry.GLASSPANEAURORIAN.getBlock());
		OreDictionary.registerOre("slimeball", ItemRegistry.Registry.AURORIANSLIMEBALL.getItem());
		OreDictionary.registerOre("string", ItemRegistry.Registry.PLANTFIBER.getItem());
		OreDictionary.registerOre("dirt", BlockRegistry.Registry.AURORIANDIRT.getBlock());
		OreDictionary.registerOre("grass", BlockRegistry.Registry.AURORIANGRASS.getBlock());
		OreDictionary.registerOre("grass", BlockRegistry.Registry.AURORIANGRASSLIGHT.getBlock());
		OreDictionary.registerOre("torch", BlockRegistry.Registry.SILENTWOODTORCH.getBlock());
		OreDictionary.registerOre("workbench", BlockRegistry.Registry.SILENTWOODWORKBENCH.getBlock());

		OreDictionary.registerOre("plankSilentwood", BlockRegistry.Registry.SILENTWOODPLANKS.getBlock());
		OreDictionary.registerOre("logSilentwood", BlockRegistry.Registry.SILENTWOODLOG.getBlock());
		OreDictionary.registerOre("stickSilentwood", ItemRegistry.Registry.SILENTWOODSTICK.getItem());

		OreDictionary.registerOre("plankWood", BlockRegistry.Registry.WEEPINGWILLOWPLANKS.getBlock());
		OreDictionary.registerOre("logWood", BlockRegistry.Registry.WEEPINGWILLOWLOG.getBlock());

		OreDictionary.registerOre("stoneAurorian", BlockRegistry.Registry.AURORIANSTONE.getBlock());
		OreDictionary.registerOre("cobblestoneAurorian", BlockRegistry.Registry.AURORIANCOBBLESTONE.getBlock());

		OreDictionary.registerOre("oreAurorianCoal", BlockRegistry.Registry.OREAURORIANCOAL.getBlock());
		OreDictionary.registerOre("oreAurorianGeode", BlockRegistry.Registry.OREGEODE.getBlock());

		OreDictionary.registerOre("oreCerulean", BlockRegistry.Registry.ORECERULEAN.getBlock());
		OreDictionary.registerOre("blockCerulean", BlockRegistry.Registry.MATERIALCERULEAN.getBlock());
		OreDictionary.registerOre("ingotCerulean", ItemRegistry.Registry.INGOTCERULEAN.getItem());
		OreDictionary.registerOre("nuggetCerulean", ItemRegistry.Registry.CERULEANNUGGET.getItem());

		OreDictionary.registerOre("oreMoonstone", BlockRegistry.Registry.OREMOONSTONE.getBlock());
		OreDictionary.registerOre("blockMoonstone", BlockRegistry.Registry.MATERIALMOONSTONE.getBlock());
		OreDictionary.registerOre("ingotMoonstone", ItemRegistry.Registry.INGOTMOONSTONE.getItem());
		OreDictionary.registerOre("nuggetMoonstone", ItemRegistry.Registry.MOONSTONENUGGET.getItem());

		OreDictionary.registerOre("blockAurorianSteel", BlockRegistry.Registry.MATERIALAURORIANSTEEL.getBlock());
		OreDictionary.registerOre("ingotAurorianSteel", ItemRegistry.Registry.INGOTAURORIANSTEEL.getItem());
		OreDictionary.registerOre("nuggetAurorianSteel", ItemRegistry.Registry.AURORIANSTEELNUGGET.getItem());
	}

}
