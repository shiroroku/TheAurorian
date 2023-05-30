package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;

public class DungeonStoneBars extends BlockPane {

	public static final String BLOCKNAME_RUNESTONE = "runestonebars";
	public static final String BLOCKNAME_MOONTEMPLE = "moontemplebars";

	public DungeonStoneBars(String blockname) {
		super(Material.IRON, true);
		this.setBlockUnbreakable();
		this.setResistance(6000000.0F);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(blockname);
		this.setUnlocalizedName(AurorianMod.MODID + "." + blockname);
	}
}
