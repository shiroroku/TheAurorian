package com.elseytd.theaurorian.World.Biomes;

import com.elseytd.theaurorian.TABiomes;

import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.storage.WorldInfo;

public class TABiomeProvider extends BiomeProvider {
	public TABiomeProvider(WorldInfo info) {
		super(info);
		allowedBiomes.clear();
		allowedBiomes.add(TABiomes.aurorianforest);
		allowedBiomes.add(TABiomes.aurorianplains);
	}
}
