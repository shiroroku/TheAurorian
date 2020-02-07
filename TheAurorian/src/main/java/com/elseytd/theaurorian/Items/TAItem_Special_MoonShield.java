package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class TAItem_Special_MoonShield extends TAItem_Tool_Shield {

	public static final String ITEMNAME = "moonshield";

	public TAItem_Special_MoonShield() {
		super();
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
		this.setMaxDamage(512);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 100;
	}

	@Override
	public net.minecraftforge.common.IRarity getForgeRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

}
