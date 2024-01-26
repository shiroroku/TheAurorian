package shiroroku.theaurorian.Compat.JEI;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import shiroroku.theaurorian.Blocks.MoonlightForge.MoonlightForgeRecipe;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.TheAurorian;

public class JEIMoonlightForgeCategory implements IRecipeCategory<MoonlightForgeRecipe> {

    private final IDrawable bg, icon;
    public static final ResourceLocation screen = new ResourceLocation(TheAurorian.MODID, "textures/gui/moonlight_forge.png");

    public JEIMoonlightForgeCategory(IGuiHelper guihelper) {
        this.bg = guihelper.createDrawable(screen, 18, 27, 148, 32);
        this.icon = guihelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockRegistry.moonlight_forge.get()));
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MoonlightForgeRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 4, 8).addIngredients(recipe.input());
        builder.addSlot(RecipeIngredientRole.INPUT, 66, 8).addIngredients(recipe.catalyst());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124, 8).addIngredients(Ingredient.of(recipe.getResultItem()));
    }

    @Override
    public RecipeType<MoonlightForgeRecipe> getRecipeType() {
        return JEIPlugin.moonlight_forge;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.theaurorian.moonlight_forge");
    }

    @Override
    public IDrawable getBackground() {
        return bg;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }
}
