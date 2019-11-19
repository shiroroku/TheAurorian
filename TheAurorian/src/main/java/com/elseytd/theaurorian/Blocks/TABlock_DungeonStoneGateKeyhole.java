package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TAUtil;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TABlock_DungeonStoneGateKeyhole extends Block {

	public static final String BLOCKNAME_RUNESTONE = "runestonegatekeyhole";
	public static final String BLOCKNAME_MOONTEMPLE = "moontemplegatekeyhole";
	public static final String BLOCKNAME_MOONTEMPLECELL = "moontemplecellgatekeyhole";
	public static final int maxBlocksFromKeyhole = 2;

	public TABlock_DungeonStoneGateKeyhole(String blockname) {
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

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		Item key = null;
		if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_RUNESTONE)) {
			key = TAItems.runestonekey;
		} else if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_MOONTEMPLE)) {
			key = TAItems.moontemplekey;
		} else if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_MOONTEMPLECELL)) {
			key = TAItems.moontemplecellkey;
		}

		if (!playerIn.isSneaking() && playerIn.getHeldItem(hand).getItem() == key) {
			playerIn.getHeldItem(hand).damageItem(2, playerIn);
			this.breakGateBlocks(worldIn, pos);
			if (!worldIn.isRemote) {
				worldIn.destroyBlock(pos, false);
			}
			playerIn.playSound(SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, 1F, 1F);
		} else if (!playerIn.isSneaking() && playerIn.getHeldItem(hand).getItem() == TAItems.lockpicks && this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_RUNESTONE)) {
			playerIn.getHeldItem(hand).damageItem(1, playerIn);
			if (TAUtil.randomChanceOf(0.33F)) {
				this.breakGateBlocks(worldIn, pos);
				if (!worldIn.isRemote) {
					worldIn.destroyBlock(pos, false);
				}
				playerIn.playSound(SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, 1F, 1F);
			} else {
				playerIn.playSound(SoundEvents.ENTITY_ITEM_BREAK, 0.5F, 1F);
			}
		}
		return true;
	}

	public void breakGateBlocks(World worldIn, BlockPos pos) {
		for (int x = -maxBlocksFromKeyhole; x <= maxBlocksFromKeyhole; x++) {
			for (int y = -maxBlocksFromKeyhole; y <= maxBlocksFromKeyhole; y++) {
				BlockPos blkpos = new BlockPos(pos.getX() - x, pos.getY() - y, pos.getZ());
				IBlockState blk = worldIn.getBlockState(blkpos);
				if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_RUNESTONE)) {
					if (blk == TABlocks.runestonegate.getDefaultState()) {
						if (!worldIn.isRemote) {
							worldIn.destroyBlock(blkpos, false);
						}
					}
				} else if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_MOONTEMPLE)) {
					if (blk == TABlocks.moontemplegate.getDefaultState()) {
						if (!worldIn.isRemote) {
							worldIn.destroyBlock(blkpos, false);
						}
					}
				} else if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_MOONTEMPLECELL)) {
					if (blk == TABlocks.moontemplecellgate.getDefaultState()) {
						if (!worldIn.isRemote) {
							worldIn.destroyBlock(blkpos, false);
						}
					}
				}
			}
		}

		for (int z = -maxBlocksFromKeyhole; z <= maxBlocksFromKeyhole; z++) {
			for (int y = -maxBlocksFromKeyhole; y <= maxBlocksFromKeyhole; y++) {
				BlockPos blkpos = new BlockPos(pos.getX(), pos.getY() - y, pos.getZ() - z);
				IBlockState blk = worldIn.getBlockState(blkpos);
				if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_RUNESTONE)) {
					if (blk == TABlocks.runestonegate.getDefaultState()) {
						if (!worldIn.isRemote) {
							worldIn.destroyBlock(blkpos, false);
						}
					}
				} else if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_MOONTEMPLE)) {
					if (blk == TABlocks.moontemplegate.getDefaultState()) {
						if (!worldIn.isRemote) {
							worldIn.destroyBlock(blkpos, false);
						}
					}
				} else if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_MOONTEMPLECELL)) {
					if (blk == TABlocks.moontemplecellgate.getDefaultState()) {
						if (!worldIn.isRemote) {
							worldIn.destroyBlock(blkpos, false);
						}
					}
				}
			}
		}
	}

}