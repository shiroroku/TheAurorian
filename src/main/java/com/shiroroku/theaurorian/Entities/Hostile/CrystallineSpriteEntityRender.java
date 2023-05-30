package com.shiroroku.theaurorian.Entities.Hostile;

import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class CrystallineSpriteEntityRender extends Render<CrystallineSpriteEntity> {

	public static final Factory FACTORY = new Factory();
	ResourceLocation rl = new ResourceLocation(AurorianMod.MODID, "textures/entity/crystallinesprite.png");

	public CrystallineSpriteEntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn);
	}

	@Override
	public void doRender(CrystallineSpriteEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + 0.25, (float) z);
		GlStateManager.scale(4, 4, 4);
		GlStateManager.rotate(-entity.rotationYawHead, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(entity.rotationPitch, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);

		Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(ItemRegistry.Registry.CRYSTALLINESPRITE.getItem()), ItemCameraTransforms.TransformType.GROUND);

		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull CrystallineSpriteEntity entity) {
		return this.rl;
	}

	public static class Factory implements IRenderFactory<CrystallineSpriteEntity> {

		@Override
		public Render<? super CrystallineSpriteEntity> createRenderFor(RenderManager manager) {
			return new CrystallineSpriteEntityRender(manager);
		}

	}
}
