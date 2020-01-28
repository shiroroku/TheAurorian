package com.elseytd.theaurorian.Compat;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TileEntities.Furnace.TAContainer_Aurorian_Furnace;
import com.elseytd.theaurorian.TileEntities.Furnace.TAGui_Aurorian_Furnace;
import com.elseytd.theaurorian.TileEntities.Workbench.TAContainer_Silentwood_Workbench;
import com.elseytd.theaurorian.TileEntities.Workbench.TAGui_Silentwood_Workbench;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;

@mezz.jei.api.JEIPlugin
public class TACompat_JEI implements IModPlugin {

	@Override
	public void register(IModRegistry registry) {
		//Let JEI know these blocks use vanilla recipes and where to add the recipes click location
		registry.addRecipeCatalyst(new ItemStack(TABlocks.aurorianfurnace), VanillaRecipeCategoryUid.SMELTING, VanillaRecipeCategoryUid.FUEL);
		registry.addRecipeCatalyst(new ItemStack(TABlocks.silentwoodworkbench), VanillaRecipeCategoryUid.CRAFTING);
		registry.addRecipeClickArea(TAGui_Silentwood_Workbench.class, 88, 32, 28, 23, VanillaRecipeCategoryUid.CRAFTING);
		registry.addRecipeClickArea(TAGui_Aurorian_Furnace.class, 78, 32, 28, 23, VanillaRecipeCategoryUid.SMELTING, VanillaRecipeCategoryUid.FUEL);

		//Add recipe autofill button to our blocks
		IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
		recipeTransferRegistry.addRecipeTransferHandler(TAContainer_Silentwood_Workbench.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 36);
		recipeTransferRegistry.addRecipeTransferHandler(TAContainer_Aurorian_Furnace.class, VanillaRecipeCategoryUid.SMELTING, 0, 1, 3, 36);
		recipeTransferRegistry.addRecipeTransferHandler(TAContainer_Aurorian_Furnace.class, VanillaRecipeCategoryUid.FUEL, 1, 1, 3, 36);
	}

}
