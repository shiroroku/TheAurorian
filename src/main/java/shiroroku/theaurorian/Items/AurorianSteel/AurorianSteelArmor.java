package shiroroku.theaurorian.Items.AurorianSteel;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.Nullable;
import shiroroku.theaurorian.Items.BaseAurorianArmor;

import java.util.List;
import java.util.function.Consumer;

public class AurorianSteelArmor extends BaseAurorianArmor {

    public AurorianSteelArmor(Holder<ArmorMaterial> material, ArmorItem.Type type, Properties properties) {
        super(material, type, properties);
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
