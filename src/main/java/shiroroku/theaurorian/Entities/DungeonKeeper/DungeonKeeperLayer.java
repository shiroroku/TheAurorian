package shiroroku.theaurorian.Entities.DungeonKeeper;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.RangedAttackMob;
import shiroroku.theaurorian.TheAurorian;

public class DungeonKeeperLayer<T extends Mob & RangedAttackMob, M extends EntityModel<T>> extends RenderLayer<T, M> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(TheAurorian.MODID, "textures/entity/dungeon_keeper/dungeon_keeper_layer.png");
    private final SkeletonModel<T> layerModel;

    public DungeonKeeperLayer(RenderLayerParent<T, M> pRenderer, EntityModelSet model) {
        super(pRenderer);
        this.layerModel = new SkeletonModel<>(model.bakeLayer(ModelLayers.STRAY_OUTER_LAYER));
    }

    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        coloredCutoutModelCopyLayerRender(this.getParentModel(), this.layerModel, TEXTURE, pMatrixStack, pBuffer, pPackedLight, pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch, pPartialTicks, 1.0F, 1.0F, 1.0F);
    }
}
