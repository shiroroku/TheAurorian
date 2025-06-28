package shiroroku.theaurorian.Renderers;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import shiroroku.theaurorian.Util.ModUtil;

public class AuroraRenderer {

    // theres probably a more performant way to render this, but im just happy it works at all!!!!

    private static final Vector4f colorTop = new Vector4f(0.3f, 0.9f, 1f, 0f);
    private static final Vector4f colorMid = new Vector4f(0.1f, 0.2f, 0.4f, 1f);
    private static final Vector4f colorBottom = new Vector4f(0.3f, 0.3f, 0.6f, 0f);

    private static final float stripHeight = 30;
    private static final float stripWidth = 10;
    private static final float sections = 40;
    private static final float height = 120;
    private static final float speed = 0.09f;
    private static final float amp = 80;
    private static final float alpha = 0.3f;

    public static void renderSky(ClientLevel level, PoseStack poseStack, Matrix4f projectionMatrix, float partialTick) {
        level.getProfiler().push("theaurorian:auroras");

        Tesselator tesselator = Tesselator.getInstance();
        poseStack.pushPose();
        poseStack.mulPose(Axis.XP.rotationDegrees(90));
        RenderSystem.enableBlend();
        RenderSystem.disableCull();

        float time = level.getGameTime() + partialTick;
        poseStack.translate(0, -300, 0);
        renderAuroraStream(poseStack, time);
        poseStack.translate(0, 100, 0);
        renderAuroraStream(poseStack, time + 10);
        poseStack.translate(0, 260, 0);
        renderAuroraStream(poseStack, time + 100);
        poseStack.translate(0, 60, 0);
        renderAuroraStream(poseStack, time + 110);
        poseStack.translate(0, 200, 0);
        renderAuroraStream(poseStack, time);

//        RenderSystem.disableTexture();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        RenderSystem.enableCull();
        poseStack.popPose();
        level.getProfiler().pop();
    }

    private static void renderAuroraStream(PoseStack pPoseStack, float time) {
        pPoseStack.pushPose();
        Matrix4f matrix = pPoseStack.last().pose();
        pPoseStack.translate(-stripWidth * sections, 0, -height);
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        for (int i = 0; i <= sections; i++) {
            pPoseStack.translate(stripWidth * 2, 0, 0);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, (-1.2f * Mth.square((i / sections) * 2 - 1) + 1) * alpha);
            float zOffset = ModUtil.wave(time * speed + i, 0.1f, amp);
            float zOffsetNext = ModUtil.wave(time * speed + i + 1, 0.1f, amp);
            renderStrip(matrix, zOffset, zOffsetNext, colorMid, colorTop);
            pPoseStack.translate(0, 0, stripHeight * 2);
            renderStrip(matrix, zOffset, zOffsetNext, colorBottom, colorMid);
            pPoseStack.translate(0, 0, -stripHeight * 2);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
        }
        pPoseStack.popPose();
    }

    private static void renderStrip(Matrix4f matrix, float zOffset1, float zOffset2, Vector4f color1, Vector4f color2) {
        BufferUploader.drawWithShader(((BufferBuilder) Tesselator.getInstance()
                .begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR)
                .addVertex(matrix, -stripWidth, 0 - zOffset1, stripHeight).setUv(1, 1).setColor(color1.x(), color1.y(), color1.z(), color1.w())
                .addVertex(matrix, stripWidth, 0 - zOffset2, stripHeight).setUv(0, 1).setColor(color1.x(), color1.y(), color1.z(), color1.w())
                .addVertex(matrix, stripWidth, 0 - zOffset2, -stripHeight).setUv(0, 0).setColor(color2.x(), color2.y(), color2.z(), color2.w())
                .addVertex(matrix, -stripWidth, 0 - zOffset1, -stripHeight).setUv(1, 0).setColor(color2.x(), color2.y(), color2.z(), color2.w()))
                .buildOrThrow());
    }
}
