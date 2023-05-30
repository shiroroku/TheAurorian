package com.shiroroku.theaurorian.Entities.Hostile;

import net.minecraft.entity.ai.EntityAIAttackMelee;

public class DisturbedHollowAIAttack extends EntityAIAttackMelee {
	private final DisturbedHollowEntity zombie;
	private int raiseArmTicks;

	public DisturbedHollowAIAttack(DisturbedHollowEntity zombieIn, double speedIn, boolean longMemoryIn) {
		super(zombieIn, speedIn, longMemoryIn);
		this.zombie = zombieIn;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting() {
		super.startExecuting();
		this.raiseArmTicks = 0;
	}

	/**
	 * Reset the task's internal state. Called when this task is interrupted by
	 * another one
	 */
	@Override
	public void resetTask() {
		super.resetTask();
		this.zombie.setArmsRaised(false);
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	@Override
	public void updateTask() {
		super.updateTask();
		++this.raiseArmTicks;

		this.zombie.setArmsRaised(this.raiseArmTicks >= 5 && this.attackTick < 10);
	}
}
