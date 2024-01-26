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
import shiroroku.theaurorian.Blocks.Scrapper.ScrapperRecipe;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.TheAurorian;

public class JEIScrapperCategory implements IRecipeCategory<ScrapperRecipe> {

    private final IDrawable bg, icon;
    public static final ResourceLocation screen = new ResourceLocation(TheAurorian.MODID, "textures/gui/scrapper.png");

    public JEIScrapperCategory(IGuiHelper guihelper) {
        this.bg = guihelper.createDrawable(screen, 22, 12, 129, 66);
        this.icon = guihelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockRegistry.scrapper.get()));
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ScrapperRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 18, 25).addIngredients(Ingredient.of(BlockRegistry.crystal.get().asItem()));
        builder.addSlot(RecipeIngredientRole.INPUT, 58, 5).addIngredients(recipe.input());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 58, 46).addIngredients(Ingredient.of(recipe.getResultItem()));
    }

    @Override
    public RecipeType<ScrapperRecipe> getRecipeType() {
        return JEIPlugin.scrapper;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.theaurorian.scrapper");
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
