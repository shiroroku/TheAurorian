package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.AurorianMod;

public class AurorianStoneSickleItem extends SickleItem {

	public static final String ITEMNAME = "aurorianstonesickle";

	public AurorianStoneSickleItem() {
		super();
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
		this.setMaxDamage(150);
	}

}
