package com.elseytd.theaurorian.Entities.Spirit;

import javax.annotation.Nonnull;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class TAEntityRender_Spirit extends RenderLiving<TAEntity_Spirit> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/" + TAEntity_Spirit.EntityName + ".png");

	public static final Factory FACTORY = new Factory();

	public TAEntityRender_Spirit(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelZombie(), 0.5F);
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull TAEntity_Spirit entity) {
		return mobTexture;
	}

	@Override
	public void doRender(TAEntity_Spirit entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		GlStateManager.disableAlpha();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

	public static class Factory implements IRenderFactory<TAEntity_Spirit> {

		@Override
		public Render<? super TAEntity_Spirit> createRenderFor(RenderManager manager) {
			return new TAEntityRender_Spirit(manager);
		}

	}
}
