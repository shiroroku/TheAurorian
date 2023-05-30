package com.shiroroku.theaurorian.Misc;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Comparator;

public class AurorianCreativeTab extends CreativeTabs {

	public AurorianCreativeTab(String label) {
		super(label);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Item.getItemFromBlock(BlockRegistry.Registry.PLANTSILENTWOODSAPLING.getBlock()));
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
