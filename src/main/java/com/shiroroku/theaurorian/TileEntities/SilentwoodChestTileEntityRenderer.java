package com.shiroroku.theaurorian.TileEntities;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SilentwoodChestTileEntityRenderer extends TileEntitySpecialRenderer<SilentwoodChestTileEntity> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(AurorianMod.MODID, "textures/entity/chest/silentwoodchest.png");
	private static final ResourceLocation TEXTURE_DOUBLE = new ResourceLocation(AurorianMod.MODID, "textures/entity/chest/silentwoodchestdouble.png");

	private final ModelChest simpleChest = new ModelChest();
	private final ModelChest largeChest = new ModelLargeChest();

	@Override
	public void render(SilentwoodChestTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.enableDepth();
		GlStateManager.depthFunc(515);
		GlStateManager.depthMask(true);
		int directionmeta;
		if (te.hasWorld()) {
			Block block = te.getBlockType();
			directionmeta = te.getBlockMetadata();
			if (block instanceof BlockChest && directionmeta == 0) {
				((BlockChest) block).checkForSurroundingChests(te.getWorld(), te.getPos(), te.getWorld().getBlockState(te.getPos()));
				directionmeta = te.getBlockMetadata();
			}
			te.checkForAdjacentChests();
		} else {
			directionmeta = 0;
		}

		if (te.adjacentChestZNeg == null && te.adjacentChestXNeg == null) {
			ModelChest modelchest;
			if (te.adjacentChestXPos == null && te.adjacentChestZPos == null) {
				modelchest = this.simpleChest;
				if (destroyStage >= 0) {
					this.bindTexture(DESTROY_STAGES[destroyStage]);
					GlStateManager.matrixMode(5890);
					GlStateManager.pushMatrix();
					GlStateManager.scale(4.0F, 4.0F, 1.0F);
					GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
					GlStateManager.matrixMode(5888);
				} else {
					this.bindTexture(TEXTURE);
				}
			} else {
				modelchest = this.largeChest;
				if (destroyStage >= 0) {
					this.bindTexture(DESTROY_STAGES[destroyStage]);
					GlStateManager.matrixMode(5890);
					GlStateManager.pushMatrix();
					GlStateManager.scale(8.0F, 4.0F, 1.0F);
					GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
					GlStateManager.matrixMode(5888);
				} else {
					this.bindTexture(TEXTURE_DOUBLE);
				}
			}
			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			if (destroyStage < 0) {
				GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
			}
			GlStateManager.translate((float) x, (float) y + 1.0F, (float) z + 1.0F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.translate(0.5F, 0.5F, 0.5F);
			int degrees = 0;
			if (directionmeta == 2) {
				degrees = 180;
			}
			if (directionmeta == 3) {
				degrees = 0;
			}
			if (directionmeta == 4) {
				degrees = 90;
			}
			if (directionmeta == 5) {
				degrees = -90;
			}
			if (directionmeta == 2 && te.adjacentChestXPos != null) {
				GlStateManager.translate(1.0F, 0.0F, 0.0F);
			}
			if (directionmeta == 5 && te.adjacentChestZPos != null) {
				GlStateManager.translate(0.0F, 0.0F, -1.0F);
			}
			GlStateManager.rotate(degrees, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-0.5F, -0.5F, -0.5F);
			float f = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;
			if (te.adjacentChestZNeg != null) {
				float f1 = te.adjacentChestZNeg.prevLidAngle + (te.adjacentChestZNeg.lidAngle - te.adjacentChestZNeg.prevLidAngle) * partialTicks;
				if (f1 > f) {
					f = f1;
				}
			}
			if (te.adjacentChestXNeg != null) {
				float f2 = te.adjacentChestXNeg.prevLidAngle + (te.adjacentChestXNeg.lidAngle - te.adjacentChestXNeg.prevLidAngle) * partialTicks;
				if (f2 > f) {
					f = f2;
				}
			}
			f = 1.0F - f;
			f = 1.0F - f * f * f;
			modelchest.chestLid.rotateAngleX = -(f * ((float) Math.PI / 2F));
			modelchest.renderAll();
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			if (destroyStage >= 0) {
				GlStateManager.matrixMode(5890);
				GlStateManager.popMatrix();
				GlStateManager.matrixMode(5888);
			}
		}
	}
}
