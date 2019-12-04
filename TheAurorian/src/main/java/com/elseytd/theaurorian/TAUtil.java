package com.elseytd.theaurorian;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.oredict.OreDictionary;

public class TAUtil {

	public static final DamageSource LIGHTNING = (new DamageSource("lightning"));

	/**
	 * Contains all ores and modded ores in the ore dictionary, populated
	 * postInit
	 */
	public static List<ItemStack> Ores = null;

	public static List<ItemStack> populateOrelocallist() {
		String[] names = OreDictionary.getOreNames();
		List<ItemStack> ores = new ArrayList<ItemStack>();
		for (String s : names) {
			if (s.startsWith("ore")) {
				ores.addAll(OreDictionary.getOres(s));
			}
		}
		return ores;
	}

	public static boolean isOre(ItemStack itemIn) {
		for (ItemStack i : TAUtil.Ores) {
			if (ItemStack.areItemsEqual(i, itemIn)) {
				return true;
			}
		}
		return false;
	}

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

	/**
	 * Finds all chests in the chunk and applies the given loot table to them.
	 * 
	 * @param c                Chunk.
	 * @param rand             Random.
	 * @param loot             Chests Loot.
	 * @param ignorechestempty If the chests should be given loot even if they
	 *                         are not empty.
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
	 * @param worldIn
	 * @param pos               Position of the chunk.
	 * @param maximumDifference Maximum number of blocks to allow between the
	 *                          highest block and lowest block in the chunk.
	 *                          Lower values mean flatter terrain.
	 * @return
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

	/**
	 * Will return true percent time specified. Example: 0.75F will return true
	 * 75% of the time.
	 */
	public static boolean randomChanceOf(float percent) {
		Random r = new Random();
		//(0 - 99) +1 = (1 - 100)
		float gen = (float) r.nextInt(100) + 1F;
		if (gen <= (percent * 100F)) {
			return true;
		}
		return false;
	}

	/**
	 * Called whenever Moonstone tools take damage.
	 */
	public static void handleMoonstoneAbility(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if (entityLiving.dimension == TAConfig.Config_AurorianDimId || (entityLiving.dimension == 0 && !entityLiving.world.isDaytime())) {
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
