package com.shiroroku.theaurorian.Entities.Boss;

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
public class SpiderEntityRender extends RenderLiving<SpiderEntity> {

	private final ResourceLocation mobTexture = new ResourceLocation(AurorianMod.MODID, "textures/entity/spider.png");

	private final float mobScale = 2F;

	public static final Factory FACTORY = new Factory();

	public SpiderEntityRender(RenderManager renderManagerIn) {
		super(renderManagerIn, new SpiderEntityModel(), 2.0F);
	}

	@Override
	protected float getDeathMaxRotation(SpiderEntity entityLivingBaseIn) {
		return 180.0F;
	}

	@Override
	protected ResourceLocation getEntityTexture(SpiderEntity entity) {
		return this.mobTexture;
	}

	@Override
	protected void preRenderCallback(SpiderEntity entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(this.mobScale, this.mobScale, this.mobScale);
		if (entitylivingbaseIn.isHanging()) {
			GlStateManager.translate(0, 0, 0.5);
			GlStateManager.rotate(90, 1, 0, 0);
		}
	}

	public static class Factory implements IRenderFactory<SpiderEntity> {
		@Override
		public Render<? super SpiderEntity> createRenderFor(RenderManager manager) {
			return new SpiderEntityRender(manager);
		}
	}

}
