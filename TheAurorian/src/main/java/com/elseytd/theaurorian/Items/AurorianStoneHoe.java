package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.Registry.ItemRegistry;
import com.elseytd.theaurorian.AurorianMod;
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
