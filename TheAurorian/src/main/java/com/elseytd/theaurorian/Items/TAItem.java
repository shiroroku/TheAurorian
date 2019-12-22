package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem extends Item {

	public static final String ITEMNAME_AURORIANSLIMEBALL = "aurorianslimeball";
	public static final String ITEMNAME_SCRAP_AURORIANITE = "scrapaurorianite";
	public static final String ITEMNAME_SCRAP_CRYSTALLINE = "scrapcrystalline";
	public static final String ITEMNAME_SCRAP_UMBRA = "scrapumbra";

	public TAItem(String name) {
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(name);
		this.setUnlocalizedName(TAMod.MODID + "." + name);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}
