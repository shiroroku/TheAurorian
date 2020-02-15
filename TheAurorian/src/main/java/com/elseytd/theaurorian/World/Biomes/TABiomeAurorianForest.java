package com.elseytd.theaurorian.World.Biomes;

public class TABiomeAurorianForest extends TABiome {

	public static final String BIOMENAME = "aurorianforest";

	public TABiomeAurorianForest() {
		super(new BiomeProperties(BIOMENAME).setBaseHeight(0.5F).setHeightVariation(0.02F));
		this.setSpawnWeight(75);
	}

}
