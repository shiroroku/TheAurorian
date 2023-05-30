package com.shiroroku.theaurorian.Entities.Projectiles;

import com.shiroroku.theaurorian.Registry.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CrystalArrowEntity extends EntityArrow {
	public static final String EntityName = "crystalarrow";

	private static final double arrowDamage = 2D;
	private static final float arrowVelocity = 0.45f;
	private static final int arrowKnockback = 2;

	private void setStats() {
		this.setDamage(arrowDamage);
		this.setKnockbackStrength(arrowKnockback);
	}

	public CrystalArrowEntity(World worldIn) {
		super(worldIn);
		this.setStats();
	}

	public CrystalArrowEntity(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		this.setStats();
	}

	public CrystalArrowEntity(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
		this.setStats();
	}

	@Override
	public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
		float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		float f1 = -MathHelper.sin(pitch * 0.017453292F);
		float f2 = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		this.shoot(f, f1, f2, velocity * arrowVelocity, inaccuracy);
		this.motionX += shooter.motionX;
		this.motionZ += shooter.motionZ;

		if (!shooter.onGround) {
			this.motionY += shooter.motionY;
		}
	}

	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(ItemRegistry.Registry.CRYSTALARROW.getItem());
	}
}
