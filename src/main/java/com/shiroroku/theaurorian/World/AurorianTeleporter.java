package com.shiroroku.theaurorian.World;

import com.shiroroku.theaurorian.Blocks.AurorianPortal;
import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.Registry.DimensionRegistry;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
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
import net.minecraftforge.fml.relauncher.ReflectionHelper;

@SuppressWarnings("deprecation")
public class AurorianTeleporter extends Teleporter {

	private static final Long2ObjectMap<AurorianTeleporter.PortalPosition> destinationCoordinateCache = new Long2ObjectOpenHashMap<>(4096);

	private final WorldServer WSInstance;

	public AurorianTeleporter(WorldServer worldIn) {
		super(worldIn);
		this.WSInstance = worldIn;
	}

	public static void transferEntity(Entity entity, int dimto) {
		Teleporter teleporter = new AurorianTeleporter(entity.getServer().getWorld(dimto));
		if (entity instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP) entity;
			if (!player.isCreative()) {
				ReflectionHelper.setPrivateValue(EntityPlayerMP.class, player, true, "invulnerableDimensionChange", "field_184851_cj");
			}

			if (!ForgeHooks.onTravelToDimension(entity, DimensionRegistry.DIMENSION_ID)) {
				return;
			}
			player.mcServer.getPlayerList().transferPlayerToDimension(player, dimto, teleporter);
		} else {
			entity.changeDimension(dimto, teleporter);
		}
	}

	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw) {
		if (!this.placeInExistingPortal(entityIn, rotationYaw)) {
			this.makePortal(entityIn);
			this.placeInExistingPortal(entityIn, rotationYaw);
		}
	}

	@Override
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
			portalposition.lastUpdateTime = this.WSInstance.getTotalWorldTime();
			flag1 = false;
		} else {
			BlockPos blockpos4 = new BlockPos(entityIn);
			for (int l = -128; l <= 128; ++l) {
				BlockPos blockpos1;

				for (int i1 = -128; i1 <= 128; ++i1) {
					for (BlockPos blockpos = blockpos4.add(l, this.WSInstance.getActualHeight() - 1 - blockpos4.getY(), i1); blockpos.getY() >= 0; blockpos = blockpos1) {
						blockpos1 = blockpos.down();
						if (this.WSInstance.getBlockState(blockpos).getBlock() == BlockRegistry.Registry.AURORIANPORTAL.getBlock()) {
							while (this.WSInstance.getBlockState(blockpos1 = blockpos.down()).getBlock() == BlockRegistry.Registry.AURORIANPORTAL.getBlock()) {
								blockpos = blockpos1;
							}
							double d1 = blockpos.distanceSq(blockpos4);
							if (d0 < 0.0D || d1 < d0) {
								d0 = d1;
								object = blockpos;
							}
						}
					}
				}
			}
		}

		if (d0 >= 0.0D) {
			if (flag1) {
				destinationCoordinateCache.put(k, new Teleporter.PortalPosition((BlockPos) object, this.WSInstance.getTotalWorldTime()));
			}

			double d4 = ((BlockPos) object).getX() + 0.5D;
			double d5 = ((BlockPos) object).getY() + 0.5D;
			double d6 = ((BlockPos) object).getZ() + 0.5D;

			EnumFacing enumfacing = null;

			if (this.WSInstance.getBlockState(((BlockPos) object).west()).getBlock() == BlockRegistry.Registry.AURORIANPORTAL.getBlock()) {
				enumfacing = EnumFacing.NORTH;
			}

			if (this.WSInstance.getBlockState(((BlockPos) object).east()).getBlock() == BlockRegistry.Registry.AURORIANPORTAL.getBlock()) {
				enumfacing = EnumFacing.SOUTH;
			}

			if (this.WSInstance.getBlockState(((BlockPos) object).north()).getBlock() == BlockRegistry.Registry.AURORIANPORTAL.getBlock()) {
				enumfacing = EnumFacing.EAST;
			}

			if (this.WSInstance.getBlockState(((BlockPos) object).south()).getBlock() == BlockRegistry.Registry.AURORIANPORTAL.getBlock()) {
				enumfacing = EnumFacing.WEST;
			}

			EnumFacing enumfacing1 = EnumFacing.getHorizontal(MathHelper.floor(entityIn.rotationYaw * 4.0F / 360.0F + 0.5D) & 3);

			if (enumfacing != null) {
				EnumFacing enumfacing2 = enumfacing.rotateYCCW();
				BlockPos blockpos2 = ((BlockPos) object).offset(enumfacing);
				boolean flag2 = this.isBlockAirAndAbove(blockpos2);
				boolean flag3 = this.isBlockAirAndAbove(blockpos2.offset(enumfacing2));

				if (flag3 && flag2) {
					object = ((BlockPos) object).offset(enumfacing2);
					enumfacing = enumfacing.getOpposite();
					enumfacing2 = enumfacing2.getOpposite();
					BlockPos blockpos3 = ((BlockPos) object).offset(enumfacing);
					flag2 = this.isBlockAirAndAbove(blockpos3);
					flag3 = this.isBlockAirAndAbove(blockpos3.offset(enumfacing2));
				}

				float f6 = 0.5F;
				float f1 = 0.5F;

				if (!flag3 && flag2) {
					f6 = 1.0F;
				} else if (flag3 && !flag2) {
					f6 = 0.0F;
				} else if (flag3) {
					f1 = 0.0F;
				}

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
			} else {
				entityIn.motionX = entityIn.motionY = entityIn.motionZ = 0.0D;
			}
			entityIn.setLocationAndAngles(d4, d5, d6, entityIn.rotationYaw, entityIn.rotationPitch);
			return true;
		} else {
			return false;
		}
	}

	private boolean isBlockAirAndAbove(BlockPos pos) {
		return !this.WSInstance.isAirBlock(pos) || !this.WSInstance.isAirBlock(pos.up());
	}

	@Override
	public boolean makePortal(Entity entityIn) {
		boolean platform = true;
		int x = MathHelper.floor(entityIn.posX);
		int y = MathHelper.floor(entityIn.posY);
		int z = MathHelper.floor(entityIn.posZ);

		for (int h = this.world.getHeight() - 20; h > 0; h--) {
			IBlockState blk = this.world.getBlockState(new BlockPos(x, h, z));
			if (!this.world.isAirBlock(new BlockPos(x, h, z)) && blk != BlockRegistry.Registry.SILENTWOODLOG.getBlock() && !(blk.getBlock() instanceof BlockLeaves) && !(blk.getBlock() instanceof BlockBush)) {
				y = h + 2;
				break;
			}
		}

		int testing4 = 4;
		int testing3 = 3;
		int testing2 = 2;

		int x1 = x;
		int y1 = y;
		int z1 = z;
		int direction = 0 % testing2;
		int directionmirr = 1 - direction;

		if (0 % testing4 >= testing2) {
			direction = -direction;
			directionmirr = -directionmirr;
		}

		if (platform) {
			for (int j7 = -1; j7 <= 1; ++j7) {
				for (int l7 = 1; l7 < testing3; ++l7) {
					for (int k8 = -1; k8 < testing3; ++k8) {
						int x9 = x1 + (l7 - 1) * direction + j7 * directionmirr;
						int y10 = y1 + k8;
						int z11 = z1 + (l7 - 1) * directionmirr - j7 * direction;
						boolean flag = k8 < 0;
						this.world.setBlockState(new BlockPos(x9, y10, z11), flag ? BlockRegistry.Registry.AURORIANSTONEBRICK.getBlock().getDefaultState() : Blocks.AIR.getDefaultState());
					}
				}
			}
		}

		IBlockState portalBlock = BlockRegistry.Registry.AURORIANPORTAL.getBlock().getDefaultState().withProperty(AurorianPortal.AXIS, direction == 0 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);

		for (int i8 = 0; i8 < testing4; ++i8) {
			for (int l8 = 0; l8 < testing4; ++l8) {
				for (int l9 = -1; l9 < testing4; ++l9) {
					int xPort = x1 + (l8 - 1) * direction;
					int yPort = y1 + l9;
					int zPort = z1 + (l8 - 1) * directionmirr;
					boolean flag1 = l8 == 0 || l8 == testing3 || l9 == -1 || l9 == testing3;
					this.world.setBlockState(new BlockPos(xPort, yPort, zPort), flag1 ? BlockRegistry.Registry.AURORIANPORTALFRAME.getBlock().getDefaultState() : portalBlock, 2);
				}
			}

			for (int i9 = 0; i9 < testing4; ++i9) {
				for (int i10 = -1; i10 < testing4; ++i10) {
					int i11 = x1 + (i9 - 1) * direction;
					int i12 = y1 + i10;
					int l12 = z1 + (i9 - 1) * directionmirr;
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
			ObjectIterator<AurorianTeleporter.PortalPosition> objectiterator = AurorianTeleporter.destinationCoordinateCache.values().iterator();

			while (objectiterator.hasNext()) {
				AurorianTeleporter.PortalPosition teleporter$portalposition = objectiterator.next();

				if (teleporter$portalposition == null || teleporter$portalposition.lastUpdateTime < i) {
					objectiterator.remove();
				}
			}
		}
	}
}
