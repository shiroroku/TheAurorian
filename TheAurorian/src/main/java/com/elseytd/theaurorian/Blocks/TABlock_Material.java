package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TABlock_Material extends Block {
	public static final String BLOCKNAME_CERULEAN = "ceruleanblock";
	public static final String BLOCKNAME_MOONSTONE = "moonstoneblock";
	public static final String BLOCKNAME_COAL = "auroriancoalblock";

	public TABlock_Material(String name) {
		super(Material.IRON);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setHardness(5.0F);
		this.setRegistryName(name);
		this.setSoundType(SoundType.METAL);
		this.setUnlocalizedName(TAMod.MODID + "." + name);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

}
