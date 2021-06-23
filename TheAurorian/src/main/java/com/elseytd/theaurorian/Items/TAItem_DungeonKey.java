package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAMod;
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

public class TAItem_DungeonKey extends Item {

	public static final String ITEMNAME_RUNESTONE = "runestonekey";
	public static final String ITEMNAME_RUNESTONELOOT = "runestonelootkey";
	public static final String ITEMNAME_MOONTEMPLE = "moontemplekey";
	public static final String ITEMNAME_MOONTEMPLECELL = "moontemplecellkey";
	public static final String ITEMNAME_DARKSTONE = "darkstonekey";

	public enum Keys {
		RUNESTONE(ITEMNAME_RUNESTONE, "string.theaurorian.tooltip.keyrunestone"),
		RUNESTONELOOT(ITEMNAME_RUNESTONELOOT, "string.theaurorian.tooltip.keyrunestoneloot"),
		MOONTEMPLE(ITEMNAME_MOONTEMPLE, "string.theaurorian.tooltip.keymoontemple"),
		MOONTEMPLECELL(ITEMNAME_MOONTEMPLECELL, "string.theaurorian.tooltip.keymoontemplecell"),
		DARKSTONE(ITEMNAME_DARKSTONE, "string.theaurorian.tooltip.keydarkstone");

		private String ITEMNAME;
		private String INFO;

		Keys(String itemname, String info) {
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

	private Keys itemKey;

	public TAItem_DungeonKey(Keys key) {
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(key.getName());
		this.setMaxStackSize(1);
		this.setUnlocalizedName(TAMod.MODID + "." + key.getName());
		this.setMaxDamage(1);
		this.itemKey = key;
	}

	@Override
	public net.minecraftforge.common.IRarity getForgeRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format(this.itemKey.getInfo()));
		}
	}
}
