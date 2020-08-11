package com.elseytd.theaurorian.Entities.Hostile;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.Util.EntityHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class UndeadKnight_Entity extends EntityMob {

	public static final String EntityName = "undeadknight";
	public static final ResourceLocation LOOT = new ResourceLocation(TAMod.MODID, "entities/" + EntityName);
	public static final float MobScale = 1.3F;
	public int maxNearby = 3 * TAConfig.Config_RunestoneDungeonMobDensity;
	
	public UndeadKnight_Entity(World worldIn) {
		super(worldIn);
		setSize(0.6F * MobScale, 1.95F * MobScale);
		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(TAItems.aurorianstonesword));
		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(TAItems.knightchestplate));
		this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(TAItems.knightleggings));
		this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(TAItems.knightboots));
		this.experienceValue = 20;
		this.isImmuneToFire = true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {
		//Drop nothing held
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, false));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		if (super.attackEntityAsMob(entityIn)) {
			if (entityIn instanceof EntityLivingBase) {
				((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.HUNGER, 60));
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean getCanSpawnHere() {
		List<EntityLivingBase> entities = EntityHelper.getEntitiesAround(this.world, this.posX, this.posY, this.posZ, 64, 6, false);
		int maxcount = maxNearby;
		int count = 0;
		for (EntityLivingBase e : entities) {
			if (e instanceof UndeadKnight_Entity) {
				count++;
			}
		}
		return count <= maxcount && super.getCanSpawnHere();
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
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_IRONGOLEM_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_IRONGOLEM_DEATH;
	}

	protected SoundEvent getStepSound() {
		return SoundEvents.ENTITY_ZOMBIE_STEP;
	}

}
