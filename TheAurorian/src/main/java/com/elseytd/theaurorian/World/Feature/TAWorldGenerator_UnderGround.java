package com.elseytd.theaurorian.World.Feature;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAUtil;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TAWorldGenerator_UnderGround extends WorldGenerator {

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		int distance = 512;
		if (position.getX() % distance == 0 && position.getZ() % distance == 0) {
			
			BlockPos location = (new BlockPos(position.getX(), 30 + rand.nextInt(10), position.getZ())).add(8, 0, 8);
			int size = 250;

			float f = rand.nextFloat() * (float) Math.PI;
			double d0 = (double) ((float) (location.getX() + 8) + MathHelper.sin(f) * (float) size / 8.0F);
			double d1 = (double) ((float) (location.getX() + 8) - MathHelper.sin(f) * (float) size / 8.0F);
			double d2 = (double) ((float) (location.getZ() + 8) + MathHelper.cos(f) * (float) size / 8.0F);
			double d3 = (double) ((float) (location.getZ() + 8) - MathHelper.cos(f) * (float) size / 8.0F);
			double d4 = (double) (location.getY() + rand.nextInt(3) - 2);
			double d5 = (double) (location.getY() + rand.nextInt(3) - 2);

			for (int i = 0; i < size; ++i) {
				float f1 = (float) i / (float) size;
				double d6 = d0 + (d1 - d0) * (double) f1;
				double d7 = d4 + (d5 - d4) * (double) f1;
				double d8 = d2 + (d3 - d2) * (double) f1;
				double d9 = rand.nextDouble() * (double) (size) / 16.0D;
				double d10 = (double) (MathHelper.sin((float) Math.PI * f1) + 1.0F) * d9 + 1.0D;
				double d11 = (double) (MathHelper.sin((float) Math.PI * f1) + 1.0F) * (d9 * 0.5D) + 1.0D;
				int j = MathHelper.floor(d6 - d10 / 2.0D);
				int k = MathHelper.floor(d7 - d11 / 2.0D);
				int l = MathHelper.floor(d8 - d10 / 2.0D);
				int i1 = MathHelper.floor(d6 + d10 / 2.0D);
				int j1 = MathHelper.floor(d7 + d11 / 2.0D);
				int k1 = MathHelper.floor(d8 + d10 / 2.0D);

				//Clear out space
				for (int x = j; x <= i1; ++x) {
					double d12 = ((double) x + 0.5D - d6) / (d10 / 2.0D);
					if (d12 * d12 < 1.0D) {
						for (int y = k; y <= j1; ++y) {
							double d13 = ((double) y + 0.5D - d7) / (d11 / 2.0D);
							if (d12 * d12 + d13 * d13 < 1.0D) {
								for (int z = l; z <= k1; ++z) {
									double d14 = ((double) z + 0.5D - d8) / (d10 / 2.0D);
									if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D) {
										BlockPos blockpos = new BlockPos(x, y, z);
										worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
									}
								}
							}
						}
					}
				}

				//Make ground grass and decorate
				for (int x = j; x <= i1; ++x) {
					double d12 = ((double) x + 0.5D - d6) / (d10 / 2.0D);
					if (d12 * d12 < 1.0D) {
						for (int y = k; y <= j1; ++y) {
							double d13 = ((double) y + 0.5D - d7) / (d11 / 2.0D);
							if (d12 * d12 + d13 * d13 < 1.0D) {
								for (int z = l; z <= k1; ++z) {
									double d14 = ((double) z + 0.5D - d8) / (d10 / 2.0D);
									if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D) {
										BlockPos blockpos = new BlockPos(x, y - 1, z);
										if (worldIn.getBlockState(blockpos.up()) == Blocks.AIR.getDefaultState()) {
											if (worldIn.getBlockState(blockpos.down()) == TABlocks.aurorianstone.getDefaultState()) {
												worldIn.setBlockState(blockpos, TABlocks.auroriangrass.getDefaultState(), 2);
												decorateBlock(worldIn, rand, blockpos.up());
											}
										}
									}
								}
							}
						}
					}
				}
			}

			return true;
		}
		return false;
	}

	private void decorateBlock(World worldIn, Random rand, BlockPos position) {
		if(TAUtil.randomChanceOf(0.25)) {
			if(!isTouchingOrAdjacent(worldIn, position, TABlocks.moonglass, 4)) {
				for(int i = 0; i < 3; i++) {
					worldIn.setBlockState(position.up(i), TABlocks.moonglass.getDefaultState());
					//TODO mushrooms
				}
			}
		}
	}
	
	private boolean isTouchingOrAdjacent(World worldIn, BlockPos position, Block b, int distance) {
		for(int x = -distance; x <= distance; x++) {
			for(int z = -distance; z <= distance; z++) {
				if(worldIn.getBlockState(position.add(x, 0, z)).getBlock() == b) {
					return true;
				}
			}
		}
		return false;
	}
}
