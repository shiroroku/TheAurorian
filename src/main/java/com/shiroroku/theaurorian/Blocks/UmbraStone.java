package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class UmbraStone extends Block {
	public static final String BLOCKNAME_UMBRASTONE = "umbrastone";
	public static final String BLOCKNAME_UMBRASTONECRACKED = "umbrastonecracked";
	public static final String BLOCKNAME_UMBRASTONEROOFTILES = "umbrastonerooftiles";

	public UmbraStone(String name) {
		super(Material.ROCK);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setHardness(5.0F);
		this.setRegistryName(name);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(AurorianMod.MODID + "." + name);
	}
}
