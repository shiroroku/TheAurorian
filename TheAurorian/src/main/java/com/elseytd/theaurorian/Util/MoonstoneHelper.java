package com.elseytd.theaurorian.Util;

import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAUtil;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MoonstoneHelper {

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