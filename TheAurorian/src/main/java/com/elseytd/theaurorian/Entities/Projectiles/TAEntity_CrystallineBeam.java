package com.elseytd.theaurorian.Entities.Projectiles;

import com.elseytd.theaurorian.TAItems;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class TAEntity_CrystallineBeam extends EntityArrow {
	public static final String EntityName = "crystallinebeam";

	private static final double arrowDamage = 2D;
	private static final float arrowVelocity = 0.5f;

	private void setStats() {
		this.setDamage(arrowDamage);
		this.setNoGravity(true);
	}

	public TAEntity_CrystallineBeam(World worldIn) {
		super(worldIn);
		setStats();
	}

	public TAEntity_CrystallineBeam(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		setStats();
	}

	public TAEntity_CrystallineBeam(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
		setStats();
	}

	@Override
	public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
		float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		float f1 = -MathHelper.sin(pitch * 0.017453292F);
		float f2 = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		this.shoot((double) f, (double) f1, (double) f2, velocity * arrowVelocity, inaccuracy);
		this.motionX += shooter.motionX;
		this.motionZ += shooter.motionZ;

		if (!shooter.onGround) {
			this.motionY += shooter.motionY;
		}
	}

	@Override
	protected void onHit(RayTraceResult raytraceResultIn) {
		Entity entity = raytraceResultIn.entityHit;

		System.out.println(true);
		if (entity != null) {
			float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
			int i = MathHelper.ceil((double) f * TAEntity_CrystallineBeam.arrowDamage);

			if (this.getIsCritical()) {
				i += this.rand.nextInt(i / 2 + 2);
			}

			DamageSource damagesource;

			if (this.shootingEntity == null) {
				damagesource = DamageSource.causeArrowDamage(this, this);
			} else {
				damagesource = DamageSource.causeArrowDamage(this, this.shootingEntity);
			}

			if (this.isBurning() && !(entity instanceof EntityEnderman)) {
				entity.setFire(5);
			}

			if (entity.attackEntityFrom(damagesource, (float) i)) {
				if (entity instanceof EntityLivingBase) {
					EntityLivingBase entitylivingbase = (EntityLivingBase) entity;
					float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
					if (f1 > 0.0F) {
						entitylivingbase.addVelocity(this.motionX * (double) 0.5 * 0.6000000238418579D / (double) f1, 0.1D, this.motionZ * (double) 0.5 * 0.6000000238418579D / (double) f1);
					}
					if (this.shootingEntity instanceof EntityLivingBase) {
						EnchantmentHelper.applyThornEnchantments(entitylivingbase, this.shootingEntity);
						EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase) this.shootingEntity, entitylivingbase);
					}
					this.arrowHit(entitylivingbase);
					if (this.shootingEntity != null && entitylivingbase != this.shootingEntity && entitylivingbase instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP) {
						((EntityPlayerMP) this.shootingEntity).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
					}
				}

				this.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 0.25F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
				if (this.world.isRemote) {
					this.world.spawnParticle(EnumParticleTypes.CLOUD, this.posX, this.posY, this.posZ, 0, 0, 0);
				}

				if (!(entity instanceof EntityEnderman)) {
					this.setDead();
				}
			} else {
				this.motionX *= -0.10000000149011612D;
				this.motionY *= -0.10000000149011612D;
				this.motionZ *= -0.10000000149011612D;
				this.rotationYaw += 180.0F;
				this.prevRotationYaw += 180.0F;

				if (!this.world.isRemote && this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ < 0.0010000000474974513D) {
					this.setDead();
				}
			}
		} else {
			this.motionX = (double) ((float) (raytraceResultIn.hitVec.x - this.posX));
			this.motionY = (double) ((float) (raytraceResultIn.hitVec.y - this.posY));
			this.motionZ = (double) ((float) (raytraceResultIn.hitVec.z - this.posZ));
			float f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
			this.posX -= this.motionX / (double) f2 * 0.05000000074505806D;
			this.posY -= this.motionY / (double) f2 * 0.05000000074505806D;
			this.posZ -= this.motionZ / (double) f2 * 0.05000000074505806D;
			this.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 0.25F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
			if (this.world.isRemote) {
				this.world.spawnParticle(EnumParticleTypes.CLOUD, this.posX, this.posY, this.posZ, 0, 0, 0);
			}
			this.inGround = true;
			this.arrowShake = 7;
			this.setIsCritical(false);
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (this.inGround || this.ticksExisted > 200) {
			this.setDead();
		}

	}

	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(TAItems.crystallinesprite);
	}
}
