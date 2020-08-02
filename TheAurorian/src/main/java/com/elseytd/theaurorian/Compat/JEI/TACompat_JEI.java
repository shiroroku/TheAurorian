package com.elseytd.theaurorian.Compat.JEI;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.Recipes.MoonlightForgeRecipe;
import com.elseytd.theaurorian.Recipes.MoonlightForgeRecipeHandler;
import com.elseytd.theaurorian.Recipes.ScrapperRecipe;
import com.elseytd.theaurorian.Recipes.ScrapperRecipeHandler;
import com.elseytd.theaurorian.TileEntities.AurorianFurnace_Container;
import com.elseytd.theaurorian.TileEntities.AurorianFurnace_Gui;
import com.elseytd.theaurorian.TileEntities.MoonLightForge_Container;
import com.elseytd.theaurorian.TileEntities.MoonLightForge_Gui;
import com.elseytd.theaurorian.TileEntities.Scrapper_Container;
import com.elseytd.theaurorian.TileEntities.Scrapper_Gui;
import com.elseytd.theaurorian.TileEntities.SilentwoodWorkbench_Container;
import com.elseytd.theaurorian.TileEntities.SilentwoodWorkbench_Gui;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;

@mezz.jei.api.JEIPlugin
public class TACompat_JEI implements IModPlugin {

	public static final String UID_MOONLIGHTFORGE = "theaurorian.moonlightforge";
	public static final String UID_SCRAPPER = "theaurorian.scrapper";

	@Override
	public void register(IModRegistry registry) {
		//Let JEI know these blocks use vanilla recipes and where to add the recipes click location
		registry.addRecipeCatalyst(new ItemStack(TABlocks.aurorianfurnace), VanillaRecipeCategoryUid.SMELTING, VanillaRecipeCategoryUid.FUEL);
		registry.addRecipeCatalyst(new ItemStack(TABlocks.silentwoodworkbench), VanillaRecipeCategoryUid.CRAFTING);
		registry.addRecipeClickArea(SilentwoodWorkbench_Gui.class, 88, 32, 28, 23, VanillaRecipeCategoryUid.CRAFTING);
		registry.addRecipeClickArea(AurorianFurnace_Gui.class, 78, 32, 28, 23, VanillaRecipeCategoryUid.SMELTING, VanillaRecipeCategoryUid.FUEL);

		//Add recipe autofill button to our blocks
		IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
		recipeTransferRegistry.addRecipeTransferHandler(SilentwoodWorkbench_Container.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 36);
		recipeTransferRegistry.addRecipeTransferHandler(AurorianFurnace_Container.class, VanillaRecipeCategoryUid.SMELTING, 0, 1, 3, 36);
		recipeTransferRegistry.addRecipeTransferHandler(AurorianFurnace_Container.class, VanillaRecipeCategoryUid.FUEL, 1, 1, 3, 36);

		//Moonlight Forge
		registry.addRecipeCatalyst(new ItemStack(TABlocks.moonlightforge), UID_MOONLIGHTFORGE);
		registry.addRecipes(MoonlightForgeRecipeHandler.allRecipes, UID_MOONLIGHTFORGE);
		registry.handleRecipes(MoonlightForgeRecipe.class, MoonlightForgeRecipeWrapper::new, UID_MOONLIGHTFORGE);
		registry.addRecipeClickArea(MoonLightForge_Gui.class, 107, 35, 24, 17, UID_MOONLIGHTFORGE);
		registry.getRecipeTransferRegistry().addRecipeTransferHandler(MoonLightForge_Container.class, UID_MOONLIGHTFORGE, 0, 2, 3, 36);

		//Scrapper
		registry.addRecipeCatalyst(new ItemStack(TABlocks.scrapper), UID_SCRAPPER);
		registry.addRecipes(ScrapperRecipeHandler.allRecipes, UID_SCRAPPER);
		registry.handleRecipes(ScrapperRecipe.class, ScrapperRecipeWrapper::new, UID_SCRAPPER);
		registry.addRecipeClickArea(Scrapper_Gui.class, 98, 16, 9, 55, UID_SCRAPPER);
		registry.getRecipeTransferRegistry().addRecipeTransferHandler(Scrapper_Container.class, UID_SCRAPPER, 0, 2, 3, 36);
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new MoonlightForgeRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new ScrapperRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
	}

}
