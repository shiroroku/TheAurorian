package com.shiroroku.theaurorian.World.Structures;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.AurorianMod;
import com.shiroroku.theaurorian.AurorianUtil;
import com.shiroroku.theaurorian.Util.GenerationHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

import java.util.Random;

public class GraveyardWorldGenerator extends WorldGenerator {

	private static final ResourceLocation LOOT = new ResourceLocation(AurorianMod.MODID, "chests/ruins");

	private static final ResourceLocation GRAVEYARD = new ResourceLocation(AurorianMod.MODID, "ruins/graveyard");

	public static boolean GENERATE_GRAVEYARDS = AurorianConfig.Config_GenerateGraveyards;

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if (AurorianUtil.randomChanceOf(0.01D)) {
			BlockPos pos = worldIn.getHeight(position.add(8, 128, 8)).up();
			if (GenerationHelper.isTerrainFlat(worldIn, position, 6)) {
				this.generateGraveyard(worldIn, pos);
			}
		}
		return true;
	}

	public void generateGraveyard(World world, BlockPos position) {
		while (world.getBlockState(position).getBlock() != BlockRegistry.Registry.AURORIANGRASS.getBlock() && position.getY() >= 40) {
			position = position.down();
		}

		if (position.getY() <= 50) {
			return;
		}

		position = position.down(3);

		if (GenerationHelper.isNearStructure(new RunestoneTowerWorldGenerator(), world, position, 64, AurorianConfig.Config_DungeonDensity * 2) || GenerationHelper.isNearStructure(new RunestoneTowerWorldGenerator(), world, position, 128, AurorianConfig.Config_DungeonDensity * 4)) {
			return;
		}

		final Template template = GenerationHelper.getTemplate(world, GRAVEYARD);
		final PlacementSettings settings = new PlacementSettings().setReplacedBlock(BlockRegistry.Registry.AURORIANSTONE.getBlock());
		template.addBlocksToWorld(world, position, settings);
		GenerationHelper.populateChestsInTemplate(world, position, template, settings, "chest", LOOT);


	}

}
