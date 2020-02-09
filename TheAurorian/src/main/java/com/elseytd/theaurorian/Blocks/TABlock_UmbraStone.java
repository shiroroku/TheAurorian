package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TABlock_UmbraStone extends Block {
	public static final String BLOCKNAME_UMBRASTONE = "umbrastone";
	public static final String BLOCKNAME_UMBRASTONECRACKED = "umbrastonecracked";
	public static final String BLOCKNAME_UMBRASTONEROOFTILES = "umbrastonerooftiles";

	public TABlock_UmbraStone(String name) {
		super(Material.ROCK);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setHardness(5.0F);
		this.setRegistryName(name);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(TAMod.MODID + "." + name);
	}
}
