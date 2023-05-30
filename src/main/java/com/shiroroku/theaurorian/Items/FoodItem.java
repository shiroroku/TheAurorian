package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class FoodItem extends ItemFood {

	public static final String ITEMNAME_SILKBERRYJAM = "silkberryjam";
	public static final String ITEMNAME_SILKBERRYJAMSANDWICH = "silkberryjamsandwich";
	public static final String ITEMNAME_AURORIANPORK = "aurorianpork";
	public static final String ITEMNAME_AURORIANBACON = "aurorianbacon";
	public static final String ITEMNAME_COOKEDAURORIANPORK = "aurorianporkcooked";
	public static final String ITEMNAME_AURORIANSLIMEBALL = "aurorianslimeball";
	public static final String ITEMNAME_SILKSHROOMSTEW = "silkshroomstew";
	public static final String ITEMNAME_LAVENDERBREAD = "lavenderbread";
	public static final String ITEMNAME_SOULLESSFLESH = "soullessflesh";

	public enum Foods {
		SILKBERRYJAM(ITEMNAME_SILKBERRYJAM, 2, 0.5F, 64),
		SILKBERRYJAMSANDWICH(ITEMNAME_SILKBERRYJAMSANDWICH, 6, 0.9F, true),
		AURORIANPORK(ITEMNAME_AURORIANPORK, 3, 0.3F),
		AURORIANBACON(ITEMNAME_AURORIANBACON, 2, 0.8F, true),
		COOKEDAURORIANPORK(ITEMNAME_COOKEDAURORIANPORK, 8, 0.8F, true),
		AURORIANSLIMEBALL(ITEMNAME_AURORIANSLIMEBALL, 1, 0.2F, 40),
		SILKSHROOMSTEW(ITEMNAME_SILKSHROOMSTEW, 6, 1F),
		LAVENDERBREAD(ITEMNAME_LAVENDERBREAD, 4, 0.4F),
		SOULLESSFLESH(ITEMNAME_SOULLESSFLESH, 2, 0.1F);

		private final String ITEMNAME;
		private final String INFO;
		private final int FEEDAMT;
		private final float SATURATION;
		private int EATTIME;

		Foods(String itemname, int feedamt, float saturation, int eattime) {
			this(itemname, feedamt, saturation, false);
			this.EATTIME = eattime;
		}

		Foods(String itemname, int feedamt, float saturation) {
			this(itemname, feedamt, saturation, false);
		}

		Foods(String itemname, int feedamt, float saturation, boolean info) {
			this.ITEMNAME = itemname;
			this.INFO = info ? "string.theaurorian.tooltip." + itemname : null;
			this.FEEDAMT = feedamt;
			this.SATURATION = saturation;
			this.EATTIME = 32;
		}

		public String getName() {
			return this.ITEMNAME;
		}

		public String getInfo() {
			return this.INFO;
		}

		public int getFeedAmount() {
			return this.FEEDAMT;
		}

		public float getSaturation() {
			return this.SATURATION;
		}

		public int getEatTime() {
			return this.EATTIME;
		}
	}

	private final Foods itemFood;

	public FoodItem(Foods foodtype) {
		super(foodtype.getFeedAmount(), foodtype.getSaturation(), false);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(foodtype.getName());
		this.setUnlocalizedName(AurorianMod.MODID + "." + foodtype.getName());
		this.itemFood = foodtype;
	}

	@Override
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

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return this.itemFood.getEatTime();
	}

}
