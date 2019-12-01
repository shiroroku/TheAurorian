package com.elseytd.theaurorian.Items;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Special_MoonTempleBreaker extends ItemPickaxe {

	public static final String ITEMNAME = "moontemplebreaker";

	public TAItem_Special_MoonTempleBreaker() {
		super(TAItems.TA_MOONSTONE);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public float getDestroySpeed(@Nonnull ItemStack stack, IBlockState state) {
		float destroySpeed = super.getDestroySpeed(stack, state);
		Block[] b = { TABlocks.moontemplebars, TABlocks.moontemplebricks, TABlocks.moontemplebrickssmooth, TABlocks.moontemplestairs, TABlocks.moontemplegate, TABlocks.moontemplegatekeyhole, TABlocks.moontemplelamp, TABlocks.moontemplecellgate, TABlocks.moontemplecellgatekeyhole };

		for (Block block : b) {
			if (state.getBlock() == block) {
				return destroySpeed * 64F;
			}
		}
		return destroySpeed;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + "Hold shift for more info" + TextFormatting.RESET);
		} else {
			tooltip.add("This pickaxe has the ability to mine Moon Temple Dungeon blocks.");
		}
	}

}
