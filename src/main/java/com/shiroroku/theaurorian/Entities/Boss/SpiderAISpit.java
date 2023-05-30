package com.shiroroku.theaurorian.Entities.Boss;

import com.shiroroku.theaurorian.Entities.Projectiles.WebbingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;

public class SpiderAISpit extends EntityAIBase {

	private final SpiderEntity entity;

	private int spitCooldown = 0;
	private int spitWindup = 0;
	private final int minDistance = 3;
	private final int maxDistance = 50;
	private double targetX;
	private double targetY;
	private double targetZ;

	public SpiderAISpit(SpiderEntity attacker) {
		this.entity = attacker;
		this.setMutexBits(3);
	}

	@Override
	public boolean isInterruptible() {
		return false;
	}

	@Override
	public boolean shouldExecute() {
		if (this.entity.getAttackTarget() != null) {
			float distToTarget = this.entity.getDistance(this.entity.getAttackTarget());
			if (distToTarget >= this.minDistance && distToTarget <= this.maxDistance) {
				if (this.entity.getEntitySenses().canSee(this.entity.getAttackTarget())) {
					if (this.spitCooldown == 0) {
						return true;
					} else if (this.spitCooldown > 0) {
						this.spitCooldown--;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void startExecuting() {
		this.entity.getNavigator().clearPath();
		this.entity.setSpitting(true);
		this.entity.faceEntity(this.entity.getAttackTarget(), this.entity.getHorizontalFaceSpeed() * 2, this.entity.getVerticalFaceSpeed() * 2);
		this.startSpit();
	}

	@Override
	public boolean shouldContinueExecuting() {
		if (!this.entity.getNavigator().noPath() || this.entity.getAttackTarget() != null) {
			if (this.entity.getAttackTarget() != null) {
				if (this.entity.getEntitySenses().canSee(this.entity.getAttackTarget())) {
					if (this.spitCooldown == 0) {
						return true;
					}
				}
			} else {
				if (this.spitCooldown == 0) {
					return true;
				}
			}
		}
		this.entity.setSpitting(false);
		return false;
	}

	@Override
	public void updateTask() {
		EntityLivingBase target = this.entity.getAttackTarget();

		if (this.spitWindup == 1) {
			this.setSpitLocation(this.entity.getAttackTarget());
		}

		if (this.spitWindup == 0) {
			this.entity.getLookHelper().setLookPosition(this.targetX, this.targetY + this.entity.getEyeHeight(), this.targetZ, this.entity.getHorizontalFaceSpeed(), this.entity.getVerticalFaceSpeed());
			this.entity.setWindingUpSpit(false);
			this.finishSpit(target);
		} else {
			this.entity.getLookHelper().setLookPosition(target.posX, target.posY + target.getEyeHeight(), target.posZ, this.entity.getHorizontalFaceSpeed(), this.entity.getVerticalFaceSpeed());
			this.entity.setWindingUpSpit(true);
			this.spitWindup--;
		}
	}

	private void startSpit() {
		this.spitWindup = 20 - this.entity.getRNG().nextInt(10);
	}

	@Override
	public void resetTask() {
		this.entity.getNavigator().clearPath();
	}

	private void finishSpit(EntityLivingBase target) {
		this.spitCooldown = 30 - this.entity.getRNG().nextInt(10);
		if (target != null) {

			WebbingEntity web = new WebbingEntity(this.entity.world, this.entity);
			double d0 = target.posX - this.entity.posX;
			double d1 = target.getEntityBoundingBox().minY + target.height / 3.0F - web.posY;
			double d2 = target.posZ - this.entity.posZ;
			double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
			web.shoot(d0, d1 + d3 * 0.1D, d2, 1F, 0F);
			this.entity.world.playSound(null, this.entity.posX, this.entity.posY, this.entity.posZ, SoundEvents.ENTITY_CAT_HISS, SoundCategory.HOSTILE, 0.8F, 0.8F / (this.entity.getRNG().nextFloat() * 0.4F + 0.8F));

			this.entity.world.spawnEntity(web);
		}
		this.entity.setSpitting(false);
	}

	private void setSpitLocation(EntityLivingBase target) {
		this.targetX = target.posX;
		this.targetY = target.posY;
		this.targetZ = target.posZ;
	}

}