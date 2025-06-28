package shiroroku.theaurorian.Items.AurorianSteel;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import shiroroku.theaurorian.Items.BaseAurorianHoe;

import java.util.List;
import java.util.function.Consumer;

public class AurorianSteelHoe extends BaseAurorianHoe {

    public AurorianSteelHoe(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, AurorianSteel.appendHoverText(tooltipComponents, stack), tooltipFlag);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, @Nullable T entity, Consumer<Item> onBroken) {
        return AurorianSteel.onItemDamage(stack, entity, amount);
    }
}
