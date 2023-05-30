package com.shiroroku.theaurorian.Entities.Boss;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class MoonQueenEntityRender extends RenderLiving<MoonQueenEntity> {

	private final ResourceLocation mobTexture = new ResourceLocation(AurorianMod.MODID, "textures/entity/" + MoonQueenEntity.EntityName + ".png");
	private final float mobScale = MoonQueenEntity.MobScale;

	public static final Factory FACTORY = new Factory();

	public MoonQueenEntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new MoonQueenEntityModel(), 0.5F);
		this.addLayer(new LayerHeldItem(this));
		this.addLayer(new LayerBipedArmor(this));
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull MoonQueenEntity entity) {
		return mobTexture;
	}

	@Override
	protected void preRenderCallback(MoonQueenEntity entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(mobScale, mobScale, mobScale);
	}

	public static class Factory implements IRenderFactory<MoonQueenEntity> {

		@Override
		public Render<? super MoonQueenEntity> createRenderFor(RenderManager manager) {
			return new MoonQueenEntityRender(manager);
		}

	}
}
