package com.shiroroku.theaurorian.Entities.Passive;

import com.shiroroku.theaurorian.Blocks.DungeonStone;
import com.shiroroku.theaurorian.Blocks.DungeonStoneLamp;
import com.shiroroku.theaurorian.Blocks.DungeonStoneSmooth;
import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class AurorianSheepEntity extends EntityAnimal implements net.minecraftforge.common.IShearable {

	public static final String EntityName = "auroriansheep";
	private static final Set<Item> BREEDING_ITEMS = Sets.newHashSet(ItemRegistry.Registry.SILKBERRY.getItem());
	private static final DataParameter<Byte> DYE_COLOR = EntityDataManager.createKey(AurorianSheepEntity.class, DataSerializers.BYTE);
	private static final Map<EnumDyeColor, float[]> DYE_TO_RGB = Maps.newEnumMap(EnumDyeColor.class);
	private int sheepTimer;
	private AurorianSheepAIEatGrass entityAIEatGrass;

	private final InventoryCrafting inventoryCrafting = new InventoryCrafting(new Container() {
		@Override
		public boolean canInteractWith(EntityPlayer playerIn) {
			return false;
		}
	}, 2, 1);

	@Override
	public boolean getCanSpawnHere() {
		int i = MathHelper.floor(this.posX);
		int j = MathHelper.floor(this.getEntityBoundingBox().minY);
		int k = MathHelper.floor(this.posZ);
		Block downblock = this.world.getBlockState(new BlockPos(i, j, k).down()).getBlock();
		return super.getCanSpawnHere() && !(downblock instanceof DungeonStone || downblock instanceof DungeonStoneSmooth || downblock instanceof DungeonStoneLamp) && this.dimension == AurorianConfig.Config_AurorianDimID;
	}

	public AurorianSheepEntity(World worldIn) {
		super(worldIn);
		this.spawnableBlock = BlockRegistry.Registry.AURORIANGRASS.getBlock();
		this.setSize(0.9F, 1.3F);
		this.inventoryCrafting.setInventorySlotContents(0, new ItemStack(Items.DYE));
		this.inventoryCrafting.setInventorySlotContents(1, new ItemStack(Items.DYE));
	}

	@Override
	protected void initEntityAI() {
		this.entityAIEatGrass = new AurorianSheepAIEatGrass(this);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
		this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(4, new EntityAITempt(this, 1.1D, false, BREEDING_ITEMS));
		this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
		this.tasks.addTask(5, this.entityAIEatGrass);
		this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
	}

	@Override
	protected void updateAITasks() {
		this.sheepTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}

	@Override
	public void onLivingUpdate() {
		if (this.world.isRemote) {
			this.sheepTimer = Math.max(0, this.sheepTimer - 1);
		}

		super.onLivingUpdate();
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return BREEDING_ITEMS.contains(stack.getItem());
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(DYE_COLOR, Byte.valueOf((byte) 0));
	}

	private static float[] createSheepColor(EnumDyeColor p_192020_0_) {
		float[] afloat = p_192020_0_.getColorComponentValues();
		return new float[] { afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F };
	}

	@SideOnly(Side.CLIENT)
	public static float[] getDyeRgb(EnumDyeColor dyeColor) {
		return DYE_TO_RGB.get(dyeColor);
	}

	@Override
	@Nullable
	protected ResourceLocation getLootTable() {
		if (this.getSheared()) {
			return LootTableList.ENTITIES_SHEEP;
		} else {
			switch (this.getFleeceColor()) {
				case WHITE:
				default:
					return LootTableList.ENTITIES_SHEEP_WHITE;
				case ORANGE:
					return LootTableList.ENTITIES_SHEEP_ORANGE;
				case MAGENTA:
					return LootTableList.ENTITIES_SHEEP_MAGENTA;
				case LIGHT_BLUE:
					return LootTableList.ENTITIES_SHEEP_LIGHT_BLUE;
				case YELLOW:
					return LootTableList.ENTITIES_SHEEP_YELLOW;
				case LIME:
					return LootTableList.ENTITIES_SHEEP_LIME;
				case PINK:
					return LootTableList.ENTITIES_SHEEP_PINK;
				case GRAY:
					return LootTableList.ENTITIES_SHEEP_GRAY;
				case SILVER:
					return LootTableList.ENTITIES_SHEEP_SILVER;
				case CYAN:
					return LootTableList.ENTITIES_SHEEP_CYAN;
				case PURPLE:
					return LootTableList.ENTITIES_SHEEP_PURPLE;
				case BLUE:
					return LootTableList.ENTITIES_SHEEP_BLUE;
				case BROWN:
					return LootTableList.ENTITIES_SHEEP_BROWN;
				case GREEN:
					return LootTableList.ENTITIES_SHEEP_GREEN;
				case RED:
					return LootTableList.ENTITIES_SHEEP_RED;
				case BLACK:
					return LootTableList.ENTITIES_SHEEP_BLACK;
			}
		}
	}

	/**
	 * Handler for {@link World#setEntityState}
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 10) {
			this.sheepTimer = 40;
		} else {
			super.handleStatusUpdate(id);
		}
	}

	public static void registerFixesSheep(DataFixer fixer) {
		EntityLiving.registerFixesMob(fixer, AurorianSheepEntity.class);
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_) {
		if (this.sheepTimer <= 0) {
			return 0.0F;
		} else if (this.sheepTimer >= 4 && this.sheepTimer <= 36) {
			return 1.0F;
		} else {
			return this.sheepTimer < 4 ? (this.sheepTimer - p_70894_1_) / 4.0F : -(this.sheepTimer - 40 - p_70894_1_) / 4.0F;
		}
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_) {
		if (this.sheepTimer > 4 && this.sheepTimer <= 36) {
			float f = (this.sheepTimer - 4 - p_70890_1_) / 32.0F;
			return ((float) Math.PI / 5F) + ((float) Math.PI * 7F / 100F) * MathHelper.sin(f * 28.7F);
		} else {
			return this.sheepTimer > 0 ? ((float) Math.PI / 5F) : this.rotationPitch * 0.017453292F;
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("Sheared", this.getSheared());
		compound.setByte("Color", (byte) this.getFleeceColor().getMetadata());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.setSheared(compound.getBoolean("Sheared"));
		this.setFleeceColor(EnumDyeColor.byMetadata(compound.getByte("Color")));
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_SHEEP_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_SHEEP_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SHEEP_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.15F, 1.0F);
	}

	public EnumDyeColor getFleeceColor() {
		return EnumDyeColor.byMetadata(this.dataManager.get(DYE_COLOR).byteValue() & 15);
	}

	public void setFleeceColor(EnumDyeColor color) {
		byte b0 = this.dataManager.get(DYE_COLOR).byteValue();
		this.dataManager.set(DYE_COLOR, Byte.valueOf((byte) (b0 & 240 | color.getMetadata() & 15)));
	}

	public boolean getSheared() {
		return (this.dataManager.get(DYE_COLOR).byteValue() & 16) != 0;
	}

	public void setSheared(boolean sheared) {
		byte b0 = this.dataManager.get(DYE_COLOR).byteValue();
		if (sheared) {
			this.dataManager.set(DYE_COLOR, Byte.valueOf((byte) (b0 | 16)));
		} else {
			this.dataManager.set(DYE_COLOR, Byte.valueOf((byte) (b0 & -17)));
		}
	}

	public static EnumDyeColor getRandomSheepColor(Random random) {
		int i = random.nextInt(100);

		if (i < 5) {
			return EnumDyeColor.WHITE;
		} else if (i < 10) {
			return EnumDyeColor.SILVER;
		} else if (i < 18) {
			return EnumDyeColor.LIGHT_BLUE;
		} else {
			return EnumDyeColor.PURPLE;
		}
	}

	@Override
	public AurorianSheepEntity createChild(EntityAgeable ageable) {
		AurorianSheepEntity entitysheep = (AurorianSheepEntity) ageable;
		AurorianSheepEntity entitysheep1 = new AurorianSheepEntity(this.world);
		entitysheep1.setFleeceColor(this.getDyeColorMixFromParents(this, entitysheep));
		return entitysheep1;
	}

	@Override
	public void eatGrassBonus() {
		this.setSheared(false);

		if (this.isChild()) {
			this.addGrowth(60);
		}
	}

	@Override
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		livingdata = super.onInitialSpawn(difficulty, livingdata);
		this.setFleeceColor(getRandomSheepColor(this.world.rand));
		return livingdata;
	}

	@Override
	public boolean isShearable(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos) {
		return !this.getSheared() && !this.isChild();
	}

	@Override
	public java.util.List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
		this.setSheared(true);
		int i = 1 + this.rand.nextInt(3);

		java.util.List<ItemStack> ret = new java.util.ArrayList<>();
		for (int j = 0; j < i; ++j) {
			ret.add(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, this.getFleeceColor().getMetadata()));
		}

		this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
		return ret;
	}

	private EnumDyeColor getDyeColorMixFromParents(EntityAnimal father, EntityAnimal mother) {
		int i = ((AurorianSheepEntity) father).getFleeceColor().getDyeDamage();
		int j = ((AurorianSheepEntity) mother).getFleeceColor().getDyeDamage();
		this.inventoryCrafting.getStackInSlot(0).setItemDamage(i);
		this.inventoryCrafting.getStackInSlot(1).setItemDamage(j);
		ItemStack itemstack = CraftingManager.findMatchingResult(this.inventoryCrafting, father.world);
		int k;

		if (itemstack.getItem() == Items.DYE) {
			k = itemstack.getMetadata();
		} else {
			k = this.world.rand.nextBoolean() ? i : j;
		}

		return EnumDyeColor.byDyeDamage(k);
	}

	@Override
	public float getEyeHeight() {
		return 0.95F * this.height;
	}

	static {
		for (EnumDyeColor enumdyecolor : EnumDyeColor.values()) {
			DYE_TO_RGB.put(enumdyecolor, createSheepColor(enumdyecolor));
		}
		DYE_TO_RGB.put(EnumDyeColor.WHITE, new float[] { 0.9019608F, 0.9019608F, 0.9019608F });
	}
}