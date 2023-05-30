package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ShieldItem extends Item {

	public ShieldItem() {
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setMaxDamage(256);
		this.setMaxStackSize(1);
		this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
			}
		});
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, ItemArmor.DISPENSER_BEHAVIOR);
	}

	public static void handleDamageEvent(LivingAttackEvent e) {
		float damage = e.getAmount();
		ItemStack activeItemStack;
		EntityPlayer player;
		if (e.getEntityLiving() instanceof EntityPlayer) {
			player = (EntityPlayer) e.getEntityLiving();
			if (player.getActiveItemStack() != null) {
				activeItemStack = player.getActiveItemStack();
				if (damage > 0.0F && activeItemStack != null && activeItemStack.getItem() instanceof ShieldItem) {
					((ShieldItem) activeItemStack.getItem()).onBlockingDamage(activeItemStack, player);
				}
			}
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BLOCK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		playerIn.setActiveHand(handIn);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}

	/**
	 * Called when the player is attacked when blocking with the shield.
	 */
	public void onBlockingDamage(ItemStack shield, EntityPlayer player) {
		shield.damageItem(1, player);
	}

}
