package shiroroku.theaurorian.Items.Loot;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import shiroroku.theaurorian.Items.BaseAurorianAxe;

import java.util.ArrayList;
import java.util.List;

public class AurorianiteAxe extends BaseAurorianAxe {

    public AurorianiteAxe(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        // we only want logs
        if (pState.getTags().noneMatch(t -> t == BlockTags.LOGS)) {
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }

        boolean isDone = false;
        int maxLogs = 256;
        int logCount = 0;
        List<BlockPos> notSearchedWood = new ArrayList<>();
        List<BlockPos> searchedWood = new ArrayList<>();

        //Set our first block to be the log we mined
        BlockPos currentPos = pPos;

        while (!isDone) {
            //for loops for getting a 3x3 on the same level of the block and 3x3 a level above
            for (int x = -1; x <= 1; x++) {
                for (int y = 0; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        BlockPos p = currentPos.offset(x, y, z);
                        //If any block we find is wood and is not already searched or in our list, we add it.
                        if (pLevel.getBlockState(p).getTags().anyMatch(t -> t == BlockTags.LOGS)) {
                            if (!notSearchedWood.contains(p) && !searchedWood.contains(p)) {
                                notSearchedWood.add(p);
                                logCount++;
                            }
                        }
                    }
                }
            }

            //Move the block we just searched from our unsearched list to a list for later breaking
            searchedWood.add(currentPos);
            notSearchedWood.remove(currentPos);

            //If no more blocks are found we are done, else we set our next block from the first in the list unless we reach the max log count
            if (notSearchedWood.isEmpty() || logCount >= maxLogs) {
                isDone = true;
            } else {
                currentPos = notSearchedWood.get(0);
            }
        }

        for (BlockPos p : searchedWood) {
            pLevel.destroyBlock(p, true);
            pStack.hurtAndBreak(1, pEntityLiving, (player) -> player.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        }

        return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }
}
