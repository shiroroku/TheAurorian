package com.shiroroku.theaurorian.Entities.Hostile;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class MoonAcolyteEntityRender extends RenderLiving<MoonAcolyteEntity> {

	private final ResourceLocation mobTexture = new ResourceLocation(AurorianMod.MODID, "textures/entity/" + MoonAcolyteEntity.EntityName + ".png");
	public static final float MobScale = 1F;
	public static final Factory FACTORY = new Factory();

	public MoonAcolyteEntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelZombie(), 0.5F);
		addLayer(new MoonAcolyteEntityLayer(this));
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull MoonAcolyteEntity entity) {
		return mobTexture;
	}

	@Override
	protected void preRenderCallback(MoonAcolyteEntity entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(MobScale, MobScale, MobScale);
	}

	public static class Factory implements IRenderFactory<MoonAcolyteEntity> {

		@Override
		public Render<? super MoonAcolyteEntity> createRenderFor(RenderManager manager) {
			return new MoonAcolyteEntityRender(manager);
		}

	}
}
