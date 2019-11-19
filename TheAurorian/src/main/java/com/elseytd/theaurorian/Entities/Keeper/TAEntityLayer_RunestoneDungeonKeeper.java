package com.elseytd.theaurorian.Entities.Keeper;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class TAEntityLayer_RunestoneDungeonKeeper implements LayerRenderer<TAEntity_RunestoneDungeonKeeper> {

	public static ResourceLocation layerTexture = new ResourceLocation("theaurorian:textures/entity/runestonedungeonkeeperlayer2.png");

	private final RenderLivingBase<?> renderer;
	private final TAEntityModel_RunestoneDungeonKeeper layerModel = new TAEntityModel_RunestoneDungeonKeeper();

	public TAEntityLayer_RunestoneDungeonKeeper(RenderLivingBase<?> rend) {
		this.renderer = rend;
	}

	@Override
	public void doRenderLayer(TAEntity_RunestoneDungeonKeeper entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
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
