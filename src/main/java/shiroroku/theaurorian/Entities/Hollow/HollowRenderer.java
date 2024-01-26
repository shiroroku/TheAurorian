package shiroroku.theaurorian.Entities.Hollow;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import shiroroku.theaurorian.TheAurorian;

public class HollowRenderer extends ZombieRenderer {

    private static final ResourceLocation TEXTURE = new ResourceLocation(TheAurorian.MODID, "textures/entity/hollow.png");

    public HollowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(Zombie zombie, PoseStack poseStack, float pTick) {
        poseStack.scale(1.0625F, 1.0625F, 1.0625F);
        super.scale(zombie, poseStack, pTick);
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie pEntity) {
        return TEXTURE;
    }
}
