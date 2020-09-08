package com.elseytd.theaurorian.World.Biomes;

import java.util.Random;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class TABiomeAurorianPlains extends TABiome {

	public static final String BIOMENAME = "aurorianplains";
	public static final String BIOMEDISPLAYNAME = "Aurorian Plains";

	public TABiomeAurorianPlains() {
		super(new BiomeProperties(BIOMEDISPLAYNAME).setBaseHeight(0.5F).setHeightVariation(0.01F));
		this.setSpawnWeight(50);
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return null;
	}

}
