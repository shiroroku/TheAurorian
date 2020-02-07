package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.item.ItemSpade;

public class TAItem_Tool_Aurorian_Stone_Shovel extends ItemSpade {

	public static final String ITEMNAME = "aurorianstoneshovel";

	public TAItem_Tool_Aurorian_Stone_Shovel() {
		super(TAItems.Materials.AURORIANSTONE);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

}
