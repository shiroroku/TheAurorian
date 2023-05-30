package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.AurorianMod;

public class SilentwoodSickle extends SickleItem {

	public static final String ITEMNAME = "silentwoodsickle";

	public SilentwoodSickle() {
		super();
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
		this.setMaxDamage(50);
	}

}
