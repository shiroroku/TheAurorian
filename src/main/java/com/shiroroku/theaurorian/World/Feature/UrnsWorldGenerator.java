package com.shiroroku.theaurorian.World.Feature;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class UrnsWorldGenerator extends WorldGenerator {

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if (worldIn.isAirBlock(position)) {
			if (this.isValidPos(worldIn, position, BlockRegistry.Registry.AURORIANSTONE.getBlock())) {
				worldIn.setBlockState(position, BlockRegistry.Registry.URN.getBlock().getDefaultState(), 2);
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

			return countedsides >= 2;

		}
		return false;
	}
}
