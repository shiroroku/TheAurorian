package com.shiroroku.theaurorian.Compat.TinkersConstruct;

import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.AurorianUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class MoonlitTinkersTrait extends AbstractTrait {

	public MoonlitTinkersTrait() {
		super("tamoonlit", TextFormatting.GRAY);
	}

	@Override
	public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
		if (entity.dimension == AurorianConfig.Config_AurorianDimID || (entity.dimension == 0 && !entity.world.isDaytime())) {
			if (AurorianUtil.randomChanceOf(0.50F)) {
				newDamage = 0;
			} else {
				newDamage = damage;
			}
		} else if (entity.world.isDaytime()) {
			if (AurorianUtil.randomChanceOf(0.50F)) {
				newDamage = 2;
			} else {
				newDamage = damage;
			}
		}
		return newDamage;
	}

}
