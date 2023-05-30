package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.SoundType;
import net.minecraft.util.EnumFacing;

public class SilentwoodLadder extends BlockLadder {

	public static final String BLOCKNAME = "silentwoodladder";

	public SilentwoodLadder() {
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setHardness(2.0F);
		this.setRegistryName(BLOCKNAME);
		this.setSoundType(SoundType.WOOD);
		this.setUnlocalizedName(AurorianMod.MODID + "." + BLOCKNAME);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}

}
