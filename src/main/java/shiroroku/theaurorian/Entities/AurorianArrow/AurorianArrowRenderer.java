package shiroroku.theaurorian.Entities.AurorianArrow;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class AurorianArrowRenderer extends ArrowRenderer<AurorianArrowEntity> {

    public final ResourceLocation texture;

    public AurorianArrowRenderer(EntityRendererProvider.Context pContext, ResourceLocation texture) {
        super(pContext);
        this.texture = texture;
    }

    @Override
    public ResourceLocation getTextureLocation(AurorianArrowEntity pEntity) {
        return texture;
    }
}
