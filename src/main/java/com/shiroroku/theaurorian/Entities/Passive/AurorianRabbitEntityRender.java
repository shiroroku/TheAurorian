package com.shiroroku.theaurorian.Entities.Passive;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class AurorianRabbitEntityRender extends RenderLiving<AurorianRabbitEntity> {

	private final ResourceLocation mobTexture = new ResourceLocation(AurorianMod.MODID, "textures/entity/" + AurorianRabbitEntity.EntityName + ".png");

	public static final Factory FACTORY = new Factory();

	public AurorianRabbitEntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new AurorianRabbitEntityModel(), 0.3F);
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull AurorianRabbitEntity entity) {
		return mobTexture;
	}

	public static class Factory implements IRenderFactory<AurorianRabbitEntity> {

		@Override
		public Render<? super AurorianRabbitEntity> createRenderFor(RenderManager manager) {
			return new AurorianRabbitEntityRender(manager);
		}

	}
}
