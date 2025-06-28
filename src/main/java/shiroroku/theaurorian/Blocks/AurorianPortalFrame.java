package shiroroku.theaurorian.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import shiroroku.theaurorian.DataGen.DataGenItemsTags;
import shiroroku.theaurorian.Portal.AurorianPortalShape;

import java.util.Optional;

public class AurorianPortalFrame extends Block {

    public AurorianPortalFrame(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        ItemStack usedItem = player.getItemInHand(hand);
        if (usedItem.is(DataGenItemsTags.PORTAL_LIGHTERS)) {
            BlockPos placePos = pos.relative(hitResult.getDirection());
            Optional<AurorianPortalShape> optional = AurorianPortalShape.findEmptyPortalShape(level, placePos, Direction.Axis.X);
            if (optional.isPresent()) {
                level.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                //usedItem.hurtAndBreak(1, pPlayer, (player) -> player.broadcastBreakEvent(pHand));
                optional.get().createPortalBlocks();
                return ItemInteractionResult.sidedSuccess(level.isClientSide());
            }
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }
}
