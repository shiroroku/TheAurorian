package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

public class TABlock_Aurorian_Dirt extends Block {

	public static final String BLOCKNAME = "auroriandirt";

	public TABlock_Aurorian_Dirt() {
		super(Material.GRASS);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setHardness(0.5F);
		this.setHarvestLevel("shovel", 0);
		this.setSoundType(SoundType.GROUND);
		this.setUnlocalizedName(TAMod.MODID + "." + BLOCKNAME);
		this.setRegistryName(BLOCKNAME);
	}

	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable) {
		net.minecraftforge.common.EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

		if (direction != EnumFacing.UP) {
			return false;
		}

		return plantType == EnumPlantType.Plains;
	}

}
