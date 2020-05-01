package com.elseytd.theaurorian.Entities.Boss;

import javax.annotation.Nonnull;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class MoonQueen_EntityRender extends RenderLiving<MoonQueen_Entity> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/" + MoonQueen_Entity.EntityName + ".png");
	private float mobScale = MoonQueen_Entity.MobScale;

	public static final Factory FACTORY = new Factory();

	public MoonQueen_EntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new MoonQueen_EntityModel(), 0.5F);
		this.addLayer(new LayerHeldItem(this));
		this.addLayer(new LayerBipedArmor(this));
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull MoonQueen_Entity entity) {
		return mobTexture;
	}

	@Override
	protected void preRenderCallback(MoonQueen_Entity entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(mobScale, mobScale, mobScale);
	}

	public static class Factory implements IRenderFactory<MoonQueen_Entity> {

		@Override
		public Render<? super MoonQueen_Entity> createRenderFor(RenderManager manager) {
			return new MoonQueen_EntityRender(manager);
		}

	}
}
