package shiroroku.theaurorian;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import shiroroku.theaurorian.Registry.EntityRegistry;

@Mod.EventBusSubscriber(modid = TheAurorian.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Events {

    @SubscribeEvent
    public static void entityAttributeCreation(EntityAttributeCreationEvent event) {
        EntityRegistry.entityAttributeCreation(event);
    }

    @SubscribeEvent
    public static void spawnPlacementRegister(SpawnPlacementRegisterEvent event) {
        EntityRegistry.registerSpawnPlacements(event);
    }
}
