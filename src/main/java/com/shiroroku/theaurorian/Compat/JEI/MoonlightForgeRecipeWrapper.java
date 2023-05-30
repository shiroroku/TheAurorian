package com.shiroroku.theaurorian.Compat.JEI;

import com.shiroroku.theaurorian.Recipes.MoonlightForgeRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MoonlightForgeRecipeWrapper implements IRecipeWrapper {

	private final List<ItemStack> INPUTS;
	private final ItemStack OUTPUT;

	public MoonlightForgeRecipeWrapper(MoonlightForgeRecipe recipe) {
		this.INPUTS = new ArrayList<>();
		this.INPUTS.add(recipe.getInput1());
		this.INPUTS.add(recipe.getInput2());
		this.OUTPUT = recipe.getOutput();
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputs(VanillaTypes.ITEM, this.INPUTS);
		ingredients.setOutput(VanillaTypes.ITEM, this.OUTPUT);
	}

}
