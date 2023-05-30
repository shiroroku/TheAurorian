package com.shiroroku.theaurorian.Entities.Boss;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class KeeperEntityLayer implements LayerRenderer<KeeperEntity> {

	public static ResourceLocation layerTexture = new ResourceLocation("theaurorian:textures/entity/runestonedungeonkeeperlayer2.png");

	private final RenderLivingBase<?> renderer;
	private final KeeperEntityModel layerModel = new KeeperEntityModel();

	public KeeperEntityLayer(RenderLivingBase<?> rend) {
		this.renderer = rend;
	}

	@Override
	public void doRenderLayer(KeeperEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
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
