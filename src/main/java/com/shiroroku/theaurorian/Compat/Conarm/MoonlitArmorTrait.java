package com.shiroroku.theaurorian.Compat.Conarm;

import c4.conarm.lib.traits.AbstractArmorTrait;
import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.AurorianUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;

public class MoonlitArmorTrait extends AbstractArmorTrait {
	public MoonlitArmorTrait() {
		super("conarmmoonlit", TextFormatting.GRAY);
	}

	@Override
	public int onArmorDamage(ItemStack armor, DamageSource source, int damage, int newDamage, EntityPlayer player, int slot) {
		if (player.dimension == AurorianConfig.Config_AurorianDimID || (player.dimension == 0 && !player.world.isDaytime())) {
			if (AurorianUtil.randomChanceOf(0.50F)) {
				newDamage = 0;
			} else {
				newDamage = damage;
			}
		} else if (player.world.isDaytime()) {
			if (AurorianUtil.randomChanceOf(0.50F)) {
				newDamage = 2;
			} else {
				newDamage = damage;
			}
		}
		return newDamage;
	}

}
