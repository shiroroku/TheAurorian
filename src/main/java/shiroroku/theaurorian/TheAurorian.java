package shiroroku.theaurorian;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shiroroku.theaurorian.Registry.*;

@Mod(TheAurorian.MODID)
public class TheAurorian {
    public static final String MODID = "theaurorian";
    public static final Logger LOGGER = LogManager.getLogger();

    public static final CreativeModeTab CREATIVETAB = new CreativeModeTab(MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BlockRegistry.silentwood_sapling.get());
        }
    };

    public TheAurorian() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configuration.config);
        BlockRegistry.register(bus);
        ItemRegistry.register(bus);
        BlockEntityRegistry.BLOCK_ENTITIES.register(bus);
        MenuRegistry.MENUS.register(bus);
        RecipeRegistry.TYPES.register(bus);
        RecipeRegistry.SERIALIZERS.register(bus);
        EntityRegistry.ENTITIES.register(bus);
        EnchantRegistry.ENCHANTMENTS.register(bus);
    }

}
