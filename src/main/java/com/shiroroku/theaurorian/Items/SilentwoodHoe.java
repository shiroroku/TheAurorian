package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.item.ItemHoe;

public class SilentwoodHoe extends ItemHoe {

	public static final String ITEMNAME = "silentwoodhoe";

	public SilentwoodHoe() {
		super(ItemRegistry.Materials.SILENTWOOD);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
	}

}
