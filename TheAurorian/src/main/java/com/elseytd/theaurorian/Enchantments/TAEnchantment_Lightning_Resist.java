package com.elseytd.theaurorian.Enchantments;

import java.util.Map;

import com.elseytd.theaurorian.TAEnchantments;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class TAEnchantment_Lightning_Resist extends Enchantment {

	private static final String NAME = "lightningresistance";
	private static final int LEVEL_COST_MIN = 10;
	private static final int LEVEL_COST = 15;
	private static final int LEVEL_MAX = 30;
	private static final int TIERS_MAX = 1;

	public TAEnchantment_Lightning_Resist() {
		super(Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[] {
					EntityEquipmentSlot.HEAD,
					EntityEquipmentSlot.CHEST,
					EntityEquipmentSlot.LEGS,
					EntityEquipmentSlot.FEET });
		this.setRegistryName(TAMod.MODID, NAME);
		this.setName(TAMod.MODID + "." + NAME);
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return LEVEL_COST_MIN + (enchantmentLevel - 1) * LEVEL_COST;
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return this.getMinEnchantability(enchantmentLevel) + LEVEL_MAX;
	}

	@Override
	public int getMaxLevel() {
		return TIERS_MAX;
	}

	@Override
	public boolean canApplyTogether(Enchantment ench) {
		return !(ench instanceof TAEnchantment_Lightning_Resist);
	}

	@Override
	public boolean canApply(ItemStack stack) {
		return stack.getItem() instanceof ItemArmor ? true : super.canApply(stack);
	}

	public static void handleDamageEvent(LivingDamageEvent e) {
		if (e.getSource() == DamageSource.LIGHTNING_BOLT) {
			EntityLivingBase target = e.getEntityLiving();
			if (target == null) {
				return;
			} else {
				boolean hasresist = false;
				for (ItemStack piece : target.getArmorInventoryList()) {
					if (piece.getItem() instanceof ItemArmor) {
						Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(piece);
						if (enchants.get(TAEnchantments.lightningresistance) != null) {
							hasresist = true;
							break;
						}
					}
				}
				if (hasresist) {
					e.setAmount(0);
					e.setCanceled(true);
				}
			}
		}
	}
}
