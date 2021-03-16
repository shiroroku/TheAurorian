package com.elseytd.theaurorian.Recipes;

import java.util.ArrayList;
import java.util.List;

import com.elseytd.theaurorian.TAItems;

import net.minecraft.item.ItemStack;

public class MoonlightForgeRecipeHandler {

	public static List<MoonlightForgeRecipe> allRecipes = new ArrayList<>();
	private static List<MoonlightForgeRecipe> recipesForRemoval = new ArrayList<>();

	public static void initRecipes() {
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.Registry.MOONSTONESWORD.getItem()), new ItemStack(TAItems.Registry.INGOTAURORIANITE.getItem()), new ItemStack(TAItems.Registry.AURORIANITESWORD.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.Registry.MOONSTONEAXE.getItem()), new ItemStack(TAItems.Registry.INGOTAURORIANITE.getItem()), new ItemStack(TAItems.Registry.AURORIANITEAXE.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.Registry.MOONSTONEPICKAXE.getItem()), new ItemStack(TAItems.Registry.INGOTAURORIANITE.getItem()), new ItemStack(TAItems.Registry.AURORIANITEPICKAXE.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.Registry.MOONSTONESWORD.getItem()), new ItemStack(TAItems.Registry.INGOTCRYSTALLINE.getItem()), new ItemStack(TAItems.Registry.CRYSTALLINESWORD.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.Registry.MOONSTONEPICKAXE.getItem()), new ItemStack(TAItems.Registry.INGOTCRYSTALLINE.getItem()), new ItemStack(TAItems.Registry.CRYSTALLINEPICKAXE.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.Registry.MOONSTONESWORD.getItem()), new ItemStack(TAItems.Registry.INGOTUMBRA.getItem()), new ItemStack(TAItems.Registry.UMBRASWORD.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.Registry.MOONSTONEPICKAXE.getItem()), new ItemStack(TAItems.Registry.INGOTUMBRA.getItem()), new ItemStack(TAItems.Registry.UMBRAPICKAXE.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.Registry.MOONSTONESHIELD.getItem()), new ItemStack(TAItems.Registry.INGOTUMBRA.getItem()), new ItemStack(TAItems.Registry.UMBRASHIELD.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.Registry.MOONSTONESHIELD.getItem()), new ItemStack(TAItems.Registry.INGOTCRYSTALLINE.getItem()), new ItemStack(TAItems.Registry.CRYSTALLINESHIELD.getItem())));

		//Boss Item Recipes
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.Registry.MOONSTONESHIELD.getItem()), new ItemStack(TAItems.Registry.TROPHYMOONQUEEN.getItem()), new ItemStack(TAItems.Registry.MOONSHIELD.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.Registry.AURORIANSTEELPICKAXE.getItem()), new ItemStack(TAItems.Registry.TROPHYMOONQUEEN.getItem()), new ItemStack(TAItems.Registry.QUEENSCHIPPER.getItem())));
		//addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.silentwoodbow), new ItemStack(TAItems.trophykeeper), new ItemStack(TAItems.keepersbow)));

		for (MoonlightForgeRecipe r : recipesForRemoval) {
			MoonlightForgeRecipe rem = null;
			for (MoonlightForgeRecipe r2 : allRecipes) {
				if (r.getInput1().getItem() == r2.getInput1().getItem()) {
					if (r.getInput2().getItem() == r2.getInput2().getItem()) {
						if (r.getOutput().getItem() == r2.getOutput().getItem()) {
							rem = r2;
							break;
						}
					}
				}
			}
			if (rem != null) {
				allRecipes.remove(rem);
			}
		}
	}

	public static void addRecipe(MoonlightForgeRecipe recipe) {
		allRecipes.add(recipe);
	}

	public static void removeRecipe(MoonlightForgeRecipe recipe) {
		recipesForRemoval.add(recipe);
	}

}
