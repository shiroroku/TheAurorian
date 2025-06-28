package shiroroku.theaurorian.Registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import shiroroku.theaurorian.Blocks.MoonlightForge.MoonlightForgeMenu;
import shiroroku.theaurorian.Blocks.MoonlightForge.MoonlightForgeScreen;
import shiroroku.theaurorian.Blocks.Scrapper.ScrapperMenu;
import shiroroku.theaurorian.Blocks.Scrapper.ScrapperScreen;
import shiroroku.theaurorian.TheAurorian;

public class MenuRegistry {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, TheAurorian.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<MoonlightForgeMenu>> moonlight_forge = MENUS.register("moonlight_forge", () -> IMenuTypeExtension.create(MoonlightForgeMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<ScrapperMenu>> scrapper = MENUS.register("scrapper", () -> IMenuTypeExtension.create(ScrapperMenu::new));

    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(moonlight_forge.get(), MoonlightForgeScreen::new);
        event.register(scrapper.get(), ScrapperScreen::new);
    }
}
