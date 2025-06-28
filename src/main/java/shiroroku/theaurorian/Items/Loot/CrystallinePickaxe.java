package shiroroku.theaurorian.Items.Loot;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;
import shiroroku.theaurorian.Config.CommonConfig;
import shiroroku.theaurorian.DataGen.DataGenItemsTags;
import shiroroku.theaurorian.Items.BaseAurorianPickaxe;
import shiroroku.theaurorian.Util.ModUtil;

public class CrystallinePickaxe extends BaseAurorianPickaxe {

    public CrystallinePickaxe(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        // must be an ore and serverside
        if (pState.getTags().noneMatch((t -> t == Tags.Blocks.ORES)) || !pLevel.isClientSide) {
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }

        // choose random item from tag, and % chance to drop it
        BuiltInRegistries.ITEM.getTag(DataGenItemsTags.CRYSTALLINE_PICKAXE_TREASURE).flatMap(tag -> tag.getRandomElement(pLevel.getRandom())).ifPresent((i) -> {
            if (ModUtil.randomChanceOf(pLevel.getRandom(), CommonConfig.crystalline_pickaxe_treasure_chance.get())) {
                pStack.hurtAndBreak(1, pEntityLiving, EquipmentSlot.OFFHAND);
                pLevel.addFreshEntity(new ItemEntity(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), new ItemStack(i)));
            }
        });

        return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }
}
