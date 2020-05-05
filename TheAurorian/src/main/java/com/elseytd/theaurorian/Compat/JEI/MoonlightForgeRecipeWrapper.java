package com.elseytd.theaurorian.Compat.JEI;

import java.util.ArrayList;
import java.util.List;

import com.elseytd.theaurorian.Recipes.MoonlightForgeRecipe;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class MoonlightForgeRecipeWrapper implements IRecipeWrapper {

	private final List<ItemStack> INPUTS;
	private final ItemStack OUTPUT;

	public MoonlightForgeRecipeWrapper(MoonlightForgeRecipe recipe) {
		this.INPUTS = new ArrayList<>();
		INPUTS.add(recipe.getInput1());
		INPUTS.add(recipe.getInput2());
		this.OUTPUT = recipe.getOutput();
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputs(VanillaTypes.ITEM, INPUTS);
		ingredients.setOutput(VanillaTypes.ITEM, OUTPUT);
	}

}
