package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class WeepingWillowPlanks extends Block {

	public static final String BLOCKNAME = "weepingwillowplanks";

	public WeepingWillowPlanks() {
		super(Material.WOOD);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setHardness(2.0F);
		this.setRegistryName(BLOCKNAME);
		this.setSoundType(SoundType.WOOD);
		this.setUnlocalizedName(AurorianMod.MODID + "." + BLOCKNAME);
	}

}