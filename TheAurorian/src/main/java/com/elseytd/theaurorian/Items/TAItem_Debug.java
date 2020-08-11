package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TAUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TAItem_Debug extends Item {

	public static final String ITEMNAME = "debugger";

	public TAItem_Debug() {
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		TAUtil.Debugging.simulateLootTable(worldIn, new ResourceLocation(TAMod.MODID, "chests/darkstonelow"), 20);

		return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}

}
