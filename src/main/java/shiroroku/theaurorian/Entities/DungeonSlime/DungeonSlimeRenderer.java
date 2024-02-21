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

    public void render(DungeonSlimeEntity dungeonSlimeEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.shadowRadius = 0.25F * (float) dungeonSlimeEntity.getSize();
        super.render(dungeonSlimeEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    protected void scale(DungeonSlimeEntity dungeonSlimeEntity, PoseStack pMatrixStack, float pPartialTick) {
        pMatrixStack.scale(0.999F, 0.999F, 0.999F);
        pMatrixStack.translate(0.0D, 0.001F, 0.0D);
        float size = (float) dungeonSlimeEntity.getSize();
        float squishLerp = Mth.lerp(pPartialTick, dungeonSlimeEntity.oSquish, dungeonSlimeEntity.squish) / (size * 0.5F + 1.0F);
        float scale = 1.0F / (squishLerp + 1.0F);
        pMatrixStack.scale(scale * size, 1.0F / scale * size, scale * size);
    }

    @Override
    public ResourceLocation getTextureLocation(DungeonSlimeEntity dungeonSlimeEntity) {
        return TEXTURE;
    }
}
