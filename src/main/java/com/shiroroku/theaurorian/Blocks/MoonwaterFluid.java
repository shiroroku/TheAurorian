package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class MoonwaterFluid extends Fluid {

	public static final String FLUIDNAME = "tamoonwater";
	private static final ResourceLocation textureStill = new ResourceLocation(AurorianMod.MODID, "blocks/moonwater");
	private static final ResourceLocation textureFlowing = new ResourceLocation(AurorianMod.MODID, "blocks/moonwaterflowing");

	public MoonwaterFluid() {
		super(FLUIDNAME, textureStill, textureFlowing);
	}

}
