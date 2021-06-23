package com.elseytd.theaurorian;

import com.elseytd.theaurorian.TileEntities.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class TAGuis implements IGuiHandler {

	public static final int SILENTWOODCRAFTINGTABLE = 1;
	public static final int AURORIANFURNACE = 2;
	public static final int MOONLIGHTFORGE = 3;
	public static final int SCRAPPER = 4;

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch (id) {
		case SILENTWOODCRAFTINGTABLE:
			return new SilentwoodWorkbench_Container(player.inventory, world, new BlockPos(x, y, z));
		case AURORIANFURNACE:
			return new AurorianFurnace_Container(player.inventory, (AurorianFurnace_TileEntity) world.getTileEntity(new BlockPos(x, y, z)));
		case MOONLIGHTFORGE:
			return new MoonLightForge_Container(player.inventory, (MoonLightForge_TileEntity) world.getTileEntity(new BlockPos(x, y, z)));
		case SCRAPPER:
			return new Scrapper_Container(player.inventory, (Scrapper_TileEntity) world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch (id) {
		case SILENTWOODCRAFTINGTABLE:
			return new SilentwoodWorkbench_Gui(player.inventory, world, new BlockPos(x, y, z));
		case AURORIANFURNACE:
			return new AurorianFurnace_Gui(player.inventory, (AurorianFurnace_TileEntity) world.getTileEntity(new BlockPos(x, y, z)));
		case MOONLIGHTFORGE:
			return new MoonLightForge_Gui(player.inventory, (MoonLightForge_TileEntity) world.getTileEntity(new BlockPos(x, y, z)));
		case SCRAPPER:
			return new Scrapper_Gui(player.inventory, (Scrapper_TileEntity) world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}
}
