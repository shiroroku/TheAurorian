package com.elseytd.theaurorian.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TAUtil;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Special_Locator extends Item {

	public static final String ITEMNAME = "locator";

	public TAItem_Special_Locator() {
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setMaxStackSize(1);
		this.setMaxDamage(30);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ChunkPos dungeon = TAUtil.WorldAndGen.getNearestRunestoneDungeon(playerIn);

		ItemStack itemstack = playerIn.getHeldItem(handIn);
		worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (dungeon != null) {
			if (worldIn.isRemote) {
				double lookx = 0.25D + -MathHelper.sin((float) Math.toRadians(playerIn.rotationYawHead)) * MathHelper.cos((float) Math.toRadians(playerIn.rotationPitch));
				double looky = 0.25D + -MathHelper.sin((float) Math.toRadians(playerIn.rotationPitch));
				double lookz = 0.25D + MathHelper.cos((float) Math.toRadians(playerIn.rotationYawHead)) * MathHelper.cos((float) Math.toRadians(playerIn.rotationPitch));

				double y = (double) playerIn.posY + 1 + Item.itemRand.nextDouble() * 6.0D / 16.0D;
				double speed = -0.005D;
				double targetx = playerIn.posX - dungeon.x * 16;
				double targetz = playerIn.posZ - dungeon.z * 16;
				double originx = playerIn.posX;
				double originz = playerIn.posZ;
				for (int i = 0; i < 2; i++) {
					worldIn.spawnParticle(EnumParticleTypes.CLOUD, originx + lookx, y + looky, originz + lookz, targetx * speed * Item.itemRand.nextDouble(), 0.25D, targetz * speed * Item.itemRand.nextDouble());
					worldIn.spawnParticle(EnumParticleTypes.ITEM_CRACK, originx + lookx, y + looky, originz + lookz, targetx * speed * Item.itemRand.nextDouble(), 0.25D, targetz * speed * Item.itemRand.nextDouble(), Item.getIdFromItem(itemstack.getItem()), itemstack.getMetadata());
				}
			}
			itemstack.damageItem(1, playerIn);
		}
		playerIn.addStat(StatList.getObjectUseStats(this));
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
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
			tooltip.add(I18n.format("string.theaurorian.tooltip.locator"));
		}
	}

}
