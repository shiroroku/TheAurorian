package com.elseytd.theaurorian.Entities.Keeper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.util.math.MathHelper;

public class TAEntityAI_RangedAttackBarrage extends TAEntityAI_RangedAttack {

	public TAEntityAI_RangedAttackBarrage(IRangedAttackMob attacker, double movespeed, int maxAttackTime, float maxAttackDistanceIn) {
		super(attacker, movespeed, maxAttackTime, maxAttackTime, maxAttackDistanceIn);
	}

	@Override
	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = this.entityHost.getAttackTarget();
		if (entitylivingbase == null) {
			return false;
		} else {
			if (this.entityHost.getHealth() <= this.entityHost.getMaxHealth() * 0.20F) {
				this.attackTarget = entitylivingbase;
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public void updateTask() {
		double d0 = this.entityHost.getDistanceSq(this.attackTarget.posX, this.attackTarget.getEntityBoundingBox().minY, this.attackTarget.posZ);
		boolean flag = this.entityHost.getEntitySenses().canSee(this.attackTarget);
		if (flag) {
			++this.seeTime;
		} else {
			this.seeTime = 0;
		}
		if (d0 <= (double) this.maxAttackDistance && this.seeTime >= 20) {
			this.entityHost.getNavigator().clearPath();
		} else {
			this.entityHost.getNavigator().tryMoveToEntityLiving(this.attackTarget, this.entityMoveSpeed);
		}
		this.entityHost.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);
		if (--this.rangedAttackTime == 0) {
			if (!flag) {
				return;
			}
			float f = MathHelper.sqrt(d0) / this.attackRadius;
			float lvt_5_1_ = MathHelper.clamp(f, 0.1F, 1.0F);
			this.rangedAttackEntityHost.attackEntityWithRangedAttack(this.attackTarget, lvt_5_1_);
			this.rangedAttackTime = MathHelper.floor(f * (float) (this.maxRangedAttackTime - this.attackIntervalMin) + (float) this.attackIntervalMin);
		} else if (this.rangedAttackTime < 0) {
			float f2 = MathHelper.sqrt(d0) / this.attackRadius;
			this.rangedAttackTime = MathHelper.floor(f2 * (float) (this.maxRangedAttackTime - this.attackIntervalMin) + (float) this.attackIntervalMin);
		}
	}

}
