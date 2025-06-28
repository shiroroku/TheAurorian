package shiroroku.theaurorian;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.atlas.SpriteSourceType;
import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import shiroroku.theaurorian.Blocks.MoonlightForge.MoonlightForgeBlockRenderer;
import shiroroku.theaurorian.Blocks.SilentwoodChest.SilentwoodChestBlockRenderer;
import shiroroku.theaurorian.Items.BaseAurorianTea;
import shiroroku.theaurorian.Items.Loot.UmbraPickaxe;
import shiroroku.theaurorian.Registry.BlockEntityRegistry;
import shiroroku.theaurorian.Registry.EntityRegistry;
import shiroroku.theaurorian.Registry.ItemRegistry;
import shiroroku.theaurorian.Registry.MenuRegistry;

import java.awt.*;
import java.util.function.Supplier;

@EventBusSubscriber(modid = TheAurorian.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EventsClient {

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // ITEM PROPERTIES
            ItemRegistry.ITEMS_GEN_SHIELD.getEntries().stream().map(Supplier::get).forEach((shield) -> ItemProperties.register(shield, ResourceLocation.withDefaultNamespace("blocking"), (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F));
            ItemProperties.register(ItemRegistry.crystalline_sword.get(), ResourceLocation.withDefaultNamespace("charge"), (stack, level, entity, i) -> entity == null || entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 20.0F);
            ItemProperties.register(ItemRegistry.crystalline_sword.get(), ResourceLocation.withDefaultNamespace("charging"), (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
            ItemProperties.register(ItemRegistry.silentwood_bow.get(), ResourceLocation.withDefaultNamespace("pull"), (stack, level, entity, i) -> entity == null || entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 20.0F);
            ItemProperties.register(ItemRegistry.silentwood_bow.get(),ResourceLocation.withDefaultNamespace("pulling"), (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
        });
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        EntityRegistry.registerRenderers(event);
        BlockEntityRegistry.registerRenderers(event);
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        EntityRegistry.registerLayerDefinitions(event);
    }

    /**
     * Adds flashing to the crystalline sword when fully charged
     */
    @SubscribeEvent
    public static void onItemColorHandler(RegisterColorHandlersEvent.Item event) {
        // Crystalline sword glow when charged
        event.register((stack, tintIndex) -> {
            if (tintIndex == 0) {
                float max = (Minecraft.getInstance().player.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration(Minecraft.getInstance().player) - Minecraft.getInstance().player.getUseItemRemainingTicks()) / 20.0F);
                if (max <= 0.8) {
                    return 16777215;
                }
                float wave = (float) (Math.sin(((double) System.currentTimeMillis()) / 200));
                float charge = Math.min(Math.max(0.15f * wave + 0.85f, 0), 1);
                return new Color(charge, charge, charge, 1f).getRGB();
            }
            return 16777215;
        }, ItemRegistry.crystalline_sword.get());
        // Tea color modifier
        event.register((stack, tintIndex) -> tintIndex == 1 ? ((BaseAurorianTea) stack.getItem()).color : 16777215, ItemRegistry.ITEMS_GEN_TEA.getEntries().stream().map(Supplier::get).toArray(ItemLike[]::new));
    }

    /**
     * Adds block icon to the umbra pickaxe item
     */
    @SubscribeEvent
    public static void onRegisterItemDecorations(RegisterItemDecorationsEvent event) {
        event.register(ItemRegistry.umbra_pickaxe.get(), (guiGraphics, font, stack, xOffset, yOffset) -> {
            Block selectedBlock = UmbraPickaxe.getSelectedBlock(stack);
            if (selectedBlock == null) {
                return false;
            }
            guiGraphics.pose().pushPose();
            guiGraphics.pose().scale(0.5f, 0.5f, 1);
            guiGraphics.pose().translate(xOffset, yOffset + 8, 0);

//            Minecraft.getInstance().getItemRenderer().renderGuiItem(new ItemStack(selectedBlock), xOffset, yOffset);
            Minecraft.getInstance().getItemRenderer().renderStatic(new ItemStack(selectedBlock),
                    ItemDisplayContext.GUI,
                    0xF000F0,
                    OverlayTexture.NO_OVERLAY,
                    guiGraphics.pose(),
                    guiGraphics.bufferSource(),
                    Minecraft.getInstance().level,
                    0);

            guiGraphics.pose().popPose();
            RenderSystem.applyModelViewMatrix();
            return true;
        });
    }

//    @SuppressWarnings("deprecation")
//    @SubscribeEvent
//    public static void onTextureStitch(TextureAtlasStitchedEvent event) {
//        if (!event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
//            return;
//        }
//        event.addSprite(MoonlightForgeBlockRenderer.RING_OVERLAY);
//        event.addSprite(SilentwoodChestBlockRenderer.DOUBLE_LEFT);
//        event.addSprite(SilentwoodChestBlockRenderer.DOUBLE_RIGHT);
//        event.addSprite(SilentwoodChestBlockRenderer.NORMAL);
//    }

    @SubscribeEvent
    public static void onRegisterScreens(RegisterMenuScreensEvent event) {
        MenuRegistry.registerMenuScreens(event);
    }
}
