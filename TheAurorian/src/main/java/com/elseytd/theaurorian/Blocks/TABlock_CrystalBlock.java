package com.elseytd.theaurorian.Blocks;

import java.util.Random;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAItems.ISpecialModel;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TileEntities.CrystalBlock_TileEntity;
import com.elseytd.theaurorian.TileEntities.CrystalBlock_TileEntitySpecialRenderer;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TABlock_CrystalBlock extends Block implements ITileEntityProvider, ISpecialModel {

	public static final String BLOCKNAME = "crystalblock";
	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.4D, 0.0D, 0.4D, 0.6D, 1.0D, 0.6D);

	public TABlock_CrystalBlock() {
		super(Material.ROCK);
		this.setHardness(2.0F);
		this.setHarvestLevel("pickaxe", 0);
		this.setRegistryName(BLOCKNAME);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(TAMod.MODID + "." + BLOCKNAME);
		this.setLightLevel(1F);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return TAItems.crystal;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new CrystalBlock_TileEntity();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
		ClientRegistry.bindTileEntitySpecialRenderer(CrystalBlock_TileEntity.class, new CrystalBlock_TileEntitySpecialRenderer());
	}
}
