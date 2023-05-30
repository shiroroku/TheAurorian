package com.shiroroku.theaurorian.Util;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.AurorianConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

import java.util.Map;
import java.util.Map.Entry;

public class GenerationHelper {

	public interface IChunkSpecific {
		boolean isValidChunkForGen(int chunkX, int chunkZ, int offsetX, int offsetZ);
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
	 * @param structure Structure.
	 * @param worldIn World.
	 * @param pos Position.
	 * @param range Range in blocks.
	 * @param searchdistance Search distance in chunks.
	 */
	public static boolean isNearStructure(IChunkSpecific structure, World worldIn, BlockPos pos, int range, int searchdistance) {
		int chunkX = worldIn.getChunkFromBlockCoords(pos).x;
		int chunkZ = worldIn.getChunkFromBlockCoords(pos).z;
		if (worldIn.provider.getDimension() == AurorianConfig.Config_AurorianDimID) {
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
	 * @param player Player.
	 * @param distance Search distance in chunks.
	 * @return ChunkPos of structure.
	 */
	public static ChunkPos getNearestStructure(IChunkSpecific structure, EntityPlayer player, int distance) {
		int playerchunkX = player.chunkCoordX;
		int playerchunkZ = player.chunkCoordZ;
		ChunkPos closest = null;
		int closestdist = 0;
		if (player.dimension == AurorianConfig.Config_AurorianDimID) {
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
	 * @param world World.
	 * @param position Stucture Position.
	 * @param template Struture Template.
	 * @param settings Placement Settings.
	 * @param dataTag Tag Data tag of Data Blocks.
	 * @param lootTable Loot table to use.
	 */
	public static void populateChestsInTemplate(World world, BlockPos position, Template template, PlacementSettings settings, String dataTag, ResourceLocation lootTable) {
		Map<BlockPos, String> map = template.getDataBlocks(position, settings);
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
	 * Returns true if the chunk is flat determined by maximumDifference.
	 *
	 * @param pos Position of the chunk.
	 * @param maximumDifference Maximum number of blocks to allow between the
	 * highest block and lowest block in the chunk.
	 * Lower values mean flatter terrain.
	 */
	public static boolean isTerrainFlat(World worldIn, BlockPos pos, int maximumDifference) {
		int low = 255;
		int high = 0;
		for (int x = 0; x <= 16; x++) {
			for (int z = 0; z <= 16; z++) {
				for (int y = 190; y >= 60; y--) {
					if (worldIn.getBlockState(new BlockPos(pos.getX() + x, y, pos.getZ() + z)).getBlock() == BlockRegistry.Registry.AURORIANGRASS.getBlock()) {
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
		return (high - low) <= maximumDifference;
	}

}
