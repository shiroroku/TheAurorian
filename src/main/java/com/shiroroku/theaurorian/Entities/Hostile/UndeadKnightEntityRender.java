package com.shiroroku.theaurorian.Entities.Hostile;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class UndeadKnightEntityRender extends RenderLiving<UndeadKnightEntity> {

	private final ResourceLocation mobTexture = new ResourceLocation(AurorianMod.MODID, "textures/entity/" + UndeadKnightEntity.EntityName + ".png");
	private final float mobScale = UndeadKnightEntity.MobScale;

	public static final Factory FACTORY = new Factory();

	public UndeadKnightEntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new UndeadKnightEntityModel(), 0.5F);
		addLayer(new LayerHeldItem(this));
		addLayer(new LayerBipedArmor(this));
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull UndeadKnightEntity entity) {
		return mobTexture;
	}

	@Override
	protected void preRenderCallback(UndeadKnightEntity entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(mobScale, mobScale, mobScale);
	}

	public static class Factory implements IRenderFactory<UndeadKnightEntity> {

		@Override
		public Render<? super UndeadKnightEntity> createRenderFor(RenderManager manager) {
			return new UndeadKnightEntityRender(manager);
		}

	}
}
