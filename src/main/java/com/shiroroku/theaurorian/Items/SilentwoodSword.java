package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
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
