package com.elseytd.theaurorian.Entities.CrystallineSprite;

import com.elseytd.theaurorian.TAItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class TAEntity_CrystallineBeam extends EntityArrow {
	public static final String EntityName = "crystallinebeam";

	private static final double arrowDamage = 2.5D;
	private static final float arrowVelocity = 0.5f;
	
	private void setStats() {
		this.setDamage(arrowDamage);
		this.setNoGravity(true);
	}

	public TAEntity_CrystallineBeam(World worldIn) {
		super(worldIn);
		setStats();
	}

	public TAEntity_CrystallineBeam(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		setStats();
	}

	public TAEntity_CrystallineBeam(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
		setStats();
	}

	@Override
	public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
		float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		float f1 = -MathHelper.sin(pitch * 0.017453292F);
		float f2 = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		this.shoot((double) f, (double) f1, (double) f2, velocity * arrowVelocity, inaccuracy);
		this.motionX += shooter.motionX;
		this.motionZ += shooter.motionZ;

		if (!shooter.onGround) {
			this.motionY += shooter.motionY;
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if(this.inGround || this.ticksExisted > 200) {
			this.setDead();
		}
		
	}
	
	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(TAItems.crystallinesprite);
	}
}
