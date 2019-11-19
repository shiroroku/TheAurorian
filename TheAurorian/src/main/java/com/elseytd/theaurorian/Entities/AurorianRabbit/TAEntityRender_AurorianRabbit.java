package com.elseytd.theaurorian.Entities.AurorianRabbit;

import javax.annotation.Nonnull;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class TAEntityRender_AurorianRabbit extends RenderLiving<TAEntity_AurorianRabbit> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/" + TAEntity_AurorianRabbit.EntityName + ".png");

	public static final Factory FACTORY = new Factory();

	public TAEntityRender_AurorianRabbit(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new TAEntityModel_AurorianRabbit(), 0.3F);
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull TAEntity_AurorianRabbit entity) {
		return mobTexture;
	}

	public static class Factory implements IRenderFactory<TAEntity_AurorianRabbit> {

		@Override
		public Render<? super TAEntity_AurorianRabbit> createRenderFor(RenderManager manager) {
			return new TAEntityRender_AurorianRabbit(manager);
		}

	}
}
