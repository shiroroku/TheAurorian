package com.shiroroku.theaurorian.Registry;

import com.shiroroku.theaurorian.Network.ParticleMessage;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketRegistry {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(AurorianMod.MODID);

	public static void registerMessages() {
		int id = 0;
		INSTANCE.registerMessage(ParticleMessage.Handler.class, ParticleMessage.class, id++, Side.CLIENT);
	}

	/**
	 * Sends a packet to clients telling to spawn a particle
	 */
	public static void spawnParticle(World worldIn, int particleID, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		ParticleMessage particlePacket = new ParticleMessage(particleID, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		NetworkRegistry.TargetPoint target = new TargetPoint(worldIn.provider.getDimension(), xCoordIn, yCoordIn, zCoordIn, 64D);
		PacketRegistry.INSTANCE.sendToAllAround(particlePacket, target);
	}

}
