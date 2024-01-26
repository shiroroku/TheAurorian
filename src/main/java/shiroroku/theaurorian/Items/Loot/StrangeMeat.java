package shiroroku.theaurorian.Items.Loot;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import shiroroku.theaurorian.Items.BaseAurorianItem;

import java.util.ArrayList;
import java.util.List;

public class StrangeMeat extends BaseAurorianItem {

    public StrangeMeat(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 64;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        pLevel.playSound(null, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), pLivingEntity.getEatingSound(pStack), SoundSource.NEUTRAL, 1.0F, 1.0F + (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 0.4F);
        pStack.hurtAndBreak(1, pLivingEntity, (p) -> {
            List<MobEffect> effects = new ArrayList<>();
            effects.add(MobEffects.REGENERATION);
            effects.add(MobEffects.FIRE_RESISTANCE);
            effects.add(MobEffects.DAMAGE_BOOST);
            effects.add(MobEffects.SATURATION);
            effects.add(MobEffects.DIG_SPEED);
            p.addEffect(new MobEffectInstance(effects.get(p.getRandom().nextInt(effects.size())), 6000));
            p.broadcastBreakEvent(pLivingEntity.getUsedItemHand());
        });
        ((Player)pLivingEntity).getFoodData().eat(pStack.getItem(), pStack, pLivingEntity);
        return pStack;
    }

}
