package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TABlock_DungeonStone extends Block {

	public static final String BLOCKNAME_RUNESTONE = "runestone";
	public static final String BLOCKNAME_MOONTEMPLE = "moontemplebricks";
	public static final String BLOCKNAME_DARK = "darkstonebricks";
	public static final String BLOCKNAME_DARK_FANCY = "darkstonefancy";
	public static final String BLOCKNAME_DARK_LAYERS = "darkstonelayers";

	public TABlock_DungeonStone(String blockname) {
		super(Material.ROCK);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setBlockUnbreakable();
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(TAMod.MODID + "." + blockname);
		this.setRegistryName(blockname);
	}
}
