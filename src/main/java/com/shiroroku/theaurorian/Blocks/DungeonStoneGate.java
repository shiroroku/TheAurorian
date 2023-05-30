package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class DungeonStoneGate extends Block {

	public static final String BLOCKNAME_RUNESTONE = "runestonegate";
	public static final String BLOCKNAME_RUNESTONELOOT = "runestonelootgate";
	public static final String BLOCKNAME_MOONTEMPLE = "moontemplegate";
	public static final String BLOCKNAME_MOONTEMPLECELL = "moontemplecellgate";
	public static final String BLOCKNAME_DARK = "darkstonegate";

	private DungeonStoneGateKeyhole keyholeBlock = null;

	public DungeonStoneGate(String blockname) {
		super(Material.ROCK);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setBlockUnbreakable();
		this.setResistance(6000000.0F);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(AurorianMod.MODID + "." + blockname);
		this.setRegistryName(blockname);
	}

	public DungeonStoneGate(String blockname, String keyholeblockname) {
		this(blockname);
		this.setKeyhole(new DungeonStoneGateKeyhole(keyholeblockname));
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return this.isInKeyholeRange(worldIn, pos);
	}

	public void setKeyhole(DungeonStoneGateKeyhole keyholename) {
		this.keyholeBlock = keyholename;
	}

	public DungeonStoneGateKeyhole getKeyhole() {
		return this.keyholeBlock;
	}

	public boolean isKeyhole(Block bcheck) {
		return getKeyhole().getRegistryName().equals(bcheck.getRegistryName());
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format("string.theaurorian.tooltip.dungeonstonegate1") + " " + getKeyhole().getMaxGateDistance() + " " + I18n.format("string.theaurorian.tooltip.dungeonstonegate2"));
		}
	}

	public boolean isInKeyholeRange(World worldIn, BlockPos pos) {
		int maxBlocksFromKeyhole = getKeyhole().getMaxGateDistance();
		for (int x = -maxBlocksFromKeyhole; x <= maxBlocksFromKeyhole; x++) {
			for (int y = -maxBlocksFromKeyhole; y <= maxBlocksFromKeyhole; y++) {
				IBlockState blk = worldIn.getBlockState(new BlockPos(pos.getX() - x, pos.getY() - y, pos.getZ()));
				if (this.isKeyhole(blk.getBlock())) {
					return true;
				}
			}
		}
		for (int z = -maxBlocksFromKeyhole; z <= maxBlocksFromKeyhole; z++) {
			for (int y = -maxBlocksFromKeyhole; y <= maxBlocksFromKeyhole; y++) {
				IBlockState blk = worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY() - y, pos.getZ() - z));
				if (this.isKeyhole(blk.getBlock())) {
					return true;
				}
			}
		}
		return false;
	}

}
