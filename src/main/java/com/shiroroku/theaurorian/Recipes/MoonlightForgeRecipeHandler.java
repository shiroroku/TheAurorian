package com.shiroroku.theaurorian.Recipes;

import com.shiroroku.theaurorian.Registry.ItemRegistry;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MoonlightForgeRecipeHandler {

	public static List<MoonlightForgeRecipe> allRecipes = new ArrayList<>();
	private static final List<MoonlightForgeRecipe> recipesForRemoval = new ArrayList<>();

	public static void initRecipes() {
		addRecipe(new MoonlightForgeRecipe(new ItemStack(ItemRegistry.Registry.MOONSTONESWORD.getItem()), new ItemStack(ItemRegistry.Registry.INGOTAURORIANITE.getItem()), new ItemStack(ItemRegistry.Registry.AURORIANITESWORD.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(ItemRegistry.Registry.MOONSTONEAXE.getItem()), new ItemStack(ItemRegistry.Registry.INGOTAURORIANITE.getItem()), new ItemStack(ItemRegistry.Registry.AURORIANITEAXE.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(ItemRegistry.Registry.MOONSTONEPICKAXE.getItem()), new ItemStack(ItemRegistry.Registry.INGOTAURORIANITE.getItem()), new ItemStack(ItemRegistry.Registry.AURORIANITEPICKAXE.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(ItemRegistry.Registry.MOONSTONESWORD.getItem()), new ItemStack(ItemRegistry.Registry.INGOTCRYSTALLINE.getItem()), new ItemStack(ItemRegistry.Registry.CRYSTALLINESWORD.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(ItemRegistry.Registry.MOONSTONEPICKAXE.getItem()), new ItemStack(ItemRegistry.Registry.INGOTCRYSTALLINE.getItem()), new ItemStack(ItemRegistry.Registry.CRYSTALLINEPICKAXE.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(ItemRegistry.Registry.MOONSTONESWORD.getItem()), new ItemStack(ItemRegistry.Registry.INGOTUMBRA.getItem()), new ItemStack(ItemRegistry.Registry.UMBRASWORD.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(ItemRegistry.Registry.MOONSTONEPICKAXE.getItem()), new ItemStack(ItemRegistry.Registry.INGOTUMBRA.getItem()), new ItemStack(ItemRegistry.Registry.UMBRAPICKAXE.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(ItemRegistry.Registry.MOONSTONESHIELD.getItem()), new ItemStack(ItemRegistry.Registry.INGOTUMBRA.getItem()), new ItemStack(ItemRegistry.Registry.UMBRASHIELD.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(ItemRegistry.Registry.MOONSTONESHIELD.getItem()), new ItemStack(ItemRegistry.Registry.INGOTCRYSTALLINE.getItem()), new ItemStack(ItemRegistry.Registry.CRYSTALLINESHIELD.getItem())));

		//Boss Item Recipes
		addRecipe(new MoonlightForgeRecipe(new ItemStack(ItemRegistry.Registry.MOONSTONESHIELD.getItem()), new ItemStack(ItemRegistry.Registry.TROPHYMOONQUEEN.getItem()), new ItemStack(ItemRegistry.Registry.MOONSHIELD.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(ItemRegistry.Registry.AURORIANSTEELPICKAXE.getItem()), new ItemStack(ItemRegistry.Registry.TROPHYMOONQUEEN.getItem()), new ItemStack(ItemRegistry.Registry.QUEENSCHIPPER.getItem())));
		addRecipe(new MoonlightForgeRecipe(new ItemStack(ItemRegistry.Registry.SILENTWOODBOW.getItem()), new ItemStack(ItemRegistry.Registry.TROPHYKEEPER.getItem()), new ItemStack(ItemRegistry.Registry.KEEPERSBOW.getItem())));

		for (MoonlightForgeRecipe r : recipesForRemoval) {
			MoonlightForgeRecipe rem = null;
			for (MoonlightForgeRecipe r2 : allRecipes) {
				if (r.getInput1().getItem() == r2.getInput1().getItem()) {
					if (r.getInput2().getItem() == r2.getInput2().getItem()) {
						if (r.getOutput().getItem() == r2.getOutput().getItem()) {
							rem = r2;
							break;
						}
					}
				}
			}
			if (rem != null) {
				allRecipes.remove(rem);
			}
		}
	}

	public static void addRecipe(MoonlightForgeRecipe recipe) {
		allRecipes.add(recipe);
	}

	public static void removeRecipe(MoonlightForgeRecipe recipe) {
		recipesForRemoval.add(recipe);
	}

}
