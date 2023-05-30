package com.shiroroku.theaurorian.Entities.Boss;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class KeeperEntityRender extends RenderLiving<KeeperEntity> {

	private final ResourceLocation mobTexture = new ResourceLocation(AurorianMod.MODID, "textures/entity/runestonedungeonkeeperlayer1.png");

	private final float mobScale = 2F;

	public static final Factory FACTORY = new Factory();

	public KeeperEntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new KeeperEntityModel(), 0.5F);
		addLayer(new KeeperEntityLayer(this));
		addLayer(new LayerHeldItem(this));
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull KeeperEntity entity) {
		return mobTexture;
	}

	@Override
	protected void preRenderCallback(KeeperEntity entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(mobScale, mobScale, mobScale);
	}

	public static class Factory implements IRenderFactory<KeeperEntity> {

		@Override
		public Render<? super KeeperEntity> createRenderFor(RenderManager manager) {
			return new KeeperEntityRender(manager);
		}

	}
}
