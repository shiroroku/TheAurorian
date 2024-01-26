package shiroroku.theaurorian.Util;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.ItemStackHandler;

public class ModUtil {

    public static boolean randomChanceOf(RandomSource randomSource, Double percentChance) {
        return randomSource.nextDouble() <= Math.min(1.0d, Math.max(percentChance, 0.0d));
    }

    public static void dropItemHandlerInWorld(BlockEntity block) {
        block.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            for (int i = 0; i < handler.getSlots(); i++) {
                block.getLevel().addFreshEntity(new ItemEntity(block.getLevel(), block.getBlockPos().getX() + 0.5f, block.getBlockPos().getY() + 0.5f, block.getBlockPos().getZ() + 0.5f, handler.getStackInSlot(i)));
            }
        });
    }

    public static float wave(float time, float speed, float amp) {
        return (float) (amp * Math.sin(((double) time) * speed));
    }

    public static boolean canItemsStack(ItemStack itemFrom, ItemStack itemOnto) {
        if (itemFrom.isEmpty() || itemOnto.isEmpty()) {
            return true;
        }
        if (!itemFrom.isStackable() || !itemOnto.isStackable()) {
            return false;
        }
        return itemFrom.is(itemOnto.getItem()) && itemFrom.getCount() + itemOnto.getCount() <= itemOnto.getMaxStackSize();
    }

    /**
     * Sets stack in slot, if there is an item of the same type, then they stack.
     */
    public static void setAndMergeStack(ItemStackHandler handler, int slot, ItemStack item) {
        ItemStack result = item.copy();
        if (canItemsStack(item, handler.getStackInSlot(slot))) {
            result.setCount(handler.getStackInSlot(slot).getCount() + result.getCount());
        }
        handler.setStackInSlot(slot, result);
    }
}
