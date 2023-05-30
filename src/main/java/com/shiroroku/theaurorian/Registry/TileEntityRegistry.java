package com.shiroroku.theaurorian.Registry;

import com.shiroroku.theaurorian.AurorianMod;
import com.shiroroku.theaurorian.Blocks.*;
import com.shiroroku.theaurorian.TileEntities.*;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityRegistry {

	public static void registerTileEntities(RegistryEvent.Register<Block> event) {
		GameRegistry.registerTileEntity(BossSpawnerTileEntity.class, new ResourceLocation(AurorianMod.MODID + ":" + "bossspawner"));
		GameRegistry.registerTileEntity(AurorianFurnaceTileEntity.class, new ResourceLocation(AurorianMod.MODID + ":" + AurorianFurnace.BLOCKNAME));
		GameRegistry.registerTileEntity(MoonLightForgeTileEntity.class, new ResourceLocation(AurorianMod.MODID + ":" + MoonLightForge.BLOCKNAME));
		GameRegistry.registerTileEntity(CrystalBlockTileEntity.class, new ResourceLocation(AurorianMod.MODID + ":" + CrystalBlock.BLOCKNAME));
		GameRegistry.registerTileEntity(ScrapperTileEntity.class, new ResourceLocation(AurorianMod.MODID + ":" + ScrapperBlock.BLOCKNAME));
		GameRegistry.registerTileEntity(SilentwoodChestTileEntity.class, new ResourceLocation(AurorianMod.MODID + ":" + SilentwoodChest.BLOCKNAME));
	}

}
