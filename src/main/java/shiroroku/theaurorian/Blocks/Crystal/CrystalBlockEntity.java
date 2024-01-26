package shiroroku.theaurorian.Blocks.Crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import shiroroku.theaurorian.Registry.BlockEntityRegistry;

public class CrystalBlockEntity extends BlockEntity {

    public CrystalBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistry.crystal.get(), pPos, pBlockState);
    }
}
