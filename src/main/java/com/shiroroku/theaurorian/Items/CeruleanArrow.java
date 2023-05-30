package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.Entities.Projectiles.CeruleanArrowEntity;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CeruleanArrow extends ItemArrow {

	public static final String ITEMNAME = "ceruleanarrow";

	public CeruleanArrow() {
		super();
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
	}

	@Override
	public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
		CeruleanArrowEntity ceruleanarrow = new CeruleanArrowEntity(worldIn, shooter);
		return ceruleanarrow;
	}

}
