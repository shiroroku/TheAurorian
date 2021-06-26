package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.AurorianMod;
import com.elseytd.theaurorian.World.AurorianBiomeDecorator.AurorianStonesPredicate.IAurorianStoneType;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class Peridotite extends Block implements IAurorianStoneType {

	public static final String BLOCKNAME = "aurorianperidotite";
	public static final String BLOCKNAME_SMOOTH = "aurorianperidotitesmooth";

	public Peridotite(String blockname) {
		super(Material.ROCK);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setHardness(5.0F);
		this.setHarvestLevel("pickaxe", 0);
		this.setRegistryName(blockname);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(AurorianMod.MODID + "." + blockname);
	}
}
