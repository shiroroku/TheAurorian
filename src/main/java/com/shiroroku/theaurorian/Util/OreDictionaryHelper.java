package com.shiroroku.theaurorian.Util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class OreDictionaryHelper {

	/**
	 * Contains all ores and modded ores in the ore dictionary, populated by
	 * populateOrelocallist in postInit.
	 */
	public static List<ItemStack> Ores = null;

	/**
	 * Populates a list with all items and blocks that are registered as "ore"
	 * in the OreDictionary.
	 */
	public static List<ItemStack> populateOrelist() {
		String[] names = OreDictionary.getOreNames();
		List<ItemStack> ores = new ArrayList<>();
		for (String s : names) {
			if (s.startsWith("ore")) {
				ores.addAll(OreDictionary.getOres(s));
			}
		}
		return ores;
	}

	/**
	 * Checks if the given itemstack is an ore block.
	 */
	public static boolean isOre(ItemStack itemIn) {
		for (ItemStack i : OreDictionaryHelper.Ores) {
			if (ItemStack.areItemsEqual(i, itemIn)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets an other itemstack of the tag specified. For example, an itemstack
	 * registered as "oreMoonstone" and a type specified as "ingot" will return
	 * the registered ore dictionary itemstack of "ingotMoonstone".
	 *
	 * @param itemIn Ore dictionary item
	 * @param type Ore name, Ex:("ingot", "nugget")
	 */
	public static ItemStack getTypeFromOre(ItemStack itemIn, String type) {
		List<ItemStack> nuggets = new ArrayList<>();
		for (int i : OreDictionary.getOreIDs(itemIn)) {
			String orename = OreDictionary.getOreName(i);
			String nuggetnamewouldbe = type + orename.substring(3);
			nuggets = OreDictionary.getOres(nuggetnamewouldbe);
			if (nuggets != null) {
				if (!nuggets.isEmpty()) {
					return nuggets.get(0);
				}
			}
		}
		return null;
	}

}