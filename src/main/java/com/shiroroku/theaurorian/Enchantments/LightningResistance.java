package com.shiroroku.theaurorian.Enchantments;

import com.shiroroku.theaurorian.Registry.EnchantmentRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

import java.util.Map;

public class LightningResistance extends Enchantment {

	private static final String NAME = "lightningresistance";
	private static final int LEVEL_COST_MIN = 10;
	private static final int LEVEL_COST = 15;
	private static final int LEVEL_MAX = 30;
	private static final int TIERS_MAX = 1;

	public LightningResistance() {
		super(Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[] {
					EntityEquipmentSlot.HEAD,
					EntityEquipmentSlot.CHEST,
					EntityEquipmentSlot.LEGS,
					EntityEquipmentSlot.FEET });
		this.setRegistryName(AurorianMod.MODID, NAME);
		this.setName(AurorianMod.MODID + "." + NAME);
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
		return !(ench instanceof LightningResistance);
	}

	@Override
	public boolean canApply(ItemStack stack) {
		return stack.getItem() instanceof ItemArmor || super.canApply(stack);
	}

	public static void handleDamageEvent(LivingDamageEvent e) {
		if (e.getSource() == DamageSource.LIGHTNING_BOLT) {
			EntityLivingBase target = e.getEntityLiving();
			if (target == null) {
			} else {
				boolean hasresist = false;
				for (ItemStack piece : target.getArmorInventoryList()) {
					if (piece.getItem() instanceof ItemArmor) {
						Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(piece);
						if (enchants.get(EnchantmentRegistry.lightningresistance) != null) {
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
