package com.elseytd.theaurorian.World;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.World.Structures.TAWorldGenerator_DarkstoneDungeon;
import com.elseytd.theaurorian.World.Structures.TAWorldGenerator_Graveyard;
import com.elseytd.theaurorian.World.Structures.TAWorldGenerator_MoonTemple;
import com.elseytd.theaurorian.World.Structures.TAWorldGenerator_Ruins;
import com.elseytd.theaurorian.World.Structures.TAWorldGenerator_Runestone_Tower;
import com.elseytd.theaurorian.World.Structures.TAWorldGenerator_UmbraTower;
import com.fluke.worleycaves.world.WorleyCaveGenerator;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import net.minecraftforge.event.terraingen.TerrainGen;

public class TAChunkGenerator implements IChunkGenerator {

	private final World worldObj;
	private Random random;
	private Biome[] biomes;

	private MapGenBase caveGenerator = new WorleyCaveGenerator();
	private TATerrainGenerator terraingen = new TATerrainGenerator();

	private TAWorldGenerator_Runestone_Tower towergen = new TAWorldGenerator_Runestone_Tower();
	private TAWorldGenerator_Ruins ruingen = new TAWorldGenerator_Ruins();
	private TAWorldGenerator_MoonTemple templegen = new TAWorldGenerator_MoonTemple();
	private TAWorldGenerator_UmbraTower umbratowergen = new TAWorldGenerator_UmbraTower();
	private TAWorldGenerator_DarkstoneDungeon darkstonegen = new TAWorldGenerator_DarkstoneDungeon();
	private TAWorldGenerator_Graveyard graveyardgen = new TAWorldGenerator_Graveyard();

	public TAChunkGenerator(World worldObj) {
		this.worldObj = worldObj;
		long seed = worldObj.getSeed();
		this.random = new Random((seed + 516) * 314);
		this.terraingen.setup(worldObj, this.random);
		this.caveGenerator = TerrainGen.getModdedMapGen(this.caveGenerator, EventType.CAVE);
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		ChunkPrimer chunkprimer = new ChunkPrimer();
		this.biomes = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomes, x * 4 - 2, z * 4 - 2, 10, 10);
		this.terraingen.setBiomes(this.biomes);
		this.terraingen.generate(x, z, chunkprimer);

		this.biomes = this.worldObj.getBiomeProvider().getBiomes(this.biomes, x * 16, z * 16, 16, 16);
		this.terraingen.replaceBiomeBlocks(x, z, chunkprimer, this, this.biomes);

		this.caveGenerator.generate(this.worldObj, x, z, chunkprimer);
		Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);

		byte[] biomeArray = chunk.getBiomeArray();
		for (int i = 0; i < biomeArray.length; ++i) {
			biomeArray[i] = (byte) Biome.getIdForBiome(this.biomes[i]);
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public void populate(int x, int z) {
		int i = x * 16;
		int j = z * 16;
		BlockPos blockpos = new BlockPos(i, 0, j);

		//STRUCTURES
		if (TAWorldGenerator_Runestone_Tower.GENERATE_TOWERS) {
			this.towergen.generate(this.worldObj, this.random, blockpos);
		}

		if (TAWorldGenerator_Ruins.GENERATE_RUINS) {
			this.ruingen.generate(this.worldObj, this.random, blockpos);
		}

		if (TAWorldGenerator_MoonTemple.GENERATE_TEMPLES) {
			this.templegen.generate(this.worldObj, this.random, blockpos);
		}

		if (TAWorldGenerator_UmbraTower.GENERATE_TOWERS) {
			this.umbratowergen.generate(this.worldObj, this.random, blockpos);
		}

		if (TAWorldGenerator_Graveyard.GENERATE_GRAVEYARDS) {
			this.graveyardgen.generate(this.worldObj, this.random, blockpos);
		}

		this.darkstonegen.generate(this.worldObj, this.random, blockpos);

		//BIOMES
		Biome biome = this.worldObj.getBiome(blockpos.add(0, 0, 0));
		biome.decorate(this.worldObj, this.random, blockpos);

		//ENTITIES
		WorldEntitySpawner.performWorldGenSpawning(this.worldObj, biome, i + 8, j + 8, 16, 16, this.random);
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		Biome biome = this.worldObj.getBiome(pos);
		return biome.getSpawnableList(creatureType);
	}

	@Nullable
	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
		return null;
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {

	}
}
