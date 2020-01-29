package com.elseytd.theaurorian.World;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TAWorldGenerator_Urns extends WorldGenerator {

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if (worldIn.isAirBlock(position)) {
			if (isValidPos(worldIn, position, TABlocks.aurorianstone)) {
				worldIn.setBlockState(position, TABlocks.urn.getDefaultState(), 2);
				return true;
			}
		}
		return false;
	}

	public boolean isValidPos(World worldIn, BlockPos position, Block cornerblocks) {
		if (worldIn.getBlockState(position.down()).getBlock() == cornerblocks) {
			int countedsides = 0;

			if (worldIn.getBlockState(position.north()).getBlock() == cornerblocks) {
				countedsides++;
			}
			if (worldIn.getBlockState(position.south()).getBlock() == cornerblocks) {
				countedsides++;
			}
			if (worldIn.getBlockState(position.east()).getBlock() == cornerblocks) {
				countedsides++;
			}
			if (worldIn.getBlockState(position.west()).getBlock() == cornerblocks) {
				countedsides++;
			}

			if (countedsides >= 2) {
				return true;
			}

		}
		return false;
	}
}
