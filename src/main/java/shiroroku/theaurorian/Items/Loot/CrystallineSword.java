package shiroroku.theaurorian.Items.Loot;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import shiroroku.theaurorian.Configuration;
import shiroroku.theaurorian.Entities.CrystallineBeam.CrystallineBeamEntity;
import shiroroku.theaurorian.Items.BaseAurorianSword;

public class CrystallineSword extends BaseAurorianSword {

    //todo add piercing

    public CrystallineSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        if(pLevel.isClientSide || this.getUseDuration(pStack) - pTimeCharged < 19){
            return;
        }
        CrystallineBeamEntity beam = new CrystallineBeamEntity(pLevel, pLivingEntity);
        beam.shootFromRotation(pLivingEntity, pLivingEntity.getXRot(), pLivingEntity.getYRot(), 0.0f, Configuration.cystalline_sword_beam_velocity.get().floatValue(), 0f);
        pLevel.addFreshEntity(beam);
        pLevel.playSound(null, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), SoundEvents.GRINDSTONE_USE, SoundSource.PLAYERS, 1F, 2.5F);
        pLevel.playSound(null, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1F, 5F);
        pStack.hurtAndBreak(1, pLivingEntity, (livingEntity) -> livingEntity.broadcastBreakEvent(livingEntity.getUsedItemHand()));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        pPlayer.startUsingItem(pHand);
        return InteractionResultHolder.consume(pPlayer.getItemInHand(pHand));
    }
}
