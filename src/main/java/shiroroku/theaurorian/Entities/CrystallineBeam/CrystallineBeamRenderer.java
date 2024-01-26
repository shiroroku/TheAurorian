package shiroroku.theaurorian.Entities.CrystallineBeam;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import shiroroku.theaurorian.TheAurorian;

public class CrystallineBeamRenderer extends EntityRenderer<CrystallineBeamEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(TheAurorian.MODID, "textures/entity/crystalline_beam.png");
    private final CrystallineBeamModel model;

    public CrystallineBeamRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        model = new CrystallineBeamModel(pContext.bakeLayer(CrystallineBeamModel.MODEL_LAYER_LOCATION));
    }

    @Override
    public void render(CrystallineBeamEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.translate(0, 0.25, 0);
        float f = Mth.lerp(pPartialTick, pEntity.yRotO, pEntity.getYRot()) + 180;
        float f1 = Mth.lerp(pPartialTick, pEntity.xRotO, pEntity.getXRot());
        this.model.setupAnim(pEntity, 0.0F, 0.0F, 0.0F, f, f1);
        VertexConsumer vertexConsumer = pBuffer.getBuffer(this.model.renderType(this.getTextureLocation(pEntity)));
        this.model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(CrystallineBeamEntity pEntity) {
        return TEXTURE;
    }
}
