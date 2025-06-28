package shiroroku.theaurorian.World;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseThresholdProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.TheAurorian;

import java.util.List;

public class AurorianFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILENTWOOD_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "silentwood_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> AURORIAN_GRASS_PATCH = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "aurorian_grass_patch"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> AURORIAN_PLANTS = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "aurorian_plants"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FOREST_ROCK = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "forest_rock"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVENDER_PATCH = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "lavender_patch"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CERULEAN = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "ore_cerulean"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_COAL = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "ore_coal"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GEODE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "ore_geode"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MOONSTONE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "ore_moonstone"));

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest canReplaceStone = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest canReplaceDeepslate = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        FeatureUtils.register(context, SILENTWOOD_TREE, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BlockRegistry.silentwood_log.get()),
                new StraightTrunkPlacer(12, 2, 1),
                BlockStateProvider.simple(BlockRegistry.silentwood_leaves.get()),
                new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(5, 7)),
                new TwoLayersFeatureSize(2, 0, 2)))
                .dirt(BlockStateProvider.simple(BlockRegistry.aurorian_dirt.get()))
                .ignoreVines()
                .build());

        FeatureUtils.register(context, AURORIAN_GRASS_PATCH, Feature.RANDOM_PATCH,
                FeatureUtils.simpleRandomPatchConfiguration(32,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BlockRegistry.aurorian_tallgrass.get())), BlockPredicate.ONLY_IN_AIR_PREDICATE)));

        FeatureUtils.register(context, AURORIAN_PLANTS, Feature.FLOWER,
                new RandomPatchConfiguration(64, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                        new NoiseThresholdProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0), 0.005f, -0.8f, 0.33333334f,
                                BlockRegistry.lavender_block.get().defaultBlockState(),
                                List.of(BlockRegistry.lavender_block.get().defaultBlockState()),
                                List.of(BlockRegistry.silkberry_block.get().defaultBlockState(), BlockRegistry.petunia.get().defaultBlockState()))))));

        FeatureUtils.register(context, FOREST_ROCK, Feature.FOREST_ROCK,
                new BlockStateConfiguration(BlockRegistry.aurorian_cobblestone.get().defaultBlockState()));

        FeatureUtils.register(context, LAVENDER_PATCH, Feature.RANDOM_PATCH,
                FeatureUtils.simpleRandomPatchConfiguration(32,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BlockRegistry.lavender_block.get())), BlockPredicate.ONLY_IN_AIR_PREDICATE)));

        FeatureUtils.register(context, ORE_CERULEAN, Feature.ORE,
                new OreConfiguration(List.of(
                        OreConfiguration.target(canReplaceStone, BlockRegistry.cerulean_ore.get().defaultBlockState()),
                        OreConfiguration.target(canReplaceDeepslate, BlockRegistry.deepslate_cerulean_ore.get().defaultBlockState())
                ), 5, 0.0f));

        FeatureUtils.register(context, ORE_COAL, Feature.ORE,
                new OreConfiguration(List.of(
                        OreConfiguration.target(canReplaceStone, BlockRegistry.aurorian_coal_ore.get().defaultBlockState()),
                        OreConfiguration.target(canReplaceDeepslate, BlockRegistry.aurorian_coal_ore.get().defaultBlockState())
                ), 7, 0.0f));

        FeatureUtils.register(context, ORE_GEODE, Feature.ORE,
                new OreConfiguration(List.of(
                        OreConfiguration.target(canReplaceStone, BlockRegistry.geode.get().defaultBlockState()),
                        OreConfiguration.target(canReplaceDeepslate, BlockRegistry.geode.get().defaultBlockState())
                ), 5, 0.0f));

        FeatureUtils.register(context, ORE_MOONSTONE, Feature.ORE,
                new OreConfiguration(List.of(
                        OreConfiguration.target(canReplaceStone, BlockRegistry.moonstone_ore.get().defaultBlockState()),
                        OreConfiguration.target(canReplaceDeepslate, BlockRegistry.deepslate_moonstone_ore.get().defaultBlockState())
                ), 5, 0.0f));

    }
}
