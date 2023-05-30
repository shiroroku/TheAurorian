package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class AurorianFarmTile extends Block {

	public static final String BLOCKNAME = "aurorianfarmtile";
	public static final PropertyInteger MOISTURE = PropertyInteger.create("moisture", 0, 7);
	protected static final AxisAlignedBB FARMLAND_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
	protected static final AxisAlignedBB field_194405_c = new AxisAlignedBB(0.0D, 0.9375D, 0.0D, 1.0D, 1.0D, 1.0D);

	public AurorianFarmTile() {
		super(Material.GROUND);
		this.setDefaultState(this.blockState.getBaseState().withProperty(MOISTURE, Integer.valueOf(0)));
		this.setTickRandomly(true);
		this.setLightOpacity(0);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setHardness(0.5F);
		this.setHarvestLevel("shovel", 0);
		this.setSoundType(SoundType.GROUND);
		this.setUnlocalizedName(AurorianMod.MODID + "." + BLOCKNAME);
		this.setRegistryName(BLOCKNAME);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FARMLAND_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		int i = state.getValue(MOISTURE).intValue();

		if (!this.hasWater(worldIn, pos) && !worldIn.isRainingAt(pos.up())) {
			if (i > 0) {
				worldIn.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(i - 1)), 2);
			} else if (!this.hasCrops(worldIn, pos)) {
				turnToDirt(worldIn, pos);
			}
		} else if (i < 7) {
			worldIn.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(7)), 2);
		}
	}

	@Override
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		if (net.minecraftforge.common.ForgeHooks.onFarmlandTrample(worldIn, pos, BlockRegistry.Registry.AURORIANDIRT.getBlock().getDefaultState(), fallDistance, entityIn)) {
			turnToDirt(worldIn, pos);
		}

		super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
	}

	protected static void turnToDirt(World world, BlockPos worldIn) {
		world.setBlockState(worldIn, BlockRegistry.Registry.AURORIANDIRT.getBlock().getDefaultState());
		AxisAlignedBB axisalignedbb = field_194405_c.offset(worldIn);

		for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(null, axisalignedbb)) {
			double d0 = Math.min(axisalignedbb.maxY - axisalignedbb.minY, axisalignedbb.maxY - entity.getEntityBoundingBox().minY);
			entity.setPositionAndUpdate(entity.posX, entity.posY + d0 + 0.001D, entity.posZ);
		}
	}

	private boolean hasCrops(World worldIn, BlockPos pos) {
		Block block = worldIn.getBlockState(pos.up()).getBlock();
		return block instanceof net.minecraftforge.common.IPlantable && this.canSustainPlant(worldIn.getBlockState(pos), worldIn, pos, net.minecraft.util.EnumFacing.UP, (net.minecraftforge.common.IPlantable) block);
	}

	private boolean hasWater(World worldIn, BlockPos pos) {
		for (BlockPos.MutableBlockPos blockpos$mutableblockpos : BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4))) {
			if (worldIn.getBlockState(blockpos$mutableblockpos).getMaterial() == Material.WATER) {
				return true;
			}
		}

		return net.minecraftforge.common.FarmlandWaterManager.hasBlockWaterTicket(worldIn, pos);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos);

		if (worldIn.getBlockState(pos.up()).getMaterial().isSolid()) {
			turnToDirt(worldIn, pos);
		}
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);

		if (worldIn.getBlockState(pos.up()).getMaterial().isSolid()) {
			turnToDirt(worldIn, pos);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		switch (side) {
			case UP:
				return true;
			case NORTH:
			case SOUTH:
			case WEST:
			case EAST:
				IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
				Block block = iblockstate.getBlock();
				return !iblockstate.isOpaqueCube() && block != BlockRegistry.Registry.AURORIANFARMTILE.getBlock() && block != Blocks.GRASS_PATH;
			default:
				return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return BlockRegistry.Registry.AURORIANDIRT.getBlock().getItemDropped(BlockRegistry.Registry.AURORIANDIRT.getBlock().getDefaultState(), rand, fortune);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(MOISTURE, Integer.valueOf(meta & 7));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(MOISTURE).intValue();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, MOISTURE);
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
	}

}
