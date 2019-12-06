package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TABlocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TABlockFluid_Moonwater extends BlockFluidClassic {

	public TABlockFluid_Moonwater() {
		super(TABlocks.Fluids.MOONWATER, Material.WATER);
		this.canCreateSources = true;
		this.setRegistryName(TAFluid_Moonwater.FLUIDNAME);
		this.setUnlocalizedName(TAFluid_Moonwater.FLUIDNAME);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
		ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(LEVEL).build());
	}

}
