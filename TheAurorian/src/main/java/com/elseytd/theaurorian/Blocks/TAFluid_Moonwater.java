package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class TAFluid_Moonwater extends Fluid {

	public static final String FLUIDNAME = "tamoonwater";
	private static final ResourceLocation textureStill = new ResourceLocation(TAMod.MODID, "blocks/moonwater");
	private static final ResourceLocation textureFlowing = new ResourceLocation(TAMod.MODID, "blocks/moonwaterflowing");

	public TAFluid_Moonwater() {
		super(FLUIDNAME, textureStill, textureFlowing);
	}

}
