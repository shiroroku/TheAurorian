package com.elseytd.theaurorian.World.Biomes;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.Entities.Hollow.TAEntity_DisturbedHollow;
import com.elseytd.theaurorian.World.TAWorldGenerator_Lavender;
import com.elseytd.theaurorian.World.TAWorldGenerator_Silkberry;
import com.elseytd.theaurorian.World.TAWorldGenerator_Tallgrass;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TABiomeAurorianPlains extends Biome {

	Block stoneBlock = TABlocks.aurorianstone;

	public TABiomeAurorianPlains() {
		super(new BiomeProperties("aurorianplains").setBaseHeight(1.0F).setHeightVariation(0.8F).setRainDisabled().setTemperature(0.2F));

		this.topBlock = TABlocks.auroriangrass.getDefaultState();
		this.fillerBlock = TABlocks.auroriandirt.getDefaultState();

		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();

		this.spawnableMonsterList.add(new Biome.SpawnListEntry(TAEntity_DisturbedHollow.class, 95, 1, 4));
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return new TAWorldGenerator_Tallgrass();
	}

	public WorldGenerator getRandomWorldGenForSilkberry(Random rand) {
		return new TAWorldGenerator_Silkberry();
	}

	public WorldGenerator getRandomWorldGenForLavender(Random rand) {
		return new TAWorldGenerator_Lavender();
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return null;
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return getModdedBiomeDecorator(new TABiomeDecoratorForest());
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		int i = worldIn.getSeaLevel();
		IBlockState iblockstate = this.topBlock;
		IBlockState iblockstate1 = this.fillerBlock;
		int j = -1;
		int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int l = x & 15;
		int i1 = z & 15;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (int j1 = 255; j1 >= 0; --j1) {
			if (j1 <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
			} else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

				if (iblockstate2.getMaterial() == Material.AIR) {
					j = -1;
				} else if (iblockstate2.getBlock() == stoneBlock) {
					if (j == -1) {
						if (k <= 0) {
							iblockstate = AIR;
							iblockstate1 = stoneBlock.getDefaultState();
						} else if (j1 >= i - 4 && j1 <= i + 1) {
							iblockstate = this.topBlock;
							iblockstate1 = this.fillerBlock;
						}

						if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
							if (this.getTemperature(blockpos$mutableblockpos.setPos(x, j1, z)) < 0.15F) {
								iblockstate = ICE;
							} else {
								iblockstate = WATER;
							}
						}

						j = k;

						if (j1 >= i - 1) {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
						} else if (j1 < i - 7 - k) {
							iblockstate = AIR;
							iblockstate1 = stoneBlock.getDefaultState();
							chunkPrimerIn.setBlockState(i1, j1, l, GRAVEL);
						} else {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
						}
					} else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);

						if (j == 0 && iblockstate1.getBlock() == Blocks.SAND && k > 1) {
							j = rand.nextInt(4) + Math.max(0, j1 - 63);
							iblockstate1 = iblockstate1.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? RED_SANDSTONE : SANDSTONE;
						}
					}
				}
			}
		}
	}

}
