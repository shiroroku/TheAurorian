package shiroroku.theaurorian.Blocks.Scrapper;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import shiroroku.theaurorian.Registry.RecipeRegistry;

public record ScrapperRecipe(ResourceLocation id, Ingredient input, ItemStack output) implements Recipe<Container> {

    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        return true;
    }

    @Override
    public ItemStack assemble(Container pContainer) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegistry.scrapper_serializer.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeRegistry.scrapper.get();
    }

    public static class Serializer implements RecipeSerializer<ScrapperRecipe> {

        @Override
        public ScrapperRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient input = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "input"));
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            return new ScrapperRecipe(recipeId, input, output);
        }

        @Override
        public ScrapperRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            Ingredient input = Ingredient.fromNetwork(buffer);
            ItemStack output = buffer.readItem();
            return new ScrapperRecipe(id, input, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ScrapperRecipe recipe) {
            recipe.input.toNetwork(buffer);
            buffer.writeItem(recipe.output);
        }
    }
}
