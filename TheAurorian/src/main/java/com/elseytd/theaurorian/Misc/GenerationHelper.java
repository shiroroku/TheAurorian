package com.elseytd.theaurorian.Misc;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAConfig;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class GenerationHelper {

	public interface IChunkSpecific {
		public boolean isValidChunkForGen(int chunkX, int chunkZ, int offsetX, int offsetZ);
	}

	/**
	 * Shorter version of
	 * "world.getSaveHandler().getStructureTemplateManager().getTemplate()" for
	 * readablility
	 */
	public static Template getTemplate(World world, ResourceLocation structure) {
		return world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), structure);
	}

	/**
	 * Returns true if the position is within the specified range of the
	 * structure.
	 * 
	 * @param structure      Structure.
	 * @param worldIn        World.
	 * @param pos            Position.
	 * @param range          Range in blocks.
	 * @param searchdistance Search distance in chunks.
	 */
	public static boolean isNearStructure(IChunkSpecific structure, World worldIn, BlockPos pos, int range, int searchdistance) {
		int chunkX = worldIn.getChunkFromBlockCoords(pos).x;
		int chunkZ = worldIn.getChunkFromBlockCoords(pos).z;
		if (worldIn.provider.getDimension() == TAConfig.Config_AurorianDimID) {
			for (int x = -(searchdistance / 2); x < (searchdistance / 2); x++) {
				for (int z = -(searchdistance / 2); z < (searchdistance / 2); z++) {
					if (structure.isValidChunkForGen(chunkX + x, chunkZ + z, 0, 0)) {
						int blocksaway = (int) pos.getDistance((chunkX + x) * 16, pos.getY(), (chunkZ + z) * 16);
						if (blocksaway <= range) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Returns the nearest Aurorian structure to the player within the distance
	 * specified
	 * 
	 * @param structure Structure.
	 * @param player    Player.
	 * @param distance  Search distance in chunks.
	 * @return ChunkPos of structure.
	 */
	public static ChunkPos getNearestStructure(IChunkSpecific structure, EntityPlayer player, int distance) {
		int playerchunkX = player.chunkCoordX;
		int playerchunkZ = player.chunkCoordZ;
		ChunkPos closest = null;
		int closestdist = 0;
		if (player.dimension == TAConfig.Config_AurorianDimID) {
			for (int x = -(distance / 2); x < (distance / 2); x++) {
				for (int z = -(distance / 2); z < (distance / 2); z++) {
					if (structure.isValidChunkForGen(playerchunkX + x, playerchunkZ + z, 0, 0)) {
						int blocksaway = (int) player.getDistance((playerchunkX + x) * 16, player.posY, (playerchunkZ + z) * 16);
						if (closest == null || blocksaway < closestdist) {
							closest = new ChunkPos(playerchunkX + x, playerchunkZ + z);
							closestdist = blocksaway;
						}
					}
				}
			}
		}
		return closest;
	}

	/**
	 * Will fill chests that have data blocks above them using the given loot
	 * table.
	 * 
	 * @param world     World.
	 * @param x         Template Placement x.
	 * @param y         Template Placement y.
	 * @param z         Template Placement z.
	 * @param template  Struture Template.
	 * @param settings  Placement Settings.
	 * @param data      Tag Data tag of Data Blocks.
	 * @param lootTable Loot table to use.
	 */
	public static void populateChestsInTemplate(World world, int x, int y, int z, Template template, PlacementSettings settings, String dataTag, ResourceLocation lootTable) {
		Map<BlockPos, String> map = template.getDataBlocks(new BlockPos(x, y, z), settings);
		for (Entry<BlockPos, String> entry : map.entrySet()) {
			if (dataTag.equals(entry.getValue())) {
				BlockPos blockpos2 = entry.getKey();
				world.setBlockState(blockpos2, Blocks.AIR.getDefaultState(), 3);
				TileEntity tileentity = world.getTileEntity(blockpos2.down());
				if (tileentity instanceof TileEntityChest) {
					((TileEntityChest) tileentity).setLootTable(lootTable, world.rand.nextLong());
				}
			}
		}
	}

	/**
	 * Finds all chests in the chunk and applies the given loot table to them.
	 * 
	 * @deprecated Use populateChestsInTemplate instead
	 * @param c                Chunk.
	 * @param rand             Random.
	 * @param loot             Chests Loot.
	 * @param ignorechestempty If the chests should be given loot even if they
	 *                         are not empty.
	 */
	@Deprecated
	public static void populateChestsInChunk(Chunk c, Random rand, ResourceLocation loot, boolean ignorechestempty) {
		Map<BlockPos, TileEntity> entry = c.getTileEntityMap();
		for (TileEntity e : entry.values()) {
			if (e != null) {
				if (e instanceof TileEntityChest) {
					TileEntityChest chest = (TileEntityChest) e;
					if (ignorechestempty) {
						chest.setLootTable(loot, rand.nextLong());
					} else {
						if (chest.isEmpty()) {
							chest.setLootTable(loot, rand.nextLong());
						}
					}
				}
			}
		}
	}

	/**
	 * Finds all chests in the chunk and applies the given loot table to them
	 * only if they're at the specified height.
	 * 
	 * @deprecated Use populateChestsInTemplate instead
	 * @param c                Chunk.
	 * @param y                Height at which to populate.
	 * @param rand             Random.
	 * @param loot             Chests Loot.
	 * @param ignorechestempty If the chests should be given loot even if they
	 *                         are not empty.
	 */
	@Deprecated
	public static void populateChestsInChunkAtHeight(Chunk c, int y, Random rand, ResourceLocation loot, boolean ignorechestempty) {
		Map<BlockPos, TileEntity> entry = c.getTileEntityMap();
		for (TileEntity e : entry.values()) {
			if (e != null) {
				if (e instanceof TileEntityChest) {
					TileEntityChest chest = (TileEntityChest) e;
					if (chest.getPos().getY() == y) {
						if (ignorechestempty) {
							chest.setLootTable(loot, rand.nextLong());
						} else {
							if (chest.isEmpty()) {
								chest.setLootTable(loot, rand.nextLong());
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Finds all empty chests in the chunk and applies the given loot table to
	 * them.
	 * 
	 * @deprecated Use populateChestsInTemplate instead
	 * @param c    Chunk.
	 * @param rand Random.
	 * @param loot Chests Loot.
	 */
	@Deprecated
	public static void populateChestsInChunk(Chunk c, Random rand, ResourceLocation loot) {
		populateChestsInChunk(c, rand, loot, false);
	}

	/**
	 * Returns true if the chunk is flat determined by maximumDifference.
	 * 
	 * @param pos               Position of the chunk.
	 * @param maximumDifference Maximum number of blocks to allow between the
	 *                          highest block and lowest block in the chunk.
	 *                          Lower values mean flatter terrain.
	 */
	public static boolean isTerrainFlat(World worldIn, BlockPos pos, int maximumDifference) {
		int low = 255;
		int high = 0;
		for (int x = 0; x <= 16; x++) {
			for (int z = 0; z <= 16; z++) {
				for (int y = 190; y >= 60; y--) {
					if (worldIn.getBlockState(new BlockPos(pos.getX() + x, y, pos.getZ() + z)).getBlock() == TABlocks.auroriangrass) {
						if (y >= high) {
							high = y;
						}
						if (y <= low) {
							low = y;
						}
						break;
					}
				}
			}
		}
		if ((high - low) <= maximumDifference) {
			return true;
		}
		return false;
	}

}
