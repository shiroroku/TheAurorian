package com.elseytd.theaurorian.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TAUtil;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Special_Aurorianite_Sword extends ItemSword {
	public static final String ITEMNAME = "aurorianitesword";

	public TAItem_Special_Aurorianite_Sword() {
		super(TAItems.Materials.AURORIANITE);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (playerIn.getHeldItemOffhand().isEmpty() || playerIn.isSneaking()) {
			List<EntityLivingBase> entities = TAUtil.Entity.getEntitiesAround(worldIn, playerIn.posX, playerIn.posY + 2.5D, playerIn.posZ, 5, 2.5D, false);
			for (EntityLivingBase e : entities) {
				if (e.isNonBoss()) {
					e.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 60));
					e.motionY = e.motionY + 0.5D;
				}
			}
			if (worldIn.isRemote) {
				playerIn.playSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 2f);
			}
			playerIn.getHeldItemMainhand().damageItem(5, playerIn);
			playerIn.getCooldownTracker().setCooldown(this, TAConfig.Config_AurorianiteSwordCooldown);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		}

		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
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
			tooltip.add(I18n.format("string.theaurorian.tooltip.aurorianitesword"));
		}
	}
}
