package com.shiroroku.theaurorian.Entities.Projectiles;

import com.shiroroku.theaurorian.Registry.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class StickySpikerEntityRender extends Render<StickySpikerEntity> {

	public static final Factory FACTORY = new Factory();

	public StickySpikerEntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn);
	}

	@Override
	public void doRender(StickySpikerEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.enableRescaleNormal();
		GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate((this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

		if (this.renderOutlines) {
			GlStateManager.enableColorMaterial();
			GlStateManager.enableOutlineMode(this.getTeamColor(entity));
		}

		Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(ItemRegistry.Registry.STICKYSPIKER.getItem()), ItemCameraTransforms.TransformType.GROUND);

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
	protected ResourceLocation getEntityTexture(@Nonnull StickySpikerEntity entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

	public static class Factory implements IRenderFactory<StickySpikerEntity> {

		@Override
		public Render<? super StickySpikerEntity> createRenderFor(RenderManager manager) {
			return new StickySpikerEntityRender(manager);
		}

	}
}
