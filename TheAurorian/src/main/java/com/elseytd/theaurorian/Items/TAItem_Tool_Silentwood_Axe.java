package com.elseytd.theaurorian.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TAUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Tool_Silentwood_Axe extends ItemAxe {

	public static final String ITEMNAME = "silentwoodaxe";

	public TAItem_Tool_Silentwood_Axe() {
		super(TAItems.TA_SILENTWOOD, 6.0F, -3.2F);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		if (!worldIn.isRemote) {
			if (state.getBlock() == TABlocks.silentwoodlog) {
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

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + "Hold shift for more info" + TextFormatting.RESET);
		} else {
			tooltip.add("Magical Silentwood has a chance to heal itself when chopping Silentwood Logs!");
		}
	}
}
