package shiroroku.theaurorian.Blocks.SilentwoodChest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import shiroroku.theaurorian.Registry.BlockEntityRegistry;

public class SilentwoodChestBlockEntity extends ChestBlockEntity {

    public SilentwoodChestBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistry.silentwood_chest.get(), pPos, pBlockState);
    }
}
