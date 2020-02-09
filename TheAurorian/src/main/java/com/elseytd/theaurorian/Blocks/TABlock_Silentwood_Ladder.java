package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.BlockLadder;
import net.minecraft.block.SoundType;
import net.minecraft.util.EnumFacing;

public class TABlock_Silentwood_Ladder extends BlockLadder {

	public static final String BLOCKNAME = "silentwoodladder";

	public TABlock_Silentwood_Ladder() {
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setHardness(2.0F);
		this.setRegistryName(BLOCKNAME);
		this.setSoundType(SoundType.WOOD);
		this.setUnlocalizedName(TAMod.MODID + "." + BLOCKNAME);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}

}
