package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class MoonTorch extends BlockTorch {

	public static final String BLOCKNAME = "moontorch";

	public MoonTorch() {
		super();
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setHardness(0.0F);
		this.setRegistryName(BLOCKNAME);
		this.setSoundType(SoundType.WOOD);
		this.setLightLevel(0.9375F);
		this.setUnlocalizedName(AurorianMod.MODID + "." + BLOCKNAME);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.YELLOW + I18n.format("string.theaurorian.tooltip." + BLOCKNAME) + TextFormatting.RESET);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		EnumFacing enumfacing = stateIn.getValue(FACING);
		double d0 = pos.getX() + 0.5D;
		double d1 = pos.getY() + 0.7D;
		double d2 = pos.getZ() + 0.5D;

		if (enumfacing.getAxis().isHorizontal()) {
			EnumFacing enumfacing1 = enumfacing.getOpposite();
			worldIn.spawnParticle(EnumParticleTypes.CLOUD, d0 + 0.27D * enumfacing1.getFrontOffsetX(), d1 + 0.22D, d2 + 0.27D * enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D);
			worldIn.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, d0 + 0.27D * enumfacing1.getFrontOffsetX(), d1 + 0.22D, d2 + 0.27D * enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D);
		} else {
			worldIn.spawnParticle(EnumParticleTypes.CLOUD, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			worldIn.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}
}
