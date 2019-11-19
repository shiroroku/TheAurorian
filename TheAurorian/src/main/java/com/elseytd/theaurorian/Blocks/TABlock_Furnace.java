package com.elseytd.theaurorian.Blocks;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAGuis;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TileEntities.Furnace.TATileEntity_Aurorian_Furnace;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TABlock_Furnace extends BlockContainer {

	public static final String BLOCKNAME = "aurorianfurnace";
	public static final String BLOCKNAME_LIT = "aurorianfurnacelit";
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	private final boolean isBurning;
	private static boolean keepInventory;

	private static final int chimneySpeedDiscount = TAConfig.Config_ChimneySpeedDiscount;
	public static final int maxChimneys = TAConfig.Config_MaximumChimneys;

	public TABlock_Furnace(boolean isBurning) {
		super(Material.ROCK);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.isBurning = isBurning;
		this.setHardness(3.5F);
		this.setSoundType(SoundType.STONE);
		if (isBurning) {
			this.setRegistryName(BLOCKNAME_LIT);
			this.setUnlocalizedName(TAMod.MODID + "." + BLOCKNAME_LIT);
			this.setLightLevel(0.875F);
		} else {
			this.setRegistryName(BLOCKNAME);
			this.setUnlocalizedName(TAMod.MODID + "." + BLOCKNAME);
			this.setCreativeTab(TAMod.CREATIVE_TAB);
		}
	}

	public int getChimneysDiscount(World worldIn, BlockPos pos) {
		int y = 1;
		int chimcount = 0;
		float discountfalloff = chimneySpeedDiscount;
		while (worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY() + y, pos.getZ())) == TABlocks.aurorianfurnacechimney.getDefaultState() && y <= TABlock_Furnace.maxChimneys) {
			y++;
			chimcount++;
			discountfalloff = discountfalloff * 0.95F;
		}
		return (int) (discountfalloff * chimcount);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(TABlocks.aurorianfurnace);
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		this.setDefaultFacing(worldIn, pos, state);
	}

	private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			IBlockState iblockstate = worldIn.getBlockState(pos.north());
			IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
			IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
			IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
			EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);
			if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()) {
				enumfacing = EnumFacing.SOUTH;
			} else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()) {
				enumfacing = EnumFacing.NORTH;
			} else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()) {
				enumfacing = EnumFacing.EAST;
			} else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()) {
				enumfacing = EnumFacing.WEST;
			}
			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("incomplete-switch")
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (this.isBurning) {
			EnumFacing enumfacing = (EnumFacing) stateIn.getValue(FACING);
			double d0 = (double) pos.getX() + 0.5D;
			double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
			double d2 = (double) pos.getZ() + 0.5D;
			double d4 = rand.nextDouble() * 0.6D - 0.3D;

			if (rand.nextDouble() < 0.1D) {
				worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
			}

			switch (enumfacing) {
			case WEST:
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
				break;
			case EAST:
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
				break;
			case NORTH:
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
				break;
			case SOUTH:
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if (Block.getBlockFromItem(playerIn.getHeldItem(hand).getItem()) == TABlocks.aurorianfurnacechimney) {
			for (int h = 0; h <= TABlock_Furnace.maxChimneys; h++) {
				if (worldIn.getBlockState(pos.up(h)).getBlock() == Blocks.AIR) {
					worldIn.setBlockState(new BlockPos(pos.up(h)), TABlocks.aurorianfurnacechimney.getDefaultState(), 11);
					if (!playerIn.isCreative()) {
						playerIn.getHeldItem(hand).shrink(1);
					}
					return true;
				}
			}
		}

		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof TATileEntity_Aurorian_Furnace) {
			TATileEntity_Aurorian_Furnace tile = (TATileEntity_Aurorian_Furnace) tileentity;
			if (this.getChimneysDiscount(worldIn, pos) - 200 <= 0) {
				tile.setSmeltTime(200 - this.getChimneysDiscount(worldIn, pos));
			} else {
				tile.setSmeltTime(1);
			}
		}
		if (worldIn.isRemote) {
			return true;
		} else {

			if (tileentity instanceof TATileEntity_Aurorian_Furnace) {
				playerIn.openGui(TAMod.INSTANCE, TAGuis.AURORIANFURNACE, worldIn, pos.getX(), pos.getY(), pos.getZ());
				playerIn.addStat(StatList.FURNACE_INTERACTION);
			}

			return true;
		}
	}

	public static void setState(boolean active, World worldIn, BlockPos pos) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		keepInventory = true;

		if (active) {
			worldIn.setBlockState(pos, TABlocks.aurorianfurnacelit.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
			worldIn.setBlockState(pos, TABlocks.aurorianfurnacelit.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
		} else {
			worldIn.setBlockState(pos, TABlocks.aurorianfurnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
			worldIn.setBlockState(pos, TABlocks.aurorianfurnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
		}

		keepInventory = false;

		if (tileentity != null) {
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TATileEntity_Aurorian_Furnace();
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (stack.hasDisplayName()) {
			if (tileentity instanceof TATileEntity_Aurorian_Furnace) {
				((TATileEntity_Aurorian_Furnace) tileentity).setCustomInventoryName(stack.getDisplayName());
			}
		}
		if (tileentity instanceof TATileEntity_Aurorian_Furnace) {
			TATileEntity_Aurorian_Furnace tile = (TATileEntity_Aurorian_Furnace) tileentity;
			if (this.getChimneysDiscount(worldIn, pos) - 200 <= 0) {
				tile.setSmeltTime(200 - this.getChimneysDiscount(worldIn, pos));
			} else {
				tile.setSmeltTime(1);
			}
		}
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!keepInventory) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof TATileEntity_Aurorian_Furnace) {
				InventoryHelper.dropInventoryItems(worldIn, pos, (TATileEntity_Aurorian_Furnace) tileentity);
				worldIn.updateComparatorOutputLevel(pos, this);
			}
		}
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(TABlocks.aurorianfurnace);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);

		if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

}
