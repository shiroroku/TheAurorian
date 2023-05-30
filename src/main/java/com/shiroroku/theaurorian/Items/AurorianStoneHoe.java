package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.item.ItemHoe;

public class AurorianStoneHoe extends ItemHoe {

	public static final String ITEMNAME = "aurorianstonehoe";

	public AurorianStoneHoe() {
		super(ItemRegistry.Materials.AURORIANSTONE);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
	}

}
