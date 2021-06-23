package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAMod;
import net.minecraft.item.Item;

public class TAItem_CrystallineSprite extends Item {

	public static final String ITEMNAME = "crystallinesprite";

	public TAItem_CrystallineSprite() {
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

}
