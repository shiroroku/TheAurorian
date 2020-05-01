package com.elseytd.theaurorian.Entities.Hostile;

import javax.annotation.Nonnull;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class Spirit_EntityRender extends RenderLiving<Spirit_Entity> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/" + Spirit_Entity.EntityName + ".png");

	public static final Factory FACTORY = new Factory();

	public Spirit_EntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelZombie(), 0.5F);
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull Spirit_Entity entity) {
		return mobTexture;
	}

	@Override
	public void doRender(Spirit_Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		GlStateManager.disableAlpha();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

	public static class Factory implements IRenderFactory<Spirit_Entity> {

		@Override
		public Render<? super Spirit_Entity> createRenderFor(RenderManager manager) {
			return new Spirit_EntityRender(manager);
		}

	}
}
