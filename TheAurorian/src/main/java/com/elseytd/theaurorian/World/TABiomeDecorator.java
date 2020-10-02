package com.elseytd.theaurorian.World;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.World.Biomes.TABiome;
import com.elseytd.theaurorian.World.Feature.TAWorldGenerator_UnderGround;
import com.elseytd.theaurorian.World.Feature.TAWorldGenerator_UnderWater;
import com.elseytd.theaurorian.World.Feature.TAWorldGenerator_Urns;
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

public class TABiomeDecorator extends BiomeDecorator {

	@Override
	public void decorate(World worldIn, Random random, Biome biome, BlockPos pos) {
		if (this.decorating) {
			throw new RuntimeException("Already decorating");
		} else {
			this.chunkProviderSettings = ChunkGeneratorSettings.Factory.jsonToFactory(worldIn.getWorldInfo().getGeneratorOptions()).build();
			this.chunkPos = pos;

			this.generateStones(worldIn, random);
			this.generateOres(worldIn, random);
			this.genDecorations(biome, worldIn, random);
			this.decorating = false;
		}
	}

	@Override
	protected void generateOres(World worldIn, Random random) {
		this.oreQuickGen(worldIn, random, TABlocks.Registry.ORECERULEAN.getBlock().getDefaultState(), TAConfig.Config_CeruleanOre_Size, TAConfig.Config_CeruleanOre_Count, TAConfig.Config_CeruleanOre_HeightMin, TAConfig.Config_CeruleanOre_HeightMax);
		this.oreQuickGen(worldIn, random, TABlocks.Registry.OREMOONSTONE.getBlock().getDefaultState(), TAConfig.Config_MoonstoneOre_Size, TAConfig.Config_MoonstoneOre_Count, TAConfig.Config_MoonstoneOre_HeightMin, TAConfig.Config_MoonstoneOre_HeightMax);
		this.oreQuickGen(worldIn, random, TABlocks.Registry.OREAURORIANCOAL.getBlock().getDefaultState(), TAConfig.Config_AurorianCoalOre_Size, TAConfig.Config_AurorianCoalOre_Count, TAConfig.Config_AurorianCoalOre_HeightMin, TAConfig.Config_AurorianCoalOre_HeightMax);
		this.oreQuickGen(worldIn, random, TABlocks.Registry.OREGEODE.getBlock().getDefaultState(), TAConfig.Config_GeodeOre_Size, TAConfig.Config_GeodeOre_Count, TAConfig.Config_GeodeOre_HeightMin, TAConfig.Config_GeodeOre_HeightMax);
	}

	protected void generateStones(World worldIn, Random random) {
		this.oreQuickGen(worldIn, random, TABlocks.Registry.PERIDOTITE.getBlock().getDefaultState(), TAConfig.Config_Peridotite_Size, TAConfig.Config_Peridotite_Count, TAConfig.Config_Peridotite_HeightMin, TAConfig.Config_Peridotite_HeightMax);
		this.oreQuickGen(worldIn, random, TABlocks.Registry.AURORIANDIRT.getBlock().getDefaultState(), TAConfig.Config_Dirt_Size, TAConfig.Config_Dirt_Count, TAConfig.Config_Dirt_HeightMin, TAConfig.Config_Dirt_HeightMax);
	}

	private void oreQuickGen(World worldIn, Random random, IBlockState oreblock, int size, int count, int heightmin, int heightmax) {
		WorldGenerator oregenerator = new WorldGenMinable(oreblock, size, new AurorianStonesPredicate());
		if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, random, oregenerator, this.chunkPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
			this.genStandardOre1(worldIn, random, count, oregenerator, heightmin, heightmax);
		}
	}

	@Override
	protected void genDecorations(Biome biomeIn, World worldIn, Random random) {
		net.minecraft.util.math.ChunkPos forgeChunkPos = new net.minecraft.util.math.ChunkPos(this.chunkPos);
		net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.terraingen.DecorateBiomeEvent.Pre(worldIn, random, forgeChunkPos));
		this.generateOres(worldIn, random);

		TABiome modbiome = (TABiome) biomeIn;

		//==Under Water Blocks==
		if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CLAY)) {
			for (int i1 = 0; i1 < 4; ++i1) {
				int l1 = random.nextInt(16) + 8;
				int i6 = random.nextInt(16) + 8;
				TAWorldGenerator_UnderWater gen = new TAWorldGenerator_UnderWater(5, TABlocks.Registry.AURORIANDIRT.getBlock());
				gen.generate(worldIn, random, worldIn.getTopSolidOrLiquidBlock(this.chunkPos.add(l1, 0, i6)));
			}
		}
		if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CLAY)) {
			for (int i1 = 0; i1 < 4; ++i1) {
				int l1 = random.nextInt(16) + 8;
				int i6 = random.nextInt(16) + 8;
				TAWorldGenerator_UnderWater gen = new TAWorldGenerator_UnderWater(5, TABlocks.Registry.AURORIANDIRT.getBlock());
				gen.generate(worldIn, random, worldIn.getTopSolidOrLiquidBlock(this.chunkPos.add(l1, 0, i6)));
			}
		}

		//===MUSHROOM CAVES===
		if (TAConfig.Config_GenerateMushroomCaves) {
			TAWorldGenerator_UnderGround fd = new TAWorldGenerator_UnderGround();
			fd.generate(worldIn, random, this.chunkPos);
		}

		//=========URNS========
		if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CUSTOM)) {
			if (TAConfig.Config_GenerateUrns) {
				int x = random.nextInt(16) + 8;
				int ymin = random.nextInt(40);
				int ymax = ymin + random.nextInt(40);
				int z = random.nextInt(16) + 8;

				for (int y = ymin; y <= ymax; y++) {
					TAWorldGenerator_Urns gen = new TAWorldGenerator_Urns();
					if (gen.generate(worldIn, random, this.chunkPos.add(x, y, z))) {
						break;
					}
				}
			}
		}

		//=========TREES=======
		if (biomeIn.getRandomTreeFeature(random) != null) {
			int tp = 10;
			if (random.nextFloat() < this.extraTreeChance) {
				++tp;
			}
			if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE)) {
				for (int j2 = 0; j2 < tp; ++j2) {
					int x = random.nextInt(16) + 8;
					int z = random.nextInt(16) + 8;
					WorldGenAbstractTree worldgenabstracttree = biomeIn.getRandomTreeFeature(random);

					worldgenabstracttree.setDecorationDefaults();
					BlockPos blockpos = worldIn.getHeight(this.chunkPos.add(x, 0, z));
					if (worldgenabstracttree.generate(worldIn, random, blockpos)) {
						worldgenabstracttree.generateSaplings(worldIn, random, blockpos);
					}
				}
			}
		}

		//=========TALLGRASS=======
		if (biomeIn.getRandomWorldGenForGrass(random) != null) {
			int gp = 6;
			if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS)) {
				for (int i3 = 0; i3 < gp; ++i3) {
					int x = random.nextInt(16) + 8;
					int z = random.nextInt(16) + 8;
					int k14 = worldIn.getHeight(this.chunkPos.add(x, 0, z)).getY() * 2;
					if (k14 > 0) {
						int l17 = random.nextInt(k14);
						biomeIn.getRandomWorldGenForGrass(random).generate(worldIn, random, this.chunkPos.add(x, l17, z));
					}
				}
			}
		}

		if (modbiome != null) {
			//=========LAVENDER=======
			if (modbiome.getRandomWorldGenForLavender(random) != null) {
				int lp = 8;
				if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS)) {
					for (int i3 = 0; i3 < lp; ++i3) {
						int x = random.nextInt(16) + 8;
						int z = random.nextInt(16) + 8;
						int k14 = worldIn.getHeight(this.chunkPos.add(x, 0, z)).getY() * 2;
						if (k14 > 0) {
							int l17 = random.nextInt(k14);
							modbiome.getRandomWorldGenForLavender(random).generate(worldIn, random, this.chunkPos.add(x, l17, z));
						}
					}
				}
			}

			//=========SILKBERRY=======
			if (modbiome.getRandomWorldGenForSilkberry(random) != null) {
				int sp = 2;
				if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS)) {
					for (int i3 = 0; i3 < sp; ++i3) {
						int x = random.nextInt(16) + 8;
						int z = random.nextInt(16) + 8;
						int k14 = worldIn.getHeight(this.chunkPos.add(x, 0, z)).getY() * 2;
						if (k14 > 0) {
							int l17 = random.nextInt(k14);
							modbiome.getRandomWorldGenForSilkberry(random).generate(worldIn, random, this.chunkPos.add(x, l17, z));
						}
					}
				}
			}

			//=========PETUNIA=======
			if (modbiome.getRandomWorldGenForPetunia(random) != null) {
				int sp = 2;
				if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS)) {
					for (int i3 = 0; i3 < sp; ++i3) {
						int x = random.nextInt(16) + 8;
						int z = random.nextInt(16) + 8;
						int k14 = worldIn.getHeight(this.chunkPos.add(x, 0, z)).getY() * 2;
						if (k14 > 0) {
							int l17 = random.nextInt(k14);
							modbiome.getRandomWorldGenForPetunia(random).generate(worldIn, random, this.chunkPos.add(x, l17, z));
						}
					}
				}
			}
		}
		net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.terraingen.DecorateBiomeEvent.Post(worldIn, random, forgeChunkPos));
	}

	public static class AurorianStonesPredicate implements Predicate<IBlockState> {
		@Override
		public boolean apply(IBlockState blkin) {
			if (blkin != null && (blkin.getBlock() instanceof IAurorianStoneType)) {
				return true;
			} else {
				return false;
			}
		}

		public interface IAurorianStoneType {
			//theres probably a better way to do this
		}
	}

}
