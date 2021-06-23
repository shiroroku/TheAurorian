package com.elseytd.theaurorian.Compat.CraftTweaker;

import com.elseytd.theaurorian.Recipes.MoonlightForgeRecipe;
import com.elseytd.theaurorian.Recipes.MoonlightForgeRecipeHandler;
import crafttweaker.IAction;

public class MoonlightForgeActionAddRecipe implements IAction {

	MoonlightForgeRecipe RECIPE;

	public MoonlightForgeActionAddRecipe(MoonlightForgeRecipe recipe) {
		RECIPE = recipe;
	}

	@Override
	public void apply() {
		MoonlightForgeRecipeHandler.addRecipe(RECIPE);
	}

	@Override
	public String describe() {
		return "Adding Moonlight Forge recipe for: [" + RECIPE.getOutput().getUnlocalizedName() + "]";
	}
}