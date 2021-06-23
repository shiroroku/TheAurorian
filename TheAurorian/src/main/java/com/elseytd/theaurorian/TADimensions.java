package com.elseytd.theaurorian;

import com.elseytd.theaurorian.World.TAWorldProvider;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class TADimensions {
	public static DimensionType theauroriandimension;
	public static int DIMENSION_ID = TAConfig.Config_AurorianDimID;

	public static void init() {
		registerDimensionTypes();
		registerDimensions();
	}

	private static void registerDimensionTypes() {
		theauroriandimension = DimensionType.register(TAMod.MODID, "_aurorian", DIMENSION_ID, TAWorldProvider.class, false);
	}

	private static void registerDimensions() {
		DimensionManager.registerDimension(DIMENSION_ID, theauroriandimension);
	}
}
