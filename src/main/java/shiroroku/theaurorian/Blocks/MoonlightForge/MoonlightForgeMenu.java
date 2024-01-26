package shiroroku.theaurorian.Blocks.MoonlightForge;

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

public class MoonlightForgeMenu extends AbstractModContainerMenu {

    private final MoonlightForgeBlockEntity blockEntity;

    public MoonlightForgeMenu(int pContainerId, BlockPos pos, Inventory playerInventory, Player playerIn) {
        super(MenuRegistry.moonlight_forge.get(), pContainerId, 3);
        blockEntity = (MoonlightForgeBlockEntity) playerIn.getCommandSenderWorld().getBlockEntity(pos);
        if (blockEntity != null) {
            blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                addSlot(new SlotItemHandler(handler, 0, 22, 35));
                addSlot(new SlotItemHandler(handler, 1, 84, 35));
                addSlot(new SlotItemHandler(handler, 2, 142, 35));
            });
        }
        addPlayerSlots(new InvWrapper(playerInventory));
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), pPlayer, BlockRegistry.moonlight_forge.get());
    }

    public float craftingProgress() {
        return (float) blockEntity.craftingProgress / blockEntity.getCraftingTime(blockEntity.cachedRecipe);
    }

    public boolean isCrafting() {
        return blockEntity.isCrafting();
    }

    public boolean canSeeMoon() {
        return blockEntity.canSeeMoon();
    }
}
