package com.elseytd.theaurorian.World.Feature;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TAUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class TAWorldGenerator_Trees_WeepingWillow extends WorldGenAbstractTree {

	private static final ResourceLocation WILLOW_SMALL_1 = new ResourceLocation(TAMod.MODID, "weepingwillow/willow_s1");
	private static final ResourceLocation WILLOW_SMALL_2 = new ResourceLocation(TAMod.MODID, "weepingwillow/willow_s2");
	private static final ResourceLocation WILLOW_SMALL_3 = new ResourceLocation(TAMod.MODID, "weepingwillow/willow_s3");
	private static final ResourceLocation WILLOW_LARGE_1 = new ResourceLocation(TAMod.MODID, "weepingwillow/willow_l1");
	private static final ResourceLocation WILLOW_LARGE_2 = new ResourceLocation(TAMod.MODID, "weepingwillow/willow_l2");

	public TAWorldGenerator_Trees_WeepingWillow(boolean notify) {
		super(notify);
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if (TAUtil.randomChanceOf(0.30D)) {
			return false;
		}

		boolean isSmall = TAUtil.randomChanceOf(0.75D);

		for (int i = 0; i <= 4; i++) {
			if (!worldIn.isAirBlock(position.up(i))) {
				return false;
			}
		}

		IBlockState state = worldIn.getBlockState(position.down());
		if (state.getBlock() == TABlocks.auroriangrasslight) {
			state.getBlock().onPlantGrow(state, worldIn, position.down(), position);
		} else {
			return false;
		}

		if (isSmall) {
			final PlacementSettings settings = new PlacementSettings().setRotation(getRandomRotation(rand)).setReplacedBlock(Blocks.AIR);
			ResourceLocation template = getRandomResource(rand, new ResourceLocation[] { WILLOW_SMALL_1, WILLOW_SMALL_2, WILLOW_SMALL_3 });
			final Template willow = worldIn.getSaveHandler().getStructureTemplateManager().getTemplate(worldIn.getMinecraftServer(), template);
			BlockPos p = getPositionForPlacement(position, settings.getRotation(), 3);
			willow.addBlocksToWorld(worldIn, p, settings);
		} else {
			final PlacementSettings settings = new PlacementSettings().setRotation(getRandomRotation(rand)).setReplacedBlock(Blocks.AIR);
			ResourceLocation template = getRandomResource(rand, new ResourceLocation[] { WILLOW_LARGE_1, WILLOW_LARGE_2 });
			final Template willow = worldIn.getSaveHandler().getStructureTemplateManager().getTemplate(worldIn.getMinecraftServer(), template);
			BlockPos p = getPositionForPlacement(position, settings.getRotation(), 4);
			willow.addBlocksToWorld(worldIn, p, settings);
		}

		return true;
	}

	private ResourceLocation getRandomResource(Random rand, ResourceLocation[] possible) {
		return possible[rand.nextInt(possible.length)];
	}

	private Rotation getRandomRotation(Random rand) {
		switch (rand.nextInt(4)) {
		default:
		case 0:
			return Rotation.NONE;
		case 1:
			return Rotation.CLOCKWISE_90;
		case 2:
			return Rotation.CLOCKWISE_180;
		case 3:
			return Rotation.COUNTERCLOCKWISE_90;
		}
	}

	private BlockPos getPositionForPlacement(BlockPos position, Rotation r, int offset) {
		BlockPos p;
		switch (r) {
		default:
		case NONE:
			p = position.add(-offset, 0, -offset);
			break;
		case CLOCKWISE_90:
			p = position.add(offset, 0, -offset);
			break;
		case CLOCKWISE_180:
			p = position.add(offset, 0, offset);
			break;
		case COUNTERCLOCKWISE_90:
			p = position.add(-offset, 0, offset);
			break;
		}
		return p;
	}
}
