package com.elseytd.theaurorian.Blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TABlock_DungeonStoneGate extends Block {

	public static final String BLOCKNAME_RUNESTONE = "runestonegate";
	public static final String BLOCKNAME_MOONTEMPLE = "moontemplegate";
	public static final String BLOCKNAME_MOONTEMPLECELL = "moontemplecellgate";

	public TABlock_DungeonStoneGate(String blockname) {
		super(Material.ROCK);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setHardness(500F);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(TAMod.MODID + "." + blockname);
		this.setRegistryName(blockname);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + "Hold shift for more info" + TextFormatting.RESET);
		} else {
			tooltip.add("Can only be placed up to " + TABlock_DungeonStoneGateKeyhole.maxBlocksFromKeyhole + " blocks away from the gate's keyhole on the X or Z axis.");
		}
	}

	public boolean isInKeyholeRange(World worldIn, BlockPos pos) {
		int maxBlocksFromKeyhole = TABlock_DungeonStoneGateKeyhole.maxBlocksFromKeyhole;

		for (int x = -maxBlocksFromKeyhole; x <= maxBlocksFromKeyhole; x++) {
			for (int y = -maxBlocksFromKeyhole; y <= maxBlocksFromKeyhole; y++) {
				IBlockState blk = worldIn.getBlockState(new BlockPos(pos.getX() - x, pos.getY() - y, pos.getZ()));
				if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_RUNESTONE)) {
					if (blk == TABlocks.runestonegatekeyhole.getDefaultState()) {
						return true;
					}
				} else if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_MOONTEMPLE)) {
					if (blk == TABlocks.moontemplegatekeyhole.getDefaultState()) {
						return true;
					}
				} else if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_MOONTEMPLECELL)) {
					if (blk == TABlocks.moontemplecellgatekeyhole.getDefaultState()) {
						return true;
					}
				}
			}
		}
		for (int z = -maxBlocksFromKeyhole; z <= maxBlocksFromKeyhole; z++) {
			for (int y = -maxBlocksFromKeyhole; y <= maxBlocksFromKeyhole; y++) {
				IBlockState blk = worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY() - y, pos.getZ() - z));
				if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_RUNESTONE)) {
					if (blk == TABlocks.runestonegatekeyhole.getDefaultState()) {
						return true;
					}
				} else if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_MOONTEMPLE)) {
					if (blk == TABlocks.moontemplegatekeyhole.getDefaultState()) {
						return true;
					}
				} else if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_MOONTEMPLECELL)) {
					if (blk == TABlocks.moontemplecellgatekeyhole.getDefaultState()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return this.isInKeyholeRange(worldIn, pos);
	}

}
