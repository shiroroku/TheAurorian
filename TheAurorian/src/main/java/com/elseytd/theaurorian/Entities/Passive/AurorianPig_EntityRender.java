package com.elseytd.theaurorian.Entities.Passive;

import com.elseytd.theaurorian.TAMod;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class AurorianPig_EntityRender extends RenderLiving<AurorianPig_Entity> {

	private ResourceLocation mobTexture = new ResourceLocation(TAMod.MODID, "textures/entity/" + AurorianPig_Entity.EntityName + ".png");

	public static final Factory FACTORY = new Factory();

	public AurorianPig_EntityRender(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new AurorianPig_EntityModel(), 0.7F);
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull AurorianPig_Entity entity) {
		return mobTexture;
	}

	public static class Factory implements IRenderFactory<AurorianPig_Entity> {
		@Override
		public Render<? super AurorianPig_Entity> createRenderFor(RenderManager manager) {
			return new AurorianPig_EntityRender(manager);
		}
	}
}
