package com.elseytd.theaurorian;

import com.elseytd.theaurorian.World.Biomes.TABiomeAurorianForest;
import com.elseytd.theaurorian.World.Biomes.TABiomeAurorianPlains;

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

	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		event.getRegistry().register(new TABiomeAurorianForest().setRegistryName(TAMod.MODID, "aurorianforest"));
		event.getRegistry().register(new TABiomeAurorianPlains().setRegistryName(TAMod.MODID, "aurorianplains"));
	}

	public static void initBiomeManagerAndDictionary() {
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(aurorianforest, 0));
		//BiomeManager.addSpawnBiome(aurorianforest);
		BiomeDictionary.addTypes(aurorianforest, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.MAGICAL);

		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(aurorianplains, 0));
		//BiomeManager.addSpawnBiome(aurorianplains);
		BiomeDictionary.addTypes(aurorianplains, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.MAGICAL);
	}
}
