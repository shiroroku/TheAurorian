package com.elseytd.theaurorian;

import com.elseytd.theaurorian.Blocks.*;
import com.elseytd.theaurorian.TileEntities.*;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TATileEntities {

	public static void registerTileEntities(RegistryEvent.Register<Block> event) {
		GameRegistry.registerTileEntity(BossSpawner_TileEntity.class, new ResourceLocation(TAMod.MODID + ":" + "bossspawner"));
		GameRegistry.registerTileEntity(AurorianFurnace_TileEntity.class, new ResourceLocation(TAMod.MODID + ":" + TABlock_Furnace.BLOCKNAME));
		GameRegistry.registerTileEntity(MoonLightForge_TileEntity.class, new ResourceLocation(TAMod.MODID + ":" + TABlock_MoonLightForge.BLOCKNAME));
		GameRegistry.registerTileEntity(CrystalBlock_TileEntity.class, new ResourceLocation(TAMod.MODID + ":" + TABlock_Crystal.BLOCKNAME));
		GameRegistry.registerTileEntity(Scrapper_TileEntity.class, new ResourceLocation(TAMod.MODID + ":" + TABlock_Scrapper.BLOCKNAME));
		GameRegistry.registerTileEntity(SilentwoodChest_TileEntity.class, new ResourceLocation(TAMod.MODID + ":" + TABlock_Silentwood_Chest.BLOCKNAME));
	}

}
