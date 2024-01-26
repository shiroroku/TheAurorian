package shiroroku.theaurorian.Items.Moonstone;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeTier;
import org.jetbrains.annotations.Nullable;
import shiroroku.theaurorian.Items.BaseAurorianSword;

import java.util.List;
import java.util.function.Consumer;

public class MoonstoneSword extends BaseAurorianSword {

    public MoonstoneSword(ForgeTier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, Moonstone.appendHoverText(pTooltipComponents), pIsAdvanced);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return Moonstone.onItemDamage(stack, entity, amount);
    }
}
