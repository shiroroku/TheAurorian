package com.elseytd.theaurorian.Entities.Hostile;

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
public class AurorianSlime_EntityRender extends RenderLiving<AurorianSlime_Entity> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/" + AurorianSlime_Entity.EntityName + ".png");

	public static final Factory FACTORY = new Factory();

	public AurorianSlime_EntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelSlime(16), 0.25F);
		this.addLayer(new AurorianSlime_EntityLayer(this));
	}

	@Override
	public void doRender(AurorianSlime_Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.shadowSize = 0.25F;
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected void preRenderCallback(AurorianSlime_Entity entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(0.999F, 0.999F, 0.999F);
		float f1 = (float) 1;
		float f2 = (entitylivingbaseIn.prevSquishFactor + (entitylivingbaseIn.squishFactor - entitylivingbaseIn.prevSquishFactor) * partialTickTime) / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		GlStateManager.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull AurorianSlime_Entity entity) {
		return mobTexture;
	}

	public static class Factory implements IRenderFactory<AurorianSlime_Entity> {
		@Override
		public Render<? super AurorianSlime_Entity> createRenderFor(RenderManager manager) {
			return new AurorianSlime_EntityRender(manager);
		}
	}
}
