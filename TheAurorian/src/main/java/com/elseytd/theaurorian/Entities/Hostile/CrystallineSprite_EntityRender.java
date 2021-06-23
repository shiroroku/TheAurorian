package com.elseytd.theaurorian.Entities.Hostile;

import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.TAMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class CrystallineSprite_EntityRender extends Render<CrystallineSprite_Entity> {

	public static final Factory FACTORY = new Factory();
	ResourceLocation rl = new ResourceLocation(TAMod.MODID, "textures/entity/crystallinesprite.png");

	public CrystallineSprite_EntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn);
	}

	@Override
	public void doRender(CrystallineSprite_Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + 0.25, (float) z);
		GlStateManager.scale(4, 4, 4);
		GlStateManager.rotate(-entity.rotationYawHead, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(entity.rotationPitch, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);

		Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(TAItems.Registry.CRYSTALLINESPRITE.getItem()), ItemCameraTransforms.TransformType.GROUND);

		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull CrystallineSprite_Entity entity) {
		return this.rl;
	}

	public static class Factory implements IRenderFactory<CrystallineSprite_Entity> {

		@Override
		public Render<? super CrystallineSprite_Entity> createRenderFor(RenderManager manager) {
			return new CrystallineSprite_EntityRender(manager);
		}

	}
}
