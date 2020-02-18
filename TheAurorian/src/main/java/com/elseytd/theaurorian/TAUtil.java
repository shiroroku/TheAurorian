package com.elseytd.theaurorian;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.elseytd.theaurorian.World.Structures.TAWorldGenerator_MoonTemple;
import com.elseytd.theaurorian.World.Structures.TAWorldGenerator_Runestone_Tower;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class TAUtil {

	/**
	 * Will return true a given percentage of the time. Example: 0.75D will
	 * return true 75% of the time.
	 */
	public static boolean randomChanceOf(double percent) {
		Random r = new Random();
		double gen = (double) r.nextDouble();
		if (gen <= percent) {
			return true;
		}
		return false;
	}

	public static class Entity {

		/**
		 * Returns true when the looker is looking at the target.
		 * 
		 * @param accuracy How close does the looker have to look at the target
		 */
		public static boolean isLookingAt(EntityLivingBase looker, EntityLivingBase target, double accuracy) {
			Vec3d lookvec = target.getLook(1.0F).normalize();
			Vec3d vec = new Vec3d(looker.posX - target.posX, looker.getEntityBoundingBox().minY + (double) looker.getEyeHeight() - (target.posY + (double) target.getEyeHeight()), looker.posZ - target.posZ);
			double leng = vec.lengthVector();
			vec = vec.normalize();
			double mult = lookvec.dotProduct(vec);
			return mult > 1.0D - accuracy / leng ? target.canEntityBeSeen(looker) : false;
		}

		public static List<EntityLivingBase> getEntitiesAround(World worldIn, double x, double y, double z, double distance, boolean debugRender) {
			return getEntitiesAround(worldIn, x, y, z, distance, distance, debugRender);
		}

		public static List<EntityLivingBase> getEntitiesAround(World worldIn, double x, double y, double z, double distance, double height, boolean debugRender) {
			AxisAlignedBB aabb = new AxisAlignedBB(x - distance, y - height, z - distance, x + distance, y + height, z + distance);
			if (debugRender) {
				TAUtil.Debugging.renderAABBBounds(worldIn, aabb);
			}
			return worldIn.getEntitiesWithinAABB(EntityLivingBase.class, aabb);
		}
	}

	public static class Debugging {

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

		public static boolean isNearRunestoneDungeon(World worldIn, BlockPos pos, int range) {
			int chunkX = worldIn.getChunkFromBlockCoords(pos).x;
			int chunkZ = worldIn.getChunkFromBlockCoords(pos).z;
			int searchdistance = TAConfig.Config_DungeonDensity * 2;
			if (worldIn.provider.getDimension() == TAConfig.Config_AurorianDimID) {
				for (int x = -(searchdistance / 2); x < (searchdistance / 2); x++) {
					for (int z = -(searchdistance / 2); z < (searchdistance / 2); z++) {
						if (TAWorldGenerator_Runestone_Tower.isValidChunkForGen(chunkX + x, chunkZ + z, 0, 0)) {
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

		public static boolean isNearMoonTemple(World worldIn, BlockPos pos, int range) {
			int chunkX = worldIn.getChunkFromBlockCoords(pos).x;
			int chunkZ = worldIn.getChunkFromBlockCoords(pos).z;
			int searchdistance = TAConfig.Config_DungeonDensity * 4;
			if (worldIn.provider.getDimension() == TAConfig.Config_AurorianDimID) {
				for (int x = -(searchdistance / 2); x < (searchdistance / 2); x++) {
					for (int z = -(searchdistance / 2); z < (searchdistance / 2); z++) {
						if (TAWorldGenerator_MoonTemple.isValidChunkForGen(chunkX + x, chunkZ + z, 0, 0)) {
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

		public static ChunkPos getNearestMoonTemple(EntityPlayer player) {
			int playerchunkX = player.chunkCoordX;
			int playerchunkZ = player.chunkCoordZ;
			int distance = TAConfig.Config_DungeonDensity * 4;
			ChunkPos closest = null;
			int closestdist = 0;
			if (player.dimension == TAConfig.Config_AurorianDimID) {
				for (int x = -(distance / 2); x < (distance / 2); x++) {
					for (int z = -(distance / 2); z < (distance / 2); z++) {
						if (TAWorldGenerator_MoonTemple.isValidChunkForGen(playerchunkX + x, playerchunkZ + z, 0, 0)) {
							int blocksaway = (int) player.getDistance((playerchunkX + x) * 16, player.posY, (playerchunkZ + z) * 16);
							if (closest == null) {
								closest = new ChunkPos(playerchunkX + x, playerchunkZ + z);
								closestdist = blocksaway;
							} else if (blocksaway < closestdist){
								closest = new ChunkPos(playerchunkX + x, playerchunkZ + z);
								closestdist = blocksaway;
							}
						}
					}
				}
			}

			return closest;
		}
		
		public static ChunkPos getNearestRunestoneDungeon(EntityPlayer player) {
			int playerchunkX = player.chunkCoordX;
			int playerchunkZ = player.chunkCoordZ;
			int distance = TAConfig.Config_DungeonDensity * 2;
			ChunkPos closest = null;
			int closestdist = 0;
			if (player.dimension == TAConfig.Config_AurorianDimID) {
				for (int x = -(distance / 2); x < (distance / 2); x++) {
					for (int z = -(distance / 2); z < (distance / 2); z++) {
						if (TAWorldGenerator_Runestone_Tower.isValidChunkForGen(playerchunkX + x, playerchunkZ + z, 0, 0)) {
							int blocksaway = (int) player.getDistance((playerchunkX + x) * 16, player.posY, (playerchunkZ + z) * 16);
							if (closest == null) {
								closest = new ChunkPos(playerchunkX + x, playerchunkZ + z);
								closestdist = blocksaway;
							} else if (blocksaway < closestdist){
								closest = new ChunkPos(playerchunkX + x, playerchunkZ + z);
								closestdist = blocksaway;
							}
						}
					}
				}
			}

			return closest;
		}

		public static void listNearbyRunestoneDungeon(EntityPlayer player, BlockPos pos) {
			if (player.world.isRemote) {
				int playerchunkX = player.chunkCoordX;
				int playerchunkZ = player.chunkCoordZ;
				int distance = TAConfig.Config_DungeonDensity * 2;
				if (player.dimension == TAConfig.Config_AurorianDimID) {
					player.sendMessage(new TextComponentString(TextFormatting.AQUA + "Nearby Runestone Dungeons:"));
					for (int x = -(distance / 2); x < (distance / 2); x++) {
						for (int z = -(distance / 2); z < (distance / 2); z++) {
							if (TAWorldGenerator_Runestone_Tower.isValidChunkForGen(playerchunkX + x, playerchunkZ + z, 0, 0)) {
								int blocksaway = (int) player.getDistance((playerchunkX + x) * 16, player.posY, (playerchunkZ + z) * 16);
								TextFormatting color = TextFormatting.GRAY;
								int scale = 128;
								if (blocksaway <= scale) {
									color = TextFormatting.GREEN;
								} else if (blocksaway > scale && blocksaway <= scale * 2) {
									color = TextFormatting.YELLOW;
								} else if (blocksaway > scale * 2 && blocksaway <= scale * 4) {
									color = TextFormatting.RED;
								} else if (blocksaway > scale * 4) {
									color = TextFormatting.DARK_RED;
								}
								player.sendMessage(new TextComponentString(TextFormatting.GRAY + "[" + (playerchunkX + x) * 16 + "," + (playerchunkZ + z) * 16 + "] Blocks away: " + color + blocksaway));
							}
						}
					}
				} else {
					player.sendMessage(new TextComponentString("Runestone Dungeons are only in The Aurorian!"));
				}
			}
		}

		public static void listNearbyMoonTemples(EntityPlayer player, BlockPos pos) {
			if (player.world.isRemote) {
				int playerchunkX = player.chunkCoordX;
				int playerchunkZ = player.chunkCoordZ;
				int distance = TAConfig.Config_DungeonDensity * 4;
				if (player.dimension == TAConfig.Config_AurorianDimID) {
					player.sendMessage(new TextComponentString(TextFormatting.AQUA + "Nearby Moon Temples:"));
					for (int x = -(distance / 2); x < (distance / 2); x++) {
						for (int z = -(distance / 2); z < (distance / 2); z++) {
							if (TAWorldGenerator_MoonTemple.isValidChunkForGen(playerchunkX + x, playerchunkZ + z, 0, 0)) {
								int blocksaway = (int) player.getDistance((playerchunkX + x) * 16, player.posY, (playerchunkZ + z) * 16);
								TextFormatting color = TextFormatting.GRAY;
								int scale = 128 * 2;
								if (blocksaway <= scale) {
									color = TextFormatting.GREEN;
								} else if (blocksaway > scale && blocksaway <= scale * 2) {
									color = TextFormatting.YELLOW;
								} else if (blocksaway > scale * 2 && blocksaway <= scale * 4) {
									color = TextFormatting.RED;
								} else if (blocksaway > scale * 4) {
									color = TextFormatting.DARK_RED;
								}
								player.sendMessage(new TextComponentString(TextFormatting.GRAY + "[" + (playerchunkX + x) * 16 + "," + (playerchunkZ + z) * 16 + "] Blocks away: " + color + blocksaway));
							}
						}
					}
				} else {
					player.sendMessage(new TextComponentString("Moon Temples are only in The Aurorian!"));
				}
			}
		}

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
