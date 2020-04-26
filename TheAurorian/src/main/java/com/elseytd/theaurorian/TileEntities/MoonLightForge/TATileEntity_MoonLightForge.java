package com.elseytd.theaurorian.TileEntities.MoonLightForge;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.Blocks.TABlock_MoonLightForge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

public class TATileEntity_MoonLightForge extends TileEntityLockable implements ITickable, ISidedInventory {

	private static final int[] SLOT = { 0, 1, 2 };
	private NonNullList<ItemStack> heldItems = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
	private int hasMoonLight = 0;
	private int craftProgress = 0;
	private boolean isCrafting = false;

	@Override
	public void update() {
		if (!this.getWorld().isRemote) {
			//if (this.getWorld().getTotalWorldTime() % 20 == 0) {
				this.setField(0, this.hasMoonlight() ? 1 : 0);

				if (isCrafting) {
					int newval = this.getField(1) + 1;
					if (newval >= 100) {
						stopCrafting();
						doCraft();
					} else {
						this.setField(1, newval);
					}
				}
			//}

			ItemStack slot1 = this.heldItems.get(0);
			ItemStack slot2 = this.heldItems.get(1);
			ItemStack slot3output = this.heldItems.get(2);

			if (getRecipeOutput(slot1, slot2) != null) {
				//If recipe is valid
				if (slot3output.isEmpty()) {
					if (isCrafting != true) {
						isCrafting = true;//If output empty start crafting
					}
				} else {
					stopCrafting();//If output full stop crafting
				}
			} else if (isCrafting) {
				//If recipe is not valid and we are crafting, stop
				stopCrafting();
			}
		}
	}

	private void doCraft() {
		this.heldItems.set(2, new ItemStack(getRecipeOutput(this.heldItems.get(0), this.heldItems.get(1))));
		this.heldItems.set(1, ItemStack.EMPTY);
		this.heldItems.set(0, ItemStack.EMPTY);
	}

	private void stopCrafting() {
		isCrafting = false;
		this.setField(1, 0);
	}

	private Item getRecipeOutput(ItemStack input1, ItemStack input2) {

		if (input1.getItem() == TAItems.moonstonepickaxe && input2.getItem() == TAItems.umbraingot) {
			return TAItems.umbrapickaxe;
		}

		return null;
	}

	@Override
	public String getGuiID() {
		return "theaurorian:moonlightforge";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new TAContainer_MoonLightForge(playerInventory, this);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.heldItems = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(nbt, this.heldItems);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		ItemStackHelper.saveAllItems(nbt, this.heldItems);
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
		return true;
	}

	@Override
	public void clear() {
		this.heldItems.clear();
	}

	@Override
	public String getName() {
		return "container." + TAMod.MODID + "." + TABlock_MoonLightForge.BLOCKNAME;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return SLOT;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return true;
	}

	private boolean hasMoonlight() {
		if (this.getWorld().canSeeSky(this.getPos())) {
			if (!this.getWorld().isDaytime()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getField(int id) {
		switch (id) {
		default:
		case 0:
			return hasMoonLight;
		case 1:
			return craftProgress;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		default:
		case 0:
			hasMoonLight = value;
		case 1:
			craftProgress = value;
		}
	}

	@Override
	public int getFieldCount() {
		return 2;
	}

}
