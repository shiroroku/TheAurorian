package shiroroku.theaurorian.Registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import shiroroku.theaurorian.Blocks.SilentwoodChest.SilentwoodChestBlockRenderer;
import shiroroku.theaurorian.Entities.AurorianArrow.AurorianArrowEntity;
import shiroroku.theaurorian.Entities.AurorianArrow.AurorianArrowRenderer;
import shiroroku.theaurorian.Entities.AurorianArrow.CeruleanArrowEntity;
import shiroroku.theaurorian.Entities.AurorianArrow.CrystalArrowEntity;
import shiroroku.theaurorian.Entities.CrystallineBeam.CrystallineBeamEntity;
import shiroroku.theaurorian.Entities.CrystallineBeam.CrystallineBeamModel;
import shiroroku.theaurorian.Entities.CrystallineBeam.CrystallineBeamRenderer;
import shiroroku.theaurorian.Entities.DungeonKeeper.DungeonKeeperEntity;
import shiroroku.theaurorian.Entities.DungeonKeeper.DungeonKeeperRenderer;
import shiroroku.theaurorian.Entities.DungeonSlime.DungeonSlimeEntity;
import shiroroku.theaurorian.Entities.DungeonSlime.DungeonSlimeModel;
import shiroroku.theaurorian.Entities.DungeonSlime.DungeonSlimeRenderer;
import shiroroku.theaurorian.Entities.Hollow.HollowEntity;
import shiroroku.theaurorian.Entities.Hollow.HollowRenderer;
import shiroroku.theaurorian.Entities.UndeadKnight.UndeadKnightEntity;
import shiroroku.theaurorian.Entities.UndeadKnight.UndeadKnightRenderer;
import shiroroku.theaurorian.TheAurorian;

public class EntityRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, TheAurorian.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<AurorianArrowEntity>> cerulean_arrow = ENTITIES.register("cerulean_arrow", () -> EntityType.Builder.<AurorianArrowEntity>of(CeruleanArrowEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("cerulean_arrow"));
    public static final DeferredHolder<EntityType<?>, EntityType<AurorianArrowEntity>> crystal_arrow = ENTITIES.register("crystal_arrow", () -> EntityType.Builder.<AurorianArrowEntity>of(CrystalArrowEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("crystal_arrow"));
    public static final DeferredHolder<EntityType<?>, EntityType<CrystallineBeamEntity>> crystalline_beam = ENTITIES.register("crystalline_beam", () -> EntityType.Builder.<CrystallineBeamEntity>of(CrystallineBeamEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).fireImmune().setUpdateInterval(1).build("crystalline_beam"));

    public static final DeferredHolder<EntityType<?>, EntityType<DungeonKeeperEntity>> dungeon_keeper = ENTITIES.register("dungeon_keeper", () -> EntityType.Builder.of(DungeonKeeperEntity::new, MobCategory.MONSTER).sized(0.8F, 2.3F).clientTrackingRange(8).sized(0.7F, 2.4F).fireImmune().build("dungeon_keeper"));
    public static final DeferredHolder<EntityType<?>, EntityType<DungeonSlimeEntity>> dungeon_slime = ENTITIES.register("dungeon_slime", () -> EntityType.Builder.of(DungeonSlimeEntity::new, MobCategory.MONSTER).sized(2.04F, 2.04F).clientTrackingRange(10).build("dungeon_slime"));
    public static final DeferredHolder<EntityType<?>, EntityType<HollowEntity>> hollow = ENTITIES.register("hollow", () -> EntityType.Builder.<HollowEntity>of(HollowEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("hollow"));
    public static final DeferredHolder<EntityType<?>, EntityType<UndeadKnightEntity>> undead_knight = ENTITIES.register("undead_knight", () -> EntityType.Builder.<UndeadKnightEntity>of(UndeadKnightEntity::new, MobCategory.MONSTER).sized(0.8F, 2.3F).clientTrackingRange(8).build("undead_knight"));

    public static void entityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.dungeon_keeper.get(), DungeonKeeperEntity.createAttributes().build());
        event.put(EntityRegistry.dungeon_slime.get(), DungeonSlimeEntity.createAttributes().build());
        event.put(EntityRegistry.hollow.get(), HollowEntity.createAttributes().build());
        event.put(EntityRegistry.undead_knight.get(), UndeadKnightEntity.createAttributes().build());
    }

    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(EntityRegistry.hollow.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HollowEntity::checkSpawn, RegisterSpawnPlacementsEvent.Operation.AND);
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // Misc
        event.registerEntityRenderer(EntityRegistry.cerulean_arrow.get(), (ctx) -> new AurorianArrowRenderer(ctx, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "textures/entity/cerulean_arrow.png")));
        event.registerEntityRenderer(EntityRegistry.crystal_arrow.get(), (ctx) -> new AurorianArrowRenderer(ctx, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "textures/entity/crystal_arrow.png")));
        event.registerEntityRenderer(EntityRegistry.crystalline_beam.get(), CrystallineBeamRenderer::new);

        // Living
        event.registerEntityRenderer(EntityRegistry.dungeon_keeper.get(), DungeonKeeperRenderer::new);
        event.registerEntityRenderer(EntityRegistry.dungeon_slime.get(), DungeonSlimeRenderer::new);
        event.registerEntityRenderer(EntityRegistry.hollow.get(), HollowRenderer::new);
        event.registerEntityRenderer(EntityRegistry.undead_knight.get(), UndeadKnightRenderer::new);
    }

    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        // Misc
        event.registerLayerDefinition(CrystallineBeamModel.MODEL_LAYER_LOCATION, CrystallineBeamModel::createLayer);
        event.registerLayerDefinition(SilentwoodChestBlockRenderer.MODEL_LAYER_DOUBLE_LEFT, SilentwoodChestBlockRenderer::createDoubleBodyLeftLayer);
        event.registerLayerDefinition(SilentwoodChestBlockRenderer.MODEL_LAYER_DOUBLE_RIGHT, SilentwoodChestBlockRenderer::createDoubleBodyRightLayer);
        event.registerLayerDefinition(SilentwoodChestBlockRenderer.MODEL_LAYER_NORMAL, SilentwoodChestBlockRenderer::createSingleBodyLayer);

        // Living
        event.registerLayerDefinition(DungeonSlimeModel.MODEL_LAYER_LOCATION, DungeonSlimeModel::createLayer);
        event.registerLayerDefinition(DungeonSlimeModel.MODEL_LAYER_LOCATION_OUTER, DungeonSlimeModel::createOuterLayer);
    }

}
