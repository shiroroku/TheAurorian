package shiroroku.theaurorian.Blocks.Scrapper;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import shiroroku.theaurorian.TheAurorian;

public class ScrapperScreen extends AbstractContainerScreen<ScrapperMenu> {

    private final ResourceLocation GUI = ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "textures/gui/scrapper.png");

    public ScrapperScreen(ScrapperMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(GUI, relX, relY, 0, 0, this.imageWidth, this.imageHeight);

        float rotation = (Minecraft.getInstance().level.getGameTime() + partialTicks) * 4 * (this.menu.isCrafting() ? 1 : 0);

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(relX + 69, relY + 37, 0);
        guiGraphics.pose().translate(8, 8, 0);
        guiGraphics.pose().mulPose(Axis.ZP.rotationDegrees(rotation));
        guiGraphics.pose().translate(-8, -8, 0);
        guiGraphics.blit(GUI, 0, 0, 176, 55, 16, 16);
        guiGraphics.pose().popPose();

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(relX + 91, relY + 37, 0);
        guiGraphics.pose().translate(8, 8, 0);
        guiGraphics.pose().mulPose(Axis.ZP.rotationDegrees(-rotation));
        guiGraphics.pose().translate(-8, -8, 0);
        guiGraphics.blit(GUI, 0, 0, 176, 55, 16, 16);
        guiGraphics.pose().popPose();


        if (this.menu.isCrafting()) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(relX + 86, relY + 35, 0);
            guiGraphics.blit(GUI, 0, 0, 176, 0, 5, (int) (21 * this.menu.craftingProgress()));

            guiGraphics.pose().popPose();
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
