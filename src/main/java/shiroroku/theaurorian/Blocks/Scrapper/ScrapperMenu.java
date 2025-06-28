package shiroroku.theaurorian.Blocks.Scrapper;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import shiroroku.theaurorian.Blocks.AbstractModContainerMenu;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.Registry.MenuRegistry;

public class ScrapperMenu extends AbstractModContainerMenu {

    private final ScrapperBlockEntity blockEntity;

    public ScrapperMenu(int pContainerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(pContainerId, playerInventory, (ScrapperBlockEntity) playerInventory.player.getCommandSenderWorld().getBlockEntity(extraData.readBlockPos()));
    }

    public ScrapperMenu(int id, Inventory playerInventory, ScrapperBlockEntity blockEntity) {
        super(MenuRegistry.scrapper.get(), id, 3);
        this.blockEntity = blockEntity;
        addPlayerSlots(new InvWrapper(playerInventory));

        addSlot(new SlotItemHandler(this.blockEntity.getItemHandler(), 0, 40, 37));
        addSlot(new SlotItemHandler(this.blockEntity.getItemHandler(), 1, 80, 17));
        addSlot(new SlotItemHandler(this.blockEntity.getItemHandler(), 2, 80, 58));
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
