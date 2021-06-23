package com.elseytd.theaurorian.Entities.Hostile;

import com.elseytd.theaurorian.TAMod;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class UndeadKnight_EntityRender extends RenderLiving<UndeadKnight_Entity> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/" + UndeadKnight_Entity.EntityName + ".png");
	private float mobScale = UndeadKnight_Entity.MobScale;

	public static final Factory FACTORY = new Factory();

	public UndeadKnight_EntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new UndeadKnight_EntityModel(), 0.5F);
		addLayer(new LayerHeldItem(this));
		addLayer(new LayerBipedArmor(this));
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull UndeadKnight_Entity entity) {
		return mobTexture;
	}

	@Override
	protected void preRenderCallback(UndeadKnight_Entity entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(mobScale, mobScale, mobScale);
	}

	public static class Factory implements IRenderFactory<UndeadKnight_Entity> {

		@Override
		public Render<? super UndeadKnight_Entity> createRenderFor(RenderManager manager) {
			return new UndeadKnight_EntityRender(manager);
		}

	}
}
