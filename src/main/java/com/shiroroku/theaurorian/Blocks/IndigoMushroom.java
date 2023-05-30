package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class IndigoMushroom extends Block {
	public static final String BLOCKNAME = "mushroom";

	public IndigoMushroom() {
		super(Material.WOOD);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setHardness(1.0F);
		this.setRegistryName(BLOCKNAME);
		this.setSoundType(SoundType.SLIME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + BLOCKNAME);
	}

	@Override
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		super.onFallenUpon(worldIn, pos, entityIn, fallDistance * 0.25F);
	}

	@Override
	public void onLanded(World worldIn, Entity entityIn) {
		if (entityIn.isSneaking()) {
			super.onLanded(worldIn, entityIn);
		} else if (entityIn.motionY < 0.0D) {
			entityIn.motionY = -entityIn.motionY * 0.75D;

			if (!(entityIn instanceof EntityLivingBase)) {
				entityIn.motionY *= 0.8D;
			}
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return BlockRegistry.Registry.MUSHROOMSMALL.getBlock().getItemDropped(BlockRegistry.Registry.MUSHROOMSMALL.getBlock().getDefaultState(), rand, fortune);
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format("string.theaurorian.tooltip.mushroom"));
		}
	}

}
