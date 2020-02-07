package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TAItem_Crafting_Nugget extends Item {

	public static final String ITEMNAME_CERULEAN = "ceruleannugget";
	public static final String ITEMNAME_MOONSTONE = "moonstonenugget";
	public static final String ITEMNAME_COAL = "auroriancoalnugget";

	public TAItem_Crafting_Nugget(String name) {
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(name);
		this.setUnlocalizedName(TAMod.MODID + "." + name);

	}

	@Override
	public int getItemBurnTime(ItemStack itemStack) {
		if (this.getRegistryName().toString().contains(ITEMNAME_COAL)) {
			return 200;
		} else {
			return -1;
		}
	}
}
