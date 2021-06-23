package com.elseytd.theaurorian.Misc;

import com.elseytd.theaurorian.TABlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Comparator;

public class TACreativeTab extends CreativeTabs {

	public TACreativeTab(String label) {
		super(label);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Item.getItemFromBlock(TABlocks.Registry.PLANTSILENTWOODSAPLING.getBlock()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void displayAllRelevantItems(final NonNullList<ItemStack> items) {
		super.displayAllRelevantItems(items);
		items.sort(new Comparator<ItemStack>() {
			@Override
			public int compare(ItemStack i1, ItemStack i2) {
				return i1.getUnlocalizedName().compareTo(i2.getUnlocalizedName());
			}
		});
	}
}
