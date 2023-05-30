package com.shiroroku.theaurorian.Util;

import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.AurorianUtil;
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
		if (entityLiving.dimension == AurorianConfig.Config_AurorianDimID || (entityLiving.dimension == 0 && !entityLiving.world.isDaytime())) {
			if (AurorianUtil.randomChanceOf(0.50F)) {
				stack.damageItem(1, entityLiving);
			}
		} else {
			if (AurorianUtil.randomChanceOf(0.50F)) {
				stack.damageItem(1, entityLiving);
			}
			stack.damageItem(1, entityLiving);
		}
	}
}