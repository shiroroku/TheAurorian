package com.elseytd.theaurorian.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Special_Amulet extends Item {

	public static final String ITEMNAME_KEEPERAMULET = "keeperamulet";
	public static final String ITEMNAME_DARKAMULET = "darkamulet";
	
	public enum Amulets {
		KEEPERAMULET(ITEMNAME_KEEPERAMULET, "Pulsates with corrupted power. Used for crafting the Moontemple Key or the Runestone Breaker."),
		DARKAMULET(ITEMNAME_DARKAMULET, "Has a faint dreadful aura.");
		
		private String ITEMNAME;
		private String INFO;
		
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
	
	private Amulets itemAmulet;
	
	public TAItem_Special_Amulet(Amulets amulet) {
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(amulet.getName());
		this.setUnlocalizedName(TAMod.MODID + "." + amulet.getName());
		this.setMaxStackSize(1);
		this.itemAmulet = amulet;
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
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
			tooltip.add(TextFormatting.ITALIC + "Hold shift for more info" + TextFormatting.RESET);
		} else {
			tooltip.add(this.itemAmulet.getInfo());
		}
	}
}
