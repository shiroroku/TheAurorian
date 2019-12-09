package com.elseytd.theaurorian.Entities;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class TAEntity_CrystallineSprite extends EntityLiving {

	public static final String EntityName = "crystallinesprite";

	public enum Side {
		LEFT, RIGHT, FRONT, BACK
	}

	public EntityLivingBase parent = null;
	public double rotAngle = 0.0F;
	public Side sideOfParent;
	private float distanceFromParent = 1.5f;

	public TAEntity_CrystallineSprite(World worldIn, EntityLivingBase Parent, Side offset) {
		super(worldIn);
		this.parent = Parent;
		this.sideOfParent = offset;
		this.setSize(1F, 1.5F);
		this.setNoGravity(true);
		this.setSilent(true);
	}

	public TAEntity_CrystallineSprite(World worldIn) {
		this(worldIn, null, Side.LEFT);
	}

	private void updateRotationsAndPos() {
		if (parent != null) {
			this.posY = parent.posY + 2.2;
			switch (sideOfParent) {
			case LEFT:
				this.posX = parent.posX + distanceFromParent;
				this.posZ = parent.posZ;
				break;
			case BACK:
				this.posX = parent.posX + distanceFromParent;
				this.posZ = parent.posZ + distanceFromParent;
				break;
			case FRONT:
				this.posX = parent.posX - distanceFromParent;
				this.posZ = parent.posZ - distanceFromParent;
				break;
			case RIGHT:
				this.posX = parent.posX - distanceFromParent;
				this.posZ = parent.posZ;
				break;
			default:
				this.posX = parent.posX;
				this.posZ = parent.posZ;
				break;
			}

			this.rotationYaw = parent.rotationYawHead;
			this.rotationPitch = parent.rotationPitch;
		}
	}

	@Override
	protected void initEntityAI() {
	}

	@Override
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		IEntityLivingData o = super.onInitialSpawn(difficulty, livingdata);
		updateRotationsAndPos();
		return o;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		updateRotationsAndPos();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		if (this.parent != null) {
			compound.setTag("parent", this.parent.serializeNBT());
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		if (compound.hasKey("parent")) {
			this.parent.deserializeNBT(compound.getCompoundTag("parent"));
		}
	}

	@Override
	public float getEyeHeight() {
		return this.height * 0.5F;
	}

}
