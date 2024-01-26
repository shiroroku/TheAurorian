package shiroroku.theaurorian.Util;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class TooltipUtil {

    @OnlyIn(Dist.CLIENT)
    public static List<Component> shiftMoreInfo(List<Component> pTooltipComponents, Component pInfo) {
        pTooltipComponents.add(Screen.hasShiftDown() ? pInfo : Component.translatable("string.theaurorian.tooltip.more_info").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
        return pTooltipComponents;
    }

    /**
     * Checks if the item has a ".desc" lang, adds a more info description tooltip if it does
     */
    public static List<Component> tryAddDesc(ItemStack pStack, List<Component> pTooltipComponents) {
        String key = pStack.getDescriptionId() + ".desc";
        return ComponentUtils.isTranslationResolvable(Component.translatable(key)) ? TooltipUtil.shiftMoreInfo(pTooltipComponents, Component.translatable(key).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC)) : pTooltipComponents;
    }

    public static int getBarColor() {
        return FastColor.ARGB32.color(255, 71, 193, 249);
    }
}
