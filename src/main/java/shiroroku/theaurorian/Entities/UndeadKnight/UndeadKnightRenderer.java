package shiroroku.theaurorian.Entities.UndeadKnight;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import shiroroku.theaurorian.TheAurorian;

public class UndeadKnightRenderer extends HumanoidMobRenderer<UndeadKnightEntity, HumanoidModel<UndeadKnightEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(TheAurorian.MODID, "textures/entity/undead_knight.png");

    public UndeadKnightRenderer(EntityRendererProvider.Context context) {
        this(context, ModelLayers.ZOMBIE, ModelLayers.ZOMBIE_INNER_ARMOR, ModelLayers.ZOMBIE_OUTER_ARMOR);
    }

    public UndeadKnightRenderer(EntityRendererProvider.Context context, ModelLayerLocation model, ModelLayerLocation innerArmor, ModelLayerLocation outerArmor) {
        super(context, new HumanoidModel<>(context.bakeLayer(model)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(context.bakeLayer(innerArmor)), new HumanoidModel<>(context.bakeLayer(outerArmor))));
    }

    @Override
    protected void scale(UndeadKnightEntity pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        pMatrixStack.scale(1.2625F, 1.2625F, 1.2625F);
        super.scale(pLivingEntity, pMatrixStack, pPartialTickTime);
    }

    @Override
    public ResourceLocation getTextureLocation(UndeadKnightEntity pEntity) {
        return TEXTURE;
    }
}


