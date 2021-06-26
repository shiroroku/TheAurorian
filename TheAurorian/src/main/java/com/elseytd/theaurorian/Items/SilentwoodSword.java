package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.Registry.ItemRegistry;
import com.elseytd.theaurorian.AurorianMod;
import net.minecraft.item.ItemSword;

public class SilentwoodSword extends ItemSword {

	public static final String ITEMNAME = "silentwoodsword";

	public SilentwoodSword() {
		super(ItemRegistry.Materials.SILENTWOOD);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
	}

}
