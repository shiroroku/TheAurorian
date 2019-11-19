package com.elseytd.theaurorian;

import com.elseytd.theaurorian.Blocks.TABlock_Furnace;
import com.elseytd.theaurorian.TileEntities.TATileEntity_Spawner_Boss;
import com.elseytd.theaurorian.TileEntities.Furnace.TATileEntity_Aurorian_Furnace;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TATileEntities {

	public static void registerTileEntities(RegistryEvent.Register<Block> event) {
		GameRegistry.registerTileEntity(TATileEntity_Spawner_Boss.class, new ResourceLocation(TAMod.MODID + ":" + "bossspawner"));
		GameRegistry.registerTileEntity(TATileEntity_Aurorian_Furnace.class, new ResourceLocation(TAMod.MODID + ":" + TABlock_Furnace.BLOCKNAME));
	}

}
