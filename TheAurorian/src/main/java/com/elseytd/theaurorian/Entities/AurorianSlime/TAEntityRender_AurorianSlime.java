package com.elseytd.theaurorian.Entities.AurorianSlime;

import javax.annotation.Nonnull;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TAEntityRender_AurorianSlime extends RenderLiving<TAEntity_AurorianSlime> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/" + TAEntity_AurorianSlime.EntityName + ".png");

	public static final Factory FACTORY = new Factory();

	public TAEntityRender_AurorianSlime(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelSlime(16), 0.25F);
		this.addLayer(new TAEntityLayer_AurorianSlime(this));
	}

	@Override
	public void doRender(TAEntity_AurorianSlime entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.shadowSize = 0.25F;
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected void preRenderCallback(TAEntity_AurorianSlime entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(0.999F, 0.999F, 0.999F);
		float f1 = (float) 1;
		float f2 = (entitylivingbaseIn.prevSquishFactor + (entitylivingbaseIn.squishFactor - entitylivingbaseIn.prevSquishFactor) * partialTickTime) / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		GlStateManager.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull TAEntity_AurorianSlime entity) {
		return mobTexture;
	}

	public static class Factory implements IRenderFactory<TAEntity_AurorianSlime> {
		@Override
		public Render<? super TAEntity_AurorianSlime> createRenderFor(RenderManager manager) {
			return new TAEntityRender_AurorianSlime(manager);
		}
	}
}
