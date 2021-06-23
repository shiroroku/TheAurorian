package com.elseytd.theaurorian.Compat.CraftTweaker;

import com.elseytd.theaurorian.Recipes.MoonlightForgeRecipe;
import com.elseytd.theaurorian.Recipes.MoonlightForgeRecipeHandler;
import crafttweaker.IAction;

public class MoonlightForgeActionRemoveRecipe implements IAction {

	MoonlightForgeRecipe RECIPE;

	public MoonlightForgeActionRemoveRecipe(MoonlightForgeRecipe recipe) {
		RECIPE = recipe;
	}

	@Override
	public void apply() {
		MoonlightForgeRecipeHandler.removeRecipe(RECIPE);
	}

	@Override
	public String describe() {
		return "Removing Moonlight Forge recipe for: [" + RECIPE.getOutput().getUnlocalizedName() + "]";
	}

}