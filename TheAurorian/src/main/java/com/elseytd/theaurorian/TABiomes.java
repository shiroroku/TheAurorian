package com.elseytd.theaurorian;

import com.elseytd.theaurorian.World.Biomes.TABiomeAurorianForest;
import com.elseytd.theaurorian.World.Biomes.TABiomeAurorianForestHills;
import com.elseytd.theaurorian.World.Biomes.TABiomeAurorianLakes;
import com.elseytd.theaurorian.World.Biomes.TABiomeAurorianPlains;
import com.elseytd.theaurorian.World.Biomes.TABiomeWeepingWillowForest;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(TAMod.MODID)
@Mod.EventBusSubscriber
public class TABiomes {

	public final static TABiomeAurorianForest aurorianforest = null;
	public final static TABiomeAurorianForestHills aurorianforesthills = null;
	public final static TABiomeAurorianPlains aurorianplains = null;
	public final static TABiomeWeepingWillowForest weepingwillowforest = null;
	public final static TABiomeAurorianLakes aurorianlakes = null;

	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		event.getRegistry().register(new TABiomeAurorianForest().setRegistryName(TAMod.MODID, TABiomeAurorianForest.BIOMENAME));
		event.getRegistry().register(new TABiomeAurorianForestHills().setRegistryName(TAMod.MODID, TABiomeAurorianForestHills.BIOMENAME));
		event.getRegistry().register(new TABiomeAurorianPlains().setRegistryName(TAMod.MODID, TABiomeAurorianPlains.BIOMENAME));
		event.getRegistry().register(new TABiomeWeepingWillowForest().setRegistryName(TAMod.MODID, TABiomeWeepingWillowForest.BIOMENAME));
		event.getRegistry().register(new TABiomeAurorianLakes().setRegistryName(TAMod.MODID, TABiomeAurorianLakes.BIOMENAME));
	}

	public static void initBiomeManagerAndDictionary() {
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(aurorianforest, 0));
		BiomeDictionary.addTypes(aurorianforest, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.FOREST);

		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(aurorianforesthills, 0));
		BiomeDictionary.addTypes(aurorianforesthills, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.HILLS);

		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(aurorianplains, 0));
		BiomeDictionary.addTypes(aurorianplains, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.PLAINS);

		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(weepingwillowforest, 0));
		BiomeDictionary.addTypes(weepingwillowforest, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.FOREST);

		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(aurorianlakes, 0));
		BiomeDictionary.addTypes(aurorianlakes, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.WATER);
	}
}
