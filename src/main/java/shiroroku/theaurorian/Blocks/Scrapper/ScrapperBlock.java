package shiroroku.theaurorian.Blocks.Scrapper;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import shiroroku.theaurorian.Blocks.AbstractRotatingBlock;
import shiroroku.theaurorian.Registry.BlockEntityRegistry;
import shiroroku.theaurorian.Util.ModUtil;

public class ScrapperBlock extends AbstractRotatingBlock implements EntityBlock {

    public ScrapperBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ScrapperBlockEntity(pPos, pState);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> blockEntity) {
        return blockEntity == BlockEntityRegistry.scrapper.get() ? ScrapperBlockEntity::updateCraft : null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state2, boolean b) {
        if (!state.is(state2.getBlock())) {
            ModUtil.dropItemHandlerInWorld(level.getBlockEntity(pos));
            super.onRemove(state, level, pos, state2, b);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            if (level.getBlockEntity(pos) instanceof ScrapperBlockEntity block) {
                MenuProvider menuProvider = new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return Component.translatable("block.theaurorian.scrapper");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
                        return new ScrapperMenu(id, pos, playerInventory, player);
                    }
                };
                block.updateClient();
                NetworkHooks.openScreen((ServerPlayer) player, menuProvider, block.getBlockPos());
            }
        }
        return InteractionResult.SUCCESS;
    }

}
