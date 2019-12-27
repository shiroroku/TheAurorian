package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TABlock_DungeonStoneBars extends BlockPane {

	public static final String BLOCKNAME_RUNESTONE = "runestonebars";
	public static final String BLOCKNAME_MOONTEMPLE = "moontemplebars";

	public TABlock_DungeonStoneBars(String blockname) {
		super(Material.IRON, false);
		this.setBlockUnbreakable();
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(blockname);
		this.setUnlocalizedName(TAMod.MODID + "." + blockname);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

}
