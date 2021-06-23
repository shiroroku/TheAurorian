package com.elseytd.theaurorian.World.Biomes;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.World.Feature.TAWorldGenerator_Trees_Silentwood;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class TABiomeAurorianOvergrowth extends TABiome {

	public static final String BIOMENAME = "aurorianovergrowth";
	public static final String BIOMEDISPLAYNAME = "Aurorian Overgrowth";

	public TABiomeAurorianOvergrowth() {
		super(new BiomeProperties(BIOMEDISPLAYNAME).setBaseHeight(0.95F).setHeightVariation(0.02F));
		this.topBlock = TABlocks.Registry.AURORIANGRASS.getBlock().getDefaultState();
		this.fillerBlock = TABlocks.Registry.AURORIANSTONE.getBlock().getDefaultState();
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
		return new TAWorldGenerator_Trees_Silentwood(false, 30, 6, 6);
	}

}
