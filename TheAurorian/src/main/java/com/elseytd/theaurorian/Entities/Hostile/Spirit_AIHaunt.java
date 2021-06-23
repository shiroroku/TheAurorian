package com.elseytd.theaurorian.Entities.Hostile;

import com.elseytd.theaurorian.Util.EntityHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class Spirit_AIHaunt extends EntityAIBase {

	private enum Direction {
		LEFT, RIGHT
	}

	private Spirit_Entity entity;

	private Direction strafeDirection;
	private float strafeDistance = 8F;
	private float strafeSpeed = 0.3F;

	public Spirit_AIHaunt(Spirit_Entity attacker) {
		this.entity = attacker;
		this.setMutexBits(3);
	}

	@Override
	public void resetTask() {
		this.entity.getNavigator().clearPath();
		this.entity.setMoveStrafing(0);
	}

	@Override
	public boolean shouldExecute() {
		if (this.entity.getAttackTarget() != null) {
			if (this.entity.getDistance(this.entity.getAttackTarget()) <= strafeDistance && this.entity.canEntityBeSeen(this.entity.getAttackTarget())) {
				return !EntityHelper.isLookingAt(this.entity, this.entity.getAttackTarget(), 0.1D);
			}
		}
		return false;
	}

	@Override
	public void startExecuting() {
		this.entity.getNavigator().clearPath();
		this.strafeDirection = getRandomDirection();
	}

	@Override
	public boolean shouldContinueExecuting() {
		if (this.entity.getAttackTarget() != null) {
			if (this.entity.getDistance(this.entity.getAttackTarget()) <= strafeDistance && this.strafeDirection != null && this.entity.canEntityBeSeen(this.entity.getAttackTarget())) {
				return !EntityHelper.isLookingAt(this.entity, this.entity.getAttackTarget(), 0.1D);
			}
		}
		return false;
	}

	@Override
	public void updateTask() {
		EntityLivingBase target = this.entity.getAttackTarget();

		this.entity.faceEntity(target, 40F, 40F);
		if (this.strafeDirection == Direction.RIGHT) {
			this.entity.setMoveStrafing(strafeSpeed);
		} else {
			this.entity.setMoveStrafing(-strafeSpeed);
		}

		if (this.entity.getDistance(this.entity.getAttackTarget()) >= 2F) {
			this.entity.getNavigator().tryMoveToEntityLiving(target, strafeSpeed * 3.5);
		} else if (this.entity.getDistance(this.entity.getAttackTarget()) < 2F && this.entity.getNavigator().getPath() != null) {
			this.entity.getNavigator().clearPath();
		} else {
			target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 60));
		}

		if (this.entity.getDistance(this.entity.getAttackTarget()) <= 1.75F) {
			this.entity.attackEntityAsMob(target);
			this.strafeDirection = null;
		}

	}

	private Direction getRandomDirection() {
		return this.entity.getRNG().nextInt(2) == 0 ? Direction.LEFT : Direction.RIGHT;
	}

}
