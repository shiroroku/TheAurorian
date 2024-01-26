package shiroroku.theaurorian.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LayerLightEngine;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.Util.ModUtil;

public class AurorianGrassBlock extends Block implements BonemealableBlock {

    public AurorianGrassBlock(Properties pProperties) {
        super(pProperties);
    }

    private static boolean canBeGrass(BlockState pState, LevelReader pLevelReader, BlockPos pPos) {
        BlockPos blockpos = pPos.above();
        BlockState blockstate = pLevelReader.getBlockState(blockpos);
        if (blockstate.getFluidState().getAmount() == 8) {
            return false;
        } else {
            return LayerLightEngine.getLightBlockInto(pLevelReader, pState, pPos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(pLevelReader, blockpos)) < pLevelReader.getMaxLightLevel();
        }
    }

    private static boolean canPropagate(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.above();
        return canBeGrass(pState, pLevel, pPos) && !pLevel.getFluidState(blockpos).is(FluidTags.WATER);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!canBeGrass(pState, pLevel, pPos)) {
            if (!pLevel.isAreaLoaded(pPos, 1)) {
                return;
            }
            pLevel.setBlockAndUpdate(pPos, BlockRegistry.aurorian_dirt.get().defaultBlockState());
        } else {
            if (!pLevel.isAreaLoaded(pPos, 3)) {
                return;
            }
            if (pLevel.getMaxLocalRawBrightness(pPos.above()) >= 9) {
                BlockState blockstate = this.defaultBlockState();
                for (int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(3) - 1);
                    if (pLevel.getBlockState(blockpos).is(BlockRegistry.aurorian_dirt.get()) && canPropagate(blockstate, pLevel, blockpos)) {
                        pLevel.setBlockAndUpdate(blockpos, blockstate);
                    }
                }
            }
        }
        if(pLevel.isAreaLoaded(pPos, 1) && pLevel.getBlockState(pPos.above()).isAir() && pPos.getY() < pLevel.getSeaLevel() - 5 && ModUtil.randomChanceOf(pRandom, 0.25)){
            if(ModUtil.randomChanceOf(pRandom, 0.05)) {
                pLevel.setBlockAndUpdate(pPos.above(), BlockRegistry.bright_bulb.get().defaultBlockState());
            }else {
                pLevel.setBlockAndUpdate(pPos.above(), BlockRegistry.aurorian_tallgrass.get().defaultBlockState());
            }
        }
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return pLevel.getBlockState(pPos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {

    }
}
