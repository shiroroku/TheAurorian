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

public class TAWorldGenerator_Graveyard extends WorldGenerator {

	private static final ResourceLocation LOOT = new ResourceLocation(TAMod.MODID, "chests/ruins");

	private static final ResourceLocation GRAVEYARD = new ResourceLocation(TAMod.MODID, "ruins/graveyard");

	public static boolean GENERATE_GRAVEYARDS = TAConfig.Config_GenerateGraveyards;

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if (TAUtil.randomChanceOf(0.01D)) {
			BlockPos pos = worldIn.getHeight(position.add(8, 128, 8)).up();
			if (GenerationHelper.isTerrainFlat(worldIn, position, 6)) {
				this.generateGraveyard(worldIn, pos);
			}
		}
		return true;
	}

	public void generateGraveyard(World world, BlockPos position) {
		while (world.getBlockState(position).getBlock() != TABlocks.Registry.AURORIANGRASS.getBlock() && position.getY() >= 40) {
			position = position.down();
		}

		if (position.getY() <= 50) {
			return;
		}

		position = position.down(3);

		if (GenerationHelper.isNearStructure(new TAWorldGenerator_Runestone_Tower(), world, position, 64, TAConfig.Config_DungeonDensity * 2) || GenerationHelper.isNearStructure(new TAWorldGenerator_Runestone_Tower(), world, position, 128, TAConfig.Config_DungeonDensity * 4)) {
			return;
		}

		final Template template = GenerationHelper.getTemplate(world, GRAVEYARD);
		final PlacementSettings settings = new PlacementSettings().setReplacedBlock(TABlocks.Registry.AURORIANSTONE.getBlock());
		template.addBlocksToWorld(world, position, settings);
		GenerationHelper.populateChestsInTemplate(world, position, template, settings, "chest", LOOT);


	}

}
