package com.elseytd.theaurorian;

import com.elseytd.theaurorian.Enchantments.TAEnchantment_Lightning_Damage;
import com.elseytd.theaurorian.Enchantments.TAEnchantment_Lightning_Resist;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(TAMod.MODID)
public class TAEnchantments {

	public static final Enchantment lightning = null;
	public static final Enchantment lightningresistance = null;

	@Mod.EventBusSubscriber(modid = TAMod.MODID)
	public static class RegistrationHandler {

		@SubscribeEvent
		public static void onEvent(final RegistryEvent.Register<Enchantment> event) {
			final IForgeRegistry<Enchantment> registry = event.getRegistry();
			registry.register(new TAEnchantment_Lightning_Damage());
			registry.register(new TAEnchantment_Lightning_Resist());
		}
	}
}
