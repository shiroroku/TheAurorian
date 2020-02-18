package com.elseytd.theaurorian.World.Structures;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAConfig;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TAUtil;
import com.elseytd.theaurorian.Misc.GenerationHelper;

import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
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
		boolean gen = false;

		if (TAUtil.randomChanceOf(0.01D) && TAUtil.randomChanceOf(0.2D)) {
			BlockPos pos = worldIn.getHeight(position.add(8, 128, 8)).up();
			if (GenerationHelper.isTerrainFlat(worldIn, position, 8)) {
				gen = generateUmbraTower(worldIn, pos);
			}
			if (gen) {
				Chunk c = worldIn.getChunkFromBlockCoords(pos);
				Chunk c1 = worldIn.getChunkFromChunkCoords(c.x + 1, c.z + 1);
				Chunk c2 = worldIn.getChunkFromChunkCoords(c.x + 1, c.z);
				Chunk c3 = worldIn.getChunkFromChunkCoords(c.x, c.z + 1);

				GenerationHelper.populateChestsInChunk(c, rand, LOOT);
				GenerationHelper.populateChestsInChunk(c1, rand, LOOT);
				GenerationHelper.populateChestsInChunk(c2, rand, LOOT);
				GenerationHelper.populateChestsInChunk(c3, rand, LOOT);
			}
		}
		return true;
	}

	public boolean generateUmbraTower(World world, BlockPos position) {
		while (world.getBlockState(position).getBlock() != TABlocks.auroriangrass && position.getY() >= 40) {
			position = position.down();
		}

		if (position.getY() <= 50) {
			return false;
		}
		position = position.up(3);

		if (GenerationHelper.isNearStructure(new TAWorldGenerator_Runestone_Tower(), world, position, 64, TAConfig.Config_DungeonDensity * 2) || GenerationHelper.isNearStructure(new TAWorldGenerator_Runestone_Tower(), world, position, 128, TAConfig.Config_DungeonDensity * 4)) {
			return false;
		}

		final Template tower = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), TOWER);
		tower.addBlocksToWorld(world, position, new PlacementSettings().setRotation(Rotation.NONE).setReplacedBlock(TABlocks.aurorianstone));

		final Template terrain = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), TOWERTERRAIN);
		for (int i = 1; i <= 16; i++) {
			terrain.addBlocksToWorld(world, position.down(i), new PlacementSettings().setRotation(Rotation.NONE).setReplacedBlock(Blocks.AIR));
		}
		return true;
	}

}
