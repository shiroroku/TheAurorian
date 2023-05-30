package com.shiroroku.theaurorian.Entities.Boss;

import com.shiroroku.theaurorian.Entities.Hostile.SpiderlingEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;

public class SpiderAIHang extends EntityAIBase {

	private final SpiderEntity entity;

	private int hangCooldown = 0;
	private int hangTime = 0;
	private final int minDistance = 8;
	private final int maxDistance = 50;

	private double hangingX;
	private double hangingY;
	private double hangingZ;

	public SpiderAIHang(SpiderEntity attacker) {
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
			System.out.println(hangCooldown);
			if (this.hangCooldown == 0) {
				hangingX = this.entity.posX;
				hangingY = this.entity.posY;
				hangingZ = this.entity.posZ;
				for (int y = minDistance; y <= maxDistance; y++) {
					if (this.entity.world.isBlockNormalCube(new BlockPos(hangingX, hangingY, hangingZ).up(y), false)) {
						hangingY = hangingY + y - 3;
						return true;
					}
				}
			} else if (this.hangCooldown > 0) {
				this.hangCooldown--;
			}
		}
		return false;
	}

	@Override
	public void startExecuting() {
		this.entity.getNavigator().clearPath();
		this.entity.setHanging(true);
		this.entity.faceEntity(this.entity.getAttackTarget(), this.entity.getHorizontalFaceSpeed() * 2, this.entity.getVerticalFaceSpeed() * 2);
		hangTime = 200;
		this.entity.setNoGravity(true);
	}

	@Override
	public boolean shouldContinueExecuting() {
		if (hangTime != 0) {
			return true;
		}
		this.entity.setHanging(false);
		this.entity.setNoGravity(false);
		this.hangCooldown = 200;
		return false;
	}

	@Override
	public void updateTask() {
		if (this.entity.posY <= hangingY) {
			this.entity.setPosition(hangingX, this.entity.posY += 0.5D, hangingZ);
		}
		if (hangTime == 100 || hangTime == 150) {
			for (int i = 0; i <= this.entity.getRNG().nextInt(5); i++) {
				SpiderlingEntity spiderling = new SpiderlingEntity(this.entity.world);
				spiderling.setPosition(this.entity.getPosition().getX() - 0.5F, this.entity.getPosition().getY() - 0.5F, this.entity.getPosition().getZ() - 0.5F);
				spiderling.setAttackTarget(this.entity.getAttackTarget());
				this.entity.world.spawnEntity(spiderling);
			}
		}
		this.hangTime--;
	}

}