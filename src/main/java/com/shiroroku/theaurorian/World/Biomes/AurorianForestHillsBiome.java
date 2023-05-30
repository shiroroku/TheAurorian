package com.shiroroku.theaurorian.World.Biomes;

public class AurorianForestHillsBiome extends AurorianBiome {

	public static final String BIOMENAME = "aurorianforesthills";
	public static final String BIOMEDISPLAYNAME = "Aurorian Forest Hills";

	public AurorianForestHillsBiome() {
		super(new BiomeProperties(BIOMEDISPLAYNAME).setBaseHeight(0.725F).setHeightVariation(0.2F));
		this.setSpawnWeight(75);
	}

}
