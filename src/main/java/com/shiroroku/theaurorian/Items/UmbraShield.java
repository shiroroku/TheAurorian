package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.Registry.PacketRegistry;
import com.shiroroku.theaurorian.AurorianConfig;
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
import java.util.Random;

public class UmbraShield extends ShieldItem {

	public static final String ITEMNAME = "umbrashield";

	public UmbraShield() {
		super();
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
		this.setMaxDamage(512);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return AurorianConfig.Config_UmbraShieldTimeUntilOverheat;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if (!player.isSneaking()) {
				player.getCooldownTracker().setCooldown(stack.getItem(), AurorianConfig.Config_UmbraShieldOverheatCooldown);
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
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			if (stack == player.getActiveItemStack() && !player.isSneaking()) {
				double x = -MathHelper.sin((float) Math.toRadians(player.rotationYawHead)) * MathHelper.cos((float) Math.toRadians(player.rotationPitch));
				double y = -MathHelper.sin((float) Math.toRadians(player.rotationPitch));
				double z = MathHelper.cos((float) Math.toRadians(player.rotationYawHead)) * MathHelper.cos((float) Math.toRadians(player.rotationPitch));

				if (worldIn.isRemote) {
					if (player.ticksExisted % 6 == 0) {
						worldIn.playSound(player.posX + 0.5D, player.posY, player.posZ + 0.5D, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0F, 0.75F, false);
					}
				}

				Random rand = player.getRNG();
				double particlespread = 50D;
				double particledistance = 0.1D;
				int particledensity = 50;

				for (int i = -1; i < rand.nextInt(particledensity); i++) {

					float f = MathHelper.sqrt(x * x + y * y + z * z);
					double particlex = x / (double) f;
					double particley = y / (double) f;
					double particlez = z / (double) f;
					particlex = particlex + rand.nextGaussian() * 0.007499999832361937D * particlespread;
					particley = particley + rand.nextGaussian() * 0.007499999832361937D * particlespread;
					particlez = particlez + rand.nextGaussian() * 0.007499999832361937D * particlespread;
					particlex = particlex * particledistance;
					particley = particley * particledistance;
					particlez = particlez * particledistance;

					PacketRegistry.spawnParticle(worldIn, EnumParticleTypes.FLAME.getParticleID(), entityIn.posX + x, entityIn.posY + y + entityIn.height / 2, entityIn.posZ + z, particlex, particley, particlez);
				}

				double reach = 1.5;
				x = x * reach + player.posX;
				y = y * reach + player.posY;
				z = z * reach + player.posZ;

				List<EntityLivingBase> entities = EntityHelper.getEntitiesAround(worldIn, x, y + 1.5, z, 1, false);
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
