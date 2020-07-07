package com.elseytd.theaurorian;

import com.elseytd.theaurorian.Particles.TAParticle_AurorianSlime;
import com.elseytd.theaurorian.Particles.TAParticle_StickySpiker;
import com.elseytd.theaurorian.Particles.TAParticle_Webbing;
import com.elseytd.theaurorian.Particles.TAParticle_WeepingWillow_Drip;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TAParticles {

	private static Minecraft mcinstance = Minecraft.getMinecraft();

	public enum Particles {
		AURORIANSLIME(111240),
		STICKYSPIKER(111241),
		WEBBING(111242),
		WEEPINGWILLOWDRIP(111243);

		int ID;

		private Particles(int particleID) {
			this.ID = particleID;
		}

		public int getID() {
			return ID;
		}
	}

	private static Particle getParticleFromType(Particles type, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		switch (type) {
		case AURORIANSLIME:
			return new TAParticle_AurorianSlime(mcinstance.world, xCoordIn, yCoordIn, zCoordIn);
		case STICKYSPIKER:
			return new TAParticle_StickySpiker(mcinstance.world, xCoordIn, yCoordIn, zCoordIn);
		case WEEPINGWILLOWDRIP:
			return new TAParticle_WeepingWillow_Drip(mcinstance.world, xCoordIn, yCoordIn, zCoordIn);
		case WEBBING:
			return new TAParticle_Webbing(mcinstance.world, xCoordIn, yCoordIn, zCoordIn);
		default:
			return null;
		}
	}

	public static Particle spawnParticle(Particles type, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		if (mcinstance != null && mcinstance.getRenderViewEntity() != null && mcinstance.effectRenderer != null) {
			int particlesetting = mcinstance.gameSettings.particleSetting;

			if (particlesetting == 1 && mcinstance.world.rand.nextInt(3) == 0) {
				particlesetting = 2;
			}

			double boundsx = mcinstance.getRenderViewEntity().posX - xCoordIn;
			double boundsy = mcinstance.getRenderViewEntity().posY - yCoordIn;
			double boundsz = mcinstance.getRenderViewEntity().posZ - zCoordIn;
			double bound = 16.0D;

			if (boundsx * boundsx + boundsy * boundsy + boundsz * boundsz > bound * bound) {
				return null;
			} else if (particlesetting > 1) {
				return null;
			} else {
				Particle particle = getParticleFromType(type, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
				mcinstance.effectRenderer.addEffect(particle);
				return particle;
			}
		}
		return null;
	}
}
