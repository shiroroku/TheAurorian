package shiroroku.theaurorian.Blocks.MoonlightForge;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import shiroroku.theaurorian.TheAurorian;

public class MoonlightForgeScreen extends AbstractContainerScreen<MoonlightForgeMenu> {

    private final ResourceLocation GUI = ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "textures/gui/moonlight_forge.png");

    public MoonlightForgeScreen(MoonlightForgeMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(GUI, relX, relY, 0, 0, this.imageWidth, this.imageHeight);

        float rotation = (Minecraft.getInstance().level.getGameTime() + pPartialTick) * 4 * (this.menu.isCrafting() ? 1 : 0);

        if (this.menu.isCrafting()) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(relX + 49, relY + 31, 0);
            guiGraphics.pose().translate(12, 12, 0);
            guiGraphics.pose().mulPose(Axis.ZP.rotationDegrees(rotation));
            guiGraphics.pose().translate(-12, -12, 0);
            guiGraphics.blit(GUI, 0, 0, 176, 41, 24, 24);
            guiGraphics.pose().popPose();

            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(relX + 107, relY + 35, 0);
            guiGraphics.blit(GUI, 0, 0, 176, 24, (int) (24 * this.menu.craftingProgress()), 17);
            guiGraphics.pose().popPose();
        }

        if (!this.menu.canSeeMoon()) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(relX + 49, relY + 31, 0);
            guiGraphics.blit(GUI, 0, 0, 176, 0, 24, 24);
            guiGraphics.pose().popPose();
            if (this.isHovering(49, 31, 24, 24, pMouseX, pMouseY)) {
                guiGraphics.renderTooltip(Minecraft.getInstance().font, Component.translatable("block.theaurorian.moonlight_forge.no_moon"), pMouseX, pMouseY);
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
