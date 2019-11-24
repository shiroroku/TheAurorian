package com.elseytd.theaurorian.World.Structures;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TAUtil;

import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class TAWorldGenerator_MoonTemple extends WorldGenerator {

	private static final ResourceLocation MOONTEMPLE_LOOTTABLE = new ResourceLocation(TAMod.MODID, "chests/moontemple");

	private static final ResourceLocation MOONTEMPLE_TERRAIN = new ResourceLocation(TAMod.MODID, "moontemple/moontemple_terrain");
	private static final ResourceLocation MOONTEMPLE_COURTYARD = new ResourceLocation(TAMod.MODID, "moontemple/moontemple_courtyard");
	private static final ResourceLocation MOONTEMPLE_CONNECTOR = new ResourceLocation(TAMod.MODID, "moontemple/moontemple_connector");
	private static final ResourceLocation MOONTEMPLE_TOWER = new ResourceLocation(TAMod.MODID, "moontemple/moontemple_tower");
	private static final ResourceLocation MOONTEMPLE_ROOM = new ResourceLocation(TAMod.MODID, "moontemple/moontemple_room");
	private static final ResourceLocation MOONTEMPLE_ISLAND = new ResourceLocation(TAMod.MODID, "moontemple/moontemple_island");
	private static final ResourceLocation MOONTEMPLE_PATH_TURN = new ResourceLocation(TAMod.MODID, "moontemple/moontemple_path_turn");
	private static final ResourceLocation MOONTEMPLE_PATH_STRAIGHT = new ResourceLocation(TAMod.MODID, "moontemple/moontemple_path_straight");

	public static int CHUNKS_BETWEEN_TEMPLES = TAConfig.Config_DungeonDensity * 2;
	public static boolean GENERATE_TEMPLES = TAConfig.Config_GenerateMoonTemple;
	public static boolean GENERATE_TEMPLES_PATH = TAConfig.Config_GenerateMoonTemplePath;

	//==================
	// TERRAIN 		   - The bottom part of structures with dirt that makes it look like a floating island.
	// COURTYARD	   - The walled platform in front of the entrance.
	// CONNECTOR       - The middle hallway that connects the whole temple.
	// ROOM            - The boss room of the temple.
	// ISLAND          - The floating pillar islands around the temple.
	// PATH            - The floating path that leads in a spiral down to the surface.
	//==================

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		Chunk c = worldIn.getChunkFromBlockCoords(position);

		if (generateTemple(worldIn, c.x, c.z, rand)) {
			TAUtil.populateChestsInChunk(c, rand, MOONTEMPLE_LOOTTABLE);
			//c.markDirty();
		}

		return true;

	}

	public static boolean isValidChunkForGen(int chunkX, int chunkZ, int offsetX, int offsetZ) {
		if ((chunkX + offsetX + (TAConfig.Config_DungeonDensity / 2)) % CHUNKS_BETWEEN_TEMPLES == 0 && (chunkZ + offsetZ + (TAConfig.Config_DungeonDensity / 2)) % CHUNKS_BETWEEN_TEMPLES == 0) {
			return true;
		}
		return false;
	}

	public boolean generateTemple(World world, int chunkX, int chunkZ, Random rand) {
		boolean gen = false;
		int x = chunkX * 16 + 8;
		int z = chunkZ * 16 + 8;
		int y = 200;

		final PlacementSettings settings = new PlacementSettings().setRotation(Rotation.NONE).setReplacedBlock(TABlocks.aurorianstone);
		final PlacementSettings settingsrotated = new PlacementSettings().setRotation(Rotation.CLOCKWISE_180).setReplacedBlock(TABlocks.aurorianstone);

		if (isValidChunkForGen(chunkX, chunkZ, 0, 0)) {
			final Template temple_connector = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_CONNECTOR);
			temple_connector.addBlocksToWorld(world, new BlockPos(x, y, z), settings);

			final Template temple_terrain = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_TERRAIN);
			temple_terrain.addBlocksToWorld(world, new BlockPos(x, y - 12, z), settings);
			gen = true;
		}

		if (isValidChunkForGen(chunkX, chunkZ, 1, 0)) {
			final Template temple_tower = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_TOWER);
			temple_tower.addBlocksToWorld(world, new BlockPos(x, y, z), settings);

			final Template temple_terrain = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_TERRAIN);
			temple_terrain.addBlocksToWorld(world, new BlockPos(x, y - 12, z), settings);
			gen = true;
		}

		if (isValidChunkForGen(chunkX, chunkZ, -1, 0)) {
			final Template temple_tower = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_TOWER);
			temple_tower.addBlocksToWorld(world, new BlockPos(x + 15, y, z + 15), settingsrotated);

			final Template temple_terrain = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_TERRAIN);
			temple_terrain.addBlocksToWorld(world, new BlockPos(x, y - 12, z), settings);
			gen = true;
		}

		if (isValidChunkForGen(chunkX, chunkZ, 0, 1)) {
			final Template temple_courtyard = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_COURTYARD);
			temple_courtyard.addBlocksToWorld(world, new BlockPos(x, y, z), settings);

			final Template temple_terrain = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_TERRAIN);
			temple_terrain.addBlocksToWorld(world, new BlockPos(x, y - 12, z), settings);
			gen = true;
		}

		if (isValidChunkForGen(chunkX, chunkZ, 0, -1)) {
			final Template temple_room = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_ROOM);
			temple_room.addBlocksToWorld(world, new BlockPos(x, y, z), settings);

			final Template temple_terrain = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_TERRAIN);
			temple_terrain.addBlocksToWorld(world, new BlockPos(x, y - 12, z), settings);
			gen = true;
		}

		//ISLANDS
		if (isValidChunkForGen(chunkX, chunkZ, -1, -3)) {
			genIsland(world, x, y, z, rand);
			gen = true;
		}
		if (isValidChunkForGen(chunkX, chunkZ, 1, -3)) {
			genIsland(world, x, y, z, rand);
			gen = true;
		}
		if (isValidChunkForGen(chunkX, chunkZ, -1, 3)) {
			genIsland(world, x, y, z, rand);
			gen = true;
		}
		if (isValidChunkForGen(chunkX, chunkZ, 1, 3)) {
			genIsland(world, x, y, z, rand);
			gen = true;
		}
		if (isValidChunkForGen(chunkX, chunkZ, -3, -1)) {
			genIsland(world, x, y, z, rand);
			gen = true;
		}
		if (isValidChunkForGen(chunkX, chunkZ, -3, 1)) {
			genIsland(world, x, y, z, rand);
			gen = true;
		}
		if (isValidChunkForGen(chunkX, chunkZ, 3, -1)) {
			genIsland(world, x, y, z, rand);
			gen = true;
		}
		if (isValidChunkForGen(chunkX, chunkZ, 3, 1)) {
			genIsland(world, x, y, z, rand);
			gen = true;
		}

		//PATH
		if (GENERATE_TEMPLES_PATH) {
			genSpiralPath(world, chunkX, chunkZ, x, y, z);
		}
		return gen;
	}

	public void genIsland(World world, int x, int y, int z, Random rand) {
		final PlacementSettings settings = new PlacementSettings().setRotation(Rotation.NONE).setReplacedBlock(Blocks.AIR);
		final Template temple_island = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), MOONTEMPLE_ISLAND);
		temple_island.addBlocksToWorld(world, new BlockPos(x, y - 27 + rand.nextInt(10), z), settings);
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
