package com.elseytd.theaurorian.World.Biomes;

import java.util.Random;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class TABiomeAurorianPlains extends TABiome {

	public TABiomeAurorianPlains() {
		super(new BiomeProperties("aurorianplains").setBaseHeight(0.125F).setHeightVariation(0.6F).setRainDisabled().setTemperature(0.2F));
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return null;
	}

}
