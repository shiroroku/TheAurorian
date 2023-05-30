package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class KnightItemArmor extends ItemArmor {
	
	public KnightItemArmor(EntityEquipmentSlot equipmentSlotIn, String name) {
		super(ItemRegistry.Materials.KNIGHT_ARMOR, 0, equipmentSlotIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(AurorianMod.MODID + "." + name);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setMaxStackSize(1);
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entityIn, EntityEquipmentSlot slot, String layer) {
		if (slot != EntityEquipmentSlot.LEGS) {
			return AurorianMod.MODID + ":textures/armor/knightarmorlayer1.png";
		} else {
			return AurorianMod.MODID + ":textures/armor/knightarmorlayer2.png";
		}
	}
}
