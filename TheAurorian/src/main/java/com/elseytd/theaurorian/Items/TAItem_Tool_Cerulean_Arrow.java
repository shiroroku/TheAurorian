package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.Entities.TAEntity_CeruleanArrow;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Tool_Cerulean_Arrow extends ItemArrow {

	public static final String ITEMNAME = "ceruleanarrow";

	public TAItem_Tool_Cerulean_Arrow() {
		super();
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

	@Override
	public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
		TAEntity_CeruleanArrow ceruleanarrow = new TAEntity_CeruleanArrow(worldIn, shooter);
		return ceruleanarrow;
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

}
