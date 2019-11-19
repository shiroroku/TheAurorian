package com.elseytd.theaurorian.Blocks;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TABlock_Plant_Crops extends BlockCrops {

	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
	private static final AxisAlignedBB[] AABB = new AxisAlignedBB[] { new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D) };

	public static final String BLOCKNAME_SILKBERRY = "silkberrycrop";
	public static final String BLOCKNAME_LAVENDER = "lavendercrop";

	public TABlock_Plant_Crops(String blockname) {
		super();
		this.setUnlocalizedName(TAMod.MODID + "." + blockname);
		this.setRegistryName(blockname);
	}

	@Override
	public int getMaxAge() {
		return 7;
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		IBlockState soil = worldIn.getBlockState(pos.down());
		return worldIn.canSeeSky(pos) && (soil.getBlock() == TABlocks.aurorianfarmtile || soil.getBlock() == Blocks.FARMLAND);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 1))
			return;

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
		if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_SILKBERRY)) {
			return TAItems.silkberry;
		} else if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_LAVENDER)) {
			return TAItems.lavenderseeds;
		} else {
			return TAItems.silkberry;
		}
	}

	@Override
	protected Item getCrop() {
		if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_SILKBERRY)) {
			return TAItems.silkberry;
		} else if (this.getRegistryName().toString().contains(TAMod.MODID + ":" + BLOCKNAME_LAVENDER)) {
			return TAItems.lavender;
		} else {
			return TAItems.silkberry;
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB[((Integer) state.getValue(this.getAgeProperty())).intValue()];
	}

	@Override
	protected boolean canSustainBush(IBlockState state) {
		return state.getBlock() == TABlocks.aurorianfarmtile || state.getBlock() == Blocks.FARMLAND;
	}

}
