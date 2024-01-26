package shiroroku.theaurorian.Blocks.MoonlightForge;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import shiroroku.theaurorian.TheAurorian;

public class MoonlightForgeScreen extends AbstractContainerScreen<MoonlightForgeMenu> {

    private final ResourceLocation GUI = new ResourceLocation(TheAurorian.MODID, "textures/gui/moonlight_forge.png");

    public MoonlightForgeScreen(MoonlightForgeMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(pPoseStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);

        float rotation = (Minecraft.getInstance().level.getGameTime() + pPartialTick) * 4 * (this.menu.isCrafting() ? 1 : 0);

        if (this.menu.isCrafting()) {
            pPoseStack.pushPose();
            pPoseStack.translate(relX + 49, relY + 31, 0);
            pPoseStack.translate(12, 12, 0);
            pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(rotation));
            pPoseStack.translate(-12, -12, 0);
            this.blit(pPoseStack, 0, 0, 176, 41, 24, 24);
            pPoseStack.popPose();

            pPoseStack.pushPose();
            pPoseStack.translate(relX + 107, relY + 35, 0);
            this.blit(pPoseStack, 0, 0, 176, 24, (int) (24 * this.menu.craftingProgress()), 17);
            pPoseStack.popPose();
        }

        if (!this.menu.canSeeMoon()) {
            pPoseStack.pushPose();
            pPoseStack.translate(relX + 49, relY + 31, 0);
            this.blit(pPoseStack, 0, 0, 176, 0, 24, 24);
            pPoseStack.popPose();
            if (this.isHovering(49, 31, 24, 24, pMouseX, pMouseY)) {
                this.renderTooltip(pPoseStack, Component.translatable("block.theaurorian.moonlight_forge.no_moon"), pMouseX, pMouseY);
            }
        }
    }
}
