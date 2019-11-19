package com.elseytd.theaurorian.Entities.Keeper;

import javax.annotation.Nonnull;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class TAEntityRender_RunestoneDungeonKeeper extends RenderLiving<TAEntity_RunestoneDungeonKeeper> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/runestonedungeonkeeperlayer1.png");

	private float mobScale = 2F;

	public static final Factory FACTORY = new Factory();

	public TAEntityRender_RunestoneDungeonKeeper(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new TAEntityModel_RunestoneDungeonKeeper(), 0.5F);
		addLayer(new TAEntityLayer_RunestoneDungeonKeeper(this));
		addLayer(new LayerHeldItem(this));
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull TAEntity_RunestoneDungeonKeeper entity) {
		return mobTexture;
	}

	@Override
	protected void preRenderCallback(TAEntity_RunestoneDungeonKeeper entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(mobScale, mobScale, mobScale);
	}

	public static class Factory implements IRenderFactory<TAEntity_RunestoneDungeonKeeper> {

		@Override
		public Render<? super TAEntity_RunestoneDungeonKeeper> createRenderFor(RenderManager manager) {
			return new TAEntityRender_RunestoneDungeonKeeper(manager);
		}

	}
}
