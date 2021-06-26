package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.Registry.BlockRegistry;
import com.elseytd.theaurorian.AurorianMod;
import com.elseytd.theaurorian.World.AurorianBiomeDecorator.AurorianStonesPredicate.IAurorianStoneType;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class AurorianStone extends Block implements IAurorianStoneType {

	public static final String BLOCKNAME = "aurorianstone";

	public AurorianStone() {
		super(Material.ROCK);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setHardness(2.0F);
		this.setHarvestLevel("pickaxe", 0);
		this.setRegistryName(BLOCKNAME);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(AurorianMod.MODID + "." + BLOCKNAME);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(BlockRegistry.Registry.AURORIANCOBBLESTONE.getBlock());
	}

}
