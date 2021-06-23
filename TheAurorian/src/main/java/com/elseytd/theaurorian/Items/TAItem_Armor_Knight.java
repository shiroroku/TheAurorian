package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class TAItem_Armor_Knight extends ItemArmor {
	
	public TAItem_Armor_Knight(EntityEquipmentSlot equipmentSlotIn, String name) {
		super(TAItems.Materials.KNIGHT_ARMOR, 0, equipmentSlotIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(TAMod.MODID + "." + name);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setMaxStackSize(1);
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entityIn, EntityEquipmentSlot slot, String layer) {
		if (slot != EntityEquipmentSlot.LEGS) {
			return TAMod.MODID + ":textures/armor/knightarmorlayer1.png";
		} else {
			return TAMod.MODID + ":textures/armor/knightarmorlayer2.png";
		}
	}
}
