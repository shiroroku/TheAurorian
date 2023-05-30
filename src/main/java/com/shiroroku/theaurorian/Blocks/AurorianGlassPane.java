package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.BlockPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class AurorianGlassPane extends BlockPane {

	public static final String BLOCKNAME_MOONGLASS = "moonglasspane";
	public static final String BLOCKNAME_AURORIAN = "aurorianglasspane";

	public AurorianGlassPane(String name) {
		super(Material.GLASS, false);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setHardness(0.3F);
		this.setSoundType(SoundType.GLASS);
		this.setRegistryName(name);
		this.setUnlocalizedName(AurorianMod.MODID + "." + name);

	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}
}
