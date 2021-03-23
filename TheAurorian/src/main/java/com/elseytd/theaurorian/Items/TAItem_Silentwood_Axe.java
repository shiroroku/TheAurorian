package com.elseytd.theaurorian.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TAUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Silentwood_Axe extends ItemAxe {

	public static final String ITEMNAME = "silentwoodaxe";

	public TAItem_Silentwood_Axe() {
		super(TAItems.Materials.SILENTWOOD, 6.0F, -3.2F);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		if (!worldIn.isRemote) {
			if (state.getBlock() == TABlocks.Registry.SILENTWOODLOG.getBlock()) {
				if (TAUtil.randomChanceOf(0.75F)) {
					if (stack.getItemDamage() > 0) {
						stack.setItemDamage(stack.getItemDamage() - 1);
					}
				} else {
					stack.damageItem(1, entityLiving);
				}
			} else {
				stack.damageItem(1, entityLiving);
			}
		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format("string.theaurorian.tooltip.silentwoodaxe"));
		}
	}
}
