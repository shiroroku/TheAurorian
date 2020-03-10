package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TABlock_MushroomCrystal extends Block {

	public static final String BLOCKNAME = "mushroomcrystal";

	public TABlock_MushroomCrystal() {
		super(Material.GLASS);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(TAMod.MODID + "." + BLOCKNAME);
		this.setRegistryName(BLOCKNAME);
		this.setLightLevel(1F);
	}
}
