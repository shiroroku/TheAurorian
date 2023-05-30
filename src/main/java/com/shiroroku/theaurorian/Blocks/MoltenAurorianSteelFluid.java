package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class MoltenAurorianSteelFluid extends Fluid {

	public static final String FLUIDNAME = "tamoltenauroriansteel";
	private static final ResourceLocation textureStill = new ResourceLocation(AurorianMod.MODID, "blocks/moltenauroriansteel");
	private static final ResourceLocation textureFlowing = new ResourceLocation(AurorianMod.MODID, "blocks/moltenauroriansteelflow");

	public MoltenAurorianSteelFluid() {
		super(FLUIDNAME, textureStill, textureFlowing);
		this.setDensity(2000);
		this.setViscosity(10000);
		this.setTemperature(800);
		this.setLuminosity(10);
	}

}
