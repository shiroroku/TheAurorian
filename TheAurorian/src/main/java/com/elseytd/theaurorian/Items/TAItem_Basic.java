package com.elseytd.theaurorian.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Basic extends Item {

	public static final String ITEMNAME_CRYSTAL = "crystal";
	public static final String ITEMNAME_CUP = "cup";
	public static final String ITEMNAME_INGOT_AURORIANITE = "aurorianiteingot";
	public static final String ITEMNAME_INGOT_AURORIANSTEEL = "auroriansteel";
	public static final String ITEMNAME_INGOT_CERULEAN = "ceruleaningot";
	public static final String ITEMNAME_INGOT_CRYSTALLINE = "crystallineingot";
	public static final String ITEMNAME_INGOT_MOONSTONE = "moonstoneingot";
	public static final String ITEMNAME_INGOT_UMBRA = "umbraingot";
	public static final String ITEMNAME_PLANTFIBER = "plantfiber";
	public static final String ITEMNAME_SCRAP_AURORIANITE = "scrapaurorianite";
	public static final String ITEMNAME_SCRAP_CRYSTALLINE = "scrapcrystalline";
	public static final String ITEMNAME_SCRAP_UMBRA = "scrapumbra";

	public enum Items {
		CRYSTAL(ITEMNAME_CRYSTAL, 16),
		CUP(ITEMNAME_CUP, 16),
		INGOT_AURORIANITE(ITEMNAME_INGOT_AURORIANITE, EnumRarity.EPIC),
		INGOT_AURORIANSTEEL(ITEMNAME_INGOT_AURORIANSTEEL, EnumRarity.EPIC),
		INGOT_CERULEAN(ITEMNAME_INGOT_CERULEAN),
		INGOT_CRYSTALLINE(ITEMNAME_INGOT_CRYSTALLINE, EnumRarity.EPIC),
		INGOT_MOONSTONE(ITEMNAME_INGOT_MOONSTONE),
		INGOT_UMBRA(ITEMNAME_INGOT_UMBRA, EnumRarity.EPIC),
		PLANTFIBER(ITEMNAME_PLANTFIBER, "string.theaurorian.tooltip.plantfiber"),
		SCRAP_AURORIANITE(ITEMNAME_SCRAP_AURORIANITE),
		SCRAP_CRYSTALLINE(ITEMNAME_SCRAP_CRYSTALLINE),
		SCRAP_UMBRA(ITEMNAME_SCRAP_UMBRA);

		private String ITEMNAME;
		private EnumRarity RARITY;
		private String INFO;
		private int STACKSIZE;

		Items(String itemname) {
			this.STACKSIZE = 64;
			this.RARITY = EnumRarity.COMMON;
			this.ITEMNAME = itemname;
		}

		Items(String itemname, EnumRarity rarity) {
			this(itemname);
			this.RARITY = rarity;
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
			return ITEMNAME;
		}

		public String getInfo() {
			return INFO;
		}

		public int getStacksize() {
			return STACKSIZE;
		}

		public EnumRarity getRarity() {
			return RARITY;
		}
	}

	private Items itemType;

	public TAItem_Basic(TAItem_Basic.Items itemtype) {
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(itemtype.getName());
		this.setUnlocalizedName(TAMod.MODID + "." + itemtype.getName());
		this.itemType = itemtype;
		if (itemtype.getStacksize() != 64) {
			this.setMaxStackSize(itemtype.getStacksize());
		}
	}

	@Override
	public net.minecraftforge.common.IRarity getForgeRarity(ItemStack stack) {
		return itemType.getRarity();
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (itemType.getInfo() != null) {
			if (!GuiScreen.isShiftKeyDown()) {
				tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
			} else {
				tooltip.add(I18n.format(itemType.getInfo()));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}
