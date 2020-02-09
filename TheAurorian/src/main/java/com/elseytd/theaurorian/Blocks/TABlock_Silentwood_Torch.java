package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;

public class TABlock_Silentwood_Torch extends BlockTorch {

	public static final String BLOCKNAME = "silentwoodtorch";

	public TABlock_Silentwood_Torch() {
		super();
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setHardness(0.0F);
		this.setRegistryName(BLOCKNAME);
		this.setSoundType(SoundType.WOOD);
		this.setLightLevel(0.9375F);
		this.setUnlocalizedName(TAMod.MODID + "." + BLOCKNAME);
	}

}
