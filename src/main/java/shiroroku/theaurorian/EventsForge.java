package shiroroku.theaurorian;

import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import shiroroku.theaurorian.Items.Spectral.Spectral;

@Mod.EventBusSubscriber(modid = TheAurorian.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventsForge {

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        Spectral.handleOnDamage(event);
    }
}
