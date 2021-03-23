package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TABlock_Moonsand extends BlockFalling {

	public static final String BLOCKNAME = "moonsand";

	public TABlock_Moonsand() {
		super(Material.SAND);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setHardness(0.5F);
		this.setHarvestLevel("shovel", 0);
		this.setSoundType(SoundType.SAND);
		this.setUnlocalizedName(TAMod.MODID + "." + BLOCKNAME);
		this.setRegistryName(BLOCKNAME);
	}
}
