package com.elseytd.theaurorian.World.Structures;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TAUtil;
import com.elseytd.theaurorian.Util.GenerationHelper;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class TAWorldGenerator_Ruins extends WorldGenerator {

	private static final ResourceLocation RUIN_LOOTTABLE = new ResourceLocation(TAMod.MODID, "chests/ruins");

	private static final ResourceLocation RUINS_1 = new ResourceLocation(TAMod.MODID, "ruins/ruins_1");
	private static final ResourceLocation RUINS_2 = new ResourceLocation(TAMod.MODID, "ruins/ruins_2");

	public static boolean GENERATE_RUINS = TAConfig.Config_GenerateRuins;
	public static final float RUINS_1_CHANCE = 0.01F;
	public static final float RUINS_2_CHANCE = 0.02F;

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if (TAUtil.randomChanceOf(RUINS_1_CHANCE)) {
			final int i = rand.nextInt(2) + 4;
			final int j = rand.nextInt(2) + 4;
			BlockPos pos = worldIn.getHeight(position.add(i, 128, j)).up();
			if (GenerationHelper.isTerrainFlat(worldIn, position, 3)) {
				this.generateRuins(worldIn, pos);
			}
		}
		if (TAUtil.randomChanceOf(RUINS_2_CHANCE)) {
			final int i = rand.nextInt(2) + 4;
			final int j = rand.nextInt(2) + 4;
			final int k = rand.nextInt(50) + 4;
			BlockPos pos = worldIn.getHeight(position.add(i, 0, j));
			this.generateRuinsUnderground(worldIn, pos, k);
		}

		return true;
	}

	public void generateRuins(World world, BlockPos position) {
		while (world.getBlockState(position).getBlock() != TABlocks.auroriangrass && position.getY() >= 5) {
			position = position.down();
		}

		final PlacementSettings settings = new PlacementSettings().setReplacedBlock(TABlocks.aurorianstone);
		GenerationHelper.getTemplate(world, RUINS_1).addBlocksToWorld(world, position, settings);
	}

	public void generateRuinsUnderground(World world, BlockPos position, int height) {
		final BlockPos pos = new BlockPos(position.getX(), height, position.getZ());

		final PlacementSettings settings = new PlacementSettings().setReplacedBlock(TABlocks.aurorianstone);
		final Template ruins2 = GenerationHelper.getTemplate(world, RUINS_2);
		ruins2.addBlocksToWorld(world, pos, settings);

		GenerationHelper.populateChestsInTemplate(world, pos, ruins2, settings, "chest", RUIN_LOOTTABLE);
	}
}
