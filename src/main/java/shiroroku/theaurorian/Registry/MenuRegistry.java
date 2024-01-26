package shiroroku.theaurorian.Registry;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import shiroroku.theaurorian.Blocks.MoonlightForge.MoonlightForgeMenu;
import shiroroku.theaurorian.Blocks.Scrapper.ScrapperMenu;
import shiroroku.theaurorian.TheAurorian;

public class MenuRegistry {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TheAurorian.MODID);

    public static final RegistryObject<MenuType<ScrapperMenu>> scrapper = MENUS.register("scrapper", () -> IForgeMenuType.create((id, playerInv, data) -> new ScrapperMenu(id, data.readBlockPos(), playerInv, playerInv.player)));
    public static final RegistryObject<MenuType<MoonlightForgeMenu>> moonlight_forge = MENUS.register("moonlight_forge", () -> IForgeMenuType.create((id, playerInv, data) -> new MoonlightForgeMenu(id, data.readBlockPos(), playerInv, playerInv.player)));
}
