package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAGuis;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TABlock_Silentwood_Workbench extends Block {

	public static final String BLOCKNAME = "silentwoodcraftingtable";

	public TABlock_Silentwood_Workbench() {
		super(Material.WOOD);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setHardness(2.5F);
		this.setRegistryName(BLOCKNAME);
		this.setSoundType(SoundType.WOOD);
		this.setUnlocalizedName(TAMod.MODID + "." + BLOCKNAME);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(TAMod.INSTANCE, TAGuis.SILENTWOODCRAFTINGTABLE, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

}
