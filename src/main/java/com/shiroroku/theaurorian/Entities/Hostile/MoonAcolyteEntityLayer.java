package com.shiroroku.theaurorian.Entities.Hostile;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class MoonAcolyteEntityLayer implements LayerRenderer<MoonAcolyteEntity> {

	public static ResourceLocation layerTexture = new ResourceLocation("theaurorian:textures/entity/moonacolytelayer.png");

	private final RenderLivingBase<?> renderer;
	private final ModelZombie layerModel = new ModelZombie(1F, false);

	public MoonAcolyteEntityLayer(RenderLivingBase<?> rend) {
		this.renderer = rend;
	}

	@Override
	public void doRenderLayer(MoonAcolyteEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.layerModel.setModelAttributes(this.renderer.getMainModel());
		this.layerModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.renderer.bindTexture(layerTexture);
		this.layerModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	}

	@Override
	public boolean shouldCombineTextures() {
		return true;
	}

}
