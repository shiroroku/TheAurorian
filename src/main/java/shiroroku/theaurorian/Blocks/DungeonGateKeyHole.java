package shiroroku.theaurorian.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import shiroroku.theaurorian.DataGen.DataGenBlocksTags;
import shiroroku.theaurorian.Registry.ItemRegistry;
import shiroroku.theaurorian.Util.ModUtil;

import java.util.function.Supplier;

public class DungeonGateKeyHole extends Block {

    private final Supplier<Item> key;
    private final boolean can_lockpick;

    public DungeonGateKeyHole(Supplier<Item> key, Properties pProperties) {
        this(key, pProperties, false);
    }

    public DungeonGateKeyHole(Supplier<Item> key, Properties pProperties, Boolean can_lockpick) {
        super(pProperties);
        this.can_lockpick = can_lockpick;
        this.key = key;
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack usedItem = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
        boolean hasItem = (can_lockpick && usedItem.is(ItemRegistry.lockpicks.get())) || usedItem.is(key.get());

        if (!hasItem) {
            if (can_lockpick) {
                pPlayer.displayClientMessage(Component.translatable("string.theaurorian.gate_key_lockpick"), true);
            } else {
                pPlayer.displayClientMessage(Component.translatable("string.theaurorian.gate_key"), true);
            }
            return InteractionResult.PASS;
        }

        if (!pLevel.isClientSide()) {
            usedItem.hurtAndBreak(1, pPlayer, (player) -> player.broadcastBreakEvent(InteractionHand.MAIN_HAND));

            // 2/3 of the time the lockpick will not unlock the gate
            if (can_lockpick && usedItem.is(ItemRegistry.lockpicks.get())) {
                if (ModUtil.randomChanceOf(pPlayer.getRandom(), 0.66)) {
                    return InteractionResult.FAIL;
                }
            }

            for (int y = -3; y <= 3; y++) {
                for (int xz = -3; xz <= 3; xz++) {
                    BlockPos posx = pPos.offset(xz, y, 0);
                    BlockPos posz = pPos.offset(0, y, xz);
                    if (pLevel.getBlockState(posx).is(DataGenBlocksTags.DUNGEON_GATES)) {
                        pLevel.destroyBlock(posx, false);
                    }
                    if (pLevel.getBlockState(posz).is(DataGenBlocksTags.DUNGEON_GATES)) {
                        pLevel.destroyBlock(posz, false);
                    }
                }
            }

            pLevel.playSound(null, pPos, SoundEvents.IRON_TRAPDOOR_OPEN, SoundSource.BLOCKS, 1, 1);
            pLevel.destroyBlock(pPos, false);
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }
}
