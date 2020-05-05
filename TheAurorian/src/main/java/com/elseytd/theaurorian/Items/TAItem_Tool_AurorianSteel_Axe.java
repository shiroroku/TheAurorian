package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.item.ItemAxe;

public class TAItem_Tool_AurorianSteel_Axe extends ItemAxe {

	public static final String ITEMNAME = "auroriansteelaxe";

	public TAItem_Tool_AurorianSteel_Axe() {
		super(TAItems.Materials.AURORIANSTEEL, 10.0F, -3.2F);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

}
