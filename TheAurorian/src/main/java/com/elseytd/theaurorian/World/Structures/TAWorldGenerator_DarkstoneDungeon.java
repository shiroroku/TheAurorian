package com.elseytd.theaurorian.World.Structures;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.Misc.GenerationHelper;

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

public class TAWorldGenerator_DarkstoneDungeon extends WorldGenerator implements GenerationHelper.IChunkSpecific {

	private static final ResourceLocation DARKSTONE_LOOTTABLELOW = new ResourceLocation(TAMod.MODID, "chests/darkstonelow");
	private static final ResourceLocation DARKSTONE_LOOTTABLEMED = new ResourceLocation(TAMod.MODID, "chests/darkstonemed");
	private static final ResourceLocation DARKSTONE_LOOTTABLEHIGH = new ResourceLocation(TAMod.MODID, "chests/darkstonehigh");

	private static final ResourceLocation DARKSTONE_CORNER = new ResourceLocation(TAMod.MODID, "darkstone/darkstone_corner");
	private static final ResourceLocation DARKSTONE_STRAIGHT = new ResourceLocation(TAMod.MODID, "darkstone/darkstone_straight");
	private static final ResourceLocation DARKSTONE_STRAIGHT_B = new ResourceLocation(TAMod.MODID, "darkstone/darkstone_straight_b");
	private static final ResourceLocation DARKSTONE_CROSS = new ResourceLocation(TAMod.MODID, "darkstone/darkstone_cross");
	private static final ResourceLocation DARKSTONE_END = new ResourceLocation(TAMod.MODID, "darkstone/darkstone_end");
	private static final ResourceLocation DARKSTONE_T = new ResourceLocation(TAMod.MODID, "darkstone/darkstone_t");
	private static final ResourceLocation DARKSTONE_STAIRS = new ResourceLocation(TAMod.MODID, "darkstone/darkstone_stairs");
	private static final ResourceLocation DARKSTONE_ENTRANCE = new ResourceLocation(TAMod.MODID, "darkstone/darkstone_entrance");
	private static final ResourceLocation DARKSTONE_BOSSROOM_FRONT = new ResourceLocation(TAMod.MODID, "darkstone/darkstone_bossroom_front");
	private static final ResourceLocation DARKSTONE_BOSSROOM_FRONTRIGHT = new ResourceLocation(TAMod.MODID, "darkstone/darkstone_bossroom_frontright");
	private static final ResourceLocation DARKSTONE_BOSSROOM_FRONTLEFT = new ResourceLocation(TAMod.MODID, "darkstone/darkstone_bossroom_frontleft");
	private static final ResourceLocation DARKSTONE_BOSSROOM_BACK = new ResourceLocation(TAMod.MODID, "darkstone/darkstone_bossroom_back");
	private static final ResourceLocation DARKSTONE_BOSSROOM_BACKRIGHT = new ResourceLocation(TAMod.MODID, "darkstone/darkstone_bossroom_backright");
	private static final ResourceLocation DARKSTONE_BOSSROOM_BACKLEFT = new ResourceLocation(TAMod.MODID, "darkstone/darkstone_bossroom_backleft");

	public static int CHUNKS_BETWEEN_DUNGEONS = TAConfig.Config_DungeonDensity * 4;

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		Chunk c = worldIn.getChunkFromBlockCoords(position);

		//generateDungeon(worldIn, c);

		return true;
	}

	@Override
	public boolean isValidChunkForGen(int chunkX, int chunkZ, int offsetX, int offsetZ) {
		int offs = (TAConfig.Config_DungeonDensity / 4);
		if ((chunkX + offsetX + offs) % CHUNKS_BETWEEN_DUNGEONS == 0 && (chunkZ + offsetZ + offs) % CHUNKS_BETWEEN_DUNGEONS == 0) {
			return true;
		}
		return false;
	}

	// └ ┘ ┌ ┐
	// ─ │
	// ╴ ╵ ╶ ╷
	// ┼ ┤ ┴ ├ ┬
	// ╟ ╤ ╢ ╧

	/*
	 * isValidChunkForGen x and z are not aligned with minecrafts, need to fix
	 * this sometime but would require rework of moontemples and runestones also
	 * Facing west: west+ = x- north+ = z-
	 */

	private String[][] map_a = { { "╷╷╟─┐", "└┴┐╷│", "┌─┘└┤", "├╴┌╴│", "└─┼─┘" }, { "┌┬ ┌╴", "│└─┼┐", "╵┌─┘│", "┌┴╴╶┘", "└─┬─╴" } };

	private String[][] mapToUse = map_a;

	private void generateDungeon(World world, Chunk c) {
		int chunkX = c.x;
		int chunkZ = c.z;
		int x = chunkX * 16 + 8;
		int z = chunkZ * 16 + 8;
		PlacementSettings settings = new PlacementSettings().setRotation(Rotation.NONE).setReplacedBlock(TABlocks.aurorianstone);

		//Generate Entrance & Stairs
		if (isValidChunkForGen(chunkX, chunkZ, 0, 0)) {
			GenerationHelper.getTemplate(world, DARKSTONE_ENTRANCE).addBlocksToWorld(world, new BlockPos(x, getHeightOfDungeon(world, chunkX, chunkZ, 0, 0), z), settings);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 1, 0)) {
			createStructureFromChar('╤', world, x, getHeightOfDungeon(world, chunkX, chunkZ, 1, 0), z, 0);
		}

		//Align Map to a chunk from entrance (presuming the map is 5x5)
		int mapoffsety = 6;
		int mapoffsetx = -2;
		for (int maplayer = 0; maplayer < mapToUse.length; maplayer++) {
			for (int iy = 0; iy < mapToUse[maplayer].length; iy++) {
				for (int ix = 0; ix < mapToUse[maplayer][iy].toCharArray().length; ix++) {
					if (isValidChunkForGen(chunkX, chunkZ, -iy + mapoffsety, ix + mapoffsetx)) {
						createStructureFromChar(mapToUse[maplayer][iy].toCharArray()[ix], world, x, getHeightOfDungeon(world, chunkX, chunkZ, -iy + mapoffsety, ix + mapoffsetx) - (14 * (maplayer + 1)), z, maplayer);
					}
				}
			}
		}

		//Generate Boss Room
		int bossRoomYOffset = 14 * 2;
		if (isValidChunkForGen(chunkX, chunkZ, 0, 0)) {
			Template template = GenerationHelper.getTemplate(world, DARKSTONE_BOSSROOM_BACK);
			template.addBlocksToWorld(world, new BlockPos(x, getHeightOfDungeon(world, chunkX, chunkZ, 0, 0) - bossRoomYOffset, z), settings);
			GenerationHelper.populateChestsInTemplate(world, x, getHeightOfDungeon(world, chunkX, chunkZ, 0, 0) - bossRoomYOffset, z, template, settings, "chest", DARKSTONE_LOOTTABLEHIGH);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 0, 1)) {
			Template template = GenerationHelper.getTemplate(world, DARKSTONE_BOSSROOM_BACKLEFT);
			template.addBlocksToWorld(world, new BlockPos(x, getHeightOfDungeon(world, chunkX, chunkZ, 0, 1) - bossRoomYOffset, z), settings);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 0, -1)) {
			Template template = GenerationHelper.getTemplate(world, DARKSTONE_BOSSROOM_BACKRIGHT);
			template.addBlocksToWorld(world, new BlockPos(x, getHeightOfDungeon(world, chunkX, chunkZ, 0, -1) - bossRoomYOffset, z), settings);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 1, 0)) {
			Template template = GenerationHelper.getTemplate(world, DARKSTONE_BOSSROOM_FRONT);
			template.addBlocksToWorld(world, new BlockPos(x, getHeightOfDungeon(world, chunkX, chunkZ, 1, 0) - bossRoomYOffset, z), settings);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 1, 1)) {
			Template template = GenerationHelper.getTemplate(world, DARKSTONE_BOSSROOM_FRONTLEFT);
			template.addBlocksToWorld(world, new BlockPos(x, getHeightOfDungeon(world, chunkX, chunkZ, 1, 1) - bossRoomYOffset, z), settings);
		}
		if (isValidChunkForGen(chunkX, chunkZ, 1, -1)) {
			Template template = GenerationHelper.getTemplate(world, DARKSTONE_BOSSROOM_FRONTRIGHT);
			template.addBlocksToWorld(world, new BlockPos(x, getHeightOfDungeon(world, chunkX, chunkZ, 1, -1) - bossRoomYOffset, z), settings);
		}

	}

	/**
	 * Gets height of dungeon from a single block specified at dungeon origin
	 * 
	 * @param xin          actual chunk x
	 * @param zin          actual chunk z
	 * @param chunkoffsetx dungeon chunk x offset
	 * @param chunkoffsetz dungeon chunk z offset
	 */
	private int getHeightOfDungeon(World world, int xin, int zin, int chunkoffsetx, int chunkoffsetz) {
		int h = world.getHeight();

		int originoffsetx = 16 + 8;
		int originoffsetz = 8 + 8;
		int heightoffset = -3;
		int minimumheight = 40;

		int x = (chunkoffsetx * 16 + (xin * 16)) + originoffsetx;
		int z = (chunkoffsetz * 16 + (zin * 16)) + originoffsetz;

		Block blk = world.getBlockState(new BlockPos(x, h, z)).getBlock();
		while ((blk == Blocks.AIR || blk == TABlocks.silentwoodleaves || blk == TABlocks.silentwoodlog || blk instanceof BlockBush) && h > minimumheight) {
			h--;
			blk = world.getBlockState(new BlockPos(x, h, z)).getBlock();
		}

		//System.out.println(chunkoffsetx + ":" + chunkoffsetz + "|" + x + " " + h + " " + z);
		return h + heightoffset;
	}

	private void createStructureFromChar(char c, World world, int x, int y, int z, int floorForLootRarity) {
		PlacementSettings settings = new PlacementSettings().setRotation(Rotation.NONE).setReplacedBlock(TABlocks.aurorianstone);
		boolean populateChests = false;
		ResourceLocation loot = floorForLootRarity == 0 ? DARKSTONE_LOOTTABLELOW : DARKSTONE_LOOTTABLEMED;
		Template template = null;
		int placementx = x;
		int placementy = y;
		int placementz = z;

		switch (c) {
		case ' ':
			return;
		case '┼':
			template = GenerationHelper.getTemplate(world, DARKSTONE_CROSS);
			populateChests = true;
			break;
		case '└':
			template = GenerationHelper.getTemplate(world, DARKSTONE_CORNER);
			break;
		case '┘':
			template = GenerationHelper.getTemplate(world, DARKSTONE_CORNER);
			settings.setRotation(Rotation.COUNTERCLOCKWISE_90);
			placementz += 15;
			break;
		case '┌':
			template = GenerationHelper.getTemplate(world, DARKSTONE_CORNER);
			settings.setRotation(Rotation.CLOCKWISE_90);
			placementx += 15;
			break;
		case '┐':
			template = GenerationHelper.getTemplate(world, DARKSTONE_CORNER);
			settings.setRotation(Rotation.CLOCKWISE_180);
			placementz += 15;
			placementx += 15;
			break;
		case '─':
			boolean variant = world.rand.nextBoolean();
			populateChests = variant ? true : false;
			template = GenerationHelper.getTemplate(world, (variant ? DARKSTONE_STRAIGHT : DARKSTONE_STRAIGHT_B));
			break;
		case '│':
			boolean variant2 = world.rand.nextBoolean();
			populateChests = variant2 ? true : false;
			template = GenerationHelper.getTemplate(world, (variant2 ? DARKSTONE_STRAIGHT : DARKSTONE_STRAIGHT_B));
			settings.setRotation(Rotation.COUNTERCLOCKWISE_90);
			placementz += 15;
			break;
		case '╷':
			template = GenerationHelper.getTemplate(world, DARKSTONE_END);
			populateChests = true;
			break;
		case '╴':
			template = GenerationHelper.getTemplate(world, DARKSTONE_END);
			settings.setRotation(Rotation.CLOCKWISE_90);
			placementx += 15;
			populateChests = true;
			break;
		case '╶':
			template = GenerationHelper.getTemplate(world, DARKSTONE_END);
			settings.setRotation(Rotation.COUNTERCLOCKWISE_90);
			placementz += 15;
			populateChests = true;
			break;
		case '╵':
			template = GenerationHelper.getTemplate(world, DARKSTONE_END);
			settings.setRotation(Rotation.CLOCKWISE_180);
			placementz += 15;
			placementx += 15;
			populateChests = true;
			break;
		case '├':
			template = GenerationHelper.getTemplate(world, DARKSTONE_T);
			break;
		case '┬':
			template = GenerationHelper.getTemplate(world, DARKSTONE_T);
			settings.setRotation(Rotation.CLOCKWISE_90);
			placementx += 15;
			break;
		case '┤':
			template = GenerationHelper.getTemplate(world, DARKSTONE_T);
			settings.setRotation(Rotation.CLOCKWISE_180);
			placementz += 15;
			placementx += 15;
			break;
		case '┴':
			template = GenerationHelper.getTemplate(world, DARKSTONE_T);
			settings.setRotation(Rotation.COUNTERCLOCKWISE_90);
			placementz += 15;
			break;
		case '╤':
			template = GenerationHelper.getTemplate(world, DARKSTONE_STAIRS);
			placementy -= 14;
			break;
		case '╟':
			template = GenerationHelper.getTemplate(world, DARKSTONE_STAIRS);
			settings.setRotation(Rotation.COUNTERCLOCKWISE_90);
			placementz += 15;
			placementy -= 14;
			break;
		case '╢':
			template = GenerationHelper.getTemplate(world, DARKSTONE_STAIRS);
			settings.setRotation(Rotation.CLOCKWISE_90);
			placementx += 15;
			placementy -= 14;
			break;
		case '╧':
			template = GenerationHelper.getTemplate(world, DARKSTONE_STAIRS);
			settings.setRotation(Rotation.CLOCKWISE_180);
			placementz += 15;
			placementx += 15;
			placementy -= 14;
			break;
		}

		if (template != null) {
			template.addBlocksToWorld(world, new BlockPos(placementx, placementy, placementz), settings);
			if (populateChests && loot != null) {
				GenerationHelper.populateChestsInTemplate(world, placementx, placementy, placementz, template, settings, "chest", loot);
			}
		}
	}

}
