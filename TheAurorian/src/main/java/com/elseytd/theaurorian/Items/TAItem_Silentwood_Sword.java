package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;
import net.minecraft.item.ItemSword;

public class TAItem_Silentwood_Sword extends ItemSword {

	public static final String ITEMNAME = "silentwoodsword";

	public TAItem_Silentwood_Sword() {
		super(TAItems.Materials.SILENTWOOD);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

}
