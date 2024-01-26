package shiroroku.theaurorian.Entities.DungeonSlime;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import shiroroku.theaurorian.TheAurorian;

public class DungeonSlimeRenderer extends MobRenderer<DungeonSlimeEntity, DungeonSlimeModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(TheAurorian.MODID, "textures/entity/dungeon_slime.png");

    public DungeonSlimeRenderer(EntityRendererProvider.Context context) {
        super(context, new DungeonSlimeModel(context.bakeLayer(DungeonSlimeModel.MODEL_LAYER_LOCATION)), 0.25F);
        this.addLayer(new DungeonSlimeLayer(this, context.getModelSet()));
    }

    public void render(DungeonSlimeEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.shadowRadius = 0.25F * (float) pEntity.getSize();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    protected void scale(DungeonSlimeEntity pLivingEntity, PoseStack pMatrixStack, float pPartialTick) {
        float f = 0.999F;
        pMatrixStack.scale(0.999F, 0.999F, 0.999F);
        pMatrixStack.translate(0.0D, 0.001F, 0.0D);
        float f1 = (float) pLivingEntity.getSize();
        float f2 = Mth.lerp(pPartialTick, pLivingEntity.oSquish, pLivingEntity.squish) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        pMatrixStack.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    @Override
    public ResourceLocation getTextureLocation(DungeonSlimeEntity pEntity) {
        return TEXTURE;
    }
}
