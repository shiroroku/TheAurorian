package com.elseytd.theaurorian.World.Structures;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TAUtil;
import com.elseytd.theaurorian.Misc.GenerationHelper;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
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
		boolean gen = false;

		if (TAUtil.randomChanceOf(RUINS_1_CHANCE)) {
			int i = rand.nextInt(2) + 4;
			int j = rand.nextInt(2) + 4;
			BlockPos pos = worldIn.getHeight(position.add(i, 128, j)).up();
			if (GenerationHelper.isTerrainFlat(worldIn, position, 3)) {
				generateRuins(worldIn, pos);
				gen = true;
			}
		}
		if (TAUtil.randomChanceOf(RUINS_2_CHANCE)) {
			int i = rand.nextInt(2) + 4;
			int j = rand.nextInt(2) + 4;
			int k = rand.nextInt(50) + 4;
			BlockPos pos = worldIn.getHeight(position.add(i, 0, j));
			generateRuinsUnderground(worldIn, pos, k);
			gen = true;
		}

		if (gen) {
			GenerationHelper.populateChestsInChunk(worldIn.getChunkFromBlockCoords(position), rand, RUIN_LOOTTABLE);
		}
		return true;
	}

	public void generateRuins(World world, BlockPos position) {
		while (world.getBlockState(position).getBlock() != TABlocks.auroriangrass && position.getY() >= 5) {
			position = position.down();
		}

		final PlacementSettings settings = new PlacementSettings().setRotation(Rotation.NONE).setReplacedBlock(TABlocks.aurorianstone);
		final Template ruins1 = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), RUINS_1);
		ruins1.addBlocksToWorld(world, position, settings);
	}

	public void generateRuinsUnderground(World world, BlockPos position, int height) {
		BlockPos pos = new BlockPos(position.getX(), height, position.getZ());

		final PlacementSettings settings = new PlacementSettings().setRotation(Rotation.NONE).setReplacedBlock(TABlocks.aurorianstone);
		final Template ruins2 = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), RUINS_2);
		ruins2.addBlocksToWorld(world, pos, settings);
	}
}
