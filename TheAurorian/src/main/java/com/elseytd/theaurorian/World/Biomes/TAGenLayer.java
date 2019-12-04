package com.elseytd.theaurorian.World.Biomes;

import com.elseytd.theaurorian.TABiomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class TAGenLayer extends GenLayer {

	protected Biome[] allowedBiomes = { TABiomes.aurorianforest, TABiomes.aurorianplains };

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
				biomes[bx + bz * width] = Biome.getIdForBiome(allowedBiomes[nextInt(allowedBiomes.length)]);
			}
		}
		return biomes;
	}

}
