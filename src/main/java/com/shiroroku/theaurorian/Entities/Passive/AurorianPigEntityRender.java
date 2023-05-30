package com.shiroroku.theaurorian.Entities.Passive;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class AurorianPigEntityRender extends RenderLiving<AurorianPigEntity> {

	private final ResourceLocation mobTexture = new ResourceLocation(AurorianMod.MODID, "textures/entity/" + AurorianPigEntity.EntityName + ".png");

	public static final Factory FACTORY = new Factory();

	public AurorianPigEntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new AurorianPigEntityModel(), 0.7F);
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull AurorianPigEntity entity) {
		return mobTexture;
	}

	public static class Factory implements IRenderFactory<AurorianPigEntity> {
		@Override
		public Render<? super AurorianPigEntity> createRenderFor(RenderManager manager) {
			return new AurorianPigEntityRender(manager);
		}
	}
}
