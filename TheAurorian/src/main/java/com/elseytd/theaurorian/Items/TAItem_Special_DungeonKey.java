package com.elseytd.theaurorian.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Special_DungeonKey extends Item {

	public static final String ITEMNAME_RUNESTONE = "runestonekey";
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

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + "Hold shift for more info" + TextFormatting.RESET);
		} else {
			if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + ITEMNAME_MOONTEMPLECELL)) {
				tooltip.add("Used to unlock Moon Temple's inner room. Single use!");
			}
			if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + ITEMNAME_RUNESTONE)) {
				tooltip.add("Used to unlock Runestone Gates. Single use!");
			}
			if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + ITEMNAME_MOONTEMPLE)) {
				tooltip.add("Used to unlock Moon Temple Gates. Single use!");
			}
			if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + ITEMNAME_DARKSTONE)) {
				tooltip.add("Used to unlock the forbidden...");
			}
		}
	}
}
