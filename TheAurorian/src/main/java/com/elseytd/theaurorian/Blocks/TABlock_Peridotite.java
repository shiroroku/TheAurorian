package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.World.TABiomeDecorator.AurorianStonesPredicate.IAurorianStoneType;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TABlock_Peridotite extends Block implements IAurorianStoneType {

	public static final String BLOCKNAME = "aurorianperidotite";
	public static final String BLOCKNAME_SMOOTH = "aurorianperidotitesmooth";

	public TABlock_Peridotite(String blockname) {
		super(Material.ROCK);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setHardness(5.0F);
		this.setHarvestLevel("pickaxe", 0);
		this.setRegistryName(blockname);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(TAMod.MODID + "." + blockname);
	}
}
