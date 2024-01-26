package shiroroku.theaurorian.Blocks.Crystal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraftforge.client.model.data.ModelData;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.Util.ModUtil;

public class CrystalBlockRenderer implements BlockEntityRenderer<CrystalBlockEntity> {

    private final BakedModel model;
    private final BlockRenderDispatcher blockRenderer;

    public CrystalBlockRenderer(BlockEntityRendererProvider.Context context) {
        model = context.getBlockRenderDispatcher().getBlockModel(BlockRegistry.crystal.get().defaultBlockState());
        blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(CrystalBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        float time = Minecraft.getInstance().level.getGameTime() + pPartialTick;
        float rotation = time * 4;
        pPoseStack.pushPose();
        pPoseStack.translate(0.5, 0, 0.5);
        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(rotation));
        pPoseStack.translate(-0.5, 0, -0.5);
        pPoseStack.translate(0, ModUtil.wave(time, 0.25f, 0.05f), 0);
        this.blockRenderer.getModelRenderer().renderModel(
                pPoseStack.last(),
                pBuffer.getBuffer(RenderType.entityTranslucent(TextureAtlas.LOCATION_BLOCKS)),
                BlockRegistry.crystal.get().defaultBlockState(),
                model, 0.0F, 0.0F, 0.0F, pPackedLight, pPackedOverlay, ModelData.EMPTY, null);
        pPoseStack.popPose();
    }
}
