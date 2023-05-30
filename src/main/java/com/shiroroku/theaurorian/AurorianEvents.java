package com.shiroroku.theaurorian;

import com.shiroroku.theaurorian.Enchantments.LightningDamage;
import com.shiroroku.theaurorian.Enchantments.LightningResistance;
import com.shiroroku.theaurorian.Entities.Boss.SpiderEntity;
import com.shiroroku.theaurorian.Entities.Hostile.SpiderlingEntity;
import com.shiroroku.theaurorian.Items.*;
import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.Registry.RecipeRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class AurorianEvents {

	@SubscribeEvent
	public void damageEvent(LivingDamageEvent e) {
		LightningDamage.handleDamageEvent(e);
		LightningResistance.handleDamageEvent(e);
		AurorianSteelItemArmor.handleDamageEvent(e);
		SpectralItemArmor.handleDamageEvent(e);
	}

	@SubscribeEvent
	public void jumpEvent(LivingJumpEvent e) {
		SlimeBootsItemArmor.handleJumpEvent(e);
	}

	@SubscribeEvent
	public void fallEvent(LivingFallEvent e) {
		SlimeBootsItemArmor.handleFallEvent(e);
		SpiderlingEntity.handleFallEvent(e);
		SpiderEntity.handleFallEvent(e);
	}

	@SubscribeEvent
	public void attackEvent(LivingAttackEvent e) {
		ShieldItem.handleDamageEvent(e);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void drawScreenEvent(DrawScreenEvent.Pre e) {
		UmbraPickaxe.renderSelectedBlock(e);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void drawBlockHighlightEvent(DrawBlockHighlightEvent e) {
		UmbraPickaxe.renderBlockOutline(e);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerItemHandlers(ColorHandlerEvent.Item event) {
		TeaFood.registerItemColorHandler(event);
	}

	/**
	 * Allows all hoes to turn Aurorian soil to Aurorian farmland
	 */
	@SubscribeEvent
	public static void onHoeUse(UseHoeEvent e) {
		Block block = e.getWorld().getBlockState(e.getPos()).getBlock();
		if (block == BlockRegistry.Registry.AURORIANGRASS.getBlock() || block == BlockRegistry.Registry.AURORIANGRASSLIGHT.getBlock() || block == BlockRegistry.Registry.AURORIANDIRT.getBlock()) {
			e.getWorld().playSound(e.getEntityPlayer(), e.getPos(), SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if (!e.getWorld().isRemote) {
				e.getWorld().setBlockState(e.getPos(), BlockRegistry.Registry.AURORIANFARMTILE.getBlock().getDefaultState(), 11);
			}
			e.setResult(Result.ALLOW);
		}
	}

	@SubscribeEvent
	public static void TAFurnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		RecipeRegistry.registerBlockBurntime(event);
	}
}
