package com.elseytd.theaurorian.Recipes;

import java.util.ArrayList;
import java.util.List;

import com.elseytd.theaurorian.TAItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ScrapperRecipeHandler {

	public static List<ScrapperRecipe> allRecipes = new ArrayList<>();
	private static List<ScrapperRecipe> recipesForRemoval = new ArrayList<>();

	public static void initRecipes() {
		//Reminder: Basic scrap is equal to how many ingots would be needed if crafted like vanilla, this multiplies by two but no more than 9 for duplication reasons!
		//Other tools: # of ingots * 6 = return nuggets
		
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.umbrasword), new ItemStack(TAItems.scrapumbra, 4)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.umbrapickaxe), new ItemStack(TAItems.scrapumbra, 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.umbrashield), new ItemStack(TAItems.scrapumbra, 9)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.spikedchestplate), new ItemStack(TAItems.scrapumbra, 9)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.aurorianitesword), new ItemStack(TAItems.scrapaurorianite, 4)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.aurorianitepickaxe), new ItemStack(TAItems.scrapaurorianite, 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.aurorianiteaxe), new ItemStack(TAItems.scrapaurorianite, 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.crystallinesword), new ItemStack(TAItems.scrapcrystalline, 4)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.crystallinepickaxe), new ItemStack(TAItems.scrapcrystalline, 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.crystallineshield), new ItemStack(TAItems.scrapcrystalline, 9)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.absorptionorb), new ItemStack(TAItems.scrapcrystalline, 8)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.livingdiviningrod), new ItemStack(TAItems.scrapcrystalline, 4)));

		addRecipe(new ScrapperRecipe(new ItemStack(Items.IRON_SWORD), new ItemStack(Items.IRON_NUGGET, 12)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.IRON_PICKAXE), new ItemStack(Items.IRON_NUGGET, 18)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.IRON_AXE), new ItemStack(Items.IRON_NUGGET, 18)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.IRON_SHOVEL), new ItemStack(Items.IRON_NUGGET, 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.IRON_HOE), new ItemStack(Items.IRON_NUGGET, 12)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.IRON_HELMET), new ItemStack(Items.IRON_NUGGET, 30)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.IRON_CHESTPLATE), new ItemStack(Items.IRON_NUGGET, 48)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.IRON_LEGGINGS), new ItemStack(Items.IRON_NUGGET, 42)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.IRON_BOOTS), new ItemStack(Items.IRON_NUGGET, 24)));

		addRecipe(new ScrapperRecipe(new ItemStack(Items.GOLDEN_SWORD), new ItemStack(Items.GOLD_NUGGET, 12)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.GOLDEN_PICKAXE), new ItemStack(Items.GOLD_NUGGET, 18)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.GOLDEN_AXE), new ItemStack(Items.GOLD_NUGGET, 18)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.GOLDEN_SHOVEL), new ItemStack(Items.GOLD_NUGGET, 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.GOLDEN_HOE), new ItemStack(Items.GOLD_NUGGET, 12)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.GOLDEN_HELMET), new ItemStack(Items.GOLD_NUGGET, 30)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.GOLDEN_CHESTPLATE), new ItemStack(Items.GOLD_NUGGET, 48)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.GOLDEN_LEGGINGS), new ItemStack(Items.GOLD_NUGGET, 42)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.GOLDEN_BOOTS), new ItemStack(Items.GOLD_NUGGET, 24)));

		addRecipe(new ScrapperRecipe(new ItemStack(Items.DIAMOND_SWORD), new ItemStack(Items.DIAMOND, 1)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.DIAMOND_PICKAXE), new ItemStack(Items.DIAMOND, 2)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.DIAMOND_AXE), new ItemStack(Items.DIAMOND, 2)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.DIAMOND_SHOVEL), new ItemStack(Items.DIAMOND, 1)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.DIAMOND_HOE), new ItemStack(Items.DIAMOND, 1)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.DIAMOND_HELMET), new ItemStack(Items.DIAMOND, 3)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.DIAMOND_CHESTPLATE), new ItemStack(Items.DIAMOND, 5)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.DIAMOND_LEGGINGS), new ItemStack(Items.DIAMOND, 4)));
		addRecipe(new ScrapperRecipe(new ItemStack(Items.DIAMOND_BOOTS), new ItemStack(Items.DIAMOND, 2)));

		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.auroriansteelsword), new ItemStack(TAItems.auroriansteelnugget, 12)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.auroriansteelpickaxe), new ItemStack(TAItems.auroriansteelnugget, 18)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.auroriansteelaxe), new ItemStack(TAItems.auroriansteelnugget, 18)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.auroriansteelshovel), new ItemStack(TAItems.auroriansteelnugget, 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.auroriansteelhoe), new ItemStack(TAItems.auroriansteelnugget, 12)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.auroriansteelhelmet), new ItemStack(TAItems.auroriansteelnugget, 30)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.auroriansteelchestplate), new ItemStack(TAItems.auroriansteelnugget, 48)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.auroriansteelleggings), new ItemStack(TAItems.auroriansteelnugget, 42)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.auroriansteelboots), new ItemStack(TAItems.auroriansteelnugget, 24)));
		
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.moonstonesword), new ItemStack(TAItems.moonstonenugget, 12)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.moonstonepickaxe), new ItemStack(TAItems.moonstonenugget, 18)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.moonstoneaxe), new ItemStack(TAItems.moonstonenugget, 18)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.moonstoneshovel), new ItemStack(TAItems.moonstonenugget, 6)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.moonstonehoe), new ItemStack(TAItems.moonstonenugget, 12)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.moonstoneshield), new ItemStack(TAItems.moonstonenugget, 36)));
		
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.ceruleanhelmet), new ItemStack(TAItems.ceruleannugget, 30)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.ceruleanchestplate), new ItemStack(TAItems.ceruleannugget, 48)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.ceruleanleggings), new ItemStack(TAItems.ceruleannugget, 42)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.ceruleanboots), new ItemStack(TAItems.ceruleannugget, 24)));
		addRecipe(new ScrapperRecipe(new ItemStack(TAItems.ceruleanshield), new ItemStack(TAItems.ceruleannugget, 36)));
		
		for (ScrapperRecipe r : recipesForRemoval) {
			ScrapperRecipe rem = null;
			for (ScrapperRecipe r2 : allRecipes) {
				if (r.getInput().getItem() == r2.getInput().getItem()) {
					if (r.getOutput().getItem() == r2.getOutput().getItem()) {
						rem = r2;
						break;
					}
				}
			}
			if (rem != null) {
				allRecipes.remove(rem);
			}
		}
	}

	public static void addRecipe(ScrapperRecipe recipe) {
		allRecipes.add(recipe);
	}

	public static void removeRecipe(ScrapperRecipe recipe) {
		recipesForRemoval.add(recipe);
	}

}
