package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAMod;

public class TAItem_Tool_Aurorian_Stone_Sickle extends TAItem_Tool_Sickle {

	public static final String ITEMNAME = "aurorianstonesickle";

	public TAItem_Tool_Aurorian_Stone_Sickle() {
		super();
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
		this.setMaxDamage(150);
	}

}
