package com.shiroroku.theaurorian.World.Feature;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.AurorianUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class MushroomWorldGenerator extends WorldGenAbstractTree {

	public MushroomWorldGenerator(boolean notify) {
		super(notify);
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		int height = 5 - rand.nextInt(3);
		int capwidth = 5;

		//Height check.
		for (int i = 0; i <= height; i++) {
			if (!worldIn.isAirBlock(position.up(i))) {
				return false;
			}
		}

		//Generate stem.
		for (int i = 0; i < height; i++) {
			BlockPos p = position.up(i);
			if (worldIn.isAirBlock(p)) {
				worldIn.setBlockState(position.up(i), BlockRegistry.Registry.MUSHROOMSTEM.getBlock().getDefaultState());
			}
		}

		//Generate topmost cap layer.
		int w2 = capwidth - 2;
		for (int x = 0; x < w2; x++) {
			for (int z = 0; z < w2; z++) {
				BlockPos p = position.add(x - w2 / 2, height, z - w2 / 2);
				if (worldIn.isAirBlock(p)) {
					worldIn.setBlockState(p, BlockRegistry.Registry.MUSHROOM.getBlock().getDefaultState());
				}
			}
		}

		//Generate second topmost cap layer, hollow, and without corners.
		for (int x = 0; x < capwidth; x++) {
			for (int z = 0; z < capwidth; z++) {
				BlockPos p = position.add(x - capwidth / 2, height - 1, z - capwidth / 2);
				if (worldIn.isAirBlock(p) && (x == 0 || z == 0 || x == capwidth - 1 || z == capwidth - 1)) {
					if (!((x == 0 && z == 0) || (x == capwidth - 1 && z == capwidth - 1) || (x == 0 && z == capwidth - 1) || (x == capwidth - 1 && z == 0))) {
						worldIn.setBlockState(p, BlockRegistry.Registry.MUSHROOM.getBlock().getDefaultState());
					}
				}
			}
		}

		//Add Crystals
		for (int x = 0; x < w2; x++) {
			for (int z = 0; z < w2; z++) {
				BlockPos p = position.add(x - w2 / 2, height - 1, z - w2 / 2);
				if (worldIn.isAirBlock(p) && AurorianUtil.randomChanceOf(0.25D)) {
					worldIn.setBlockState(p, BlockRegistry.Registry.MUSHROOMCRYSTAL.getBlock().getDefaultState());
					return true;
				}
			}
		}

		return true;
	}

}
