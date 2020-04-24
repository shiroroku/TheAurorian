package com.elseytd.theaurorian.TileEntities;

import com.elseytd.theaurorian.Entities.Keeper.TAEntity_RunestoneDungeonKeeper;
import com.elseytd.theaurorian.Entities.MoonQueen.TAEntity_MoonQueen;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EntitySelectors;
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
		if (!this.IsSpawned && isPlayerInRange() && !this.getWorld().isRemote) {
			if (this.getWorld().getDifficulty() != EnumDifficulty.PEACEFUL) {
				if (spawnBoss()) {
					this.getWorld().destroyBlock(pos, false);
					IsSpawned = true;
				}
			}
		}
	}

	public boolean spawnBoss() {
		switch (bossEntity) {
		case TAEntity_RunestoneDungeonKeeper.EntityName:
			TAEntity_RunestoneDungeonKeeper boss = new TAEntity_RunestoneDungeonKeeper(this.getWorld());
			boss.moveToBlockPosAndAngles(pos, this.getWorld().rand.nextFloat() * 360F, 0.0F);
			boss.onInitialSpawn(this.getWorld().getDifficultyForLocation(pos), null);
			return this.getWorld().spawnEntity(boss);
		case TAEntity_MoonQueen.EntityName:
			TAEntity_MoonQueen bossq = new TAEntity_MoonQueen(this.getWorld());
			bossq.moveToBlockPosAndAngles(pos, this.getWorld().rand.nextFloat() * 360F, 0.0F);
			bossq.onInitialSpawn(this.getWorld().getDifficultyForLocation(pos), null);
			return this.getWorld().spawnEntity(bossq);
		}
		System.out.println("Failed to spawn boss, entity is null.");
		return false;
	}

	public boolean isPlayerInRange() {
		int dist = 8;
		return this.isAnyPlayerWithinRangeAtInDome(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, dist);
	}

	/**
	 * Same as World.isAnyPlayerWithinRangeAt but limited to a dome shape.
	 */
	public boolean isAnyPlayerWithinRangeAtInDome(double x, double y, double z, double range) {
		for (int playercount = 0; playercount < this.getWorld().playerEntities.size(); ++playercount) {
			EntityPlayer entityplayer = this.getWorld().playerEntities.get(playercount);
			if (EntitySelectors.NOT_SPECTATING.apply(entityplayer)) {
				double d0 = entityplayer.getDistanceSq(x, y, z);
				if (range < 0.0D || d0 < range * range) {
					if (entityplayer.getPosition().getY() >= y) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
