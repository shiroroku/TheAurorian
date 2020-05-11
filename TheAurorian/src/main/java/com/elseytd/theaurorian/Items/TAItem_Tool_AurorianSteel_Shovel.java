package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.item.ItemSpade;

public class TAItem_Tool_AurorianSteel_Shovel extends ItemSpade {

	public static final String ITEMNAME = "auroriansteelshovel";

	public TAItem_Tool_AurorianSteel_Shovel() {
		super(TAItems.Materials.AURORIANSTEEL);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

}
