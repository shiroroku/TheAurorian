package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class DungeonStone extends Block {

	public static final String BLOCKNAME_RUNESTONE = "runestone";
	public static final String BLOCKNAME_MOONTEMPLE = "moontemplebricks";
	public static final String BLOCKNAME_DARK = "darkstonebricks";
	public static final String BLOCKNAME_DARK_FANCY = "darkstonefancy";
	public static final String BLOCKNAME_DARK_LAYERS = "darkstonelayers";

	public DungeonStone(String blockname) {
		super(Material.ROCK);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setBlockUnbreakable();
		this.setResistance(6000000.0F);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(AurorianMod.MODID + "." + blockname);
		this.setRegistryName(blockname);
	}
}
