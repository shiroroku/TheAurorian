package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class MoltenMoonstoneFluid extends Fluid {

	public static final String FLUIDNAME = "tamoltenmoonstone";
	private static final ResourceLocation textureStill = new ResourceLocation(AurorianMod.MODID, "blocks/moltenmoonstone");
	private static final ResourceLocation textureFlowing = new ResourceLocation(AurorianMod.MODID, "blocks/moltenmoonstoneflow");

	public MoltenMoonstoneFluid() {
		super(FLUIDNAME, textureStill, textureFlowing);
		this.setDensity(2000);
		this.setViscosity(10000);
		this.setTemperature(800);
		this.setLuminosity(10);
	}

}
