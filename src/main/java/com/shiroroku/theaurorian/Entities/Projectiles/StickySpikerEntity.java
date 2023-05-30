package com.shiroroku.theaurorian.Entities.Projectiles;

import com.shiroroku.theaurorian.Registry.ParticleRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StickySpikerEntity extends EntityThrowable {
	public static final String EntityName = "stickyspiker";

	public StickySpikerEntity(World worldIn) {
		super(worldIn);
	}

	public StickySpikerEntity(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	public StickySpikerEntity(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 3) {
			if (this.world.isRemote) {
				for (int i = 0; i < 8; ++i) {
					ParticleRegistry.spawnParticle(ParticleRegistry.Particles.STICKYSPIKER, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
				}
			}
		}
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (!this.world.isRemote) {
			if (result.entityHit != null) {
				if (result.entityHit instanceof EntityLivingBase) {
					EntityLivingBase e = (EntityLivingBase) result.entityHit;
					if (e != this.getThrower()) {
						e.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.5f);
						e.addPotionEffect(new PotionEffect(MobEffects.POISON, 200));
					}
				}
			}

			this.world.setEntityState(this, (byte) 3);
			this.setDead();
		}
	}
}
