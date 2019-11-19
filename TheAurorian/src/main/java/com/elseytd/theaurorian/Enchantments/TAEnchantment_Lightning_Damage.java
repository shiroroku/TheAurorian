package com.elseytd.theaurorian.Enchantments;

import java.util.Map;

import com.elseytd.theaurorian.TAEnchantments;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TAUtil;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class TAEnchantment_Lightning_Damage extends Enchantment {

	private static final String NAME = "lightning";
	private static final int LEVEL_COST_MIN = 5;
	private static final int LEVEL_COST = 15;
	private static final int LEVEL_MAX = 20;
	private static final int TIERS_MAX = 4;

	public TAEnchantment_Lightning_Damage() {
		super(Enchantment.Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND });
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
		return !(ench instanceof TAEnchantment_Lightning_Damage);
	}

	@Override
	public boolean canApply(ItemStack stack) {
		return stack.getItem() instanceof ItemAxe ? true : super.canApply(stack);
	}

	/**
	 * Called whenever a mob is damaged with an item that has this enchantment
	 * on it.
	 */
	@Override
	public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
		if (target instanceof EntityLivingBase) {
			EntityLivingBase entitylivingbase = (EntityLivingBase) target;

			//if the target is wearing armor, and is not leather, scale up the damage per armor piece
			int armorconductivecount = 0;
			for (ItemStack piece : entitylivingbase.getArmorInventoryList()) {
				if (piece.getItem() instanceof ItemArmor) {
					boolean hasresist = false;
					Map<Enchantment, Integer> e = EnchantmentHelper.getEnchantments(piece);
					if (e.get(TAEnchantments.lightningresistance) != null) {
						hasresist = true;
					}

					ItemArmor armorpart = (ItemArmor) piece.getItem();
					if (armorpart.getArmorMaterial() != ItemArmor.ArmorMaterial.LEATHER && !hasresist) {
						if (armorconductivecount < level) {
							armorconductivecount++;
						}
					}
				}
			}

			int time = entitylivingbase.hurtResistantTime;
			entitylivingbase.hurtResistantTime = 0;
			float resistscale = (time / 20);
			
			float damagescale = 0.25F * resistscale;
			float extradamage = armorconductivecount * damagescale;

			entitylivingbase.attackEntityFrom(TAUtil.LIGHTNING, extradamage + extradamage);
		
			/*
			 * if (entitylivingbase.getCreatureAttribute() ==
			 * EnumCreatureAttribute.ARTHROPOD) { int i = 20 +
			 * user.getRNG().nextInt(10 * level);
			 * entitylivingbase.addPotionEffect(new
			 * PotionEffect(MobEffects.SLOWNESS, i, 3)); }
			 */
		}
	}

}
