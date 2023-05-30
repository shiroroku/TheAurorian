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
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Set;

public class AurorianPigEntity extends EntityAnimal {

	public static final String EntityName = "aurorianpig";
	public static final ResourceLocation LOOT = new ResourceLocation(AurorianMod.MODID, "entities/" + EntityName);
	private static final Set<Item> BREEDING_ITEMS = Sets.newHashSet(ItemRegistry.Registry.SILKBERRY.getItem());

	public AurorianPigEntity(World worldIn) {
		super(worldIn);
		this.spawnableBlock = BlockRegistry.Registry.AURORIANGRASS.getBlock();
		this.setSize(0.9F, 0.9F);
	}

	@Override
	public boolean getCanSpawnHere() {
		int i = MathHelper.floor(this.posX);
		int j = MathHelper.floor(this.getEntityBoundingBox().minY);
		int k = MathHelper.floor(this.posZ);
		Block downblock = this.world.getBlockState(new BlockPos(i, j, k).down()).getBlock();
		return super.getCanSpawnHere() && !(downblock instanceof DungeonStone || downblock instanceof DungeonStoneSmooth || downblock instanceof DungeonStoneLamp) && this.dimension == AurorianConfig.Config_AurorianDimID;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
		this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(4, new EntityAITempt(this, 1.2D, false, BREEDING_ITEMS));
		this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
		this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	public static void registerFixesPig(DataFixer fixer) {
		EntityLiving.registerFixesMob(fixer, AurorianPigEntity.class);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_PIG_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_PIG_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_PIG_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15F, 1.0F);
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if (!super.processInteract(player, hand)) {
			ItemStack itemstack = player.getHeldItem(hand);

			if (itemstack.getItem() == Items.NAME_TAG) {
				itemstack.interactWithEntity(player, this, hand);
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	@Override
	@Nullable
	protected ResourceLocation getLootTable() {
		return LOOT;
	}

	@Override
	public AurorianPigEntity createChild(EntityAgeable ageable) {
		return new AurorianPigEntity(this.world);
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return BREEDING_ITEMS.contains(stack.getItem());
	}
}
