package com.shiroroku.theaurorian.Entities.Boss;

import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.Registry.EnchantmentRegistry;
import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class KeeperEntity extends EntityMob implements IRangedAttackMob {

	public static final String EntityName = "runestonedungeonkeeper";
	public static final ResourceLocation LOOT = new ResourceLocation(AurorianMod.MODID, "entities/" + EntityName);
	private final BossInfoServer bossInfo = (BossInfoServer) (new BossInfoServer(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);

	public KeeperEntity(World worldIn) {
		super(worldIn);
		this.setHealth(this.getMaxHealth());
		this.setSize(0.7F * 2F, 4.2F);
		ItemStack s = new ItemStack(ItemRegistry.Registry.MOONSTONESWORD.getItem());
		s.addEnchantment(EnchantmentRegistry.lightning, 3);
		s.addEnchantment(Enchantments.KNOCKBACK, 2);
		this.setHeldItem(EnumHand.MAIN_HAND, s);
		this.experienceValue = 350;
		this.isImmuneToFire = true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(175.0D * AurorianConfig.Config_RunestoneKeeperHealthMuliplier);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D * AurorianConfig.Config_RunestoneKeeperDamageMuliplier);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new KeeperAIBowAttackBarrage(this, 0.25F, 2, 40F));
		this.tasks.addTask(2, new KeeperAIBowAttack(this, 0.85F, 20, 40F));
		this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.6D, false) {
			@Override
			public void startExecuting() {
				super.startExecuting();
				ItemStack s = new ItemStack(ItemRegistry.Registry.MOONSTONESWORD.getItem());
				s.addEnchantment(EnchantmentRegistry.lightning, 3);
				s.addEnchantment(Enchantments.KNOCKBACK, 2);
				this.attacker.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, s);
			}
		});
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		if (super.attackEntityAsMob(entityIn)) {
			if (entityIn instanceof EntityLivingBase) {
				((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200));
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if (this.world.isRemote && this.ticksExisted % 2 == 0 && this.getHealth() <= this.getMaxHealth() * 0.30F) {
			double motionX = this.rand.nextGaussian() * 0.02D;
			double motionY = this.rand.nextGaussian() * 0.1D;
			double motionZ = this.rand.nextGaussian() * 0.02D;
			this.world.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX + this.rand.nextFloat(), this.posY + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat(), motionX, motionY, motionZ);
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
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		EntityArrow entityarrow = this.getArrow(distanceFactor);
		double d0 = target.posX - this.posX;
		double d1 = target.getEntityBoundingBox().minY + target.height / 3.0F - entityarrow.posY;
		double d2 = target.posZ - this.posZ;
		double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
		entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, 9F);
		this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		this.world.spawnEntity(entityarrow);
	}

	protected EntityArrow getArrow(float ench) {
		EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.world, this);
		return entitytippedarrow;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_WITHER_SKELETON_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_WITHER_SKELETON_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_WITHER_SKELETON_DEATH;
	}

	protected SoundEvent getStepSound() {
		return SoundEvents.ENTITY_WITHER_SKELETON_STEP;
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {
	}

	@Override
	protected void despawnEntity() {
	}
}
