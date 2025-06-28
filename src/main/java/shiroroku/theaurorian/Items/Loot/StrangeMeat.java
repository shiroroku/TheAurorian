package shiroroku.theaurorian.Items.Loot;

import net.minecraft.core.Holder;
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
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 64;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        pLevel.playSound(null, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), pLivingEntity.getEatingSound(pStack), SoundSource.NEUTRAL, 1.0F, 1.0F + (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 0.4F);
        pStack.hurtAndBreak(1, pLivingEntity, LivingEntity.getSlotForHand(pLivingEntity.getUsedItemHand()));

        List<Holder<MobEffect>> effects = new ArrayList<>(5);
        effects.add(MobEffects.REGENERATION);
        effects.add(MobEffects.FIRE_RESISTANCE);
        effects.add(MobEffects.DAMAGE_BOOST);
        effects.add(MobEffects.SATURATION);
        effects.add(MobEffects.DIG_SPEED);
        pLivingEntity.addEffect(new MobEffectInstance(effects.get(pLivingEntity.getRandom().nextInt(effects.size())), 6000));

        pLivingEntity.eat(pLevel, pStack);
        return pStack;
    }

}
