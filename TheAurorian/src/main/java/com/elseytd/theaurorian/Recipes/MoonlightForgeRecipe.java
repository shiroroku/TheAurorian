package com.elseytd.theaurorian.Recipes;

import net.minecraft.item.Item;

public class MoonlightForgeRecipe {
	final Item INPUT1;
	final Item INPUT2;
	final Item OUTPUT;

	public MoonlightForgeRecipe(Item input1, Item input2, Item output) {
		INPUT1 = input1;
		INPUT2 = input2;
		OUTPUT = output;
	}

	public Item getInput1() {
		return INPUT1;
	}

	public Item getInput2() {
		return INPUT2;
	}

	public Item getOutput() {
		return OUTPUT;
	}
}
