package com.elseytd.theaurorian.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
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

public class TAItem_Special_Aurorianite_Sword extends ItemSword {
	public static final String ITEMNAME = "aurorianitesword";

	public TAItem_Special_Aurorianite_Sword() {
		super(TAItems.Materials.AURORIANITE);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (playerIn.getHeldItemOffhand().isEmpty() || playerIn.isSneaking()) {
			double width = 9;
			double height = 3;
			double x = playerIn.posX;
			double y = playerIn.posY;
			double z = playerIn.posZ;
			double boxsx = x - (width / 2);
			double boxsy = y - 1;
			double boxsz = z - (width / 2);
			double boxex = x + (width / 2);
			double boxey = y + height;
			double boxez = z + (width / 2);

			List<EntityLivingBase> entities = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(boxsx, boxsy, boxsz, boxex, boxey, boxez));
			for (EntityLivingBase e : entities) {
				e.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 60));
				e.motionY = e.motionY + 0.5D;
			}
			
			playerIn.getHeldItemMainhand().damageItem(5, playerIn);
			playerIn.getCooldownTracker().setCooldown(this, TAConfig.Config_AurorianiteSwordCooldown);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
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
			tooltip.add("Magical power shines in this metal. Has the ability to levitate everyone nearby!");
		}
	}
}
