package com.elseytd.theaurorian;

import com.elseytd.theaurorian.Enchantments.TAEnchantment_Lightning_Damage;
import com.elseytd.theaurorian.Items.TAItem_Armor_AurorianSteel;
import com.elseytd.theaurorian.Items.TAItem_Armor_SlimeBoots;
import com.elseytd.theaurorian.Items.TAItem_Food_Tea;
import com.elseytd.theaurorian.Items.TAItem_Tool_Umbra_Pickaxe;
import com.elseytd.theaurorian.Items.TAItem_Tool_Shield;

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
public class TAEvents {

	@SubscribeEvent
	public void damageEvent(LivingDamageEvent e) {
		TAEnchantment_Lightning_Damage.handleDamageEvent(e);
		TAItem_Armor_AurorianSteel.handleDamageEvent(e);
	}

	@SubscribeEvent
	public void jumpEvent(LivingJumpEvent e) {
		TAItem_Armor_SlimeBoots.handleJumpEvent(e);
	}

	@SubscribeEvent
	public void fallEvent(LivingFallEvent e) {
		TAItem_Armor_SlimeBoots.handleFallEvent(e);
	}

	@SubscribeEvent
	public void attackEvent(LivingAttackEvent e) {
		TAItem_Tool_Shield.handleDamageEvent(e);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void drawScreenEvent(DrawScreenEvent.Pre e) {
		TAItem_Tool_Umbra_Pickaxe.renderSelectedBlock(e);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void drawBlockHighlightEvent(DrawBlockHighlightEvent e) {
		TAItem_Tool_Umbra_Pickaxe.renderBlockOutline(e);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerItemHandlers(ColorHandlerEvent.Item event) {
		TAItem_Food_Tea.registerItemColorHandler(event);
	}

	/**
	 * Allows all hoes to turn Aurorian soil to Aurorian farmland
	 */
	@SubscribeEvent
	public static void onHoeUse(UseHoeEvent e) {
		Block block = e.getWorld().getBlockState(e.getPos()).getBlock();
		if (block == TABlocks.auroriangrass || block == TABlocks.auroriangrasslight || block == TABlocks.auroriandirt) {
			e.getWorld().playSound(e.getEntityPlayer(), e.getPos(), SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if (!e.getWorld().isRemote) {
				e.getWorld().setBlockState(e.getPos(), TABlocks.aurorianfarmtile.getDefaultState(), 11);
			}
			e.setResult(Result.ALLOW);
		}
	}

	@SubscribeEvent
	public static void TAFurnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		TARecipes.registerBlockBurntime(event);
	}
}
