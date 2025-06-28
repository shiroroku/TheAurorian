package shiroroku.theaurorian.Blocks.MoonlightForge;

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

public class MoonlightForgeMenu extends AbstractModContainerMenu {

    private final MoonlightForgeBlockEntity blockEntity;

    public MoonlightForgeMenu(int pContainerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(pContainerId, playerInventory, (MoonlightForgeBlockEntity) playerInventory.player.getCommandSenderWorld().getBlockEntity(extraData.readBlockPos()));
    }

    public MoonlightForgeMenu(int id, Inventory playerInventory, MoonlightForgeBlockEntity blockEntity) {
        super(MenuRegistry.moonlight_forge.get(), id, 3);
        this.blockEntity = blockEntity;
        addPlayerSlots(new InvWrapper(playerInventory));

        addSlot(new SlotItemHandler(this.blockEntity.getItemHandler(), 0, 22, 35));
        addSlot(new SlotItemHandler(this.blockEntity.getItemHandler(), 1, 84, 35));
        addSlot(new SlotItemHandler(this.blockEntity.getItemHandler(), 2, 142, 35));
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
