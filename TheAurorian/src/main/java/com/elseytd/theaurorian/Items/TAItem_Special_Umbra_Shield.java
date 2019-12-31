package com.elseytd.theaurorian.Items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Special_Umbra_Shield extends TAItem_Tool_Shield {

	public static final String ITEMNAME = "umbrashield";

	public TAItem_Special_Umbra_Shield() {
		super();
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
		this.setMaxDamage(512);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return TAConfig.Config_UmbraShieldTimeUntilOverheat;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if (!player.isSneaking()) {
				player.getCooldownTracker().setCooldown(stack.getItem(), TAConfig.Config_UmbraShieldOverheatCooldown);
				if (worldIn.isRemote) {
					worldIn.playSound(player.posX + 0.5D, player.posY, player.posZ + 0.5D, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
				}
			}
		}
		return stack;
	}

	@Override
	public void onBlockingDamage(ItemStack shield, EntityPlayer player) {
		super.onBlockingDamage(shield, player);
		player.getCooldownTracker().setCooldown(shield.getItem(), 20);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			if (stack == player.getActiveItemStack() && !player.isSneaking()) {
				double x = -MathHelper.sin(player.rotationYawHead * 0.017453292F) * MathHelper.cos(player.rotationPitch * 0.017453292F);
				double y = -MathHelper.sin((player.rotationPitch) * 0.017453292F);
				double z = MathHelper.cos(player.rotationYawHead * 0.017453292F) * MathHelper.cos(player.rotationPitch * 0.017453292F);

				if (worldIn.isRemote) {
					if (player.ticksExisted % 6 == 0) {
						worldIn.playSound(player.posX + 0.5D, player.posY, player.posZ + 0.5D, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0F, 0.75F, false);
					}
					Random rand = player.getRNG();
					double spread = 20D;
					double distance = 0.25D;
					int density = 50;

					for (int i = -1; i < rand.nextInt(density); i++) {

						float f = MathHelper.sqrt(x * x + y * y + z * z);
						double particlex = x / (double) f;
						double particley = y / (double) f;
						double particlez = z / (double) f;
						particlex = particlex + rand.nextGaussian() * 0.007499999832361937D * spread;
						particley = particley + rand.nextGaussian() * 0.007499999832361937D * spread;
						particlez = particlez + rand.nextGaussian() * 0.007499999832361937D * spread;
						particlex = particlex * distance;
						particley = particley * distance;
						particlez = particlez * distance;

						worldIn.spawnParticle(EnumParticleTypes.FLAME, entityIn.posX + x, entityIn.posY + y + entityIn.height / 2, entityIn.posZ + z, particlex, particley, particlez);
					}
				}

				double distance = 3;
				x = x * distance + player.posX;
				y = y * distance + player.posY;
				z = z * distance + player.posZ;

				double width = 3;
				double height = 3;
				double boxsx = x - (width / 2);
				double boxsy = y - 1;
				double boxsz = z - (width / 2);
				double boxex = x + (width / 2);
				double boxey = y + height;
				double boxez = z + (width / 2);

				List<EntityLivingBase> entities = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(boxsx, boxsy, boxsz, boxex, boxey, boxez));
				for (EntityLivingBase e : entities) {
					if (e.isNonBoss() && e != player) {
						if (e instanceof EntityPlayer) {
							if (player.canAttackPlayer((EntityPlayer) e)) {
								e.setFire(1);
							}
						} else {
							e.setFire(1);
						}
					}
				}
			}
		}
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
			tooltip.add(I18n.format("string.theaurorian.tooltip.umbrashield"));
		}
	}

}
