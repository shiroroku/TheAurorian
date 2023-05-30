package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class SilentwoodPickaxe extends ItemPickaxe {

	public static final String ITEMNAME = "silentwoodpickaxe";

	public SilentwoodPickaxe() {
		super(ItemRegistry.Materials.SILENTWOOD);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		if (!worldIn.isRemote && (double) state.getBlockHardness(worldIn, pos) != 0.0D) {
			stack.damageItem(1, entityLiving);
		}

		NBTTagCompound nbt = checkNbt(stack);
		double damagedpercent = (float) getDamage(stack) / (float) this.getMaxDamage(stack);
		if (damagedpercent >= 0.0D && damagedpercent < 0.25D) {
			nbt.setInteger("currentharvestlevel", 0);
		} else if (damagedpercent >= 0.25D && damagedpercent < 0.5D) {
			nbt.setInteger("currentharvestlevel", 1);
		} else if (damagedpercent >= 0.5D && damagedpercent < 0.75D) {
			nbt.setInteger("currentharvestlevel", 2);
		} else if (damagedpercent >= 0.75D && damagedpercent < 1D) {
			nbt.setInteger("currentharvestlevel", 3);
		}
		stack.setTagCompound(nbt);
		return true;
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass, @javax.annotation.Nullable net.minecraft.entity.player.EntityPlayer player, @javax.annotation.Nullable IBlockState blockState) {
		checkNbt(stack);
		return stack.getTagCompound().getInteger("currentharvestlevel");
	}

	/**
	 * Makes sure we have our harvest level saved.
	 */
	private NBTTagCompound checkNbt(ItemStack stack) {
		NBTTagCompound nbt;
		if (stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
		} else {
			nbt = new NBTTagCompound();
		}
		if (!nbt.hasKey("currentharvestlevel")) {
			nbt.setInteger("currentharvestlevel", 0);
			stack.setTagCompound(nbt);
		}
		return nbt;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		checkNbt(stack);
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.AQUA + I18n.format("string.theaurorian.tooltip.silentwoodpickaxe1") + " [" + stack.getTagCompound().getInteger("currentharvestlevel") + "]" + TextFormatting.RESET);
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format("string.theaurorian.tooltip.silentwoodpickaxe2"));
		}
	}
}
