package com.elseytd.theaurorian.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Silentwood_Stick extends Item {

	public static final String ITEMNAME = "silentwoodstick";

	public TAItem_Silentwood_Stick() {
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format("string.theaurorian.tooltip.silentwoodstick"));
		}
	}

	@Override
	public int getItemBurnTime(ItemStack itemStack) {
		return 100;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player.getHeldItemOffhand().getItem() == TAItems.Registry.SILENTWOODSTICK.getItem()) {
			pos = pos.offset(facing);
			ItemStack itemstack = player.getHeldItem(hand);
			if (!player.canPlayerEdit(pos, facing, itemstack)) {
				return EnumActionResult.FAIL;
			} else if (TAConfig.Config_SticksMakeFire) {
				if (worldIn.isAirBlock(pos)) {
					worldIn.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
					worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState(), 11);
				}

				if (player instanceof EntityPlayerMP) {
					CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, pos, itemstack);
				}

				itemstack.shrink(1);
				player.getHeldItemOffhand().shrink(1);

				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.FAIL;
	}
}
