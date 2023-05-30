package com.shiroroku.theaurorian.TileEntities;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ScrapperGui extends GuiContainer {

	private static final ResourceLocation TEXTURES = new ResourceLocation(AurorianMod.MODID, "textures/gui/scrapper.png");
	private final InventoryPlayer playerInventory;
	private final IInventory inventory;
	private float craftRotation = 0.0f;

	public ScrapperGui(InventoryPlayer playerInv, IInventory inventory) {
		super(new ScrapperContainer(playerInv, inventory));
		this.playerInventory = playerInv;
		this.inventory = inventory;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = this.inventory.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
		this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		GL11.glPushMatrix();
		float x = (float) (i + 62 + 8);
		float y = (float) (j + 37 + 8);
		GL11.glTranslatef(x, y, 0);

		if (getCraftProgress() > 0) {
			GL11.glRotatef(this.craftRotation, 0, 0, 45);
			this.craftRotation += 0.25f;
		}

		GL11.glTranslatef(-x, -y, 0f);
		this.drawTexturedModalRect(i + 62, j + 37, 176, 55, 16, 16);
		GL11.glPopMatrix();

		int scaled = (int) (((float) getCraftProgress() / 100) * 55);
		this.drawTexturedModalRect(i + 98, j + 16, 176, 0, 9, scaled);

	}

	private int getCraftProgress() {
		return this.inventory.getField(1);
	}

}
