package com.shiroroku.theaurorian.World.Biomes;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.World.Feature.WeepingWillowTreeWorldGenerator;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WeepingWillowForestBiome extends AurorianBiome {

	public static final String BIOMENAME = "weepingwillowforest";
	public static final String BIOMEDISPLAYNAME = "Weeping Willow Forest";

	public WeepingWillowForestBiome() {
		super(new BiomeProperties(BIOMEDISPLAYNAME).setBaseHeight(0.6F).setHeightVariation(0.5F));
		this.topBlock = BlockRegistry.Registry.AURORIANGRASSLIGHT.getBlock().getDefaultState();
		this.setSpawnWeight(20);
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return new WeepingWillowTreeWorldGenerator(false);
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
