package com.elseytd.theaurorian;

import com.elseytd.theaurorian.World.Biomes.TABiomeAurorianForest;
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
	public final static TABiomeAurorianPlains aurorianplains = null;
	public final static TABiomeWeepingWillowForest weepingwillowforest = null;

	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		event.getRegistry().register(new TABiomeAurorianForest().setRegistryName(TAMod.MODID, "aurorianforest"));
		event.getRegistry().register(new TABiomeAurorianPlains().setRegistryName(TAMod.MODID, "aurorianplains"));
		event.getRegistry().register(new TABiomeWeepingWillowForest().setRegistryName(TAMod.MODID, "weepingwillowforest"));
	}

	public static void initBiomeManagerAndDictionary() {
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(aurorianforest, 0));
		BiomeDictionary.addTypes(aurorianforest, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.MAGICAL);

		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(aurorianplains, 0));
		BiomeDictionary.addTypes(aurorianplains, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.MAGICAL);
		
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(weepingwillowforest, 0));
		BiomeDictionary.addTypes(weepingwillowforest, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.MAGICAL);
	}
}
