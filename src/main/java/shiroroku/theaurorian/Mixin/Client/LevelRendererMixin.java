package shiroroku.theaurorian.Mixin.Client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import shiroroku.theaurorian.Config.ClientConfig;
import shiroroku.theaurorian.Renderers.AuroraRenderer;
import shiroroku.theaurorian.TheAurorian;
import shiroroku.theaurorian.Util.ModUtil;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {

    @Shadow
    private ClientLevel level;

    @Shadow
    private int ticks;

    @Final
    @Shadow
    private Minecraft minecraft;

    @Shadow
    private VertexBuffer skyBuffer;

    @Shadow
    private VertexBuffer starBuffer;

    @Shadow
    private VertexBuffer darkBuffer;

    @Final
    @Shadow
    private static ResourceLocation MOON_LOCATION;

    @Shadow
    private boolean doesMobEffectBlockSky(Camera camera) {
        return false;
    }

    @Inject(at = @At("HEAD"), method = "renderSky", cancellable = true)
    public void renderSky(Matrix4f frustumMatrix, Matrix4f projectionMatrix, float partialTick, Camera camera, boolean isFoggy, Runnable skyFogSetup, CallbackInfo ci) {
        if (level.dimension() != TheAurorian.the_aurorian) {
            return; // Only override aurorian sky renderer
        }
        skyFogSetup.run();
        if (!isFoggy) {
            FogType fogtype = camera.getFluidInCamera();
            if (fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA && !this.doesMobEffectBlockSky(camera)) {
                PoseStack poseStack = new PoseStack();
                poseStack.mulPose(frustumMatrix);

                Tesselator tesselator = Tesselator.getInstance();
                Vec3 skyColor = this.level.getSkyColor(this.minecraft.gameRenderer.getMainCamera().getPosition(), partialTick);
                FogRenderer.levelFogColor();
                RenderSystem.depthMask(false);
                RenderSystem.setShaderColor((float) skyColor.x, (float) skyColor.y, (float) skyColor.z, 1.0F);
                ShaderInstance shaderInstance = RenderSystem.getShader();

                this.skyBuffer.bind();
                this.skyBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderInstance);
                VertexBuffer.unbind();
                RenderSystem.enableBlend();
                float[] sunriseColor = this.level.effects().getSunriseColor(this.level.getTimeOfDay(partialTick), partialTick);
                if (sunriseColor != null) {
                    RenderSystem.setShader(GameRenderer::getPositionColorShader);
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    poseStack.pushPose();
                    poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                    poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.sin(this.level.getSunAngle(partialTick)) < 0.0F ? 180.0F : 0.0F));
                    poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
                    Matrix4f matrix0 = poseStack.last().pose();
                    BufferBuilder bufferBuilder0 = tesselator.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
                    bufferBuilder0.addVertex(matrix0, 0.0F, 100.0F, 0.0F).setColor(sunriseColor[0], sunriseColor[1], sunriseColor[2], sunriseColor[3]);
                    for (int j = 0; j <= 16; ++j) {
                        float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
                        float f8 = Mth.sin(f7);
                        float f9 = Mth.cos(f7);
                        bufferBuilder0.addVertex(matrix0, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * sunriseColor[3]).setColor(sunriseColor[0], sunriseColor[1], sunriseColor[2], 0.0F);
                    }
                    BufferUploader.drawWithShader(bufferBuilder0.buildOrThrow());
                    poseStack.popPose();
                }

                RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                poseStack.pushPose();
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                float skyRotationSpeed = (this.level.getGameTime() + partialTick) * 0.02f;
                poseStack.mulPose(Axis.YP.rotationDegrees(skyRotationSpeed));
                poseStack.mulPose(Axis.XP.rotationDegrees(140 + ModUtil.wave(skyRotationSpeed, 0.05f, 60)));
                Matrix4f matrix1 = poseStack.last().pose();
                float moonSize = 40.0F;
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderTexture(0, MOON_LOCATION);
                int moonPhase = this.level.getMoonPhase();
                int l = moonPhase % 4;
                int i1 = moonPhase / 4 % 2;
                float u1 = (float) (l) / 4.0F;
                float v1 = (float) (i1) / 2.0F;
                float u2 = (float) (l + 1) / 4.0F;
                float v2 = (float) (i1 + 1) / 2.0F;
                BufferBuilder bufferBuilder1 = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                bufferBuilder1.addVertex(matrix1, -moonSize, -100.0F, moonSize).setUv(u2, v2);
                bufferBuilder1.addVertex(matrix1, moonSize, -100.0F, moonSize).setUv(u1, v2);
                bufferBuilder1.addVertex(matrix1, moonSize, -100.0F, -moonSize).setUv(u1, v1);
                bufferBuilder1.addVertex(matrix1, -moonSize, -100.0F, -moonSize).setUv(u2, v1);
                BufferUploader.drawWithShader(bufferBuilder1.buildOrThrow());

                float starBrightness = this.level.getStarBrightness(partialTick);
                RenderSystem.setShaderColor(starBrightness, starBrightness, starBrightness, starBrightness);
                FogRenderer.setupNoFog();
                this.starBuffer.bind();
                this.starBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, GameRenderer.getPositionShader());
                VertexBuffer.unbind();
                skyFogSetup.run();

                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.disableBlend();
                poseStack.popPose();

                if (ClientConfig.enable_auroras.get()) {
                    AuroraRenderer.renderSky(level, poseStack, projectionMatrix, partialTick);
                }

                RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
                double d0 = this.minecraft.player.getEyePosition(partialTick).y - this.level.getLevelData().getHorizonHeight(this.level);
                if (d0 < 0.0D) {
                    poseStack.pushPose();
                    poseStack.translate(0.0D, 12.0D, 0.0D);
                    this.darkBuffer.bind();
                    this.darkBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderInstance);
                    VertexBuffer.unbind();
                    poseStack.popPose();
                }

                // TODO: Let renderer correctly sets shader color with skyColor.
//                RenderSystem.setShaderColor((float) skyColor.x * 0.2F + 0.04F, (float) skyColor.y * 0.2F + 0.04F, (float) skyColor.z * 0.6F + 0.1F, 1.0F);
                RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                RenderSystem.depthMask(true);

            }
        }

        ci.cancel(); // Dont render vanilla sky after
    }
}
