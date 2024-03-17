package shiroroku.theaurorian.Entities.DungeonKeeper;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import shiroroku.theaurorian.TheAurorian;

public class DungeonKeeperRenderer extends HumanoidMobRenderer<AbstractSkeleton, DungeonKeeperModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(TheAurorian.MODID, "textures/entity/dungeon_keeper/dungeon_keeper.png");

    public DungeonKeeperRenderer(EntityRendererProvider.Context ctx) {
        this(ctx, ModelLayers.STRAY, ModelLayers.STRAY_INNER_ARMOR, ModelLayers.STRAY_OUTER_ARMOR);
    }

    public DungeonKeeperRenderer(EntityRendererProvider.Context pContext, ModelLayerLocation model, ModelLayerLocation p_174384_, ModelLayerLocation p_174385_) {
        super(pContext, new DungeonKeeperModel(pContext.bakeLayer(model)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new SkeletonModel<>(pContext.bakeLayer(p_174384_)), new SkeletonModel<>(pContext.bakeLayer(p_174385_))));
        this.addLayer(new DungeonKeeperLayer<>(this, pContext.getModelSet()));
    }

    @Override
    protected void scale(AbstractSkeleton pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        pMatrixStack.scale(1.3F, 1.3F, 1.3F);
        super.scale(pLivingEntity, pMatrixStack, pPartialTickTime);
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractSkeleton dungeonSlimeEntity) {
        return TEXTURE;
    }
}
