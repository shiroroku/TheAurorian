package com.elseytd.theaurorian.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Armor_SlimeBoots extends ItemArmor {

	public TAItem_Armor_SlimeBoots(EntityEquipmentSlot equipmentSlotIn, String name) {
		super(TAItems.Materials.AURORIAN_SLIME, 0, equipmentSlotIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(TAMod.MODID + "." + name);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setMaxStackSize(1);
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entityIn, EntityEquipmentSlot slot, String layer) {
		return TAMod.MODID + ":textures/armor/aurorianslimeboots.png";
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public net.minecraftforge.common.IRarity getForgeRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format("string.theaurorian.tooltip.aurorianslimeboots"));
		}
	}

	public static void handleFallEvent(LivingFallEvent e) {
		if (e.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e.getEntity();
			for (ItemStack s : player.getArmorInventoryList()) {
				if (s.getItem() instanceof TAItem_Armor_SlimeBoots) {
					if (e.getDistance() > 3f) {
						player.addVelocity(0, 0.75, 0);
						player.velocityChanged = true;
						if (player.getEntityWorld().isRemote) {
							player.playSound(SoundEvents.ENTITY_SLIME_SQUISH, 1f, 1f);
						}
						e.setCanceled(true);
					}
					return;
				}
			}
		}
	}

	public static void handleJumpEvent(LivingJumpEvent e) {
		if (e.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e.getEntity();
			if (player.isSneaking() && player.onGround && !player.getCooldownTracker().hasCooldown(TAItems.aurorianslimeboots)) {
				for (ItemStack s : player.getArmorInventoryList()) {
					if (s.getItem() instanceof TAItem_Armor_SlimeBoots) {
						player.addVelocity(0, 1.25D, 0);
						player.velocityChanged = true;
						if (player.getEntityWorld().isRemote) {
							player.playSound(SoundEvents.ENTITY_SLIME_JUMP, 1f, 1f);
						}
						player.getCooldownTracker().setCooldown(TAItems.aurorianslimeboots, TAConfig.Config_SlimeBootsCooldown);
						return;
					}
				}
			}
		}
	}
}
