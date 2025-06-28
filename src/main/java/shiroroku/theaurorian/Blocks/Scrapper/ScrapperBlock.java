package shiroroku.theaurorian.Blocks.Scrapper;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import shiroroku.theaurorian.Blocks.AbstractRotatingBlock;
import shiroroku.theaurorian.Registry.BlockEntityRegistry;

public class ScrapperBlock extends AbstractRotatingBlock implements EntityBlock {

    public static final MapCodec<ScrapperBlock> CODEC = simpleCodec(ScrapperBlock::new);

    public ScrapperBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ScrapperBlockEntity(pPos, pState);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> blockEntity) {
        return blockEntity == BlockEntityRegistry.scrapper.get() ? ScrapperBlockEntity::updateCraft : null;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state2, boolean b) {
        if (!state.is(state2.getBlock())) {
            if (level.getBlockEntity(pos) instanceof ScrapperBlockEntity scrapperBlock) {
                scrapperBlock.dropItems();
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, state2, b);
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            if (level.getBlockEntity(pos) instanceof ScrapperBlockEntity blockEntity) {
                MenuProvider menuProvider = new SimpleMenuProvider((_id, _inv, _player) -> new ScrapperMenu(_id, _inv, blockEntity),
                        Component.translatable("block.theaurorian.scrapper"));
                blockEntity.updateClient();
                player.openMenu(menuProvider, blockEntity.getBlockPos());
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

}
