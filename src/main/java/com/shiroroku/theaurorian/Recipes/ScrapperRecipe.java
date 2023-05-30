package com.shiroroku.theaurorian.Recipes;

import net.minecraft.item.ItemStack;

public class ScrapperRecipe {
	final ItemStack INPUT;
	final ItemStack OUTPUT;

	public ScrapperRecipe(ItemStack input, ItemStack output) {
		INPUT = input;
		OUTPUT = output;
	}

	public ItemStack getInput() {
		return INPUT;
	}

	public ItemStack getOutput() {
		return OUTPUT;
	}
}
