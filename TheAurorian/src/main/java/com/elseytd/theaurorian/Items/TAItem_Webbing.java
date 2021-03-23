package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.Entities.Projectiles.Webbing_Entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class TAItem_Webbing extends Item {

	public static final String ITEMNAME = "webbing";

	public TAItem_Webbing() {
		this.setRegistryName(ITEMNAME);
		this.setMaxStackSize(1);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);

		worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		if (!worldIn.isRemote) {
			Webbing_Entity entity = new Webbing_Entity(worldIn, playerIn);
			entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.7F, 1.0F);
			worldIn.spawnEntity(entity);
		}

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}

}
