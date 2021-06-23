package com.elseytd.theaurorian.Entities.Boss;

import com.elseytd.theaurorian.TAMod;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Spider_EntityRender extends RenderLiving<Spider_Entity> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/spider.png");

	private float mobScale = 2F;

	public static final Factory FACTORY = new Factory();

	public Spider_EntityRender(RenderManager renderManagerIn) {
		super(renderManagerIn, new Spider_EntityModel(), 2.0F);
	}

	@Override
	protected float getDeathMaxRotation(Spider_Entity entityLivingBaseIn) {
		return 180.0F;
	}

	@Override
	protected ResourceLocation getEntityTexture(Spider_Entity entity) {
		return this.mobTexture;
	}

	@Override
	protected void preRenderCallback(Spider_Entity entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(this.mobScale, this.mobScale, this.mobScale);
		if (entitylivingbaseIn.isHanging()) {
			GlStateManager.translate(0, 0, 0.5);
			GlStateManager.rotate(90, 1, 0, 0);
		}
	}

	public static class Factory implements IRenderFactory<Spider_Entity> {
		@Override
		public Render<? super Spider_Entity> createRenderFor(RenderManager manager) {
			return new Spider_EntityRender(manager);
		}
	}

}
