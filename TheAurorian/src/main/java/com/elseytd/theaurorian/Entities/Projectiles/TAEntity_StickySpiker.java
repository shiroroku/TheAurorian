package com.elseytd.theaurorian.Entities.Projectiles;

import com.elseytd.theaurorian.TAParticles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class TAEntity_StickySpiker extends EntityThrowable {
	public static final String EntityName = "stickyspiker";

	public TAEntity_StickySpiker(World worldIn) {
		super(worldIn);
	}

	public TAEntity_StickySpiker(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	public TAEntity_StickySpiker(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
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

			for (int i = 0; i < 8; ++i) {
				TAParticles.spawn(TAParticles.Particles.STICKYSPIKER, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}

			this.setDead();
		}
	}
}
