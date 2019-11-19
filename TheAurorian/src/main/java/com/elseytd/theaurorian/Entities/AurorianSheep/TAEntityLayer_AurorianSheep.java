package com.elseytd.theaurorian.Entities.AurorianSheep;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TAEntityLayer_AurorianSheep implements LayerRenderer<TAEntity_AurorianSheep> {

	private static final ResourceLocation layerTexture = new ResourceLocation("textures/entity/sheep/sheep_fur.png");

	private final TAEntityRender_AurorianSheep renderer;
	private final TAEntityModel_AurorianSheep1 layerModel = new TAEntityModel_AurorianSheep1();

	public TAEntityLayer_AurorianSheep(TAEntityRender_AurorianSheep sheepRendererIn) {
		this.renderer = sheepRendererIn;
	}

	public void doRenderLayer(TAEntity_AurorianSheep entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (!entitylivingbaseIn.getSheared() && !entitylivingbaseIn.isInvisible()) {
			this.renderer.bindTexture(layerTexture);

			float[] afloat = TAEntity_AurorianSheep.getDyeRgb(entitylivingbaseIn.getFleeceColor());
			if (entitylivingbaseIn.getFleeceColor() == EnumDyeColor.PURPLE) {
				GlStateManager.color(117F / 255F, 69F / 255F, 254F / 255F);
			} else if (entitylivingbaseIn.getFleeceColor() == EnumDyeColor.LIGHT_BLUE) {
				GlStateManager.color(95F / 255F, 169F / 255F, 237F / 255F);
			} else {
				GlStateManager.color(afloat[0], afloat[1], afloat[2]);
			}

			this.layerModel.setModelAttributes(this.renderer.getMainModel());
			this.layerModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
			this.layerModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		}
	}

	public boolean shouldCombineTextures() {
		return true;
	}
}
