package shiroroku.theaurorian.Util;

import net.minecraft.data.worldgen.Carvers;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import shiroroku.theaurorian.World.AurorianPlacements;

public class BiomeUtil {
    public static BiomeGenerationSettings.Builder addAurorianOres(BiomeGenerationSettings.Builder builder) {
        return builder
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, AurorianPlacements.ORE_CERULEAN)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, AurorianPlacements.ORE_COAL)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, AurorianPlacements.ORE_GEODE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, AurorianPlacements.ORE_MOONSTONE);
    }

    public static BiomeGenerationSettings.Builder addAurorianCaves(BiomeGenerationSettings.Builder builder) {
        return builder
                .addCarver(GenerationStep.Carving.AIR, Carvers.CAVE)
                .addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND)
                .addCarver(GenerationStep.Carving.AIR, Carvers.CANYON);
    }

    public static BiomeGenerationSettings.Builder addAurorianDefaultPlants(BiomeGenerationSettings.Builder builder) {
        return builder
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AurorianPlacements.AURORIAN_GRASS_PATCH)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AurorianPlacements.AURORIAN_PLANTS);
    }
}
