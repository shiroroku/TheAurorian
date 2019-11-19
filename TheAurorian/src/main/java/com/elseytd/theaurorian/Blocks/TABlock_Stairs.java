package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TABlock_Stairs extends BlockStairs {

	public static final String BLOCKNAME_SILENTWOOD = "silentwoodstairs";
	public static final String BLOCKNAME_AURORIANCOBBLESTONE = "auroriancobblestonestairs";
	public static final String BLOCKNAME_RUNESTONE = "runestonestairs";
	public static final String BLOCKNAME_MOONTEMPLE = "moontemplestairs";
	public static final String BLOCKNAME_AURORIANSTONE = "aurorianstonestairs";

	public TABlock_Stairs(Block blockbase, String name) {
		super(blockbase.getDefaultState());
        this.setLightOpacity(0);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setUnlocalizedName(TAMod.MODID + "." + name);
		this.setRegistryName(name);
		if (name == BLOCKNAME_AURORIANCOBBLESTONE || name == BLOCKNAME_AURORIANSTONE) {
			this.setHarvestLevel("pickaxe", 0);
		} else if (name == BLOCKNAME_MOONTEMPLE || name == BLOCKNAME_RUNESTONE) {
			this.setHardness(500F);
		}
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

}
