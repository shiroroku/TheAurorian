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

public class BasicItem extends Item {

	public static final String ITEMNAME_AURORIANCOAL = "auroriancoal";
	public static final String ITEMNAME_CUP = "cup";
	public static final String ITEMNAME_INGOT_AURORIANITE = "aurorianiteingot";
	public static final String ITEMNAME_INGOT_AURORIANSTEEL = "auroriansteel";
	public static final String ITEMNAME_INGOT_CERULEAN = "ceruleaningot";
	public static final String ITEMNAME_INGOT_CRYSTALLINE = "crystallineingot";
	public static final String ITEMNAME_INGOT_MOONSTONE = "moonstoneingot";
	public static final String ITEMNAME_INGOT_UMBRA = "umbraingot";
	public static final String ITEMNAME_LAVENDER = "lavender";
	public static final String ITEMNAME_MOONTEMPLECELLKEYFRAGMENT = "moontemplecellkeyfragment";
	public static final String ITEMNAME_NUGGET_AURORIANSTEEL = "auroriansteelnugget";
	public static final String ITEMNAME_NUGGET_CERULEAN = "ceruleannugget";
	public static final String ITEMNAME_NUGGET_AURORIANCOAL = "auroriancoalnugget";
	public static final String ITEMNAME_NUGGET_MOONSTONE = "moonstonenugget";
	public static final String ITEMNAME_PLANTFIBER = "plantfiber";
	public static final String ITEMNAME_SCRAP_AURORIANITE = "scrapaurorianite";
	public static final String ITEMNAME_SCRAP_CRYSTALLINE = "scrapcrystalline";
	public static final String ITEMNAME_SCRAP_UMBRA = "scrapumbra";
	public static final String ITEMNAME_SPECTRALSILK = "spectralsilk";
	public static final String ITEMNAME_TROPHY_KEEPER = "trophykeeper";
	public static final String ITEMNAME_TROPHY_MOONQUEEN = "trophymoonqueen";
	public static final String ITEMNAME_TROPHY_SPIDER = "trophyspider";

	public enum Items {
		AURORIANCOAL(ITEMNAME_AURORIANCOAL, 64, 1800),
		CUP(ITEMNAME_CUP, 16),
		INGOT_AURORIANITE(ITEMNAME_INGOT_AURORIANITE, EnumRarity.EPIC),
		INGOT_AURORIANSTEEL(ITEMNAME_INGOT_AURORIANSTEEL, EnumRarity.EPIC),
		INGOT_CERULEAN(ITEMNAME_INGOT_CERULEAN),
		INGOT_CRYSTALLINE(ITEMNAME_INGOT_CRYSTALLINE, EnumRarity.EPIC),
		INGOT_MOONSTONE(ITEMNAME_INGOT_MOONSTONE),
		INGOT_UMBRA(ITEMNAME_INGOT_UMBRA, EnumRarity.EPIC),
		LAVENDER(ITEMNAME_LAVENDER, "string.theaurorian.tooltip.lavender"),
		MOONTEMPLECELLKEYFRAGMENT(ITEMNAME_MOONTEMPLECELLKEYFRAGMENT, 1, "string.theaurorian.tooltip.moontemplecellkeyfragment"),
		NUGGET_AURORIANSTEEL(ITEMNAME_NUGGET_AURORIANSTEEL),
		NUGGET_CERULEAN(ITEMNAME_NUGGET_CERULEAN),
		NUGGET_AURORIANCOAL(ITEMNAME_NUGGET_AURORIANCOAL, 64, 200),
		NUGGET_MOONSTONE(ITEMNAME_NUGGET_MOONSTONE),
		PLANTFIBER(ITEMNAME_PLANTFIBER, "string.theaurorian.tooltip.plantfiber"),
		SCRAP_AURORIANITE(ITEMNAME_SCRAP_AURORIANITE),
		SCRAP_CRYSTALLINE(ITEMNAME_SCRAP_CRYSTALLINE),
		SCRAP_UMBRA(ITEMNAME_SCRAP_UMBRA),
		SPECTRALSILK(ITEMNAME_SPECTRALSILK, EnumRarity.RARE, "string.theaurorian.tooltip.spectralsilk"),
		TROPHY_KEEPER(ITEMNAME_TROPHY_KEEPER, EnumRarity.RARE),
		TROPHY_MOONQUEEN(ITEMNAME_TROPHY_MOONQUEEN, EnumRarity.RARE),
		TROPHY_SPIDER(ITEMNAME_TROPHY_SPIDER, EnumRarity.RARE);

		private final String ITEMNAME;
		private EnumRarity RARITY;
		private int BURNTIME;
		private String INFO;
		private int STACKSIZE;

		Items(String itemname) {
			this.STACKSIZE = 64;
			this.BURNTIME = -1;
			this.RARITY = EnumRarity.COMMON;
			this.ITEMNAME = itemname;
		}

		Items(String itemname, int stacksize, int burntime) {
			this(itemname, stacksize);
			this.BURNTIME = burntime;
		}

		Items(String itemname, EnumRarity rarity) {
			this(itemname);
			this.RARITY = rarity;
		}

		Items(String itemname, EnumRarity rarity, String info) {
			this(itemname, rarity);
			this.INFO = info;
		}

		Items(String itemname, int stacksize, String info) {
			this(itemname, stacksize);
			this.INFO = info;
		}

		Items(String itemname, int stacksize) {
			this(itemname);
			this.STACKSIZE = stacksize;
		}

		Items(String itemname, String info) {
			this(itemname);
			this.INFO = info;
		}

		public String getName() {
			return this.ITEMNAME;
		}

		public String getInfo() {
			return this.INFO;
		}

		public int getStacksize() {
			return this.STACKSIZE;
		}

		public int getBurntime() {
			return this.BURNTIME;
		}

		public EnumRarity getRarity() {
			return this.RARITY;
		}
	}

	private final Items itemType;

	public BasicItem(BasicItem.Items itemtype) {
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(itemtype.getName());
		this.setUnlocalizedName(AurorianMod.MODID + "." + itemtype.getName());
		this.itemType = itemtype;
		if (itemtype.getStacksize() != 64) {
			this.setMaxStackSize(itemtype.getStacksize());
		}
	}

	@Override
	public net.minecraftforge.common.IRarity getForgeRarity(ItemStack stack) {
		return this.itemType.getRarity();
	}

	@Override
	public int getItemBurnTime(ItemStack itemStack) {
		return this.itemType.getBurntime();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (this.itemType.getInfo() != null) {
			if (!GuiScreen.isShiftKeyDown()) {
				tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
			} else {
				tooltip.add(I18n.format(this.itemType.getInfo()));
			}
		}
	}
}
