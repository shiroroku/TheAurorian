package shiroroku.theaurorian.Blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BushBlock;

public class BushBlockImpl extends BushBlock {

    public static final MapCodec<BushBlockImpl> CODEC = simpleCodec(BushBlockImpl::new);

    public BushBlockImpl(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }
}
