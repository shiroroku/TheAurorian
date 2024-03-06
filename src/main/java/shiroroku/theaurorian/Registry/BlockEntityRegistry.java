package shiroroku.theaurorian.Registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import shiroroku.theaurorian.Blocks.AurorianFurnace.AurorianFurnaceBlockEntity;
import shiroroku.theaurorian.Blocks.BossSpawner.BossSpawnerBlockEntity;
import shiroroku.theaurorian.Blocks.Crystal.CrystalBlockEntity;
import shiroroku.theaurorian.Blocks.MoonlightForge.MoonlightForgeBlockEntity;
import shiroroku.theaurorian.Blocks.Scrapper.ScrapperBlockEntity;
import shiroroku.theaurorian.Blocks.SilentwoodChest.SilentwoodChestBlockEntity;
import shiroroku.theaurorian.TheAurorian;

import java.util.function.Supplier;

public class BlockEntityRegistry {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TheAurorian.MODID);

    public static final Supplier<BlockEntityType<ScrapperBlockEntity>> scrapper = BLOCK_ENTITIES.register("scrapper", () -> BlockEntityType.Builder.of(ScrapperBlockEntity::new, BlockRegistry.scrapper.get()).build(null));
    public static final Supplier<BlockEntityType<MoonlightForgeBlockEntity>> moonlight_forge = BLOCK_ENTITIES.register("moonlight_forge", () -> BlockEntityType.Builder.of(MoonlightForgeBlockEntity::new, BlockRegistry.moonlight_forge.get()).build(null));
    public static final Supplier<BlockEntityType<AurorianFurnaceBlockEntity>> aurorian_furnace = BLOCK_ENTITIES.register("aurorian_furnace", () -> BlockEntityType.Builder.of(AurorianFurnaceBlockEntity::new, BlockRegistry.aurorian_furnace.get()).build(null));
    public static final Supplier<BlockEntityType<CrystalBlockEntity>> crystal = BLOCK_ENTITIES.register("crystal", () -> BlockEntityType.Builder.of(CrystalBlockEntity::new, BlockRegistry.crystal.get()).build(null));
    public static final Supplier<BlockEntityType<BossSpawnerBlockEntity>> boss_spawner = BLOCK_ENTITIES.register("boss_spawner", () -> BlockEntityType.Builder.of(BossSpawnerBlockEntity::new, BlockRegistry.boss_spawner.get()).build(null));
    public static final Supplier<BlockEntityType<? extends ChestBlockEntity>> silentwood_chest = BLOCK_ENTITIES.register("silentwood_chest", () -> BlockEntityType.Builder.of(SilentwoodChestBlockEntity::new, BlockRegistry.silentwood_chest.get()).build(null));

}
