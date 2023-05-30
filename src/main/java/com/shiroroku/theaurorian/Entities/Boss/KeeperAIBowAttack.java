package com.shiroroku.theaurorian.Entities.Boss;

import com.shiroroku.theaurorian.Registry.ItemRegistry;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class KeeperAIBowAttack extends EntityAIBase {

	public final EntityLiving entityHost;
	public final IRangedAttackMob rangedAttackEntityHost;
	public EntityLivingBase attackTarget;

	public int rangedAttackTime;
	public final double entityMoveSpeed;
	public int seeTime;
	public final int attackIntervalMin;
	public final int maxRangedAttackTime;
	public final float attackRadius;
	public final float maxAttackDistance;

	public KeeperAIBowAttack(IRangedAttackMob attacker, double movespeed, int maxAttackTime, float maxAttackDistanceIn) {
		this(attacker, movespeed, maxAttackTime, maxAttackTime, maxAttackDistanceIn);
	}

	public KeeperAIBowAttack(IRangedAttackMob attacker, double movespeed, int p_i1650_4_, int maxAttackTime, float maxAttackDistanceIn) {
		this.rangedAttackTime = -1;
		if (!(attacker instanceof EntityLivingBase)) {
			throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
		} else {
			this.rangedAttackEntityHost = attacker;
			this.entityHost = (EntityLiving) attacker;
			this.entityMoveSpeed = movespeed;
			this.attackIntervalMin = p_i1650_4_;
			this.maxRangedAttackTime = maxAttackTime;
			this.attackRadius = maxAttackDistanceIn;
			this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
			this.setMutexBits(3);
		}
	}

	@Override
	public void startExecuting() {
		super.startExecuting();
		this.entityHost.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemRegistry.Registry.SILENTWOODBOW.getItem()));
	}

	@Override
	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = this.entityHost.getAttackTarget();
		if (entitylivingbase == null) {
			return false;
		} else {
			if (entitylivingbase.getDistance(this.entityHost) > 5) {
				this.attackTarget = entitylivingbase;
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean shouldContinueExecuting() {
		return this.shouldExecute() || !this.entityHost.getNavigator().noPath();
	}

	@Override
	public void resetTask() {
		this.attackTarget = null;
		this.seeTime = 0;
		this.rangedAttackTime = -1;
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
		if (d0 <= this.maxAttackDistance && this.seeTime >= 20) {
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
			this.rangedAttackTime = MathHelper.floor(f * (this.maxRangedAttackTime - this.attackIntervalMin) + this.attackIntervalMin);
		} else if (this.rangedAttackTime < 0) {
			float f2 = MathHelper.sqrt(d0) / this.attackRadius;
			this.rangedAttackTime = MathHelper.floor(f2 * (this.maxRangedAttackTime - this.attackIntervalMin) + this.attackIntervalMin);
		}
	}

}
