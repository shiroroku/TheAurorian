package shiroroku.theaurorian.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.Util.ModUtil;

public class AurorianDeepslateBlock extends Block {

    public static final BooleanProperty SHOULD_GROW_VINES = BooleanProperty.create("should_grow_vines");

    public AurorianDeepslateBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(SHOULD_GROW_VINES, true));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(SHOULD_GROW_VINES, false);
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return pState.getValue(SHOULD_GROW_VINES);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(SHOULD_GROW_VINES);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pLevel.isAreaLoaded(pPos, 3) || !ModUtil.randomChanceOf(pRandom, 0.5)) {
            return;
        }

        if (pPos.getX() % 2 != 0 || pPos.getZ() % 2 != 0) {
            return;
        }

        pPos = pPos.offset(pRandom.nextIntBetweenInclusive(-1, 1), 0, pRandom.nextIntBetweenInclusive(-1, 1));

        if (pLevel.getBlockState(pPos.above()).isAir() || !pLevel.getBlockState(pPos.below()).isAir() || !pLevel.getBlockState(pPos.below(2)).isAir()) {
            return;
        }

        boolean hasLowerDeepslate = false;
        for (int zx = -1; zx < 1; zx++) {
            if (zx == 0) {
                continue;
            }
            if (pLevel.getBlockState(pPos.offset(zx, -1, 0)).is(BlockRegistry.aurorian_deepslate.get())) {
                hasLowerDeepslate = true;
                break;
            }
            if (pLevel.getBlockState(pPos.offset(0, -1, zx)).is(BlockRegistry.aurorian_deepslate.get())) {
                hasLowerDeepslate = true;
                break;
            }
        }
        if (!hasLowerDeepslate) {
            return;
        }

        pLevel.setBlockAndUpdate(pPos.below(), BlockRegistry.silentwood_log.get().defaultBlockState());
        pLevel.setBlockAndUpdate(pPos.below(2), BlockRegistry.silentwood_leaves.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));

        BlockPos.MutableBlockPos mutPos = new BlockPos.MutableBlockPos();
        while (pLevel.getBlockState(pPos.below(3).offset(mutPos)).isAir() && mutPos.getY() > -50) {
            mutPos = mutPos.move(0, -1, 0);
        }

        for (int y = mutPos.getY() / 2; y < 0; y++) {
            if (!pLevel.getBlockState(pPos.offset(0, y, 0)).isAir()) {
                break;
            }
            pLevel.setBlockAndUpdate(pPos.offset(0, y, 0), BlockRegistry.silentwood_leaves.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
        }
        pLevel.setBlockAndUpdate(pPos, BlockRegistry.aurorian_deepslate.get().defaultBlockState().setValue(SHOULD_GROW_VINES, false));
    }
}
