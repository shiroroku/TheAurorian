package com.elseytd.theaurorian.World;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TAWorldGenerator_UnderWater extends WorldGenerator {

	private final Block blockIn;
	private final int numberOfBlocks;

	public TAWorldGenerator_UnderWater(int amt, Block b) {
		this.blockIn = b;
		this.numberOfBlocks = amt;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if (worldIn.getBlockState(position).getMaterial() != Material.WATER) {
			return false;
		} else {
			int i = rand.nextInt(this.numberOfBlocks - 2) + 2;
			for (int k = position.getX() - i; k <= position.getX() + i; ++k) {
				for (int l = position.getZ() - i; l <= position.getZ() + i; ++l) {
					int i1 = k - position.getX();
					int j1 = l - position.getZ();
					if (i1 * i1 + j1 * j1 <= i * i) {
						int y = position.getY() - 2;
						for (int k1 = y - 1; k1 <= y + 1; ++k1) {
							BlockPos blockpos = new BlockPos(k, k1, l);
							Block block = worldIn.getBlockState(blockpos).getBlock();
							if (block == TABlocks.moonsand || block == blockIn) {
								worldIn.setBlockState(blockpos, this.blockIn.getDefaultState(), 2);
							}
						}
					}
				}
			}
			return true;
		}
	}
}
