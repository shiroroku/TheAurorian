package com.fluke.worleycaves.config;

public class Configs {
	public static ConfigCaveGen cavegen = new ConfigCaveGen();

	public static class ConfigCaveGen {
		//Controls size of caves. Smaller values = larger caves. Between -1.0 and 1.0", "Default: -0.18"
		public double noiseCutoffValue = -0.18;

		//Controls size of caves at the surface. Smaller values = more caves break through the surface. Between -1.0 and 1.0", "Default: -0.081 (45% of noiseCutoffValue)"
		public double surfaceCutoffValue = -0.081;

		//Controls how much to warp caves. Lower values = straighter caves", "Default: 8.0"
		public double warpAmplifier = 6.0;

		//Reduces number of caves at surface level, becoming more common until caves generate normally X number of blocks below the surface", "Default: 15"
		public int easeInDepth = 15;

		//Squishes caves on the Y axis. Lower values = taller caves and more steep drops", "Default: 2.0"
		public double verticalCompressionMultiplier = 2.0;

		//Streches (when > 1.0) or compresses (when < 1.0) cave generation along X and Z axis", "Default: 1.0"
		public double horizonalCompressionMultiplier = 1.0;

		//Dimension IDs that will use Vanilla cave generation rather than Worley's Caves", "Default:"
		public int[] blackListedDims = {};

		//Caves will not attempt to generate above this y level. Range 1-256", "Default: 128"
		public int maxCaveHeight = 60;

		//Caves will not attempt to generate below this y level. Range 1-256", "Default: 1"
		public int minCaveHeight = 1;

		//Block to use when generating large lava lakes below lavaDepth (usually y=10)", "Default: minecraft:lava"
		public String lavaBlock = "minecraft:lava";

		//Air blocks at or below this y level will generate as lavaBlock", "Default: 10"}
		public int lavaDepth = 10;

		//Allow replacing more blocks with caves (useful for mods which completely overwrite world gen)
		public boolean allowReplaceMoreBlocks = true;
	}
}
