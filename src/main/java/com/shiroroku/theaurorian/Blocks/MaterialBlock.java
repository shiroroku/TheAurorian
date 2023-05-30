package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MaterialBlock extends Block {
	public static final String BLOCKNAME_CERULEAN = "ceruleanblock";
	public static final String BLOCKNAME_MOONSTONE = "moonstoneblock";
	public static final String BLOCKNAME_COAL = "auroriancoalblock";
	public static final String BLOCKNAME_AURORIANSTEEL = "auroriansteelblock";

	public MaterialBlock(String name) {
		super(Material.IRON);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setHardness(5.0F);
		this.setRegistryName(name);
		this.setSoundType(SoundType.METAL);
		this.setUnlocalizedName(AurorianMod.MODID + "." + name);
	}

}
