package com.shiroroku.theaurorian.Compat.JEI;

import com.shiroroku.theaurorian.Recipes.MoonlightForgeRecipe;
import com.shiroroku.theaurorian.Recipes.MoonlightForgeRecipeHandler;
import com.shiroroku.theaurorian.Recipes.ScrapperRecipe;
import com.shiroroku.theaurorian.Recipes.ScrapperRecipeHandler;
import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.TileEntities.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;

@mezz.jei.api.JEIPlugin
public class JEICompat implements IModPlugin {

	public static final String UID_MOONLIGHTFORGE = "theaurorian.moonlightforge";
	public static final String UID_SCRAPPER = "theaurorian.scrapper";

	@Override
	public void register(IModRegistry registry) {
		//Let JEI know these blocks use vanilla recipes and where to add the recipes click location
		registry.addRecipeCatalyst(new ItemStack(BlockRegistry.Registry.AURORIANFURNACEOFF.getBlock()), VanillaRecipeCategoryUid.SMELTING, VanillaRecipeCategoryUid.FUEL);
		registry.addRecipeCatalyst(new ItemStack(BlockRegistry.Registry.SILENTWOODWORKBENCH.getBlock()), VanillaRecipeCategoryUid.CRAFTING);
		registry.addRecipeClickArea(SilentwoodWorkbenchGui.class, 88, 32, 28, 23, VanillaRecipeCategoryUid.CRAFTING);
		registry.addRecipeClickArea(AurorianFurnaceGui.class, 78, 32, 28, 23, VanillaRecipeCategoryUid.SMELTING, VanillaRecipeCategoryUid.FUEL);

		//Add recipe autofill button to our blocks
		IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
		recipeTransferRegistry.addRecipeTransferHandler(SilentwoodWorkbenchContainer.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 36);
		recipeTransferRegistry.addRecipeTransferHandler(AurorianFurnaceContainer.class, VanillaRecipeCategoryUid.SMELTING, 0, 1, 3, 36);
		recipeTransferRegistry.addRecipeTransferHandler(AurorianFurnaceContainer.class, VanillaRecipeCategoryUid.FUEL, 1, 1, 3, 36);

		//Moonlight Forge
		registry.addRecipeCatalyst(new ItemStack(BlockRegistry.Registry.MOONLIGHTFORGE.getBlock()), UID_MOONLIGHTFORGE);
		registry.addRecipes(MoonlightForgeRecipeHandler.allRecipes, UID_MOONLIGHTFORGE);
		registry.handleRecipes(MoonlightForgeRecipe.class, MoonlightForgeRecipeWrapper::new, UID_MOONLIGHTFORGE);
		registry.addRecipeClickArea(MoonLightForgeGui.class, 107, 35, 24, 17, UID_MOONLIGHTFORGE);
		registry.getRecipeTransferRegistry().addRecipeTransferHandler(MoonLightForgeContainer.class, UID_MOONLIGHTFORGE, 0, 2, 3, 36);

		//Scrapper
		registry.addRecipeCatalyst(new ItemStack(BlockRegistry.Registry.SCRAPPER.getBlock()), UID_SCRAPPER);
		registry.addRecipes(ScrapperRecipeHandler.allRecipes, UID_SCRAPPER);
		registry.handleRecipes(ScrapperRecipe.class, ScrapperRecipeWrapper::new, UID_SCRAPPER);
		registry.addRecipeClickArea(ScrapperGui.class, 98, 16, 9, 55, UID_SCRAPPER);
		registry.getRecipeTransferRegistry().addRecipeTransferHandler(ScrapperContainer.class, UID_SCRAPPER, 0, 2, 3, 36);
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new MoonlightForgeRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new ScrapperRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
	}

}
