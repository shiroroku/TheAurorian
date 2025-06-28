package shiroroku.theaurorian.Items.Moonstone;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import shiroroku.theaurorian.Items.BaseAurorianShovel;

import java.util.List;
import java.util.function.Consumer;

public class MoonstoneShovel extends BaseAurorianShovel {

    public MoonstoneShovel(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, Moonstone.appendHoverText(tooltipComponents), tooltipFlag);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<Item> onBroken) {
        return Moonstone.onItemDamage(stack, entity, amount);
    }
}
