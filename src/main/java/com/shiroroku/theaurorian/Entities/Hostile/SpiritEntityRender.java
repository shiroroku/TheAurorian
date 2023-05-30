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

public class SpiritEntityRender extends RenderLiving<SpiritEntity> {

	private final ResourceLocation mobTexture = new ResourceLocation(AurorianMod.MODID, "textures/entity/" + SpiritEntity.EntityName + ".png");

	public static final Factory FACTORY = new Factory();

	public SpiritEntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelZombie(), 0.5F);
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull SpiritEntity entity) {
		return mobTexture;
	}

	@Override
	public void doRender(SpiritEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		GlStateManager.disableAlpha();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

	public static class Factory implements IRenderFactory<SpiritEntity> {

		@Override
		public Render<? super SpiritEntity> createRenderFor(RenderManager manager) {
			return new SpiritEntityRender(manager);
		}

	}
}
