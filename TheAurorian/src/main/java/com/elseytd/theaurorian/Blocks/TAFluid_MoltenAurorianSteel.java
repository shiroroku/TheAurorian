package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class TAFluid_MoltenAurorianSteel extends Fluid {

	public static final String FLUIDNAME = "tamoltenauroriansteel";
	private static final ResourceLocation textureStill = new ResourceLocation(TAMod.MODID, "blocks/moltenauroriansteel");
	private static final ResourceLocation textureFlowing = new ResourceLocation(TAMod.MODID, "blocks/moltenauroriansteelflow");

	public TAFluid_MoltenAurorianSteel() {
		super(FLUIDNAME, textureStill, textureFlowing);
		this.setDensity(2000);
		this.setViscosity(10000);
		this.setTemperature(800);
		this.setLuminosity(10);
	}

}
