package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class CropsBlock extends BlockCrops {

	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
	private static final AxisAlignedBB[] AABB = new AxisAlignedBB[] {
				new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D),
				new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D),
				new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D),
				new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D),
				new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D),
				new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D),
				new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D),
				new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D) };

	public static final String BLOCKNAME_SILKBERRY = "silkberrycrop";
	public static final String BLOCKNAME_LAVENDER = "lavendercrop";

	public CropsBlock(String blockname) {
		super();
		this.setUnlocalizedName(AurorianMod.MODID + "." + blockname);
		this.setRegistryName(blockname);
	}

	@Override
	public int getMaxAge() {
		return 7;
	}

	@Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		IBlockState soil = worldIn.getBlockState(pos.down());
		return worldIn.canSeeSky(pos) && (soil.getBlock() == BlockRegistry.Registry.AURORIANFARMTILE.getBlock() || soil.getBlock() == Blocks.FARMLAND);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 1)) {
			return;
		}

		int i = this.getAge(state);

		if (i < this.getMaxAge()) {
			float f = getGrowthChance(this, worldIn, pos);

			if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int) (25.0F / f) + 1) == 0)) {
				worldIn.setBlockState(pos, this.withAge(i + 1), 2);
				net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
			}
		}
	}

	@Override
	protected Item getSeed() {
		if (this.getRegistryName().toString().contains(AurorianMod.MODID + ":" + BLOCKNAME_SILKBERRY)) {
			return ItemRegistry.Registry.SILKBERRY.getItem();
		} else if (this.getRegistryName().toString().contains(AurorianMod.MODID + ":" + BLOCKNAME_LAVENDER)) {
			return ItemRegistry.Registry.SEEDSLAVENDER.getItem();
		} else {
			return ItemRegistry.Registry.SILKBERRY.getItem();
		}
	}

	@Override
	protected Item getCrop() {
		if (this.getRegistryName().toString().contains(AurorianMod.MODID + ":" + BLOCKNAME_SILKBERRY)) {
			return ItemRegistry.Registry.SILKBERRY.getItem();
		} else if (this.getRegistryName().toString().contains(AurorianMod.MODID + ":" + BLOCKNAME_LAVENDER)) {
			return ItemRegistry.Registry.LAVENDER.getItem();
		} else {
			return ItemRegistry.Registry.SILKBERRY.getItem();
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB[state.getValue(this.getAgeProperty()).intValue()];
	}

	@Override
	protected boolean canSustainBush(IBlockState state) {
		return state.getBlock() == BlockRegistry.Registry.AURORIANFARMTILE.getBlock() || state.getBlock() == Blocks.FARMLAND;
	}

}
