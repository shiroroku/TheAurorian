package com.shiroroku.theaurorian.Entities.Boss;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class MoonQueenEntity extends EntityMob {

	public static final String EntityName = "moonqueen";
	public static final ResourceLocation LOOT = new ResourceLocation(AurorianMod.MODID, "entities/" + EntityName);
	public static final ResourceLocation CHEST_LOOT = new ResourceLocation(AurorianMod.MODID, "chests/moontemplehigh");
	public static final float MobScale = 0.9F;
	private final BossInfoServer bossInfo = (BossInfoServer) (new BossInfoServer(this.getDisplayName(), BossInfo.Color.PINK, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
	private static final DataParameter<Boolean> CHARGING = EntityDataManager.createKey(MoonQueenEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> WINDINGUPCHARGE = EntityDataManager.createKey(MoonQueenEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> CHARGEHIT = EntityDataManager.createKey(MoonQueenEntity.class, DataSerializers.BOOLEAN);

	public MoonQueenEntity(World worldIn) {
		super(worldIn);
		this.setHealth(this.getMaxHealth());
		this.setSize(0.6F * MobScale, 1.95F * MobScale);
		this.experienceValue = 500;
		this.isImmuneToFire = true;
		this.stepHeight = 1F;
		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ItemRegistry.Registry.MOONSTONESWORD.getItem()));
		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemRegistry.Registry.KNIGHTARMORCHESTPLATE.getItem()));
		this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemRegistry.Registry.KNIGHTARMORLEGGINGS.getItem()));
		this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemRegistry.Registry.KNIGHTARMORBOOTS.getItem()));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D * AurorianConfig.Config_MoonQueenHealthMuliplier);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D * AurorianConfig.Config_MoonQueenDamageMuliplier);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.85D);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new MoonQueenAICharge(this, true));
		this.tasks.addTask(2, new MoonQueenAISideStrafe(this));
		this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.35D, false));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.getDataManager().register(WINDINGUPCHARGE, Boolean.valueOf(false));
		this.getDataManager().register(CHARGING, Boolean.valueOf(false));
		this.getDataManager().register(CHARGEHIT, Boolean.valueOf(false));
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if (this.world.isRemote && this.ticksExisted % 2 == 0 && this.isWindingUpCharge()) {
			double motionX = this.getRNG().nextGaussian() * 0.02D;
			double motionY = this.getRNG().nextGaussian() * 0.1D;
			double motionZ = this.getRNG().nextGaussian() * 0.02D;
			this.world.spawnParticle(EnumParticleTypes.VILLAGER_ANGRY, this.posX + this.getRNG().nextFloat(), this.posY + this.getRNG().nextFloat() * this.height, this.posZ + this.getRNG().nextFloat(), motionX, motionY, motionZ);
		}

		if (this.didChargeHit()) {
			if (this.world.isRemote) {
				this.world.playSound(this.posX, this.posY, this.posZ, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS, 1F, 1.5F, false);
			}
			this.setChargeHit(false);
		}
	}

	public void setWindingUpCharge(boolean bool) {
		this.getDataManager().set(WINDINGUPCHARGE, Boolean.valueOf(bool));
	}

	public boolean isWindingUpCharge() {
		return this.getDataManager().get(WINDINGUPCHARGE).booleanValue();
	}

	public void setCharging(boolean bool) {
		this.getDataManager().set(CHARGING, Boolean.valueOf(bool));
	}

	public boolean isCharging() {
		return this.getDataManager().get(CHARGING).booleanValue();
	}

	public void setChargeHit(boolean bool) {
		this.getDataManager().set(CHARGEHIT, Boolean.valueOf(bool));
	}

	public boolean didChargeHit() {
		return this.getDataManager().get(CHARGEHIT).booleanValue();
	}

	@Override
	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {
		//Drop nothing held
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		int distance = 50;
		for (int x = 0; x <= distance; x++) {
			for (int y = 0; y <= distance; y++) {
				for (int z = 0; z <= distance; z++) {
					int offs = distance / 2;
					BlockPos p = new BlockPos(x + this.getPosition().getX() - offs, y + this.getPosition().getY() - offs, z + this.getPosition().getZ() - offs);
					if (this.world.getBlockState(p).getBlock() == BlockRegistry.Registry.MYSTICALBARRIER.getBlock()) {
						this.world.destroyBlock(p, false);
					}
				}
			}
		}

		this.world.setBlockState(this.getPosition(), Blocks.CHEST.getDefaultState());
		TileEntity te = this.world.getTileEntity(this.getPosition());
		if (te != null) {
			if (te instanceof TileEntityChest) {
				((TileEntityChest) te).setLootTable(MoonQueenEntity.CHEST_LOOT, this.getRNG().nextLong());
			}
		} else {
			System.err.println("Failed to spawn Moon Queen loot box!");
		}

	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		if (super.attackEntityAsMob(entityIn)) {
			if (entityIn instanceof EntityLivingBase) {
				((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 50));
			}
			return true;
		} else {
			return false;
		}
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

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_GHAST_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		if (this.isActiveItemStackBlocking()) {
			return SoundEvents.BLOCK_ANVIL_PLACE;
		}
		return SoundEvents.ENTITY_IRONGOLEM_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_GHAST_DEATH;
	}

	protected SoundEvent getStepSound() {
		return SoundEvents.ENTITY_IRONGOLEM_STEP;
	}

	@Override
	protected void despawnEntity() {
	}
}
