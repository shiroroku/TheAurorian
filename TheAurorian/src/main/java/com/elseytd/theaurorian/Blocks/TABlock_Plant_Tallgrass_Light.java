package com.elseytd.theaurorian.Blocks;

import java.util.List;
import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;

public class TABlock_Plant_Tallgrass_Light extends BlockBush implements IShearable {

	public static final String BLOCKNAME = "auroriantallgrasslight";
	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

	public TABlock_Plant_Tallgrass_Light() {
		super(Material.VINE);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setHardness(0.0F);
		this.setRegistryName(BLOCKNAME);
		this.setSoundType(SoundType.PLANT);
		this.setUnlocalizedName(TAMod.MODID + "." + BLOCKNAME);
	}

	@Override
	public Block.EnumOffsetType getOffsetType() {
		return Block.EnumOffsetType.XYZ;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB.offset(state.getOffset(source, pos));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		/*
		 * if (RANDOM.nextInt(8) != 0) return; ItemStack seed =
		 * net.minecraftforge.common.ForgeHooks.getGrassSeed(RANDOM, fortune);
		 * if (!seed.isEmpty()) drops.add(seed);
		 */
	}

	@Override
	protected boolean canSustainBush(IBlockState state) {
		return state.getBlock() == TABlocks.Registry.AURORIANGRASSLIGHT.getBlock();
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return NonNullList.withSize(1, new ItemStack(TAItems.Registry.PLANTFIBER.getItem()));
	}

}
