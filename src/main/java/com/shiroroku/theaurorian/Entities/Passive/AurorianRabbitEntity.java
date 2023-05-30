package com.shiroroku.theaurorian.Entities.Passive;

import com.shiroroku.theaurorian.Blocks.DungeonStone;
import com.shiroroku.theaurorian.Blocks.DungeonStoneLamp;
import com.shiroroku.theaurorian.Blocks.DungeonStoneSmooth;
import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.*;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Set;

public class AurorianRabbitEntity extends EntityAnimal {

	public static final String EntityName = "aurorianrabbit";
	public static final ResourceLocation LOOT = new ResourceLocation(AurorianMod.MODID, "entities/" + EntityName);
	private static final Set<Item> BREEDING_ITEMS = Sets.newHashSet(ItemRegistry.Registry.SILKBERRY.getItem());
	private int jumpTicks;
	private int jumpDuration;
	private boolean wasOnGround;
	private int currentMoveTypeDuration;
	private int carrotTicks;

	public AurorianRabbitEntity(World worldIn) {
		super(worldIn);
		this.spawnableBlock = BlockRegistry.Registry.AURORIANGRASS.getBlock();
		this.setSize(0.4F, 0.5F);
		this.jumpHelper = new AurorianRabbitEntity.RabbitJumpHelper(this);
		this.moveHelper = new AurorianRabbitEntity.RabbitMoveHelper(this);
		this.setMovementSpeed(0.0D);
	}

	@Override
	public boolean getCanSpawnHere() {
		int i = MathHelper.floor(this.posX);
		int j = MathHelper.floor(this.getEntityBoundingBox().minY);
		int k = MathHelper.floor(this.posZ);
		Block downblock = this.world.getBlockState(new BlockPos(i, j, k).down()).getBlock();
		return super.getCanSpawnHere() && !(downblock instanceof DungeonStone || downblock instanceof DungeonStoneSmooth || downblock instanceof DungeonStoneLamp) && this.dimension == AurorianConfig.Config_AurorianDimID;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(1, new AurorianRabbitEntity.AIPanic(this, 2.2D));
		this.tasks.addTask(2, new EntityAIMate(this, 0.8D));
		this.tasks.addTask(3, new EntityAITempt(this, 1.0D, ItemRegistry.Registry.SILKBERRY.getItem(), false));
		this.tasks.addTask(4, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 2.2D, 2.2D));
		this.tasks.addTask(4, new EntityAIAvoidEntity(this, EntityWolf.class, 10.0F, 2.2D, 2.2D));
		this.tasks.addTask(4, new EntityAIAvoidEntity(this, EntityMob.class, 4.0F, 2.2D, 2.2D));
		this.tasks.addTask(5, new AurorianRabbitEntity.AIRaidFarm(this));
		this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6D));
		this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
	}

	@Override
	@Nullable
	protected ResourceLocation getLootTable() {
		return LOOT;
	}

	@Override
	protected float getJumpUpwardsMotion() {
		if (!this.collidedHorizontally && (!this.moveHelper.isUpdating() || this.moveHelper.getY() <= this.posY + 0.5D)) {
			Path path = this.navigator.getPath();
			if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength()) {
				Vec3d vec3d = path.getPosition(this);

				if (vec3d.y > this.posY + 0.5D) {
					return 0.5F;
				}
			}
			return this.moveHelper.getSpeed() <= 0.6D ? 0.2F : 0.3F;
		} else {
			return 0.5F;
		}
	}

	/**
	 * Causes this entity to do an upwards motion (jumping).
	 */
	@Override
	protected void jump() {
		super.jump();
		double d0 = this.moveHelper.getSpeed();
		if (d0 > 0.0D) {
			double d1 = this.motionX * this.motionX + this.motionZ * this.motionZ;

			if (d1 < 0.010000000000000002D) {
				this.moveRelative(0.0F, 0.0F, 1.0F, 0.1F);
			}
		}
		if (!this.world.isRemote) {
			this.world.setEntityState(this, (byte) 1);
		}
	}

	@SideOnly(Side.CLIENT)
	public float setJumpCompletion(float p_175521_1_) {
		return this.jumpDuration == 0 ? 0.0F : (this.jumpTicks + p_175521_1_) / this.jumpDuration;
	}

	public void setMovementSpeed(double newSpeed) {
		this.getNavigator().setSpeed(newSpeed);
		this.moveHelper.setMoveTo(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ(), newSpeed);
	}

	@Override
	public void setJumping(boolean jumping) {
		super.setJumping(jumping);
		if (jumping) {
			this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
		}
	}

	public void startJumping() {
		this.setJumping(true);
		this.jumpDuration = 10;
		this.jumpTicks = 0;
	}

	@Override
	public void updateAITasks() {
		if (this.currentMoveTypeDuration > 0) {
			--this.currentMoveTypeDuration;
		}

		if (this.carrotTicks > 0) {
			this.carrotTicks -= this.rand.nextInt(3);

			if (this.carrotTicks < 0) {
				this.carrotTicks = 0;
			}
		}

		if (this.onGround) {
			if (!this.wasOnGround) {
				this.setJumping(false);
				this.checkLandingDelay();
			}

			AurorianRabbitEntity.RabbitJumpHelper entityrabbit$rabbitjumphelper = (AurorianRabbitEntity.RabbitJumpHelper) this.jumpHelper;

			if (!entityrabbit$rabbitjumphelper.getIsJumping()) {
				if (this.moveHelper.isUpdating() && this.currentMoveTypeDuration == 0) {
					Path path = this.navigator.getPath();
					Vec3d vec3d = new Vec3d(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ());

					if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength()) {
						vec3d = path.getPosition(this);
					}

					this.calculateRotationYaw(vec3d.x, vec3d.z);
					this.startJumping();
				}
			} else if (!entityrabbit$rabbitjumphelper.canJump()) {
				this.enableJumpControl();
			}
		}

		this.wasOnGround = this.onGround;
	}

	/**
	 * Attempts to create sprinting particles if the entity is sprinting and not
	 * in water.
	 */
	@Override
	public void spawnRunningParticles() {
	}

	private void calculateRotationYaw(double x, double z) {
		this.rotationYaw = (float) (MathHelper.atan2(z - this.posZ, x - this.posX) * (180D / Math.PI)) - 90.0F;
	}

	private void enableJumpControl() {
		((AurorianRabbitEntity.RabbitJumpHelper) this.jumpHelper).setCanJump(true);
	}

	private void disableJumpControl() {
		((AurorianRabbitEntity.RabbitJumpHelper) this.jumpHelper).setCanJump(false);
	}

	private void updateMoveTypeDuration() {
		if (this.moveHelper.getSpeed() < 2.2D) {
			this.currentMoveTypeDuration = 10;
		} else {
			this.currentMoveTypeDuration = 1;
		}
	}

	private void checkLandingDelay() {
		this.updateMoveTypeDuration();
		this.disableJumpControl();
	}

	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.jumpTicks != this.jumpDuration) {
			++this.jumpTicks;
		} else if (this.jumpDuration != 0) {
			this.jumpTicks = 0;
			this.jumpDuration = 0;
			this.setJumping(false);
		}
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
	}

	public static void registerFixesRabbit(DataFixer fixer) {
		EntityLiving.registerFixesMob(fixer, AurorianRabbitEntity.class);
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("MoreCarrotTicks", this.carrotTicks);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.carrotTicks = compound.getInteger("MoreCarrotTicks");
	}

	protected SoundEvent getJumpSound() {
		return SoundEvents.ENTITY_RABBIT_JUMP;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_RABBIT_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_RABBIT_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_RABBIT_DEATH;
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
	}

	@Override
	public SoundCategory getSoundCategory() {
		return SoundCategory.NEUTRAL;
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return !this.isEntityInvulnerable(source) && super.attackEntityFrom(source, amount);
	}

	private boolean isRabbitBreedingItem(Item itemIn) {
		return BREEDING_ITEMS.contains(itemIn);
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed
	 * it (wheat, carrots or seeds depending on the animal type)
	 */
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return this.isRabbitBreedingItem(stack.getItem());
	}

	/**
	 * Called only once on an entity when first time spawned, via egg, mob
	 * spawner, natural spawning etc, but not called when entity is reloaded
	 * from nbt. Mainly used for initializing attributes and inventory
	 */
	@Override
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		livingdata = super.onInitialSpawn(difficulty, livingdata);
		int i = this.getRandomRabbitType();
		boolean flag = false;

		if (livingdata instanceof AurorianRabbitEntity.RabbitTypeData) {
			i = ((AurorianRabbitEntity.RabbitTypeData) livingdata).typeData;
			flag = true;
		} else {
			livingdata = new AurorianRabbitEntity.RabbitTypeData(i);
		}

		if (flag) {
			this.setGrowingAge(-24000);
		}

		return livingdata;
	}

	private int getRandomRabbitType() {
		Biome biome = this.world.getBiome(new BlockPos(this));
		int i = this.rand.nextInt(100);

		if (biome.isSnowyBiome()) {
			return i < 80 ? 1 : 3;
		} else if (biome instanceof BiomeDesert) {
			return 4;
		} else {
			return i < 50 ? 0 : (i < 90 ? 5 : 2);
		}
	}

	private boolean isCarrotEaten() {
		return this.carrotTicks == 0;
	}

	protected void createEatingParticles() {
		this.world.spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, 0.0D, 0.0D, 0.0D, Block.getStateId(BlockRegistry.Registry.PLANTSILKBERRY.getBlock().getDefaultState()));
		this.carrotTicks = 40;
	}

	/**
	 * Handler for {@link World#setEntityState}
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 1) {
			this.createRunningParticles();
			this.jumpDuration = 10;
			this.jumpTicks = 0;
		} else {
			super.handleStatusUpdate(id);
		}
	}

	static class AIPanic extends EntityAIPanic {
		private final AurorianRabbitEntity rabbit;

		public AIPanic(AurorianRabbitEntity rabbit, double speedIn) {
			super(rabbit, speedIn);
			this.rabbit = rabbit;
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		@Override
		public void updateTask() {
			super.updateTask();
			this.rabbit.setMovementSpeed(this.speed);
		}
	}

	static class AIRaidFarm extends EntityAIMoveToBlock {
		private final AurorianRabbitEntity rabbit;
		private boolean wantsToRaid;
		private boolean canRaid;

		public AIRaidFarm(AurorianRabbitEntity rabbitIn) {
			super(rabbitIn, 0.699999988079071D, 16);
			this.rabbit = rabbitIn;
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		@Override
		public boolean shouldExecute() {
			if (this.runDelay <= 0) {
				if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.rabbit.world, this.rabbit)) {
					return false;
				}

				this.canRaid = false;
				this.wantsToRaid = this.rabbit.isCarrotEaten();
				this.wantsToRaid = true;
			}

			return super.shouldExecute();
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		@Override
		public boolean shouldContinueExecuting() {
			return this.canRaid && super.shouldContinueExecuting();
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		@Override
		public void updateTask() {
			super.updateTask();
			this.rabbit.getLookHelper().setLookPosition(this.destinationBlock.getX() + 0.5D, this.destinationBlock.getY() + 1, this.destinationBlock.getZ() + 0.5D, 10.0F, this.rabbit.getVerticalFaceSpeed());

			if (this.getIsAboveDestination()) {
				//World world = this.rabbit.world;
				//BlockPos blockpos = this.destinationBlock.up();
				//IBlockState iblockstate = world.getBlockState(blockpos);
				//Block block = iblockstate.getBlock();

				//if (this.canRaid && block instanceof BlockCarrot) {
				//	Integer integer = (Integer) iblockstate.getValue(BlockCarrot.AGE);

				//	if (integer.intValue() == 0) {
				//		world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
				//		world.destroyBlock(blockpos, true);
				//	} else {
				//		world.setBlockState(blockpos, iblockstate.withProperty(BlockCarrot.AGE, Integer.valueOf(integer.intValue() - 1)), 2);
				//		world.playEvent(2001, blockpos, Block.getStateId(iblockstate));
				//	}

				//	this.rabbit.createEatingParticles();
				//}

				this.canRaid = false;
				this.runDelay = 10;
			}
		}

		/**
		 * Return true to set given position as destination
		 */
		@Override
		protected boolean shouldMoveTo(World worldIn, BlockPos pos) {
			Block block = worldIn.getBlockState(pos).getBlock();

			if (block == Blocks.FARMLAND && this.wantsToRaid && !this.canRaid) {
				pos = pos.up();
				IBlockState iblockstate = worldIn.getBlockState(pos);
				block = iblockstate.getBlock();

				//if (block instanceof BlockCarrot && ((BlockCarrot) block).isMaxAge(iblockstate)) {
				//	this.canRaid = true;
				//	return true;
				//}
			}

			return false;
		}
	}

	public class RabbitJumpHelper extends EntityJumpHelper {
		private final AurorianRabbitEntity rabbit;
		private boolean canJump;

		public RabbitJumpHelper(AurorianRabbitEntity rabbit) {
			super(rabbit);
			this.rabbit = rabbit;
		}

		public boolean getIsJumping() {
			return this.isJumping;
		}

		public boolean canJump() {
			return this.canJump;
		}

		public void setCanJump(boolean canJumpIn) {
			this.canJump = canJumpIn;
		}

		/**
		 * Called to actually make the entity jump if isJumping is true.
		 */
		@Override
		public void doJump() {
			if (this.isJumping) {
				this.rabbit.startJumping();
				this.isJumping = false;
			}
		}
	}

	static class RabbitMoveHelper extends EntityMoveHelper {
		private final AurorianRabbitEntity rabbit;
		private double nextJumpSpeed;

		public RabbitMoveHelper(AurorianRabbitEntity rabbit) {
			super(rabbit);
			this.rabbit = rabbit;
		}

		@Override
		public void onUpdateMoveHelper() {
			if (this.rabbit.onGround && !this.rabbit.isJumping && !((AurorianRabbitEntity.RabbitJumpHelper) this.rabbit.jumpHelper).getIsJumping()) {
				this.rabbit.setMovementSpeed(0.0D);
			} else if (this.isUpdating()) {
				this.rabbit.setMovementSpeed(this.nextJumpSpeed);
			}

			super.onUpdateMoveHelper();
		}

		/**
		 * Sets the speed and location to move to
		 */
		@Override
		public void setMoveTo(double x, double y, double z, double speedIn) {
			if (this.rabbit.isInWater()) {
				speedIn = 1.5D;
			}

			super.setMoveTo(x, y, z, speedIn);

			if (speedIn > 0.0D) {
				this.nextJumpSpeed = speedIn;
			}
		}
	}

	public static class RabbitTypeData implements IEntityLivingData {
		public int typeData;

		public RabbitTypeData(int type) {
			this.typeData = type;
		}
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return new AurorianRabbitEntity(this.world);
	}
}