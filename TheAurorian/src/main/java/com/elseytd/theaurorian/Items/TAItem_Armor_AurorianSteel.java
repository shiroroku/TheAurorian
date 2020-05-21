package com.elseytd.theaurorian.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TAUtil;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Armor_AurorianSteel extends ItemArmor {

	public TAItem_Armor_AurorianSteel(EntityEquipmentSlot equipmentSlotIn, String name) {
		super(TAItems.Materials.AURORIANSTEEL_ARMOR, 0, equipmentSlotIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(TAMod.MODID + "." + name);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setMaxStackSize(1);
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entityIn, EntityEquipmentSlot slot, String layer) {
		if (slot != EntityEquipmentSlot.LEGS) {
			return TAMod.MODID + ":textures/armor/auroriansteelarmorlayer1.png";
		} else {
			return TAMod.MODID + ":textures/armor/auroriansteelarmorlayer2.png";
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.LIGHT_PURPLE + I18n.format("string.theaurorian.tooltip.silentwoodpickaxe1") + " [" + TAUtil.AurorianSteel.getLevel(stack) + "/" + Math.round(TAUtil.AurorianSteel.maxlevelbase * TAUtil.AurorianSteel.getMultiplier(stack)) + "]" + TextFormatting.RESET);
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(TAUtil.AurorianSteel.getAurorianSteelTooltip());
		}
	}

	public static void handleDamageEvent(LivingDamageEvent e) {
		EntityLivingBase target = e.getEntityLiving();
		if (target == null) {
			return;
		}
		for (ItemStack piece : target.getArmorInventoryList()) {
			if (piece.getItem() instanceof ItemArmor) {
				ItemArmor armorpart = (ItemArmor) piece.getItem();
				if (armorpart.getArmorMaterial() == TAItems.Materials.AURORIANSTEEL_ARMOR) {
					TAUtil.AurorianSteel.handleAurorianSteelDurability(piece, target.world, target);
				}
			}
		}
	}
}
