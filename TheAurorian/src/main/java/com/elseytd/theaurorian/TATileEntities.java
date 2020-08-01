package com.elseytd.theaurorian;

import com.elseytd.theaurorian.Blocks.TABlock_CrystalBlock;
import com.elseytd.theaurorian.Blocks.TABlock_Furnace;
import com.elseytd.theaurorian.Blocks.TABlock_MoonLightForge;
import com.elseytd.theaurorian.TileEntities.AurorianFurnace_TileEntity;
import com.elseytd.theaurorian.TileEntities.BossSpawner_TileEntity;
import com.elseytd.theaurorian.TileEntities.CrystalBlock_TileEntity;
import com.elseytd.theaurorian.TileEntities.MoonLightForge_TileEntity;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TATileEntities {

	public static void registerTileEntities(RegistryEvent.Register<Block> event) {
		GameRegistry.registerTileEntity(BossSpawner_TileEntity.class, new ResourceLocation(TAMod.MODID + ":" + "bossspawner"));
		GameRegistry.registerTileEntity(AurorianFurnace_TileEntity.class, new ResourceLocation(TAMod.MODID + ":" + TABlock_Furnace.BLOCKNAME));
		GameRegistry.registerTileEntity(MoonLightForge_TileEntity.class, new ResourceLocation(TAMod.MODID + ":" + TABlock_MoonLightForge.BLOCKNAME));
		GameRegistry.registerTileEntity(CrystalBlock_TileEntity.class, new ResourceLocation(TAMod.MODID + ":" + TABlock_CrystalBlock.BLOCKNAME));
	}

}
