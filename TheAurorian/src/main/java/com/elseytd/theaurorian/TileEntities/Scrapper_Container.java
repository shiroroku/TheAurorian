package com.elseytd.theaurorian.TileEntities;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.Recipes.ScrapperRecipe;
import com.elseytd.theaurorian.Recipes.ScrapperRecipeHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Scrapper_Container extends Container {

	private final IInventory inventory;
	private int progress;

	public Scrapper_Container(InventoryPlayer playerInventory, IInventory machineinv) {
		this.inventory = machineinv;
		this.addSlotToContainer(new Slot(machineinv, 0, 80, 17) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				for (ScrapperRecipe recipe : ScrapperRecipeHandler.allRecipes) {
					if (recipe.getInput().getItem() == stack.getItem()) {
						return true;
					}
				}
				return false;
			}
		});
		this.addSlotToContainer(new Slot(machineinv, 1, 40, 37) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				if (Item.getItemFromBlock(TABlocks.crystal) == stack.getItem()) {
					return true;
				}

				return false;
			}
		});
		this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, machineinv, 2, 80, 58) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}
		});
		this.addPlayerInvSlots(playerInventory);
	}

	private void addPlayerInvSlots(InventoryPlayer playerInventory) {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		for (int k = 0; k < 9; ++k) {
			this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.inventory.setField(id, data);
	}

	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.inventory);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icontainerlistener = this.listeners.get(i);
			if (progress != this.inventory.getField(0)) {
				icontainerlistener.sendWindowProperty(this, 0, this.inventory.getField(1));
			}
		}
		progress = this.inventory.getField(0);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.inventory.isUsableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < this.inventory.getSizeInventory()) {
				if (!this.mergeItemStack(itemstack1, this.inventory.getSizeInventory(), this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, this.inventory.getSizeInventory(), false)) {
				return ItemStack.EMPTY;
			}
			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

}
