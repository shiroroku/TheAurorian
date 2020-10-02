package com.elseytd.theaurorian.World.Biomes;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.World.Feature.TAWorldGenerator_Trees_WeepingWillow;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TABiomeWeepingWillowForest extends TABiome {

	public static final String BIOMENAME = "weepingwillowforest";
	public static final String BIOMEDISPLAYNAME = "Weeping Willow Forest";

	public TABiomeWeepingWillowForest() {
		super(new BiomeProperties(BIOMEDISPLAYNAME).setBaseHeight(0.6F).setHeightVariation(0.5F));
		this.topBlock = TABlocks.Registry.AURORIANGRASSLIGHT.getBlock().getDefaultState();
		this.setSpawnWeight(20);
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return new TAWorldGenerator_Trees_WeepingWillow(false);
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
