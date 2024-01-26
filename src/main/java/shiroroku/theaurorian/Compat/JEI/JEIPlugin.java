package shiroroku.theaurorian.Compat.JEI;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import shiroroku.theaurorian.Blocks.MoonlightForge.MoonlightForgeMenu;
import shiroroku.theaurorian.Blocks.MoonlightForge.MoonlightForgeRecipe;
import shiroroku.theaurorian.Blocks.MoonlightForge.MoonlightForgeScreen;
import shiroroku.theaurorian.Blocks.Scrapper.ScrapperMenu;
import shiroroku.theaurorian.Blocks.Scrapper.ScrapperRecipe;
import shiroroku.theaurorian.Blocks.Scrapper.ScrapperScreen;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.Registry.MenuRegistry;
import shiroroku.theaurorian.Registry.RecipeRegistry;
import shiroroku.theaurorian.TheAurorian;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    public static final RecipeType<ScrapperRecipe> scrapper = RecipeType.create(TheAurorian.MODID, "scrapper", ScrapperRecipe.class);
    public static final RecipeType<MoonlightForgeRecipe> moonlight_forge = RecipeType.create(TheAurorian.MODID, "moonlight_forge", MoonlightForgeRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(TheAurorian.MODID, "recipes");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new JEIScrapperCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new JEIMoonlightForgeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        registry.addRecipes(scrapper, Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(RecipeRegistry.scrapper.get()));
        registry.addRecipes(moonlight_forge, Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(RecipeRegistry.moonlight_forge.get()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        registry.addRecipeCatalyst(new ItemStack(BlockRegistry.scrapper.get()), scrapper);
        registry.addRecipeCatalyst(new ItemStack(BlockRegistry.moonlight_forge.get()), moonlight_forge);
        registry.addRecipeCatalyst(new ItemStack(BlockRegistry.aurorian_furnace.get()), RecipeTypes.SMELTING);
        registry.addRecipeCatalyst(new ItemStack(BlockRegistry.silentwood_crafting_table.get()), RecipeTypes.CRAFTING);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registry) {
        registry.addRecipeTransferHandler(ScrapperMenu.class, MenuRegistry.scrapper.get(), scrapper, 0, 3, 3, 36);
        registry.addRecipeTransferHandler(MoonlightForgeMenu.class, MenuRegistry.moonlight_forge.get(), moonlight_forge, 0, 3, 3, 36);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registry) {
        registry.addRecipeClickArea(ScrapperScreen.class, 84, 35, 8, 21, scrapper);
        registry.addRecipeClickArea(MoonlightForgeScreen.class, 107, 35, 24, 17, moonlight_forge);
    }
}
