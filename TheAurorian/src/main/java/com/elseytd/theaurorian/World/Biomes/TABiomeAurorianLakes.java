package com.elseytd.theaurorian.World.Biomes;

import java.util.Random;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class TABiomeAurorianLakes extends TABiome {

	public static final String BIOMENAME = "aurorianlakes";
	
	public TABiomeAurorianLakes() {
		super(new BiomeProperties(BIOMENAME).setBaseHeight(-0.3F).setHeightVariation(0F));
		this.setSpawnWeight(25);
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return null;
	}

}
