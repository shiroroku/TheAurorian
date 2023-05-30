package com.shiroroku.theaurorian.Registry;

import com.shiroroku.theaurorian.TileEntities.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIRegistry implements IGuiHandler {

    public static final int SILENTWOODCRAFTINGTABLE = 1;
    public static final int AURORIANFURNACE = 2;
    public static final int MOONLIGHTFORGE = 3;
    public static final int SCRAPPER = 4;

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (id) {
            case SILENTWOODCRAFTINGTABLE:
                return new SilentwoodWorkbenchContainer(player.inventory, world, new BlockPos(x, y, z));
            case AURORIANFURNACE:
                return new AurorianFurnaceContainer(player.inventory, (AurorianFurnaceTileEntity) world.getTileEntity(new BlockPos(x, y, z)));
            case MOONLIGHTFORGE:
                return new MoonLightForgeContainer(player.inventory, (MoonLightForgeTileEntity) world.getTileEntity(new BlockPos(x, y, z)));
            case SCRAPPER:
                return new ScrapperContainer(player.inventory, (ScrapperTileEntity) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (id) {
            case SILENTWOODCRAFTINGTABLE:
                return new SilentwoodWorkbenchGui(player.inventory, world, new BlockPos(x, y, z));
            case AURORIANFURNACE:
                return new AurorianFurnaceGui(player.inventory, (AurorianFurnaceTileEntity) world.getTileEntity(new BlockPos(x, y, z)));
            case MOONLIGHTFORGE:
                return new MoonLightForgeGui(player.inventory, (MoonLightForgeTileEntity) world.getTileEntity(new BlockPos(x, y, z)));
            case SCRAPPER:
                return new ScrapperGui(player.inventory, (ScrapperTileEntity) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }
}
