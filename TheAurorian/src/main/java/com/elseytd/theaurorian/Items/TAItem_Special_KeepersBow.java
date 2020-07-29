package com.elseytd.theaurorian.Items;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Special_KeepersBow extends ItemBow {

	public static final String ITEMNAME = "keepersbow";

	public TAItem_Special_KeepersBow() {
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
		this.setMaxDamage(512);
		this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				if (entityIn == null) {
					return 0.0F;
				} else {
					return entityIn.getActiveItemStack().getItem() != TAItems.keepersbow ? 0.0F : (float) (stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F;
				}
			}
		});
		this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
			}
		});
	}

}
