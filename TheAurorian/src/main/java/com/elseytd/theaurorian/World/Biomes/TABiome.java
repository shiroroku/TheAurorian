package com.elseytd.theaurorian.World.Biomes;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.Entities.AurorianPig.TAEntity_AurorianPig;
import com.elseytd.theaurorian.Entities.AurorianRabbit.TAEntity_AurorianRabbit;
import com.elseytd.theaurorian.Entities.AurorianSheep.TAEntity_AurorianSheep;
import com.elseytd.theaurorian.Entities.Hollow.TAEntity_DisturbedHollow;
import com.elseytd.theaurorian.World.TATerrainGenerator;
import com.elseytd.theaurorian.World.TAWorldGenerator_Lavender;
import com.elseytd.theaurorian.World.TAWorldGenerator_Silkberry;
import com.elseytd.theaurorian.World.TAWorldGenerator_Tallgrass;
import com.elseytd.theaurorian.World.TAWorldGenerator_Trees;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TABiome extends Biome {

	Block stoneBlock = TABlocks.aurorianstone;
	
	public TABiome(Biome.BiomeProperties properties) {
		super(properties.setRainDisabled().setTemperature(0.2F));
		this.topBlock = TABlocks.auroriangrass.getDefaultState();
		this.fillerBlock = TABlocks.auroriandirt.getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(TAEntity_DisturbedHollow.class, 95, 1, 4));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(TAEntity_AurorianRabbit.class, 4, 1, 2));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(TAEntity_AurorianSheep.class, 5, 1, 3));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(TAEntity_AurorianPig.class, 5, 1, 3));
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
		return new TAWorldGenerator_Trees(false);
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return getModdedBiomeDecorator(new TABiomeDecorator());
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		int i = TATerrainGenerator.waterLevel + 1;
		IBlockState iblockstate = this.topBlock;
		IBlockState iblockstate1 = this.fillerBlock;
		IBlockState iblockstatesand = TABlocks.moonsand.getDefaultState();
		int j = -1;
		int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int l = x & 15;
		int i1 = z & 15;

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
						j = k;

						if (j1 >= i - 1) {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
						} else if (j1 < i - 7 - k) {
							iblockstate = AIR;
							iblockstate1 = stoneBlock.getDefaultState();
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
						} else {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstatesand);
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
