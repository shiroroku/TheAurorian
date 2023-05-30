package com.shiroroku.theaurorian.World.Structures;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.AurorianMod;
import com.shiroroku.theaurorian.Util.GenerationHelper;
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

import java.util.Random;

public class RunestoneTowerWorldGenerator extends WorldGenerator implements GenerationHelper.IChunkSpecific {

	private static final ResourceLocation RUNESTONETOWER_LOOTTABLELOW = new ResourceLocation(AurorianMod.MODID, "chests/runestonetowerlow");
	private static final ResourceLocation RUNESTONETOWER_LOOTTABLEMED = new ResourceLocation(AurorianMod.MODID, "chests/runestonetowermed");
	private static final ResourceLocation RUNESTONETOWER_LOOTTABLEHIGH = new ResourceLocation(AurorianMod.MODID, "chests/runestonetowerhigh");

	private static final ResourceLocation RUNESTONETOWER_TERRAIN_TL = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_terrain_tl");
	private static final ResourceLocation RUNESTONETOWER_TERRAIN_TR = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_terrain_tr");
	private static final ResourceLocation RUNESTONETOWER_TERRAIN_BL = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_terrain_bl");
	private static final ResourceLocation RUNESTONETOWER_TERRAIN_BR = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_terrain_br");
	private static final ResourceLocation RUNESTONETOWER_BASE_TL = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_base_tlv2");
	private static final ResourceLocation RUNESTONETOWER_BASE_TR = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_base_trv2");
	private static final ResourceLocation RUNESTONETOWER_BASE_BL = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_base_blv2");
	private static final ResourceLocation RUNESTONETOWER_BASE_BR = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_base_brv2");
	private static final ResourceLocation RUNESTONETOWER_FLOOR_TL = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_floor_tlv2");
	private static final ResourceLocation RUNESTONETOWER_FLOOR_TR = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_floor_trv2");
	private static final ResourceLocation RUNESTONETOWER_FLOOR_BL = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_floor_blv2");
	private static final ResourceLocation RUNESTONETOWER_FLOOR_BR = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_floor_brv2");
	private static final ResourceLocation RUNESTONETOWER_FLOOR2_TL = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_floor_2_tlv2");
	private static final ResourceLocation RUNESTONETOWER_FLOOR2_TR = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_floor_2_trv2");
	private static final ResourceLocation RUNESTONETOWER_FLOOR2_BL = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_floor_2_blv2");
	private static final ResourceLocation RUNESTONETOWER_FLOOR2_BR = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_floor_2_brv2");
	private static final ResourceLocation RUNESTONETOWER_TOP_TL = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_top_tlv2");
	private static final ResourceLocation RUNESTONETOWER_TOP_TR = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_top_trv2");
	private static final ResourceLocation RUNESTONETOWER_TOP_BL = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_top_blv2");
	private static final ResourceLocation RUNESTONETOWER_TOP_BR = new ResourceLocation(AurorianMod.MODID, "runestonedungeon/runestonetower_top_brv2");

	//TODO I made a mistake of having the templates be 15x15 when I first made them and also saving them all on different sides.
	//Need to rewrite all this soon.
	//==================
	// TERRAIN - Filler layers that go under the base so the tower doesnt look like its floating.
	// BASE	   - The first floor, has the entrance into the tower.
	// FLOOR   - Main smaller floor
	// FLOOR2  - The larger floor with parkour
	// TOP     - The boss room of the tower, not rotated (why it must be odd # of floors so stairs line up).
	//==================
	//TL = 1, -1
	//TR = 1, 0
	//BL = 0, -1
	//BR = 0, 0
	//==================

	public static int FLOOR_COUNT = AurorianConfig.Config_RunestoneDungeonFloors;
	public static int CHUNKS_BETWEEN_TOWERS = AurorianConfig.Config_DungeonDensity;
	public static boolean GENERATE_TOWERS = AurorianConfig.Config_GenerateRunestoneDungeon;

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		this.generateTower(worldIn, worldIn.getChunkFromBlockCoords(position), rand);
		return true;
	}

	@Override
	public boolean isValidChunkForGen(int chunkX, int chunkZ, int offsetX, int offsetZ) {
		return (chunkX + offsetX) % CHUNKS_BETWEEN_TOWERS == 0 && (chunkZ + offsetZ) % CHUNKS_BETWEEN_TOWERS == 0;
	}

	//This needs to be fixed, see darkstone dungeon for proper way
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
		while (blk == Blocks.AIR || blk == BlockRegistry.Registry.SILENTWOODLEAVES.getBlock() || blk == BlockRegistry.Registry.SILENTWOODLOG.getBlock() || blk instanceof BlockBush && i > 0) {
			i--;
			blk = world.getBlockState(new BlockPos(x1, i, z1)).getBlock();
		}

		//System.out.println(x1 + " " + i + 1 + " " + z1 + " - " + part);

		return i + 1;
	}

	private void generateTower(World world, Chunk c, Random r) {
		if (FLOOR_COUNT % 2 != 0) {
			FLOOR_COUNT = FLOOR_COUNT + 1;
		}

		final int chunkX = c.x;
		final int chunkZ = c.z;
		final int x = chunkX * 16 + 8;
		final int z = chunkZ * 16 + 8;
		int y = 86;

		final PlacementSettings settings = new PlacementSettings().setRotation(Rotation.NONE).setReplacedBlock(BlockRegistry.Registry.AURORIANSTONE.getBlock());
		final PlacementSettings settingsrotated = new PlacementSettings().setRotation(Rotation.CLOCKWISE_180).setReplacedBlock(BlockRegistry.Registry.AURORIANSTONE.getBlock());

		final Template floor_tl = GenerationHelper.getTemplate(world, RUNESTONETOWER_FLOOR_TL);
		final Template floor_br = GenerationHelper.getTemplate(world, RUNESTONETOWER_FLOOR_BR);
		final Template floor_tr = GenerationHelper.getTemplate(world, RUNESTONETOWER_FLOOR_TR);
		final Template floor_bl = GenerationHelper.getTemplate(world, RUNESTONETOWER_FLOOR_BL);

		final Template floor2_tl = GenerationHelper.getTemplate(world, RUNESTONETOWER_FLOOR2_TL);
		final Template floor2_br = GenerationHelper.getTemplate(world, RUNESTONETOWER_FLOOR2_BR);
		final Template floor2_tr = GenerationHelper.getTemplate(world, RUNESTONETOWER_FLOOR2_TR);
		final Template floor2_bl = GenerationHelper.getTemplate(world, RUNESTONETOWER_FLOOR2_BL);

		if (this.isValidChunkForGen(chunkX, chunkZ, 1, -1)) {
			final Template terrain_tl = GenerationHelper.getTemplate(world, RUNESTONETOWER_TERRAIN_TL);
			final Template base_tl = GenerationHelper.getTemplate(world, RUNESTONETOWER_BASE_TL);
			final Template top_tl = GenerationHelper.getTemplate(world, RUNESTONETOWER_TOP_TL);
			final Template top_br = GenerationHelper.getTemplate(world, RUNESTONETOWER_TOP_BR);
			y = this.getDungeonBaseHeight(world, x + 1, z, 1);

			//Terrain
			for (int i = y; i >= 50; i--) {
				terrain_tl.addBlocksToWorld(world, new BlockPos(x + 1, i - 1, z), settings);
			}

			//Base
			base_tl.addBlocksToWorld(world, new BlockPos(x + 1, y, z), settings);
			GenerationHelper.populateChestsInTemplate(world, new BlockPos(x + 1, y, z), base_tl, settings, "chest", RUNESTONETOWER_LOOTTABLELOW);

			//Floors
			boolean alt = true;
			int floor = 1;
			while (floor <= FLOOR_COUNT) {
				if (alt) {
					floor_tl.addBlocksToWorld(world, new BlockPos(x + 1, y + (6 * floor), z), settings);
					GenerationHelper.populateChestsInTemplate(world, new BlockPos(x + 1, y + (6 * floor), z), floor_tl, settings, "chest", floor < FLOOR_COUNT / 2 ? RUNESTONETOWER_LOOTTABLELOW : RUNESTONETOWER_LOOTTABLEMED);
					floor++;
					alt = !alt;
				} else {
					floor2_br.addBlocksToWorld(world, new BlockPos(x + 1 + 14, y + (6 * floor), z + 14), settingsrotated);
					GenerationHelper.populateChestsInTemplate(world, new BlockPos(x + 1 + 14, y + (6 * floor), z + 14), floor2_br, settingsrotated, "chest", floor < FLOOR_COUNT / 2 ? RUNESTONETOWER_LOOTTABLELOW : RUNESTONETOWER_LOOTTABLEMED);
					floor++;
					floor++;
					alt = !alt;
				}
			}

			//Boss Room
			int bossfloorlevel = y + (6 * floor);
			if (alt) {
				top_tl.addBlocksToWorld(world, new BlockPos(x + 1, bossfloorlevel, z), settings);
				GenerationHelper.populateChestsInTemplate(world, new BlockPos(x + 1, bossfloorlevel, z), top_tl, settings, "chest", RUNESTONETOWER_LOOTTABLEHIGH);
			} else {
				top_br.addBlocksToWorld(world, new BlockPos(x + 1 + 14, bossfloorlevel, z + 14), settingsrotated);
				GenerationHelper.populateChestsInTemplate(world, new BlockPos(x + 1 + 14, bossfloorlevel, z + 14), top_br, settingsrotated, "chest", RUNESTONETOWER_LOOTTABLEHIGH);
			}
		} else if (this.isValidChunkForGen(chunkX, chunkZ, 1, 0)) {
			final Template terrain_tr = GenerationHelper.getTemplate(world, RUNESTONETOWER_TERRAIN_TR);
			final Template base_tr = GenerationHelper.getTemplate(world, RUNESTONETOWER_BASE_TR);
			final Template top_tr = GenerationHelper.getTemplate(world, RUNESTONETOWER_TOP_TR);
			final Template top_bl = GenerationHelper.getTemplate(world, RUNESTONETOWER_TOP_BL);
			y = this.getDungeonBaseHeight(world, x + 1, z - 1, 2);

			for (int i = y; i >= 50; i--) {
				terrain_tr.addBlocksToWorld(world, new BlockPos(x + 1, i - 1, z + 1), settings);
			}

			base_tr.addBlocksToWorld(world, new BlockPos(x + 1, y, z + 1), settings);
			GenerationHelper.populateChestsInTemplate(world, new BlockPos(x + 1, y, z + 1), base_tr, settings, "chest", RUNESTONETOWER_LOOTTABLELOW);

			boolean alt = true;
			int floor = 1;
			while (floor <= FLOOR_COUNT) {
				if (alt) {
					floor_tr.addBlocksToWorld(world, new BlockPos(x + 1, y + (6 * floor), z + 1), settings);
					GenerationHelper.populateChestsInTemplate(world, new BlockPos(x + 1, y + (6 * floor), z + 1), floor_tr, settings, "chest", floor < FLOOR_COUNT / 2 ? RUNESTONETOWER_LOOTTABLELOW : RUNESTONETOWER_LOOTTABLEMED);
					floor++;
					alt = !alt;
				} else {
					floor2_bl.addBlocksToWorld(world, new BlockPos(x + 1 + 14, y + (6 * floor), z + 1 + 14), settingsrotated);
					GenerationHelper.populateChestsInTemplate(world, new BlockPos(x + 1 + 14, y + (6 * floor), z + 1 + 14), floor2_bl, settingsrotated, "chest", floor < FLOOR_COUNT / 2 ? RUNESTONETOWER_LOOTTABLELOW : RUNESTONETOWER_LOOTTABLEMED);
					floor++;
					floor++;
					alt = !alt;
				}
			}

			int bossfloorlevel = y + (6 * floor);
			if (alt) {
				top_tr.addBlocksToWorld(world, new BlockPos(x + 1, bossfloorlevel, z + 1), settings);
				GenerationHelper.populateChestsInTemplate(world, new BlockPos(x + 1, bossfloorlevel, z + 1), top_tr, settings, "chest", RUNESTONETOWER_LOOTTABLEHIGH);
			} else {
				top_bl.addBlocksToWorld(world, new BlockPos(x + 1 + 14, bossfloorlevel, z + 1 + 14), settingsrotated);
				GenerationHelper.populateChestsInTemplate(world, new BlockPos(x + 1 + 14, bossfloorlevel, z + 1 + 14), top_bl, settingsrotated, "chest", RUNESTONETOWER_LOOTTABLEHIGH);
			}
		} else if (this.isValidChunkForGen(chunkX, chunkZ, 0, -1)) {
			final Template terrain_bl = GenerationHelper.getTemplate(world, RUNESTONETOWER_TERRAIN_BL);
			final Template base_bl = GenerationHelper.getTemplate(world, RUNESTONETOWER_BASE_BL);
			final Template top_bl = GenerationHelper.getTemplate(world, RUNESTONETOWER_TOP_BL);
			final Template top_tr = GenerationHelper.getTemplate(world, RUNESTONETOWER_TOP_TR);
			y = this.getDungeonBaseHeight(world, x, z, 3);

			for (int i = y; i >= 50; i--) {
				terrain_bl.addBlocksToWorld(world, new BlockPos(x, i - 1, z), settings);
			}

			base_bl.addBlocksToWorld(world, new BlockPos(x, y, z), settings);
			GenerationHelper.populateChestsInTemplate(world, new BlockPos(x, y, z), base_bl, settings, "chest", RUNESTONETOWER_LOOTTABLELOW);

			boolean alt = true;
			int floor = 1;
			while (floor <= FLOOR_COUNT) {
				if (alt) {
					floor_bl.addBlocksToWorld(world, new BlockPos(x, y + (6 * floor), z), settings);
					GenerationHelper.populateChestsInTemplate(world, new BlockPos(x, y + (6 * floor), z), floor_bl, settings, "chest", floor < FLOOR_COUNT / 2 ? RUNESTONETOWER_LOOTTABLELOW : RUNESTONETOWER_LOOTTABLEMED);
					floor++;
					alt = !alt;
				} else {
					floor2_tr.addBlocksToWorld(world, new BlockPos(x + 14, y + (6 * floor), z + 14), settingsrotated);
					GenerationHelper.populateChestsInTemplate(world, new BlockPos(x + 14, y + (6 * floor), z + 14), floor2_tr, settingsrotated, "chest", floor < FLOOR_COUNT / 2 ? RUNESTONETOWER_LOOTTABLELOW : RUNESTONETOWER_LOOTTABLEMED);
					floor++;
					floor++;
					alt = !alt;
				}
			}

			int bossfloorlevel = y + (6 * floor);
			if (alt) {
				top_bl.addBlocksToWorld(world, new BlockPos(x, bossfloorlevel, z), settings);
				GenerationHelper.populateChestsInTemplate(world, new BlockPos(x, bossfloorlevel, z), top_bl, settings, "chest", RUNESTONETOWER_LOOTTABLEHIGH);
			} else {
				top_tr.addBlocksToWorld(world, new BlockPos(x + 14, bossfloorlevel, z + 14), settingsrotated);
				GenerationHelper.populateChestsInTemplate(world, new BlockPos(x + 14, bossfloorlevel, z + 14), top_tr, settingsrotated, "chest", RUNESTONETOWER_LOOTTABLEHIGH);
			}
		} else if (this.isValidChunkForGen(chunkX, chunkZ, 0, 0)) {
			final Template terrain_br = GenerationHelper.getTemplate(world, RUNESTONETOWER_TERRAIN_BR);
			final Template base_br = GenerationHelper.getTemplate(world, RUNESTONETOWER_BASE_BR);
			final Template top_br = GenerationHelper.getTemplate(world, RUNESTONETOWER_TOP_BR);
			final Template top_tl = GenerationHelper.getTemplate(world, RUNESTONETOWER_TOP_TL);
			y = this.getDungeonBaseHeight(world, x, z + 1, 4);

			for (int i = y; i >= 50; i--) {
				terrain_br.addBlocksToWorld(world, new BlockPos(x, i, z + 1), settings);
			}

			base_br.addBlocksToWorld(world, new BlockPos(x, y, z + 1), settings);
			GenerationHelper.populateChestsInTemplate(world, new BlockPos(x, y, z + 1), base_br, settings, "chest", RUNESTONETOWER_LOOTTABLELOW);

			boolean alt = true;
			int floor = 1;
			while (floor <= FLOOR_COUNT) {
				if (alt) {
					floor_br.addBlocksToWorld(world, new BlockPos(x, y + (6 * floor), z + 1), settings);
					GenerationHelper.populateChestsInTemplate(world, new BlockPos(x, y + (6 * floor), z + 1), floor_br, settings, "chest", floor < FLOOR_COUNT / 2 ? RUNESTONETOWER_LOOTTABLELOW : RUNESTONETOWER_LOOTTABLEMED);
					floor++;
					alt = !alt;
				} else {
					floor2_tl.addBlocksToWorld(world, new BlockPos(x + 14, y + (6 * floor), z + 1 + 14), settingsrotated);
					GenerationHelper.populateChestsInTemplate(world, new BlockPos(x + 14, y + (6 * floor), z + 1 + 14), floor2_tl, settingsrotated, "chest", floor < FLOOR_COUNT / 2 ? RUNESTONETOWER_LOOTTABLELOW : RUNESTONETOWER_LOOTTABLEMED);
					floor++;
					floor++;
					alt = !alt;
				}
			}

			int bossfloorlevel = y + (6 * floor);
			if (alt) {
				top_br.addBlocksToWorld(world, new BlockPos(x, bossfloorlevel, z + 1), settings);
				GenerationHelper.populateChestsInTemplate(world, new BlockPos(x, bossfloorlevel, z + 1), top_br, settings, "chest", RUNESTONETOWER_LOOTTABLEHIGH);
			} else {
				top_tl.addBlocksToWorld(world, new BlockPos(x + 14, bossfloorlevel, z + 1 + 14), settingsrotated);
				GenerationHelper.populateChestsInTemplate(world, new BlockPos(x + 14, bossfloorlevel, z + 1 + 14), top_tl, settingsrotated, "chest", RUNESTONETOWER_LOOTTABLEHIGH);
			}
		}
	}

}
