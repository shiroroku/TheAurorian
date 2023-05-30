package com.shiroroku.theaurorian.Compat.CraftTweaker;

import com.shiroroku.theaurorian.Recipes.ScrapperRecipe;
import com.shiroroku.theaurorian.Recipes.ScrapperRecipeHandler;
import crafttweaker.IAction;

public class ScrapperActionAddRecipe implements IAction {

	ScrapperRecipe RECIPE;

	public ScrapperActionAddRecipe(ScrapperRecipe recipe) {
		RECIPE = recipe;
	}

	@Override
	public void apply() {
		ScrapperRecipeHandler.addRecipe(RECIPE);
	}

	@Override
	public String describe() {
		return "Adding Scrapper recipe for: [" + RECIPE.getOutput().getUnlocalizedName() + "]";
	}
}