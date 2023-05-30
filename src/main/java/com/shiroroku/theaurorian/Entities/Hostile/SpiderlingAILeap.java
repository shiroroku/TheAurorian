package com.shiroroku.theaurorian.Entities.Hostile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;

public class SpiderlingAILeap extends EntityAIBase {

	private final SpiderlingEntity entity;
	private EntityLivingBase target;

	private final float leapVelocity = 0.5f;
	private final int minDistance = 20;

	public SpiderlingAILeap(SpiderlingEntity attacker) {
		this.entity = attacker;
		this.setMutexBits(5);
	}

	@Override
	public boolean shouldExecute() {
		this.target = this.entity.getAttackTarget();

		if (this.target == null) {
			return false;
		} else {
			double d0 = this.entity.getDistanceSq(this.target);
			if (d0 >= minDistance) {
				return this.entity.onGround;
			} else {
				return false;
			}
		}
	}

	@Override
	public void updateTask() {
		this.entity.faceEntity(target, this.entity.getHorizontalFaceSpeed(), this.entity.getVerticalFaceSpeed());
		this.entity.getLookHelper().setLookPositionWithEntity(target, (float) this.entity.getHorizontalFaceSpeed(), (float) this.entity.getVerticalFaceSpeed());
	}

	@Override
	public boolean shouldContinueExecuting() {
		return !this.entity.onGround;
	}

	@Override
	public void startExecuting() {

		double d0 = this.target.posX - this.entity.posX;
		double d1 = this.target.posZ - this.entity.posZ;
		float f = MathHelper.sqrt(d0 * d0 + d1 * d1);

		double val1 = 1;
		double val2 = 1;

		if ((double) f >= 1.0E-4D) {
			this.entity.motionX += d0 / (double) f * 0.5D * val1 + this.entity.motionX * val2;
			this.entity.motionZ += d1 / (double) f * 0.5D * val1 + this.entity.motionZ * val2;
		}

		this.entity.motionY = this.leapVelocity;
	}
}