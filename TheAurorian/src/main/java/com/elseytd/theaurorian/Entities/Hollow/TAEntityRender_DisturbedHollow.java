package com.elseytd.theaurorian.Entities.Hollow;

import javax.annotation.Nonnull;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class TAEntityRender_DisturbedHollow extends RenderLiving<TAEntity_DisturbedHollow> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/" + TAEntity_DisturbedHollow.EntityName + ".png");

	public static final Factory FACTORY = new Factory();

	public TAEntityRender_DisturbedHollow(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelZombie(), 0.5F);
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull TAEntity_DisturbedHollow entity) {
		return mobTexture;
	}

	public static class Factory implements IRenderFactory<TAEntity_DisturbedHollow> {

		@Override
		public Render<? super TAEntity_DisturbedHollow> createRenderFor(RenderManager manager) {
			return new TAEntityRender_DisturbedHollow(manager);
		}

	}
}
