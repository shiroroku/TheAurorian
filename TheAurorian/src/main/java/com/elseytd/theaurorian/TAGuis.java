package com.elseytd.theaurorian;

import com.elseytd.theaurorian.TileEntities.Furnace.TAContainer_Aurorian_Furnace;
import com.elseytd.theaurorian.TileEntities.Furnace.TAGui_Aurorian_Furnace;
import com.elseytd.theaurorian.TileEntities.Furnace.TATileEntity_Aurorian_Furnace;
import com.elseytd.theaurorian.TileEntities.Workbench.TAContainer_Silentwood_Workbench;
import com.elseytd.theaurorian.TileEntities.Workbench.TAGui_Silentwood_Workbench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class TAGuis implements IGuiHandler {

	public static final int SILENTWOODCRAFTINGTABLE = 1;
	public static final int AURORIANFURNACE = 2;

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch (id) {
		case SILENTWOODCRAFTINGTABLE:
			return new TAContainer_Silentwood_Workbench(player.inventory, world, new BlockPos(x, y, z));
		case AURORIANFURNACE:
			return new TAContainer_Aurorian_Furnace(player.inventory, (TATileEntity_Aurorian_Furnace) world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch (id) {
		case SILENTWOODCRAFTINGTABLE:
			return new TAGui_Silentwood_Workbench(player.inventory, world, new BlockPos(x, y, z));
		case AURORIANFURNACE:
			return new TAGui_Aurorian_Furnace(player.inventory, (TATileEntity_Aurorian_Furnace) world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}
}
