package com.shiroroku.theaurorian.TileEntities;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class CrystalBlockTileEntityRenderer extends TileEntitySpecialRenderer<CrystalBlockTileEntity> {
	@Override
	public void render(CrystalBlockTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushAttrib();
		GlStateManager.pushMatrix();

		GlStateManager.translate(x, y, z);
		GlStateManager.disableRescaleNormal();

		GlStateManager.translate(.5, 0, .5);
		long angle = (System.currentTimeMillis() / 10) % 360;
		GlStateManager.rotate(angle, 0, 1, 0);
		GlStateManager.translate(-.5, 0, -.5);

		double time = System.currentTimeMillis() / 10;
		double amp = 0.1;
		double freq = 0.03;
		double height = amp * Math.sin(time * freq);
		GlStateManager.translate(0, height, 0);

		RenderHelper.disableStandardItemLighting();
		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		if (Minecraft.isAmbientOcclusionEnabled()) {
			GlStateManager.shadeModel(GL11.GL_SMOOTH);
		} else {
			GlStateManager.shadeModel(GL11.GL_FLAT);
		}

		World world = te.getWorld();
		GlStateManager.translate(-te.getPos().getX(), -te.getPos().getY(), -te.getPos().getZ());

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferBuilder = tessellator.getBuffer();
		bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

		IBlockState state = BlockRegistry.Registry.CRYSTAL.getBlock().getDefaultState();
		BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		IBakedModel model = dispatcher.getModelForState(state);
		dispatcher.getBlockModelRenderer().renderModel(world, model, state, te.getPos(), bufferBuilder, true);
		tessellator.draw();

		RenderHelper.enableStandardItemLighting();

		GlStateManager.popMatrix();
		GlStateManager.popAttrib();
	}
}
