package com.elseytd.theaurorian.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Special_DungeonKey extends Item {

	public static final String ITEMNAME_RUNESTONE = "runestonekey";
	public static final String ITEMNAME_RUNESTONELOOT = "runestonelootkey";
	public static final String ITEMNAME_MOONTEMPLE = "moontemplekey";
	public static final String ITEMNAME_MOONTEMPLECELL = "moontemplecellkey";
	public static final String ITEMNAME_DARKSTONE = "darkstonekey";

	public TAItem_Special_DungeonKey(String name) {
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(name);
		this.setMaxStackSize(1);
		this.setUnlocalizedName(TAMod.MODID + "." + name);
		this.setMaxDamage(1);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	@Override
	public net.minecraftforge.common.IRarity getForgeRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + ITEMNAME_MOONTEMPLECELL)) {
				tooltip.add(I18n.format("string.theaurorian.tooltip.keymoontemplecell"));
			}
			if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + ITEMNAME_RUNESTONE)) {
				tooltip.add(I18n.format("string.theaurorian.tooltip.keyrunestone"));
			}
			if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + ITEMNAME_MOONTEMPLE)) {
				tooltip.add(I18n.format("string.theaurorian.tooltip.keymoontemple"));
			}
			if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + ITEMNAME_DARKSTONE)) {
				tooltip.add(I18n.format("string.theaurorian.tooltip.keydarkstone"));
			}
			if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + ITEMNAME_RUNESTONELOOT)) {
				tooltip.add(I18n.format("string.theaurorian.tooltip.keyrunestoneloot"));
			}
		}
	}
}
