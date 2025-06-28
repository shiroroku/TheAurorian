package shiroroku.theaurorian.Registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import shiroroku.theaurorian.Blocks.AurorianFurnace.AurorianFurnaceBlockEntity;
import shiroroku.theaurorian.Blocks.BossSpawner.BossSpawnerBlockEntity;
import shiroroku.theaurorian.Blocks.BossSpawner.BossSpawnerBlockRenderer;
import shiroroku.theaurorian.Blocks.Crystal.CrystalBlockEntity;
import shiroroku.theaurorian.Blocks.Crystal.CrystalBlockRenderer;
import shiroroku.theaurorian.Blocks.MoonlightForge.MoonlightForgeBlockEntity;
import shiroroku.theaurorian.Blocks.MoonlightForge.MoonlightForgeBlockRenderer;
import shiroroku.theaurorian.Blocks.Scrapper.ScrapperBlockEntity;
import shiroroku.theaurorian.Blocks.SilentwoodChest.SilentwoodChestBlock;
import shiroroku.theaurorian.Blocks.SilentwoodChest.SilentwoodChestBlockEntity;
import shiroroku.theaurorian.Blocks.SilentwoodChest.SilentwoodChestBlockRenderer;
import shiroroku.theaurorian.TheAurorian;

public class BlockEntityRegistry {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, TheAurorian.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SilentwoodChestBlockEntity>> silentwood_chest = BLOCK_ENTITIES.register("silentwood_chest", () -> BlockEntityType.Builder.of(SilentwoodChestBlockEntity::new, BlockRegistry.silentwood_chest.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AurorianFurnaceBlockEntity>> aurorian_furnace = BLOCK_ENTITIES.register("aurorian_furnace", () -> BlockEntityType.Builder.of(AurorianFurnaceBlockEntity::new, BlockRegistry.aurorian_furnace.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BossSpawnerBlockEntity>> boss_spawner = BLOCK_ENTITIES.register("boss_spawner", () -> BlockEntityType.Builder.of(BossSpawnerBlockEntity::new, BlockRegistry.boss_spawner.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CrystalBlockEntity>> crystal = BLOCK_ENTITIES.register("crystal", () -> BlockEntityType.Builder.of(CrystalBlockEntity::new, BlockRegistry.crystal.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MoonlightForgeBlockEntity>> moonlight_forge = BLOCK_ENTITIES.register("moonlight_forge", () -> BlockEntityType.Builder.of(MoonlightForgeBlockEntity::new, BlockRegistry.moonlight_forge.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ScrapperBlockEntity>> scrapper = BLOCK_ENTITIES.register("scrapper", () -> BlockEntityType.Builder.of(ScrapperBlockEntity::new, BlockRegistry.scrapper.get()).build(null));

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {

        // BLOCK ENTITY RENDERERS
        event.registerBlockEntityRenderer(BlockEntityRegistry.boss_spawner.get(), BossSpawnerBlockRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.crystal.get(), CrystalBlockRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.moonlight_forge.get(), MoonlightForgeBlockRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.moonlight_forge.get(), MoonlightForgeBlockRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.silentwood_chest.get(), SilentwoodChestBlockRenderer::new);
    }
}
