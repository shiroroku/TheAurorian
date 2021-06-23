package com.elseytd.theaurorian.Entities.Boss;

import com.elseytd.theaurorian.TAMod;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class Keeper_EntityRender extends RenderLiving<Keeper_Entity> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/runestonedungeonkeeperlayer1.png");

	private float mobScale = 2F;

	public static final Factory FACTORY = new Factory();

	public Keeper_EntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new Keeper_EntityModel(), 0.5F);
		addLayer(new Keeper_EntityLayer(this));
		addLayer(new LayerHeldItem(this));
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull Keeper_Entity entity) {
		return mobTexture;
	}

	@Override
	protected void preRenderCallback(Keeper_Entity entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(mobScale, mobScale, mobScale);
	}

	public static class Factory implements IRenderFactory<Keeper_Entity> {

		@Override
		public Render<? super Keeper_Entity> createRenderFor(RenderManager manager) {
			return new Keeper_EntityRender(manager);
		}

	}
}
