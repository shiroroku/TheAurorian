package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class StrangeMeatFood extends ItemFood {

	public static final String ITEMNAME = "strangemeat";

	public StrangeMeatFood() {
		super(8, 0.9F, true);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
		this.setMaxDamage(AurorianConfig.Config_StrangeMeatUses);
		this.setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) entityLiving;
			entityplayer.getFoodStats().addStats(this, stack);
			worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
			this.onFoodEaten(stack, worldIn, entityplayer);
			entityplayer.addStat(StatList.getObjectUseStats(this));

			if (entityplayer instanceof EntityPlayerMP) {
				CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP) entityplayer, stack);
			}

			if (stack.getItemDamage() == stack.getMaxDamage()) {
				int select = worldIn.rand.nextInt(7);
				switch (select) {
				case 0:
					entityplayer.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 2400));
					break;
				case 1:
					entityplayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2400));
					break;
				case 2:
					entityplayer.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2400));
					break;
				case 3:
					entityplayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2400));
					break;
				case 4:
					entityplayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 2400));
					break;
				case 5:
					entityplayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, 2400));
					break;
				case 6:
					entityplayer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 2400));
					break;
				}
			}
		}

		stack.damageItem(1, entityLiving);
		return stack;
	}

	@Override
	public net.minecraftforge.common.IRarity getForgeRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 64;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format("string.theaurorian.tooltip.strangemeat"));
		}
	}

}
