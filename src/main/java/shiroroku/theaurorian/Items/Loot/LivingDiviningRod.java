package shiroroku.theaurorian.Items.Loot;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import shiroroku.theaurorian.Items.BaseAurorianItem;
import shiroroku.theaurorian.Util.ModUtil;

public class LivingDiviningRod extends BaseAurorianItem {

    public LivingDiviningRod(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pLevel.isClientSide()) {
            return super.use(pLevel, pPlayer, pUsedHand);
        }

        pLevel.getNearbyEntities(LivingEntity.class, TargetingConditions.forNonCombat(), pPlayer, pPlayer.getBoundingBox().inflate(18)).forEach((e) -> {
            if (ModUtil.randomChanceOf(pPlayer.getRandom(), 0.75)) {
                e.addEffect(new MobEffectInstance(MobEffects.GLOWING, 120));
            }
        });

        pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer, (p) -> p.broadcastBreakEvent(pUsedHand));
        pPlayer.getCooldowns().addCooldown(this, 120);
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
