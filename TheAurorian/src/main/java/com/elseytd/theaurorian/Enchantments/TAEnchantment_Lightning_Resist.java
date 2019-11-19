package com.elseytd.theaurorian.Enchantments;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class TAEnchantment_Lightning_Resist extends Enchantment {

	private static final String NAME = "lightningresistance";
	private static final int LEVEL_COST_MIN = 10;
	private static final int LEVEL_COST = 15;
	private static final int LEVEL_MAX = 30;
	private static final int TIERS_MAX = 1;

	public TAEnchantment_Lightning_Resist() {
		super(Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[] { EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET });
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

}
