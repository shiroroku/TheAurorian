package com.shiroroku.theaurorian.TileEntities;

import com.shiroroku.theaurorian.Blocks.MoonLightForge;
import com.shiroroku.theaurorian.Recipes.MoonlightForgeRecipe;
import com.shiroroku.theaurorian.Recipes.MoonlightForgeRecipeHandler;
import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;

public class MoonLightForgeTileEntity extends TileEntityLockable implements ITickable, ISidedInventory {

	private NonNullList<ItemStack> heldItems = NonNullList.withSize(3, ItemStack.EMPTY);
	private int hasMoonLight = 0;
	private int craftProgress = 0;
	private int isPowered = 0;
	private boolean isCrafting = false;

	@Override
	public void update() {
		if (!this.getWorld().isRemote) {
			if (!this.getWorld().isBlockPowered(this.getPos())) {
				this.isPowered = 0;
				this.hasMoonLight = this.hasMoonlight() ? 1 : 0;

				if (this.isCrafting) {

					int height = this.getPos().getY();
					int worldheight = this.getWorld().getHeight();
					float heightpercent = (float) height / (float) worldheight;
					int tickinterval = 2;
					if (heightpercent <= 0.25) {
						tickinterval += 16;
					} else if (heightpercent > 0.25 && heightpercent <= 0.5) {
						tickinterval += 8;
					} else if (heightpercent > 0.5 && heightpercent <= 0.75) {
						tickinterval += 4;
					}

					if (this.getWorld().getTotalWorldTime() % tickinterval == 0) {
						int newval = this.craftProgress + 1;

						if (newval >= 100) {
							this.stopCrafting();
							this.doCraft();
						} else {
							this.craftProgress = newval;
						}
					}
				}

				ItemStack slot1 = this.heldItems.get(0);
				ItemStack slot2 = this.heldItems.get(1);
				ItemStack slot3output = this.heldItems.get(2);

				if (this.getRecipeOutput(slot1, slot2) != null && this.hasMoonlight()) {
					if (slot3output.isEmpty() || (slot3output.isStackable() && this.getRecipeOutput(slot1, slot2).getItem() == slot3output.getItem() && slot3output.getCount() + this.getRecipeOutput(slot1, slot2).getCount() <= slot3output.getMaxStackSize())) {
						if (!this.isCrafting) {
							this.isCrafting = true;
							this.updateClient();
						}
					} else {
						this.stopCrafting();
					}
				} else if (this.isCrafting) {
					this.stopCrafting();
				}
			} else {
				this.isPowered = 1;
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean isCrafting() {
		return this.isPowered == 0 && this.hasMoonLight == 1 && this.craftProgress > 0;
	}

	public ItemStack getRecipeOutput(ItemStack input1, ItemStack input2) {
		for (MoonlightForgeRecipe recipe : MoonlightForgeRecipeHandler.allRecipes) {
			if ((input1.getItem() == recipe.getInput1().getItem() && input1.getCount() >= recipe.getInput1().getCount()) && (input2.getItem() == recipe.getInput2().getItem() && input2.getCount() >= recipe.getInput2().getCount())) {

				ItemStack outputitem = recipe.getOutput().copy();
				if (outputitem.isItemStackDamageable() && input1.isItemStackDamageable() && input1.isItemDamaged()) {
					outputitem.setItemDamage(input1.getItemDamage());
				}

				if (AurorianConfig.Config_MoonlightForgeTransfersEnchants) {
					if (input1.isItemEnchanted() && outputitem.isItemEnchantable()) {
						EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(input1), outputitem);
					}
				}

				return outputitem;
			}
		}

		return null;
	}

	private void doCraft() {
		ItemStack output = null;
		ItemStack input1 = this.heldItems.get(0);
		ItemStack input2 = this.heldItems.get(1);
		ItemStack req1 = null;
		ItemStack req2 = null;

		for (MoonlightForgeRecipe recipe : MoonlightForgeRecipeHandler.allRecipes) {
			if ((input1.getItem() == recipe.getInput1().getItem() && input1.getCount() >= recipe.getInput1().getCount()) && (input2.getItem() == recipe.getInput2().getItem() && input2.getCount() >= recipe.getInput2().getCount())) {

				ItemStack outputitem = recipe.getOutput().copy();
				if (outputitem.isItemStackDamageable() && input1.isItemStackDamageable() && input1.isItemDamaged()) {
					outputitem.setItemDamage(input1.getItemDamage());
				}

				if (AurorianConfig.Config_MoonlightForgeTransfersEnchants) {
					if (input1.isItemEnchanted() && outputitem.isItemEnchantable()) {
						EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(input1), outputitem);
					}
				}

				req1 = recipe.getInput1();
				req2 = recipe.getInput2();
				output = outputitem;
			}
		}

		if (output != null) {
			this.heldItems.get(0).shrink(req1 == null ? 1 : req1.getCount());
			this.heldItems.get(1).shrink(req2 == null ? 1 : req2.getCount());
			if (this.heldItems.get(2).isEmpty()) {
				this.heldItems.set(2, output);
			} else if (this.heldItems.get(2).getItem() == output.getItem() && output.isStackable()) {
				this.heldItems.get(2).grow(output.getCount());
			}
		}
	}

	private void stopCrafting() {
		this.isCrafting = false;
		this.craftProgress = 0;
		this.updateClient();
	}

	@Override
	public String getGuiID() {
		return "theaurorian:moonlightforge";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new MoonLightForgeContainer(playerInventory, this);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.heldItems = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(nbt, this.heldItems);
		this.craftProgress = nbt.getInteger("CraftProgress");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		ItemStackHelper.saveAllItems(nbt, this.heldItems);
		nbt.setInteger("CraftProgress", (short) this.craftProgress);
		return nbt;
	}

	@Override
	public int getSizeInventory() {
		return this.heldItems.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.heldItems) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.heldItems.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.heldItems, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.heldItems, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = this.heldItems.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.heldItems.set(index, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}
		if (!flag) {
			this.markDirty();
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		if (this.world.getTileEntity(this.pos) != this) {
			return false;
		} else {
			return player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
		}
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		switch (index) {
			case 0:
				for (MoonlightForgeRecipe recipe : MoonlightForgeRecipeHandler.allRecipes) {
					if (recipe.getInput1().getItem() == stack.getItem()) {
						return true;
					}
				}
				return false;
			case 1:
				for (MoonlightForgeRecipe recipe : MoonlightForgeRecipeHandler.allRecipes) {
					if (recipe.getInput2().getItem() == stack.getItem()) {
						return true;
					}
				}
				return false;
			default:
			case 2:
				return false;
		}
	}

	private void updateClient() {
		this.markDirty();
		if (this.world != null) {
			IBlockState state = this.world.getBlockState(this.getPos());
			this.world.notifyBlockUpdate(this.getPos(), state, state, 3);
		}
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbtTag = new NBTTagCompound();
		this.writeToNBT(nbtTag);
		return new SPacketUpdateTileEntity(this.getPos(), 1, nbtTag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		this.readFromNBT(packet.getNbtCompound());
	}

	@Override
	public void clear() {
		this.heldItems.clear();
	}

	@Override
	public String getName() {
		return "container." + AurorianMod.MODID + "." + MoonLightForge.BLOCKNAME;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	private boolean hasMoonlight() {
		if (this.getWorld().canSeeSky(this.getPos())) {
			return !this.getWorld().isDaytime();
		}
		return false;
	}

	@Override
	public int getField(int id) {
		switch (id) {
			default:
			case 0:
				return this.hasMoonLight;
			case 1:
				return this.craftProgress;
			case 2:
				return this.isPowered;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
			default:
			case 0:
				this.hasMoonLight = value;
				break;
			case 1:
				this.craftProgress = value;
				break;
			case 2:
				this.isPowered = value;
				break;
		}
	}

	@Override
	public int getFieldCount() {
		return 3;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return index == 2;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return side == EnumFacing.DOWN ? new int[] { 2 } : new int[] { 0, 1 };
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	IItemHandler handlerBottom = new SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);

	@Override
	@Nullable
	@SuppressWarnings("unchecked")
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (facing == EnumFacing.DOWN) {
				return (T) this.handlerBottom;
			}
		}
		return super.getCapability(capability, facing);
	}
}
