package com.shiroroku.theaurorian.TileEntities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MoonLightForgeTileEntityRenderer extends TileEntitySpecialRenderer<MoonLightForgeTileEntity> {

	@Override
	public void render(MoonLightForgeTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushAttrib();
		GlStateManager.pushMatrix();

		GlStateManager.translate(x, y, z);
		GlStateManager.disableRescaleNormal();

		ItemStack stack = te.getStackInSlot(2).isEmpty() ? te.getStackInSlot(0) : te.getStackInSlot(2);
		if (!stack.isEmpty()) {
			RenderHelper.enableStandardItemLighting();
			GlStateManager.enableLighting();
			GlStateManager.pushMatrix();
			
			GlStateManager.translate(.5, 0.656, .5);
			GlStateManager.rotate(90, 0, 1, 0);
			GlStateManager.rotate(-90, 1, 0, 0);
			GlStateManager.scale(0.8f, 0.8f, 0.8f);

			Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.NONE);

			GlStateManager.popMatrix();
		}

		GlStateManager.popMatrix();
		GlStateManager.popAttrib();
	}
}
