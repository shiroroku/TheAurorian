package com.elseytd.theaurorian;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class TAUtil {

	/**
	 * Will return true a given percentage of the time. Example: 0.75F will
	 * return true 75% of the time.
	 */
	public static boolean randomChanceOf(float percent) {
		Random r = new Random();
		float gen = (float) r.nextInt(100) + 1F;
		if (gen <= (percent * 100F)) {
			return true;
		}
		return false;
	}

	public static class Debugging {

		/**
		 * Draws some particles to show where an aabb is.
		 */
		public static void renderAABBBounds(World worldIn, AxisAlignedBB aabb) {
			if (worldIn.isRemote && worldIn.getTotalWorldTime() % 5 == 0) {
				for (double ix = aabb.minX; ix <= aabb.maxX; ix = ix + 0.1D) {
					for (double iy = aabb.minY; iy <= aabb.maxY; iy = iy + 0.1D) {
						for (double iz = aabb.minZ; iz <= aabb.maxZ; iz = iz + 0.1D) {
							if (ix == aabb.minX || ix == aabb.maxX) {
								worldIn.spawnParticle(EnumParticleTypes.FLAME, ix, iy, iz, 0, 0, 0);
							}
							if (iy == aabb.minY || iy == aabb.maxY) {
								worldIn.spawnParticle(EnumParticleTypes.FLAME, ix, iy, iz, 0, 0, 0);
							}
							if (iz == aabb.minZ || iz == aabb.maxZ) {
								worldIn.spawnParticle(EnumParticleTypes.FLAME, ix, iy, iz, 0, 0, 0);
							}
						}
					}
				}
			}
		}

	}

	public static class LocalOreDictionary {

		/**
		 * Contains all ores and modded ores in the ore dictionary, populated by
		 * populateOrelocallist in postInit.
		 */
		public static List<ItemStack> Ores = null;

		/**
		 * Populates a list with all items and blocks that are registered as
		 * "ore" in the OreDictionary.
		 */
		public static List<ItemStack> populateOrelist() {
			String[] names = OreDictionary.getOreNames();
			List<ItemStack> ores = new ArrayList<ItemStack>();
			for (String s : names) {
				if (s.startsWith("ore")) {
					ores.addAll(OreDictionary.getOres(s));
				}
			}
			return ores;
		}

		/**
		 * Checks if the given itemstack is an ore block.
		 */
		public static boolean isOre(ItemStack itemIn) {
			for (ItemStack i : TAUtil.LocalOreDictionary.Ores) {
				if (ItemStack.areItemsEqual(i, itemIn)) {
					return true;
				}
			}
			return false;
		}

		/**
		 * Gets an other itemstack of the tag specified. For example, an
		 * itemstack registered as "oreMoonstone" and a type specified as
		 * "ingot" will return the registered ore dictionary itemstack of
		 * "ingotMoonstone".
		 * 
		 * @param Ore dictionary item
		 * @param Ore name, Ex:("ingot", "nugget")
		 */
		public static ItemStack getTypeFromOre(ItemStack itemIn, String type) {
			List<ItemStack> nuggets = new ArrayList<ItemStack>();
			for (int i : OreDictionary.getOreIDs(itemIn)) {
				String orename = OreDictionary.getOreName(i);
				String nuggetnamewouldbe = type + orename.substring(3);
				nuggets = OreDictionary.getOres(nuggetnamewouldbe);
				if (nuggets != null) {
					if (!nuggets.isEmpty()) {
						return nuggets.get(0);
					}
				}
			}
			return null;
		}

	}

	public static class WorldAndGen {

		/**
		 * Finds all chests in the chunk and applies the given loot table to
		 * them.
		 * 
		 * @param c                Chunk.
		 * @param rand             Random.
		 * @param loot             Chests Loot.
		 * @param ignorechestempty If the chests should be given loot even if
		 *                         they are not empty.
		 */
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
		 * Finds all chests in the chunk and applies the given loot table to
		 * them only if they're at the specified height.
		 * 
		 * @param c                  Chunk.
		 * @param y                  Height at which to populate.
		 * @param rand               Random.
		 * @param loot               Chests Loot.
		 * @param ignorechestemptyIf the chests should be given loot even if
		 *                           they are not empty.
		 */
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
		 * Finds all empty chests in the chunk and applies the given loot table
		 * to them.
		 * 
		 * @param c    Chunk.
		 * @param rand Random.
		 * @param loot Chests Loot.
		 */
		public static void populateChestsInChunk(Chunk c, Random rand, ResourceLocation loot) {
			populateChestsInChunk(c, rand, loot, false);
		}

		/**
		 * Returns true if the chunk is flat determined by maximumDifference.
		 * 
		 * @param pos               Position of the chunk.
		 * @param maximumDifference Maximum number of blocks to allow between
		 *                          the highest block and lowest block in the
		 *                          chunk. Lower values mean flatter terrain.
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

	public static class Moonstone {

		/**
		 * Tooltip for all Moonstone tools.
		 */
		@SideOnly(Side.CLIENT)
		public static String getMoonstoneTooltip() {
			return I18n.format("string.theaurorian.tooltip.moonstonetools");
		}

		/**
		 * Called whenever Moonstone tools take damage.
		 */
		public static void handleMoonstoneDurability(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
			if (entityLiving.dimension == TAConfig.Config_AurorianDimID || (entityLiving.dimension == 0 && !entityLiving.world.isDaytime())) {
				if (TAUtil.randomChanceOf(0.50F)) {
					stack.damageItem(1, entityLiving);
				}
			} else {
				if (TAUtil.randomChanceOf(0.50F)) {
					stack.damageItem(1, entityLiving);
				}
				stack.damageItem(1, entityLiving);
			}
		}
	}
}
