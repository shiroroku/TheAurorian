package com.elseytd.theaurorian.World.Biomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class TAGenLayer extends GenLayer {

	protected TABiome[] allowedBiomes = TABiomeProvider.biomes;

	public TAGenLayer(long seed, GenLayer genlayer) {
		super(seed);
		parent = genlayer;
	}

	public TAGenLayer(long seed) {
		super(seed);
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth) {
		int[] biomes = IntCache.getIntCache(width * depth);
		for (int bx = 0; bx < depth; bx++) {
			for (int bz = 0; bz < width; bz++) {
				initChunkSeed(bx + x, bz + z);
				biomes[bx + bz * width] = getBiomeByWeight();
			}
		}
		return biomes;
	}

	private int getBiomeByWeight() {
		int total = 0;
		for (TABiome b : allowedBiomes) {
			total = total + b.getSpawnWeight();
		}
		int weight = this.nextInt(total) + 1;
		for (TABiome b : allowedBiomes) {
			weight = weight - b.getSpawnWeight();
			if (weight <= 0) {
				return Biome.getIdForBiome(b);
			}
		}
		return Biome.getIdForBiome(allowedBiomes[0]);
	}

}
