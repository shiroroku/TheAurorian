package com.elseytd.theaurorian.Recipes;

import java.util.ArrayList;
import java.util.List;

import com.elseytd.theaurorian.TAItems;

import net.minecraft.item.ItemStack;

public class ScrapperRecipeHandler {

	public static List<ScrapperRecipe> allRecipes = new ArrayList<>();
	private static List<ScrapperRecipe> recipesForRemoval = new ArrayList<>();

	public static void initRecipes() {
		//Reminder: Basic scrap is equal to how many ingots would be needed if crafted like vanilla, this multiplies by two but no more than 9 for duplication reasons!

		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.umbrasword), new ItemStack(TAItems.scrapumbra, 4)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.umbrapickaxe), new ItemStack(TAItems.scrapumbra, 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.umbrashield), new ItemStack(TAItems.scrapumbra, 9)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.spikedchestplate), new ItemStack(TAItems.scrapumbra, 9)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.aurorianitesword), new ItemStack(TAItems.scrapaurorianite, 4)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.aurorianitepickaxe), new ItemStack(TAItems.scrapaurorianite, 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.aurorianiteaxe), new ItemStack(TAItems.scrapaurorianite, 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.crystallinesword), new ItemStack(TAItems.scrapcrystalline, 4)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.crystallinepickaxe), new ItemStack(TAItems.scrapcrystalline, 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.crystallineshield), new ItemStack(TAItems.scrapcrystalline, 9)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.absorptionorb), new ItemStack(TAItems.scrapcrystalline, 8)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.livingdiviningrod), new ItemStack(TAItems.scrapcrystalline, 4)));

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
