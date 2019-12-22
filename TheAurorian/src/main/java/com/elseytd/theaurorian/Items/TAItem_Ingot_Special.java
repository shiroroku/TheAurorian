package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Ingot_Special extends Item {

	public static final String ITEMNAME_AURORIANITE = "aurorianiteingot";
	public static final String ITEMNAME_CRYSTALLINE = "crystallineingot";
	public static final String ITEMNAME_UMBRA = "umbraingot";
	public static final String ITEMNAME_AURORIANSTEEL = "auroriansteel";
	
	public TAItem_Ingot_Special(String itemname) {
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(itemname);
		this.setUnlocalizedName(TAMod.MODID + "." + itemname);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	@Override
	public net.minecraftforge.common.IRarity getForgeRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}
}
