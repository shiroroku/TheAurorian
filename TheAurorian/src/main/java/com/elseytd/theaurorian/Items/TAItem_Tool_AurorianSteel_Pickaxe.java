package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.item.ItemPickaxe;

public class TAItem_Tool_AurorianSteel_Pickaxe extends ItemPickaxe {

	public static final String ITEMNAME = "auroriansteelpickaxe";

	public TAItem_Tool_AurorianSteel_Pickaxe() {
		super(TAItems.Materials.AURORIANSTEEL);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

}
