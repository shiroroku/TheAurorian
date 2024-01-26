package shiroroku.theaurorian.Items.Moonstone;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import shiroroku.theaurorian.Items.BaseAurorianSickle;

import java.util.List;
import java.util.function.Consumer;

public class MoonstoneSickle extends BaseAurorianSickle {

    public MoonstoneSickle(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
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
