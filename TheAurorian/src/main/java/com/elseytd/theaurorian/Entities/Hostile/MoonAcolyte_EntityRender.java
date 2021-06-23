package com.elseytd.theaurorian.Entities.Hostile;

import com.elseytd.theaurorian.TAMod;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class MoonAcolyte_EntityRender extends RenderLiving<MoonAcolyte_Entity> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/" + MoonAcolyte_Entity.EntityName + ".png");
	public static final float MobScale = 1F;
	public static final Factory FACTORY = new Factory();

	public MoonAcolyte_EntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelZombie(), 0.5F);
		addLayer(new MoonAcolyte_EntityLayer(this));
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull MoonAcolyte_Entity entity) {
		return mobTexture;
	}

	@Override
	protected void preRenderCallback(MoonAcolyte_Entity entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(MobScale, MobScale, MobScale);
	}

	public static class Factory implements IRenderFactory<MoonAcolyte_Entity> {

		@Override
		public Render<? super MoonAcolyte_Entity> createRenderFor(RenderManager manager) {
			return new MoonAcolyte_EntityRender(manager);
		}

	}
}
