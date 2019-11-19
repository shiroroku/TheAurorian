package com.elseytd.theaurorian.Entities.MoonAcolyte;

import javax.annotation.Nonnull;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class TAEntityRender_MoonAcolyte extends RenderLiving<TAEntity_MoonAcolyte> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/" + TAEntity_MoonAcolyte.EntityName + ".png");
	public static final float MobScale = 1F;
	public static final Factory FACTORY = new Factory();

	public TAEntityRender_MoonAcolyte(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelZombie(), 0.5F);
		addLayer(new TAEntityLayer_MoonAcolyte(this));
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull TAEntity_MoonAcolyte entity) {
		return mobTexture;
	}

	@Override
	protected void preRenderCallback(TAEntity_MoonAcolyte entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(MobScale, MobScale, MobScale);
	}

	public static class Factory implements IRenderFactory<TAEntity_MoonAcolyte> {

		@Override
		public Render<? super TAEntity_MoonAcolyte> createRenderFor(RenderManager manager) {
			return new TAEntityRender_MoonAcolyte(manager);
		}

	}
}
