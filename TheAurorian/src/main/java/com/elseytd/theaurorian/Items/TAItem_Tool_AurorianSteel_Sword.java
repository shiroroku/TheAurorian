package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.item.ItemSword;

public class TAItem_Tool_AurorianSteel_Sword extends ItemSword {
	public static final String ITEMNAME = "auroriansteelsword";

	public TAItem_Tool_AurorianSteel_Sword() {
		super(TAItems.Materials.AURORIANSTEEL);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}
}
