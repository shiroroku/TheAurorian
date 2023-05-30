package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

public class AurorianDirt extends Block {

	public static final String BLOCKNAME = "auroriandirt";

	public AurorianDirt() {
		super(Material.GRASS);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setHardness(0.5F);
		this.setHarvestLevel("shovel", 0);
		this.setSoundType(SoundType.GROUND);
		this.setUnlocalizedName(AurorianMod.MODID + "." + BLOCKNAME);
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
