package shiroroku.theaurorian.Registry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;
import shiroroku.theaurorian.Blocks.*;
import shiroroku.theaurorian.Blocks.AurorianFurnace.AurorianFurnaceBlock;
import shiroroku.theaurorian.Blocks.AurorianFurnace.ChimneyBlock;
import shiroroku.theaurorian.Blocks.BossSpawner.BossSpawnerBlock;
import shiroroku.theaurorian.Blocks.Crystal.CrystalBlock;
import shiroroku.theaurorian.Blocks.MoonlightForge.MoonlightForgeBlock;
import shiroroku.theaurorian.Blocks.Scrapper.ScrapperBlock;
import shiroroku.theaurorian.Blocks.SilentwoodChest.SilentwoodChestBlock;
import shiroroku.theaurorian.Blocks.SilentwoodCraftingTable.SilentwoodCraftingTableBlock;
import shiroroku.theaurorian.TheAurorian;
import shiroroku.theaurorian.Util.TooltipUtil;

import java.util.List;
import java.util.function.Supplier;

public class BlockRegistry {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TheAurorian.MODID); // no datagen
    public static final DeferredRegister.Blocks BLOCKS_GEN = DeferredRegister.createBlocks(TheAurorian.MODID); // basic block, all sided model which drops itself
    public static final DeferredRegister.Blocks BLOCKS_GEN_NL = DeferredRegister.createBlocks(TheAurorian.MODID); // nl = generates model without loot table
    public static final DeferredRegister.Blocks BLOCKS_GEN_NL_PLANT = DeferredRegister.createBlocks(TheAurorian.MODID); // plants, blocks with cross model

    // Runestone
    public static final DeferredBlock<Block> runestone = regBlockItem(BLOCKS_GEN, "runestone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(-1.0F, 3600000.0F)));
    public static final DeferredBlock<Block> runestone_bars = regBlockItem(BLOCKS, "runestone_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(runestone.get())));
    public static final DeferredBlock<Block> runestone_gate = regBlockItem(BLOCKS_GEN, "runestone_gate", () -> new Block(BlockBehaviour.Properties.ofFullCopy(runestone.get())));
    public static final DeferredBlock<Block> runestone_gate_keyhole = regBlockItem(BLOCKS_GEN, "runestone_gate_keyhole", () -> new DungeonGateKeyHole(ItemRegistry.runestone_key, BlockBehaviour.Properties.ofFullCopy(runestone.get()), true));
    public static final DeferredBlock<Block> runestone_gate_loot_keyhole = regBlockItem(BLOCKS_GEN, "runestone_gate_loot_keyhole", () -> new DungeonGateKeyHole(ItemRegistry.runestone_loot_key, BlockBehaviour.Properties.ofFullCopy(runestone.get())));
    public static final DeferredBlock<Block> runestone_lamp = regBlockItem(BLOCKS_GEN, "runestone_lamp", () -> new Block(BlockBehaviour.Properties.ofFullCopy(runestone.get()).lightLevel((state) -> 15)));
    public static final DeferredBlock<Block> runestone_smooth = regBlockItem(BLOCKS_GEN, "runestone_smooth", () -> new Block(BlockBehaviour.Properties.ofFullCopy(runestone.get())));
    public static final DeferredBlock<Block> runestone_stairs = regBlockItem(BLOCKS, "runestone_stairs", () -> new StairBlock(runestone.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(runestone.get())));

    // Darkstone
    public static final DeferredBlock<Block> darkstone = regBlockItem(BLOCKS_GEN, "darkstone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(runestone.get())));
    public static final DeferredBlock<Block> darkstone_chipped = regBlockItem(BLOCKS_GEN, "darkstone_chipped", () -> new Block(BlockBehaviour.Properties.ofFullCopy(darkstone.get())));
    public static final DeferredBlock<Block> darkstone_gate = regBlockItem(BLOCKS_GEN, "darkstone_gate", () -> new Block(BlockBehaviour.Properties.ofFullCopy(darkstone.get())));
    public static final DeferredBlock<Block> darkstone_gate_keyhole = regBlockItem(BLOCKS_GEN, "darkstone_gate_keyhole", () -> new DungeonGateKeyHole(ItemRegistry.darkstone_key, BlockBehaviour.Properties.ofFullCopy(darkstone.get())));
    public static final DeferredBlock<Block> darkstone_lamp = regBlockItem(BLOCKS_GEN, "darkstone_lamp", () -> new Block(BlockBehaviour.Properties.ofFullCopy(darkstone.get()).lightLevel((state) -> 15)));
    public static final DeferredBlock<Block> darkstone_pillar = regBlockItem(BLOCKS_GEN, "darkstone_pillar", () -> new Block(BlockBehaviour.Properties.ofFullCopy(darkstone.get())));
    public static final DeferredBlock<Block> darkstone_stairs = regBlockItem(BLOCKS, "darkstone_stairs", () -> new StairBlock(darkstone.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(darkstone.get())));

    // Moon Temple
    public static final DeferredBlock<Block> moon_temple_bricks = regBlockItem(BLOCKS_GEN, "moon_temple_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(runestone.get())));
    public static final DeferredBlock<Block> moon_temple_bars = regBlockItem(BLOCKS, "moon_temple_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(moon_temple_bricks.get())));
    public static final DeferredBlock<Block> moon_temple_bricks_smooth = regBlockItem(BLOCKS_GEN, "moon_temple_bricks_smooth", () -> new Block(BlockBehaviour.Properties.ofFullCopy(moon_temple_bricks.get())));
    public static final DeferredBlock<Block> moon_temple_gate = regBlockItem(BLOCKS_GEN, "moon_temple_gate", () -> new Block(BlockBehaviour.Properties.ofFullCopy(moon_temple_bricks.get())));
    public static final DeferredBlock<Block> moon_temple_gate_keyhole = regBlockItem(BLOCKS_GEN, "moon_temple_gate_keyhole", () -> new DungeonGateKeyHole(ItemRegistry.moon_temple_key, BlockBehaviour.Properties.ofFullCopy(moon_temple_bricks.get())));
    public static final DeferredBlock<Block> moon_temple_interior_gate = regBlockItem(BLOCKS_GEN, "moon_temple_interior_gate", () -> new Block(BlockBehaviour.Properties.ofFullCopy(moon_temple_bricks.get())));
    public static final DeferredBlock<Block> moon_temple_interior_gate_keyhole = regBlockItem(BLOCKS_GEN, "moon_temple_interior_gate_keyhole", () -> new DungeonGateKeyHole(ItemRegistry.moon_temple_interior_key, BlockBehaviour.Properties.ofFullCopy(moon_temple_bricks.get())));
    public static final DeferredBlock<Block> moon_temple_lamp = regBlockItem(BLOCKS_GEN, "moon_temple_lamp", () -> new Block(BlockBehaviour.Properties.ofFullCopy(moon_temple_bricks.get()).lightLevel((state) -> 15)));
    public static final DeferredBlock<Block> moon_temple_stairs = regBlockItem(BLOCKS, "moon_temple_stairs", () -> new StairBlock(moon_temple_bricks.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(moon_temple_bricks.get())));

    // Natural
    public static final DeferredBlock<Block> aurorian_cobblestone = regBlockItem(BLOCKS_GEN, "aurorian_cobblestone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
    public static final DeferredBlock<Block> aurorian_cobblestone_slab = regBlockItem(BLOCKS, "aurorian_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(BlockRegistry.aurorian_cobblestone.get())));
    public static final DeferredBlock<Block> aurorian_cobblestone_stairs = regBlockItem(BLOCKS, "aurorian_cobblestone_stairs", () -> new StairBlock(aurorian_cobblestone.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(aurorian_cobblestone.get())));
    public static final DeferredBlock<Block> aurorian_cobblestone_wall = regBlockItem(BLOCKS, "aurorian_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(BlockRegistry.aurorian_cobblestone.get())));
    public static final DeferredBlock<Block> aurorian_deepslate = regBlockItem(BLOCKS_GEN, "aurorian_deepslate", () -> new AurorianDeepslateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).randomTicks()));
    public static final DeferredBlock<Block> aurorian_deepslate_slab = regBlockItem(BLOCKS, "aurorian_deepslate_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(BlockRegistry.aurorian_deepslate.get())));
    public static final DeferredBlock<Block> aurorian_deepslate_stairs = regBlockItem(BLOCKS, "aurorian_deepslate_stairs", () -> new StairBlock(Blocks.DEEPSLATE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(aurorian_deepslate.get())));
    public static final DeferredBlock<Block> aurorian_deepslate_wall = regBlockItem(BLOCKS, "aurorian_deepslate_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(BlockRegistry.aurorian_deepslate.get())));
    public static final DeferredBlock<Block> aurorian_dirt = regBlockItem(BLOCKS_GEN, "aurorian_dirt", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)));
    public static final DeferredBlock<Block> aurorian_grass_block = regBlockItem(BLOCKS, "aurorian_grass", () -> new AurorianGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)));
    public static final DeferredBlock<Block> aurorian_stone = regBlockItem(BLOCKS_GEN_NL, "aurorian_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> silentwood_fence = regBlockItemWithBurntime(BLOCKS, "silentwood_fence", () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(BlockRegistry.silentwood_planks.get())), 300);
    public static final DeferredBlock<Block> silentwood_leaves = regBlockItem(BLOCKS_GEN_NL, "silentwood_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)));
    public static final DeferredBlock<Block> silentwood_log = regBlockItemWithBurntime(BLOCKS, "silentwood_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG)), 300);
    public static final DeferredBlock<Block> silentwood_planks = regBlockItemWithBurntime(BLOCKS_GEN, "silentwood_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)), 300);
    public static final DeferredBlock<Block> silentwood_sapling = regBlockItemWithBurntime(BLOCKS_GEN_NL_PLANT, "silentwood_sapling", () -> new SaplingBlock(AurorianTreeGrowers.SILENTWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)), 100);
    public static final DeferredBlock<Block> silentwood_slab = regBlockItemWithBurntime(BLOCKS, "silentwood_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(BlockRegistry.silentwood_planks.get())), 150);
    public static final DeferredBlock<Block> silentwood_stairs = regBlockItemWithBurntime(BLOCKS, "silentwood_stairs", () -> new StairBlock(silentwood_planks.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(silentwood_planks.get())), 300);

    // Plants
    public static final DeferredBlock<Block> aurorian_tallgrass = regBlockItem(BLOCKS_GEN_NL_PLANT, "aurorian_tallgrass", () -> new TallGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    public static final DeferredBlock<Block> bright_bulb = regBlockItem(BLOCKS_GEN_NL_PLANT, "bright_bulb", () -> new BushBlockImpl(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS).lightLevel((state) -> 10)));
    public static final DeferredBlock<Block> lavender_block = regBlockItem(BLOCKS_GEN_NL_PLANT, "lavender_block", () -> new BushBlockImpl(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    public static final DeferredBlock<Block> petunia = regBlockItem(BLOCKS_GEN_NL_PLANT, "petunia", () -> new BushBlockImpl(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    public static final DeferredBlock<Block> silkberry_block = regBlockItem(BLOCKS_GEN_NL_PLANT, "silkberry_block", () -> new BushBlockImpl(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));

    // Ores
    public static final DeferredBlock<Block> aurorian_coal_ore = regBlockItem(BLOCKS_GEN_NL, "aurorian_coal_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COAL_ORE)));
    public static final DeferredBlock<Block> cerulean_ore = regBlockItem(BLOCKS_GEN, "cerulean_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
    public static final DeferredBlock<Block> deepslate_cerulean_ore = regBlockItem(BLOCKS_GEN, "deepslate_cerulean_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(cerulean_ore.get())));
    public static final DeferredBlock<Block> moonstone_ore = regBlockItem(BLOCKS_GEN, "moonstone_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
    public static final DeferredBlock<Block> deepslate_moonstone_ore = regBlockItem(BLOCKS_GEN, "deepslate_moonstone_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(moonstone_ore.get())));
    public static final DeferredBlock<Block> geode = regBlockItem(BLOCKS_GEN_NL, "geode", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));

    // Machines
    public static final DeferredBlock<Block> aurorian_furnace = regBlockItem(BLOCKS, "aurorian_furnace", () -> new AurorianFurnaceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE)));
    public static final DeferredBlock<Block> boss_spawner = regBlockItem(BLOCKS, "boss_spawner", () -> new BossSpawnerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPAWNER)));
    public static final DeferredBlock<Block> moonlight_forge = regBlockItem(BLOCKS, "moonlight_forge", () -> new MoonlightForgeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));
    public static final DeferredBlock<Block> scrapper = regBlockItem(BLOCKS, "scrapper", () -> new ScrapperBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> silentwood_chest = regBlockItem(BLOCKS, "silentwood_chest", () -> new SilentwoodChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
    public static final DeferredBlock<Block> silentwood_crafting_table = regBlockItem(BLOCKS, "silentwood_crafting_table", () -> new SilentwoodCraftingTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)));

    // Other
    public static final DeferredBlock<Block> aurorian_portal = regBlockItem(BLOCKS, "aurorian_portal", () -> new AurorianPortal(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_PORTAL)));
    public static final DeferredBlock<Block> aurorian_portal_frame = regBlockItem(BLOCKS_GEN, "aurorian_portal_frame", () -> new AurorianPortalFrame(BlockBehaviour.Properties.ofFullCopy(BlockRegistry.aurorian_cobblestone.get())));
    public static final DeferredBlock<Block> chimney = regBlockItem(BLOCKS, "chimney", () -> new ChimneyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));
    public static final DeferredBlock<Block> crystal = regBlockItem(BLOCKS, "crystal", () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().lightLevel((state) -> 15).sound(SoundType.GLASS)));
    public static final DeferredBlock<Block> fog_wall = regBlockItem(BLOCKS, "fog_wall", () -> new FogWallBlock(BlockBehaviour.Properties.ofFullCopy(runestone.get()).noCollission().noOcclusion().lightLevel((state) -> 10)));
    public static final DeferredBlock<Block> moon_gem = regBlockItem(BLOCKS, "moon_gem", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));

    public static void register(IEventBus bus) {
        // The order of these matter because some blocks copy from previous register
        BLOCKS_GEN.register(bus);
        BLOCKS.register(bus);
        BLOCKS_GEN_NL.register(bus);
        BLOCKS_GEN_NL_PLANT.register(bus);
    }

    /**
     * Registers a BlockItem while registering the block.
     */
    private static <I extends Block> DeferredBlock<I> regBlockItem(DeferredRegister.Blocks registry, final String id, final Supplier<? extends I> supplier) {
        return regBlockItemWithBurntime(registry, id, supplier, 0);
    }

    private static <I extends Block> DeferredBlock<I> regBlockItemWithBurntime(DeferredRegister.Blocks registry, final String id, final Supplier<? extends I> supplier, int burnTime) {
        DeferredBlock<I> createdBlock = registry.register(id, supplier);
        ItemRegistry.ITEMS.register(id, () -> new BlockItem(createdBlock.get(), ItemRegistry.defaultProp()) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return burnTime == 0 ? super.getBurnTime(itemStack, recipeType) : burnTime;
            }

            @Override
            public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                super.appendHoverText(stack, context, TooltipUtil.tryAddDesc(stack, tooltipComponents), tooltipFlag);
            }
        });
        return createdBlock;
    }
}
