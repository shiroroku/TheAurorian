package shiroroku.theaurorian.World;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.TheAurorian;

public class AurorianPlacements {
    public static final ResourceKey<PlacedFeature> SILENTWOOD_TREE = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "silentwood_tree"));
    public static final ResourceKey<PlacedFeature> AURORIAN_GRASS_PATCH = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "aurorian_grass_patch"));
    public static final ResourceKey<PlacedFeature> AURORIAN_PLANTS = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "aurorian_plants"));
    public static final ResourceKey<PlacedFeature> FOREST_ROCK = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "forest_rock"));
    public static final ResourceKey<PlacedFeature> LAVENDER_PATCH = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "lavender_patch"));
    public static final ResourceKey<PlacedFeature> ORE_CERULEAN = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "ore_cerulean"));
    public static final ResourceKey<PlacedFeature> ORE_COAL = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "ore_coal"));
    public static final ResourceKey<PlacedFeature> ORE_GEODE = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "ore_geode"));
    public static final ResourceKey<PlacedFeature> ORE_MOONSTONE = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "ore_moonstone"));

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var features = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, AURORIAN_GRASS_PATCH, features.getOrThrow(AurorianFeatures.AURORIAN_GRASS_PATCH),
                NoiseThresholdCountPlacement.of(-0.8, 5, 10),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

        PlacementUtils.register(context, AURORIAN_PLANTS, features.getOrThrow(AurorianFeatures.AURORIAN_PLANTS),
                NoiseThresholdCountPlacement.of(-0.8, 5, 15),
                RarityFilter.onAverageOnceEvery(6),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome());

        PlacementUtils.register(context, FOREST_ROCK, features.getOrThrow(AurorianFeatures.FOREST_ROCK),
                CountPlacement.of(2),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome());

        PlacementUtils.register(context, LAVENDER_PATCH, features.getOrThrow(AurorianFeatures.LAVENDER_PATCH),
                NoiseThresholdCountPlacement.of(-0.8, 5, 10),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

        PlacementUtils.register(context, ORE_CERULEAN, features.getOrThrow(AurorianFeatures.ORE_CERULEAN),
                CountPlacement.of(24),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(64)),
                BiomeFilter.biome());

        PlacementUtils.register(context, ORE_COAL, features.getOrThrow(AurorianFeatures.ORE_COAL),
                CountPlacement.of(48),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.absolute(16), VerticalAnchor.absolute(116)),
                BiomeFilter.biome());

        PlacementUtils.register(context, ORE_GEODE, features.getOrThrow(AurorianFeatures.ORE_GEODE),
                CountPlacement.of(32),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.absolute(32), VerticalAnchor.absolute(116)),
                BiomeFilter.biome());

        PlacementUtils.register(context, ORE_MOONSTONE, features.getOrThrow(AurorianFeatures.ORE_MOONSTONE),
                CountPlacement.of(24),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32)),
                BiomeFilter.biome());

        PlacementUtils.register(context, SILENTWOOD_TREE, features.getOrThrow(AurorianFeatures.SILENTWOOD_TREE),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1f, 1), BlockRegistry.silentwood_sapling.get()));

    }
}
