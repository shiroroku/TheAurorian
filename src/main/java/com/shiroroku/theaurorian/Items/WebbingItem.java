package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.Entities.Projectiles.WebbingEntity;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class WebbingItem extends Item {

	public static final String ITEMNAME = "webbing";

	public WebbingItem() {
		this.setRegistryName(ITEMNAME);
		this.setMaxStackSize(1);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);

		worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		if (!worldIn.isRemote) {
			WebbingEntity entity = new WebbingEntity(worldIn, playerIn);
			entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.7F, 1.0F);
			worldIn.spawnEntity(entity);
		}

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}

}
