package com.shiroroku.theaurorian.Network;

import com.shiroroku.theaurorian.AurorianCompatibility;
import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.AurorianEvents;
import com.shiroroku.theaurorian.AurorianMod;
import com.shiroroku.theaurorian.Recipes.MoonlightForgeRecipeHandler;
import com.shiroroku.theaurorian.Recipes.ScrapperRecipeHandler;
import com.shiroroku.theaurorian.Registry.*;
import com.shiroroku.theaurorian.Util.OreDictionaryHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.io.File;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
		File directory = e.getModConfigurationDirectory();
		AurorianMod.CONFIG = new Configuration(new File(directory.getPath(), "theaurorian.cfg"));
		AurorianConfig.readConfig();
		BlockRegistry.Fluids.registerFluids();
		DimensionRegistry.init();
		AurorianCompatibility.preInit(e);
		PacketRegistry.registerMessages();
	}

	public void init(FMLInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(new AurorianEvents());
		RecipeRegistry.registerFurnaceRecipes();
		NetworkRegistry.INSTANCE.registerGuiHandler(AurorianMod.INSTANCE, new GUIRegistry());
		BiomeRegistry.initBiomeManagerAndDictionary();
		EntityRegistry.init();
		RecipeRegistry.registerOreDictionary();
		MoonlightForgeRecipeHandler.initRecipes();
		ScrapperRecipeHandler.initRecipes();
		ItemRegistry.Materials.initRepairMaterials();
	}

	public void postInit(FMLPostInitializationEvent e) {
		if (AurorianMod.CONFIG.hasChanged()) {
			AurorianMod.CONFIG.save();
		}
		AurorianCompatibility.postInit(e);
		OreDictionaryHelper.Ores = OreDictionaryHelper.populateOrelist();
	}
}
