package shiroroku.theaurorian.Blocks.Scrapper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import shiroroku.theaurorian.Blocks.AbstractModContainerMenu;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.Registry.MenuRegistry;

public class ScrapperMenu extends AbstractModContainerMenu {

    private final ScrapperBlockEntity blockEntity;

    public ScrapperMenu(int pContainerId, BlockPos pos, Inventory playerInventory, Player playerIn) {
        super(MenuRegistry.scrapper.get(), pContainerId, 3);
        blockEntity = (ScrapperBlockEntity) playerIn.getCommandSenderWorld().getBlockEntity(pos);
        if (blockEntity != null) {
            blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                addSlot(new SlotItemHandler(handler, 0, 40, 37));
                addSlot(new SlotItemHandler(handler, 1, 80, 17));
                addSlot(new SlotItemHandler(handler, 2, 80, 58));
            });
        }
        addPlayerSlots(new InvWrapper(playerInventory));
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), pPlayer, BlockRegistry.scrapper.get());
    }

    public float craftingProgress() {
        return (float) blockEntity.craftingProgress / blockEntity.getCraftingTime(blockEntity.cachedRecipe);
    }

    public boolean isCrafting() {
        return blockEntity.isCrafting();
    }
}
