package com.elseytd.theaurorian.Entities.Passive;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class AurorianSheep_EntityRender extends RenderLiving<AurorianSheep_Entity> {
	
	private ResourceLocation mobTexture = new ResourceLocation("textures/entity/sheep/sheep.png");
	public static final Factory FACTORY = new Factory();

	public AurorianSheep_EntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new AurorianSheep_EntityModel2(), 0.7F);
		this.addLayer(new AurorianSheep_EntityLayer(this));
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull AurorianSheep_Entity entity) {
		return mobTexture;
	}

	public static class Factory implements IRenderFactory<AurorianSheep_Entity> {

		@Override
		public Render<? super AurorianSheep_Entity> createRenderFor(RenderManager manager) {
			return new AurorianSheep_EntityRender(manager);
		}

	}
}
