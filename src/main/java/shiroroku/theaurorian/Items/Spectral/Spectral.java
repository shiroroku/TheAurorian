package shiroroku.theaurorian.Items.Spectral;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import shiroroku.theaurorian.Configuration;
import shiroroku.theaurorian.DataGen.DataGenItemsTags;
import shiroroku.theaurorian.Util.ModUtil;
import shiroroku.theaurorian.Util.TooltipUtil;

import java.util.List;
import java.util.stream.StreamSupport;

public class Spectral {

    public static List<Component> appendHoverText(List<Component> pTooltipComponents) {
        return TooltipUtil.shiftMoreInfo(pTooltipComponents, Component.translatable("string.theaurorian.tooltip.spectral", Configuration.spectral_armor_cleanse_chance.get() * 100).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
    }

    public static void handleOnDamage(LivingDamageEvent event) {
        if (!event.getEntity().level.isClientSide && event.getSource().getEntity() instanceof Player player) {
            int spectralArmorPieces = (int) StreamSupport.stream(player.getArmorSlots().spliterator(), false).filter((a) -> a.is(DataGenItemsTags.SPECTRAL_ARMOR)).count();
            if (spectralArmorPieces == 0) {
                return;
            }

            double cleanseChance = spectralArmorPieces * Configuration.spectral_armor_cleanse_chance.get();
            if (ModUtil.randomChanceOf(player.getRandom(), cleanseChance)) {
                player.getActiveEffects().stream().filter(e -> !e.getEffect().isBeneficial()).findFirst().ifPresent(mobEffectInstance -> player.removeEffect(mobEffectInstance.getEffect()));
            }
        }
    }
}
