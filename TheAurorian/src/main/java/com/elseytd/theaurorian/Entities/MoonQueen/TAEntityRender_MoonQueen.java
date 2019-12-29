package com.elseytd.theaurorian.Entities.MoonQueen;

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

public class TAEntityRender_MoonQueen extends RenderLiving<TAEntity_MoonQueen> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/" + TAEntity_MoonQueen.EntityName + ".png");
	private float mobScale = TAEntity_MoonQueen.MobScale;

	public static final Factory FACTORY = new Factory();

	public TAEntityRender_MoonQueen(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new TAEntityModel_MoonQueen(), 0.5F);
		this.addLayer(new LayerHeldItem(this));
		this.addLayer(new LayerBipedArmor(this));
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull TAEntity_MoonQueen entity) {
		return mobTexture;
	}

	@Override
	protected void preRenderCallback(TAEntity_MoonQueen entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(mobScale, mobScale, mobScale);
	}

	public static class Factory implements IRenderFactory<TAEntity_MoonQueen> {

		@Override
		public Render<? super TAEntity_MoonQueen> createRenderFor(RenderManager manager) {
			return new TAEntityRender_MoonQueen(manager);
		}

	}
}
