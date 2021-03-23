package com.elseytd.theaurorian.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_AurorianStone_Axe extends ItemAxe {

	public static final String ITEMNAME = "aurorianstoneaxe";

	public TAItem_AurorianStone_Axe() {
		super(TAItems.Materials.AURORIANSTONE, 8.0F, -3.2F);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

	/**
	 * Destroys all silentwood logs above until durability or logs runs out.
	 */
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		if (!worldIn.isRemote && state.getBlockHardness(worldIn, pos) != 0.0D) {
			stack.damageItem(1, entityLiving);
			int y = 1;
			while (worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY() + y, pos.getZ())) == TABlocks.Registry.SILENTWOODLOG.getBlock().getDefaultState() && stack.getItemDamage() <= (stack.getMaxDamage() - 3)) {
				worldIn.destroyBlock(new BlockPos(pos.getX(), pos.getY() + y, pos.getZ()), true);
				stack.damageItem(3, entityLiving);
				y++;
			}
		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format("string.theaurorian.tooltip.aurorianstoneaxe"));
		}
	}
}
