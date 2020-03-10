package com.elseytd.theaurorian.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Food extends ItemFood {

	public static final String ITEMNAME_SILKBERRYRASIN = "silkberryrasin";
	public static final String ITEMNAME_AURORIANPORK = "aurorianpork";
	public static final String ITEMNAME_AURORIANBACON = "aurorianbacon";
	public static final String ITEMNAME_COOKEDAURORIANPORK = "aurorianporkcooked";
	public static final String ITEMNAME_AURORIANSLIMEBALL = "aurorianslimeball";
	public static final String ITEMNAME_SILKSHROOMSTEW = "silkshroomstew";

	public enum Foods {
		SILKBERRYRASIN(ITEMNAME_SILKBERRYRASIN, 3, 0.2F, null),
		AURORIANPORK(ITEMNAME_AURORIANPORK, 3, 0.3F, null),
		AURORIANBACON(ITEMNAME_AURORIANBACON, 2, 0.8F, "string.theaurorian.tooltip.aurorianbacon"),
		COOKEDAURORIANPORK(ITEMNAME_COOKEDAURORIANPORK, 8, 0.8F, "string.theaurorian.tooltip.aurorianporkcooked"),
		AURORIANSLIMEBALL(ITEMNAME_AURORIANSLIMEBALL, 1, 0.2F, null),
		SILKSHROOMSTEW(ITEMNAME_SILKSHROOMSTEW, 6, 1F, null);

		private String ITEMNAME;
		private String INFO;
		private int FEEDAMT;
		private float SATURATION;

		Foods(String itemname, int feedamt, float saturation, String info) {
			this.ITEMNAME = itemname;
			this.INFO = info;
			this.FEEDAMT = feedamt;
			this.SATURATION = saturation;
		}

		public String getName() {
			return ITEMNAME;
		}

		public String getInfo() {
			return INFO;
		}

		public int getFeedAmount() {
			return FEEDAMT;
		}

		public float getSaturation() {
			return SATURATION;
		}
	}

	private Foods itemFood;

	public TAItem_Food(Foods foodtype) {
		super(foodtype.getFeedAmount(), foodtype.getSaturation(), false);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(foodtype.getName());
		this.setUnlocalizedName(TAMod.MODID + "." + foodtype.getName());
		this.itemFood = foodtype;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (this.itemFood.getInfo() != null) {
			if (!GuiScreen.isShiftKeyDown()) {
				tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
			} else {
				tooltip.add(I18n.format(this.itemFood.getInfo()));
			}
		}
	}

}
