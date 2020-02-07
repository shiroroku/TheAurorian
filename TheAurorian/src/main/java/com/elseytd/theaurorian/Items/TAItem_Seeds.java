package com.elseytd.theaurorian.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Seeds extends Item implements net.minecraftforge.common.IPlantable {

	public static final String ITEMNAME_LAVENDER = "lavenderseeds";

	public TAItem_Seeds(String namein) {
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(namein);
		this.setUnlocalizedName(TAMod.MODID + "." + namein);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack itemstack = player.getHeldItem(hand);
		net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);
		if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && state.getBlock() == TABlocks.aurorianfarmtile || state.getBlock() == Blocks.FARMLAND && worldIn.isAirBlock(pos.up())) {
			worldIn.setBlockState(pos.up(), getPlant(worldIn, pos), 11);
			itemstack.shrink(1);
			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Crop;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + ITEMNAME_LAVENDER)) {
			return TABlocks.lavendercrop.getDefaultState();
		} else {
			return TABlocks.lavendercrop.getDefaultState();
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format("string.theaurorian.tooltip.lavenderseeds"));
		}
	}
}
