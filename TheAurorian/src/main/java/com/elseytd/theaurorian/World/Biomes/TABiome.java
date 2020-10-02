package com.elseytd.theaurorian.World.Biomes;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.Entities.Hostile.CrystallineSprite_Entity;
import com.elseytd.theaurorian.Entities.Hostile.DisturbedHollow_Entity;
import com.elseytd.theaurorian.Entities.Hostile.MoonAcolyte_Entity;
import com.elseytd.theaurorian.Entities.Hostile.Spirit_Entity;
import com.elseytd.theaurorian.Entities.Passive.AurorianPig_Entity;
import com.elseytd.theaurorian.Entities.Passive.AurorianRabbit_Entity;
import com.elseytd.theaurorian.Entities.Passive.AurorianSheep_Entity;
import com.elseytd.theaurorian.World.TABiomeDecorator;
import com.elseytd.theaurorian.World.TATerrainGenerator;
import com.elseytd.theaurorian.World.Feature.TAWorldGenerator_Plant;
import com.elseytd.theaurorian.World.Feature.TAWorldGenerator_TallGrass;
import com.elseytd.theaurorian.World.Feature.TAWorldGenerator_Trees_Silentwood;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TABiome extends Biome {

	private Block stoneBlock = TABlocks.Registry.AURORIANSTONE.getBlock();
	private int genChance = 1;

	public TABiome(Biome.BiomeProperties properties) {
		super(properties.setRainDisabled().setTemperature(0.2F));
		this.topBlock = TABlocks.Registry.AURORIANGRASS.getBlock().getDefaultState();
		this.fillerBlock = TABlocks.Registry.AURORIANDIRT.getBlock().getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();

		//Ambient mobs
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(DisturbedHollow_Entity.class, 95, 1, 4));
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(Spirit_Entity.class, 2, 1, 2));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(AurorianRabbit_Entity.class, 4, 1, 2));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(AurorianSheep_Entity.class, 5, 1, 3));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(AurorianPig_Entity.class, 5, 1, 3));

		//Moontemple mobs
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(CrystallineSprite_Entity.class, 65, 2, 2));
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(MoonAcolyte_Entity.class, 35, 1, 4));

	}

	public int getSpawnWeight() {
		return this.genChance;
	}

	public void setSpawnWeight(int genChance) {
		this.genChance = genChance;
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return new TAWorldGenerator_TallGrass();
	}

	public WorldGenerator getRandomWorldGenForSilkberry(Random rand) {
		return new TAWorldGenerator_Plant(TABlocks.Registry.PLANTSILKBERRY.getBlock().getDefaultState(), 0.25f);
	}

	public WorldGenerator getRandomWorldGenForLavender(Random rand) {
		return new TAWorldGenerator_Plant(TABlocks.Registry.PLANTLAVENDER.getBlock().getDefaultState(), 1f);
	}

	public WorldGenerator getRandomWorldGenForPetunia(Random rand) {
		return new TAWorldGenerator_Plant(TABlocks.Registry.PLANTPETUNIA.getBlock().getDefaultState(), 0.25f);
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return new TAWorldGenerator_Trees_Silentwood(false);
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return this.getModdedBiomeDecorator(new TABiomeDecorator());
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		int i = TATerrainGenerator.waterLevel + 1;
		IBlockState topblk = this.topBlock;
		IBlockState fillerblk = this.fillerBlock;
		IBlockState sandblk = TABlocks.Registry.MOONSAND.getBlock().getDefaultState();
		int j = -1;
		int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int zc = x & 15;
		int xc = z & 15;

		for (int y = 255; y >= 0; --y) {
			if (y <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(xc, y, zc, BEDROCK);
			} else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(xc, y, zc);
				if (iblockstate2.getMaterial() == Material.AIR) {
					j = -1;
				} else if (iblockstate2.getBlock() == this.stoneBlock) {
					if (j == -1) {
						if (k <= 0) {
							topblk = AIR;
							fillerblk = this.stoneBlock.getDefaultState();
						} else if (y >= i - 4 && y <= i + 1) {
							topblk = this.topBlock;
							fillerblk = this.fillerBlock;
						}
						j = k;

						if (y >= i - 1) {
							chunkPrimerIn.setBlockState(xc, y, zc, topblk);
						} else if (y < i - 7 - k) {
							topblk = AIR;
							fillerblk = this.stoneBlock.getDefaultState();
							chunkPrimerIn.setBlockState(xc, y, zc, fillerblk);
						} else {
							chunkPrimerIn.setBlockState(xc, y, zc, sandblk);
						}
					} else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(xc, y, zc, fillerblk);
					}
				}
			}
		}
	}

}
