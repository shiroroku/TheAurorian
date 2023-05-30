package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AurorianiteAxe extends ItemAxe {

	public static final String ITEMNAME = "aurorianiteaxe";

	public AurorianiteAxe() {
		super(ItemRegistry.Materials.AURORIANITE, 12.0F, -3.5F);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		if (!worldIn.isRemote && (double) state.getBlockHardness(worldIn, pos) != 0.0D) {
			if (state.getBlock().isWood(worldIn, pos)) {
				chopTree(worldIn, pos, stack, entityLiving);
			} else {
				super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
			}
		}
		return true;
	}

	@Override
	public net.minecraftforge.common.IRarity getForgeRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}
	
	private void chopTree(World worldIn, BlockPos pos, ItemStack stack, EntityLivingBase entityLiving) {

		boolean isDone = false;
		int maxLogs = AurorianConfig.Config_AurorianiteAxeMaxChopSize;
		int logCount = 0;
		List<BlockPos> notSearchedWood = new ArrayList<BlockPos>();
		List<BlockPos> searchedWood = new ArrayList<BlockPos>();

		//Set our first block to be the log we mined
		BlockPos currentPos = pos;

		while (!isDone) {

			//for loops for getting a 3x3 on the same level of the block and 3x3 a level above
			for (int x = -1; x <= 1; x++) {
				for (int y = 0; y <= 1; y++) {
					for (int z = -1; z <= 1; z++) {
						BlockPos p = currentPos.add(x, y, z);
						//If any block we find is wood and is not already searched or in our list, we add it.
						if (worldIn.getBlockState(p).getBlock().isWood(worldIn, p)) {
							if (!notSearchedWood.contains(p) && !searchedWood.contains(p)) {
								notSearchedWood.add(p);
								logCount++;
							}
						}
					}
				}
			}

			//Move the block we just searched from our unsearched list to a list for later breaking
			searchedWood.add(currentPos);
			notSearchedWood.remove(currentPos);

			//If no more blocks are found we are done, else we set our next block from the first in the list unless we reach the max log count
			if (notSearchedWood.isEmpty() || logCount >= maxLogs) {
				isDone = true;
			} else {
				currentPos = notSearchedWood.get(0);
			}
		}

		for (BlockPos p : searchedWood) {
			worldIn.destroyBlock(p, true);
			stack.damageItem(1, entityLiving);
		}
		System.out.println(logCount);

	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format("string.theaurorian.tooltip.aurorianiteaxe"));
		}
	}
}
