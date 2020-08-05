package com.elseytd.theaurorian.Entities.Hostile;

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
public class Spiderling_EntityRender extends RenderLiving<Spiderling_Entity> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/spiderling.png");

	private float mobScale = 0.5F;

	public static final Factory FACTORY = new Factory();

	public Spiderling_EntityRender(RenderManager renderManagerIn) {
		super(renderManagerIn, new Spiderling_EntityModel(), 0.2F);
	}

	@Override
	protected float getDeathMaxRotation(Spiderling_Entity entityLivingBaseIn) {
		return 180.0F;
	}

	@Override
	protected ResourceLocation getEntityTexture(Spiderling_Entity entity) {
		return mobTexture;
	}

	@Override
	protected void preRenderCallback(Spiderling_Entity entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(mobScale, mobScale, mobScale);
	}

	public static class Factory implements IRenderFactory<Spiderling_Entity> {
		@Override
		public Render<? super Spiderling_Entity> createRenderFor(RenderManager manager) {
			return new Spiderling_EntityRender(manager);
		}
	}

}
