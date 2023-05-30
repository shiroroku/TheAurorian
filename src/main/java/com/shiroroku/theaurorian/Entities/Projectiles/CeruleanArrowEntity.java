package com.shiroroku.theaurorian.Entities.Projectiles;

import com.shiroroku.theaurorian.Registry.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CeruleanArrowEntity extends EntityArrow {
	public static final String EntityName = "ceruleanarrow";

	private static final double arrowDamage = 1D;
	private static final float arrowVelocity = 1.5f;

	private void setStats() {
		this.setDamage(arrowDamage);
	}

	public CeruleanArrowEntity(World worldIn) {
		super(worldIn);
		this.setStats();
	}

	public CeruleanArrowEntity(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		this.setStats();
	}

	public CeruleanArrowEntity(World worldIn, EntityLivingBase shooter) {
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
		return new ItemStack(ItemRegistry.Registry.CERULEANARROW.getItem());
	}
}
