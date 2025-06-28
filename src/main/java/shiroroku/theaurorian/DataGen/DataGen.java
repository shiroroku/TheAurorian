package shiroroku.theaurorian.DataGen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import shiroroku.theaurorian.TheAurorian;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = TheAurorian.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGen {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new DataGenBlocks(output, existingFileHelper));
        generator.addProvider(event.includeClient(), new DataGenItems(output, existingFileHelper));
        BlockTagsProvider blockTags = generator.addProvider(event.includeServer(), new DataGenBlocksTags(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new DataGenItemsTags(output, lookupProvider, blockTags.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new DataGenLoot(output, lookupProvider));
        generator.addProvider(event.includeServer(), new DataGenDatapack(output, lookupProvider));
    }

}