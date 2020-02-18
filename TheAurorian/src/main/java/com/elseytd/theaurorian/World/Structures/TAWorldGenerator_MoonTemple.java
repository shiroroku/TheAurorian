package com.elseytd.theaurorian.World.Structures;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.Misc.GenerationHelper;

import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class TAWorldGenerator_MoonTemple extends WorldGenerator implements GenerationHelper.IChunkSpecific{

	private static final ResourceLocation MOONTEMPLE_LOOTTABLE_LOW = new ResourceLocation(TAMod.MODID, "chests/moontemplelow");
	private static final ResourceLocation MOONTEMPLE_LOOTTABLE_MED = new ResourceLocation(TAMod.MODID, "chests/moontemplemed");
	
	private static final ResourceLocation MOONTEMPLE_TERRAIN = new ResourceLocation(TAMod.MODID, "moontemple/moontemple_terrain");
	private static final ResourceLocation MOONTEMPLE_COURTYARD = new ResourceLocation(TAMod.MODID, "moontemple/moontemplev2_courtyard");
	private static final ResourceLocation MOONTEMPLE_COURTYARD_LEFT = new ResourceLocation(TAMod.MODID, "moontemple/moontemplev2_courtyardl");
	private static final ResourceLocation MOONTEMPLE_COURTYARD_RIGHT = new ResourceLocation(TAMod.MODID, "moontemple/moontemplev2_courtyardr");
	private static final ResourceLocation MOONTEMPLE_CONNECTOR = new ResourceLocation(TAMod.MODID, "moontemple/moontemplev2_center");
	private static final ResourceLocation MOONTEMPLE_TOWER_LEFT = new ResourceLocation(TAMod.MODID, "moontemple/moontemplev2_left");
	private static final ResourceLocation MOONTEMPLE_TOWER_RIGHT = new ResourceLocation(TAMod.MODID, "moontemple/moontemplev2_right");
	private static final ResourceLocation MOONTEMPLE_ROOM = new ResourceLocation(TAMod.MODID, "moontemple/moontemplev2_room");
	private static final ResourceLocation MOONTEMPLE_ISLAND = new ResourceLocation(TAMod.MODID, "moontemple/moontemple_island");
	private static final ResourceLocation MOONTEMPLE_PATH_TURN = new ResourceLocation(TAMod.MODID, "moontemple/moontemple_path_turn");
	private static final ResourceLocation MOONTEMPLE_PATH_STRAIGHT = new ResourceLocation(TAMod.MODID, "moontemple/moontemple_path_straight");

	public static int CHUNKS_BETWEEN_TEMPLES = TAConfig.Config_DungeonDensity * 2;
	public static boolean GENERATE_TEMPLES = TAConfig.Config_GenerateMoonTemple;
	public static boolean GENERATE_TEMPLES_PATH = TAConfig.Config_GenerateMoonTemplePath;

	//==================
	// TERRAIN 		   - The bottom part of structures with dirt that makes it look like a floating island.
	// COURTYARD	   - Platform in front of the entrance.
	// COURTYARD LEFT  - Border blocks for front of left tower.
	// COURTYARD RIGHT - Border blocks for front of right tower.
	// CONNECTOR       - The middle of the temple.
	// TOWER LEFT 	   - Left side of the temple.
	// TOWER RIGHT 	   - Right side of the temple.
	// ROOM            - The back parkour room.
	// ISLAND          - The floating pillar islands around the temple.
	// PATH            - The floating path that leads in a spiral down to the surface.
	//==================

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		Chunk c = worldIn.getChunkFromBlockCoords(position);
		int height = 200;
		
		if(generateTemple(worldIn, c, height, rand)) {
			//Populate loot in a 4x4 chunk since the structure is offset
			Chunk c1 = worldIn.getChunkFromChunkCoords(c.x + 1, c.z + 1);
			Chunk c2 = worldIn.getChunkFromChunkCoords(c.x + 1, c.z);
			Chunk c3 = worldIn.getChunkFromChunkCoords(c.x, c.z + 1);
			
			populateChests(c, height, height + 12, MOONTEMPLE_LOOTTABLE_LOW, rand);
			populateChests(c, height + 12, height + 30, MOONTEMPLE_LOOTTABLE_MED, rand);
			populateChests(c1, height, height + 12, MOONTEMPLE_LOOTTABLE_LOW, rand);
			populateChests(c1, height + 12, height + 30, MOONTEMPLE_LOOTTABLE_MED, rand);
			populateChests(c2, height, height + 12, MOONTEMPLE_LOOTTABLE_LOW, rand);
			populateChests(c2, height + 12, height + 30, MOONTEMPLE_LOOTTABLE_MED, rand);
			populateChests(c3, height, height + 12, MOONTEMPLE_LOOTTABLE_LOW, rand);
			populateChests(c3, height + 12, height + 30, MOONTEMPLE_LOOTTABLE_MED, rand);
		}
		
		return true;
	}

	private void populateChests(Chunk c, int heightmin, int heightmax, ResourceLocation loot, Random r) {
		for (int y = heightmin; y <= heightmax; y++) {
			GenerationHelper.populateChestsInChunkAtHeight(c, y, r, loot, false);
		}
	}
	
	@Override
	public boolean isValidChunkForGen(int chunkX, int chunkZ, int offsetX, int offsetZ) {
		if ((chunkX + offsetX + (TAConfig.Config_DungeonDensity / 2)) % CHUNKS_BETWEEN_TEMPLES == 0 && (chunkZ + offsetZ + (TAConfig.Config_DungeonDensity / 2)) % CHUNKS_BETWEEN_TEMPLES == 0) {
			return true;
		}
		return false;
	}

	public boolean generateTemple(World world, Chunk c, int height, Random rand) {
		boolean populateChests = false;
		int chunkX = c.x;
		int chunkZ = c.z;
		int x = chunkX * 16 + 8;
		int z = chunkZ * 16 + 8;
		int y = height;

		final PlacementSettings settings = new PlacementSettings().setRotation(Rotation.NONE).setReplacedBlock(TABlocks.aurorianstone);

		if (isValidChunkForGen(chunkX, chunkZ, 0, 0)) {
			final Template temple_connector = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_CONNECTOR);
			temple_connector.addBlocksToWorld(world, new BlockPos(x, y, z), settings);

			final Template temple_terrain = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_TERRAIN);
			temple_terrain.addBlocksToWorld(world, new BlockPos(x, y - 12, z), settings);
			populateChests = true;
		}

		if (isValidChunkForGen(chunkX, chunkZ, -1, 0)) {
			final Template temple_tower = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_TOWER_LEFT);
			temple_tower.addBlocksToWorld(world, new BlockPos(x, y, z), settings);

			final Template temple_terrain = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_TERRAIN);
			temple_terrain.addBlocksToWorld(world, new BlockPos(x, y - 12, z), settings);
			populateChests = true;
		}

		if (isValidChunkForGen(chunkX, chunkZ, 1, 0)) {
			final Template temple_tower = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_TOWER_RIGHT);
			temple_tower.addBlocksToWorld(world, new BlockPos(x, y, z), settings);

			final Template temple_terrain = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_TERRAIN);
			temple_terrain.addBlocksToWorld(world, new BlockPos(x, y - 12, z), settings);
			populateChests = true;
		}

		if (isValidChunkForGen(chunkX, chunkZ, 0, 1)) {
			final Template temple_courtyard = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_COURTYARD);
			temple_courtyard.addBlocksToWorld(world, new BlockPos(x, y, z), settings);

			final Template temple_terrain = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_TERRAIN);
			temple_terrain.addBlocksToWorld(world, new BlockPos(x, y - 12, z), settings);
			populateChests = true;
		}

		if (isValidChunkForGen(chunkX, chunkZ, -1, 1)) {
			final Template temple_courtyard = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_COURTYARD_LEFT);
			temple_courtyard.addBlocksToWorld(world, new BlockPos(x, y, z), settings);
			populateChests = true;
		}

		if (isValidChunkForGen(chunkX, chunkZ, 1, 1)) {
			final Template temple_courtyard = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_COURTYARD_RIGHT);
			temple_courtyard.addBlocksToWorld(world, new BlockPos(x, y, z), settings);
			populateChests = true;
		}

		if (isValidChunkForGen(chunkX, chunkZ, 0, -1)) {
			final Template temple_room = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_ROOM);
			temple_room.addBlocksToWorld(world, new BlockPos(x, y, z), settings);

			final Template temple_terrain = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_TERRAIN);
			temple_terrain.addBlocksToWorld(world, new BlockPos(x, y - 12, z), settings);
			populateChests = true;
		}
		
		//ISLANDS
		if (isValidChunkForGen(chunkX, chunkZ, -1, -3)) {
			genIsland(world, x, y, z, rand);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 1, -3)) {
			genIsland(world, x, y, z, rand);
		}
		if (isValidChunkForGen(chunkX, chunkZ, -1, 3)) {
			genIsland(world, x, y, z, rand);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 1, 3)) {
			genIsland(world, x, y, z, rand);
		}
		if (isValidChunkForGen(chunkX, chunkZ, -3, -1)) {
			genIsland(world, x, y, z, rand);
		}
		if (isValidChunkForGen(chunkX, chunkZ, -3, 1)) {
			genIsland(world, x, y, z, rand);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 3, -1)) {
			genIsland(world, x, y, z, rand);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 3, 1)) {
			genIsland(world, x, y, z, rand);
		}

		//PATH
		if (GENERATE_TEMPLES_PATH) {
			genSpiralPath(world, chunkX, chunkZ, x, y, z);
		}
		
		return populateChests;
	}

	public void genIsland(World world, int x, int y, int z, Random rand) {
		final PlacementSettings settings = new PlacementSettings().setRotation(Rotation.NONE).setReplacedBlock(Blocks.AIR);
		final Template temple_island = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_ISLAND);
		temple_island.addBlocksToWorld(world, new BlockPos(x, y - 27 + rand.nextInt(10), z), settings, 3);
	}

	public void genSpiralPath(World world, int chunkX, int chunkZ, int x, int y, int z) {
		final PlacementSettings settings = new PlacementSettings().setRotation(Rotation.NONE).setReplacedBlock(Blocks.AIR);
		final PlacementSettings settingsrotated90 = new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90).setReplacedBlock(Blocks.AIR);
		final PlacementSettings settingsrotated90c = new PlacementSettings().setRotation(Rotation.CLOCKWISE_90).setReplacedBlock(Blocks.AIR);
		final PlacementSettings settingsrotated180 = new PlacementSettings().setRotation(Rotation.CLOCKWISE_180).setReplacedBlock(Blocks.AIR);
		int height = y - 1;
		int yoffset = 7;

		if (isValidChunkForGen(chunkX, chunkZ, 0, 2)) {
			final Template path_corner = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_PATH_TURN);
			path_corner.addBlocksToWorld(world, new BlockPos(x, height - yoffset, z), settings);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 1, 2)) {
			final Template path_straight = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_PATH_STRAIGHT);
			path_straight.addBlocksToWorld(world, new BlockPos(x, height - yoffset * 2, z), settings);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 2, 2)) {
			final Template path_corner = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_PATH_TURN);
			path_corner.addBlocksToWorld(world, new BlockPos(x, height - yoffset * 3, z + 15), settingsrotated90);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 2, 1)) {
			final Template path_straight = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_PATH_STRAIGHT);
			path_straight.addBlocksToWorld(world, new BlockPos(x, height - yoffset * 4, z + 15), settingsrotated90);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 2, 0)) {
			final Template path_straight = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_PATH_STRAIGHT);
			path_straight.addBlocksToWorld(world, new BlockPos(x, height - yoffset * 5, z + 15), settingsrotated90);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 2, -1)) {
			final Template path_straight = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_PATH_STRAIGHT);
			path_straight.addBlocksToWorld(world, new BlockPos(x, height - yoffset * 6, z + 15), settingsrotated90);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 2, -2)) {
			final Template path_corner = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_PATH_TURN);
			path_corner.addBlocksToWorld(world, new BlockPos(x + 15, height - yoffset * 7, z + 15), settingsrotated180);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 1, -2)) {
			final Template path_straight = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_PATH_STRAIGHT);
			path_straight.addBlocksToWorld(world, new BlockPos(x + 15, height - yoffset * 8, z + 15), settingsrotated180);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 0, -2)) {
			final Template path_straight = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_PATH_STRAIGHT);
			path_straight.addBlocksToWorld(world, new BlockPos(x + 15, height - yoffset * 9, z + 15), settingsrotated180);
		}
		if (isValidChunkForGen(chunkX, chunkZ, -1, -2)) {
			final Template path_straight = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_PATH_STRAIGHT);
			path_straight.addBlocksToWorld(world, new BlockPos(x + 15, height - yoffset * 10, z + 15), settingsrotated180);
		}
		if (isValidChunkForGen(chunkX, chunkZ, -2, -2)) {
			final Template path_corner = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_PATH_TURN);
			path_corner.addBlocksToWorld(world, new BlockPos(x + 15, height - yoffset * 11, z), settingsrotated90c);
		}
		if (isValidChunkForGen(chunkX, chunkZ, -2, -1)) {
			final Template path_straight = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_PATH_STRAIGHT);
			path_straight.addBlocksToWorld(world, new BlockPos(x + 15, height - yoffset * 12, z), settingsrotated90c);
		}
		if (isValidChunkForGen(chunkX, chunkZ, -2, 0)) {
			final Template path_straight = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_PATH_STRAIGHT);
			path_straight.addBlocksToWorld(world, new BlockPos(x + 15, height - yoffset * 13, z), settingsrotated90c);
		}
		if (isValidChunkForGen(chunkX, chunkZ, -2, 1)) {
			final Template path_straight = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_PATH_STRAIGHT);
			path_straight.addBlocksToWorld(world, new BlockPos(x + 15, height - yoffset * 14, z), settingsrotated90c);
		}
		if (isValidChunkForGen(chunkX, chunkZ, -2, 2)) {
			final Template path_corner = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_PATH_TURN);
			path_corner.addBlocksToWorld(world, new BlockPos(x, height - yoffset * 15, z), settings);
		}
		if (isValidChunkForGen(chunkX, chunkZ, -1, 2)) {
			final Template path_straight = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_PATH_STRAIGHT);
			path_straight.addBlocksToWorld(world, new BlockPos(x, height - yoffset * 16, z), settings);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 0, 2)) {
			final Template path_straight = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_PATH_STRAIGHT);
			path_straight.addBlocksToWorld(world, new BlockPos(x, height - yoffset * 17, z), settings);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 1, 2)) {
			final Template path_straight = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_PATH_STRAIGHT);
			path_straight.addBlocksToWorld(world, new BlockPos(x, height - yoffset * 18, z), settings);
		}
	}
}
