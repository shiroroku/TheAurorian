package shiroroku.theaurorian.Items.MirrorOfGuidance;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec2;
import shiroroku.theaurorian.TheAurorian;
import shiroroku.theaurorian.Util.RenderUtil;
import shiroroku.theaurorian.Util.SimpleTimer;

import java.util.List;

public class MirrorNode {

    public enum NODE_BORDER {
        REGULAR("REGULAR"),
        BLUE("BLUE"),
        GOLD("GOLD");

        public final String key;

        NODE_BORDER(String key) {
            this.key = key;
        }
    }

    public final Component name;
    public final Component description;
    public final NODE_BORDER border;
    public final Item icon;
    public final int x;
    public final int y;
    private final List<ResourceLocation> children;

    private boolean selected = false;
    private final SimpleTimer SpinTimer = new SimpleTimer(200, true);

    public MirrorNode(Component name, Item icon, int x, int y, Component description, List<ResourceLocation> children, NODE_BORDER border) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.x = x;
        this.y = y;
        this.children = children;
        this.border = border;
    }

    public void render(GuiGraphics guiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(x, y, 0);
        RenderUtil.renderItem(guiGraphics, icon, 0, 0);
        if (selected) {
            SpinTimer.tick(pPartialTick);
            guiGraphics.pose().mulPose(Axis.ZP.rotationDegrees(SpinTimer.getPercentageProgress() * 360));
        }

        int u = 160;
        if (border == NODE_BORDER.BLUE) {
            u += 32;
        }
        if (border == NODE_BORDER.GOLD) {
            u += 64;
        }
        RenderUtil.blit(guiGraphics, MirrorOGScreen.WIDGETS, -16, -16, u, 224, 32, 32, 256, 256);
        guiGraphics.pose().popPose();
    }

    public void renderLines(GuiGraphics guiGraphics, SimpleTimer lineTimer) {
        children.forEach(childKey -> {
            MirrorNode child = MirrorDataLoader.NODES.get(childKey);
            if (child == null) {
                TheAurorian.LOGGER.error("Mirror of Guidance couldnt find child [{}] for [{}]", childKey, name);
                return;
            }

            Vec2 start = new Vec2(x, y);
            Vec2 end = new Vec2(child.x, child.y);
            int count = (int) Math.sqrt(Mth.square(end.x - start.x) + Mth.square(end.y - start.y)) / 10;
            for (int i = 0; i < count; i++) {
                guiGraphics.pose().pushPose();
                float lerpx = Mth.lerp((lineTimer.getPercentageProgress() + i) / count, start.x, end.x);
                float lerpy = Mth.lerp((lineTimer.getPercentageProgress() + i) / count, start.y, end.y);
                guiGraphics.pose().translate(lerpx, lerpy, 0);
                guiGraphics.pose().scale(0.5f, 0.5f, 0);
                RenderSystem.enableBlend();
                RenderSystem.setShaderColor(1, 1, 1, 0.5f);
                RenderUtil.blit(guiGraphics, MirrorOGScreen.WIDGETS, -4, -4, 40, 240, 8, 8, 256, 256);
                guiGraphics.pose().popPose();
            }
        });
    }

    public void setSelected(boolean selected) {
        if (selected) {
            if (!this.selected) {
                SpinTimer.start();
            }
        } else {
            SpinTimer.stop();
        }
        this.selected = selected;
    }

    public boolean isMouseOver(double pMouseX, double pMouseY) {
        return RenderUtil.isMouseOver(x - 12, y - 12, 24, 24, pMouseX, pMouseY);
    }

}
