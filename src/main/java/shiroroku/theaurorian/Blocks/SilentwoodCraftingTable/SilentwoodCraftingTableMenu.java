package shiroroku.theaurorian.Blocks.SilentwoodCraftingTable;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import shiroroku.theaurorian.Registry.BlockRegistry;

public class SilentwoodCraftingTableMenu extends CraftingMenu {

    private final ContainerLevelAccess access;

    public SilentwoodCraftingTableMenu(int pContainerId, Inventory pPlayerInventory) {
        this(pContainerId, pPlayerInventory, ContainerLevelAccess.NULL);
    }

    public SilentwoodCraftingTableMenu(int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess pAccess) {
        super(pContainerId, pPlayerInventory, pAccess);
        this.access = pAccess;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(this.access, pPlayer, BlockRegistry.silentwood_crafting_table.get());
    }
}
