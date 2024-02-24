package shiroroku.theaurorian.Blocks.MoonlightForge;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.TheAurorian;
import shiroroku.theaurorian.Util.ModUtil;

@SuppressWarnings("deprecation")
public class MoonlightForgeBlockRenderer implements BlockEntityRenderer<MoonlightForgeBlockEntity> {

    public static final ResourceLocation RING_OVERLAY = new ResourceLocation(TheAurorian.MODID, "block/moonlight_forge_overlay");
    private final BlockRenderDispatcher blockRenderer;

    public MoonlightForgeBlockRenderer(BlockEntityRendererProvider.Context pContext) {
        this.blockRenderer = pContext.getBlockRenderDispatcher();
    }

    @Override
    public void render(MoonlightForgeBlockEntity pBlockEntity, float pPartialTick, PoseStack stack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        float time = Minecraft.getInstance().level.getGameTime() + pPartialTick;
        float rotation = time * 1;
        float scale = 0.4f;

        int heightMin = 0; // below is 1x
        int heightMax = 300; // above is 5x
        int height = Math.max(heightMin, Math.min(heightMax, pBlockEntity.getBlockPos().getY()));
        float multiplier = ((float) height / heightMax) + 1;

        stack.pushPose();
        stack.translate(0.5, 0.9, 0.5D);
        stack.mulPose(Vector3f.ZP.rotationDegrees(rotation * multiplier * 3));
        stack.mulPose(Vector3f.YP.rotationDegrees(rotation * multiplier * 3));
        stack.translate(-scale * 0.5, -scale * 0.5, -scale * 0.5);
        stack.scale(scale, scale, scale);
        this.blockRenderer.renderSingleBlock(BlockRegistry.moon_gem.get().defaultBlockState(), stack, pBufferSource, pPackedLight, pPackedOverlay);
        stack.popPose();

        // void
        stack.pushPose();
        VertexConsumer consumer = pBufferSource.getBuffer(RenderType.endGateway());
        PoseStack.Pose last = stack.last();
        Matrix4f pose = last.pose();
        Matrix3f normal = last.normal();
        stack.translate(0.5, 0.6275f, 0.5);
        stack.scale(0.625f, 0, 0.625f);
        vert(consumer, pose, normal, -0.5f, 0, -0.5f, 0, 0);
        vert(consumer, pose, normal, -0.5f, 0, 0.5f, 0, 0);
        vert(consumer, pose, normal, 0.5f, 0, 0.5f, 0, 0);
        vert(consumer, pose, normal, 0.5f, 0, -0.5f, 0, 0);
        stack.popPose();

        // rings
        stack.pushPose();
        stack.translate(0.5, 0, 0.5);
        float movement = ModUtil.wave(time, 0.15f, 0.1f);
        stack.scale(0.7f, 1, 0.7f);
        stack.translate(0, 0.9 + movement, 0D);
        stack.mulPose(Vector3f.YP.rotationDegrees(rotation));
        renderRing(pBufferSource, stack);
        movement += multiplier * 1;
        stack.scale(0.95f * movement, 1, 0.95f * movement);
        stack.mulPose(Vector3f.YP.rotationDegrees(rotation));
        stack.translate(0, 0.15, 0D);
        renderRing(pBufferSource, stack);
        stack.scale(0.95f * movement, 1, 0.95f * movement);
        stack.mulPose(Vector3f.YP.rotationDegrees(rotation));
        stack.translate(0, 0.15, 0D);
        renderRing(pBufferSource, stack);
        stack.popPose();
    }

    private static void renderRing(MultiBufferSource pBufferSource, PoseStack stack) {
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(RING_OVERLAY);
        VertexConsumer builder = pBufferSource.getBuffer(RenderType.cutout());
        PoseStack.Pose last = stack.last();
        Matrix4f pose = last.pose();
        Matrix3f normal = last.normal();
        stack.pushPose();
        vert(builder, pose, normal, -0.5f, 0, 0.5f, sprite.getU0(), sprite.getV1());
        vert(builder, pose, normal, 0.5f, 0, 0.5f, sprite.getU1(), sprite.getV1());
        vert(builder, pose, normal, 0.5f, 0, -0.5f, sprite.getU1(), sprite.getV0());
        vert(builder, pose, normal, -0.5f, 0, -0.5f, sprite.getU0(), sprite.getV0());
        stack.popPose();
    }

    private static void vert(VertexConsumer vertexConsumer, Matrix4f matrix4f, Matrix3f matrix3f, float x, float y, float z, float u, float v) {
        vertexConsumer.vertex(matrix4f, x, y, z).color(255, 255, 255, 255).uv(u, v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(matrix3f, 0.0F, 1.0F, 0.0F).endVertex();
    }
}
