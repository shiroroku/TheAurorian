package shiroroku.theaurorian.Items.MirrorOfGuidance;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import shiroroku.theaurorian.TheAurorian;
import shiroroku.theaurorian.Util.ModUtil;
import shiroroku.theaurorian.Util.RenderUtil;
import shiroroku.theaurorian.Util.SimpleTimer;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class MirrorOGScreen extends Screen {

    public static final ResourceLocation WIDGETS = ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "textures/gui/mirror_of_guidance.png");
    private static final ResourceLocation BACKGROUND_1 = ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "textures/gui/mirror_of_guidance_bg.png");
    private static final ResourceLocation BACKGROUND_2 = ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "textures/gui/mirror_of_guidance_bg_2.png");

    // Texture dimensions
    private static final int WIDTH = 256;
    private static final int HEIGHT = 224;
    private final SimpleTimer SoundTimer = new SimpleTimer(40, true);
    private final SimpleTimer LineTimer = new SimpleTimer(10, true);
    private final SimpleTimer LerpTimer = new SimpleTimer(6);
    private final SimpleTimer LerpPanTimer = new SimpleTimer(6);
    private final Random Rand = new Random();

    private double ViewX = 0;
    private double ViewY = 0;
    private double ViewXO = 0;
    private double ViewYO = 0;
    private double ViewLerpToX = 0;
    private double ViewLerpToY = 0;

    private MirrorNode selectedNode = null;
    private final List<MirrorNode> nodes;

    public MirrorOGScreen() {
        super(GameNarrator.NO_TITLE);
        SoundTimer.start();
        LineTimer.start();
        LerpTimer.setTick(0);
        nodes = MirrorDataLoader.NODES.values().stream().toList();
    }

    @Override
    public void onClose() {
        super.onClose();
        setSelectedNode(null);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        SoundTimer.tick(pPartialTick);
        LineTimer.tick(pPartialTick);
        LerpTimer.tick(pPartialTick);
        LerpPanTimer.tick(pPartialTick);

        int x_gui_left = (int) ((this.width - WIDTH) * 0.5f);
        int y_gui_top = (int) ((this.height - HEIGHT) * 0.5f);
        int x_gui_center = (int) (x_gui_left + WIDTH * 0.5f);
        int y_gui_center = (int) (y_gui_top + HEIGHT * 0.5f);
        final double mouse_x = pMouseX - x_gui_center - ViewX;
        final double mouse_y = pMouseY - y_gui_center - ViewY;

        // There are two lerp timers because the content gradient uses one for fading in + out and other is for reset view button
        if (LerpTimer.isActive()) {
            ViewX = Mth.lerp(Math.pow(LerpTimer.getPercentageProgress(), 1.5), ViewXO, ViewLerpToX);
            ViewY = Mth.lerp(Math.pow(LerpTimer.getPercentageProgress(), 1.5), ViewYO, ViewLerpToY);
        }
        if (LerpPanTimer.isActive()) {
            ViewX = Mth.lerp(LerpPanTimer.getPercentageProgress(), ViewXO, ViewLerpToX);
            ViewY = Mth.lerp(LerpPanTimer.getPercentageProgress(), ViewYO, ViewLerpToY);
        }

        if (SoundTimer.getTicks() % 40 == 0 && Rand.nextBoolean()) {
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.AMETHYST_BLOCK_CHIME, (float) (1.5 - Rand.nextDouble() * 0.5f), 1F));
        }

        // Game tint
        this.renderBackground(guiGraphics, pMouseX, pMouseY, pPartialTick);

        // Setup
        RenderSystem.enableBlend();
        guiGraphics.pose().pushPose();
        guiGraphics.enableScissor(x_gui_left, y_gui_top, x_gui_left + WIDTH - 5, y_gui_top + HEIGHT - 5);

        // BACKGROUND STARS
        RenderUtil.blitRepeating(BACKGROUND_1, x_gui_left, y_gui_top, 256, 256, (float) (-ViewX / 256) * 0.25f, (float) (-ViewY / 256) * 0.25f);
        RenderSystem.setShaderColor(1, 1, 1, ModUtil.wave(Util.getMillis(), 5f / 1000f, 0.5f) + 0.5f);
        RenderUtil.blitRepeating(BACKGROUND_2, x_gui_left, y_gui_top, 256, 256, (float) (-ViewX / 256) * 0.5f, (float) (-ViewY / 256) * 0.5f);
        RenderSystem.setShaderColor(1, 1, 1, 1);

        // Background Moon
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(x_gui_center, y_gui_center, 0); // align 0,0 with center
        guiGraphics.pose().translate(ViewX * 0.2f, ViewY * 0.2f, 0); // move to view
        guiGraphics.pose().mulPose(Axis.ZP.rotationDegrees(ModUtil.wave(Util.getMillis(), 1f / 1000f, 15)));
        guiGraphics.pose().scale(6, 6, 0);
        RenderSystem.setShaderColor(1, 1, 1, 0.2f);
        RenderUtil.blit(guiGraphics, WIDGETS, -4, -4, 32, 240, 8, 8, 256, 256);
        guiGraphics.pose().popPose();

        // NODE CONTENT
        float fade = selectedNode != null ? LerpTimer.getPercentageProgress() : 1 - LerpTimer.getPercentageProgress();
        RenderSystem.setShaderColor(1, 1, 1, fade);
        guiGraphics.fillGradient(x_gui_left, y_gui_top, x_gui_left + WIDTH, y_gui_top + HEIGHT, new Color(39, 20, 138, 255).getRGB(), new Color(0, 0, 0, 0).getRGB());
        if (selectedNode != null) {
            int fadeColor = new Color(1, 1, 1, Mth.clamp(fade, 0, 1)).getRGB();
            guiGraphics.drawString(this.font, selectedNode.name.copy().withStyle(ChatFormatting.ITALIC), x_gui_left + 42, y_gui_top + 14, fadeColor);
            guiGraphics.drawWordWrap(this.font, selectedNode.description, x_gui_left + 20, y_gui_top + 45, WIDTH - 20 * 2, fadeColor);
        }
        RenderSystem.setShaderColor(1, 1, 1, 1);

        // NODES & LINES
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(x_gui_center, y_gui_center, 0); // align 0,0 with center
        guiGraphics.pose().translate(ViewX, ViewY, 0); // move to view
        if (selectedNode != null) {
            selectedNode.render(guiGraphics, pMouseX, pMouseY, pPartialTick);
        } else {
            nodes.forEach(node -> node.renderLines(guiGraphics, LineTimer));
            nodes.forEach(node -> node.render(guiGraphics, pMouseX, pMouseY, pPartialTick));
        }
        guiGraphics.pose().popPose();

        // End of content
        guiGraphics.disableScissor();


        // BORDER
        RenderSystem.enableBlend();
        RenderUtil.blit(guiGraphics, WIDGETS, x_gui_left - 2, y_gui_top - 2, 0, 0, WIDTH, HEIGHT, 256, 256);

        // TOOLTIPS
        if (selectedNode == null) {
            for (MirrorNode node : nodes) {
                if (node.isMouseOver(mouse_x, mouse_y) && RenderUtil.isMouseOver(x_gui_left, y_gui_top, WIDTH - 8, HEIGHT, pMouseX, pMouseY)) {
                    guiGraphics.renderTooltip(this.font, node.name, pMouseX, pMouseY);
                }
            }
            RenderSystem.enableBlend();
        }

        // BUTTONS
        if (ViewX != 0 || ViewY != 0) {
            if (selectedNode == null) {
                if (RenderUtil.isMouseOver(x_gui_left + 4, y_gui_top + HEIGHT - 24, 16, 16, pMouseX, pMouseY)) {
                    RenderSystem.setShaderColor(1.5f, 1.5f, 1.5f, 1);
                }
                RenderUtil.blit(guiGraphics, WIDGETS, x_gui_left + 4, y_gui_top + HEIGHT - 24, 32, 224, 16, 16, 256, 256);
                RenderSystem.setShaderColor(1, 1, 1, 1);
            }
        }
        if (selectedNode != null) {
            if (RenderUtil.isMouseOver(x_gui_left + WIDTH - 28, y_gui_top + 8, 16, 16, pMouseX, pMouseY)) {
                RenderSystem.setShaderColor(1.3f, 1.3f, 1.3f, 1);
            }
            RenderUtil.blit(guiGraphics, WIDGETS, x_gui_left + WIDTH - 28, y_gui_top + 8, 48, 224, 16, 16, 256, 256);
            RenderSystem.setShaderColor(1, 1, 1, 1);
        }

        // Cleanup
        RenderSystem.disableBlend();
        guiGraphics.pose().popPose();
        super.render(guiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    /**
     * Handles closing the menu when inventory key is pressed
     */
    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (super.keyPressed(pKeyCode, pScanCode, pModifiers)) {
            return true;
        } else if (this.minecraft.options.keyInventory.isActiveAndMatches(InputConstants.getKey(pKeyCode, pScanCode))) {
            if (selectedNode == null) {
                this.onClose();
            } else {
                lerpTo(LerpTimer, -selectedNode.x, -selectedNode.y);
                setSelectedNode(null);
                playClickSound(1, SoundEvents.UI_TOAST_OUT);
            }
            return true;
        }
        return false;
    }

    private void lerpTo(SimpleTimer timer, double x, double y) {
        ViewXO = ViewX;
        ViewYO = ViewY;
        ViewLerpToX = x;
        ViewLerpToY = y;
        timer.start();
    }

    private void playClickSound(float pitch, SoundEvent sound) {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(sound, (float) (pitch - Rand.nextDouble() * 0.01f), 0.8F));
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        int x_gui_left = (int) ((this.width - WIDTH) * 0.5f);
        int y_gui_top = (int) ((this.height - HEIGHT) * 0.5f);
        int x_gui_center = (int) (x_gui_left + WIDTH * 0.5f);
        int y_gui_center = (int) (y_gui_top + HEIGHT * 0.5f);

        // Buttons
        if (selectedNode == null && RenderUtil.isMouseOver(x_gui_left + 4, y_gui_top + HEIGHT - 24, 16, 16, pMouseX, pMouseY)) { // reset view button
            lerpTo(LerpPanTimer, 0, 0);
            setSelectedNode(null);
            playClickSound(2f, SoundEvents.UI_TOAST_IN);
            return true;
        }
        if (selectedNode != null && RenderUtil.isMouseOver(x_gui_left + WIDTH - 28, y_gui_top + 8, 16, 16, pMouseX, pMouseY)) { // close button
            lerpTo(LerpTimer, -selectedNode.x, -selectedNode.y);
            setSelectedNode(null);
            playClickSound(1, SoundEvents.UI_TOAST_OUT);
            return true;
        }

        // Nodes
        if (selectedNode == null && RenderUtil.isMouseOver(x_gui_left, y_gui_top, WIDTH - 8, HEIGHT, pMouseX, pMouseY)) {
            for (MirrorNode node : nodes) {
                if (RenderUtil.isMouseOver((int) (ViewX + x_gui_center + node.x - 12), (int) (ViewY + y_gui_center + node.y - 12), 24, 24, pMouseX, pMouseY)) {
                    setSelectedNode(node);
                    lerpTo(LerpTimer, -selectedNode.x - 108, -selectedNode.y - 92);
                    playClickSound(1.5f, SoundEvents.UI_TOAST_IN);
                    break;
                }
            }
        }

        return super.mouseReleased(pMouseX, pMouseY, pButton);
    }

    private void setSelectedNode(MirrorNode node) {
        if (this.selectedNode != null && this.selectedNode != node) {
            this.selectedNode.setSelected(false); // if theres already something selected, deselect it
        }
        this.selectedNode = node;
        if (node != null) {
            this.selectedNode.setSelected(true);
        }
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        if (!LerpPanTimer.isActive() && selectedNode == null) {
            ViewX += pDragX;
            ViewY += pDragY;
            return true;
        }
        return false;
    }
}
