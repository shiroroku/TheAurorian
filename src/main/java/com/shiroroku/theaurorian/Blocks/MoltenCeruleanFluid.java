package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class MoltenCeruleanFluid extends Fluid {

	public static final String FLUIDNAME = "tamoltencerulean";
	private static final ResourceLocation textureStill = new ResourceLocation(AurorianMod.MODID, "blocks/moltencerulean");
	private static final ResourceLocation textureFlowing = new ResourceLocation(AurorianMod.MODID, "blocks/moltenceruleanflow");

	public MoltenCeruleanFluid() {
		super(FLUIDNAME, textureStill, textureFlowing);
		this.setDensity(2000);
		this.setViscosity(10000);
		this.setTemperature(800);
		this.setLuminosity(10);
	}

}
