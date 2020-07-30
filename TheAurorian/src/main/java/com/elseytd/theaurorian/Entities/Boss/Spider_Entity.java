package com.elseytd.theaurorian.Entities.Boss;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class Spider_Entity extends EntityMob {

	public static final String EntityName = "spider";
	public static final ResourceLocation LOOT = new ResourceLocation(TAMod.MODID, "entities/" + EntityName);
	private final BossInfoServer bossInfo = (BossInfoServer) (new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
	private static final DataParameter<Boolean> WINDINGUPSPIT = EntityDataManager.createKey(Spider_Entity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> SPITTING = EntityDataManager.createKey(Spider_Entity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> CLIMBING = EntityDataManager.createKey(Spider_Entity.class, DataSerializers.BOOLEAN);

	public Spider_Entity(World worldIn) {
		super(worldIn);
		this.setHealth(this.getMaxHealth());
		this.setSize(1.4F * 2, 0.9F * 2);
		this.experienceValue = 400;
		this.isImmuneToFire = true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.getDataManager().register(WINDINGUPSPIT, Boolean.valueOf(false));
		this.getDataManager().register(SPITTING, Boolean.valueOf(false));
		this.getDataManager().register(CLIMBING, Boolean.valueOf(false));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(180.0D * 1);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new Spider_AISpit(this));
		this.tasks.addTask(4, new Spider_AILeap(this));
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityCow>(this, EntityCow.class, true));
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if (this.world.isRemote && this.ticksExisted % 2 == 0 && isWindingUpSpit()) {
			float distance = 1.6F;
			double sinx = MathHelper.sin(-this.rotationYawHead / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * distance;
			double cosz = MathHelper.cos(this.rotationYawHead / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * distance;
			double motionX = this.getRNG().nextGaussian() * 0.02D;
			double motionY = this.getRNG().nextGaussian() * 0.02D;
			double motionZ = this.getRNG().nextGaussian() * 0.02D;
			this.world.spawnParticle(EnumParticleTypes.CLOUD, this.posX + sinx, this.posY + this.getEyeHeight() - 0.2F, this.posZ + cosz, motionX, motionY, motionZ);
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!this.world.isRemote) {
			this.setBesideClimbableBlock(this.collidedHorizontally);
		}
	}

	@Override
	public boolean isOnLadder() {
		return this.isBesideClimbableBlock();
	}

	@Override
	protected PathNavigate createNavigator(World worldIn) {
		return new PathNavigateClimber(this, worldIn);
	}

	public void setWindingUpSpit(boolean bool) {
		this.getDataManager().set(WINDINGUPSPIT, Boolean.valueOf(bool));
	}

	public boolean isWindingUpSpit() {
		return this.getDataManager().get(WINDINGUPSPIT).booleanValue();
	}

	public void setSpitting(boolean bool) {
		this.getDataManager().set(SPITTING, Boolean.valueOf(bool));
	}

	public boolean isSpitting() {
		return this.getDataManager().get(SPITTING).booleanValue();
	}

	public void setBesideClimbableBlock(boolean bool) {
		this.getDataManager().set(CLIMBING, Boolean.valueOf(bool));
	}

	public boolean isBesideClimbableBlock() {
		return this.getDataManager().get(CLIMBING).booleanValue();
	}

	@Override
	public void addTrackingPlayer(EntityPlayerMP player) {
		super.addTrackingPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(EntityPlayerMP player) {
		super.removeTrackingPlayer(player);
		this.bossInfo.removePlayer(player);
	}

	@Override
	@Nullable
	protected ResourceLocation getLootTable() {
		return LOOT;
	}

	@Override
	protected boolean isValidLightLevel() {
		return true;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
	}

	protected EntityArrow getArrow(float ench) {
		EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.world, this);
		return entitytippedarrow;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_SPIDER_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_SPIDER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SPIDER_DEATH;
	}

	protected SoundEvent getStepSound() {
		return SoundEvents.ENTITY_SPIDER_STEP;
	}

	@Override
	public float getEyeHeight() {
		return 1.1F;
	}

	@Override
	protected void despawnEntity() {
	}

	@Override
	public void setInWeb() {
	}
}
