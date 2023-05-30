package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import com.shiroroku.theaurorian.Util.AurorianSteelHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class AurorianSteelItemArmor extends ItemArmor {

	public AurorianSteelItemArmor(EntityEquipmentSlot equipmentSlotIn, String name) {
		super(ItemRegistry.Materials.AURORIANSTEEL_ARMOR, 0, equipmentSlotIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(AurorianMod.MODID + "." + name);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setMaxStackSize(1);
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entityIn, EntityEquipmentSlot slot, String layer) {
		if (slot != EntityEquipmentSlot.LEGS) {
			return AurorianMod.MODID + ":textures/armor/auroriansteelarmorlayer1.png";
		} else {
			return AurorianMod.MODID + ":textures/armor/auroriansteelarmorlayer2.png";
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		AurorianSteelHelper.getAurorianSteelInfo(stack, worldIn, tooltip, flagIn);
	}

	public static void handleDamageEvent(LivingDamageEvent e) {
		EntityLivingBase target = e.getEntityLiving();
		if (target == null) {
			return;
		}
		for (ItemStack piece : target.getArmorInventoryList()) {
			if (piece.getItem() instanceof ItemArmor) {
				ItemArmor armorpart = (ItemArmor) piece.getItem();
				if (armorpart.getArmorMaterial() == ItemRegistry.Materials.AURORIANSTEEL_ARMOR) {
					AurorianSteelHelper.handleAurorianSteelDurability(piece, target.world, target);
				}
			}
		}
	}
}
