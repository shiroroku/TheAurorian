package com.elseytd.theaurorian.World.Biomes;

public class TABiomeAurorianForest extends TABiome {

	public static final String BIOMENAME = "aurorianforest";
	public static final String BIOMEDISPLAYNAME = "Aurorian Forest";

	public TABiomeAurorianForest() {
		super(new BiomeProperties(BIOMEDISPLAYNAME).setBaseHeight(0.5F).setHeightVariation(0.02F));
		this.setSpawnWeight(75);
	}

}
