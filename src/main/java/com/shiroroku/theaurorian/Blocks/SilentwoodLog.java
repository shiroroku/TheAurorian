package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class SilentwoodLog extends BlockLog {

	public static final String BLOCKNAME = "silentwoodlog";

	public SilentwoodLog() {
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(BLOCKNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + BLOCKNAME);

		this.setDefaultState(blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
	}

	@Override
	public boolean canSustainLeaves(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean isWood(net.minecraft.world.IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.getDefaultState();

		switch (meta & 12) {
		case 0:
			state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
			break;
		case 4:
			state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
			break;
		case 8:
			state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
			break;
		default:
			state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
		}

		return state;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;
		switch (state.getValue(LOG_AXIS)) {
		case X:
			meta |= 4;
			break;
		case Z:
			meta |= 8;
			break;
		case NONE:
			meta |= 12;
		}
		return meta;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, LOG_AXIS);
	}
}
