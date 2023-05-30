package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.Entities.Boss.KeeperEntity;
import com.shiroroku.theaurorian.Entities.Boss.MoonQueenEntity;
import com.shiroroku.theaurorian.Entities.Boss.SpiderEntity;
import com.shiroroku.theaurorian.AurorianMod;
import com.shiroroku.theaurorian.TileEntities.BossSpawnerTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BossSpawner extends Block implements ITileEntityProvider {

	public static final String BLOCKNAME_KEEPER = "bossspawnerkeeper";
	public static final String BLOCKNAME_MOONQUEEN = "bossspawnermoonqueen";
	public static final String BLOCKNAME_SPIDER = "bossspawnerspider";

	public enum Bosses {
		KEEPER(BLOCKNAME_KEEPER, KeeperEntity.EntityName),
		MOONQUEEN(BLOCKNAME_MOONQUEEN, MoonQueenEntity.EntityName),
		SPIDER(BLOCKNAME_SPIDER, SpiderEntity.EntityName);

		private final String BLOCKNAME;
		private final String BOSS;

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

	private final Bosses blockBoss;

	public BossSpawner(Bosses boss) {
		super(Material.ROCK);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setBlockUnbreakable();
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(AurorianMod.MODID + "." + boss.getName());
		this.setRegistryName(boss.getName());
		this.blockBoss = boss;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		double rx = pos.getX() + worldIn.rand.nextFloat();
		double ry = pos.getY() + worldIn.rand.nextFloat();
		double rz = pos.getZ() + worldIn.rand.nextFloat();
		worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, rx, ry, rz, 0.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.FLAME, rx, ry, rz, 0.0D, 0.0D, 0.0D);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new BossSpawnerTileEntity(this.blockBoss.getBoss());
	}

}
