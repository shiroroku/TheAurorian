package com.shiroroku.theaurorian.Entities.Passive;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class AurorianSheepEntityRender extends RenderLiving<AurorianSheepEntity> {
	
	private final ResourceLocation mobTexture = new ResourceLocation("textures/entity/sheep/sheep.png");
	public static final Factory FACTORY = new Factory();

	public AurorianSheepEntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new AurorianSheepEntityModel2(), 0.7F);
		this.addLayer(new AurorianSheepEntityLayer(this));
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull AurorianSheepEntity entity) {
		return mobTexture;
	}

	public static class Factory implements IRenderFactory<AurorianSheepEntity> {

		@Override
		public Render<? super AurorianSheepEntity> createRenderFor(RenderManager manager) {
			return new AurorianSheepEntityRender(manager);
		}

	}
}
