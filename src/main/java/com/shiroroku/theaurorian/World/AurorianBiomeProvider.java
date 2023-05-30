package com.shiroroku.theaurorian.World;

import com.shiroroku.theaurorian.Registry.BiomeRegistry;
import com.shiroroku.theaurorian.World.Biomes.AurorianBiome;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.storage.WorldInfo;

import java.util.Collections;

public class AurorianBiomeProvider extends BiomeProvider {

	public static AurorianBiome[] biomes = new AurorianBiome[] { BiomeRegistry.aurorianplains, BiomeRegistry.aurorianforest, BiomeRegistry.aurorianforesthills, BiomeRegistry.aurorianlakes, BiomeRegistry.weepingwillowforest };

	public AurorianBiomeProvider(WorldInfo inf) {
		super(inf);
		getBiomesToSpawnIn().clear();
		allowedBiomes.clear();
		Collections.addAll(allowedBiomes, biomes);
	}

	@Override
	public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original) {
		GenLayer biomes = new AurorianGenLayer(1);

		biomes = new GenLayerSmooth(1000, biomes);

		biomes = new GenLayerZoom(1000, biomes);
		biomes = new GenLayerZoom(1000, biomes);
		biomes = new GenLayerZoom(1000, biomes);
		biomes = new GenLayerZoom(1000, biomes);

		GenLayer biomeIndexLayer = new GenLayerVoronoiZoom(10L, biomes);
		biomeIndexLayer.initWorldGenSeed(seed);

		return new GenLayer[] { biomes, biomeIndexLayer };
	}

}
