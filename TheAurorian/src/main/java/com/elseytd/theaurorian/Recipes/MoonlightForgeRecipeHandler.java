package com.elseytd.theaurorian.Recipes;

import java.util.ArrayList;
import java.util.List;

import com.elseytd.theaurorian.TAItems;

public class MoonlightForgeRecipeHandler {

	public static List<MoonlightForgeRecipe> allRecipes = new ArrayList<>();

	public static void initRecipes() {
		allRecipes.add(new MoonlightForgeRecipe(TAItems.moonstonesword, TAItems.aurorianiteingot, TAItems.aurorianitesword));
		allRecipes.add(new MoonlightForgeRecipe(TAItems.moonstoneaxe, TAItems.aurorianiteingot, TAItems.aurorianiteaxe));
		allRecipes.add(new MoonlightForgeRecipe(TAItems.moonstonepickaxe, TAItems.aurorianiteingot, TAItems.aurorianitepickaxe));
		allRecipes.add(new MoonlightForgeRecipe(TAItems.moonstonesword, TAItems.crystallineingot, TAItems.crystallinesword));
		allRecipes.add(new MoonlightForgeRecipe(TAItems.moonstonepickaxe, TAItems.crystallineingot, TAItems.crystallinepickaxe));
		allRecipes.add(new MoonlightForgeRecipe(TAItems.moonstonesword, TAItems.umbraingot, TAItems.umbrasword));
		allRecipes.add(new MoonlightForgeRecipe(TAItems.moonstonepickaxe, TAItems.umbraingot, TAItems.umbrapickaxe));
	}
	
}
