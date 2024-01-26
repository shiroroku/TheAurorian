package shiroroku.theaurorian.Blocks.MoonlightForge;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import shiroroku.theaurorian.Registry.RecipeRegistry;

public record MoonlightForgeRecipe(ResourceLocation id, Ingredient input, Ingredient catalyst, ItemStack output) implements Recipe<Container> {

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
        return RecipeRegistry.moonlight_forge_serializer.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeRegistry.moonlight_forge.get();
    }

    public static class Serializer implements RecipeSerializer<MoonlightForgeRecipe> {

        @Override
        public MoonlightForgeRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient input = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "input"));
            Ingredient catalyst = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "catalyst"));
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            return new MoonlightForgeRecipe(recipeId, input, catalyst, output);
        }

        @Override
        public MoonlightForgeRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            Ingredient input = Ingredient.fromNetwork(buffer);
            Ingredient catalyst = Ingredient.fromNetwork(buffer);
            ItemStack output = buffer.readItem();
            return new MoonlightForgeRecipe(id, input, catalyst, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MoonlightForgeRecipe recipe) {
            recipe.input.toNetwork(buffer);
            recipe.catalyst.toNetwork(buffer);
            buffer.writeItem(recipe.output);
        }
    }
}
