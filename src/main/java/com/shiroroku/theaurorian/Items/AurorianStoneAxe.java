package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
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

import javax.annotation.Nullable;
import java.util.List;

public class AurorianStoneAxe extends ItemAxe {

	public static final String ITEMNAME = "aurorianstoneaxe";

	public AurorianStoneAxe() {
		super(ItemRegistry.Materials.AURORIANSTONE, 8.0F, -3.2F);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
	}

	/**
	 * Destroys all silentwood logs above until durability or logs runs out.
	 */
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		if (!worldIn.isRemote && state.getBlockHardness(worldIn, pos) != 0.0D) {
			stack.damageItem(1, entityLiving);
			int y = 1;
			while (worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY() + y, pos.getZ())) == BlockRegistry.Registry.SILENTWOODLOG.getBlock().getDefaultState() && stack.getItemDamage() <= (stack.getMaxDamage() - 3)) {
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
