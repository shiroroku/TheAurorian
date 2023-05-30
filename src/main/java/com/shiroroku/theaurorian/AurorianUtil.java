package com.shiroroku.theaurorian;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;

import java.util.*;
import java.util.Map.Entry;

public class AurorianUtil {

	/**
	 * Will return true a given percentage of the time. Example: 0.75D will
	 * return true 75% of the time.
	 */
	public static boolean randomChanceOf(double percent) {
		Random r = new Random();
		double gen = r.nextDouble();
		return gen <= percent;
	}

	/**
	 * Draws some particles to show where an aabb is.
	 */
	public static void renderAABBBounds(World worldIn, AxisAlignedBB aabb) {
		if (worldIn.isRemote) {
			for (double ix = aabb.minX; ix <= aabb.maxX; ix++) {
				for (double iy = aabb.minY; iy <= aabb.maxY; iy++) {
					for (double iz = aabb.minZ; iz <= aabb.maxZ; iz++) {
						EnumParticleTypes particle = EnumParticleTypes.CLOUD;
						if (ix == aabb.minX || ix == aabb.maxX) {
							worldIn.spawnParticle(particle, ix, iy, iz, 0, 0, 0);
						}
						if (iy == aabb.minY || iy == aabb.maxY) {
							worldIn.spawnParticle(particle, ix, iy, iz, 0, 0, 0);
						}
						if (iz == aabb.minZ || iz == aabb.maxZ) {
							worldIn.spawnParticle(particle, ix, iy, iz, 0, 0, 0);
						}
					}
				}
			}
		}
	}

	/**
	 * Generates and prints loot tables to the console.
	 *
	 * @param world     World Object
	 * @param lootTable Loot Table
	 * @param rolls     Rolls/Chests to simulate
	 */
	public static void simulateLootTable(World world, ResourceLocation lootTable, int rolls) {
		if (!world.isRemote) {
			Map<Item, Integer> allItems = new TreeMap<>(new Comparator<Item>() {
				@Override
				public int compare(Item i1, Item i2) {
					return new ItemStack(i1).getDisplayName().compareTo(new ItemStack(i2).getDisplayName());
				}
			});

			LootTable table = world.getLootTableManager().getLootTableFromLocation(lootTable);
			LootContext ctx = new LootContext.Builder((WorldServer) world).build();

			for (int c = 1; c <= rolls; c++) {
				List<ItemStack> stacks = table.generateLootForPools(world.rand, ctx);

				for (ItemStack item : stacks) {
					if (allItems.containsKey(item.getItem())) {
						allItems.put(item.getItem(), allItems.get(item.getItem()) + item.getCount());
					} else {
						allItems.put(item.getItem(), item.getCount());
					}

				}
			}

			System.out.println("==Loot Simulation:== (Rolls: " + rolls + ")");
			for (Entry<Item, Integer> item : allItems.entrySet()) {
				System.out.printf("%-30s%d%n", new ItemStack(item.getKey()).getDisplayName(), item.getValue());
			}
			System.out.println("===============================");
		}
	}
}
