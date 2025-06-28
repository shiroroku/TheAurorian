package shiroroku.theaurorian.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
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

    private static final int radius = 3;
    private static final double pick_failure_chance = 0.66;
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

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack usedItem = player.getItemInHand(InteractionHand.MAIN_HAND);
        boolean hasItem = (can_lockpick && usedItem.is(ItemRegistry.lockpicks.get())) || usedItem.is(key.get());
        if (!hasItem) {
            if (can_lockpick) {
                player.displayClientMessage(Component.translatable("string.theaurorian.gate_key_lockpick"), true);
            } else {
                player.displayClientMessage(Component.translatable("string.theaurorian.gate_key"), true);
            }
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        if (!level.isClientSide()) {
            usedItem.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));

            // Pick chance
            if (can_lockpick && usedItem.is(ItemRegistry.lockpicks.get())) {
                if (ModUtil.randomChanceOf(player.getRandom(), pick_failure_chance)) {
                    return ItemInteractionResult.FAIL;
                }
            }

            // Break gates
            for (int y = -radius; y <= radius; y++) {
                for (int x = -radius; x <= radius; x++) {
                    for (int z = -radius; z <= radius; z++) {
                        BlockPos posx = pos.offset(x, y, z);
                        if (level.getBlockState(posx).is(DataGenBlocksTags.DUNGEON_GATES)) {
                            level.destroyBlock(posx, false);
                        }
                    }
                }
            }

            level.playSound(null, pos, SoundEvents.IRON_TRAPDOOR_OPEN, SoundSource.BLOCKS, 1, 1);
            level.destroyBlock(pos, false);
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }
}
