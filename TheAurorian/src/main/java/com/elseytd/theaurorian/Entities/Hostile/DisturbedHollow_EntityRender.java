package com.elseytd.theaurorian.Entities.Hostile;

import com.elseytd.theaurorian.TAMod;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class DisturbedHollow_EntityRender extends RenderLiving<DisturbedHollow_Entity> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/" + DisturbedHollow_Entity.EntityName + ".png");

	public static final Factory FACTORY = new Factory();

	public DisturbedHollow_EntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelZombie(), 0.5F);
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull DisturbedHollow_Entity entity) {
		return mobTexture;
	}

	public static class Factory implements IRenderFactory<DisturbedHollow_Entity> {

		@Override
		public Render<? super DisturbedHollow_Entity> createRenderFor(RenderManager manager) {
			return new DisturbedHollow_EntityRender(manager);
		}

	}
}
