package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;
import net.minecraft.item.ItemHoe;

public class TAItem_Silentwood_Hoe extends ItemHoe {

	public static final String ITEMNAME = "silentwoodhoe";

	public TAItem_Silentwood_Hoe() {
		super(TAItems.Materials.SILENTWOOD);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

}
