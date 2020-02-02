package com.elseytd.theaurorian.World.Biomes;

public class TABiomeAurorianForest extends TABiome {

	public TABiomeAurorianForest() {
		super(new BiomeProperties("aurorianforest").setBaseHeight(0.6F).setHeightVariation(0.5F));
		this.setSpawnWeight(75);
	}

}
