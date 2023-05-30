package com.shiroroku.theaurorian.Registry;

import com.shiroroku.theaurorian.AurorianMod;
import com.shiroroku.theaurorian.World.Biomes.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(AurorianMod.MODID)
@Mod.EventBusSubscriber
public class BiomeRegistry {

	public final static AurorianForestBiome aurorianforest = null;
	public final static AurorianForestHillsBiome aurorianforesthills = null;
	public final static AurorianPlainsBiome aurorianplains = null;
	public final static WeepingWillowForestBiome weepingwillowforest = null;
	public final static AurorianLakesBiome aurorianlakes = null;

	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		event.getRegistry().register(new AurorianForestBiome().setRegistryName(AurorianMod.MODID, AurorianForestBiome.BIOMENAME));
		event.getRegistry().register(new AurorianForestHillsBiome().setRegistryName(AurorianMod.MODID, AurorianForestHillsBiome.BIOMENAME));
		event.getRegistry().register(new AurorianPlainsBiome().setRegistryName(AurorianMod.MODID, AurorianPlainsBiome.BIOMENAME));
		event.getRegistry().register(new WeepingWillowForestBiome().setRegistryName(AurorianMod.MODID, WeepingWillowForestBiome.BIOMENAME));
		event.getRegistry().register(new AurorianLakesBiome().setRegistryName(AurorianMod.MODID, AurorianLakesBiome.BIOMENAME));
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
