package com.elseytd.theaurorian.Entities.Projectiles;

import javax.annotation.Nonnull;

import com.elseytd.theaurorian.TAItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class Webbing_EntityRender extends Render<Webbing_Entity> {

	public static final Factory FACTORY = new Factory();

	public Webbing_EntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn);
	}

	@Override
	public void doRender(Webbing_Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.enableRescaleNormal();
		GlStateManager.scale(1.8, 1.8, 1.8);
		GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate((float) (this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

		if (this.renderOutlines) {
			GlStateManager.enableColorMaterial();
			GlStateManager.enableOutlineMode(this.getTeamColor(entity));
		}

		Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(TAItems.webbing), ItemCameraTransforms.TransformType.GROUND);

		if (this.renderOutlines) {
			GlStateManager.disableOutlineMode();
			GlStateManager.disableColorMaterial();
		}

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}


	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull Webbing_Entity entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

	public static class Factory implements IRenderFactory<Webbing_Entity> {

		@Override
		public Render<? super Webbing_Entity> createRenderFor(RenderManager manager) {
			return new Webbing_EntityRender(manager);
		}

	}
}
