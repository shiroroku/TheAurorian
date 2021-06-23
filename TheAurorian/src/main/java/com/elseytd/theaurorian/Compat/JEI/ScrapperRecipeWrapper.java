package com.elseytd.theaurorian.Compat.JEI;

import com.elseytd.theaurorian.Recipes.ScrapperRecipe;
import com.elseytd.theaurorian.TABlocks;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ScrapperRecipeWrapper implements IRecipeWrapper {

	private final List<ItemStack> INPUTS;
	private final ItemStack OUTPUT;

	public ScrapperRecipeWrapper(ScrapperRecipe recipe) {
		this.INPUTS = new ArrayList<>();
		this.INPUTS.add(recipe.getInput());
		this.INPUTS.add(new ItemStack(Item.getItemFromBlock(TABlocks.Registry.CRYSTAL.getBlock())));
		this.OUTPUT = recipe.getOutput();
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputs(VanillaTypes.ITEM, this.INPUTS);
		ingredients.setOutput(VanillaTypes.ITEM, this.OUTPUT);
	}

}
