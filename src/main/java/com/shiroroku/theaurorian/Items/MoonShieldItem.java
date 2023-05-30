package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.AurorianMod;
import com.shiroroku.theaurorian.Util.EntityHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class MoonShieldItem extends ShieldItem {

	public static final String ITEMNAME = "moonshield";

	public MoonShieldItem() {
		super();
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
		this.setMaxDamage(512);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 50;
	}

	@Override
	public net.minecraftforge.common.IRarity getForgeRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format("string.theaurorian.tooltip.moonshield"));
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityPlayer) {
			EntityPlayer playerIn = (EntityPlayer) entityIn;
			if (playerIn.getHeldItemMainhand() == stack || playerIn.getHeldItemOffhand() == stack) {
				if (worldIn.isRemote) {
					if (!playerIn.isSneaking() && playerIn.onGround && playerIn.ticksExisted % 4 == 0 && stack == playerIn.getActiveItemStack()) {
						double motionX = playerIn.getRNG().nextGaussian() * 0.02D;
						double motionY = playerIn.getRNG().nextGaussian() * 0.1D;
						double motionZ = playerIn.getRNG().nextGaussian() * 0.02D;
						worldIn.spawnParticle(EnumParticleTypes.VILLAGER_ANGRY, playerIn.posX + playerIn.getRNG().nextFloat() - 0.5, playerIn.posY + playerIn.getRNG().nextFloat() * playerIn.height, playerIn.posZ + playerIn.getRNG().nextFloat() - 0.5, motionX, motionY, motionZ);
					}
				}

				if (playerIn.getCooldownTracker().hasCooldown(stack.getItem()) && playerIn.motionX <= 1.5 && playerIn.motionZ <= 1.5) {
					List<EntityLivingBase> entities = EntityHelper.getEntitiesAround(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, 1, 2.5D, false);
					for (EntityLivingBase e : entities) {
						if (e.isNonBoss() && e != playerIn) {
							if (e instanceof EntityPlayer) {
								if (!playerIn.canAttackPlayer((EntityPlayer) e)) {
									return;
								}
							}
							e.motionY = e.motionY + 1D;
							worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS, 1F, 1.5F);
							playerIn.getCooldownTracker().setCooldown(stack.getItem(), 0);
							return;
						}
					}
				}
			}
		}
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if (!player.isSneaking() && player.onGround) {
				double velx = MathHelper.sin(-player.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI);
				double velz = MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI);
				int distance = 4;
				player.addVelocity(velx * distance, 0, velz * distance);
				player.getCooldownTracker().setCooldown(stack.getItem(), 10);
			}
		}
		return stack;
	}

}
