package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;

public class SilentwoodTorch extends BlockTorch {

	public static final String BLOCKNAME = "silentwoodtorch";

	public SilentwoodTorch() {
		super();
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setHardness(0.0F);
		this.setRegistryName(BLOCKNAME);
		this.setSoundType(SoundType.WOOD);
		this.setLightLevel(0.9375F);
		this.setUnlocalizedName(AurorianMod.MODID + "." + BLOCKNAME);
	}

}
