package com.elseytd.theaurorian.Registry;

import com.elseytd.theaurorian.AurorianMod;
import com.elseytd.theaurorian.AurorianConfig;
import com.elseytd.theaurorian.World.AurorianWorldProvider;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class DimensionRegistry {
	public static DimensionType theauroriandimension;
	public static int DIMENSION_ID = AurorianConfig.Config_AurorianDimID;

	public static void init() {
		registerDimensionTypes();
		registerDimensions();
	}

	private static void registerDimensionTypes() {
		theauroriandimension = DimensionType.register(AurorianMod.MODID, "_aurorian", DIMENSION_ID, AurorianWorldProvider.class, false);
	}

	private static void registerDimensions() {
		DimensionManager.registerDimension(DIMENSION_ID, theauroriandimension);
	}
}
