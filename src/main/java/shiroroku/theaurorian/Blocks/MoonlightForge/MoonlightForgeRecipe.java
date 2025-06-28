package shiroroku.theaurorian.Blocks.MoonlightForge;

import com.google.gson.JsonObject;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import shiroroku.theaurorian.Recipe.DoubleRecipeInput;
import shiroroku.theaurorian.Registry.RecipeRegistry;

public record MoonlightForgeRecipe(
        Ingredient input,
        Ingredient catalyst,
        ItemStack output
) implements Recipe<DoubleRecipeInput> {

    @Override
    public boolean matches(DoubleRecipeInput recipeInput, Level level) {
        if (level.isClientSide) {
            return false;
        }
        return input.test(recipeInput.first()) && catalyst.test(recipeInput.second());
    }

    @Override
    public ItemStack assemble(DoubleRecipeInput recipeInput, HolderLookup.Provider provider) {
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
        return RecipeRegistry.moonlight_forge_serializer.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeRegistry.moonlight_forge.get();
    }

    public static class Serializer implements RecipeSerializer<MoonlightForgeRecipe> {

        public static final MapCodec<MoonlightForgeRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("input").forGetter(MoonlightForgeRecipe::input),
                Ingredient.CODEC.fieldOf("catalyst").forGetter(MoonlightForgeRecipe::catalyst),
                ItemStack.CODEC.fieldOf("output").forGetter(MoonlightForgeRecipe::output)
        ).apply(inst, MoonlightForgeRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, MoonlightForgeRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, MoonlightForgeRecipe::input,
                        Ingredient.CONTENTS_STREAM_CODEC, MoonlightForgeRecipe::catalyst,
                        ItemStack.STREAM_CODEC, MoonlightForgeRecipe::output,
                        MoonlightForgeRecipe::new
                );

        @Override
        public MapCodec<MoonlightForgeRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, MoonlightForgeRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
