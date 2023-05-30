package com.shiroroku.theaurorian.Entities.Hostile;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class DisturbedHollowEntityRender extends RenderLiving<DisturbedHollowEntity> {

	private final ResourceLocation mobTexture = new ResourceLocation(AurorianMod.MODID, "textures/entity/" + DisturbedHollowEntity.EntityName + ".png");

	public static final Factory FACTORY = new Factory();

	public DisturbedHollowEntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelZombie(), 0.5F);
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull DisturbedHollowEntity entity) {
		return mobTexture;
	}

	public static class Factory implements IRenderFactory<DisturbedHollowEntity> {

		@Override
		public Render<? super DisturbedHollowEntity> createRenderFor(RenderManager manager) {
			return new DisturbedHollowEntityRender(manager);
		}

	}
}
