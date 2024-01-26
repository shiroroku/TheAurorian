package shiroroku.theaurorian.Blocks;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractModContainerMenu extends AbstractContainerMenu {

    private final int inventorySize;

    protected AbstractModContainerMenu(@Nullable MenuType<?> pMenuType, int pContainerId, int inventorySize) {
        super(pMenuType, pContainerId);
        this.inventorySize = inventorySize;
    }

    public void addPlayerSlots(IItemHandler playerSlots) {
        //Add player slots
        int slotIndex = 0;
        int invX = 8;
        int invY = 142;
        for (int x = 0; x < 9; x++) {
            addSlot(new SlotItemHandler(playerSlots, slotIndex, invX + 18 * x, invY));
            slotIndex++;
        }
        invY -= 58;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlot(new SlotItemHandler(playerSlots, slotIndex, invX + 18 * x, invY + 18 * y));
                slotIndex++;
            }
        }
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack slotItem = slot.getItem();
            itemstack = slotItem.copy();
            if (pIndex < inventorySize) {
                if (!this.moveItemStackTo(slotItem, inventorySize, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(slotItem, 0, inventorySize, false)) {
                    return ItemStack.EMPTY;
                }
            }
            if (slotItem.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemstack;
    }

}
