package com.elseytd.theaurorian.Compat.CraftTweaker;

import com.elseytd.theaurorian.Recipes.ScrapperRecipe;
import com.elseytd.theaurorian.Recipes.ScrapperRecipeHandler;

import crafttweaker.IAction;

public class ScrapperActionRemoveRecipe implements IAction {

	ScrapperRecipe RECIPE;

	public ScrapperActionRemoveRecipe(ScrapperRecipe recipe) {
		RECIPE = recipe;
	}

	@Override
	public void apply() {
		ScrapperRecipeHandler.removeRecipe(RECIPE);
	}

	@Override
	public String describe() {
		return "Removing Scrapper recipe for: [" + RECIPE.getOutput().getUnlocalizedName() + "]";
	}

}