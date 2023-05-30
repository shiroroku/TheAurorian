package com.shiroroku.theaurorian.World;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent;

import java.util.Random;

public class AurorianTerrainGenerator {
	public static final int waterLevel = 65;
	private World world;
	private Random random;

	private final double[] heightMap;
	private double[] mainNoiseRegion;
	private double[] minLimitRegion;
	private double[] maxLimitRegion;
	private double[] depthRegion;

	private NoiseGeneratorOctaves minLimitPerlinNoise;
	private NoiseGeneratorOctaves maxLimitPerlinNoise;
	private NoiseGeneratorOctaves mainPerlinNoise;
	private NoiseGeneratorPerlin surfaceNoise;
	private NoiseGeneratorOctaves depthNoise;

	//Main Noise Scale X:
	//	stretches the terrain along the x-axis, consequently making the terrain more smooth.
	//	Larger values for smoother terrain.
	//Main Noise Scale Y:
	//	stretches the terrain along the y (height) axis.
	//	Larger values for smoother, higher and more hilly terrain. Ranges from 1 to 5000, and defaults to 160.
	//Main Noise Scale Z:
	//	stretches the terrain along the z-axis, consequently making the terrain more smooth.
	//	Larger values for smoother terrain. Ranges from 1 to 5000, and defaults to 80.
	//Depth Noise Scale X:
	//	changes the abruptness of terrain height along the x axis.
	//	It ranges from 1 to 2000, and defaults to 200.
	//Depth Noise Scale Z:
	//	changes the abruptness of terrain height along the z axis.
	//	It ranges from 1 to 2000, and defaults to 200.
	//Depth Noise Exponent:
	//	ranges from 0.01 to 20, and defaults to 0.5. Not quite sure what it does.
	//Depth Base Size:
	//	changes the base height of land. It ranges from 1 to 25, and defaults to 8.5.
	//	1 in this value corresponds to 8 blocks, so the default is 8.5 * 8, which is 68.
	//Coordinate Scale:
	//	Larger values sharpen and create more frequent hills without stretching the biome.
	//	It is best used last to tweak the terrain. Ranges from 1 to 6000, and defaults to 684.412.
	//Height Scale:
	//	stretches the base hills vertically before more details are made.
	//	It ranges from 1 to 6000, and defaults to 684.412.
	//Height Stretch:
	//	pulls terrain upward, with smaller values causing more extreme stretching.
	//	Ranges from 0.01 to 50, and defaults to 12.
	//Upper Limit Scale:
	//	makes terrain more solid/riddled with holes depending on how close the values are to the lower limit scale values.
	//	Ranges from 1 to 5000, and defaults to 512.
	//Lower Limit Scale:
	//	makes terrain more solid or riddled with holes depending on how close the values are to the upper limit scale values.
	//	Ranges from 1 to 5000, and defaults to 512.

	private static final int mainNoiseScaleX = 80;
	private static final int mainNoiseScaleY = 160;
	private static final int mainNoiseScaleZ = 80;
	private static final double depthNoiseScaleX = 200;
	private static final double depthNoiseScaleZ = 200;
	private static final double depthNoiseScaleExponent = 0.5;
	private static final double depthBaseSize = 8.5;
	private static final double coordScale = 800;
	private static final double heightScale = 800;
	private static final double heightStretch = 10;
	private static final double lowerLimitScale = 512;
	private static final double upperLimitScale = 512;

	private Biome[] containedBiomes;
	private final float[] biomeWeights;
	private double[] depthBuffer = new double[256];

	public AurorianTerrainGenerator() {
		this.heightMap = new double[825];

		this.biomeWeights = new float[25];
		for (int j = -2; j <= 2; ++j) {
			for (int k = -2; k <= 2; ++k) {
				float f = 10.0F / MathHelper.sqrt(j * j + k * k + 0.2F);
				this.biomeWeights[j + 2 + (k + 2) * 5] = f;
			}
		}
	}

	public void setBiomes(Biome[] biomes) {
		this.containedBiomes = biomes;
	}

	public void setup(World world, Random rand) {
		this.world = world;
		this.random = rand;

		this.minLimitPerlinNoise = new NoiseGeneratorOctaves(rand, 16);
		this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(rand, 16);
		this.mainPerlinNoise = new NoiseGeneratorOctaves(rand, 8);
		this.surfaceNoise = new NoiseGeneratorPerlin(rand, 4);
		this.depthNoise = new NoiseGeneratorOctaves(rand, 16);
		NoiseGeneratorOctaves noiseGen5 = new NoiseGeneratorOctaves(rand, 10);

		InitNoiseGensEvent.ContextOverworld ctx = new InitNoiseGensEvent.ContextOverworld(this.minLimitPerlinNoise, this.maxLimitPerlinNoise, this.mainPerlinNoise, this.surfaceNoise, noiseGen5, this.depthNoise, null);
		ctx = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(world, rand, ctx);
		this.minLimitPerlinNoise = ctx.getLPerlin1();
		this.maxLimitPerlinNoise = ctx.getLPerlin2();
		this.mainPerlinNoise = ctx.getPerlin();
		this.surfaceNoise = ctx.getHeight();
		this.depthNoise = ctx.getDepth();
	}

	private void generateHeightmap(int chunkX4, int chunkY4, int chunkZ4) {
		this.depthRegion = this.depthNoise.generateNoiseOctaves(this.depthRegion, chunkX4, chunkZ4, 5, 5, depthNoiseScaleX, depthNoiseScaleZ, depthNoiseScaleExponent);
		this.mainNoiseRegion = this.mainPerlinNoise.generateNoiseOctaves(this.mainNoiseRegion, chunkX4, chunkY4, chunkZ4, 5, 33, 5, coordScale / mainNoiseScaleX, heightScale / mainNoiseScaleY, coordScale / mainNoiseScaleZ);
		this.minLimitRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minLimitRegion, chunkX4, chunkY4, chunkZ4, 5, 33, 5, coordScale, heightScale, coordScale);
		this.maxLimitRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxLimitRegion, chunkX4, chunkY4, chunkZ4, 5, 33, 5, coordScale, heightScale, coordScale);
		int l = 0;
		int i1 = 0;

		for (int j1 = 0; j1 < 5; ++j1) {
			for (int k1 = 0; k1 < 5; ++k1) {
				float f = 0.0F;
				float f1 = 0.0F;
				float f2 = 0.0F;
				Biome biome1 = this.containedBiomes[j1 + 2 + (k1 + 2) * 10];

				for (int l1 = -2; l1 <= 2; ++l1) {
					for (int i2 = -2; i2 <= 2; ++i2) {

						Biome biome2 = this.containedBiomes[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
						float baseHeight = biome2.getBaseHeight();
						float variation = biome2.getHeightVariation();

						float f5 = this.biomeWeights[l1 + 2 + (i2 + 2) * 5] / (baseHeight + 2.0F);
						if (biome2.getBaseHeight() > biome1.getBaseHeight()) {
							f5 /= 2.0F;
						}

						f += variation * f5;
						f1 += baseHeight * f5;
						f2 += f5;
					}
				}

				f /= f2;
				f1 /= f2;
				f = f * 0.9F + 0.1F;
				f1 = (f1 * 4.0F - 1.0F) / 8.0F;
				double d12 = this.depthRegion[i1] / 8000.0D;

				if (d12 < 0.0D) {
					d12 = -d12 * 0.3D;
				}

				d12 = d12 * 3.0D - 2.0D;

				if (d12 < 0.0D) {
					d12 /= 2.0D;

					if (d12 < -1.0D) {
						d12 = -1.0D;
					}

					d12 /= 1.4D;
					d12 /= 2.0D;
				} else {
					if (d12 > 1.0D) {
						d12 = 1.0D;
					}

					d12 /= 8.0D;
				}

				++i1;
				double d13 = f1;
				double d14 = f;
				d13 += d12 * 0.2D;
				d13 = d13 * depthBaseSize / 8.0D;
				double d5 = depthBaseSize + d13 * 4.0D;

				for (int j2 = 0; j2 < 33; ++j2) {
					double d6 = (j2 - d5) * heightStretch * 128.0D / 256.0D / d14;

					if (d6 < 0.0D) {
						d6 *= 4.0D;
					}

					double d7 = this.minLimitRegion[l] / lowerLimitScale;
					double d8 = this.maxLimitRegion[l] / upperLimitScale;
					double d9 = (this.mainNoiseRegion[l] / 10.0D + 1.0D) / 2.0D;
					double d10 = MathHelper.clampedLerp(d7, d8, d9) - d6;

					if (j2 > 29) {
						double d11 = ((j2 - 29) / 3.0F);
						d10 = d10 * (1.0D - d11) + -10.0D * d11;
					}

					this.heightMap[l] = d10;
					++l;
				}
			}
		}
	}

	public void generate(int chunkX, int chunkZ, ChunkPrimer primer) {
		this.generateHeightmap(chunkX * 4, 0, chunkZ * 4);
		for (int x4 = 0; x4 < 4; ++x4) {
			int l = x4 * 5;
			int i1 = (x4 + 1) * 5;

			for (int z4 = 0; z4 < 4; ++z4) {
				int k1 = (l + z4) * 33;
				int l1 = (l + z4 + 1) * 33;
				int i2 = (i1 + z4) * 33;
				int j2 = (i1 + z4 + 1) * 33;

				for (int height32 = 0; height32 < 32; ++height32) {
					double d0 = 0.125D;
					double d1 = this.heightMap[k1 + height32];
					double d2 = this.heightMap[l1 + height32];
					double d3 = this.heightMap[i2 + height32];
					double d4 = this.heightMap[j2 + height32];
					double d5 = (this.heightMap[k1 + height32 + 1] - d1) * d0;
					double d6 = (this.heightMap[l1 + height32 + 1] - d2) * d0;
					double d7 = (this.heightMap[i2 + height32 + 1] - d3) * d0;
					double d8 = (this.heightMap[j2 + height32 + 1] - d4) * d0;

					for (int h = 0; h < 8; ++h) {
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;
						int height = (height32 * 8) + h;

						for (int x = 0; x < 4; ++x) {
							double d14 = 0.25D;
							double d16 = (d11 - d10) * d14;
							double d15 = d10 - d16;

							for (int z = 0; z < 4; ++z) {
								if (height < 2) {
									primer.setBlockState(x4 * 4 + x, height32 * 8 + h, z4 * 4 + z, Blocks.BEDROCK.getDefaultState());
								} else if ((d15 += d16) > 0.0D) {
									primer.setBlockState(x4 * 4 + x, height32 * 8 + h, z4 * 4 + z, BlockRegistry.Registry.AURORIANSTONE.getBlock().getDefaultState());
								} else if (height < waterLevel && height > 30) {
									primer.setBlockState(x4 * 4 + x, height32 * 8 + h, z4 * 4 + z, BlockRegistry.Registry.FLUIDMOONWATER.getBlock().getDefaultState());
								}
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, IChunkGenerator generator, Biome[] biomes) {
		if (!ForgeEventFactory.onReplaceBiomeBlocks(generator, x, z, primer, this.world)) {
			return;
		}
		this.depthBuffer = this.surfaceNoise.getRegion(this.depthBuffer, x * 16, z * 16, 16, 16, 0.0625D, 0.0625D, 1.0D);

		for (int xInChunk = 0; xInChunk < 16; ++xInChunk) {
			for (int zInChunk = 0; zInChunk < 16; ++zInChunk) {
				Biome biome = biomes[zInChunk + xInChunk * 16];
				biome.genTerrainBlocks(this.world, this.random, primer, x * 16 + xInChunk, z * 16 + zInChunk, this.depthBuffer[zInChunk + xInChunk * 16]);
			}
		}
	}
}
