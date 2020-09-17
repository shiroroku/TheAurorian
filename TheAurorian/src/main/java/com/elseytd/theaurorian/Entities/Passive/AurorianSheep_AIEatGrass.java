package com.elseytd.theaurorian.Entities.Passive;

import com.elseytd.theaurorian.TABlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AurorianSheep_AIEatGrass extends EntityAIBase {
	private final EntityLiving grassEaterEntity;
	private final World entityWorld;
	int eatingGrassTimer;

	public AurorianSheep_AIEatGrass(EntityLiving grassEaterEntityIn) {
		this.grassEaterEntity = grassEaterEntityIn;
		this.entityWorld = grassEaterEntityIn.world;
		this.setMutexBits(7);
	}

	@Override
	public boolean shouldExecute() {
		if (this.grassEaterEntity.getRNG().nextInt(this.grassEaterEntity.isChild() ? 50 : 1000) != 0) {
			return false;
		} else {
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
			return this.entityWorld.getBlockState(blockpos.down()).getBlock() == TABlocks.Registry.AURORIANGRASS.getBlock();
		}
	}

	@Override
	public void startExecuting() {
		this.eatingGrassTimer = 40;
		this.entityWorld.setEntityState(this.grassEaterEntity, (byte) 10);
		this.grassEaterEntity.getNavigator().clearPath();
	}

	@Override
	public void resetTask() {
		this.eatingGrassTimer = 0;
	}

	@Override
	public boolean shouldContinueExecuting() {
		return this.eatingGrassTimer > 0;
	}

	public int getEatingGrassTimer() {
		return this.eatingGrassTimer;
	}

	@Override
	public void updateTask() {
		this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);
		if (this.eatingGrassTimer == 4) {
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
			BlockPos blockpos1 = blockpos.down();
			if (this.entityWorld.getBlockState(blockpos1).getBlock() == TABlocks.Registry.AURORIANGRASS.getBlock()) {
				if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.entityWorld, this.grassEaterEntity)) {
					this.entityWorld.playEvent(2001, blockpos1, Block.getIdFromBlock(TABlocks.Registry.AURORIANGRASS.getBlock()));
					this.entityWorld.setBlockState(blockpos1, TABlocks.Registry.AURORIANDIRT.getBlock().getDefaultState(), 2);
				}
				this.grassEaterEntity.eatGrassBonus();
			}
		}
	}
}
