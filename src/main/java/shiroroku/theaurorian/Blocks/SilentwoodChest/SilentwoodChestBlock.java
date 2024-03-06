package shiroroku.theaurorian.Blocks.SilentwoodChest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import shiroroku.theaurorian.Registry.BlockEntityRegistry;

public class SilentwoodChestBlock extends ChestBlock {

    public SilentwoodChestBlock(Properties pProperties) {
        super(pProperties, BlockEntityRegistry.silentwood_chest);
    }

    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SilentwoodChestBlockEntity(pPos, pState);
    }
}
