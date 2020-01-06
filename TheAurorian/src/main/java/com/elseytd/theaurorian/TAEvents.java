package com.elseytd.theaurorian;

import com.elseytd.theaurorian.Enchantments.TAEnchantment_Lightning_Damage;
import com.elseytd.theaurorian.Items.TAItem_Food_Tea;
import com.elseytd.theaurorian.Items.TAItem_Tool_Shield;

import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class TAEvents {

	@SubscribeEvent
	public void damageEvent(LivingDamageEvent e) {
		TAEnchantment_Lightning_Damage.handleDamageEvent(e);
	}

	@SubscribeEvent
	public void attackEvent(LivingAttackEvent e) {
		TAItem_Tool_Shield.handleDamageEvent(e);
	}

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
		if (block == TABlocks.auroriangrass || block == TABlocks.auroriandirt) {
			e.getWorld().playSound(e.getEntityPlayer(), e.getPos(), SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if (!e.getWorld().isRemote) {
				e.getWorld().setBlockState(e.getPos(), TABlocks.aurorianfarmtile.getDefaultState(), 11);
			}
			e.setResult(Result.ALLOW);
		}
	}

	/**
	 * Adds burntime to blocks
	 */
	@SubscribeEvent
	public static void TAFurnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		Item item = event.getItemStack().getItem();
		if (item == Item.getItemFromBlock(TABlocks.auroriancoalblock)) {
			event.setBurnTime(16000);
		} else if (item == Item.getItemFromBlock(TABlocks.silentwoodplanks)) {
			event.setBurnTime(200);
		} else if (item == Item.getItemFromBlock(TABlocks.silentwoodlog)) {
			event.setBurnTime(300);
		} else if (item == Item.getItemFromBlock(TABlocks.silentwoodstairs)) {
			event.setBurnTime(300);
		}
	}
}
