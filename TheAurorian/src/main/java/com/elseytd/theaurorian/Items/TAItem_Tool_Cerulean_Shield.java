package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.item.ItemStack;

public class TAItem_Tool_Cerulean_Shield extends TAItem_Tool_Shield {

	public static final String ITEMNAME = "ceruleanshield";

	public TAItem_Tool_Cerulean_Shield() {
		super();
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
		this.setMaxDamage(512);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == TAItems.Registry.INGOTCERULEAN.getItem() ? true : super.getIsRepairable(toRepair, repair);
	}

}
