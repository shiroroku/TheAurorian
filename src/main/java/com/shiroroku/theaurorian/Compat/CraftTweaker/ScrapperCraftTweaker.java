package com.shiroroku.theaurorian.Compat.CraftTweaker;

import com.shiroroku.theaurorian.Recipes.ScrapperRecipe;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;


@ModOnly("theaurorian")
@ZenClass("mods.theaurorian.Scrapper")
@ZenRegister
public class ScrapperCraftTweaker {
	
	@ZenMethod
	public static void addRecipe(IItemStack input, IItemStack output) {
		ItemStack item = CraftTweakerMC.getItemStack(input);
		ItemStack item3 = CraftTweakerMC.getItemStack(output);
		CraftTweakerAPI.apply(new ScrapperActionAddRecipe(new ScrapperRecipe(item, item3)));
	}
	
	@ZenMethod
	public static void removeRecipe(IItemStack input, IItemStack output) {
		ItemStack item = CraftTweakerMC.getItemStack(input);
		ItemStack item3 = CraftTweakerMC.getItemStack(output);
		CraftTweakerAPI.apply(new ScrapperActionRemoveRecipe(new ScrapperRecipe(item, item3)));
	}
}
