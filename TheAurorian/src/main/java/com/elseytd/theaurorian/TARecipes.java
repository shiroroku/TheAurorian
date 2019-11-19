package com.elseytd.theaurorian;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TARecipes {

	public static void registerFurnaceRecipes() {
		// BASIC
		GameRegistry.addSmelting(TABlocks.auroriancobblestone, new ItemStack(TABlocks.aurorianstone), 0.1F);
		GameRegistry.addSmelting(TABlocks.ceruleanore, new ItemStack(TAItems.ceruleaningot), 1F);
		GameRegistry.addSmelting(TABlocks.moonstoneore, new ItemStack(TAItems.moonstoneingot), 1F);
		GameRegistry.addSmelting(TAItems.silkberry, new ItemStack(TAItems.silkberryrasin), 0.1F);
		GameRegistry.addSmelting(TAItems.aurorianpork, new ItemStack(TAItems.aurorianporkcooked), 0.1F);

		// RECYCLING
		GameRegistry.addSmelting(TAItems.moonstonesword, new ItemStack(TAItems.moonstonenugget, 4), 0F);
		GameRegistry.addSmelting(TAItems.moonstonehoe, new ItemStack(TAItems.moonstonenugget, 4), 0F);
		GameRegistry.addSmelting(TAItems.moonstonepickaxe, new ItemStack(TAItems.moonstonenugget, 6), 0F);
		GameRegistry.addSmelting(TAItems.moonstoneshovel, new ItemStack(TAItems.moonstonenugget, 2), 0F);
		GameRegistry.addSmelting(TAItems.moonstonesickle, new ItemStack(TAItems.moonstonenugget, 6), 0F);
		GameRegistry.addSmelting(TAItems.moonstoneaxe, new ItemStack(TAItems.moonstonenugget, 6), 0F);
		GameRegistry.addSmelting(TAItems.ceruleanboots, new ItemStack(TAItems.ceruleannugget, 8), 0F);
		GameRegistry.addSmelting(TAItems.ceruleanchestplate, new ItemStack(TAItems.ceruleannugget, 16), 0F);
		GameRegistry.addSmelting(TAItems.ceruleanleggings, new ItemStack(TAItems.ceruleannugget, 14), 0F);
		GameRegistry.addSmelting(TAItems.ceruleanhelmet, new ItemStack(TAItems.ceruleannugget, 10), 0F);

	}

}
