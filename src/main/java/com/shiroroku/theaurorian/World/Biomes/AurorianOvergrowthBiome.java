package com.shiroroku.theaurorian.World.Biomes;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.World.Feature.SilentwoodTreeWorldGenerator;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class AurorianOvergrowthBiome extends AurorianBiome {

	public static final String BIOMENAME = "aurorianovergrowth";
	public static final String BIOMEDISPLAYNAME = "Aurorian Overgrowth";

	public AurorianOvergrowthBiome() {
		super(new BiomeProperties(BIOMEDISPLAYNAME).setBaseHeight(0.95F).setHeightVariation(0.02F));
		this.topBlock = BlockRegistry.Registry.AURORIANGRASS.getBlock().getDefaultState();
		this.fillerBlock = BlockRegistry.Registry.AURORIANSTONE.getBlock().getDefaultState();
		this.setSpawnWeight(30);
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
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return new SilentwoodTreeWorldGenerator(false, 30, 6, 6);
	}

}
