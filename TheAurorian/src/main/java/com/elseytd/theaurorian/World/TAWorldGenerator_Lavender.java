package com.elseytd.theaurorian.World;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TAWorldGenerator_Lavender extends WorldGenerator {

	private final IBlockState lavenderState;

	public TAWorldGenerator_Lavender() {
		this.lavenderState = TABlocks.lavenderplant.getDefaultState();
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		for (IBlockState iblockstate = worldIn.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position) || iblockstate.getBlock().isLeaves(iblockstate, worldIn, position)) && position.getY() > 0; iblockstate = worldIn.getBlockState(position)) {
			position = position.down();
		}

		for (int i = 0; i < 128; ++i) {
			BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

			if (worldIn.isAirBlock(blockpos) && TABlocks.lavenderplant.canBlockStay(worldIn, blockpos, this.lavenderState) && blockpos.getY() <= 90) {
				worldIn.setBlockState(blockpos, this.lavenderState, 2);
			}
		}

		return true;
	}

}
