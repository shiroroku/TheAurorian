package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.AurorianMod;
import com.shiroroku.theaurorian.Util.GenerationHelper;
import com.shiroroku.theaurorian.World.Structures.DarkstoneDungeonWorldGenerator;
import com.shiroroku.theaurorian.World.Structures.MoonTempleWorldGenerator;
import com.shiroroku.theaurorian.World.Structures.RunestoneTowerWorldGenerator;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class DungeonLocatorItem extends Item {

	public static final String ITEMNAME = "locator";

	public DungeonLocatorItem() {
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setMaxStackSize(1);
		this.setMaxDamage(30);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if (playerIn.isSneaking()) {
			switch (this.getSelectedDungeon(itemstack)) {
				case "Moontemple":
					this.setSelectedDungeon(itemstack, "Runestone");
					playerIn.sendStatusMessage(new TextComponentString(I18n.format("string.theaurorian.item.locator1")), true);
					break;
				default:
				case "Runestone":
					this.setSelectedDungeon(itemstack, "Darkstone");
					playerIn.sendStatusMessage(new TextComponentString(I18n.format("string.theaurorian.item.locator3")), true);
					break;
				case "Darkstone":
					this.setSelectedDungeon(itemstack, "Moontemple");
					playerIn.sendStatusMessage(new TextComponentString(I18n.format("string.theaurorian.item.locator2")), true);
					break;
			}
		} else {
			ChunkPos dungeon;
			switch (this.getSelectedDungeon(itemstack)) {
				case "Moontemple":
					dungeon = GenerationHelper.getNearestStructure(new MoonTempleWorldGenerator(), playerIn, AurorianConfig.Config_DungeonDensity * 4);
					break;
				default:
				case "Runestone":
					dungeon = GenerationHelper.getNearestStructure(new RunestoneTowerWorldGenerator(), playerIn, AurorianConfig.Config_DungeonDensity * 2);
					break;
				case "Darkstone":
					dungeon = GenerationHelper.getNearestStructure(new DarkstoneDungeonWorldGenerator(), playerIn, AurorianConfig.Config_DungeonDensity * 6);
					break;
			}

			worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (dungeon != null) {
				if (worldIn.isRemote) {
					double lookx = 0.25D + -MathHelper.sin((float) Math.toRadians(playerIn.rotationYawHead)) * MathHelper.cos((float) Math.toRadians(playerIn.rotationPitch));
					double looky = 0.25D + -MathHelper.sin((float) Math.toRadians(playerIn.rotationPitch));
					double lookz = 0.25D + MathHelper.cos((float) Math.toRadians(playerIn.rotationYawHead)) * MathHelper.cos((float) Math.toRadians(playerIn.rotationPitch));

					double y = playerIn.posY + 1 + Item.itemRand.nextDouble() * 6.0D / 16.0D;
					double speed = 0.01D;
					double targetx = playerIn.posX - dungeon.x * 16;
					double targetz = playerIn.posZ - dungeon.z * 16;
					double originx = playerIn.posX;
					double originz = playerIn.posZ;

					double partx = targetx * -speed;
					double partz = targetz * -speed;

					if (partx < -0.5) {
						partx = -0.5;
					}
					if (partx > 0.5) {
						partx = 0.5;
					}
					if (partz < -0.5) {
						partz = -0.5;
					}
					if (partz > 0.5) {
						partz = 0.5;
					}

					double randx = Item.itemRand.nextDouble() / 8;
					double randz = Item.itemRand.nextDouble() / 8;

					for (int i = 0; i < 2; i++) {
						worldIn.spawnParticle(EnumParticleTypes.CLOUD, originx + lookx, y + looky, originz + lookz, partx + randx, 0.25D, partz + randz);
						worldIn.spawnParticle(EnumParticleTypes.ITEM_CRACK, originx + lookx, y + looky, originz + lookz, partx + randx, 0.25D, partz + randz, Item.getIdFromItem(itemstack.getItem()), itemstack.getMetadata());
					}
				}
				itemstack.damageItem(1, playerIn);
			}
			playerIn.addStat(StatList.getObjectUseStats(this));
			return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
		}
		return new ActionResult<>(EnumActionResult.PASS, itemstack);
	}

	private NBTTagCompound getNBT(ItemStack stack) {
		NBTTagCompound nbt;
		if (stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
		} else {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}
		return nbt;
	}

	private String getSelectedDungeon(ItemStack stack) {
		String blockname = this.getNBT(stack).getString("dungeon");
		if (blockname.isEmpty()) {
			return "Runestone";
		} else {
			return blockname;
		}
	}

	private boolean setSelectedDungeon(ItemStack stack, String dungeon) {
		NBTTagCompound nbt = this.getNBT(stack);
		if (dungeon == null) {
			nbt.setString("dungeon", "Runestone");
			return true;
		}
		if (dungeon != this.getSelectedDungeon(stack)) {
			nbt.setString("dungeon", dungeon);
			return true;
		}
		return false;
	}

	@Override
	public net.minecraftforge.common.IRarity getForgeRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (this.getSelectedDungeon(stack) != null) {
			tooltip.add(TextFormatting.AQUA + "[" + this.getSelectedDungeon(stack) + "]" + TextFormatting.RESET);
		}
		if (!GuiScreen.isShiftKeyDown()) {
			tooltip.add(TextFormatting.ITALIC + I18n.format("string.theaurorian.tooltip.shiftinfo") + TextFormatting.RESET);
		} else {
			tooltip.add(I18n.format("string.theaurorian.tooltip.locator"));
		}
	}

}
