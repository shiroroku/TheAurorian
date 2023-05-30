package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.item.ItemSpade;

public class AurorianStoneShovel extends ItemSpade {

	public static final String ITEMNAME = "aurorianstoneshovel";

	public AurorianStoneShovel() {
		super(ItemRegistry.Materials.AURORIANSTONE);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
	}

}
