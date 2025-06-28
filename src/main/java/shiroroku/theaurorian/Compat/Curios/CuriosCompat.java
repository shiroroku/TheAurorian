package shiroroku.theaurorian.Compat.Curios;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.InterModComms;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import shiroroku.theaurorian.TheAurorian;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;


@EventBusSubscriber(modid = TheAurorian.MODID, bus = EventBusSubscriber.Bus.MOD)
public class CuriosCompat {

    @SubscribeEvent
    public static void enqueueIMC(InterModEnqueueEvent evt) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.NECKLACE.getMessageBuilder().size(1).build());
    }

}