package shiroroku.theaurorian.Blocks.Scrapper;

import com.google.gson.JsonObject;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import shiroroku.theaurorian.Blocks.MoonlightForge.MoonlightForgeRecipe;
import shiroroku.theaurorian.Registry.RecipeRegistry;

public record ScrapperRecipe(Ingredient input, ItemStack output) implements Recipe<SingleRecipeInput> {

    @Override
    public boolean matches(SingleRecipeInput recipeInput, Level level) {
        if (level.isClientSide) {
            return false;
        }
        return input.test(recipeInput.item());
    }

    @Override
    public ItemStack assemble(SingleRecipeInput singleRecipeInput, HolderLookup.Provider provider) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.output.copy();
    }

    public ItemStack getResultItem() {
        return this.output.copy();
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

        public static final MapCodec<ScrapperRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("input").forGetter(ScrapperRecipe::input),
                ItemStack.CODEC.fieldOf("output").forGetter(ScrapperRecipe::output)
        ).apply(inst, ScrapperRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ScrapperRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, ScrapperRecipe::input,
                        ItemStack.STREAM_CODEC, ScrapperRecipe::output,
                        ScrapperRecipe::new
                );

        @Override
        public MapCodec<ScrapperRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ScrapperRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
