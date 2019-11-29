package com.elseytd.theaurorian.World.Structures;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TAUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class TAWorldGenerator_Runestone_Tower extends WorldGenerator {

	private static final ResourceLocation RUNESTONETOWER_LOOTTABLE = new ResourceLocation(TAMod.MODID, "chests/runestonetower");

	private static final ResourceLocation RUNESTONETOWER_TERRAIN_TL = new ResourceLocation(TAMod.MODID, "runestonedungeon/runestonetower_terrain_tl");
	private static final ResourceLocation RUNESTONETOWER_TERRAIN_TR = new ResourceLocation(TAMod.MODID, "runestonedungeon/runestonetower_terrain_tr");
	private static final ResourceLocation RUNESTONETOWER_TERRAIN_BL = new ResourceLocation(TAMod.MODID, "runestonedungeon/runestonetower_terrain_bl");
	private static final ResourceLocation RUNESTONETOWER_TERRAIN_BR = new ResourceLocation(TAMod.MODID, "runestonedungeon/runestonetower_terrain_br");

	private static final ResourceLocation RUNESTONETOWER_BASE_TL = new ResourceLocation(TAMod.MODID, "runestonedungeon/runestonetower_base_tl");
	private static final ResourceLocation RUNESTONETOWER_BASE_TR = new ResourceLocation(TAMod.MODID, "runestonedungeon/runestonetower_base_tr");
	private static final ResourceLocation RUNESTONETOWER_BASE_BL = new ResourceLocation(TAMod.MODID, "runestonedungeon/runestonetower_base_bl");
	private static final ResourceLocation RUNESTONETOWER_BASE_BR = new ResourceLocation(TAMod.MODID, "runestonedungeon/runestonetower_base_br");

	private static final ResourceLocation RUNESTONETOWER_FLOOR_TL = new ResourceLocation(TAMod.MODID, "runestonedungeon/runestonetower_floor_tl");
	private static final ResourceLocation RUNESTONETOWER_FLOOR_TR = new ResourceLocation(TAMod.MODID, "runestonedungeon/runestonetower_floor_tr");
	private static final ResourceLocation RUNESTONETOWER_FLOOR_BL = new ResourceLocation(TAMod.MODID, "runestonedungeon/runestonetower_floor_bl");
	private static final ResourceLocation RUNESTONETOWER_FLOOR_BR = new ResourceLocation(TAMod.MODID, "runestonedungeon/runestonetower_floor_br");

	private static final ResourceLocation RUNESTONETOWER_TOP_TL = new ResourceLocation(TAMod.MODID, "runestonedungeon/runestonetower_top_tl");
	private static final ResourceLocation RUNESTONETOWER_TOP_TR = new ResourceLocation(TAMod.MODID, "runestonedungeon/runestonetower_top_tr");
	private static final ResourceLocation RUNESTONETOWER_TOP_BL = new ResourceLocation(TAMod.MODID, "runestonedungeon/runestonetower_top_bl");
	private static final ResourceLocation RUNESTONETOWER_TOP_BR = new ResourceLocation(TAMod.MODID, "runestonedungeon/runestonetower_top_br");

	//==================
	// TERRAIN - Filler layers that go under the base so the tower doesnt look like its floating.
	// BASE	   - The first floor, has the entrance into the tower.
	// FLOOR   - The Floors, its rotated 180 every level so that the stairs match up.
	// TOP     - The boss room of the tower, not rotated (why it must be odd # of floors so stairs line up).
	//==================
	//TL = 1, -1
	//TR = 1, 0
	//BL = 0, -1
	//BR = 0, 0
	//==================

	public static int FLOOR_COUNT = TAConfig.Config_RunestoneTowerFloors;
	public static int CHUNKS_BETWEEN_TOWERS = TAConfig.Config_DungeonDensity;
	public static boolean GENERATE_TOWERS = TAConfig.Config_GenerateRunestoneTower;

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		Chunk c = worldIn.getChunkFromBlockCoords(position);

		if (generateTower(worldIn, c.x, c.z)) {
			TAUtil.populateChestsInChunk(c, rand, RUNESTONETOWER_LOOTTABLE);
		}

		return true;

	}

	public static boolean isValidChunkForGen(int chunkX, int chunkZ, int offsetX, int offsetZ) {
		if ((chunkX + offsetX) % CHUNKS_BETWEEN_TOWERS == 0 && (chunkZ + offsetZ) % CHUNKS_BETWEEN_TOWERS == 0) {
			return true;
		}
		return false;
	}

	private int getDungeonBaseHeight(World world, int x, int z, int part) {
		int x1 = x;
		int i = world.getHeight();
		int z1 = z;

		switch (part) {
		case 1:
			x1 = x1 + 30;
			break;
		case 2:
			x1 = x1 + 30;
			z1 = z1 + 17;
			break;
		case 3:
			x1 = x1 + 15;
			break;
		case 4:
			x1 = x1 + 15;
			z1 = z1 + 15;
			break;
		}

		Block blk = world.getBlockState(new BlockPos(x1, i, z1)).getBlock();
		while (blk == Blocks.AIR || blk == TABlocks.silentwoodleaves || blk == TABlocks.silentwoodlog && i > 0) {
			i--;
			blk = world.getBlockState(new BlockPos(x1, i, z1)).getBlock();
		}

		return i + 1;
	}

	public boolean generateTower(World world, int chunkX, int chunkZ) {
		if (FLOOR_COUNT % 2 != 0) {
			FLOOR_COUNT = FLOOR_COUNT + 1;
		}

		boolean gen = false;
		int x = chunkX * 16 + 8;
		int z = chunkZ * 16 + 8;
		int y = 86;

		final PlacementSettings settings = new PlacementSettings().setRotation(Rotation.NONE).setReplacedBlock(TABlocks.aurorianstone);
		final PlacementSettings settingsrotated = new PlacementSettings().setRotation(Rotation.CLOCKWISE_180).setReplacedBlock(TABlocks.aurorianstone);

		final Template floor_tl = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), RUNESTONETOWER_FLOOR_TL);
		final Template floor_br = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), RUNESTONETOWER_FLOOR_BR);
		final Template floor_tr = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), RUNESTONETOWER_FLOOR_TR);
		final Template floor_bl = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), RUNESTONETOWER_FLOOR_BL);

		if (isValidChunkForGen(chunkX, chunkZ, 1, -1)) {
			final Template terrain_tl = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), RUNESTONETOWER_TERRAIN_TL);
			final Template base_tl = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), RUNESTONETOWER_BASE_TL);
			final Template top_tl = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), RUNESTONETOWER_TOP_TL);
			y = getDungeonBaseHeight(world, x + 1, z, 1);
			int toplevel = y + (6 * (FLOOR_COUNT + 1));

			for (int i = y; i >= 50; i--) {
				terrain_tl.addBlocksToWorld(world, new BlockPos(x + 1, i - 1, z), settings);
			}

			base_tl.addBlocksToWorld(world, new BlockPos(x + 1, y, z), settings);
			top_tl.addBlocksToWorld(world, new BlockPos(x + 1, toplevel, z), settings);

			for (int i = 1; i <= FLOOR_COUNT; i++) {
				if (i % 2 != 0) {
					floor_tl.addBlocksToWorld(world, new BlockPos(x + 1, y + (6 * i), z), settings);
				} else {
					floor_br.addBlocksToWorld(world, new BlockPos(x + 1 + 14, y + (6 * i), z + 14), settingsrotated);
				}
			}
			gen = true;
		} else if (isValidChunkForGen(chunkX, chunkZ, 1, 0)) {
			final Template terrain_tr = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), RUNESTONETOWER_TERRAIN_TR);
			final Template base_tr = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), RUNESTONETOWER_BASE_TR);
			final Template top_tr = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), RUNESTONETOWER_TOP_TR);
			y = getDungeonBaseHeight(world, x + 1, z - 1, 2);
			int toplevel = y + (6 * (FLOOR_COUNT + 1));

			for (int i = y; i >= 50; i--) {
				terrain_tr.addBlocksToWorld(world, new BlockPos(x + 1, i - 1, z + 1), settings);
			}

			base_tr.addBlocksToWorld(world, new BlockPos(x + 1, y, z + 1), settings);
			top_tr.addBlocksToWorld(world, new BlockPos(x + 1, toplevel, z + 1), settings);

			for (int i = 1; i <= FLOOR_COUNT; i++) {
				if (i % 2 != 0) {
					floor_tr.addBlocksToWorld(world, new BlockPos(x + 1, y + (6 * i), z + 1), settings);
				} else {
					floor_bl.addBlocksToWorld(world, new BlockPos(x + 1 + 14, y + (6 * i), z + 1 + 14), settingsrotated);
				}
			}
			gen = true;
		} else if (isValidChunkForGen(chunkX, chunkZ, 0, -1)) {
			final Template terrain_bl = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), RUNESTONETOWER_TERRAIN_BL);
			final Template base_bl = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), RUNESTONETOWER_BASE_BL);
			final Template top_bl = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), RUNESTONETOWER_TOP_BL);
			y = getDungeonBaseHeight(world, x, z, 3);
			int toplevel = y + (6 * (FLOOR_COUNT + 1));

			for (int i = y; i >= 50; i--) {
				terrain_bl.addBlocksToWorld(world, new BlockPos(x, i - 1, z), settings);
			}

			base_bl.addBlocksToWorld(world, new BlockPos(x, y, z), settings);
			top_bl.addBlocksToWorld(world, new BlockPos(x, toplevel, z), settings);

			for (int i = 1; i <= FLOOR_COUNT; i++) {
				if (i % 2 != 0) {
					floor_bl.addBlocksToWorld(world, new BlockPos(x, y + (6 * i), z), settings);
				} else {
					floor_tr.addBlocksToWorld(world, new BlockPos(x + 14, y + (6 * i), z + 14), settingsrotated);
				}
			}
			gen = true;
		} else if (isValidChunkForGen(chunkX, chunkZ, 0, 0)) {
			final Template terrain_br = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), RUNESTONETOWER_TERRAIN_BR);
			final Template base_br = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), RUNESTONETOWER_BASE_BR);
			final Template top_br = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), RUNESTONETOWER_TOP_BR);
			y = getDungeonBaseHeight(world, x, z + 1, 4);
			int toplevel = y + (6 * (FLOOR_COUNT + 1));

			for (int i = y; i >= 50; i--) {
				terrain_br.addBlocksToWorld(world, new BlockPos(x, i, z + 1), settings);
			}

			base_br.addBlocksToWorld(world, new BlockPos(x, y, z + 1), settings);
			top_br.addBlocksToWorld(world, new BlockPos(x, toplevel, z + 1), settings);

			for (int i = 1; i <= FLOOR_COUNT; i++) {
				if (i % 2 != 0) {
					floor_br.addBlocksToWorld(world, new BlockPos(x, y + (6 * i), z + 1), settings);
				} else {
					floor_tl.addBlocksToWorld(world, new BlockPos(x + 14, y + (6 * i), z + 1 + 14), settingsrotated);
				}
			}
			gen = true;
		}
		return gen;
	}

}
