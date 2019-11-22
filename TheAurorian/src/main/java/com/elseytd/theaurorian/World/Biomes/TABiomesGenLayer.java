package com.elseytd.theaurorian.World.Biomes;

import com.elseytd.theaurorian.TABiomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class TABiomesGenLayer extends GenLayer {

	protected Biome[] allowedBiomes = { TABiomes.aurorianforest, TABiomes.aurorianplains };

	public TABiomesGenLayer(long seed, GenLayer genlayer) {
		super(seed);
		parent = genlayer;
	}

	public TABiomesGenLayer(long seed) {
		super(seed);
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth) {
		int[] dest = IntCache.getIntCache(width * depth);

		for (int dz = 0; dz < depth; dz++)
			for (int dx = 0; dx < width; dx++) {
				initChunkSeed(dx + x, dz + z);
				dest[dx + dz * width] = Biome.getIdForBiome(allowedBiomes[nextInt(allowedBiomes.length)]);
			}
		return dest;
	}

}
