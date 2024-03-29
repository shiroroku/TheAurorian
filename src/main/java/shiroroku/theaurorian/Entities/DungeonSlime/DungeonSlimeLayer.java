package shiroroku.theaurorian.Entities.DungeonSlime;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

public class DungeonSlimeLayer extends RenderLayer<DungeonSlimeEntity, DungeonSlimeModel> {

    private final EntityModel<DungeonSlimeEntity> model;

    public DungeonSlimeLayer(RenderLayerParent<DungeonSlimeEntity, DungeonSlimeModel> pRenderer, EntityModelSet set) {
        super(pRenderer);
        this.model = new SlimeModel<>(set.bakeLayer(DungeonSlimeModel.MODEL_LAYER_LOCATION_OUTER));
    }

    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, DungeonSlimeEntity dungeonSlimeEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        boolean glowing = Minecraft.getInstance().shouldEntityAppearGlowing(dungeonSlimeEntity) && dungeonSlimeEntity.isInvisible();
        if (!dungeonSlimeEntity.isInvisible() || glowing) {
            VertexConsumer vertexconsumer = glowing ? pBuffer.getBuffer(RenderType.outline(this.getTextureLocation(dungeonSlimeEntity))) : pBuffer.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(dungeonSlimeEntity)));
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(dungeonSlimeEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
            this.model.setupAnim(dungeonSlimeEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
            this.model.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, LivingEntityRenderer.getOverlayCoords(dungeonSlimeEntity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
