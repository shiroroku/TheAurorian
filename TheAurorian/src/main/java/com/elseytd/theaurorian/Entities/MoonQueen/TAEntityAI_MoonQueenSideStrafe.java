package com.elseytd.theaurorian.Entities.MoonQueen;

import com.elseytd.theaurorian.TAItems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class TAEntityAI_MoonQueenSideStrafe extends EntityAIBase {

	private enum Direction {
		LEFT, RIGHT;
	}

	private TAEntity_MoonQueen entity;

	private Direction strafeDirection;
	private float strafeDistance = 8F;
	private float strafeSpeed = 0.3F;
	private float strafeTimer;

	public TAEntityAI_MoonQueenSideStrafe(TAEntity_MoonQueen attacker) {
		this.entity = attacker;
		this.setMutexBits(3);
	}

	@Override
	public void resetTask() {
		this.entity.resetActiveHand();
		this.entity.getNavigator().clearPath();
		this.entity.setMoveStrafing(0);
	}

	@Override
	public boolean shouldExecute() {
		if (this.entity.getAttackTarget() != null) {
			if (this.entity.getDistance(this.entity.getAttackTarget()) <= strafeDistance && !this.entity.isCharging()) {
				if (this.strafeTimer == 0) {
					return true;
				} else if (this.strafeTimer > 0) {
					this.strafeTimer--;
				}
			}
		}
		return false;
	}

	@Override
	public void startExecuting() {
		this.entity.getNavigator().clearPath();
		this.strafeDirection = getRandomDirection();
		this.strafeTimer = 10;
		this.entity.setHeldItem(EnumHand.OFF_HAND, new ItemStack(TAItems.moonshield));
		this.entity.setActiveHand(EnumHand.OFF_HAND);
	}

	@Override
	public boolean shouldContinueExecuting() {
		if (this.entity.getAttackTarget() != null && !this.entity.isCharging()) {
			if (this.entity.getDistance(this.entity.getAttackTarget()) <= strafeDistance && this.strafeDirection != null) {
				return true;
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
		
		if(this.entity.getDistance(this.entity.getAttackTarget()) <= 2.5F) {
			this.entity.attackEntityAsMob(target);
			this.strafeDirection = null;
		}

	}

	private Direction getRandomDirection() {
		return this.entity.getRNG().nextInt(2) == 0 ? Direction.LEFT : Direction.RIGHT;
	}

}
