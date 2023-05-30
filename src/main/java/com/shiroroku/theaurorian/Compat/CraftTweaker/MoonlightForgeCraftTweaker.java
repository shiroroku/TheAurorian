package com.shiroroku.theaurorian.Compat.CraftTweaker;

import com.shiroroku.theaurorian.Recipes.MoonlightForgeRecipe;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;


/** Script Example:
 *
 * To import: "import mods.theaurorian.MoonlightForge;"
 * To add recipe: "mods.theaurorian.MoonlightForge.addRecipe(<theaurorian:moonstonesword>, <minecraft:diamond>, <minecraft:diamond_sword>);"
 * To remove recipe: "mods.theaurorian.MoonlightForge.removeRecipe(<theaurorian:moonstonesword>, <theaurorian:aurorianiteingot>, <theaurorian:aurorianitesword>);"
 *
 * Note: the Moonlight Forge's recipe checker compares input items as Items and Itemstack counts. This means item damage and other NBT data on input items is ignored.
 */
@ModOnly("theaurorian")
@ZenClass("mods.theaurorian.MoonlightForge")
@ZenRegister
public class MoonlightForgeCraftTweaker {

	@ZenMethod
	public static void addRecipe(IItemStack input1, IItemStack input2, IItemStack output) {
		ItemStack item1 = CraftTweakerMC.getItemStack(input1);
		ItemStack item2 = CraftTweakerMC.getItemStack(input2);
		ItemStack item3 = CraftTweakerMC.getItemStack(output);
		CraftTweakerAPI.apply(new MoonlightForgeActionAddRecipe(new MoonlightForgeRecipe(item1, item2, item3)));
	}

	@ZenMethod
	public static void removeRecipe(IItemStack input1, IItemStack input2, IItemStack output) {
		ItemStack item1 = CraftTweakerMC.getItemStack(input1);
		ItemStack item2 = CraftTweakerMC.getItemStack(input2);
		ItemStack item3 = CraftTweakerMC.getItemStack(output);
		CraftTweakerAPI.apply(new MoonlightForgeActionRemoveRecipe(new MoonlightForgeRecipe(item1, item2, item3)));
	}
}
