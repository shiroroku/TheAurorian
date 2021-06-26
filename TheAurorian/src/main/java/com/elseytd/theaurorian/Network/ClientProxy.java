package com.elseytd.theaurorian.Network;

import com.elseytd.theaurorian.*;
import com.elseytd.theaurorian.Registry.BlockRegistry;
import com.elseytd.theaurorian.Registry.EntityRegistry;
import com.elseytd.theaurorian.Registry.ItemRegistry;
import com.elseytd.theaurorian.Registry.SoundRegistry;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		EntityRegistry.initModels();
		AurorianCompatibility.clientPreInit(e);
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		SoundRegistry.addMusicTypes();
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		ItemRegistry.initModels();
		BlockRegistry.initModels();
	}
}
