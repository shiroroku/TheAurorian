package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.Registry.ItemRegistry;
import com.elseytd.theaurorian.AurorianMod;
import net.minecraft.item.ItemSpade;

public class SilentwoodShovel extends ItemSpade {

	public static final String ITEMNAME = "silentwoodshovel";

	public SilentwoodShovel() {
		super(ItemRegistry.Materials.SILENTWOOD);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
	}

}
