package com.shiroroku.theaurorian.Registry;

import com.shiroroku.theaurorian.AurorianMod;
import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.World.AurorianWorldProvider;
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
