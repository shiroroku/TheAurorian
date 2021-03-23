package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAMod;

public class TAItem_Silentwood_Sickle extends TAItem_Sickle {

	public static final String ITEMNAME = "silentwoodsickle";

	public TAItem_Silentwood_Sickle() {
		super();
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
		this.setMaxDamage(50);
	}

}
