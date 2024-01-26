package shiroroku.theaurorian.Registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import shiroroku.theaurorian.Blocks.MoonlightForge.MoonlightForgeRecipe;
import shiroroku.theaurorian.Blocks.Scrapper.ScrapperRecipe;
import shiroroku.theaurorian.TheAurorian;

public class RecipeRegistry {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TheAurorian.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, TheAurorian.MODID);

    public static final RegistryObject<RecipeType<ScrapperRecipe>> scrapper = TYPES.register("scrapper", () -> RecipeType.simple(new ResourceLocation(TheAurorian.MODID, "scrapper")));
    public static final RegistryObject<RecipeSerializer<ScrapperRecipe>> scrapper_serializer = SERIALIZERS.register("scrapper", ScrapperRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<MoonlightForgeRecipe>> moonlight_forge = TYPES.register("moonlight_forge", () -> RecipeType.simple(new ResourceLocation(TheAurorian.MODID, "moonlight_forge")));
    public static final RegistryObject<RecipeSerializer<MoonlightForgeRecipe>> moonlight_forge_serializer = SERIALIZERS.register("moonlight_forge", MoonlightForgeRecipe.Serializer::new);

}
