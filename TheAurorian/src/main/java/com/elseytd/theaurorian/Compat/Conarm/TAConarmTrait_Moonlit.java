package com.elseytd.theaurorian.Compat.Conarm;

import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAUtil;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;

public class TAConarmTrait_Moonlit extends AbstractArmorTrait {
	public TAConarmTrait_Moonlit() {
		super("conarmmoonlit", TextFormatting.GRAY);
	}

	@Override
	public int onArmorDamage(ItemStack armor, DamageSource source, int damage, int newDamage, EntityPlayer player, int slot) {
		if (player.dimension == TAConfig.Config_AurorianDimID || (player.dimension == 0 && !player.world.isDaytime())) {
			if (TAUtil.randomChanceOf(0.50F)) {
				newDamage = 0;
			} else {
				newDamage = damage;
			}
		} else if (player.world.isDaytime()) {
			if (TAUtil.randomChanceOf(0.50F)) {
				newDamage = 2;
			} else {
				newDamage = damage;
			}
		}
		return newDamage;
	}

}
