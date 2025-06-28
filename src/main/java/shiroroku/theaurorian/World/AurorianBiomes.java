package shiroroku.theaurorian.World;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import shiroroku.theaurorian.Registry.EntityRegistry;
import shiroroku.theaurorian.TheAurorian;
import shiroroku.theaurorian.Util.BiomeUtil;

public class AurorianBiomes {
    public static final ResourceKey<Biome> AURORIAN_FOREST = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "aurorian_forest"));
    public static final ResourceKey<Biome> AURORIAN_PLAINS = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "aurorian_plains"));
    public static final ResourceKey<Biome> AURORIAN_ROUGH_FOREST = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "aurorian_rough_forest"));

    public static void bootstrap(BootstrapContext<Biome> context) {
        var placements = context.lookup(Registries.PLACED_FEATURE);
        var carvers = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(AURORIAN_FOREST, aurorianForest(placements, carvers));
        context.register(AURORIAN_PLAINS, aurorianPlains(placements, carvers));
        context.register(AURORIAN_ROUGH_FOREST, aurorianRoughForest(placements, carvers));
    }

    private static Biome aurorianForest(HolderGetter<PlacedFeature> placements, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        var spawnBuilder = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityRegistry.hollow.get(), 100, 4, 6));

        var generationBuilder = new BiomeGenerationSettings.Builder(placements, carvers)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AurorianPlacements.SILENTWOOD_TREE);

        BiomeUtil.addAurorianOres(generationBuilder);
        BiomeUtil.addAurorianCaves(generationBuilder);
        BiomeUtil.addAurorianDefaultPlants(generationBuilder);

        return new Biome.BiomeBuilder()
                .temperature(0.7f)
                .downfall(0.8f)
                .hasPrecipitation(false)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .skyColor(7972607)
                        .fogColor(12638463)
                        .waterColor(16777215)
                        .waterFogColor(10131862)
                        .build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(generationBuilder.build())
                .build();
    }

    private static Biome aurorianPlains(HolderGetter<PlacedFeature> placements, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        var spawnBuilder = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityRegistry.hollow.get(), 100, 4, 6));

        var generationBuilder = new BiomeGenerationSettings.Builder(placements, carvers)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AurorianPlacements.LAVENDER_PATCH);

        BiomeUtil.addAurorianOres(generationBuilder);
        BiomeUtil.addAurorianCaves(generationBuilder);
        BiomeUtil.addAurorianDefaultPlants(generationBuilder);

        return new Biome.BiomeBuilder()
                .temperature(0.8f)
                .downfall(0.4f)
                .hasPrecipitation(false)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .skyColor(7972607)
                        .fogColor(12638463)
                        .waterColor(16777215)
                        .waterFogColor(10131862)
                        .build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(generationBuilder.build())
                .build();
    }

    private static Biome aurorianRoughForest(HolderGetter<PlacedFeature> placements, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        var spawnBuilder = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityRegistry.hollow.get(), 100, 4, 6));

        var generationBuilder = new BiomeGenerationSettings.Builder(placements, carvers)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AurorianPlacements.SILENTWOOD_TREE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AurorianPlacements.LAVENDER_PATCH)
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AurorianPlacements.FOREST_ROCK);

        BiomeUtil.addAurorianOres(generationBuilder);
        BiomeUtil.addAurorianCaves(generationBuilder);
        BiomeUtil.addAurorianDefaultPlants(generationBuilder);

        return new Biome.BiomeBuilder()
                .temperature(0.5f)
                .downfall(0.5f)
                .hasPrecipitation(false)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .skyColor(7972607)
                        .fogColor(12638463)
                        .waterColor(16777215)
                        .waterFogColor(10131862)
                        .build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(generationBuilder.build())
                .build();
    }
}
