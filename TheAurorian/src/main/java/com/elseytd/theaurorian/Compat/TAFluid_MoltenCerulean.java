package com.elseytd.theaurorian.Compat;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class TAFluid_MoltenCerulean extends Fluid {

	public static final String FLUIDNAME = "tamoltencerulean";
	private static final ResourceLocation textureStill = new ResourceLocation(TAMod.MODID, "blocks/moltencerulean");
	private static final ResourceLocation textureFlowing = new ResourceLocation(TAMod.MODID, "blocks/moltenceruleanflow");

	public TAFluid_MoltenCerulean() {
		super(FLUIDNAME, textureStill, textureFlowing);
		this.setDensity(2000);
		this.setViscosity(10000);
		this.setTemperature(800);
		this.setLuminosity(10);
	}

}
