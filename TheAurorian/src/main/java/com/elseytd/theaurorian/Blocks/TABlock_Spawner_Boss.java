package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.Entities.Keeper.TAEntity_RunestoneDungeonKeeper;
import com.elseytd.theaurorian.Entities.MoonQueen.TAEntity_MoonQueen;
import com.elseytd.theaurorian.TileEntities.TATileEntity_Spawner_Boss;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TABlock_Spawner_Boss extends Block implements ITileEntityProvider {

	public static final String BLOCKNAME_KEEPER = "bossspawnerkeeper";
	public static final String BLOCKNAME_MOONQUEEN = "bossspawnermoonqueen";

	public enum Bosses {
		KEEPER(BLOCKNAME_KEEPER, TAEntity_RunestoneDungeonKeeper.EntityName),
		MOONQUEEN(BLOCKNAME_MOONQUEEN, TAEntity_MoonQueen.EntityName);

		private String BLOCKNAME;
		private String BOSS;

		Bosses(String blockname, String boss) {
			this.BLOCKNAME = blockname;
			this.BOSS = boss;
		}

		public String getName() {
			return BLOCKNAME;
		}

		public String getBoss() {
			return BOSS;
		}

	}

	private Bosses blockBoss;

	public TABlock_Spawner_Boss(Bosses boss) {
		super(Material.ROCK);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setBlockUnbreakable();
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(TAMod.MODID + "." + boss.getName());
		this.setRegistryName(boss.getName());
		this.blockBoss = boss;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	//
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		TATileEntity_Spawner_Boss te = new TATileEntity_Spawner_Boss();
		te.bossEntity = this.blockBoss.getBoss();
		return te;
	}

}
