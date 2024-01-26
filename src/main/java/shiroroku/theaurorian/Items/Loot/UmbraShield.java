package shiroroku.theaurorian.Items.Loot;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import shiroroku.theaurorian.Items.BaseAurorianShield;

public class UmbraShield extends BaseAurorianShield {

    public UmbraShield(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        pPlayer.getItemInHand(pHand).hurtAndBreak(1, pPlayer, (livingEntity) -> livingEntity.broadcastBreakEvent(livingEntity.getUsedItemHand()));
        return super.use(pLevel, pPlayer, pHand);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onUseTick(Level pLevel, LivingEntity entity, ItemStack pStack, int pRemainingUseDuration) {
        if (entity.tickCount % 2 == 0) {
            pLevel.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 0.5F, 0.6F);
        }

        Vec3 lookv = entity.getLookAngle();
        pLevel.getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat(), entity, entity.getBoundingBox().inflate(0.7).move(lookv.x, lookv.y, lookv.z)).forEach((e) -> e.setSecondsOnFire(1));

        //todo check if this syncs correctly
        for (int i = 0; i < 5; i++) {
            double spread = 0.5;
            double velocity = 0.2;
            double offset = 0.5;
            double x = ((entity.getRandom().nextDouble() - 0.5) * spread + entity.getX()) + lookv.x * offset;
            double y = ((entity.getRandom().nextDouble() - 0.5) * spread + entity.getEyeY()) + lookv.y * offset;
            double z = ((entity.getRandom().nextDouble() - 0.5) * spread + entity.getZ()) + lookv.z * offset;
            pLevel.addParticle(ParticleTypes.FLAME, x, y, z, lookv.x * velocity, lookv.y * velocity, lookv.z * velocity);
            pLevel.addParticle(ParticleTypes.SMOKE, x, y, z, lookv.x * velocity, lookv.y * velocity, lookv.z * velocity);
        }
    }
}
