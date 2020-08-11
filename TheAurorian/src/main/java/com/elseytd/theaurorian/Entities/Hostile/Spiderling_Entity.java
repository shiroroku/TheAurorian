package com.elseytd.theaurorian.Entities.Hostile;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.Util.EntityHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class Spiderling_Entity extends EntityMob {

	public static final String EntityName = "spiderling";
	public static final ResourceLocation LOOT = new ResourceLocation(TAMod.MODID, "entities/" + EntityName);
	private static final DataParameter<Boolean> CLIMBING = EntityDataManager.createKey(Spiderling_Entity.class, DataSerializers.BOOLEAN);
	public int maxNearby = 3 * TAConfig.Config_DarkstoneDungeonMobDensity;

	public Spiderling_Entity(World worldIn) {
		super(worldIn);
		this.setHealth(this.getMaxHealth());
		this.setSize(1.4F * 0.5F, 0.9F * 0.5F);
		this.experienceValue = 10;
		this.isImmuneToFire = false;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.getDataManager().register(CLIMBING, Boolean.valueOf(false));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(7D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.7D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(3, new Spiderling_AILeap(this));
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 0.4D, true));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
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

	public static void handleFallEvent(LivingFallEvent e) {
		if (e.getEntity() instanceof Spiderling_Entity) {
			e.setCanceled(true);
			return;
		}
	}

	public void setBesideClimbableBlock(boolean bool) {
		this.getDataManager().set(CLIMBING, Boolean.valueOf(bool));
	}

	public boolean isBesideClimbableBlock() {
		return this.getDataManager().get(CLIMBING).booleanValue();
	}

	@Override
	@Nullable
	protected ResourceLocation getLootTable() {
		return LOOT;
	}

	@Override
	public boolean getCanSpawnHere() {
		List<EntityLivingBase> entities = EntityHelper.getEntitiesAround(this.world, this.posX, this.posY, this.posZ, 64, 6, false);
		int maxcount = this.maxNearby;
		int count = 0;
		for (EntityLivingBase e : entities) {
			if (e instanceof Spiderling_Entity) {
				count++;
			}
		}
		return count <= maxcount && super.getCanSpawnHere();
	}

	@Override
	protected boolean isValidLightLevel() {
		return true;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 3;
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
		return 0.25F;
	}

	@Override
	public void setInWeb() {
	}
}
