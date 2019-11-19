package com.elseytd.theaurorian.World;

import java.util.Random;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TADimensions;
import com.elseytd.theaurorian.Blocks.TABlock_Portal;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeHooks;

public class TATeleporter extends Teleporter {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private final Long2ObjectMap<Teleporter.PortalPosition> destinationCoordinateCache = new Long2ObjectOpenHashMap(4096);

	private final Random random;
	private final WorldServer WSInstance;

	public TATeleporter(WorldServer worldIn) {
		super(worldIn);
		this.random = new Random(worldIn.getSeed());
		this.WSInstance = worldIn;
	}

	public static void transferEntity(Entity entity, int dimto) {
		Teleporter teleporter = new TATeleporter(entity.getServer().getWorld(dimto));
		if (entity instanceof EntityPlayerMP) {
			if (!ForgeHooks.onTravelToDimension(entity, TADimensions.DIMENSION_ID))
				return;

			((EntityPlayerMP) entity).mcServer.getPlayerList().transferPlayerToDimension((EntityPlayerMP) entity, dimto, teleporter);
		} else
			entity.changeDimension(dimto, teleporter);
	}

	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw) {
		if (!placeInExistingPortal(entityIn, rotationYaw)) {
			makePortal(entityIn);
			placeInExistingPortal(entityIn, rotationYaw);
		}
	}

	public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
		double d0 = -1.0D;
		int i = MathHelper.floor(entityIn.posX);
		int j = MathHelper.floor(entityIn.posZ);
		boolean flag1 = true;
		Object object = BlockPos.ORIGIN;
		long k = ChunkPos.asLong(i, j);

		if (destinationCoordinateCache.containsKey(k)) {
			Teleporter.PortalPosition portalposition = destinationCoordinateCache.get(k);
			d0 = 0.0D;
			object = portalposition;
			portalposition.lastUpdateTime = WSInstance.getTotalWorldTime();
			flag1 = false;
		} else {
			BlockPos blockpos4 = new BlockPos(entityIn);

			for (int l = -128; l <= 128; ++l) {
				BlockPos blockpos1;

				for (int i1 = -128; i1 <= 128; ++i1)
					for (BlockPos blockpos = blockpos4.add(l, WSInstance.getActualHeight() - 1 - blockpos4.getY(), i1); blockpos.getY() >= 0; blockpos = blockpos1) {
						blockpos1 = blockpos.down();

						if (WSInstance.getBlockState(blockpos).getBlock() == TABlocks.aurorianportal) {
							while (WSInstance.getBlockState(blockpos1 = blockpos.down()).getBlock() == TABlocks.aurorianportal)
								blockpos = blockpos1;

							double d1 = blockpos.distanceSq(blockpos4);

							if (d0 < 0.0D || d1 < d0) {
								d0 = d1;
								object = blockpos;
							}
						}
					}
			}
		}

		if (d0 >= 0.0D) {
			if (flag1)
				destinationCoordinateCache.put(k, new Teleporter.PortalPosition((BlockPos) object, WSInstance.getTotalWorldTime()));

			double d4 = ((BlockPos) object).getX() + 0.5D;
			double d5 = ((BlockPos) object).getY() + 0.5D;
			double d6 = ((BlockPos) object).getZ() + 0.5D;
			EnumFacing enumfacing = null;

			if (WSInstance.getBlockState(((BlockPos) object).west()).getBlock() == TABlocks.aurorianportal)
				enumfacing = EnumFacing.NORTH;

			if (WSInstance.getBlockState(((BlockPos) object).east()).getBlock() == TABlocks.aurorianportal)
				enumfacing = EnumFacing.SOUTH;

			if (WSInstance.getBlockState(((BlockPos) object).north()).getBlock() == TABlocks.aurorianportal)
				enumfacing = EnumFacing.EAST;

			if (WSInstance.getBlockState(((BlockPos) object).south()).getBlock() == TABlocks.aurorianportal)
				enumfacing = EnumFacing.WEST;

			EnumFacing enumfacing1 = EnumFacing.getHorizontal(MathHelper.floor(entityIn.rotationYaw * 4.0F / 360.0F + 0.5D) & 3);

			if (enumfacing != null) {
				EnumFacing enumfacing2 = enumfacing.rotateYCCW();
				BlockPos blockpos2 = ((BlockPos) object).offset(enumfacing);
				boolean flag2 = func_180265_a(blockpos2);
				boolean flag3 = func_180265_a(blockpos2.offset(enumfacing2));

				if (flag3 && flag2) {
					object = ((BlockPos) object).offset(enumfacing2);
					enumfacing = enumfacing.getOpposite();
					enumfacing2 = enumfacing2.getOpposite();
					BlockPos blockpos3 = ((BlockPos) object).offset(enumfacing);
					flag2 = func_180265_a(blockpos3);
					flag3 = func_180265_a(blockpos3.offset(enumfacing2));
				}

				float f6 = 0.5F;
				float f1 = 0.5F;

				if (!flag3 && flag2)
					f6 = 1.0F;
				else if (flag3 && !flag2)
					f6 = 0.0F;
				else if (flag3)
					f1 = 0.0F;

				d4 = ((BlockPos) object).getX() + 0.5D;
				d5 = ((BlockPos) object).getY() + 0.5D;
				d6 = ((BlockPos) object).getZ() + 0.5D;
				d4 += enumfacing2.getFrontOffsetX() * f6 + enumfacing.getFrontOffsetX() * f1;
				d6 += enumfacing2.getFrontOffsetZ() * f6 + enumfacing.getFrontOffsetZ() * f1;
				float f2 = 0.0F;
				float f3 = 0.0F;
				float f4 = 0.0F;
				float f5 = 0.0F;

				if (enumfacing == enumfacing1) {
					f2 = 1.0F;
					f3 = 1.0F;
				} else if (enumfacing == enumfacing1.getOpposite()) {
					f2 = -1.0F;
					f3 = -1.0F;
				} else if (enumfacing == enumfacing1.rotateY()) {
					f4 = 1.0F;
					f5 = -1.0F;
				} else {
					f4 = -1.0F;
					f5 = 1.0F;
				}

				double d2 = entityIn.motionX;
				double d3 = entityIn.motionZ;
				entityIn.motionX = d2 * f2 + d3 * f5;
				entityIn.motionZ = d2 * f4 + d3 * f3;
				entityIn.rotationYaw = rotationYaw - enumfacing1.getHorizontalIndex() * 90 + enumfacing.getHorizontalIndex() * 90;
			} else
				entityIn.motionX = entityIn.motionY = entityIn.motionZ = 0.0D;

			entityIn.setLocationAndAngles(d4, d5, d6, entityIn.rotationYaw, entityIn.rotationPitch);
			return true;
		} else
			return false;
	}

	private boolean func_180265_a(BlockPos p_180265_1_) {
		return !WSInstance.isAirBlock(p_180265_1_) || !WSInstance.isAirBlock(p_180265_1_.up());
	}

	@Override
	public boolean makePortal(Entity entityIn) {
		double d0 = -1.0D;
		int j = MathHelper.floor(entityIn.posX);
		int k = MathHelper.floor(entityIn.posY);
		int l = MathHelper.floor(entityIn.posZ);
		int i1 = j;
		int j1 = k;
		int k1 = l;
		int l1 = 0;
		int i2 = this.random.nextInt(4);
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		int testing4 = 4;//4 - 5
		int testing3 = testing4 - 1;
		int testing2 = testing4 - 2;

		for (int j2 = j - 16; j2 <= j + 16; ++j2) {

			double d1 = (double) j2 + 0.5D - entityIn.posX;

			for (int l2 = l - 16; l2 <= l + 16; ++l2) {
				double d2 = (double) l2 + 0.5D - entityIn.posZ;
				label293:

				for (int j3 = this.world.getActualHeight() - 1; j3 >= 0; --j3) {
					if (this.world.isAirBlock(blockpos$mutableblockpos.setPos(j2, j3, l2))) {
						while (j3 > 0 && this.world.isAirBlock(blockpos$mutableblockpos.setPos(j2, j3 - 1, l2))) {
							--j3;
						}

						for (int k3 = i2; k3 < i2 + testing4; ++k3) {
							int l3 = k3 % testing2;
							int i4 = 1 - l3;

							if (k3 % testing4 >= testing2) {
								l3 = -l3;
								i4 = -i4;
							}

							for (int j4 = 0; j4 < testing3; ++j4) {
								for (int k4 = 0; k4 < testing4; ++k4) {
									for (int l4 = -1; l4 < testing4; ++l4) {
										int i5 = j2 + (k4 - 1) * l3 + j4 * i4;
										int j5 = j3 + l4;
										int k5 = l2 + (k4 - 1) * i4 - j4 * l3;
										blockpos$mutableblockpos.setPos(i5, j5, k5);

										if (l4 < 0 && !this.world.getBlockState(blockpos$mutableblockpos).getMaterial().isSolid() || l4 >= 0 && !this.world.isAirBlock(blockpos$mutableblockpos)) {
											continue label293;
										}
									}
								}
							}

							double d5 = (double) j3 + 0.5D - entityIn.posY;
							double d7 = d1 * d1 + d5 * d5 + d2 * d2;

							if (d0 < 0.0D || d7 < d0) {
								d0 = d7;
								i1 = j2;
								j1 = j3;
								k1 = l2;
								l1 = k3 % testing4;
							}
						}
					}
				}
			}
		}

		if (d0 < 0.0D) {
			for (int l5 = j - 16; l5 <= j + 16; ++l5) {
				double d3 = (double) l5 + 0.5D - entityIn.posX;

				for (int j6 = l - 16; j6 <= l + 16; ++j6) {
					double d4 = (double) j6 + 0.5D - entityIn.posZ;
					label231:

					for (int i7 = this.world.getActualHeight() - 1; i7 >= 0; --i7) {
						if (this.world.isAirBlock(blockpos$mutableblockpos.setPos(l5, i7, j6))) {
							while (i7 > 0 && this.world.isAirBlock(blockpos$mutableblockpos.setPos(l5, i7 - 1, j6))) {
								--i7;
							}

							for (int k7 = i2; k7 < i2 + testing2; ++k7) {
								int j8 = k7 % testing2;
								int j9 = 1 - j8;

								for (int j10 = 0; j10 < testing4; ++j10) {
									for (int j11 = -1; j11 < testing4; ++j11) {
										int j12 = l5 + (j10 - 1) * j8;
										int i13 = i7 + j11;
										int j13 = j6 + (j10 - 1) * j9;
										blockpos$mutableblockpos.setPos(j12, i13, j13);

										if (j11 < 0 && !this.world.getBlockState(blockpos$mutableblockpos).getMaterial().isSolid() || j11 >= 0 && !this.world.isAirBlock(blockpos$mutableblockpos)) {
											continue label231;
										}
									}
								}

								double d6 = (double) i7 + 0.5D - entityIn.posY;
								double d8 = d3 * d3 + d6 * d6 + d4 * d4;

								if (d0 < 0.0D || d8 < d0) {
									d0 = d8;
									i1 = l5;
									j1 = i7;
									k1 = j6;
									l1 = k7 % testing2;
								}
							}
						}
					}
				}
			}
		}

		int i6 = i1;
		int k2 = j1;
		int z6 = k1;
		int l6 = l1 % testing2;
		int i3 = 1 - l6;

		if (l1 % testing4 >= testing2) {
			l6 = -l6;
			i3 = -i3;
		}

		if (d0 < 0.0D) {
			j1 = MathHelper.clamp(j1, 70, this.world.getActualHeight() - 10);
			k2 = j1;

			for (int j7 = -1; j7 <= 1; ++j7) {
				for (int l7 = 1; l7 < testing3; ++l7) {
					for (int k8 = -1; k8 < testing3; ++k8) {
						int x9 = i6 + (l7 - 1) * l6 + j7 * i3;
						int y10 = k2 + k8;
						int z11 = z6 + (l7 - 1) * i3 - j7 * l6;
						boolean flag = k8 < 0;
						this.world.setBlockState(new BlockPos(x9, y10, z11), flag ? TABlocks.aurorianportalframebricks.getDefaultState() : Blocks.AIR.getDefaultState());
					}
				}
			}
		}

		IBlockState iblockstate = TABlocks.aurorianportal.getDefaultState().withProperty(TABlock_Portal.AXIS, l6 == 0 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);

		for (int i8 = 0; i8 < testing4; ++i8) {
			for (int l8 = 0; l8 < testing4; ++l8) {
				for (int l9 = -1; l9 < testing4; ++l9) {
					int l10 = i6 + (l8 - 1) * l6;
					int l11 = k2 + l9;
					int k12 = z6 + (l8 - 1) * i3;
					boolean flag1 = l8 == 0 || l8 == testing3 || l9 == -1 || l9 == testing3;
					this.world.setBlockState(new BlockPos(l10, l11, k12), flag1 ? TABlocks.aurorianportalframebricks.getDefaultState() : iblockstate, 2);
				}
			}

			for (int i9 = 0; i9 < testing4; ++i9) {
				for (int i10 = -1; i10 < testing4; ++i10) {
					int i11 = i6 + (i9 - 1) * l6;
					int i12 = k2 + i10;
					int l12 = z6 + (i9 - 1) * i3;
					BlockPos blockpos = new BlockPos(i11, i12, l12);
					this.world.notifyNeighborsOfStateChange(blockpos, this.world.getBlockState(blockpos).getBlock(), false);
				}
			}
		}

		return true;
	}

	@Override
	public void removeStalePortalLocations(long worldTime) {
		if (worldTime % 100L == 0L) {
			long i = worldTime - 300L;
			ObjectIterator<TATeleporter.PortalPosition> objectiterator = this.destinationCoordinateCache.values().iterator();

			while (objectiterator.hasNext()) {
				TATeleporter.PortalPosition teleporter$portalposition = (TATeleporter.PortalPosition) objectiterator.next();

				if (teleporter$portalposition == null || teleporter$portalposition.lastUpdateTime < i) {
					objectiterator.remove();
				}
			}
		}
	}
}
