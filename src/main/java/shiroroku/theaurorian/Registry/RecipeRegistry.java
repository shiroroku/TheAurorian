package shiroroku.theaurorian.Registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import shiroroku.theaurorian.Blocks.MoonlightForge.MoonlightForgeRecipe;
import shiroroku.theaurorian.Blocks.Scrapper.ScrapperRecipe;
import shiroroku.theaurorian.TheAurorian;

public class RecipeRegistry {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, TheAurorian.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, TheAurorian.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<MoonlightForgeRecipe>> moonlight_forge_serializer = SERIALIZERS.register("moonlight_forge", MoonlightForgeRecipe.Serializer::new);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ScrapperRecipe>> scrapper_serializer = SERIALIZERS.register("scrapper", ScrapperRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<MoonlightForgeRecipe>> moonlight_forge = TYPES.register("moonlight_forge", () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "moonlight_forge")));
    public static final DeferredHolder<RecipeType<?>, RecipeType<ScrapperRecipe>> scrapper = TYPES.register("scrapper", () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "scrapper")));

}
