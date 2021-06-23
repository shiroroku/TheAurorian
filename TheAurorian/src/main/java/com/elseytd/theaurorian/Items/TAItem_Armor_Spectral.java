package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TAUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TAItem_Armor_Spectral extends ItemArmor {

	public TAItem_Armor_Spectral(EntityEquipmentSlot equipmentSlotIn, String name) {
		super(TAItems.Materials.SPECTRAL_ARMOR, 0, equipmentSlotIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(TAMod.MODID + "." + name);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setMaxStackSize(1);
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entityIn, EntityEquipmentSlot slot, String layer) {
		if (slot != EntityEquipmentSlot.LEGS) {
			return TAMod.MODID + ":textures/armor/spectralarmorlayer1.png";
		} else {
			return TAMod.MODID + ":textures/armor/spectralarmorlayer2.png";
		}
	}

	//TODO Replace tooltips with variables with format like this
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format("string.theaurorian.tooltip.spectralarmor", TAConfig.Config_Spectral_Armor_CleanseChance * 100 + "%"));
		}
	}

	@Override
	public net.minecraftforge.common.IRarity getForgeRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	public static void handleDamageEvent(LivingDamageEvent e) {
		if (e.getSource() instanceof EntityDamageSource) {
			EntityDamageSource source = (EntityDamageSource) e.getSource();
			if (!(source.getTrueSource() instanceof EntityLivingBase)) {
				return;
			}

			EntityLivingBase wearer = (EntityLivingBase) source.getTrueSource();

			if (wearer == null) {
				return;
			}

			float chance = 0.00f;
			for (ItemStack piece : wearer.getArmorInventoryList()) {
				if (piece.getItem() instanceof ItemArmor) {
					ItemArmor armorpart = (ItemArmor) piece.getItem();
					if (armorpart.getArmorMaterial() == TAItems.Materials.SPECTRAL_ARMOR) {
						chance += TAConfig.Config_Spectral_Armor_CleanseChance;
					}
				}
			}
			if (chance != 0.00f) {
				if (TAUtil.randomChanceOf(chance)) {
					List<Potion> remove = new ArrayList<>();
					for (PotionEffect p : wearer.getActivePotionEffects()) {
						if (p.getPotion().isBadEffect()) {
							remove.add(p.getPotion());
						}
					}
					for (Potion r : remove) {
						wearer.removePotionEffect(r);
					}
				}
			}
		}
	}

}
