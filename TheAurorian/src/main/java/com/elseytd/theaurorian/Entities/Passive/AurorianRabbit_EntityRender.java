package com.elseytd.theaurorian.Entities.Passive;

import javax.annotation.Nonnull;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class AurorianRabbit_EntityRender extends RenderLiving<AurorianRabbit_Entity> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/" + AurorianRabbit_Entity.EntityName + ".png");

	public static final Factory FACTORY = new Factory();

	public AurorianRabbit_EntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new AurorianRabbit_EntityModel(), 0.3F);
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull AurorianRabbit_Entity entity) {
		return mobTexture;
	}

	public static class Factory implements IRenderFactory<AurorianRabbit_Entity> {

		@Override
		public Render<? super AurorianRabbit_Entity> createRenderFor(RenderManager manager) {
			return new AurorianRabbit_EntityRender(manager);
		}

	}
}
