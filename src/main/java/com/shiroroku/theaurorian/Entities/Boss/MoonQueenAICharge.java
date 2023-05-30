package com.shiroroku.theaurorian.Entities.Boss;

import com.shiroroku.theaurorian.Registry.ItemRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class MoonQueenAICharge extends EntityAIBase {

	private final MoonQueenEntity entity;

	private int chargeTime = 0;
	private final int maxChargeTime = 20;
	private int chargeCooldown = 0;
	private int chargeWindup = 0;
	private final double chargeSpeed = 3.5D;
	private final int minDistance = 2;
	private final int maxDistance = 20;
	private double targetX;
	private double targetY;
	private double targetZ;
	private final int attackReach = 2;
	private boolean chainCharge = true;
	private int maxChainCharges = 0;
	private int queuedChainCharges = 0;

	/**
	 * Does charge attack.
	 *
	 * @param chainCharge If the entity should charge immediately after if they
	 *                    miss, will do more charges at lower health.
	 */
	public MoonQueenAICharge(MoonQueenEntity attacker, boolean chainCharge) {
		this.entity = attacker;
		this.chainCharge = chainCharge;
		this.setMutexBits(3);
	}

	@Override
	public boolean isInterruptible() {
		return false;
	}

	@Override
	public boolean shouldExecute() {
		//If we have a target and in range for a charge. Also if we can see them and our charge isnt on cooldown
		if (this.entity.getAttackTarget() != null) {
			float distToTarget = this.entity.getDistance(this.entity.getAttackTarget());
			if (distToTarget >= this.minDistance && distToTarget <= this.maxDistance) {
				if (this.entity.getEntitySenses().canSee(this.entity.getAttackTarget())) {
					if (this.chargeCooldown == 0) {
						return true;
					} else if (this.chargeCooldown > 0) {
						this.chargeCooldown--;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void startExecuting() {
		this.entity.getNavigator().clearPath();
		this.entity.setCharging(true);
		this.startChargeAttack();
	}

	@Override
	public boolean shouldContinueExecuting() {
		//If we have a path and a target that we can see and our charge isnt on cooldown
		if (!this.entity.getNavigator().noPath() || this.entity.getAttackTarget() != null) {
			if (this.entity.getAttackTarget() != null) {
				if (this.entity.getEntitySenses().canSee(this.entity.getAttackTarget())) {
					if (this.chargeCooldown == 0) {
						return true;
					}
				}
			} else {
				if (this.chargeCooldown == 0) {
					return true;
				}
			}
		}
		this.entity.setCharging(false);
		return false;
	}

	@Override
	public void updateTask() {
		EntityLivingBase target = this.entity.getAttackTarget();

		if (this.chargeTime > 0) {
			this.chargeTime--;
		}

		//A tick before we do a charge we set location, so the entity has a chance to dodge.
		if (this.chargeWindup == 1) {
			this.setChargeLocation(this.entity.getAttackTarget());
		}

		if (this.chargeWindup == 0) {
			//Look at where we are charging and run to the position
			this.entity.getLookHelper().setLookPosition(this.targetX, this.targetY + this.entity.getEyeHeight(), this.targetZ, this.entity.getHorizontalFaceSpeed(), this.entity.getVerticalFaceSpeed());
			this.entity.setWindingUpCharge(false);
			this.entity.getNavigator().tryMoveToXYZ(this.targetX, this.targetY, this.targetZ, this.chargeSpeed);

			//If we are in range of our target or finish our path, we finish the charge
			if (this.entity.getAttackTarget() != null) {
				if (this.entity.getDistance(this.entity.getAttackTarget()) <= this.attackReach) {
					this.finishChargeAttack(target);
				} else if (this.entity.getNavigator().noPath()) {
					this.finishChargeAttack(null);
				}
			} else if (this.entity.getNavigator().noPath()) {
				this.finishChargeAttack(null);
			}
		} else {
			//Look at our target while winding up
			this.entity.getLookHelper().setLookPosition(target.posX, target.posY + target.getEyeHeight(), target.posZ, this.entity.getHorizontalFaceSpeed(), this.entity.getVerticalFaceSpeed());
			this.entity.setWindingUpCharge(true);
			this.chargeWindup--;
		}
	}

	private void startChargeAttack() {
		this.chargeTime = this.maxChargeTime;

		//Set held item to shield and block
		this.entity.setHeldItem(EnumHand.OFF_HAND, new ItemStack(ItemRegistry.Registry.MOONSHIELD.getItem()));
		this.entity.setActiveHand(EnumHand.OFF_HAND);
		//Start windup timer
		this.chargeWindup = 40 - this.entity.getRNG().nextInt(10);

		//If chaincharge is enabled, we add charges to be done if the first charge misses, depending on health
		if (this.chainCharge) {
			double healthScale = this.entity.getHealth() / this.entity.getMaxHealth();

			if (healthScale >= 0.75) {
				this.maxChainCharges = 0;
			} else if (healthScale >= 0.50 && healthScale < 0.75) {
				this.maxChainCharges = 1;
			} else if (healthScale >= 0.25 && healthScale < 0.50) {
				this.maxChainCharges = 2;
			} else if (healthScale >= 0 && healthScale < 0.25) {
				this.maxChainCharges = 3;
			}
		}
	}

	@Override
	public void resetTask() {
		this.entity.getNavigator().clearPath();
		this.entity.resetActiveHand();
	}

	private void finishChargeAttack(EntityLivingBase target) {
		//We set our cooldown before we can do a charge again and stop blocking
		this.chargeCooldown = 60 - this.entity.getRNG().nextInt(10);
		this.entity.resetActiveHand();
		//If we didnt miss our target, attack them and knock them up. Else if we missed, we do chain charges
		if (target != null) {
			this.entity.setChargeHit(true);
			this.entity.attackEntityAsMob(target);
			target.motionY = target.motionY + 0.25;
		} else {
			if (this.chainCharge) {
				this.handleChainCharge();
			}
		}

		this.entity.setCharging(false);
	}

	private void handleChainCharge() {
		//If we are able to chaincharge
		if (this.maxChainCharges != 0) {
			//If we have charges left in queue, reset charge cooldown and immediately charge again.
			//Else we reset our queue and let the regular charge cooldown go
			if (this.queuedChainCharges != 0) {
				this.chargeCooldown = 5;
				this.queuedChainCharges--;
			} else {
				this.queuedChainCharges = this.maxChainCharges;
			}
		}
	}

	private void setChargeLocation(EntityLivingBase target) {
		this.targetX = target.posX;
		this.targetY = target.posY;
		this.targetZ = target.posZ;
	}

}