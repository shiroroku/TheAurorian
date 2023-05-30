package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import com.shiroroku.theaurorian.AurorianUtil;
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

public class SpectralItemArmor extends ItemArmor {

	public SpectralItemArmor(EntityEquipmentSlot equipmentSlotIn, String name) {
		super(ItemRegistry.Materials.SPECTRAL_ARMOR, 0, equipmentSlotIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(AurorianMod.MODID + "." + name);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setMaxStackSize(1);
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entityIn, EntityEquipmentSlot slot, String layer) {
		if (slot != EntityEquipmentSlot.LEGS) {
			return AurorianMod.MODID + ":textures/armor/spectralarmorlayer1.png";
		} else {
			return AurorianMod.MODID + ":textures/armor/spectralarmorlayer2.png";
		}
	}

	//TODO Replace tooltips with variables with format like this
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format("string.theaurorian.tooltip.spectralarmor", AurorianConfig.Config_Spectral_Armor_CleanseChance * 100 + "%"));
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
					if (armorpart.getArmorMaterial() == ItemRegistry.Materials.SPECTRAL_ARMOR) {
						chance += AurorianConfig.Config_Spectral_Armor_CleanseChance;
					}
				}
			}
			if (chance != 0.00f) {
				if (AurorianUtil.randomChanceOf(chance)) {
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
