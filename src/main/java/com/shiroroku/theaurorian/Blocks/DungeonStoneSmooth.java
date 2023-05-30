package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class DungeonStoneSmooth extends Block {

	public static final String BLOCKNAME_RUNESTONE = "runestonesmooth";
	public static final String BLOCKNAME_MOONTEMPLE = "moontemplebrickssmooth";

	public DungeonStoneSmooth(String blockname) {
		super(Material.ROCK);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setBlockUnbreakable();
		this.setResistance(6000000.0F);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(AurorianMod.MODID + "." + blockname);
		this.setRegistryName(blockname);
	}
}
