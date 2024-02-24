package shiroroku.theaurorian;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterItemDecorationsEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import shiroroku.theaurorian.Blocks.BossSpawner.BossSpawnerBlockRenderer;
import shiroroku.theaurorian.Blocks.Crystal.CrystalBlockRenderer;
import shiroroku.theaurorian.Blocks.MoonlightForge.MoonlightForgeBlockRenderer;
import shiroroku.theaurorian.Blocks.MoonlightForge.MoonlightForgeScreen;
import shiroroku.theaurorian.Blocks.Scrapper.ScrapperScreen;
import shiroroku.theaurorian.Items.BaseAurorianTea;
import shiroroku.theaurorian.Items.Loot.UmbraPickaxe;
import shiroroku.theaurorian.Registry.BlockEntityRegistry;
import shiroroku.theaurorian.Registry.EntityRegistry;
import shiroroku.theaurorian.Registry.ItemRegistry;
import shiroroku.theaurorian.Registry.MenuRegistry;

import java.awt.*;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = TheAurorian.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EventsClient {

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // ITEM PROPERTIES
            ItemRegistry.ITEMS_GEN_SHIELD.getEntries().stream().map(Supplier::get).forEach((shield) -> ItemProperties.register(shield, new ResourceLocation("blocking"), (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F));
            ItemProperties.register(ItemRegistry.crystalline_sword.get(), new ResourceLocation("charge"), (stack, level, entity, i) -> entity == null || entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F);
            ItemProperties.register(ItemRegistry.crystalline_sword.get(), new ResourceLocation("charging"), (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
            ItemProperties.register(ItemRegistry.silentwood_bow.get(), new ResourceLocation("pull"), (stack, level, entity, i) -> entity == null || entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F);
            ItemProperties.register(ItemRegistry.silentwood_bow.get(), new ResourceLocation("pulling"), (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);

            // MENU SCREENS
            MenuScreens.register(MenuRegistry.scrapper.get(), ScrapperScreen::new);
            MenuScreens.register(MenuRegistry.moonlight_forge.get(), MoonlightForgeScreen::new);

            // BLOCK ENTITY RENDERERS
            BlockEntityRenderers.register(BlockEntityRegistry.crystal.get(), CrystalBlockRenderer::new);
            BlockEntityRenderers.register(BlockEntityRegistry.boss_spawner.get(), BossSpawnerBlockRenderer::new);
            BlockEntityRenderers.register(BlockEntityRegistry.moonlight_forge.get(), MoonlightForgeBlockRenderer::new);
        });
    }

    @SubscribeEvent
    public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {
        EntityRegistry.registerRenderers(event);
    }

    @SubscribeEvent
    public static void registerRenderer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        EntityRegistry.registerLayerDefinitions(event);
    }

    /**
     * Adds flashing to the crystalline sword when fully charged
     */
    @SubscribeEvent
    public static void onItemColorHandler(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> {
            if (tintIndex == 0) {
                float max = (Minecraft.getInstance().player.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration() - Minecraft.getInstance().player.getUseItemRemainingTicks()) / 20.0F);
                if (max <= 0.8) {
                    return 16777215;
                }
                float wave = (float) (Math.sin(((double) System.currentTimeMillis()) / 200));
                float charge = Math.min(Math.max(0.15f * wave + 0.85f, 0), 1);
                return new Color(charge, charge, charge, 1f).getRGB();
            }
            return 16777215;
        }, ItemRegistry.crystalline_sword.get());
        event.register((stack, tintIndex) -> {
            if (tintIndex == 1) {
                return ((BaseAurorianTea) stack.getItem()).color;
            }
            return 16777215;
        }, ItemRegistry.ITEMS_GEN_TEA.getEntries().stream().map(Supplier::get).toArray(ItemLike[]::new));
    }

    /**
     * Adds block icon to the umbra pickaxe item
     */
    @SubscribeEvent
    public static void registerItemDecorations(RegisterItemDecorationsEvent event) {
        event.register(ItemRegistry.umbra_pickaxe.get(), (font, stack, xOffset, yOffset, blitOffset) -> {
            Block selectedBlock = UmbraPickaxe.getSelectedBlock(stack);
            if (selectedBlock == null) {
                return false;
            }
            PoseStack poseStack = RenderSystem.getModelViewStack();
            poseStack.pushPose();
            poseStack.scale(0.5f, 0.5f, 1);
            poseStack.translate(xOffset, yOffset + 8, 0);
            Minecraft.getInstance().getItemRenderer().renderGuiItem(new ItemStack(selectedBlock), xOffset, yOffset);
            poseStack.popPose();
            RenderSystem.applyModelViewMatrix();
            return true;
        });
    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if (!event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
            return;
        }
        event.addSprite(MoonlightForgeBlockRenderer.RING_OVERLAY);
    }

}
