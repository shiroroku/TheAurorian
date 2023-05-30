package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class AmuletItem extends Item {

	public static final String ITEMNAME_KEEPERAMULET = "keeperamulet";
	public static final String ITEMNAME_DARKAMULET = "darkamulet";
	
	public enum Amulets {
		KEEPERAMULET(ITEMNAME_KEEPERAMULET, "string.theaurorian.tooltip.amuletkeeper"),
		DARKAMULET(ITEMNAME_DARKAMULET, "string.theaurorian.tooltip.amuletdark");
		
		private final String ITEMNAME;
		private final String INFO;
		
		Amulets(String itemname, String info){
			this.ITEMNAME = itemname;
			this.INFO = info;
		}
		
		public String getName() {
			return ITEMNAME;
		}
		
		public String getInfo() {
			return INFO;
		}
	}
	
	private final Amulets itemAmulet;
	
	public AmuletItem(Amulets amulet) {
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(amulet.getName());
		this.setUnlocalizedName(AurorianMod.MODID + "." + amulet.getName());
		this.setMaxStackSize(1);
		this.itemAmulet = amulet;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public net.minecraftforge.common.IRarity getForgeRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format(this.itemAmulet.getInfo()));
		}
	}
}
