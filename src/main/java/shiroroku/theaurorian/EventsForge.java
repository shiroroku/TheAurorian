package shiroroku.theaurorian;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import shiroroku.theaurorian.Enchantments.LightningEnchant;
import shiroroku.theaurorian.Items.MirrorOfGuidance.MirrorDataLoader;
import shiroroku.theaurorian.Items.Spectral.Spectral;

@EventBusSubscriber(modid = TheAurorian.MODID, bus = EventBusSubscriber.Bus.GAME)
public class EventsForge {

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Pre event) {
        LightningEnchant.handleOnDamage(event);
        Spectral.handleOnDamage(event);
    }

    @SubscribeEvent
    public static void onAddReloadListener(AddReloadListenerEvent event) {
        event.addListener(new MirrorDataLoader());
    }
}
