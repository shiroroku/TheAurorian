package com.elseytd.theaurorian.Entities.Hostile;

import java.util.List;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAUtil;
import com.elseytd.theaurorian.Entities.Projectiles.CrystallineBeam_Entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CrystallineSprite_Entity extends EntityMob implements IRangedAttackMob {

	public static final String EntityName = "crystallinesprite";
	private float heightOffset = 0.5F;
	private int heightOffsetUpdateTime;
	public int maxNearby = 5 * TAConfig.Config_MoonTempleMobDensity;

	public CrystallineSprite_Entity(World worldIn) {
		super(worldIn);
		this.experienceValue = 25;
		this.setSize(1F, 1.5F);
		this.setSilent(true);
	}

	@Override
	public boolean getCanSpawnHere() {
		int i = MathHelper.floor(this.posX);
		int j = MathHelper.floor(this.getEntityBoundingBox().minY);
		int k = MathHelper.floor(this.posZ);
		BlockPos blockpos = new BlockPos(i, j, k);
		List<EntityLivingBase> entities = TAUtil.Entity.getEntitiesAround(this.world, this.posX, this.posY, this.posZ, 64, 30, false);
		int maxcount = maxNearby;
		int count = 0;
		for (EntityLivingBase e : entities) {
			if (e instanceof CrystallineSprite_Entity) {
				count++;
			}
		}
		return count <= maxcount && this.world.getBlockState(blockpos.down()).getBlock() == TABlocks.moontemplebricks && this.dimension == TAConfig.Config_AurorianDimID && super.getCanSpawnHere();
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(2, new CrystallineSprite_AIRangedAttack(this, 0.85F, 40, 40F));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
	}

	@Override
	public float getEyeHeight() {
		return this.height * 0.5F;
	}

	@Override
	protected boolean isValidLightLevel() {
		return true;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		EntityArrow entityarrow = this.getArrow(distanceFactor);
		double d0 = target.posX - this.posX;
		double d1 = target.getEntityBoundingBox().minY + (double) (target.height / 3.0F) - entityarrow.posY;
		double d2 = target.posZ - this.posZ;
		double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
		entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, 9F);
		this.playSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		this.world.spawnEntity(entityarrow);
	}

	@Override
	public void onLivingUpdate() {
		if (!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.6D;
		}
		super.onLivingUpdate();
	}

	@Override
	protected void updateAITasks() {
		--this.heightOffsetUpdateTime;

		if (this.heightOffsetUpdateTime <= 0) {
			this.heightOffsetUpdateTime = 100;
			this.heightOffset = 0.5F + (float) this.rand.nextGaussian() * 3.0F;
		}

		EntityLivingBase entitylivingbase = this.getAttackTarget();

		if (entitylivingbase != null && entitylivingbase.posY + (double) entitylivingbase.getEyeHeight() > this.posY + (double) this.getEyeHeight() + (double) this.heightOffset) {
			this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
			this.isAirBorne = true;
		}

		super.updateAITasks();
	}

	protected EntityArrow getArrow(float ench) {
		CrystallineBeam_Entity entitytippedarrow = new CrystallineBeam_Entity(this.world, this);
		return entitytippedarrow;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_GENERIC_HURT;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {

	}
}
