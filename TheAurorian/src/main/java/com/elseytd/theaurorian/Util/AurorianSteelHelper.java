package com.elseytd.theaurorian.Util;

import java.util.Map;

import com.elseytd.theaurorian.TAConfig;

import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AurorianSteelHelper {

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