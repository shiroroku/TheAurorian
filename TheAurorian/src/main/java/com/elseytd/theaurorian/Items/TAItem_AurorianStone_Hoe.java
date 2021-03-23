package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.item.ItemHoe;

public class TAItem_AurorianStone_Hoe extends ItemHoe {

	public static final String ITEMNAME = "aurorianstonehoe";

	public TAItem_AurorianStone_Hoe() {
		super(TAItems.Materials.AURORIANSTONE);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

}
