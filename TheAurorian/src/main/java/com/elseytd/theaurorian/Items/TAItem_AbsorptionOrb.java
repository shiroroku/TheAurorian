package com.elseytd.theaurorian.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_AbsorptionOrb extends Item {
	public static final String ITEMNAME = "absorptionorb";

	public TAItem_AbsorptionOrb() {
		super();
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
		this.setMaxDamage(TAConfig.Config_OrbOfAbsorptionDurability);
		this.setMaxStackSize(1);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer) entityIn;
			if (p.getHeldItem(EnumHand.OFF_HAND).getItem() == this) {
				ItemStack offhand = p.getHeldItem(EnumHand.OFF_HAND);
				ItemStack mainhand = p.getHeldItem(EnumHand.MAIN_HAND);
				switch (TAConfig.Config_OrbOfAbsorptionWhitelistBlacklist) {
					case 0:
					default:
						if (mainhand.isItemStackDamageable() && mainhand.isItemDamaged()) {
							if (!p.isCreative()) {
								offhand.damageItem(1, p);
							}
							mainhand.setItemDamage(mainhand.getItemDamage() - 1);
							return;
						}
						break;
					case 1:
						for (String i : TAConfig.Config_OrbOfAbsorptionList) {
							if (Item.getByNameOrId(i) == mainhand.getItem()) {
								if (mainhand.isItemStackDamageable() && mainhand.isItemDamaged()) {
									if (!p.isCreative()) {
										offhand.damageItem(1, p);
									}
									mainhand.setItemDamage(mainhand.getItemDamage() - 1);
									return;
								}
							} else if (!i.contains(":")) {
								if (i.equals(mainhand.getItem().getRegistryName().getResourceDomain())) {
									if (mainhand.isItemStackDamageable() && mainhand.isItemDamaged()) {
										if (!p.isCreative()) {
											offhand.damageItem(1, p);
										}
										mainhand.setItemDamage(mainhand.getItemDamage() - 1);
										return;
									}
								}
							}
						}
						break;
					case 2:
						for (String i : TAConfig.Config_OrbOfAbsorptionList) {
							if (Item.getByNameOrId(i) == mainhand.getItem()) {
								return;
							} else if (!i.contains(":")) {
								if (i.equals(mainhand.getItem().getRegistryName().getResourceDomain())) {
									return;
								}
							}
						}
						if (mainhand.isItemStackDamageable() && mainhand.isItemDamaged()) {
							if (!p.isCreative()) {
								offhand.damageItem(1, p);
							}
							mainhand.setItemDamage(mainhand.getItemDamage() - 1);
							return;
						}
						break;
				}
			}
		}
	}

	@Override
	public net.minecraftforge.common.IRarity getForgeRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format("string.theaurorian.tooltip.absorptionorb"));
		}
	}
}
