package shiroroku.theaurorian;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shiroroku.theaurorian.Config.ClientConfig;
import shiroroku.theaurorian.Config.CommonConfig;
import shiroroku.theaurorian.Registry.*;

@Mod(TheAurorian.MODID)
public class TheAurorian {
    public static final String MODID = "theaurorian";
    public static final Logger LOGGER = LogManager.getLogger();

    public static final ResourceKey<Level> the_aurorian = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "the_aurorian"));
    public static final ResourceKey<LevelStem> the_aurorian_stem = ResourceKey.create(Registries.LEVEL_STEM, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "the_aurorian"));
    public static final ResourceKey<DimensionType> the_aurorian_type = ResourceKey.create(Registries.DIMENSION_TYPE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "the_aurorian"));
    public static final ResourceKey<NoiseGeneratorSettings> the_aurorian_noise = ResourceKey.create(Registries.NOISE_SETTINGS, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "the_aurorian"));
    public static final DeferredRegister<CreativeModeTab> CREATIVETABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TheAurorian.MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVETAB = CREATIVETABS.register(MODID, () -> CreativeModeTab.builder()
            .icon(() -> BlockRegistry.silentwood_sapling.asItem().getDefaultInstance())
            .title(Component.translatable("itemGroup.theaurorian"))
            .displayItems((params, output) -> {
                output.acceptAll(ItemRegistry.ITEMS.getEntries().stream().map(DeferredHolder::get).map(Item::getDefaultInstance).toList());
                output.acceptAll(ItemRegistry.ITEMS_GEN.getEntries().stream().map(DeferredHolder::get).map(Item::getDefaultInstance).toList());
                output.acceptAll(ItemRegistry.ITEMS_GEN_HANDHELD.getEntries().stream().map(DeferredHolder::get).map(Item::getDefaultInstance).toList());
                output.acceptAll(ItemRegistry.ITEMS_GEN_SHIELD.getEntries().stream().map(DeferredHolder::get).map(Item::getDefaultInstance).toList());
                output.acceptAll(ItemRegistry.ITEMS_SPAWN_EGGS.getEntries().stream().map(DeferredHolder::get).map(Item::getDefaultInstance).toList());
                output.acceptAll(ItemRegistry.ITEMS_GEN_KEY.getEntries().stream().map(DeferredHolder::get).map(Item::getDefaultInstance).toList());
                output.acceptAll(ItemRegistry.ITEMS_GEN_TEA.getEntries().stream().map(DeferredHolder::get).map(Item::getDefaultInstance).toList());
            })
            .build());

    public TheAurorian(ModContainer container, IEventBus bus, Dist dist) {
        container.registerConfig(ModConfig.Type.COMMON, CommonConfig.config);
        container.registerConfig(ModConfig.Type.CLIENT, ClientConfig.config);
        BlockRegistry.register(bus);
        ItemRegistry.register(bus);
        BlockEntityRegistry.BLOCK_ENTITIES.register(bus);
        MenuRegistry.MENUS.register(bus);
        RecipeRegistry.TYPES.register(bus);
        RecipeRegistry.SERIALIZERS.register(bus);
        EntityRegistry.ENTITIES.register(bus);
        POIRegistry.POIS.register(bus);
        MaterialTiers.ARMOR_MATERIALS.register(bus);
        CREATIVETABS.register(bus);
    }

}
