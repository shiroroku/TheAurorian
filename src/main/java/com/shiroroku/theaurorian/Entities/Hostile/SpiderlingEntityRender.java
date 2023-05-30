package com.shiroroku.theaurorian.Entities.Hostile;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SpiderlingEntityRender extends RenderLiving<SpiderlingEntity> {

	private final ResourceLocation mobTexture = new ResourceLocation(AurorianMod.MODID, "textures/entity/spiderling.png");

	private final float mobScale = 0.5F;

	public static final Factory FACTORY = new Factory();

	public SpiderlingEntityRender(RenderManager renderManagerIn) {
		super(renderManagerIn, new SpiderlingEntityModel(), 0.2F);
	}

	@Override
	protected float getDeathMaxRotation(SpiderlingEntity entityLivingBaseIn) {
		return 180.0F;
	}

	@Override
	protected ResourceLocation getEntityTexture(SpiderlingEntity entity) {
		return mobTexture;
	}

	@Override
	protected void preRenderCallback(SpiderlingEntity entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(mobScale, mobScale, mobScale);
	}

	public static class Factory implements IRenderFactory<SpiderlingEntity> {
		@Override
		public Render<? super SpiderlingEntity> createRenderFor(RenderManager manager) {
			return new SpiderlingEntityRender(manager);
		}
	}

}
