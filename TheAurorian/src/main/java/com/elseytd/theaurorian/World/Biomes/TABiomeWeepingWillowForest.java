package com.elseytd.theaurorian.World.Biomes;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.World.Feature.TAWorldGenerator_Trees_WeepingWillow;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TABiomeWeepingWillowForest extends TABiome {

	public TABiomeWeepingWillowForest() {
		super(new BiomeProperties("aurorianplains").setBaseHeight(0.125F).setHeightVariation(0.6F));
		this.topBlock = TABlocks.auroriangrasslight.getDefaultState();
		this.setSpawnWeight(10);
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return new TAWorldGenerator_Trees_WeepingWillow(false, true);
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return null;
	}

	@Override
	public WorldGenerator getRandomWorldGenForSilkberry(Random rand) {
		return null;
	}

	@Override
	public WorldGenerator getRandomWorldGenForLavender(Random rand) {
		return null;
	}

	@Override
	public WorldGenerator getRandomWorldGenForPetunia(Random rand) {
		return null;
	}

}
