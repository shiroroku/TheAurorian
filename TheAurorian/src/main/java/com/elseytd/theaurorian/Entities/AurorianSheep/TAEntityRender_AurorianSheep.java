package com.elseytd.theaurorian.Entities.AurorianSheep;

import javax.annotation.Nonnull;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class TAEntityRender_AurorianSheep extends RenderLiving<TAEntity_AurorianSheep> {
	
	private ResourceLocation mobTexture = new ResourceLocation("textures/entity/sheep/sheep.png");
	public static final Factory FACTORY = new Factory();

	public TAEntityRender_AurorianSheep(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new TAEntityModel_AurorianSheep2(), 0.7F);
		this.addLayer(new TAEntityLayer_AurorianSheep(this));
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull TAEntity_AurorianSheep entity) {
		return mobTexture;
	}

	public static class Factory implements IRenderFactory<TAEntity_AurorianSheep> {

		@Override
		public Render<? super TAEntity_AurorianSheep> createRenderFor(RenderManager manager) {
			return new TAEntityRender_AurorianSheep(manager);
		}

	}
}
