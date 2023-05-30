package com.shiroroku.theaurorian.Entities.Hostile;

import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import com.shiroroku.theaurorian.Util.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
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

import javax.annotation.Nullable;
import java.util.List;

public class UndeadKnightEntity extends EntityMob {

	public static final String EntityName = "undeadknight";
	public static final ResourceLocation LOOT = new ResourceLocation(AurorianMod.MODID, "entities/" + EntityName);
	public static final float MobScale = 1.3F;
	public int maxNearby = 3 * AurorianConfig.Config_RunestoneDungeonMobDensity;

	public UndeadKnightEntity(World worldIn) {
		super(worldIn);
		this.setSize(0.6F * MobScale, 1.95F * MobScale);
		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ItemRegistry.Registry.AURORIANSTONESWORD.getItem()));
		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemRegistry.Registry.KNIGHTARMORCHESTPLATE.getItem()));
		this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemRegistry.Registry.KNIGHTARMORLEGGINGS.getItem()));
		this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemRegistry.Registry.KNIGHTARMORBOOTS.getItem()));
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
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((AurorianConfig.Config_NIGHTMAREMODE ? 40 * AurorianConfig.Config_NIGHTMAREMODE_Multiplier : 20));
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((AurorianConfig.Config_NIGHTMAREMODE ? 0.5D * AurorianConfig.Config_NIGHTMAREMODE_Multiplier : 0.20D));
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue((AurorianConfig.Config_NIGHTMAREMODE ? 6D * AurorianConfig.Config_NIGHTMAREMODE_Multiplier : 3D));
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
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
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
		int maxcount = this.maxNearby;
		int count = 0;
		for (EntityLivingBase e : entities) {
			if (e instanceof UndeadKnightEntity) {
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
		return this.maxNearby;
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
