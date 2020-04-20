package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class TABlock_Stairs extends BlockStairs {

	public static final String BLOCKNAME_SILENTWOOD = "silentwoodstairs";
	public static final String BLOCKNAME_AURORIANCOBBLESTONE = "auroriancobblestonestairs";
	public static final String BLOCKNAME_RUNESTONE = "runestonestairs";
	public static final String BLOCKNAME_MOONTEMPLE = "moontemplestairs";
	public static final String BLOCKNAME_AURORIANSTONE = "aurorianstonestairs";
	public static final String BLOCKNAME_DARK = "darkstonestairs";
	public static final String BLOCKNAME_UMBRASTONEROOFTILES = "umbrastoneroofstairs";
	public static final String BLOCKNAME_WEEPINGWILLOWPLANKS = "weepingwillowplanksstairs";
	public static final String BLOCKNAME_PERIDOTITESMOOTH = "aurorianperidotitesmoothstairs";

	public TABlock_Stairs(Block blockbase, String name) {
		super(blockbase.getDefaultState());
		this.setLightOpacity(0);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setUnlocalizedName(TAMod.MODID + "." + name);
		this.setRegistryName(name);
		this.setHarvestLevel(blockbase.getHarvestTool(blockbase.getDefaultState()), blockbase.getHarvestLevel(blockbase.getDefaultState()));
	}
}
