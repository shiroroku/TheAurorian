package shiroroku.theaurorian.Blocks;

import net.minecraft.world.level.block.grower.TreeGrower;
import shiroroku.theaurorian.World.AurorianFeatures;

import java.util.Optional;

public class AurorianTreeGrowers {
    public static final TreeGrower SILENTWOOD = new TreeGrower("silentwood", Optional.empty(), Optional.of(AurorianFeatures.SILENTWOOD_TREE), Optional.empty());
}
