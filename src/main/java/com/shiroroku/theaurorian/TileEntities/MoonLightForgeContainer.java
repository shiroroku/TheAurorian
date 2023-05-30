package com.shiroroku.theaurorian.TileEntities;

import com.shiroroku.theaurorian.Recipes.MoonlightForgeRecipe;
import com.shiroroku.theaurorian.Recipes.MoonlightForgeRecipeHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MoonLightForgeContainer extends Container {

	private final IInventory inventory;
	private int hasMoonlight;
	private int progress;
	private int isPowered;

	public MoonLightForgeContainer(InventoryPlayer playerInventory, IInventory machineinv) {
		this.inventory = machineinv;
		this.addSlotToContainer(new Slot(machineinv, 0, 22, 35) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				for (MoonlightForgeRecipe recipe : MoonlightForgeRecipeHandler.allRecipes) {
					if (recipe.getInput1().getItem() == stack.getItem()) {
						return true;
					}
				}
				return false;
			}
		});
		this.addSlotToContainer(new Slot(machineinv, 1, 84, 35) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				for (MoonlightForgeRecipe recipe : MoonlightForgeRecipeHandler.allRecipes) {
					if (recipe.getInput2().getItem() == stack.getItem()) {
						return true;
					}
				}
				return false;
			}
		});
		this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, machineinv, 2, 142, 35) {
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
			if (hasMoonlight != this.inventory.getField(0)) {
				icontainerlistener.sendWindowProperty(this, 0, this.inventory.getField(0));
			}
			if (progress != this.inventory.getField(1)) {
				icontainerlistener.sendWindowProperty(this, 1, this.inventory.getField(1));
			}
			if (isPowered != this.inventory.getField(2)) {
				icontainerlistener.sendWindowProperty(this, 2, this.inventory.getField(2));
			}
		}
		hasMoonlight = this.inventory.getField(0);
		progress = this.inventory.getField(1);
		isPowered = this.inventory.getField(2);
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
