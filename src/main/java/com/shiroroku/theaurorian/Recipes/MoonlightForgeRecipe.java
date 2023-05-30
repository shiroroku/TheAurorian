package com.shiroroku.theaurorian.Recipes;

import net.minecraft.item.ItemStack;

public class MoonlightForgeRecipe {
	final ItemStack INPUT1;
	final ItemStack INPUT2;
	final ItemStack OUTPUT;

	public MoonlightForgeRecipe(ItemStack input1, ItemStack input2, ItemStack output) {
		INPUT1 = input1;
		INPUT2 = input2;
		OUTPUT = output;
	}

	public ItemStack getInput1() {
		return INPUT1;
	}

	public ItemStack getInput2() {
		return INPUT2;
	}

	public ItemStack getOutput() {
		return OUTPUT;
	}
}
