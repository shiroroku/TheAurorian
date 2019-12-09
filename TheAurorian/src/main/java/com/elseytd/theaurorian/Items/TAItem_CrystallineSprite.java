package com.elseytd.theaurorian.Items;

import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.Entities.TAEntity_CrystallineSprite;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItem_CrystallineSprite extends Item {

	public static final String ITEMNAME = "crystallinesprite";

	public TAItem_CrystallineSprite() {
		//this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(TAMod.MODID + "." + ITEMNAME);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		if (!worldIn.isRemote && playerIn.isSneaking()) {
			TAEntity_CrystallineSprite spr = new TAEntity_CrystallineSprite(worldIn, playerIn, TAEntity_CrystallineSprite.Side.LEFT);
			spr.moveToBlockPosAndAngles(playerIn.getPosition(), 0F, 0.0F);
			spr.onInitialSpawn(worldIn.getDifficultyForLocation(playerIn.getPosition()), null);
			worldIn.spawnEntity(spr);
		}else if(!worldIn.isRemote) {
			TAEntity_CrystallineSprite spr = new TAEntity_CrystallineSprite(worldIn, playerIn, TAEntity_CrystallineSprite.Side.RIGHT);
			spr.moveToBlockPosAndAngles(playerIn.getPosition(), 0F, 0.0F);
			spr.onInitialSpawn(worldIn.getDifficultyForLocation(playerIn.getPosition()), null);
			worldIn.spawnEntity(spr);
		}

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

}
