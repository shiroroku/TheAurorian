package shiroroku.theaurorian.Items.Loot;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;
import shiroroku.theaurorian.Items.BaseAurorianPickaxe;

public class AurorianitePickaxe extends BaseAurorianPickaxe {

    public AurorianitePickaxe(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        // give the player haste if they mine an ore
        if (pState.getTags().anyMatch(t -> t == Tags.Blocks.ORES)) {
            if (pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 100, 1))) {
                pStack.hurtAndBreak(1, pEntityLiving, EquipmentSlot.MAINHAND);
            }
        }
        return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }
}
