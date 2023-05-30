package com.shiroroku.theaurorian.Entities.Hostile;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class AurorianSlimeEntityRender extends RenderLiving<AurorianSlimeEntity> {

	private final ResourceLocation mobTexture = new ResourceLocation(AurorianMod.MODID, "textures/entity/" + AurorianSlimeEntity.EntityName + ".png");

	public static final Factory FACTORY = new Factory();

	public AurorianSlimeEntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelSlime(16), 0.25F);
		this.addLayer(new AurorianSlimeEntityLayer(this));
	}

	@Override
	public void doRender(AurorianSlimeEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.shadowSize = 0.25F;
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected void preRenderCallback(AurorianSlimeEntity entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(0.999F, 0.999F, 0.999F);
		float f1 = (float) 1;
		float f2 = (entitylivingbaseIn.prevSquishFactor + (entitylivingbaseIn.squishFactor - entitylivingbaseIn.prevSquishFactor) * partialTickTime) / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		GlStateManager.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull AurorianSlimeEntity entity) {
		return mobTexture;
	}

	public static class Factory implements IRenderFactory<AurorianSlimeEntity> {
		@Override
		public Render<? super AurorianSlimeEntity> createRenderFor(RenderManager manager) {
			return new AurorianSlimeEntityRender(manager);
		}
	}
}
