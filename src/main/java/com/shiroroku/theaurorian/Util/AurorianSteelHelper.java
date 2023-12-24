package com.shiroroku.theaurorian.Util;

import com.shiroroku.theaurorian.AurorianConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class AurorianSteelHelper {

	public static int maxlevelbase = AurorianConfig.Config_AurorianSteel_BaseMaxLevel;
	public static float maxlevelmultiplier = AurorianConfig.Config_AurorianSteel_BaseMaxLevelMultiplier;

	/**
	 * Tooltip for all Aurorian Steel tools.
	 */
	@SideOnly(Side.CLIENT)
	public static String getAurorianSteelTooltip() {
		return I18n.format("string.theaurorian.tooltip.auroriansteeltools");
	}

	@SideOnly(Side.CLIENT)
	public static void getAurorianSteelInfo(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		boolean canlevelup = hasEnchantToLevelUp(stack, worldIn);
		if (!GuiScreen.isShiftKeyDown()) {
			if (!canlevelup) {
				tooltip.add(TextFormatting.LIGHT_PURPLE + I18n.format("string.theaurorian.tooltip.auroriansteeltools.noenchants") + TextFormatting.RESET);
			} else {
				tooltip.add(TextFormatting.LIGHT_PURPLE + I18n.format("string.theaurorian.tooltip.auroriansteeltools.level") + " [" + AurorianSteelHelper.getLevel(stack) + "/" + Math.round(AurorianSteelHelper.maxlevelbase * AurorianSteelHelper.getMultiplier(stack)) + "]" + TextFormatting.RESET);
			}
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			if (canlevelup) {
				String nextenchant = getNextEnchantment(stack, worldIn);
				if (nextenchant != null) {
					tooltip.add(TextFormatting.LIGHT_PURPLE + I18n.format("string.theaurorian.tooltip.auroriansteeltools.nextenchant") + nextenchant + TextFormatting.RESET);
				}
			}
			tooltip.add(getAurorianSteelTooltip());
		}
	}

	/**
	 * Returns the display name of the next enchantment it will level up.
	 */
	public static String getNextEnchantment(ItemStack stack, World worldIn) {
		if (stack.isItemEnchanted()) {
			Map<Enchantment, Integer> enchs = EnchantmentHelper.getEnchantments(stack);
			for (Map.Entry<Enchantment, Integer> e : enchs.entrySet()) {
				if (e.getKey().getMaxLevel() > 1 && e.getValue() < e.getKey().getMaxLevel()) {
					switch (AurorianConfig.Config_AurorianSteel_Enchants_WhitelistBlacklist) {
						case 0:
						default:
							return e.getKey().getTranslatedName(e.getValue() + 1);
						case 1:
							for (String enchreg : AurorianConfig.Config_AurorianSteel_Enchants) {
								if (enchreg.equals(e.getKey().getRegistryName().toString()) || e.getKey().getRegistryName().getResourceDomain().equals(enchreg)) {
									return e.getKey().getTranslatedName(e.getValue() + 1);
								}
							}
							break;
						case 2:
							for (String enchreg : AurorianConfig.Config_AurorianSteel_Enchants) {
								if (enchreg.equals(e.getKey().getRegistryName().toString()) || e.getKey().getRegistryName().getResourceDomain().equals(enchreg)) {
									return null;
								}
							}
							return e.getKey().getTranslatedName(e.getValue() + 1);
					}
				}
			}
		}
		return null;
	}

	/**
	 * Checks the itemstack and config to see if the item has enchantments to
	 * level up.
	 */
	public static boolean hasEnchantToLevelUp(ItemStack stack, World worldIn) {
		if (stack.isItemEnchanted()) {
			Map<Enchantment, Integer> enchs = EnchantmentHelper.getEnchantments(stack);
			for (Map.Entry<Enchantment, Integer> e : enchs.entrySet()) {
				if (e.getKey().getMaxLevel() > 1 && e.getValue() < e.getKey().getMaxLevel()) {
					switch (AurorianConfig.Config_AurorianSteel_Enchants_WhitelistBlacklist) {
						case 0:
						default:
							return true;
						case 1:
							for (String enchreg : AurorianConfig.Config_AurorianSteel_Enchants) {
								if (enchreg.equals(e.getKey().getRegistryName().toString()) || e.getKey().getRegistryName().getResourceDomain().equals(enchreg)) {
									return true;
								}
							}
							break;
						case 2:
							for (String enchreg : AurorianConfig.Config_AurorianSteel_Enchants) {
								if (enchreg.equals(e.getKey().getRegistryName().toString()) || e.getKey().getRegistryName().getResourceDomain().equals(enchreg)) {
									return false;
								}
							}
							return true;
					}
				}
			}
		}
		return false;
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
						switch (AurorianConfig.Config_AurorianSteel_Enchants_WhitelistBlacklist) {
							case 0:
							default:
								enchs.put(e.getKey(), e.getValue() + 1);
								EnchantmentHelper.setEnchantments(enchs, stack);
								setMultiplier(stack, levelmultiplier * maxlevelmultiplier);
								setLevel(stack, 0);
								return;
							case 1:
								for (String enchreg : AurorianConfig.Config_AurorianSteel_Enchants) {
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
								for (String enchreg : AurorianConfig.Config_AurorianSteel_Enchants) {
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