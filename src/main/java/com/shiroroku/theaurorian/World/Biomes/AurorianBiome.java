package com.shiroroku.theaurorian.World.Biomes;

import com.shiroroku.theaurorian.Entities.Hostile.CrystallineSpriteEntity;
import com.shiroroku.theaurorian.Entities.Hostile.DisturbedHollowEntity;
import com.shiroroku.theaurorian.Entities.Hostile.MoonAcolyteEntity;
import com.shiroroku.theaurorian.Entities.Hostile.SpiritEntity;
import com.shiroroku.theaurorian.Entities.Passive.AurorianPigEntity;
import com.shiroroku.theaurorian.Entities.Passive.AurorianRabbitEntity;
import com.shiroroku.theaurorian.Entities.Passive.AurorianSheepEntity;
import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.World.Feature.PlantWorldGenerator;
import com.shiroroku.theaurorian.World.Feature.TallGrassWorldGenerator;
import com.shiroroku.theaurorian.World.Feature.SilentwoodTreeWorldGenerator;
import com.shiroroku.theaurorian.World.AurorianBiomeDecorator;
import com.shiroroku.theaurorian.World.AurorianTerrainGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class AurorianBiome extends Biome {

	private final Block stoneBlock = BlockRegistry.Registry.AURORIANSTONE.getBlock();
	private int genChance = 1;

	public AurorianBiome(Biome.BiomeProperties properties) {
		super(properties.setRainDisabled().setTemperature(0.2F));
		this.topBlock = BlockRegistry.Registry.AURORIANGRASS.getBlock().getDefaultState();
		this.fillerBlock = BlockRegistry.Registry.AURORIANDIRT.getBlock().getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();

		//Ambient mobs
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(DisturbedHollowEntity.class, 95, 1, 4));
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(SpiritEntity.class, 2, 1, 2));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(AurorianRabbitEntity.class, 4, 1, 2));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(AurorianSheepEntity.class, 5, 1, 3));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(AurorianPigEntity.class, 5, 1, 3));

		//Moontemple mobs
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(CrystallineSpriteEntity.class, 65, 2, 2));
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(MoonAcolyteEntity.class, 35, 1, 4));

	}

	public int getSpawnWeight() {
		return this.genChance;
	}

	public void setSpawnWeight(int genChance) {
		this.genChance = genChance;
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return new TallGrassWorldGenerator();
	}

	public WorldGenerator getRandomWorldGenForSilkberry(Random rand) {
		return new PlantWorldGenerator(BlockRegistry.Registry.PLANTSILKBERRY.getBlock().getDefaultState(), 0.25f);
	}

	public WorldGenerator getRandomWorldGenForLavender(Random rand) {
		return new PlantWorldGenerator(BlockRegistry.Registry.PLANTLAVENDER.getBlock().getDefaultState(), 1f);
	}

	public WorldGenerator getRandomWorldGenForPetunia(Random rand) {
		return new PlantWorldGenerator(BlockRegistry.Registry.PLANTPETUNIA.getBlock().getDefaultState(), 0.25f);
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return new SilentwoodTreeWorldGenerator(false);
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return this.getModdedBiomeDecorator(new AurorianBiomeDecorator());
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		int i = AurorianTerrainGenerator.waterLevel + 1;
		IBlockState topblk = this.topBlock;
		IBlockState fillerblk = this.fillerBlock;
		IBlockState sandblk = BlockRegistry.Registry.MOONSAND.getBlock().getDefaultState();
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
				} else {
					if (iblockstate2.getBlock() == this.stoneBlock) {
						if (j == -1) {
							if (k <= 0) {
								topblk = AIR;
								fillerblk = this.stoneBlock.getDefaultState();
							} else {
								if (y >= i - 4 && y <= i + 1) {
									topblk = this.topBlock;
									fillerblk = this.fillerBlock;
								}
							}
							j = k;

							if (y >= i - 1) {
								chunkPrimerIn.setBlockState(xc, y, zc, topblk);
							} else {
								if (y < i - 7 - k) {
									topblk = AIR;
									fillerblk = this.stoneBlock.getDefaultState();
									chunkPrimerIn.setBlockState(xc, y, zc, fillerblk);
								} else {
									chunkPrimerIn.setBlockState(xc, y, zc, sandblk);
								}
							}
						} else {
							if (j > 0) {
								--j;
								chunkPrimerIn.setBlockState(xc, y, zc, fillerblk);
							}
						}
					}
				}
			}
		}
	}

}
