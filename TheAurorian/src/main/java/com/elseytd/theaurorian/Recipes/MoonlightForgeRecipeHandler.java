package com.elseytd.theaurorian.Recipes;

import java.util.ArrayList;
import java.util.List;

import com.elseytd.theaurorian.TAItems;

import net.minecraft.item.ItemStack;

public class MoonlightForgeRecipeHandler {

	public static List<MoonlightForgeRecipe> allRecipes = new ArrayList<>();
	private static List<MoonlightForgeRecipe> recipesForRemoval = new ArrayList<>();

	public static void initRecipes() {
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.moonstonesword), new ItemStack(TAItems.aurorianiteingot), new ItemStack(TAItems.aurorianitesword)));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.moonstoneaxe), new ItemStack(TAItems.aurorianiteingot), new ItemStack(TAItems.aurorianiteaxe)));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.moonstonepickaxe), new ItemStack(TAItems.aurorianiteingot), new ItemStack(TAItems.aurorianitepickaxe)));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.moonstonesword), new ItemStack(TAItems.crystallineingot), new ItemStack(TAItems.crystallinesword)));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.moonstonepickaxe), new ItemStack(TAItems.crystallineingot), new ItemStack(TAItems.crystallinepickaxe)));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.moonstonesword), new ItemStack(TAItems.umbraingot), new ItemStack(TAItems.umbrasword)));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.moonstonepickaxe), new ItemStack(TAItems.umbraingot), new ItemStack(TAItems.umbrapickaxe)));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.moonstoneshield), new ItemStack(TAItems.umbraingot), new ItemStack(TAItems.umbrashield)));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.moonstoneshield), new ItemStack(TAItems.crystallineingot), new ItemStack(TAItems.crystallineshield)));

		//Boss Item Recipes
		addRecipe(new MoonlightForgeRecipe(new ItemStack(TAItems.moonstoneshield), new ItemStack(TAItems.trophymoonqueen), new ItemStack(TAItems.moonshield)));
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
