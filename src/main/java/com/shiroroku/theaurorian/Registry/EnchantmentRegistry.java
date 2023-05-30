package com.shiroroku.theaurorian.Registry;

import com.shiroroku.theaurorian.Enchantments.LightningDamage;
import com.shiroroku.theaurorian.Enchantments.LightningResistance;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(AurorianMod.MODID)
public class EnchantmentRegistry {

	public static final Enchantment lightning = null;
	public static final Enchantment lightningresistance = null;

	@Mod.EventBusSubscriber(modid = AurorianMod.MODID)
	public static class RegistrationHandler {

		@SubscribeEvent
		public static void onEvent(final RegistryEvent.Register<Enchantment> event) {
			final IForgeRegistry<Enchantment> registry = event.getRegistry();
			registry.register(new LightningDamage());
			registry.register(new LightningResistance());
		}
	}
}
