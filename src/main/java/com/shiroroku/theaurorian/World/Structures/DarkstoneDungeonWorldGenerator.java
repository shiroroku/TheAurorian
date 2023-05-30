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

public class DarkstoneDungeonWorldGenerator extends WorldGenerator implements GenerationHelper.IChunkSpecific {

	private static final ResourceLocation DARKSTONE_LOOTTABLELOW = new ResourceLocation(AurorianMod.MODID, "chests/darkstonelow");
	private static final ResourceLocation DARKSTONE_LOOTTABLEMED = new ResourceLocation(AurorianMod.MODID, "chests/darkstonemed");
	private static final ResourceLocation DARKSTONE_LOOTTABLEHIGH = new ResourceLocation(AurorianMod.MODID, "chests/darkstonehigh");

	private static final ResourceLocation DARKSTONE_CORNER = new ResourceLocation(AurorianMod.MODID, "darkstone/darkstone_corner");
	private static final ResourceLocation DARKSTONE_STRAIGHT = new ResourceLocation(AurorianMod.MODID, "darkstone/darkstone_straight");
	private static final ResourceLocation DARKSTONE_STRAIGHT_B = new ResourceLocation(AurorianMod.MODID, "darkstone/darkstone_straight_b");
	private static final ResourceLocation DARKSTONE_CROSS = new ResourceLocation(AurorianMod.MODID, "darkstone/darkstone_cross");
	private static final ResourceLocation DARKSTONE_END = new ResourceLocation(AurorianMod.MODID, "darkstone/darkstone_end");
	private static final ResourceLocation DARKSTONE_T = new ResourceLocation(AurorianMod.MODID, "darkstone/darkstone_t");
	private static final ResourceLocation DARKSTONE_STAIRS = new ResourceLocation(AurorianMod.MODID, "darkstone/darkstone_stairs");
	private static final ResourceLocation DARKSTONE_ENTRANCE = new ResourceLocation(AurorianMod.MODID, "darkstone/darkstone_entrance");
	private static final ResourceLocation DARKSTONE_BOSSROOM_FRONT = new ResourceLocation(AurorianMod.MODID, "darkstone/darkstone_bossroom_front");
	private static final ResourceLocation DARKSTONE_BOSSROOM_FRONTRIGHT = new ResourceLocation(AurorianMod.MODID, "darkstone/darkstone_bossroom_frontright");
	private static final ResourceLocation DARKSTONE_BOSSROOM_FRONTLEFT = new ResourceLocation(AurorianMod.MODID, "darkstone/darkstone_bossroom_frontleft");
	private static final ResourceLocation DARKSTONE_BOSSROOM_BACK = new ResourceLocation(AurorianMod.MODID, "darkstone/darkstone_bossroom_back");
	private static final ResourceLocation DARKSTONE_BOSSROOM_BACKRIGHT = new ResourceLocation(AurorianMod.MODID, "darkstone/darkstone_bossroom_backright");
	private static final ResourceLocation DARKSTONE_BOSSROOM_BACKLEFT = new ResourceLocation(AurorianMod.MODID, "darkstone/darkstone_bossroom_backleft");

	public static int CHUNKS_BETWEEN_DUNGEONS = AurorianConfig.Config_DungeonDensity * 2;
	public static boolean GENERATE_DUNGEON = AurorianConfig.Config_GenerateDarkstoneDungeon;

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		this.generateDungeon(worldIn, worldIn.getChunkFromBlockCoords(position));
		return true;
	}

	/*
	 * isValidChunkForGen x and z are not aligned with minecrafts, need to fix
	 * this sometime but would require rework of moontemples and runestones also
	 * Facing west: west+ = x- north+ = z-
	 */

	@Override
	public boolean isValidChunkForGen(int chunkX, int chunkZ, int offsetX, int offsetZ) {
		int offs = AurorianConfig.Config_DungeonDensity / 2;
		return (chunkX + offsetX + offs) % CHUNKS_BETWEEN_DUNGEONS == 0 && (chunkZ + offsetZ + offs) % CHUNKS_BETWEEN_DUNGEONS == 0;
	}

	/*
	 * Because gradle didnt like to build my map chars, I have to change to
	 * letters -3-
	 */
	private final String[][] map_a = { { "AAQIH", "EOHAJ", "GIFEN", "LBGBJ", "EIKIF" }, { "GM GB", "JEIKH", "DGIFJ", "GOBCF", "EIMIB" } };
	private final String[][] map_b = { { "GHCHA", "JSAEN", "EMKIF", "GFEMH", "DCMFD" }, { "GIMHA", "J JEN", "JJDGF", "EFGFA", "CIKIF" } };

	//Map Settings
	private final int mapWidth = 5;
	private final int mapLength = 5;
	private final int mapFloors = 2;
	private final int mapoffsetx = 6;
	private final int mapoffsetz = -2;

	//A clever way we can have map variations without using rng
	private String[][] getMapFromDungeonHeight(int height) {
		return height % 2 == 0 ? this.map_b : this.map_a;
	}

	private void generateDungeon(World world, Chunk c) {
		final int chunkX = c.x;
		final int chunkZ = c.z;
		final int x = chunkX * 16 + 8;
		final int z = chunkZ * 16 + 8;
		final PlacementSettings settings = new PlacementSettings().setReplacedBlock(BlockRegistry.Registry.AURORIANSTONE.getBlock());

		//Generate Entrance & Stairs
		if (this.isValidChunkForGen(chunkX, chunkZ, 0, 0)) {
			GenerationHelper.getTemplate(world, DARKSTONE_ENTRANCE).addBlocksToWorld(world, new BlockPos(x, this.getHeightOfDungeon(world, chunkX, chunkZ, 0, 0), z), settings);
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, 1, 0)) {
			this.createStructureFromChar('P', world, x, this.getHeightOfDungeon(world, chunkX, chunkZ, 1, 0), z, 0);
		}

		//Generate from map
		for (int maplayer = 0; maplayer < this.mapFloors; maplayer++) {
			for (int ix = 0; ix < this.mapLength; ix++) {
				for (int iz = 0; iz < this.mapWidth; iz++) {
					if (this.isValidChunkForGen(chunkX, chunkZ, -ix + this.mapoffsetx, iz + this.mapoffsetz)) {
						final int y = this.getHeightOfDungeon(world, chunkX, chunkZ, -ix + this.mapoffsetx, iz + this.mapoffsetz) - (14 * (maplayer + 1));
						final String[][] mapToUse = this.getMapFromDungeonHeight(y);
						this.createStructureFromChar(mapToUse[maplayer][ix].toCharArray()[iz], world, x, y, z, maplayer);
					}
				}
			}
		}

		//Generate Boss Room
		this.generateBossRoom(world, chunkX, chunkZ, x, z);
	}

	private void generateBossRoom(World world, int chunkX, int chunkZ, int x, int z) {
		final PlacementSettings settings = new PlacementSettings().setReplacedBlock(BlockRegistry.Registry.AURORIANSTONE.getBlock());
		final int bossRoomYOffset = 14 * 2;
		if (this.isValidChunkForGen(chunkX, chunkZ, 0, 0)) {
			final Template template = GenerationHelper.getTemplate(world, DARKSTONE_BOSSROOM_BACK);
			template.addBlocksToWorld(world, new BlockPos(x, this.getHeightOfDungeon(world, chunkX, chunkZ, 0, 0) - bossRoomYOffset, z), settings);
			GenerationHelper.populateChestsInTemplate(world, new BlockPos(x, this.getHeightOfDungeon(world, chunkX, chunkZ, 0, 0) - bossRoomYOffset, z), template, settings, "chest", DARKSTONE_LOOTTABLEHIGH);
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, 0, 1)) {
			final Template template = GenerationHelper.getTemplate(world, DARKSTONE_BOSSROOM_BACKLEFT);
			template.addBlocksToWorld(world, new BlockPos(x, this.getHeightOfDungeon(world, chunkX, chunkZ, 0, 1) - bossRoomYOffset, z), settings);
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, 0, -1)) {
			final Template template = GenerationHelper.getTemplate(world, DARKSTONE_BOSSROOM_BACKRIGHT);
			template.addBlocksToWorld(world, new BlockPos(x, this.getHeightOfDungeon(world, chunkX, chunkZ, 0, -1) - bossRoomYOffset, z), settings);
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, 1, 0)) {
			final Template template = GenerationHelper.getTemplate(world, DARKSTONE_BOSSROOM_FRONT);
			template.addBlocksToWorld(world, new BlockPos(x, this.getHeightOfDungeon(world, chunkX, chunkZ, 1, 0) - bossRoomYOffset, z), settings);
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, 1, 1)) {
			final Template template = GenerationHelper.getTemplate(world, DARKSTONE_BOSSROOM_FRONTLEFT);
			template.addBlocksToWorld(world, new BlockPos(x, this.getHeightOfDungeon(world, chunkX, chunkZ, 1, 1) - bossRoomYOffset, z), settings);
			return;
		}
		if (this.isValidChunkForGen(chunkX, chunkZ, 1, -1)) {
			final Template template = GenerationHelper.getTemplate(world, DARKSTONE_BOSSROOM_FRONTRIGHT);
			template.addBlocksToWorld(world, new BlockPos(x, this.getHeightOfDungeon(world, chunkX, chunkZ, 1, -1) - bossRoomYOffset, z), settings);
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
		int heightoffset = -1;
		int minimumheight = 40;

		int x = (chunkoffsetx * 16 + (xin * 16)) + originoffsetx;
		int z = (chunkoffsetz * 16 + (zin * 16)) + originoffsetz;

		Block blk = world.getBlockState(new BlockPos(x, h, z)).getBlock();
		while ((blk == Blocks.AIR || blk == BlockRegistry.Registry.SILENTWOODLEAVES.getBlock() || blk == BlockRegistry.Registry.SILENTWOODLOG.getBlock() || blk instanceof BlockBush) && h > minimumheight) {
			h--;
			blk = world.getBlockState(new BlockPos(x, h, z)).getBlock();
		}

		//System.out.println(chunkoffsetx + ":" + chunkoffsetz + "|" + x + " " + h + " " + z);
		return h + heightoffset;
	}

	private void createStructureFromChar(char c, World world, int x, int y, int z, int floorForLootRarity) {
		final PlacementSettings settings = new PlacementSettings().setReplacedBlock(BlockRegistry.Registry.AURORIANSTONE.getBlock());
		final ResourceLocation loot = floorForLootRarity == 0 ? DARKSTONE_LOOTTABLELOW : DARKSTONE_LOOTTABLEMED;
		boolean populateChests = false;
		Template template = null;
		int placementx = x;
		int placementy = y;
		int placementz = z;

		switch (c) {
			case ' ':
				return;
			case 'K':
				template = GenerationHelper.getTemplate(world, DARKSTONE_CROSS);
				populateChests = true;
				break;
			case 'E':
				template = GenerationHelper.getTemplate(world, DARKSTONE_CORNER);
				break;
			case 'F':
				template = GenerationHelper.getTemplate(world, DARKSTONE_CORNER);
				settings.setRotation(Rotation.COUNTERCLOCKWISE_90);
				placementz += 15;
				break;
			case 'G':
				template = GenerationHelper.getTemplate(world, DARKSTONE_CORNER);
				settings.setRotation(Rotation.CLOCKWISE_90);
				placementx += 15;
				break;
			case 'H':
				template = GenerationHelper.getTemplate(world, DARKSTONE_CORNER);
				settings.setRotation(Rotation.CLOCKWISE_180);
				placementz += 15;
				placementx += 15;
				break;
			case 'I':
				boolean variant = world.rand.nextBoolean();
				populateChests = variant;
				template = GenerationHelper.getTemplate(world, (variant ? DARKSTONE_STRAIGHT : DARKSTONE_STRAIGHT_B));
				break;
			case 'J':
				boolean variant2 = world.rand.nextBoolean();
				populateChests = variant2;
				template = GenerationHelper.getTemplate(world, (variant2 ? DARKSTONE_STRAIGHT : DARKSTONE_STRAIGHT_B));
				settings.setRotation(Rotation.COUNTERCLOCKWISE_90);
				placementz += 15;
				break;
			case 'A':
				template = GenerationHelper.getTemplate(world, DARKSTONE_END);
				populateChests = true;
				break;
			case 'B':
				template = GenerationHelper.getTemplate(world, DARKSTONE_END);
				settings.setRotation(Rotation.CLOCKWISE_90);
				placementx += 15;
				populateChests = true;
				break;
			case 'C':
				template = GenerationHelper.getTemplate(world, DARKSTONE_END);
				settings.setRotation(Rotation.COUNTERCLOCKWISE_90);
				placementz += 15;
				populateChests = true;
				break;
			case 'D':
				template = GenerationHelper.getTemplate(world, DARKSTONE_END);
				settings.setRotation(Rotation.CLOCKWISE_180);
				placementz += 15;
				placementx += 15;
				populateChests = true;
				break;
			case 'L':
				template = GenerationHelper.getTemplate(world, DARKSTONE_T);
				break;
			case 'M':
				template = GenerationHelper.getTemplate(world, DARKSTONE_T);
				settings.setRotation(Rotation.CLOCKWISE_90);
				placementx += 15;
				break;
			case 'N':
				template = GenerationHelper.getTemplate(world, DARKSTONE_T);
				settings.setRotation(Rotation.CLOCKWISE_180);
				placementz += 15;
				placementx += 15;
				break;
			case 'O':
				template = GenerationHelper.getTemplate(world, DARKSTONE_T);
				settings.setRotation(Rotation.COUNTERCLOCKWISE_90);
				placementz += 15;
				break;
			case 'P':
				template = GenerationHelper.getTemplate(world, DARKSTONE_STAIRS);
				placementy -= 14;
				break;
			case 'Q':
				template = GenerationHelper.getTemplate(world, DARKSTONE_STAIRS);
				settings.setRotation(Rotation.COUNTERCLOCKWISE_90);
				placementz += 15;
				placementy -= 14;
				break;
			case 'R':
				template = GenerationHelper.getTemplate(world, DARKSTONE_STAIRS);
				settings.setRotation(Rotation.CLOCKWISE_90);
				placementx += 15;
				placementy -= 14;
				break;
			case 'S':
				template = GenerationHelper.getTemplate(world, DARKSTONE_STAIRS);
				settings.setRotation(Rotation.CLOCKWISE_180);
				placementz += 15;
				placementx += 15;
				placementy -= 14;
				break;
		}

		if (template != null) {
			BlockPos position = new BlockPos(placementx, placementy, placementz);
			template.addBlocksToWorld(world, position, settings);
			if (populateChests && loot != null) {
				GenerationHelper.populateChestsInTemplate(world, position, template, settings, "chest", loot);
			}
		}
	}

}
