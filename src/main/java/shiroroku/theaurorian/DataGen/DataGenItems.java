package shiroroku.theaurorian.DataGen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import shiroroku.theaurorian.Registry.ItemRegistry;
import shiroroku.theaurorian.TheAurorian;

import java.util.Objects;
import java.util.function.Supplier;

public class DataGenItems extends ItemModelProvider {

    public DataGenItems(DataGenerator output, ExistingFileHelper existingFileHelper) {
        super(output, TheAurorian.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // AUTO GENERATED
        ItemRegistry.ITEMS_GEN_TEA.getEntries().stream().map(Supplier::get).forEach(this::teaItem);
        ItemRegistry.ITEMS_GEN.getEntries().stream().map(Supplier::get).forEach(this::basicItem);
        ItemRegistry.ITEMS_GEN_HANDHELD.getEntries().stream().map(Supplier::get).forEach(this::basicItemHandheld);
        ItemRegistry.ITEMS_GEN_SHIELD.getEntries().stream().map(Supplier::get).forEach(this::shieldItem);
        ItemRegistry.ITEMS_SPAWN_EGGS.getEntries().stream().map(Supplier::get).forEach((e) -> getBuilder(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(e)).toString()).parent(new ModelFile.UncheckedModelFile("item/template_spawn_egg")));
    }

    public void teaItem(Item item) {
        getBuilder(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).toString()).parent(getExistingFile(modLoc("item/tea")));
    }

    public void basicItemHandheld(Item item) {
        ResourceLocation location = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item));
        getBuilder(location.toString())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", new ResourceLocation(location.getNamespace(), "item/" + location.getPath()));
    }

    private void shieldItem(Item item) {
        ResourceLocation location = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item));
        getBuilder(item.toString() + "_blocking")
                .parent(getExistingFile(modLoc("item/shield_blocking")))
                .texture("layer0", new ResourceLocation(location.getNamespace(), "item/" + location.getPath()));
        getBuilder(item.toString())
                .parent(getExistingFile(modLoc("item/shield")))
                .texture("layer0", new ResourceLocation(location.getNamespace(), "item/" + location.getPath()))
                .override()
                .predicate(new ResourceLocation("blocking"), 1)
                .model(new ModelFile.UncheckedModelFile(new ResourceLocation(location.getNamespace(), "item/" + location.getPath() + "_blocking")));
    }

}
