package com.shiroroku.theaurorian.World;

import com.shiroroku.theaurorian.World.Biomes.AurorianBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class AurorianGenLayer extends GenLayer {

	protected AurorianBiome[] allowedBiomes = AurorianBiomeProvider.biomes;

	public AurorianGenLayer(long seed, GenLayer genlayer) {
		super(seed);
		parent = genlayer;
	}

	public AurorianGenLayer(long seed) {
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
		for (AurorianBiome b : allowedBiomes) {
			total = total + b.getSpawnWeight();
		}
		int weight = this.nextInt(total) + 1;
		for (AurorianBiome b : allowedBiomes) {
			weight = weight - b.getSpawnWeight();
			if (weight <= 0) {
				return Biome.getIdForBiome(b);
			}
		}
		return Biome.getIdForBiome(allowedBiomes[0]);
	}

}
