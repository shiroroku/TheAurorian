package shiroroku.theaurorian.Items.Loot;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import shiroroku.theaurorian.Items.BaseAurorianSword;

public class AurorianiteSword extends BaseAurorianSword {

    public AurorianiteSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        // if player has a shield, we want them to be crouching
        if (pPlayer.getOffhandItem().getTags().anyMatch(t -> t == Tags.Items.TOOLS_SHIELDS) && !pPlayer.isCrouching()) {
            return super.use(pLevel, pPlayer, pUsedHand);
        }

        pLevel.getNearbyEntities(LivingEntity.class, TargetingConditions.forNonCombat(), pPlayer, pPlayer.getBoundingBox().inflate(5)).forEach((e) -> e.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60)));
        pPlayer.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60));
        pPlayer.getItemInHand(pUsedHand).hurtAndBreak(5, pPlayer, (p) -> p.broadcastBreakEvent(pUsedHand));
        pPlayer.getCooldowns().addCooldown(this, 600);
        pLevel.playSound(null, pPlayer, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1F, 2F);
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
