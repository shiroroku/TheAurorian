package com.elseytd.theaurorian;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
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

	public static class AurorianSteel {

		public static int maxlevelbase = TAConfig.Config_AurorianSteel_BaseMaxLevel;
		public static float maxlevelmultiplier = TAConfig.Config_AurorianSteel_BaseMaxLevelMultiplier;

		/**
		 * Tooltip for all Aurorian Steel tools.
		 */
		@SideOnly(Side.CLIENT)
		public static String getAurorianSteelTooltip() {
			return I18n.format("string.theaurorian.tooltip.auroriansteeltools");
		}

		/**
		 * Called whenever Aurorian Steel tools take damage.
		 */
		public static void handleAurorianSteelDurability(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
			int itemlevel = getLevel(stack);
			float levelmultiplier = getMultiplier(stack);

			if (itemlevel >= Math.round(maxlevelbase * levelmultiplier) - 1) {
				if (stack.isItemEnchanted()) {
					Map<Enchantment, Integer> enchs = EnchantmentHelper.getEnchantments(stack);
					for (Map.Entry<Enchantment, Integer> e : enchs.entrySet()) {
						if (e.getKey().getMaxLevel() > 1 && e.getValue() < e.getKey().getMaxLevel()) {
							switch (TAConfig.Config_AurorianSteel_Enchants_WhitelistBlacklist) {
							case 0:
							default:
								enchs.put(e.getKey(), e.getValue() + 1);
								EnchantmentHelper.setEnchantments(enchs, stack);
								setMultiplier(stack, levelmultiplier * maxlevelmultiplier);
								setLevel(stack, 0);
								return;
							case 1:
								for (String enchreg : TAConfig.Config_AurorianSteel_Enchants) {
									if (enchreg.equals(e.getKey().getRegistryName().toString()) || e.getKey().getRegistryName().getResourceDomain().equals(enchreg)) {
										enchs.put(e.getKey(), e.getValue() + 1);
										EnchantmentHelper.setEnchantments(enchs, stack);
										setMultiplier(stack, levelmultiplier * maxlevelmultiplier);
										setLevel(stack, 0);
										return;
									}
								}
								break;
							case 2:
								for (String enchreg : TAConfig.Config_AurorianSteel_Enchants) {
									if (enchreg.equals(e.getKey().getRegistryName().toString()) || e.getKey().getRegistryName().getResourceDomain().equals(enchreg)) {
										return;
									}
								}
								enchs.put(e.getKey(), e.getValue() + 1);
								EnchantmentHelper.setEnchantments(enchs, stack);
								setMultiplier(stack, levelmultiplier * maxlevelmultiplier);
								setLevel(stack, 0);
								return;
							}
						}
					}
				}
			} else {
				setLevel(stack, itemlevel + 1);
			}

		}

		public static float getMultiplier(ItemStack stack) {
			checkNbt(stack);
			return stack.getTagCompound().getFloat("upgrademultiplier");
		}

		public static void setMultiplier(ItemStack stack, float amt) {
			NBTTagCompound nbt = checkNbt(stack);
			nbt.setFloat("upgrademultiplier", amt);
		}

		public static int getLevel(ItemStack stack) {
			checkNbt(stack);
			return stack.getTagCompound().getInteger("currentupgradelevel");
		}

		public static void setLevel(ItemStack stack, int amt) {
			NBTTagCompound nbt = checkNbt(stack);
			nbt.setInteger("currentupgradelevel", amt);
		}

		public static NBTTagCompound checkNbt(ItemStack stack) {
			NBTTagCompound nbt;
			if (stack.hasTagCompound()) {
				nbt = stack.getTagCompound();
			} else {
				nbt = new NBTTagCompound();
			}
			if (!nbt.hasKey("currentupgradelevel")) {
				nbt.setInteger("currentupgradelevel", 0);
				stack.setTagCompound(nbt);
			}
			if (!nbt.hasKey("upgrademultiplier")) {
				nbt.setFloat("upgrademultiplier", 1F);
				stack.setTagCompound(nbt);
			}
			return nbt;
		}

	}
}
