package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.AurorianMod;
import net.minecraft.item.Item;

public class CrystallineSpriteItem extends Item {

	public static final String ITEMNAME = "crystallinesprite";

	public CrystallineSpriteItem() {
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
	}

}
