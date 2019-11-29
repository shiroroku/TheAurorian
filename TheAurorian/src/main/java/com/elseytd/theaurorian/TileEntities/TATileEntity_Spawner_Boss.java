package com.elseytd.theaurorian.TileEntities;

import com.elseytd.theaurorian.Entities.Keeper.TAEntity_RunestoneDungeonKeeper;
import com.elseytd.theaurorian.Entities.MoonQueen.TAEntity_MoonQueen;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.world.EnumDifficulty;

public class TATileEntity_Spawner_Boss extends TileEntity implements ITickable {

	protected boolean IsSpawned = false;
	public String bossEntity;

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.bossEntity = compound.getString("containedboss");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setString("containedboss", bossEntity);
		return compound;
	}

	@Override
	public void update() {
		if (world.isRemote) {
			double rx = pos.getX() + world.rand.nextFloat();
			double ry = pos.getY() + world.rand.nextFloat();
			double rz = pos.getZ() + world.rand.nextFloat();
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, rx, ry, rz, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(EnumParticleTypes.FLAME, rx, ry, rz, 0.0D, 0.0D, 0.0D);
		} else if (!IsSpawned && isPlayerInRange()) {
			if (world.getDifficulty() != EnumDifficulty.PEACEFUL) {
				if (spawnBoss()) {
					world.destroyBlock(pos, false);
					IsSpawned = true;
				}
			}
		}
	}

	public boolean spawnBoss() {
		switch (bossEntity) {
		case TAEntity_RunestoneDungeonKeeper.EntityName:
			TAEntity_RunestoneDungeonKeeper boss = new TAEntity_RunestoneDungeonKeeper(world);
			boss.moveToBlockPosAndAngles(pos, world.rand.nextFloat() * 360F, 0.0F);
			boss.onInitialSpawn(world.getDifficultyForLocation(pos), null);
			return world.spawnEntity(boss);
		case TAEntity_MoonQueen.EntityName:
			TAEntity_MoonQueen bossq = new TAEntity_MoonQueen(world);
			bossq.moveToBlockPosAndAngles(pos, world.rand.nextFloat() * 360F, 0.0F);
			bossq.onInitialSpawn(world.getDifficultyForLocation(pos), null);
			return world.spawnEntity(bossq);
		}
		System.out.println("Failed to spawn boss, entity is null.");
		return false;
	}

	public boolean isPlayerInRange() {
		int dist = 8;
		return world.isAnyPlayerWithinRangeAt(pos.getX() + 0.5D, pos.getY() + 0.5D + dist - 1, pos.getZ() + 0.5D, dist);
	}
}
