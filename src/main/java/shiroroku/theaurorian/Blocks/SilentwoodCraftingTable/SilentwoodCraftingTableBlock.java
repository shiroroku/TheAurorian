package shiroroku.theaurorian.Blocks.SilentwoodCraftingTable;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;

public class SilentwoodCraftingTableBlock extends CraftingTableBlock {

    public SilentwoodCraftingTableBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        return new SimpleMenuProvider((id, playerInv, player) -> new SilentwoodCraftingTableMenu(id, playerInv, ContainerLevelAccess.create(pLevel, pPos)), Component.translatable("container.crafting"));
    }
}

