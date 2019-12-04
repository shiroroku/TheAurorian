package com.elseytd.theaurorian.World.Biomes;

import com.elseytd.theaurorian.TABiomes;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.storage.WorldInfo;

public class TABiomeProvider extends BiomeProvider {

	public TABiomeProvider(WorldInfo inf) {
		super(inf);
		getBiomesToSpawnIn().clear();
		allowedBiomes.clear();
		allowedBiomes.add(TABiomes.aurorianforest);
		allowedBiomes.add(TABiomes.aurorianplains);
	}

	@Override
	public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original) {
		GenLayer biomes = new TAGenLayer(1);

		biomes = new GenLayerZoom(1000, biomes);
		biomes = new GenLayerZoom(1000, biomes);

		GenLayer biomeIndexLayer = new GenLayerVoronoiZoom(10L, biomes);
		biomeIndexLayer.initWorldGenSeed(seed);

		return new GenLayer[] { biomes, biomeIndexLayer };
	}

}
