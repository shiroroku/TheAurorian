package shiroroku.theaurorian.DataGen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import shiroroku.theaurorian.Registry.EnchantRegistry;
import shiroroku.theaurorian.TheAurorian;
import shiroroku.theaurorian.World.AurorianBiomes;
import shiroroku.theaurorian.World.AurorianFeatures;
import shiroroku.theaurorian.World.AurorianPlacements;

import java.util.List;
import java.util.OptionalLong;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DataGenDatapack extends DatapackBuiltinEntriesProvider {
    public DataGenDatapack(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(ResourceLocation.DEFAULT_NAMESPACE, TheAurorian.MODID));
    }

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, EnchantRegistry::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, AurorianFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, AurorianPlacements::bootstrap)
            .add(Registries.BIOME, AurorianBiomes::bootstrap)
            .add(Registries.LEVEL_STEM, context -> {
                var types = context.lookup(Registries.DIMENSION_TYPE);
                var noises = context.lookup(Registries.NOISE_SETTINGS);
                var biomes = context.lookup(Registries.BIOME);

                context.register(TheAurorian.the_aurorian_stem, new LevelStem(
                        types.getOrThrow(TheAurorian.the_aurorian_type),
                        new NoiseBasedChunkGenerator(
                                MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(
                                        List.of(
                                                Pair.of(Climate.parameters(0.0f, 0.2f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f), biomes.getOrThrow(AurorianBiomes.AURORIAN_FOREST)),
                                                Pair.of(Climate.parameters(0.0f, 0.1f, -0.3f, 0.0f, 0.0f, 0.0f, 0.0f), biomes.getOrThrow(AurorianBiomes.AURORIAN_PLAINS)),
                                                Pair.of(Climate.parameters(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f), biomes.getOrThrow(AurorianBiomes.AURORIAN_ROUGH_FOREST)),
                                                Pair.of(Climate.parameters(0.0f, 0.0f, 0.05f, 0.0f, 0.0f, 0.0f, 0.0f), biomes.getOrThrow(AurorianBiomes.AURORIAN_FOREST)),
                                                Pair.of(Climate.parameters(0.0f, 0.0f, -0.2f, 0.0f, 0.0f, 0.0f, 0.0f), biomes.getOrThrow(AurorianBiomes.AURORIAN_PLAINS)),
                                                Pair.of(Climate.parameters(0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f), biomes.getOrThrow(AurorianBiomes.AURORIAN_PLAINS))
                                        ))),
                                noises.getOrThrow(TheAurorian.the_aurorian_noise)
                        )
                ));
            })
            .add(Registries.DIMENSION_TYPE, context -> {
                context.register(TheAurorian.the_aurorian_type, new DimensionType(
                        OptionalLong.of(18000L),
                        true,
                        false,
                        false,
                        false,
                        1.0f,
                        true,
                        true,
                        -64,
                        384,
                        384,
                        BlockTags.INFINIBURN_OVERWORLD,
                        BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                        0.1f,
                        new DimensionType.MonsterSettings(
                                false,
                                false,
                                UniformInt.of(0, 7),
                                0
                        )
                ));
            })
            .add(Registries.NOISE_SETTINGS, context -> {

            });
}
