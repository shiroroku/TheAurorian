package shiroroku.theaurorian.Blocks.AurorianFurnace;

import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import shiroroku.theaurorian.Registry.BlockEntityRegistry;

public class AurorianFurnaceBlock extends AbstractFurnaceBlock {

    public AurorianFurnaceBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected void openContainer(Level pLevel, BlockPos pPos, Player pPlayer) {
        pPlayer.openMenu((MenuProvider) pLevel.getBlockEntity(pPos));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new AurorianFurnaceBlockEntity(pPos, pState);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createFurnaceTicker(pLevel, pBlockEntityType, BlockEntityRegistry.aurorian_furnace.get());
    }
}
