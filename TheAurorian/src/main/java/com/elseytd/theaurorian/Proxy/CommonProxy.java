package com.elseytd.theaurorian.Proxy;

import java.io.File;

import com.elseytd.theaurorian.TABiomes;
import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TACompat;
import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TADimensions;
import com.elseytd.theaurorian.TAEntities;
import com.elseytd.theaurorian.TAEvents;
import com.elseytd.theaurorian.TAGuis;
import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TARecipes;
import com.elseytd.theaurorian.TAUtil;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		TAItems.registerItems(event);
		TABlocks.registerItemblocks(event);
	}

	public void preInit(FMLPreInitializationEvent e) {
		File directory = e.getModConfigurationDirectory();
		TAMod.CONFIG = new Configuration(new File(directory.getPath(), "theaurorian.cfg"));
		TAConfig.readConfig();
		TADimensions.init();
		TACompat.preInit(e);
	}

	public void init(FMLInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(new TAEvents());
		TARecipes.registerFurnaceRecipes();
		NetworkRegistry.INSTANCE.registerGuiHandler(TAMod.INSTANCE, new TAGuis());
		TABiomes.initBiomeManagerAndDictionary();
		TAEntities.init();
		TABlocks.registerOreDictionary();
		TAItems.Materials.initRepairMaterials();
		TACompat.init(e);
	}

	public void postInit(FMLPostInitializationEvent e) {
		if (TAMod.CONFIG.hasChanged()) {
			TAMod.CONFIG.save();
		}
		TACompat.postInit(e);
		TAUtil.Ores = TAUtil.populateOrelocallist();
	}
}
