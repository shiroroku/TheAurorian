package com.shiroroku.theaurorian.Entities.Passive;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AurorianSheepAIEatGrass extends EntityAIBase {
	private final EntityLiving grassEaterEntity;
	private final World entityWorld;
	int eatingGrassTimer;

	public AurorianSheepAIEatGrass(EntityLiving grassEaterEntityIn) {
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
			return this.entityWorld.getBlockState(blockpos.down()).getBlock() == BlockRegistry.Registry.AURORIANGRASS.getBlock();
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
			if (this.entityWorld.getBlockState(blockpos1).getBlock() == BlockRegistry.Registry.AURORIANGRASS.getBlock()) {
				if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.entityWorld, this.grassEaterEntity)) {
					this.entityWorld.playEvent(2001, blockpos1, Block.getIdFromBlock(BlockRegistry.Registry.AURORIANGRASS.getBlock()));
					this.entityWorld.setBlockState(blockpos1, BlockRegistry.Registry.AURORIANDIRT.getBlock().getDefaultState(), 2);
				}
				this.grassEaterEntity.eatGrassBonus();
			}
		}
	}
}
