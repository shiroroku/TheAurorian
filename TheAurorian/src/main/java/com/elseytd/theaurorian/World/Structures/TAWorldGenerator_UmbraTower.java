package com.elseytd.theaurorian.World.Structures;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TAUtil;
import com.elseytd.theaurorian.Util.GenerationHelper;

import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class TAWorldGenerator_UmbraTower extends WorldGenerator {

	private static final ResourceLocation LOOT = new ResourceLocation(TAMod.MODID, "chests/ruins");

	private static final ResourceLocation TOWER = new ResourceLocation(TAMod.MODID, "umbratower/umbratower");
	private static final ResourceLocation TOWERTERRAIN = new ResourceLocation(TAMod.MODID, "umbratower/umbratowerterrain");

	public static boolean GENERATE_TOWERS = TAConfig.Config_GenerateUmbraTower;

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		//this needs to be changed ._.
		if (TAUtil.randomChanceOf(0.01D) && TAUtil.randomChanceOf(0.2D)) {
			BlockPos pos = worldIn.getHeight(position.add(8, 128, 8)).up();
			if (GenerationHelper.isTerrainFlat(worldIn, position, 8)) {
				this.generateUmbraTower(worldIn, pos);
			}
		}
		return true;
	}

	public void generateUmbraTower(World world, BlockPos position) {
		while (world.getBlockState(position).getBlock() != TABlocks.auroriangrass && position.getY() >= 40) {
			position = position.down();
		}

		if (position.getY() <= 50) {
			return;
		}

		position = position.up(3);

		if (GenerationHelper.isNearStructure(new TAWorldGenerator_Runestone_Tower(), world, position, 64, TAConfig.Config_DungeonDensity * 2) || GenerationHelper.isNearStructure(new TAWorldGenerator_Runestone_Tower(), world, position, 128, TAConfig.Config_DungeonDensity * 4)) {
			return;
		}

		final Template tower = GenerationHelper.getTemplate(world, TOWER);
		final PlacementSettings settings = new PlacementSettings().setReplacedBlock(TABlocks.aurorianstone);
		tower.addBlocksToWorld(world, position, settings);
		GenerationHelper.populateChestsInTemplate(world, position, tower, settings, "chest", LOOT);

		final Template terrain = GenerationHelper.getTemplate(world, TOWERTERRAIN);
		for (int i = 1; i <= 16; i++) {
			terrain.addBlocksToWorld(world, position.down(i), new PlacementSettings().setReplacedBlock(Blocks.AIR));
		}

	}

}
