package shiroroku.theaurorian.Portal;

import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.TicketType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.levelgen.Heightmap;
import shiroroku.theaurorian.Blocks.AurorianPortal;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.Registry.POIRegistry;

import java.util.Comparator;
import java.util.Optional;

public class AurorianPortalTeleporter {

    protected final ServerLevel level;

    public AurorianPortalTeleporter(ServerLevel level) {
        this.level = level;
    }

    public Optional<BlockUtil.FoundRectangle> findClosestPortalPosition(BlockPos pPos) {
        PoiManager manager = this.level.getPoiManager();
        manager.ensureLoadedAndValid(this.level, pPos, 64);
        Optional<PoiRecord> optional = manager.getInSquare((poi) -> poi.is(POIRegistry.aurorian_portal.getKey()), pPos, 64, PoiManager.Occupancy.ANY)
                .sorted(Comparator.<PoiRecord>comparingDouble((poi) -> poi.getPos().distSqr(pPos)).thenComparingInt((poi) -> poi.getPos().getY()))
                .filter((poi) -> this.level.getBlockState(poi.getPos()).is(BlockRegistry.aurorian_portal.get()))
                .findFirst();
        return optional.map((poi) -> {
            BlockPos blockPos = poi.getPos();
            this.level.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(blockPos), 3, blockPos);
            BlockState blockState = this.level.getBlockState(blockPos);
            return BlockUtil.getLargestRectangleAround(blockPos, blockState.getValue(AurorianPortal.FACING).getAxis(), 21, Direction.Axis.Y, 21, (pos) -> this.level.getBlockState(pos) == blockState);
        });
    }

    public Optional<BlockUtil.FoundRectangle> createPortal(BlockPos pPos, Direction direction) {
        double d0 = -1.0D;
        BlockPos blockpos = null;
        double d1 = -1.0D;
        BlockPos blockpos1 = null;
        WorldBorder worldBorder = this.level.getWorldBorder();
        int maxY = Math.min(this.level.getMaxBuildHeight(), this.level.getMinBuildHeight() + this.level.getLogicalHeight()) - 1;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.mutable();

        for (BlockPos.MutableBlockPos mut : BlockPos.spiralAround(pPos, 48, Direction.EAST, Direction.SOUTH)) {
            int height = Math.min(maxY, this.level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, mut.getX(), mut.getZ()));
            if (worldBorder.isWithinBounds(mut) && worldBorder.isWithinBounds(mut.move(direction, 1)) && !this.level.structureManager().hasAnyStructureAt(mut)) {
                mut.move(direction.getOpposite(), 1);
                // goes down from heightmap to bottom of world
                for (int y = height; y >= this.level.getMinBuildHeight(); --y) {
                    // move mut
                    mut.setY(y);
                    // when empty block
                    if (this.level.isEmptyBlock(mut)) {
                        int emptyY;
                        // go from current y to bottom of world or until theres another empty block below
                        emptyY = y;
                        while (y > this.level.getMinBuildHeight() && this.level.isEmptyBlock(mut.move(Direction.DOWN))) {
                            --y;
                        }

                        if (y + 4 <= maxY) {
                            int j1 = emptyY - y;
                            if (j1 <= 0 || j1 >= 3) {
                                mut.setY(y);
                                if (this.isValidFrame(mut, blockpos$mutableblockpos, direction, 0)) {
                                    double d2 = pPos.distSqr(mut);
                                    if (this.isValidFrame(mut, blockpos$mutableblockpos, direction, -1) && this.isValidFrame(mut, blockpos$mutableblockpos, direction, 1) && (d0 == -1.0D || d0 > d2)) {
                                        d0 = d2;
                                        blockpos = mut.immutable();
                                    }

                                    if (d0 == -1.0D && (d1 == -1.0D || d1 > d2)) {
                                        d1 = d2;
                                        blockpos1 = mut.immutable();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (d0 == -1.0D && d1 != -1.0D) {
            blockpos = blockpos1;
            d0 = d1;
        }

        if (d0 == -1.0D) {
            int k1 = Math.max(this.level.getMinBuildHeight() + 1, 70);
            int i2 = maxY - 9;
            if (i2 < k1) {
                return Optional.empty();
            }

            blockpos = (new BlockPos(pPos.getX(), Mth.clamp(pPos.getY(), k1, i2), pPos.getZ())).immutable();
            Direction direction1 = direction.getClockWise();
            if (!worldBorder.isWithinBounds(blockpos)) {
                return Optional.empty();
            }

            for (int i3 = -1; i3 < 2; ++i3) {
                for (int j3 = 0; j3 < 2; ++j3) {
                    for (int k3 = -1; k3 < 3; ++k3) {
                        BlockState blockstate1 = k3 < 0 ? BlockRegistry.aurorian_portal_frame.get().defaultBlockState() : Blocks.AIR.defaultBlockState();
                        blockpos$mutableblockpos.setWithOffset(blockpos, j3 * direction.getStepX() + i3 * direction1.getStepX(), k3, j3 * direction.getStepZ() + i3 * direction1.getStepZ());
                        this.level.setBlockAndUpdate(blockpos$mutableblockpos, blockstate1);
                    }
                }
            }
        }

        for (int l1 = -1; l1 < 3; ++l1) {
            for (int j2 = -1; j2 < 4; ++j2) {
                if (l1 == -1 || l1 == 2 || j2 == -1 || j2 == 3) {
                    blockpos$mutableblockpos.setWithOffset(blockpos, l1 * direction.getStepX(), j2, l1 * direction.getStepZ());
                    this.level.setBlock(blockpos$mutableblockpos, BlockRegistry.aurorian_portal_frame.get().defaultBlockState(), 3);
                }
            }
        }

        BlockState portal = BlockRegistry.aurorian_portal.get().defaultBlockState().setValue(AurorianPortal.FACING, direction.getClockWise());
        for (int k2 = 0; k2 < 2; ++k2) {
            for (int l2 = 0; l2 < 3; ++l2) {
                blockpos$mutableblockpos.setWithOffset(blockpos, k2 * direction.getStepX(), l2, k2 * direction.getStepZ());
                this.level.setBlock(blockpos$mutableblockpos, portal, 18);
            }
        }

        return Optional.of(new BlockUtil.FoundRectangle(blockpos.immutable(), 2, 3));
    }

    private boolean canPortalReplaceBlock(BlockPos.MutableBlockPos pos) {
        BlockState blockstate = this.level.getBlockState(pos);
        return blockstate.canBeReplaced() && blockstate.getFluidState().isEmpty();
    }

    private boolean isValidFrame(BlockPos pOriginalPos, BlockPos.MutableBlockPos pOffsetPos, Direction pDirection, int pOffsetScale) {
        Direction direction = pDirection.getClockWise();
        for (int xz = -1; xz < 3; ++xz) {
            for (int y = -1; y < 4; ++y) {
                pOffsetPos.setWithOffset(pOriginalPos, pDirection.getStepX() * xz + direction.getStepX() * pOffsetScale, y, pDirection.getStepZ() * xz + direction.getStepZ() * pOffsetScale);
                if (y < 0 && !this.level.getBlockState(pOffsetPos).isSolid()) {
                    return false;
                }
                if (y >= 0 && !this.canPortalReplaceBlock(pOffsetPos)) {
                    return false;
                }
            }
        }
        return true;
    }
}
