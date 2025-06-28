package shiroroku.theaurorian.Renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;
import shiroroku.theaurorian.Items.Loot.UmbraPickaxe;
import shiroroku.theaurorian.Registry.ItemRegistry;
import shiroroku.theaurorian.Util.RenderUtil;

public class UmbraPickaxeBlockOutline {

    /**
     * Handles the umbra pickaxe block outline colors
     */
    public static void onRenderOutline(RenderHighlightEvent.Block event) {
        if (!Minecraft.getInstance().player.getMainHandItem().is(ItemRegistry.umbra_pickaxe.get())) {
            return;
        }

        BlockPos hitPos = event.getTarget().getBlockPos();
        if (UmbraPickaxe.getSelectedBlock(Minecraft.getInstance().player.getMainHandItem()) != Minecraft.getInstance().level.getBlockState(hitPos).getBlock()) {
            return;
        }

        float colorWave = 0.75f * Math.min(1, Math.max(0, 0.5f * (float) (1 + Math.sin(((double) System.currentTimeMillis()) / 200))));
        RenderUtil.renderBlockOutline(event.getPoseStack(), event.getMultiBufferSource(), hitPos, event.getCamera(), colorWave, colorWave, colorWave, 0.5f);
        event.setCanceled(true);
    }
}
