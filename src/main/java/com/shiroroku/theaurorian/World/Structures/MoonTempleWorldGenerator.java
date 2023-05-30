package com.shiroroku.theaurorian.World.Structures;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.AurorianMod;
import com.shiroroku.theaurorian.Util.GenerationHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

import java.util.Random;

public class MoonTempleWorldGenerator extends WorldGenerator implements GenerationHelper.IChunkSpecific {

	private static final ResourceLocation MOONTEMPLE_LOOTTABLE_LOW = new ResourceLocation(AurorianMod.MODID, "chests/moontemplelow");
	private static final ResourceLocation MOONTEMPLE_LOOTTABLE_MED = new ResourceLocation(AurorianMod.MODID, "chests/moontemplemed");

	private static final ResourceLocation MOONTEMPLE_TERRAIN = new ResourceLocation(AurorianMod.MODID, "moontemple/moontemple_terrain");
	private static final ResourceLocation MOONTEMPLE_COURTYARD = new ResourceLocation(AurorianMod.MODID, "moontemple/moontemplev2_courtyard");
	private static final ResourceLocation MOONTEMPLE_COURTYARD_LEFT = new ResourceLocation(AurorianMod.MODID, "moontemple/moontemplev2_courtyardl");
	private static final ResourceLocation MOONTEMPLE_COURTYARD_RIGHT = new ResourceLocation(AurorianMod.MODID, "moontemple/moontemplev2_courtyardr");
	private static final ResourceLocation MOONTEMPLE_CENTER = new ResourceLocation(AurorianMod.MODID, "moontemple/moontemplev2_center");
	private static final ResourceLocation MOONTEMPLE_TOWER_LEFT = new ResourceLocation(AurorianMod.MODID, "moontemple/moontemplev2_left");
	private static final ResourceLocation MOONTEMPLE_TOWER_RIGHT = new ResourceLocation(AurorianMod.MODID, "moontemple/moontemplev2_right");
	private static final ResourceLocation MOONTEMPLE_ROOM = new ResourceLocation(AurorianMod.MODID, "moontemple/moontemplev2_room");
	private static final ResourceLocation MOONTEMPLE_ISLAND = new ResourceLocation(AurorianMod.MODID, "moontemple/moontemple_island");
	private static final ResourceLocation MOONTEMPLE_PATH_TURN = new ResourceLocation(AurorianMod.MODID, "moontemple/moontemple_path_turn");
	private static final ResourceLocation MOONTEMPLE_PATH_STRAIGHT = new ResourceLocation(AurorianMod.MODID, "moontemple/moontemple_path_straight");

	public static int CHUNKS_BETWEEN_TEMPLES = AurorianConfig.Config_DungeonDensity * 4;
	public static boolean GENERATE_TEMPLES = AurorianConfig.Config_GenerateMoonTemple;
	public static boolean GENERATE_TEMPLES_PATH = AurorianConfig.Config_GenerateMoonTemplePath;

	//==================
	// TERRAIN 		   - The bottom part of structures with dirt that makes it look like a floating island.
	// COURTYARD	   - Platform in front of the entrance.
	// COURTYARD LEFT  - Border blocks for front of left tower.
	// COURTYARD RIGHT - Border blocks for front of right tower.
	// CENTER          - The middle of the temple.
	// TOWER LEFT 	   - Left side of the temple.
	// TOWER RIGHT 	   - Right side of the temple.
	// ROOM            - The back parkour room.
	// ISLAND          - The floating pillar islands around the temple.
	// PATH            - The floating path that leads in a spiral down to the surface.
	//==================

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		this.generateTemple(worldIn, worldIn.getChunkFromBlockCoords(position), 200);
		return true;
	}

	private void populateChests(World world, BlockPos pos, Template template, PlacementSettings settings) {
		GenerationHelper.populateChestsInTemplate(world, pos, template, settings, "chest_low", MOONTEMPLE_LOOTTABLE_LOW);
		GenerationHelper.populateChestsInTemplate(world, pos, template, settings, "chest_med", MOONTEMPLE_LOOTTABLE_MED);
	}

	@Override
	public boolean isValidChunkForGen(int chunkX, int chunkZ, int offsetX, int offsetZ) {
		int offs = AurorianConfig.Config_DungeonDensity + AurorianConfig.Config_DungeonDensity / 2;
		return (chunkX + offsetX + offs) % CHUNKS_BETWEEN_TEMPLES == 0 && (chunkZ + offsetZ + offs) % CHUNKS_BETWEEN_TEMPLES == 0;
	}

	private void generateTemple(World world, Chunk c, int height) {
		final int chunkX = c.x;
		final int chunkZ = c.z;
		final BlockPos position = new BlockPos(chunkX * 16 + 8, height, chunkZ * 16 + 8);
		final PlacementSettings settings = new PlacementSettings().setReplacedBlock(BlockRegistry.Registry.AURORIANSTONE.getBlock());

		if (this.isValidChunkForGen(chunkX, chunkZ, 0, 0)) {
			final Template temple_center = GenerationHelper.getTemplate(world, MOONTEMPLE_CENTER);
			temple_center.addBlocksToWorld(world, position, settings);
			this.populateChests(world, position, temple_center, settings);

			GenerationHelper.getTemplate(world, MOONTEMPLE_TERRAIN).addBlocksToWorld(world, position.down(12), settings);
		}

		if (this.isValidChunkForGen(chunkX, chunkZ, -1, 0)) {
			final Template temple_tower = GenerationHelper.getTemplate(world, MOONTEMPLE_TOWER_LEFT);
			temple_tower.addBlocksToWorld(world, position, settings);
			this.populateChests(world, position, temple_tower, settings);

			GenerationHelper.getTemplate(world, MOONTEMPLE_TERRAIN).addBlocksToWorld(world, position.down(12), settings);
		}

		if (this.isValidChunkForGen(chunkX, chunkZ, 1, 0)) {
			final Template temple_tower = GenerationHelper.getTemplate(world, MOONTEMPLE_TOWER_RIGHT);
			temple_tower.addBlocksToWorld(world, position, settings);
			this.populateChests(world, position, temple_tower, settings);

			GenerationHelper.getTemplate(world, MOONTEMPLE_TERRAIN).addBlocksToWorld(world, position.down(12), settings);
		}

		if (this.isValidChunkForGen(chunkX, chunkZ, 0, 1)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_COURTYARD).addBlocksToWorld(world, position, settings);
			GenerationHelper.getTemplate(world, MOONTEMPLE_TERRAIN).addBlocksToWorld(world, position.down(12), settings);
		}

		if (this.isValidChunkForGen(chunkX, chunkZ, -1, 1)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_COURTYARD_LEFT).addBlocksToWorld(world, position, settings);
		}

		if (this.isValidChunkForGen(chunkX, chunkZ, 1, 1)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_COURTYARD_RIGHT).addBlocksToWorld(world, position, settings);
		}

		if (this.isValidChunkForGen(chunkX, chunkZ, 0, -1)) {
			final Template temple_room = GenerationHelper.getTemplate(world, MOONTEMPLE_ROOM);
			temple_room.addBlocksToWorld(world, position, settings);
			this.populateChests(world, position, temple_room, settings);

			GenerationHelper.getTemplate(world, MOONTEMPLE_TERRAIN).addBlocksToWorld(world, position.down(12), settings);
		}

		//ISLANDS
		this.genIslands(world, chunkX, chunkZ, position, world.rand);

		//PATH
		if (GENERATE_TEMPLES_PATH) {
			this.genSpiralPath(world, chunkX, chunkZ, position.getX(), position.getY(), position.getZ());
		}

	}

	private void genIslands(World world, int chunkX, int chunkZ, BlockPos position, Random rand) {
		if (this.isValidChunkForGen(chunkX, chunkZ, -1, -3)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_ISLAND).addBlocksToWorld(world, position.down(27 + rand.nextInt(10)), new PlacementSettings().setReplacedBlock(Blocks.AIR), 3);
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, 1, -3)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_ISLAND).addBlocksToWorld(world, position.down(27 + rand.nextInt(10)), new PlacementSettings().setReplacedBlock(Blocks.AIR), 3);
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, -1, 3)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_ISLAND).addBlocksToWorld(world, position.down(27 + rand.nextInt(10)), new PlacementSettings().setReplacedBlock(Blocks.AIR), 3);
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, 1, 3)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_ISLAND).addBlocksToWorld(world, position.down(27 + rand.nextInt(10)), new PlacementSettings().setReplacedBlock(Blocks.AIR), 3);
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, -3, -1)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_ISLAND).addBlocksToWorld(world, position.down(27 + rand.nextInt(10)), new PlacementSettings().setReplacedBlock(Blocks.AIR), 3);
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, -3, 1)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_ISLAND).addBlocksToWorld(world, position.down(27 + rand.nextInt(10)), new PlacementSettings().setReplacedBlock(Blocks.AIR), 3);
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, 3, -1)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_ISLAND).addBlocksToWorld(world, position.down(27 + rand.nextInt(10)), new PlacementSettings().setReplacedBlock(Blocks.AIR), 3);
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, 3, 1)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_ISLAND).addBlocksToWorld(world, position.down(27 + rand.nextInt(10)), new PlacementSettings().setReplacedBlock(Blocks.AIR), 3);
		}
	}

	private void genSpiralPath(World world, int chunkX, int chunkZ, int x, int y, int z) {
		final int height = y - 1;
		final int yoffset = 7;

		if (this.isValidChunkForGen(chunkX, chunkZ, 0, 2)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_PATH_TURN).addBlocksToWorld(world, new BlockPos(x, height - yoffset, z), new PlacementSettings().setReplacedBlock(Blocks.AIR));
			GenerationHelper.getTemplate(world, MOONTEMPLE_PATH_STRAIGHT).addBlocksToWorld(world, new BlockPos(x, height - yoffset * 17, z), new PlacementSettings().setReplacedBlock(Blocks.AIR));
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, 1, 2)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_PATH_STRAIGHT).addBlocksToWorld(world, new BlockPos(x, height - yoffset * 2, z), new PlacementSettings().setReplacedBlock(Blocks.AIR));
			GenerationHelper.getTemplate(world, MOONTEMPLE_PATH_STRAIGHT).addBlocksToWorld(world, new BlockPos(x, height - yoffset * 18, z), new PlacementSettings().setReplacedBlock(Blocks.AIR));
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, 2, 2)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_PATH_TURN).addBlocksToWorld(world, new BlockPos(x, height - yoffset * 3, z + 15), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90).setReplacedBlock(Blocks.AIR));
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, 2, 1)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_PATH_STRAIGHT).addBlocksToWorld(world, new BlockPos(x, height - yoffset * 4, z + 15), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90).setReplacedBlock(Blocks.AIR));
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, 2, 0)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_PATH_STRAIGHT).addBlocksToWorld(world, new BlockPos(x, height - yoffset * 5, z + 15), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90).setReplacedBlock(Blocks.AIR));
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, 2, -1)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_PATH_STRAIGHT).addBlocksToWorld(world, new BlockPos(x, height - yoffset * 6, z + 15), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90).setReplacedBlock(Blocks.AIR));
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, 2, -2)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_PATH_TURN).addBlocksToWorld(world, new BlockPos(x + 15, height - yoffset * 7, z + 15), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180).setReplacedBlock(Blocks.AIR));
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, 1, -2)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_PATH_STRAIGHT).addBlocksToWorld(world, new BlockPos(x + 15, height - yoffset * 8, z + 15), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180).setReplacedBlock(Blocks.AIR));
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, 0, -2)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_PATH_STRAIGHT).addBlocksToWorld(world, new BlockPos(x + 15, height - yoffset * 9, z + 15), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180).setReplacedBlock(Blocks.AIR));
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, -1, -2)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_PATH_STRAIGHT).addBlocksToWorld(world, new BlockPos(x + 15, height - yoffset * 10, z + 15), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180).setReplacedBlock(Blocks.AIR));
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, -2, -2)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_PATH_TURN).addBlocksToWorld(world, new BlockPos(x + 15, height - yoffset * 11, z), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90).setReplacedBlock(Blocks.AIR));
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, -2, -1)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_PATH_STRAIGHT).addBlocksToWorld(world, new BlockPos(x + 15, height - yoffset * 12, z), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90).setReplacedBlock(Blocks.AIR));
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, -2, 0)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_PATH_STRAIGHT).addBlocksToWorld(world, new BlockPos(x + 15, height - yoffset * 13, z), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90).setReplacedBlock(Blocks.AIR));
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, -2, 1)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_PATH_STRAIGHT).addBlocksToWorld(world, new BlockPos(x + 15, height - yoffset * 14, z), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90).setReplacedBlock(Blocks.AIR));
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, -2, 2)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_PATH_TURN).addBlocksToWorld(world, new BlockPos(x, height - yoffset * 15, z), new PlacementSettings().setReplacedBlock(Blocks.AIR));
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, -1, 2)) {
			GenerationHelper.getTemplate(world, MOONTEMPLE_PATH_STRAIGHT).addBlocksToWorld(world, new BlockPos(x, height - yoffset * 16, z), new PlacementSettings().setReplacedBlock(Blocks.AIR));
		}
	}
}
