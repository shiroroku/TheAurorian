package shiroroku.theaurorian.World.Feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import org.jetbrains.annotations.Nullable;
import shiroroku.theaurorian.Registry.BlockRegistry;

public class SilentwoodTreeFeature extends AbstractTreeGrower {

    private static final Holder<ConfiguredFeature<TreeConfiguration, ?>> TREE = FeatureUtils.register("silentwood_tree", Feature.TREE, (
            new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockRegistry.silentwood_log.get()),
                    new StraightTrunkPlacer(12, 2, 1), BlockStateProvider.simple(BlockRegistry.silentwood_leaves.get()),
                    new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(5, 7)),
                    new TwoLayersFeatureSize(2, 0, 2)))
            .ignoreVines()
            .build());

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pLargeHive) {
        return TREE; //todo use json silentwood instead
    }
}
