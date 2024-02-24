package shiroroku.theaurorian.DataGen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.TheAurorian;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DataGenBlocks extends BlockStateProvider {

    public DataGenBlocks(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, TheAurorian.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // AUTO GENERATED
        List<RegistryObject<Block>> BASIC = new ArrayList<>();
        BASIC.addAll(BlockRegistry.BLOCKS_GEN.getEntries());
        BASIC.addAll(BlockRegistry.BLOCKS_GEN_NL.getEntries());
        BASIC.stream().map(Supplier::get).forEach((block) -> {
            simpleBlock(block);
            simpleBlockItem(block);
        });
        BlockRegistry.BLOCKS_GEN_NL_PLANT.getEntries().stream().map(Supplier::get).forEach((block) -> {
            getVariantBuilder(block).partialState().setModels(new ConfiguredModel(models().cross(blockTexture(block).getPath(), blockTexture(block)).renderType("cutout")));
            itemModels().getBuilder(ForgeRegistries.BLOCKS.getKey(block).getPath()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", blockTexture(block));
        });

        // CUSTOM
        axisBlock((RotatedPillarBlock) BlockRegistry.silentwood_log.get());
        simpleBlockItem(BlockRegistry.silentwood_log.get());
        barsBlock(BlockRegistry.runestone_bars.get());
        stairsBlock((StairBlock) BlockRegistry.runestone_stairs.get(), blockTexture(BlockRegistry.runestone.get()));
        simpleBlockItem(BlockRegistry.runestone_stairs.get());
        simpleBlockItem(BlockRegistry.fog_wall.get());
        simpleBlockItem(BlockRegistry.boss_spawner.get());
    }

    private void simpleBlockItem(Block parent) {
        ResourceLocation location = ForgeRegistries.BLOCKS.getKey(parent);
        itemModels().getBuilder(location.getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + location.getPath())));
    }
    private void simpleBlockItem(Block parent, String renderType) {
        ResourceLocation location = ForgeRegistries.BLOCKS.getKey(parent);
        itemModels().getBuilder(location.getPath()).renderType(renderType).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + location.getPath())));
    }

    private void barsBlock(Block block) {
        ResourceLocation texture = blockTexture(block);
        this.paneBlockWithRenderType((IronBarsBlock) block, texture, texture, "cutout");
        itemModels().getBuilder(ForgeRegistries.BLOCKS.getKey(block).getPath()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", texture);
    }

    private static ResourceLocation append(ResourceLocation loc, String value) {
        return new ResourceLocation(loc.getNamespace(), loc.getPath() + value);
    }
}
