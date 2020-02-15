package com.elseytd.theaurorian.World.Feature;

import java.util.Random;

import com.elseytd.theaurorian.TAUtil;

import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TAWorldGenerator_Plant extends WorldGenerator {

	private final IBlockState plantState;
	private final float plantDensity;

	public TAWorldGenerator_Plant(IBlockState plant, float density) {
		this.plantState = plant;
		this.plantDensity = density;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		for (IBlockState iblockstate = worldIn.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position) || iblockstate.getBlock().isLeaves(iblockstate, worldIn, position)) && position.getY() > 0; iblockstate = worldIn.getBlockState(position)) {
			position = position.down();
		}
		if (plantState.getBlock() instanceof BlockBush) {
			BlockBush blk = (BlockBush) plantState.getBlock();
			for (int i = 0; i < 128; ++i) {
				BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

				if (worldIn.isAirBlock(blockpos) && blk.canBlockStay(worldIn, blockpos, worldIn.getBlockState(blockpos.down())) && TAUtil.randomChanceOf(plantDensity)) {
					worldIn.setBlockState(blockpos, this.plantState, 2);
				}
			}
			return true;
		} else {
			return false;
		}
	}
}
