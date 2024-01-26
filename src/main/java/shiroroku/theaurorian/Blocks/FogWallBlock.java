package shiroroku.theaurorian.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.Vec3;

@SuppressWarnings("deprecation")
public class FogWallBlock extends Block {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private final double repellingForce = 0.20D;

    public FogWallBlock(Properties pProperties) {
        super(pProperties);
    }

    public Vec3 getRepellingForce(BlockState pState, Entity pEntity) {
        if(pEntity instanceof Player p && p.isCreative()){
            return pEntity.getDeltaMovement();
        }
        return getRepellingForce(pState, pEntity.getDeltaMovement());
    }

    public Vec3 getRepellingForce(BlockState pState, Vec3 baseForce) {
        double xV = baseForce.x;
        double zV = baseForce.z;
        switch (pState.getValue(FACING)) {
            default:
            case NORTH:
                zV += this.repellingForce;
                break;
            case SOUTH:
                zV -= this.repellingForce;
                break;
            case EAST:
                xV -= this.repellingForce;
                break;
            case WEST:
                xV += this.repellingForce;
                break;
        }
        return new Vec3(xV, baseForce.y, zV);
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        pEntity.setDeltaMovement(getRepellingForce(pState, pEntity));
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        super.animateTick(pState, pLevel, pPos, pRandom);
        Vec3 force = getRepellingForce(pState, Vec3.ZERO);
        pLevel.addParticle(ParticleTypes.WAX_OFF, pPos.getX() + pRandom.nextDouble(), pPos.getY() + pRandom.nextDouble(), pPos.getZ() + pRandom.nextDouble(), force.x * 4, 0, force.z * 4);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> definition) {
        definition.add(FACING);
    }
}
