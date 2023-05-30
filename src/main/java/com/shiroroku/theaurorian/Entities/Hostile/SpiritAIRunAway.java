package com.shiroroku.theaurorian.Entities.Hostile;

import com.shiroroku.theaurorian.Util.EntityHelper;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.Vec3d;

public class SpiritAIRunAway extends EntityAIBase {

	private final SpiritEntity entity;
	private Path pathaway;

	public SpiritAIRunAway(SpiritEntity attacker) {
		this.entity = attacker;
		this.setMutexBits(3);
	}

	@Override
	public void resetTask() {
		this.entity.getNavigator().clearPath();
	}

	@Override
	public boolean shouldExecute() {
		if (this.entity.getAttackTarget() != null) {
			if (EntityHelper.isLookingAt(this.entity, this.entity.getAttackTarget(), 0.1D) && this.entity.canEntityBeSeen(this.entity.getAttackTarget())) {
				Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.entity, 16, 10, new Vec3d(this.entity.getAttackTarget().posX, this.entity.getAttackTarget().posY, this.entity.getAttackTarget().posZ));

				if (vec3d != null) {
					if (!(this.entity.getAttackTarget().getDistanceSq(vec3d.x, vec3d.y, vec3d.z) < this.entity.getAttackTarget().getDistanceSq(this.entity))) {
						this.pathaway = this.entity.getNavigator().getPathToXYZ(vec3d.x, vec3d.y, vec3d.z);
						return pathaway != null;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void startExecuting() {
		this.entity.getNavigator().clearPath();
		this.entity.getNavigator().setPath(this.pathaway, 2D);
	}

	@Override
	public boolean shouldContinueExecuting() {
		if (this.entity.getAttackTarget() != null) {
			return !this.entity.getNavigator().noPath();
		}
		return false;
	}

	@Override
	public void updateTask() {
	}

}
