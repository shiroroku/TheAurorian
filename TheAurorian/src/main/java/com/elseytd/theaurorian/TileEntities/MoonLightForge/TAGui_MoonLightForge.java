package com.elseytd.theaurorian.TileEntities.MoonLightForge;

import com.elseytd.theaurorian.TAMod;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TAGui_MoonLightForge extends GuiContainer {
	private static final ResourceLocation TEXTURES = new ResourceLocation(TAMod.MODID, "textures/gui/moonlightforge.png");
	private final InventoryPlayer playerInventory;
	private final IInventory inventory;

	public TAGui_MoonLightForge(InventoryPlayer playerInv, IInventory inventory) {
		super(new TAContainer_MoonLightForge(playerInv, inventory));
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

		if (!hasMoonlight()) {
			int i = (this.width - this.xSize) / 2;
			int j = (this.height - this.ySize) / 2;
			if (mouseX >= i + 49 && mouseY >= j + 31) {
				if (mouseX <= i + 49 + 24 && mouseY <= j + 31 + 24) {
					this.drawHoveringText("No Moonlight Detected!", mouseX - i, mouseY - j);
				}
			}
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		if (!hasMoonlight()) {
			this.drawTexturedModalRect(i + 49, j + 31, 176, 0, 24, 24);
		}

		if (getCraftProgress() > 0) {
			int scaled = (int)(((float)getCraftProgress() / 100) * 24);
			this.drawTexturedModalRect(i + 107, j + 35, 176, 24, scaled, 17);
		}

	}

	private boolean hasMoonlight() {
		switch (this.inventory.getField(0)) {
		case 0:
			return false;
		case 1:
			return true;
		}
		return false;
	}

	private int getCraftProgress() {
		return this.inventory.getField(1);
	}

}
