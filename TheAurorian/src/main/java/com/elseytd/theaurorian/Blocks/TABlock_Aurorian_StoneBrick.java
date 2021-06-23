package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TABlock_Aurorian_StoneBrick extends Block {

	public static final String BLOCKNAME = "aurorianstonebrick";

	public TABlock_Aurorian_StoneBrick() {
		super(Material.ROCK);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setHardness(2.0F);
		this.setHarvestLevel("pickaxe", 0);
		this.setRegistryName(BLOCKNAME);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(TAMod.MODID + "." + BLOCKNAME);
	}
}
