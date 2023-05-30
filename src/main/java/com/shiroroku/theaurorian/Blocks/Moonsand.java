package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class Moonsand extends BlockFalling {

	public static final String BLOCKNAME = "moonsand";

	public Moonsand() {
		super(Material.SAND);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setHardness(0.5F);
		this.setHarvestLevel("shovel", 0);
		this.setSoundType(SoundType.SAND);
		this.setUnlocalizedName(AurorianMod.MODID + "." + BLOCKNAME);
		this.setRegistryName(BLOCKNAME);
	}
}
