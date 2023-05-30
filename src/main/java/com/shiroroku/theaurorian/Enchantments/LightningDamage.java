package com.shiroroku.theaurorian.Enchantments;

import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.Registry.EnchantmentRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

import java.util.Map;

public class LightningDamage extends Enchantment {

	private static final String NAME = "lightning";
	private static final int LEVEL_COST_MIN = 5;
	private static final int LEVEL_COST = 15;
	private static final int LEVEL_MAX = 20;
	private static final int TIERS_MAX = 4;

	public LightningDamage() {
		super(Enchantment.Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND });
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
		return !(ench instanceof LightningDamage);
	}

	@Override
	public boolean canApply(ItemStack stack) {
		return stack.getItem() instanceof ItemAxe || super.canApply(stack);
	}

	public static void handleDamageEvent(LivingDamageEvent e) {
		if (e.getSource() instanceof EntityDamageSource) {
			EntityDamageSource source = (EntityDamageSource) e.getSource();
			if (!(source.getTrueSource() instanceof EntityLivingBase)) {
				return;
			}
			EntityLivingBase attacker = (EntityLivingBase) source.getTrueSource();
			EntityLivingBase target = e.getEntityLiving();

			if (attacker == null || target == null) {
				return;
			}

			ItemStack attackersword = attacker.getHeldItemMainhand();
			Map<Enchantment, Integer> swordenchants = EnchantmentHelper.getEnchantments(attackersword);
			if (swordenchants.get(EnchantmentRegistry.lightning) != null) {
				int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistry.lightning, attackersword);
				float extradamage = 0;
				for (ItemStack piece : target.getArmorInventoryList()) {
					if (piece.getItem() instanceof ItemArmor) {
						boolean hasresist = false;
						Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(piece);
						if (enchants.get(EnchantmentRegistry.lightningresistance) != null) {
							hasresist = true;
						}

						ItemArmor armorpart = (ItemArmor) piece.getItem();
						if (armorpart.getArmorMaterial() != ItemArmor.ArmorMaterial.LEATHER && !hasresist) {
							if (extradamage <= level) {
								extradamage++;
							}
						}
					}
				}
				e.setAmount(e.getAmount() + (extradamage * AurorianConfig.Config_LightningEnchantmentMulitplier));
			}
		}
	}

}
