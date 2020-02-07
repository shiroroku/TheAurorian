package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TAItem_Crafting_AurorianCoal extends Item {

	public static final String ITEMNAME = "auroriancoal";

	public TAItem_Crafting_AurorianCoal() {
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

	@Override
	public int getItemBurnTime(ItemStack itemStack) {
		return 1600;
	}

}
