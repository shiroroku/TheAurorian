package com.elseytd.theaurorian.World.Biomes;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class TABiomeAurorianLakes extends TABiome {

	public static final String BIOMENAME = "aurorianlakes";
	public static final String BIOMEDISPLAYNAME = "Aurorian Lakes";

	public TABiomeAurorianLakes() {
		super(new BiomeProperties(BIOMEDISPLAYNAME).setBaseHeight(-0.3F).setHeightVariation(0F));
		this.setSpawnWeight(25);
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return null;
	}

}
