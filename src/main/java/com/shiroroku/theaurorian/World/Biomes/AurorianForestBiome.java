package com.shiroroku.theaurorian.World.Biomes;

public class AurorianForestBiome extends AurorianBiome {

	public static final String BIOMENAME = "aurorianforest";
	public static final String BIOMEDISPLAYNAME = "Aurorian Forest";

	public AurorianForestBiome() {
		super(new BiomeProperties(BIOMEDISPLAYNAME).setBaseHeight(0.5F).setHeightVariation(0.02F));
		this.setSpawnWeight(75);
	}

}
