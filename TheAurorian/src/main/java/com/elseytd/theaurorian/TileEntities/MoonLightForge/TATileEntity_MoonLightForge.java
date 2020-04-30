package com.elseytd.theaurorian.TileEntities.MoonLightForge;

import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.Blocks.TABlock_MoonLightForge;
import com.elseytd.theaurorian.Recipes.MoonlightForgeRecipe;
import com.elseytd.theaurorian.Recipes.MoonlightForgeRecipeHandler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TATileEntity_MoonLightForge extends TileEntityLockable implements ITickable {

	private NonNullList<ItemStack> heldItems = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
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

				if (getRecipeOutput(slot1, slot2) != null && this.hasMoonlight()) {
					if (slot3output.isEmpty()) {
						if (this.isCrafting != true) {
							this.isCrafting = true;
						}
					} else {
						stopCrafting();
					}
				} else if (this.isCrafting) {
					stopCrafting();
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
			if (input1.getItem() == recipe.getInput1() && input2.getItem() == recipe.getInput2()) {

				ItemStack outputitem = new ItemStack(recipe.getOutput());
				if (outputitem.isItemStackDamageable() && input1.isItemStackDamageable() && input1.isItemDamaged()) {
					outputitem.setItemDamage(input1.getItemDamage());
				}

				if (TAConfig.Config_MoonlightForgeTransfersEnchants) {
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
		ItemStack output = getRecipeOutput(this.heldItems.get(0), this.heldItems.get(1));
		if (output != null) {
			this.heldItems.get(0).shrink(1);
			this.heldItems.get(1).shrink(1);
			this.heldItems.set(2, output);
		}
	}

	private void stopCrafting() {
		this.isCrafting = false;
		this.craftProgress = 0;
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
				if (recipe.getInput1() == stack.getItem()) {
					return true;
				}
			}
			return false;
		case 1:
			for (MoonlightForgeRecipe recipe : MoonlightForgeRecipeHandler.allRecipes) {
				if (recipe.getInput2() == stack.getItem()) {
					return true;
				}
			}
			return false;
		default:
		case 2:
			return false;
		}
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
		case 2:
			return isPowered;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		default:
		case 0:
			hasMoonLight = value;
			break;
		case 1:
			craftProgress = value;
			break;
		case 2:
			isPowered = value;
			break;
		}
	}

	@Override
	public int getFieldCount() {
		return 3;
	}

}
