package com.elseytd.theaurorian.Network;

import java.io.File;

import com.elseytd.theaurorian.TABiomes;
import com.elseytd.theaurorian.TABlocks.Fluids;
import com.elseytd.theaurorian.TACompat;
import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TADimensions;
import com.elseytd.theaurorian.TAEntities;
import com.elseytd.theaurorian.TAEvents;
import com.elseytd.theaurorian.TAGuis;
import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TARecipes;
import com.elseytd.theaurorian.Recipes.MoonlightForgeRecipeHandler;
import com.elseytd.theaurorian.Recipes.ScrapperRecipeHandler;
import com.elseytd.theaurorian.Util.OreDictionaryHelper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
		File directory = e.getModConfigurationDirectory();
		TAMod.CONFIG = new Configuration(new File(directory.getPath(), "theaurorian.cfg"));
		TAConfig.readConfig();
		Fluids.registerFluids();
		TADimensions.init();
		TACompat.preInit(e);
		TAPacketHandler.registerMessages();
	}

	public void init(FMLInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(new TAEvents());
		TARecipes.registerFurnaceRecipes();
		NetworkRegistry.INSTANCE.registerGuiHandler(TAMod.INSTANCE, new TAGuis());
		TABiomes.initBiomeManagerAndDictionary();
		TAEntities.init();
		TARecipes.registerOreDictionary();
		MoonlightForgeRecipeHandler.initRecipes();
		ScrapperRecipeHandler.initRecipes();
		TAItems.Materials.initRepairMaterials();
	}

	public void postInit(FMLPostInitializationEvent e) {
		if (TAMod.CONFIG.hasChanged()) {
			TAMod.CONFIG.save();
		}
		TACompat.postInit(e);
		OreDictionaryHelper.Ores = OreDictionaryHelper.populateOrelist();
	}
}
