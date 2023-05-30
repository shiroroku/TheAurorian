package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class IndigoMushroomCrystal extends Block {

	public static final String BLOCKNAME = "mushroomcrystal";

	public IndigoMushroomCrystal() {
		super(Material.GLASS);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(AurorianMod.MODID + "." + BLOCKNAME);
		this.setRegistryName(BLOCKNAME);
		this.setLightLevel(1F);
	}
}
