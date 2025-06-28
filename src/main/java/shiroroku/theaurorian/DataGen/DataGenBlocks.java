package shiroroku.theaurorian.DataGen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.TheAurorian;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DataGenBlocks extends BlockStateProvider {

    public DataGenBlocks(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TheAurorian.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // AUTO GENERATED
        List<DeferredHolder<Block, ? extends Block>> BASIC = new ArrayList<>();
        BASIC.addAll(BlockRegistry.BLOCKS_GEN.getEntries());
        BASIC.addAll(BlockRegistry.BLOCKS_GEN_NL.getEntries());
        BASIC.stream().map(Supplier::get).forEach(block -> {
            simpleBlock(block);
            simpleBlockItem(block);
        });
        BlockRegistry.BLOCKS_GEN_NL_PLANT.getEntries().stream().map(Supplier::get).forEach(block -> {
            getVariantBuilder(block).partialState().setModels(new ConfiguredModel(models().cross(blockTexture(block).getPath(), blockTexture(block)).renderType("cutout")));
            itemModels().getBuilder(BuiltInRegistries.BLOCK.getKey(block).getPath()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", blockTexture(block));
        });

        // CUSTOM
        axisBlock((RotatedPillarBlock) BlockRegistry.silentwood_log.get());
        barsBlock(BlockRegistry.runestone_bars.get());
        barsBlock(BlockRegistry.moon_temple_bars.get());
        fenceBlock(BlockRegistry.silentwood_fence.get(), blockTexture(BlockRegistry.silentwood_planks.get()));
        simpleBlockItem(BlockRegistry.boss_spawner.get());
        simpleBlockItem(BlockRegistry.fog_wall.get());
        simpleBlockItem(BlockRegistry.silentwood_log.get());
        simpleBlockItem(BlockRegistry.aurorian_portal.get());
        slabBlock(BlockRegistry.aurorian_cobblestone_slab.get(), blockTexture(BlockRegistry.aurorian_cobblestone.get()));
        slabBlock(BlockRegistry.aurorian_deepslate_slab.get(), blockTexture(BlockRegistry.aurorian_deepslate.get()));
        slabBlock(BlockRegistry.silentwood_slab.get(), blockTexture(BlockRegistry.silentwood_planks.get()));
        stairsBlock(BlockRegistry.aurorian_cobblestone_stairs.get(), blockTexture(BlockRegistry.aurorian_cobblestone.get()));
        stairsBlock(BlockRegistry.aurorian_deepslate_stairs.get(), blockTexture(BlockRegistry.aurorian_deepslate.get()));
        stairsBlock(BlockRegistry.darkstone_stairs.get(), blockTexture(BlockRegistry.darkstone.get()));
        stairsBlock(BlockRegistry.runestone_stairs.get(), blockTexture(BlockRegistry.runestone.get()));
        stairsBlock(BlockRegistry.moon_temple_stairs.get(), blockTexture(BlockRegistry.moon_temple_bricks.get()));
        stairsBlock(BlockRegistry.silentwood_stairs.get(), blockTexture(BlockRegistry.silentwood_planks.get()));
        wallBlock(BlockRegistry.aurorian_cobblestone_wall.get(), blockTexture(BlockRegistry.aurorian_cobblestone.get()));
        wallBlock(BlockRegistry.aurorian_deepslate_wall.get(), blockTexture(BlockRegistry.aurorian_deepslate.get()));
    }

    private void wallBlock(Block parent, ResourceLocation texture) {
        super.wallBlock((WallBlock) parent, texture);
        ResourceLocation location = BuiltInRegistries.BLOCK.getKey(parent);
        itemModels().getBuilder(location.getPath()).parent(new ModelFile.UncheckedModelFile(mcLoc("block/wall_inventory"))).texture("wall", texture);
    }

    private void fenceBlock(Block parent, ResourceLocation texture) {
        super.fenceBlock((FenceBlock) parent, texture);
        ResourceLocation location = BuiltInRegistries.BLOCK.getKey(parent);
        itemModels().getBuilder(location.getPath()).parent(new ModelFile.UncheckedModelFile(mcLoc("block/fence_inventory"))).texture("texture", texture);
    }

    private void stairsBlock(Block parent, ResourceLocation texture) {
        super.stairsBlock((StairBlock) parent, texture);
        simpleBlockItem(parent);
    }

    private void simpleBlockItem(Block parent) {
        ResourceLocation location = BuiltInRegistries.BLOCK.getKey(parent);
        itemModels().getBuilder(location.getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + location.getPath())));
    }

    private void simpleBlockItem(Block parent, String renderType) {
        ResourceLocation location = BuiltInRegistries.BLOCK.getKey(parent);
        itemModels().getBuilder(location.getPath()).renderType(renderType).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + location.getPath())));
    }

    private void barsBlock(Block block) {
        ResourceLocation texture = blockTexture(block);
        this.paneBlockWithRenderType((IronBarsBlock) block, texture, texture, "cutout");
        itemModels().getBuilder(BuiltInRegistries.BLOCK.getKey(block).getPath()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", texture);
    }

    private void slabBlock(Block block, ResourceLocation texture) {
        this.slabBlock((SlabBlock) block, texture, texture);
        simpleBlockItem(block);
    }

    private static ResourceLocation append(ResourceLocation loc, String value) {
        return ResourceLocation.fromNamespaceAndPath(loc.getNamespace(), loc.getPath() + value);
    }
}
