package com.shiroroku.theaurorian.Particles;

import com.shiroroku.theaurorian.Registry.SoundRegistry;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleDrip;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WeepingWillowDripParticle extends ParticleDrip {

	private int bobTimer;

	public WeepingWillowDripParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, Material.WATER);
		this.particleRed = 0;
		this.particleGreen = 0;
		this.particleBlue = 0;
		this.particleGravity = 0.015F;
		this.bobTimer = 80;
		this.particleMaxAge = 300;
		this.particleScale = 3;
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		this.particleRed = -16.0F / (float) (80 - this.bobTimer + 16);
		this.particleGreen = -16.0F / (float) (80 - this.bobTimer + 16);
		this.particleBlue = -16.0F / (float) (80 - this.bobTimer + 16);

		this.motionY -= this.particleGravity;

		if (this.bobTimer-- > 0) {
			this.motionX *= 0.02D;
			this.motionY *= 0.02D;
			this.motionZ *= 0.02D;
			this.setParticleTextureIndex(113);
		} else {
			this.setParticleTextureIndex(112);
		}

		this.move(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9800000190734863D;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= 0.9800000190734863D;

		if (this.particleMaxAge-- <= 0) {
			this.setExpired();
		}

		if (this.onGround) {
			this.setExpired();
			this.world.spawnParticle(EnumParticleTypes.CLOUD, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			this.world.playSound(this.posX, this.posY, this.posZ, SoundRegistry.WEEPINGWILLOWBELL, SoundCategory.BLOCKS, 0.015F, this.rand.nextFloat() * 0.3F + 0.7F, false);
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}

		BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
		IBlockState iblockstate = this.world.getBlockState(blockpos);
		Material material = iblockstate.getMaterial();

		if (material.isLiquid() || material.isSolid()) {
			double d0 = 0.0D;

			if (iblockstate.getBlock() instanceof BlockLiquid) {
				d0 = BlockLiquid.getLiquidHeightPercent(iblockstate.getValue(BlockLiquid.LEVEL).intValue());
			}

			double d1 = (double) (MathHelper.floor(this.posY) + 1) - d0;

			if (this.posY < d1) {
				this.setExpired();
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
			return new WeepingWillowDripParticle(worldIn, xCoordIn, yCoordIn, zCoordIn);
		}
	}
}
