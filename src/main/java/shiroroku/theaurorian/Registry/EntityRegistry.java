package shiroroku.theaurorian.Registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import shiroroku.theaurorian.Entities.AurorianArrow.AurorianArrowEntity;
import shiroroku.theaurorian.Entities.AurorianArrow.AurorianArrowRenderer;
import shiroroku.theaurorian.Entities.AurorianArrow.CeruleanArrowEntity;
import shiroroku.theaurorian.Entities.AurorianArrow.CrystalArrowEntity;
import shiroroku.theaurorian.Entities.CrystallineBeam.CrystallineBeamEntity;
import shiroroku.theaurorian.Entities.CrystallineBeam.CrystallineBeamModel;
import shiroroku.theaurorian.Entities.CrystallineBeam.CrystallineBeamRenderer;
import shiroroku.theaurorian.Entities.DungeonSlime.DungeonSlimeEntity;
import shiroroku.theaurorian.Entities.DungeonSlime.DungeonSlimeModel;
import shiroroku.theaurorian.Entities.DungeonSlime.DungeonSlimeRenderer;
import shiroroku.theaurorian.Entities.Hollow.HollowEntity;
import shiroroku.theaurorian.Entities.Hollow.HollowRenderer;
import shiroroku.theaurorian.Entities.UndeadKnight.UndeadKnightEntity;
import shiroroku.theaurorian.Entities.UndeadKnight.UndeadKnightRenderer;
import shiroroku.theaurorian.TheAurorian;

public class EntityRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TheAurorian.MODID);

    public static final RegistryObject<EntityType<CrystallineBeamEntity>> crystalline_beam = ENTITIES.register("crystalline_beam", () -> EntityType.Builder.<CrystallineBeamEntity>of(CrystallineBeamEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).fireImmune().setUpdateInterval(1).build("crystalline_beam"));
    public static final RegistryObject<EntityType<AurorianArrowEntity>> cerulean_arrow = ENTITIES.register("cerulean_arrow", () -> EntityType.Builder.<AurorianArrowEntity>of(CeruleanArrowEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("cerulean_arrow"));
    public static final RegistryObject<EntityType<AurorianArrowEntity>> crystal_arrow = ENTITIES.register("crystal_arrow", () -> EntityType.Builder.<AurorianArrowEntity>of(CrystalArrowEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("crystal_arrow"));

    public static final RegistryObject<EntityType<HollowEntity>> hollow = ENTITIES.register("hollow", () -> EntityType.Builder.<HollowEntity>of(HollowEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("hollow"));
    public static final RegistryObject<EntityType<UndeadKnightEntity>> undead_knight = ENTITIES.register("undead_knight", () -> EntityType.Builder.<UndeadKnightEntity>of(UndeadKnightEntity::new, MobCategory.MONSTER).sized(0.8F, 2.3F).clientTrackingRange(8).build("undead_knight"));
    public static final RegistryObject<EntityType<DungeonSlimeEntity>> dungeon_slime = ENTITIES.register("dungeon_slime", () -> EntityType.Builder.of(DungeonSlimeEntity::new, MobCategory.MONSTER).sized(2.04F, 2.04F).clientTrackingRange(10).build("dungeon_slime"));

    public static void entityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.hollow.get(), HollowEntity.createAttributes().build());
        event.put(EntityRegistry.undead_knight.get(), UndeadKnightEntity.createAttributes().build());
        event.put(EntityRegistry.dungeon_slime.get(), DungeonSlimeEntity.createAttributes().build());
    }

    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(EntityRegistry.hollow.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HollowEntity::checkSpawn, SpawnPlacementRegisterEvent.Operation.AND);
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegistry.crystalline_beam.get(), CrystallineBeamRenderer::new);
        event.registerEntityRenderer(EntityRegistry.cerulean_arrow.get(), (ctx) -> new AurorianArrowRenderer(ctx, new ResourceLocation(TheAurorian.MODID, "textures/entity/cerulean_arrow.png")));
        event.registerEntityRenderer(EntityRegistry.crystal_arrow.get(), (ctx) -> new AurorianArrowRenderer(ctx, new ResourceLocation(TheAurorian.MODID, "textures/entity/crystal_arrow.png")));

        event.registerEntityRenderer(EntityRegistry.hollow.get(), HollowRenderer::new);
        event.registerEntityRenderer(EntityRegistry.undead_knight.get(), UndeadKnightRenderer::new);
        event.registerEntityRenderer(EntityRegistry.dungeon_slime.get(), DungeonSlimeRenderer::new);
    }

    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CrystallineBeamModel.MODEL_LAYER_LOCATION, CrystallineBeamModel::createLayer);
        event.registerLayerDefinition(DungeonSlimeModel.MODEL_LAYER_LOCATION, DungeonSlimeModel::createLayer);
        event.registerLayerDefinition(DungeonSlimeModel.MODEL_LAYER_LOCATION_OUTER, DungeonSlimeModel::createOuterLayer);
    }

}
