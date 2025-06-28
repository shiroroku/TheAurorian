package shiroroku.theaurorian.Blocks.MoonlightForge;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import shiroroku.theaurorian.Blocks.AbstractRotatingBlock;
import shiroroku.theaurorian.Blocks.Scrapper.ScrapperMenu;
import shiroroku.theaurorian.Registry.BlockEntityRegistry;

public class MoonlightForgeBlock extends AbstractRotatingBlock implements EntityBlock {

    public static final MapCodec<MoonlightForgeBlock> CODEC = simpleCodec(MoonlightForgeBlock::new);
    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 10, 16);

    public MoonlightForgeBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new MoonlightForgeBlockEntity(pPos, pState);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> blockEntity) {
        return blockEntity == BlockEntityRegistry.moonlight_forge.get() ? MoonlightForgeBlockEntity::updateCraft : null;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state2, boolean b) {
        if (!state.is(state2.getBlock())) {
            if (level.getBlockEntity(pos) instanceof MoonlightForgeBlockEntity moonlightForgeBlock) {
                moonlightForgeBlock.dropItems();
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, state2, b);
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            if (level.getBlockEntity(pos) instanceof MoonlightForgeBlockEntity blockEntity) {
                MenuProvider menuProvider = new SimpleMenuProvider((_id, _inv, _player) -> new MoonlightForgeMenu(_id, _inv, blockEntity),
                        Component.translatable("block.theaurorian.moonlight_forge"));
                blockEntity.updateClient();
                player.openMenu(menuProvider, blockEntity.getBlockPos());
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

}
