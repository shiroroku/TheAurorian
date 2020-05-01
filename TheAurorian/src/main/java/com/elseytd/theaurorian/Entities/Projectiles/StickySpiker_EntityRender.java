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

public class StickySpiker_EntityRender extends Render<StickySpiker_Entity> {

	public static final Factory FACTORY = new Factory();

	public StickySpiker_EntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn);
	}

	@Override
	public void doRender(StickySpiker_Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.enableRescaleNormal();
		GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate((float) (this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

		if (this.renderOutlines) {
			GlStateManager.enableColorMaterial();
			GlStateManager.enableOutlineMode(this.getTeamColor(entity));
		}

		Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(TAItems.stickyspiker), ItemCameraTransforms.TransformType.GROUND);

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
	protected ResourceLocation getEntityTexture(@Nonnull StickySpiker_Entity entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

	public static class Factory implements IRenderFactory<StickySpiker_Entity> {

		@Override
		public Render<? super StickySpiker_Entity> createRenderFor(RenderManager manager) {
			return new StickySpiker_EntityRender(manager);
		}

	}
}
