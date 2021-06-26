package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.Registry.ItemRegistry;
import com.elseytd.theaurorian.AurorianMod;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class CeruleanItemArmor extends ItemArmor {
	
	public CeruleanItemArmor(EntityEquipmentSlot equipmentSlotIn, String name) {
		super(ItemRegistry.Materials.CERULEAN_ARMOR, 0, equipmentSlotIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(AurorianMod.MODID + "." + name);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setMaxStackSize(1);
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entityIn, EntityEquipmentSlot slot, String layer) {
		if (slot != EntityEquipmentSlot.LEGS) {
			return AurorianMod.MODID + ":textures/armor/ceruleanarmorlayer1.png";
		} else {
			return AurorianMod.MODID + ":textures/armor/ceruleanarmorlayer2.png";
		}
	}
}
