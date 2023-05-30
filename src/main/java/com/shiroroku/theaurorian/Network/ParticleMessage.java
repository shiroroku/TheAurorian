package com.shiroroku.theaurorian.Network;

import com.shiroroku.theaurorian.Registry.ParticleRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ParticleMessage implements IMessage {
	private int particle;
	private double x;
	private double y;
	private double z;
	private double xm;
	private double ym;
	private double zm;

	public ParticleMessage() {
	}

	public ParticleMessage(int particleid, double xPos, double yPos, double zPos, double xMot, double yMot, double zMot) {
		this.particle = particleid;
		this.x = xPos;
		this.y = yPos;
		this.z = zPos;
		this.xm = xMot;
		this.ym = yMot;
		this.zm = zMot;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		particle = buf.readInt();
		x = buf.readDouble();
		y = buf.readDouble();
		z = buf.readDouble();
		xm = buf.readDouble();
		ym = buf.readDouble();
		zm = buf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(particle);
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);
		buf.writeDouble(xm);
		buf.writeDouble(ym);
		buf.writeDouble(zm);
	}

	public static class Handler implements IMessageHandler<ParticleMessage, IMessage> {
		@Override
		public IMessage onMessage(ParticleMessage particlemessage, MessageContext ctx) {
			if (ctx.side == Side.CLIENT) {
				Minecraft minecraft = Minecraft.getMinecraft();
				final WorldClient worldClient = minecraft.world;
				minecraft.addScheduledTask(() -> spawnParticleFromPacket(particlemessage, worldClient));
			}
			return null;
		}

		private void spawnParticleFromPacket(ParticleMessage pm, WorldClient worldClient) {
			for (ParticleRegistry.Particles particle : ParticleRegistry.Particles.values()) {
				if (particle.getID() == pm.particle) {
					ParticleRegistry.spawnParticle(particle, pm.x, pm.y, pm.z, pm.xm, pm.ym, pm.zm);
					return;
				}
			}

			worldClient.spawnParticle(EnumParticleTypes.getParticleFromId(pm.particle), pm.x, pm.y, pm.z, pm.xm, pm.ym, pm.zm);
		}
	}

}