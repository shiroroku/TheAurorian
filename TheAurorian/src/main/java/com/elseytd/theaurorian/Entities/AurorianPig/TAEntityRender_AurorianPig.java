package com.elseytd.theaurorian.Entities.AurorianPig;

import javax.annotation.Nonnull;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TAEntityRender_AurorianPig extends RenderLiving<TAEntity_AurorianPig> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/" + TAEntity_AurorianPig.EntityName + ".png");

	public static final Factory FACTORY = new Factory();

	public TAEntityRender_AurorianPig(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new TAEntityModel_AurorianPig(), 0.7F);
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull TAEntity_AurorianPig entity) {
		return mobTexture;
	}

	public static class Factory implements IRenderFactory<TAEntity_AurorianPig> {
		@Override
		public Render<? super TAEntity_AurorianPig> createRenderFor(RenderManager manager) {
			return new TAEntityRender_AurorianPig(manager);
		}
	}
}
