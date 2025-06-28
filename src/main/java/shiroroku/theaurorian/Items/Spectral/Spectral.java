package shiroroku.theaurorian.Items.Spectral;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import shiroroku.theaurorian.Config.CommonConfig;
import shiroroku.theaurorian.DataGen.DataGenItemsTags;
import shiroroku.theaurorian.Util.ModUtil;
import shiroroku.theaurorian.Util.TooltipUtil;

import java.util.List;
import java.util.stream.StreamSupport;

public class Spectral {

    public static List<Component> appendHoverText(List<Component> pTooltipComponents) {
        return TooltipUtil.shiftMoreInfo(pTooltipComponents, Component.translatable("string.theaurorian.tooltip.spectral", CommonConfig.spectral_armor_cleanse_chance.get() * 100).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
    }

    public static void handleOnDamage(LivingDamageEvent.Pre event) {
        if (!event.getEntity().level().isClientSide && event.getSource().getEntity() instanceof Player player) {
            int spectralArmorPieces = (int) StreamSupport.stream(player.getArmorSlots().spliterator(), false).filter((a) -> a.is(DataGenItemsTags.SPECTRAL_ARMOR)).count();
            if (spectralArmorPieces == 0) {
                return;
            }

            double cleanseChance = spectralArmorPieces * CommonConfig.spectral_armor_cleanse_chance.get();
            if (ModUtil.randomChanceOf(player.getRandom(), cleanseChance)) {
                player.getActiveEffects().stream().filter(e -> !e.getEffect().value().isBeneficial()).findFirst().ifPresent(mobEffectInstance -> player.removeEffect(mobEffectInstance.getEffect()));
            }
        }
    }
}
