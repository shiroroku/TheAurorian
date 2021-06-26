package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.Registry.ItemRegistry;
import com.elseytd.theaurorian.AurorianMod;
import net.minecraft.item.ItemSword;

public class AurorianStoneSword extends ItemSword {

	public static final String ITEMNAME = "aurorianstonesword";

	public AurorianStoneSword() {
		super(ItemRegistry.Materials.AURORIANSTONE);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
	}

}
