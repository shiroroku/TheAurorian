package com.elseytd.theaurorian.Compat;

import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAUtil;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TATinkersTrait_Moonlit extends AbstractTrait {

	public TATinkersTrait_Moonlit() {
		super("tamoonlit", TextFormatting.GRAY);
	}

	@Override
	public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
		if (entity.dimension == TAConfig.Config_AurorianDimId || (entity.dimension == 0 && !entity.world.isDaytime())) {
			if (TAUtil.randomChanceOf(0.50F)) {
				newDamage = 0;
			} else {
				newDamage = damage;
			}
		} else if (entity.world.isDaytime()) {
			if (TAUtil.randomChanceOf(0.50F)) {
				newDamage = 2;
			} else {
				newDamage = damage;
			}
		}
		return newDamage;
	}

}
