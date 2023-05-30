package com.shiroroku.theaurorian.Compat.JEI;

import com.shiroroku.theaurorian.AurorianMod;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class MoonlightForgeRecipeCategory implements IRecipeCategory<MoonlightForgeRecipeWrapper> {

	private final IDrawable GUI_TEXTURE;

	public MoonlightForgeRecipeCategory(IGuiHelper helper) {
		this.GUI_TEXTURE = helper.createDrawable(new ResourceLocation(AurorianMod.MODID, "textures/gui/moonlightforge.png"), 17, 22, 152, 44);
	}

	@Override
	public String getUid() {
		return JEICompat.UID_MOONLIGHTFORGE;
	}

	@Override
	public String getTitle() {
		return I18n.format("container.theaurorian.moonlightforge");
	}

	@Override
	public String getModName() {
		return AurorianMod.MODID;
	}

	@Override
	public IDrawable getBackground() {
		return this.GUI_TEXTURE;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, MoonlightForgeRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guistacks = recipeLayout.getItemStacks();
		guistacks.init(0, true, 4, 12);
		guistacks.init(1, true, 66, 12);
		guistacks.init(2, false, 124, 12);

		List<ItemStack> input1 = ingredients.getInputs(VanillaTypes.ITEM).get(0);
		List<ItemStack> input2 = ingredients.getInputs(VanillaTypes.ITEM).get(1);
		List<ItemStack> output = ingredients.getOutputs(VanillaTypes.ITEM).get(0);

		guistacks.set(0, input1);
		guistacks.set(1, input2);
		guistacks.set(2, output);
	}

}
