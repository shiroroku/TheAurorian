package com.elseytd.theaurorian.Entities.MoonAcolyte;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class TAEntityLayer_MoonAcolyte implements LayerRenderer<TAEntity_MoonAcolyte> {

	public static ResourceLocation layerTexture = new ResourceLocation("theaurorian:textures/entity/moonacolytelayer.png");

	private final RenderLivingBase<?> renderer;
	private final ModelZombie layerModel = new ModelZombie(1F, false);

	public TAEntityLayer_MoonAcolyte(RenderLivingBase<?> rend) {
		this.renderer = rend;
	}

	@Override
	public void doRenderLayer(TAEntity_MoonAcolyte entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
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
