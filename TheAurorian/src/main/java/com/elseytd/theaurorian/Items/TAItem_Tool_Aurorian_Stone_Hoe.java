package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.item.ItemHoe;

public class TAItem_Tool_Aurorian_Stone_Hoe extends ItemHoe {

	public static final String ITEMNAME = "aurorianstonehoe";

	public TAItem_Tool_Aurorian_Stone_Hoe() {
		super(TAItems.Materials.AURORIANSTONE);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

}
