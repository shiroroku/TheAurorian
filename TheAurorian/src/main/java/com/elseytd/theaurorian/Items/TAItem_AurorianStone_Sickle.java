package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAMod;

public class TAItem_AurorianStone_Sickle extends TAItem_Sickle {

	public static final String ITEMNAME = "aurorianstonesickle";

	public TAItem_AurorianStone_Sickle() {
		super();
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
		this.setMaxDamage(150);
	}

}
