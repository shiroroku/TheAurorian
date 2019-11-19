package com.elseytd.theaurorian.World;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TAWorldGenerator_Sand extends WorldGenerator {

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if (worldIn.getBlockState(position).getBlock() == TABlocks.auroriangrass && position.getY() <= 75) {
			worldIn.setBlockState(position, TABlocks.moonsand.getDefaultState(), 2);
			return true;
		}
		return false;
	}
}
