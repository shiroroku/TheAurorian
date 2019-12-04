package com.elseytd.theaurorian.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TAUtil;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_Special_LivingDiviningRod extends Item {
	public static final String ITEMNAME = "livingdiviningrod";

	public TAItem_Special_LivingDiviningRod() {
		super();
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
		this.setMaxDamage(100);
		this.setMaxStackSize(1);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		double width = 18;
		double height = 18;
		double x = playerIn.posX;
		double y = playerIn.posY;
		double z = playerIn.posZ;
		double boxsx = x - (width / 2);
		double boxsy = y - (height / 2);
		double boxsz = z - (width / 2);
		double boxex = x + (width / 2);
		double boxey = y + (height / 2);
		double boxez = z + (width / 2);

		List<EntityLivingBase> entities = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(boxsx, boxsy, boxsz, boxex, boxey, boxez));
		for (EntityLivingBase e : entities) {
			if (TAUtil.randomChanceOf(0.75f)) {
				e.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 120));
			}
		}
		playerIn.getHeldItemMainhand().damageItem(1, playerIn);
		playerIn.getCooldownTracker().setCooldown(this, 120);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}

	@Override
	public net.minecraftforge.common.IRarity getForgeRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + "Hold shift for more info" + TextFormatting.RESET);
		} else {
			tooltip.add("Has a chance to glow nearby entities!");
		}
	}
}
