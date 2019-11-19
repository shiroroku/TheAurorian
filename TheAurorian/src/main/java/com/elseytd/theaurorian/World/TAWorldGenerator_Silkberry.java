package com.elseytd.theaurorian.World;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TAWorldGenerator_Silkberry extends WorldGenerator {

	private final IBlockState silkberryState;

	public TAWorldGenerator_Silkberry() {
		this.silkberryState = TABlocks.silkberryplant.getDefaultState();
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		for (IBlockState iblockstate = worldIn.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position) || iblockstate.getBlock().isLeaves(iblockstate, worldIn, position)) && position.getY() > 0; iblockstate = worldIn.getBlockState(position)) {
			position = position.down();
		}

		for (int i = 0; i < 128; ++i) {
			BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

			if (worldIn.isAirBlock(blockpos) && TABlocks.silkberryplant.canBlockStay(worldIn, blockpos, this.silkberryState)) {
				worldIn.setBlockState(blockpos, this.silkberryState, 2);
			}
		}

		return true;
	}

}
