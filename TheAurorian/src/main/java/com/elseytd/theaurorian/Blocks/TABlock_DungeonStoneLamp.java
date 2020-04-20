package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TABlock_DungeonStoneLamp extends Block {

	public static final String BLOCKNAME_RUNESTONE = "runestonelamp";
	public static final String BLOCKNAME_MOONTEMPLE = "moontemplelamp";
	public static final String BLOCKNAME_DARK = "darkstonelamp";

	public TABlock_DungeonStoneLamp(String blockname) {
		super(Material.ROCK);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setBlockUnbreakable();
		this.setResistance(6000000.0F);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(TAMod.MODID + "." + blockname);
		this.setRegistryName(blockname);
		this.setLightLevel(1F);
	}
}
