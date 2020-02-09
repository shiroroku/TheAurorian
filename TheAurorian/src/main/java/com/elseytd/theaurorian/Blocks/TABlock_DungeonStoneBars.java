package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;

public class TABlock_DungeonStoneBars extends BlockPane {

	public static final String BLOCKNAME_RUNESTONE = "runestonebars";
	public static final String BLOCKNAME_MOONTEMPLE = "moontemplebars";

	public TABlock_DungeonStoneBars(String blockname) {
		super(Material.IRON, false);
		this.setBlockUnbreakable();
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(blockname);
		this.setUnlocalizedName(TAMod.MODID + "." + blockname);
	}
}
