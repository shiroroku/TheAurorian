package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class CrystallineShieldItem extends ShieldItem {

	public static final String ITEMNAME = "crystallineshield";

	public CrystallineShieldItem() {
		super();
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
		this.setMaxDamage(512);
	}

	@Override
	public void onBlockingDamage(ItemStack shield, EntityPlayer player) {
		shield.damageItem(1, player);
		if (player.getHeldItemMainhand().getItemDamage() < player.getHeldItemMainhand().getMaxDamage() && player.getHeldItemMainhand().isItemStackDamageable()) {
			player.getHeldItemMainhand().setItemDamage(player.getHeldItemMainhand().getItemDamage() - 1);
		}
		player.getCooldownTracker().setCooldown(shield.getItem(), 20);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 100;
	}

	@Override
	public net.minecraftforge.common.IRarity getForgeRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format("string.theaurorian.tooltip.crystallineshield"));
		}
	}
}
