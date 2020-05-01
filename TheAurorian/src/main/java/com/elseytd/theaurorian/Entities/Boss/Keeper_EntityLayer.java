package com.elseytd.theaurorian.Entities.Boss;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class Keeper_EntityLayer implements LayerRenderer<Keeper_Entity> {

	public static ResourceLocation layerTexture = new ResourceLocation("theaurorian:textures/entity/runestonedungeonkeeperlayer2.png");

	private final RenderLivingBase<?> renderer;
	private final Keeper_EntityModel layerModel = new Keeper_EntityModel();

	public Keeper_EntityLayer(RenderLivingBase<?> rend) {
		this.renderer = rend;
	}

	@Override
	public void doRenderLayer(Keeper_Entity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
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
