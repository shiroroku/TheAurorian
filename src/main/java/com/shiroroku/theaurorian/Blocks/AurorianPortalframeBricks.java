package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class AurorianPortalframeBricks extends Block {

	public static final String BLOCKNAME = "aurorianportalframebricks";

	public AurorianPortalframeBricks() {
		super(Material.ROCK);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setHardness(2.0F);
		this.setHarvestLevel("pickaxe", 0);
		this.setRegistryName(BLOCKNAME);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(AurorianMod.MODID + "." + BLOCKNAME);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format("string.theaurorian.tooltip.portalframebricks"));
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		ItemStack itemstack = playerIn.getHeldItem(hand);

		if (!itemstack.isEmpty()) {
			if (itemstack.getItem() == ItemRegistry.Registry.SILENTWOODSTICK.getItem() && AurorianConfig.Config_SticksMakeFire) {
				itemstack.shrink(1);
				if (playerIn.getHeldItemOffhand().getItem() == ItemRegistry.Registry.SILENTWOODSTICK.getItem()) {
					playerIn.getHeldItemOffhand().shrink(1);
				}
				((AurorianPortal) BlockRegistry.Registry.AURORIANPORTAL.getBlock()).trySpawnPortal(worldIn, pos.up());
				return true;
			} else {
				for (String i : AurorianConfig.Config_PortalLighter) {
					if (Item.getByNameOrId(i) == itemstack.getItem()) {
						if (itemstack.getItem().isDamageable()) {
							itemstack.damageItem(1, playerIn);
						} else {
							itemstack.shrink(1);
						}
						((AurorianPortal) BlockRegistry.Registry.AURORIANPORTAL.getBlock()).trySpawnPortal(worldIn, pos.up());
						return true;
					}
				}
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
}
