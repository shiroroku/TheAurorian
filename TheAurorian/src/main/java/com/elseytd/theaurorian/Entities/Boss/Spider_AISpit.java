package com.elseytd.theaurorian.Entities.Boss;

import com.elseytd.theaurorian.Entities.Projectiles.Webbing_Entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;

public class Spider_AISpit extends EntityAIBase {

	private Spider_Entity entity;

	private int spitCooldown = 0;
	private int spitWindup = 0;
	private int minDistance = 10;
	private int maxDistance = 50;
	private double targetX;
	private double targetY;
	private double targetZ;

	public Spider_AISpit(Spider_Entity attacker) {
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
		startSpit();
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
			setSpitLocation(this.entity.getAttackTarget());
		}

		if (this.spitWindup == 0) {
			this.entity.getLookHelper().setLookPosition(this.targetX, this.targetY + (double) this.entity.getEyeHeight(), this.targetZ, (float) this.entity.getHorizontalFaceSpeed(), (float) this.entity.getVerticalFaceSpeed());
			this.entity.setWindingUpSpit(false);
			finishSpit(target);
		} else {
			this.entity.getLookHelper().setLookPosition(target.posX, target.posY + (double) target.getEyeHeight(), target.posZ, (float) this.entity.getHorizontalFaceSpeed(), (float) this.entity.getVerticalFaceSpeed());
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
		this.entity.resetActiveHand();
	}

	private void finishSpit(EntityLivingBase target) {
		this.spitCooldown = 30 - this.entity.getRNG().nextInt(10);
		if (target != null) {

			Webbing_Entity web = new Webbing_Entity(this.entity.world, this.entity);
			double d0 = target.posX - entity.posX;
			double d1 = target.getEntityBoundingBox().minY + (double) (target.height / 3.0F) - web.posY;
			double d2 = target.posZ - entity.posZ;
			double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
			web.shoot(d0, d1 + d3 * 0.1D, d2, 1F, 0F);
			entity.world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_CAT_HISS, SoundCategory.HOSTILE, 0.8F, 0.8F / (entity.getRNG().nextFloat() * 0.4F + 0.8F));

			entity.world.spawnEntity(web);
		}
		this.entity.setSpitting(false);
	}

	private void setSpitLocation(EntityLivingBase target) {
		this.targetX = target.posX;
		this.targetY = target.posY;
		this.targetZ = target.posZ;
	}

}