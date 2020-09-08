package com.elseytd.theaurorian.World.Biomes;

public class TABiomeAurorianForestHills extends TABiome {

	public static final String BIOMENAME = "aurorianforesthills";
	public static final String BIOMEDISPLAYNAME = "Aurorian Forest Hills";

	public TABiomeAurorianForestHills() {
		super(new BiomeProperties(BIOMEDISPLAYNAME).setBaseHeight(0.725F).setHeightVariation(0.2F));
		this.setSpawnWeight(75);
	}

}
