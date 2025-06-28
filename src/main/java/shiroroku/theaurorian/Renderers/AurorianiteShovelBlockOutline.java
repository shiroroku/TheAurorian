package shiroroku.theaurorian.Renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;
import shiroroku.theaurorian.Registry.ItemRegistry;
import shiroroku.theaurorian.Util.RenderUtil;

public class AurorianiteShovelBlockOutline {

    public static void onRenderOutline(RenderHighlightEvent.Block event) {
        if (!Minecraft.getInstance().player.getMainHandItem().is(ItemRegistry.aurorianite_shovel.get())) {
            return;
        }

        BlockPos hitPos = event.getTarget().getBlockPos();
        Direction.Axis hitAxis = event.getTarget().getDirection().getAxis();

        // If we are aiming at a block that cannot be dug, we dont wanna render any extra outlines
        if (Minecraft.getInstance().level.getBlockState(hitPos).getTags().noneMatch(t -> t == BlockTags.MINEABLE_WITH_SHOVEL)) {
            return;
        }

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos offsetHitpos = switch (hitAxis) {
                    case X -> hitPos.offset(0, x, z);
                    case Y -> hitPos.offset(x, 0, z);
                    default -> hitPos.offset(x, z, 0);
                };

                RenderUtil.renderBlockOutline(event.getPoseStack(), event.getMultiBufferSource(), offsetHitpos, event.getCamera(), 0, 0, 0, 0.25f);
            }
        }
        event.setCanceled(true);
    }
}
