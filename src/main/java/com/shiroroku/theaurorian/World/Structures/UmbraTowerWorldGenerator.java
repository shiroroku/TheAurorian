package com.shiroroku.theaurorian.World.Structures;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.AurorianMod;
import com.shiroroku.theaurorian.AurorianUtil;
import com.shiroroku.theaurorian.Util.GenerationHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

import java.util.Random;

public class UmbraTowerWorldGenerator extends WorldGenerator {

	private static final ResourceLocation LOOT = new ResourceLocation(AurorianMod.MODID, "chests/ruins");

	private static final ResourceLocation TOWER = new ResourceLocation(AurorianMod.MODID, "umbratower/umbratower");
	private static final ResourceLocation TOWERTERRAIN = new ResourceLocation(AurorianMod.MODID, "umbratower/umbratowerterrain");

	public static boolean GENERATE_TOWERS = AurorianConfig.Config_GenerateUmbraTower;

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		//this needs to be changed ._.
		if (AurorianUtil.randomChanceOf(0.01D) && AurorianUtil.randomChanceOf(0.2D)) {
			BlockPos pos = worldIn.getHeight(position.add(8, 128, 8)).up();
			if (GenerationHelper.isTerrainFlat(worldIn, position, 8)) {
				this.generateUmbraTower(worldIn, pos);
			}
		}
		return true;
	}

	public void generateUmbraTower(World world, BlockPos position) {
		while (world.getBlockState(position).getBlock() != BlockRegistry.Registry.AURORIANGRASS.getBlock() && position.getY() >= 40) {
			position = position.down();
		}

		if (position.getY() <= 50) {
			return;
		}

		position = position.up(3);

		if (GenerationHelper.isNearStructure(new RunestoneTowerWorldGenerator(), world, position, 64, AurorianConfig.Config_DungeonDensity * 2) || GenerationHelper.isNearStructure(new RunestoneTowerWorldGenerator(), world, position, 128, AurorianConfig.Config_DungeonDensity * 4)) {
			return;
		}

		final Template tower = GenerationHelper.getTemplate(world, TOWER);
		final PlacementSettings settings = new PlacementSettings().setReplacedBlock(BlockRegistry.Registry.AURORIANSTONE.getBlock());
		tower.addBlocksToWorld(world, position, settings);
		GenerationHelper.populateChestsInTemplate(world, position, tower, settings, "chest", LOOT);

		final Template terrain = GenerationHelper.getTemplate(world, TOWERTERRAIN);
		for (int i = 1; i <= 16; i++) {
			terrain.addBlocksToWorld(world, position.down(i), new PlacementSettings().setReplacedBlock(Blocks.AIR));
		}

	}

}
