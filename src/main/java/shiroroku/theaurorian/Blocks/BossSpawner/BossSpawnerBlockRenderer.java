package shiroroku.theaurorian.Blocks.BossSpawner;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class BossSpawnerBlockRenderer implements BlockEntityRenderer<BossSpawnerBlockEntity> {

    private final BlockRenderDispatcher blockRenderer;

    public BossSpawnerBlockRenderer(BlockEntityRendererProvider.Context pContext) {
        this.blockRenderer = pContext.getBlockRenderDispatcher();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void render(BossSpawnerBlockEntity bossSpawnerBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        float time = Minecraft.getInstance().level.getGameTime() + pPartialTick;
        float rotation = time * 4;
        float scale = 0.6f;
        pPoseStack.pushPose();
        pPoseStack.translate(0.5, 0.5D, 0.5D);
        if (bossSpawnerBlockEntity.bossEntity != null) {
            pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(rotation));
            pPoseStack.mulPose(Vector3f.YP.rotationDegrees(rotation));
            pPoseStack.translate(-scale * 0.5, -scale * 0.5, -scale * 0.5);
            pPoseStack.scale(scale, scale, scale);
            this.blockRenderer.renderSingleBlock(bossSpawnerBlockEntity.getBlockState(), pPoseStack, pBufferSource, pPackedLight, pPackedOverlay);
        }

        pPoseStack.popPose();
    }
}
