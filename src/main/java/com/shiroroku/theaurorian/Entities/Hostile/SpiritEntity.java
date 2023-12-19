package com.shiroroku.theaurorian.Entities.Hostile;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class SpiritEntity extends EntityMob {

	public static final String EntityName = "spirit";
	public static final ResourceLocation LOOT = new ResourceLocation(AurorianMod.MODID, "entities/" + EntityName);
	public static final float MobScale = 1F;
	private static final DataParameter<Boolean> ARMS_RAISED = EntityDataManager.createKey(SpiritEntity.class, DataSerializers.BOOLEAN);

	public SpiritEntity(World worldIn) {
		super(worldIn);
		this.setSize(0.6F * MobScale, 1.95F * MobScale);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.getDataManager().register(ARMS_RAISED, Boolean.valueOf(false));
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((AurorianConfig.Config_NIGHTMAREMODE ? 40 * AurorianConfig.Config_NIGHTMAREMODE_Multiplier : 20));
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((AurorianConfig.Config_NIGHTMAREMODE ? 0.5D * AurorianConfig.Config_NIGHTMAREMODE_Multiplier : 0.2D));
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue((AurorianConfig.Config_NIGHTMAREMODE ? 6D * AurorianConfig.Config_NIGHTMAREMODE_Multiplier : 3D));
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0D);
	}

	public void setArmsRaised(boolean armsRaised) {
		this.getDataManager().set(ARMS_RAISED, Boolean.valueOf(armsRaised));
	}

	@SideOnly(Side.CLIENT)
	public boolean isArmsRaised() {
		return this.getDataManager().get(ARMS_RAISED).booleanValue();
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new SpiritAIHaunt(this));
		this.tasks.addTask(3, new SpiritAIRunAway(this));
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.25D, false));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	@Override
	public boolean getCanSpawnHere() {
		int i = MathHelper.floor(this.posX);
		int j = MathHelper.floor(this.getEntityBoundingBox().minY);
		int k = MathHelper.floor(this.posZ);
		BlockPos blockpos = new BlockPos(i, j, k);
		return this.world.getBlockState(blockpos.down()).getBlock() == BlockRegistry.Registry.AURORIANGRASS.getBlock() && this.dimension == AurorianConfig.Config_AurorianDimID && super.getCanSpawnHere();
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
		if (this.getRNG().nextBoolean()) {
			return SoundEvents.AMBIENT_CAVE;
		} else {
			return SoundEvents.ENTITY_VEX_AMBIENT;
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_WITHER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_BLAZE_DEATH;
	}

	protected SoundEvent getStepSound() {
		return SoundEvents.ENTITY_ZOMBIE_STEP;
	}
}
