package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import com.shiroroku.theaurorian.AurorianUtil;
import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.Registry.ParticleRegistry;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class WeepingWillowLeaves extends BlockLeaves {

	public static final String BLOCKNAME = "weepingwillowleaves";

	public WeepingWillowLeaves() {
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(BLOCKNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + BLOCKNAME);
		this.setHardness(0.2F);
		this.setSoundType(SoundType.PLANT);
		this.setLightOpacity(1);
		this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (worldIn.isRemote && (worldIn.isAirBlock(pos.down()))) {
			if (AurorianUtil.randomChanceOf(0.01)) {
				double d0 = pos.getX() + rand.nextDouble();
				double d1 = pos.getY() - 0.2D;
				double d2 = pos.getZ() + rand.nextDouble();
				ParticleRegistry.spawnParticle(ParticleRegistry.Particles.WEEPINGWILLOWDRIP, d0, d1, d2, 0, 0, 0);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return Blocks.LEAVES.getBlockLayer();
	}

	@Override
	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
		if (worldIn.rand.nextInt(chance) == 0) {
			spawnAsEntity(worldIn, pos, new ItemStack(ItemRegistry.Registry.WEEPINGWILLOWSAP.getItem()));
		}
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return Blocks.LEAVES.isOpaqueCube(state);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		this.leavesFancy = !Blocks.LEAVES.isOpaqueCube(blockState);
		return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(DECAYABLE, Boolean.valueOf(false)).withProperty(CHECK_DECAY, Boolean.valueOf(false));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(BlockRegistry.Registry.WEEPINGWILLOWSAPLING.getBlock());
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return NonNullList.withSize(1, new ItemStack(this, 1));
	}

	@Override
	public EnumType getWoodType(int meta) {
		return null;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState();
	}

}
