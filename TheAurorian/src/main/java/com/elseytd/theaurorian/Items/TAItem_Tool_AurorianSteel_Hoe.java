package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.item.ItemHoe;

public class TAItem_Tool_AurorianSteel_Hoe extends ItemHoe {

	public static final String ITEMNAME = "auroriansteelhoe";

	public TAItem_Tool_AurorianSteel_Hoe() {
		super(TAItems.Materials.AURORIANSTEEL);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

}
