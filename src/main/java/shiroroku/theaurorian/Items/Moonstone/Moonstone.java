package shiroroku.theaurorian.Items.Moonstone;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import shiroroku.theaurorian.Configuration;
import shiroroku.theaurorian.Util.ModUtil;
import shiroroku.theaurorian.Util.TooltipUtil;

import java.util.List;

public class Moonstone {

    public static List<Component> appendHoverText(List<Component> pTooltipComponents) {
        return TooltipUtil.shiftMoreInfo(pTooltipComponents, Component.translatable("string.theaurorian.tooltip.moonstone").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
    }

    public static <T extends LivingEntity> int onItemDamage(ItemStack stack, T entity, int amount) {
        // 50% to -1 damage
        amount -= ModUtil.randomChanceOf(entity.getRandom(), Configuration.moonstone_damage_chance.get()) ? 1 : 0;
        // if day +1 damage
        amount += (entity.level.isDay() ? 1 : 0);
        return Math.max(0, amount);
    }
}
