package com.elseytd.theaurorian.Particles;

import com.elseytd.theaurorian.TAItems;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleBreaking;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TAParticle_AurorianSlime extends ParticleBreaking {
	public TAParticle_AurorianSlime(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, TAItems.Registry.AURORIANSLIMEBALL.getItem());
	}

	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		@Override
		public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
			return new TAParticle_AurorianSlime(worldIn, xCoordIn, yCoordIn, zCoordIn);
		}
	}
}
