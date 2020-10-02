package com.elseytd.theaurorian.Recipes;

import java.util.ArrayList;
import java.util.List;

import com.elseytd.theaurorian.TAItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ScrapperRecipeHandler {

	public static List<ScrapperRecipe> allRecipes = new ArrayList<>();
	private static List<ScrapperRecipe> recipesForRemoval = new ArrayList<>();

	public static void initRecipes() {
		//Reminder: Basic scrap is equal to how many ingots would be needed if crafted like vanilla, this multiplies by two but no more than 9 for duplication reasons!
		//Other tools: # of ingots * 6 = return nuggets

		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.UMBRASWORD.getItem()), new ItemStack(TAItems.Registry.SCRAPUMBRA.getItem(), 4)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.UMBRAPICKAXE.getItem()), new ItemStack(TAItems.Registry.SCRAPUMBRA.getItem(), 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.UMBRASHIELD.getItem()), new ItemStack(TAItems.Registry.SCRAPUMBRA.getItem(), 9)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.SPIKEDCHESTPLATE.getItem()), new ItemStack(TAItems.Registry.SCRAPUMBRA.getItem(), 9)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.AURORIANITESWORD.getItem()), new ItemStack(TAItems.Registry.SCRAPAURORIANITE.getItem(), 4)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.AURORIANITEPICKAXE.getItem()), new ItemStack(TAItems.Registry.SCRAPAURORIANITE.getItem(), 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.AURORIANITEAXE.getItem()), new ItemStack(TAItems.Registry.SCRAPAURORIANITE.getItem(), 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.CRYSTALLINESWORD.getItem()), new ItemStack(TAItems.Registry.SCRAPCRYSTALLINE.getItem(), 4)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.CRYSTALLINEPICKAXE.getItem()), new ItemStack(TAItems.Registry.SCRAPCRYSTALLINE.getItem(), 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.CRYSTALLINESHIELD.getItem()), new ItemStack(TAItems.Registry.SCRAPCRYSTALLINE.getItem(), 9)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.ABSORPTIONORB.getItem()), new ItemStack(TAItems.Registry.SCRAPCRYSTALLINE.getItem(), 8)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.LIVINGDIVININGROD.getItem()), new ItemStack(TAItems.Registry.SCRAPCRYSTALLINE.getItem(), 4)));

		addRecipe(new ScrapperRecipe(new ItemStack(Items.IRON_SWORD), new ItemStack(Items.IRON_NUGGET, 12)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.IRON_PICKAXE), new ItemStack(Items.IRON_NUGGET, 18)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.IRON_AXE), new ItemStack(Items.IRON_NUGGET, 18)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.IRON_SHOVEL), new ItemStack(Items.IRON_NUGGET, 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.IRON_HOE), new ItemStack(Items.IRON_NUGGET, 12)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.IRON_HELMET), new ItemStack(Items.IRON_NUGGET, 30)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.IRON_CHESTPLATE), new ItemStack(Items.IRON_NUGGET, 48)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.IRON_LEGGINGS), new ItemStack(Items.IRON_NUGGET, 42)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.IRON_BOOTS), new ItemStack(Items.IRON_NUGGET, 24)));

		addRecipe(new ScrapperRecipe(new ItemStack(Items.GOLDEN_SWORD), new ItemStack(Items.GOLD_NUGGET, 12)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.GOLDEN_PICKAXE), new ItemStack(Items.GOLD_NUGGET, 18)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.GOLDEN_AXE), new ItemStack(Items.GOLD_NUGGET, 18)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.GOLDEN_SHOVEL), new ItemStack(Items.GOLD_NUGGET, 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.GOLDEN_HOE), new ItemStack(Items.GOLD_NUGGET, 12)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.GOLDEN_HELMET), new ItemStack(Items.GOLD_NUGGET, 30)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.GOLDEN_CHESTPLATE), new ItemStack(Items.GOLD_NUGGET, 48)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.GOLDEN_LEGGINGS), new ItemStack(Items.GOLD_NUGGET, 42)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.GOLDEN_BOOTS), new ItemStack(Items.GOLD_NUGGET, 24)));

		addRecipe(new ScrapperRecipe(new ItemStack(Items.DIAMOND_SWORD), new ItemStack(Items.DIAMOND, 1)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.DIAMOND_PICKAXE), new ItemStack(Items.DIAMOND, 2)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.DIAMOND_AXE), new ItemStack(Items.DIAMOND, 2)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.DIAMOND_SHOVEL), new ItemStack(Items.DIAMOND, 1)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.DIAMOND_HOE), new ItemStack(Items.DIAMOND, 1)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.DIAMOND_HELMET), new ItemStack(Items.DIAMOND, 3)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.DIAMOND_CHESTPLATE), new ItemStack(Items.DIAMOND, 5)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.DIAMOND_LEGGINGS), new ItemStack(Items.DIAMOND, 4)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.DIAMOND_BOOTS), new ItemStack(Items.DIAMOND, 2)));

		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.AURORIANSTEELSWORD.getItem()), new ItemStack(TAItems.Registry.AURORIANSTEELNUGGET.getItem(), 12)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.AURORIANSTEELPICKAXE.getItem()), new ItemStack(TAItems.Registry.AURORIANSTEELNUGGET.getItem(), 18)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.AURORIANSTEELAXE.getItem()), new ItemStack(TAItems.Registry.AURORIANSTEELNUGGET.getItem(), 18)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.AURORIANSTEELSHOVEL.getItem()), new ItemStack(TAItems.Registry.AURORIANSTEELNUGGET.getItem(), 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.AURORIANSTEELHOE.getItem()), new ItemStack(TAItems.Registry.AURORIANSTEELNUGGET.getItem(), 12)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.AURORIANSTEELARMORHELMET.getItem()), new ItemStack(TAItems.Registry.AURORIANSTEELNUGGET.getItem(), 30)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.AURORIANSTEELARMORCHESTPLATE.getItem()), new ItemStack(TAItems.Registry.AURORIANSTEELNUGGET.getItem(), 48)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.AURORIANSTEELARMORLEGGINGS.getItem()), new ItemStack(TAItems.Registry.AURORIANSTEELNUGGET.getItem(), 42)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.AURORIANSTEELARMORBOOTS.getItem()), new ItemStack(TAItems.Registry.AURORIANSTEELNUGGET.getItem(), 24)));

		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.MOONSTONESWORD.getItem()), new ItemStack(TAItems.Registry.MOONSTONENUGGET.getItem(), 12)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.MOONSTONEPICKAXE.getItem()), new ItemStack(TAItems.Registry.MOONSTONENUGGET.getItem(), 18)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.MOONSTONEAXE.getItem()), new ItemStack(TAItems.Registry.MOONSTONENUGGET.getItem(), 18)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.MOONSTONESHOVEL.getItem()), new ItemStack(TAItems.Registry.MOONSTONENUGGET.getItem(), 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.MOONSTONEHOE.getItem()), new ItemStack(TAItems.Registry.MOONSTONENUGGET.getItem(), 12)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.MOONSTONESHIELD.getItem()), new ItemStack(TAItems.Registry.MOONSTONENUGGET.getItem(), 36)));

		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.CERULEANARMORHELMET.getItem()), new ItemStack(TAItems.Registry.CERULEANNUGGET.getItem(), 30)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.CERULEANARMORCHESTPLATE.getItem()), new ItemStack(TAItems.Registry.CERULEANNUGGET.getItem(), 48)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.CERULEANARMORLEGGINGS.getItem()), new ItemStack(TAItems.Registry.CERULEANNUGGET.getItem(), 42)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.CERULEANARMORBOOTS.getItem()), new ItemStack(TAItems.Registry.CERULEANNUGGET.getItem(), 24)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.Registry.CERULEANSHIELD.getItem()), new ItemStack(TAItems.Registry.CERULEANNUGGET.getItem(), 36)));

		for (ScrapperRecipe r : recipesForRemoval) {
			ScrapperRecipe rem = null;
			for (ScrapperRecipe r2 : allRecipes) {
				if (r.getInput().getItem() == r2.getInput().getItem()) {
					if (r.getOutput().getItem() == r2.getOutput().getItem()) {
						rem = r2;
						break;
					}
				}
			}
			if (rem != null) {
				allRecipes.remove(rem);
			}
		}
	}

	public static void addRecipe(ScrapperRecipe recipe) {
		allRecipes.add(recipe);
	}

	public static void removeRecipe(ScrapperRecipe recipe) {
		recipesForRemoval.add(recipe);
	}

}
