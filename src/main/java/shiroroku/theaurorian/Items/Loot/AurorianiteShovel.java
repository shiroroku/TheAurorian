package shiroroku.theaurorian.Items.Loot;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import shiroroku.theaurorian.Config.CommonConfig;
import shiroroku.theaurorian.Items.BaseAurorianShovel;

public class AurorianiteShovel extends BaseAurorianShovel {

    public AurorianiteShovel(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }


    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        // we only want diggable things, and a player
        if (pState.getTags().noneMatch(t -> t == BlockTags.MINEABLE_WITH_SHOVEL) && !(pEntityLiving instanceof Player)) {
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }

        HitResult hitResult = pEntityLiving.pick(pEntityLiving.getAttribute(Attributes.BLOCK_INTERACTION_RANGE).getValue(), 1, false);
        // Make sure we hit a block
        if (hitResult.getType() != HitResult.Type.BLOCK) {
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
        Direction.Axis hitAxis = ((BlockHitResult) hitResult).getDirection().getAxis();

        int radius = CommonConfig.aurorianite_shovel_dig_radius.get();
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                BlockPos offsetHitpos = switch (hitAxis) {
                    case X -> pPos.offset(0, x, z);
                    case Y -> pPos.offset(x, 0, z);
                    default -> pPos.offset(x, z, 0);
                };

                BlockState currentBlock = pLevel.getBlockState(offsetHitpos);
                float resistanceDifference = Mth.abs(currentBlock.getDestroySpeed(pLevel, offsetHitpos) - pState.getDestroySpeed(pLevel, pPos));

                if (currentBlock.getTags().anyMatch(t -> t == BlockTags.MINEABLE_WITH_SHOVEL) && resistanceDifference <= CommonConfig.aurorianite_shovel_resistance_difference.get()) {
                    pLevel.destroyBlock(offsetHitpos, true);
                    pStack.hurtAndBreak(1, pEntityLiving, EquipmentSlot.MAINHAND);
                }
            }
        }

        return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }
}
