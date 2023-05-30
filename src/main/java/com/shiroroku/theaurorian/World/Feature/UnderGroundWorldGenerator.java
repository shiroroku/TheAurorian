package com.shiroroku.theaurorian.World.Feature;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.AurorianUtil;
import com.shiroroku.theaurorian.World.AurorianBiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UnderGroundWorldGenerator extends WorldGenerator {

	//TODO Find a way to generate underground mushroom biomes

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		int distance = 320;
		if (position.getX() % distance == 0 && position.getZ() % distance == 0) {

			BlockPos location = (new BlockPos(position.getX(), 30 + rand.nextInt(10), position.getZ())).add(8, 0, 8);
			int size = 250;

			float f = rand.nextFloat() * (float) Math.PI;
			double d0 = location.getX() + 8 + MathHelper.sin(f) * size / 8.0F;
			double d1 = location.getX() + 8 - MathHelper.sin(f) * size / 8.0F;
			double d2 = location.getZ() + 8 + MathHelper.cos(f) * size / 8.0F;
			double d3 = location.getZ() + 8 - MathHelper.cos(f) * size / 8.0F;
			double d4 = location.getY() + rand.nextInt(3) - 2;
			double d5 = location.getY() + rand.nextInt(3) - 2;

			List<BlockPos> blks = new ArrayList<>();
			for (int i = 0; i < size; ++i) {
				float f1 = (float) i / (float) size;
				double d6 = d0 + (d1 - d0) * f1;
				double d7 = d4 + (d5 - d4) * f1;
				double d8 = d2 + (d3 - d2) * f1;
				double d9 = rand.nextDouble() * (size) / 16.0D;
				double d10 = (MathHelper.sin((float) Math.PI * f1) + 1.0F) * d9 + 1.0D;
				double d11 = (MathHelper.sin((float) Math.PI * f1) + 1.0F) * (d9 * 0.5D) + 1.0D;
				int j = MathHelper.floor(d6 - d10 / 2.0D);
				int k = MathHelper.floor(d7 - d11 / 2.0D);
				int l = MathHelper.floor(d8 - d10 / 2.0D);
				int i1 = MathHelper.floor(d6 + d10 / 2.0D);
				int j1 = MathHelper.floor(d7 + d11 / 2.0D);
				int k1 = MathHelper.floor(d8 + d10 / 2.0D);

				//Clear out space
				for (int x = j; x <= i1; ++x) {
					double d12 = (x + 0.5D - d6) / (d10 / 2.0D);
					if (d12 * d12 < 1.0D) {
						for (int y = k; y <= j1; ++y) {
							double d13 = (y + 0.5D - d7) / (d11 / 2.0D);
							if (d12 * d12 + d13 * d13 < 1.0D) {
								for (int z = l; z <= k1; ++z) {
									double d14 = (z + 0.5D - d8) / (d10 / 2.0D);
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
					double d12 = (x + 0.5D - d6) / (d10 / 2.0D);
					if (d12 * d12 < 1.0D) {
						for (int y = k; y <= j1; ++y) {
							double d13 = (y + 0.5D - d7) / (d11 / 2.0D);
							if (d12 * d12 + d13 * d13 < 1.0D) {
								for (int z = l; z <= k1; ++z) {
									double d14 = (z + 0.5D - d8) / (d10 / 2.0D);
									if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D) {
										BlockPos blockpos = new BlockPos(x, y - 1, z);
										if (worldIn.getBlockState(blockpos.up()) == Blocks.AIR.getDefaultState()) {
											if (worldIn.getBlockState(blockpos.down()).getBlock() instanceof AurorianBiomeDecorator.AurorianStonesPredicate.IAurorianStoneType) {
												worldIn.setBlockState(blockpos, BlockRegistry.Registry.AURORIANGRASS.getBlock().getDefaultState(), 2);
												blks.add(blockpos);
											}
										}
									}
								}
							}
						}
					}
				}
			}

			for (BlockPos p : blks) {
				this.decorateBlock(worldIn, rand, p.up());
			}

			return true;
		}
		return false;
	}

	private void decorateBlock(World worldIn, Random rand, BlockPos position) {
		if (AurorianUtil.randomChanceOf(0.10)) {
			if (!this.isTouchingOrAdjacent(worldIn, position, BlockRegistry.Registry.MUSHROOMSTEM.getBlock(), 6)) {
				if (worldIn.getBlockState(position.down()) == BlockRegistry.Registry.AURORIANGRASS.getBlock().getDefaultState()) {
					MushroomWorldGenerator worldgenabstracttree = new MushroomWorldGenerator(false);
					worldgenabstracttree.generate(worldIn, rand, position);
				}
			}
		}
	}

	private boolean isTouchingOrAdjacent(World worldIn, BlockPos position, Block b, int distance) {
		for (int x = -distance; x <= distance; x++) {
			for (int y = -distance; y <= distance; y++) {
				for (int z = -distance; z <= distance; z++) {
					if (worldIn.getBlockState(position.add(x, y, z)).getBlock() == b) {
						return true;
					}
				}
			}
		}
		return false;
	}
}