package com.elseytd.theaurorian.Entities.UndeadKnight;

import javax.annotation.Nonnull;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class TAEntityRender_UndeadKnight extends RenderLiving<TAEntity_UndeadKnight> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/" + TAEntity_UndeadKnight.EntityName + ".png");
	private float mobScale = TAEntity_UndeadKnight.MobScale;

	public static final Factory FACTORY = new Factory();

	public TAEntityRender_UndeadKnight(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new TAEntityModel_UndeadKnight(), 0.5F);
		addLayer(new LayerHeldItem(this));
		addLayer(new LayerBipedArmor(this));
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull TAEntity_UndeadKnight entity) {
		return mobTexture;
	}

	@Override
	protected void preRenderCallback(TAEntity_UndeadKnight entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(mobScale, mobScale, mobScale);
	}

	public static class Factory implements IRenderFactory<TAEntity_UndeadKnight> {

		@Override
		public Render<? super TAEntity_UndeadKnight> createRenderFor(RenderManager manager) {
			return new TAEntityRender_UndeadKnight(manager);
		}

	}
}
