package com.shiroroku.theaurorian.TileEntities;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;

public class BossSpawnerTileEntity extends TileEntity implements ITickable {

	private boolean IsSpawned;
	private String bossEntity;

	public BossSpawnerTileEntity() {
		IsSpawned = false;
	}

	public BossSpawnerTileEntity(String mobName) {
		this();
		this.bossEntity = mobName;
	}

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

	private boolean spawnBoss() {
		EntityLiving boss = (EntityLiving) EntityList.createEntityByIDFromName(new ResourceLocation(AurorianMod.MODID, bossEntity), this.getWorld());
		boss.moveToBlockPosAndAngles(pos, this.getWorld().rand.nextFloat() * 360F, 0.0F);
		boss.onInitialSpawn(this.getWorld().getDifficultyForLocation(pos), null);
		return this.getWorld().spawnEntity(boss);
	}

	private boolean isPlayerInRange() {
		int dist = 8;
		return this.isAnyPlayerWithinRangeAtInDome(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, dist);
	}

	/**
	 * Same as World.isAnyPlayerWithinRangeAt but limited to a dome shape.
	 */
	private boolean isAnyPlayerWithinRangeAtInDome(double x, double y, double z, double range) {
		for (int playercount = 0; playercount < this.getWorld().playerEntities.size(); ++playercount) {
			EntityPlayer entityplayer = this.getWorld().playerEntities.get(playercount);
			if (EntitySelectors.NOT_SPECTATING.apply(entityplayer)) {
				double d0 = entityplayer.getDistanceSq(x, y, z);
				if (range < 0.0D || d0 < range * range && !entityplayer.isCreative()) {
					if (entityplayer.getPosition().getY() >= y) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
