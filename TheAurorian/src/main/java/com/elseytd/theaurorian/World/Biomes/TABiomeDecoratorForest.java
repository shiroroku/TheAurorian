package com.elseytd.theaurorian.World.Biomes;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAConfig;
import com.google.common.base.Predicate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TABiomeDecoratorForest extends BiomeDecorator {

	public WorldGenerator ceruleanGen;
	public WorldGenerator moonstoneGen;
	public WorldGenerator auroriancoalGen;
	public WorldGenerator geodeGen;
	//public TAWorldGenerator_Sand sandgen = new TAWorldGenerator_Sand();

	@Override
	public void decorate(World worldIn, Random random, Biome biome, BlockPos pos) {
		if (this.decorating) {
			throw new RuntimeException("Already decorating");
		} else {
			this.chunkProviderSettings = ChunkGeneratorSettings.Factory.jsonToFactory(worldIn.getWorldInfo().getGeneratorOptions()).build();
			this.chunkPos = pos;

			this.ceruleanGen = new WorldGenMinable(TABlocks.ceruleanore.getDefaultState(), TAConfig.Config_CeruleanOre_Size, new AurorianStonePredicate());
			this.moonstoneGen = new WorldGenMinable(TABlocks.moonstoneore.getDefaultState(), TAConfig.Config_MoonstoneOre_Size, new AurorianStonePredicate());
			this.auroriancoalGen = new WorldGenMinable(TABlocks.auroriancoalore.getDefaultState(), TAConfig.Config_AurorianCoalOre_Size, new AurorianStonePredicate());
			this.geodeGen = new WorldGenMinable(TABlocks.geodeore.getDefaultState(), TAConfig.Config_GeodeOre_Size, new AurorianStonePredicate());

			this.generateOres(worldIn, random);
			this.genDecorations(biome, worldIn, random);
			this.decorating = false;
		}
	}

	@Override
	protected void generateOres(World worldIn, Random random) {
		if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, random, ceruleanGen, chunkPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.CUSTOM))
			this.genStandardOre1(worldIn, random, TAConfig.Config_CeruleanOre_Count, this.ceruleanGen, TAConfig.Config_CeruleanOre_HeightMin, TAConfig.Config_CeruleanOre_HeightMax);
		if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, random, moonstoneGen, chunkPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.CUSTOM))
			this.genStandardOre1(worldIn, random, TAConfig.Config_MoonstoneOre_Count, this.moonstoneGen, TAConfig.Config_MoonstoneOre_HeightMin, TAConfig.Config_MoonstoneOre_HeightMax);
		if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, random, moonstoneGen, chunkPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.CUSTOM))
			this.genStandardOre1(worldIn, random, TAConfig.Config_AurorianCoalOre_Count, this.auroriancoalGen, TAConfig.Config_AurorianCoalOre_HeightMin, TAConfig.Config_AurorianCoalOre_HeightMax);
		if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, random, geodeGen, chunkPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.CUSTOM))
			this.genStandardOre1(worldIn, random, TAConfig.Config_GeodeOre_Count, this.geodeGen, TAConfig.Config_GeodeOre_HeightMin, TAConfig.Config_GeodeOre_HeightMax);
	}

	@Override
	protected void genDecorations(Biome biomeIn, World worldIn, Random random) {
		net.minecraft.util.math.ChunkPos forgeChunkPos = new net.minecraft.util.math.ChunkPos(chunkPos);
		net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.terraingen.DecorateBiomeEvent.Pre(worldIn, random, forgeChunkPos));
		this.generateOres(worldIn, random);

		//sandgen.generate(worldIn, random, worldIn.getHeight(this.chunkPos));

		//=========TREES=======
		//trees per chunk
		int tp = 10;
		if (random.nextFloat() < this.extraTreeChance) {
			++tp;
		}
		if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE))
			for (int j2 = 0; j2 < tp; ++j2) {
				int k6 = random.nextInt(16) + 8;
				int l = random.nextInt(16) + 8;
				WorldGenAbstractTree worldgenabstracttree = biomeIn.getRandomTreeFeature(random);
				if (worldgenabstracttree != null) {
					worldgenabstracttree.setDecorationDefaults();
					BlockPos blockpos = worldIn.getHeight(this.chunkPos.add(k6, 0, l));
					if (blockpos.getY() >= 80) {
						if (worldgenabstracttree.generate(worldIn, random, blockpos)) {
							worldgenabstracttree.generateSaplings(worldIn, random, blockpos);
						}
					}
				}
			}

		//=========TALLGRASS=======
		//grass per chunk
		int gp = 6;
		if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
			for (int i3 = 0; i3 < gp; ++i3) {
				int j7 = random.nextInt(16) + 8;
				int i11 = random.nextInt(16) + 8;
				int k14 = worldIn.getHeight(this.chunkPos.add(j7, 0, i11)).getY() * 2;
				if (k14 > 0) {
					int l17 = random.nextInt(k14);
					biomeIn.getRandomWorldGenForGrass(random).generate(worldIn, random, this.chunkPos.add(j7, l17, i11));
				}
			}

		//=========LAVENDER=======
		int lp = 8;
		if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
			for (int i3 = 0; i3 < lp; ++i3) {
				int j7 = random.nextInt(16) + 8;
				int i11 = random.nextInt(16) + 8;
				int k14 = worldIn.getHeight(this.chunkPos.add(j7, 0, i11)).getY() * 2;
				if (k14 > 0) {
					int l17 = random.nextInt(k14);
					if (biomeIn instanceof TABiomeAurorianForest) {
						TABiomeAurorianForest bio = (TABiomeAurorianForest) biomeIn;
						bio.getRandomWorldGenForLavender(random).generate(worldIn, random, this.chunkPos.add(j7, l17, i11));
					}
				}
			}

		//=========SILKBERRY=======
		int sp = 2;
		if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS))
			for (int i3 = 0; i3 < sp; ++i3) {
				int j7 = random.nextInt(16) + 8;
				int i11 = random.nextInt(16) + 8;
				int k14 = worldIn.getHeight(this.chunkPos.add(j7, 0, i11)).getY() * 2;
				if (k14 > 0) {
					int l17 = random.nextInt(k14);
					if (biomeIn instanceof TABiomeAurorianForest) {
						TABiomeAurorianForest bio = (TABiomeAurorianForest) biomeIn;
						bio.getRandomWorldGenForSilkberry(random).generate(worldIn, random, this.chunkPos.add(j7, l17, i11));
					}
				}
			}

		net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.terraingen.DecorateBiomeEvent.Post(worldIn, random, forgeChunkPos));
	}

	static class AurorianStonePredicate implements Predicate<IBlockState> {
		private AurorianStonePredicate() {
		}

		public boolean apply(IBlockState blkin) {
			if (blkin != null && blkin.getBlock() == TABlocks.aurorianstone) {
				return true;
			} else {
				return false;
			}
		}
	}
}
