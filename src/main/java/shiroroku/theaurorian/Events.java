package shiroroku.theaurorian;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import shiroroku.theaurorian.Registry.EntityRegistry;

@EventBusSubscriber(modid = TheAurorian.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Events {

    @SubscribeEvent
    public static void entityAttributeCreation(EntityAttributeCreationEvent event) {
        EntityRegistry.entityAttributeCreation(event);
    }

    @SubscribeEvent
    public static void spawnPlacementRegister(RegisterSpawnPlacementsEvent event) {
        EntityRegistry.registerSpawnPlacements(event);
    }

}
