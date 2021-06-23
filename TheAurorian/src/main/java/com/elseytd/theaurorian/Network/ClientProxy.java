package com.elseytd.theaurorian.Network;

import com.elseytd.theaurorian.*;
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
		TAEntities.initModels();
		TACompat.clientPreInit(e);
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		TASounds.addMusicTypes();
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		TAItems.initModels();
		TABlocks.initModels();
	}
}
