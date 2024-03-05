package shiroroku.theaurorian.Registry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import shiroroku.theaurorian.Blocks.AurorianDeepslateBlock;
import shiroroku.theaurorian.Blocks.AurorianFurnace.AurorianFurnaceBlock;
import shiroroku.theaurorian.Blocks.AurorianFurnace.ChimneyBlock;
import shiroroku.theaurorian.Blocks.AurorianGrassBlock;
import shiroroku.theaurorian.Blocks.BossSpawner.BossSpawnerBlock;
import shiroroku.theaurorian.Blocks.Crystal.CrystalBlock;
import shiroroku.theaurorian.Blocks.DungeonGateKeyHole;
import shiroroku.theaurorian.Blocks.FogWallBlock;
import shiroroku.theaurorian.Blocks.MoonlightForge.MoonlightForgeBlock;
import shiroroku.theaurorian.Blocks.Scrapper.ScrapperBlock;
import shiroroku.theaurorian.Blocks.SilentwoodCraftingTable.SilentwoodCraftingTableBlock;
import shiroroku.theaurorian.TheAurorian;
import shiroroku.theaurorian.Util.TooltipUtil;
import shiroroku.theaurorian.World.Feature.SilentwoodTreeFeature;

import java.util.List;
import java.util.function.Supplier;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TheAurorian.MODID); // no datagen
    public static final DeferredRegister<Block> BLOCKS_GEN = DeferredRegister.create(ForgeRegistries.BLOCKS, TheAurorian.MODID); // basic block, all sided model which drops itself
    public static final DeferredRegister<Block> BLOCKS_GEN_NL = DeferredRegister.create(ForgeRegistries.BLOCKS, TheAurorian.MODID); // nl = generates model without loot table
    public static final DeferredRegister<Block> BLOCKS_GEN_NL_PLANT = DeferredRegister.create(ForgeRegistries.BLOCKS, TheAurorian.MODID); // plants, blocks with cross model

    // Runestone
    public static final RegistryObject<Block> runestone = regBlockItem(BLOCKS_GEN, "runestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(-1.0F, 3600000.0F)));
    public static final RegistryObject<Block> runestone_bars = regBlockItem(BLOCKS, "runestone_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.copy(runestone.get())));
    public static final RegistryObject<Block> runestone_gate = regBlockItem(BLOCKS_GEN, "runestone_gate", () -> new Block(BlockBehaviour.Properties.copy(runestone.get())));
    public static final RegistryObject<Block> runestone_gate_keyhole = regBlockItem(BLOCKS_GEN, "runestone_gate_keyhole", () -> new DungeonGateKeyHole(ItemRegistry.runestone_key, BlockBehaviour.Properties.copy(runestone.get()), true));
    public static final RegistryObject<Block> runestone_gate_loot_keyhole = regBlockItem(BLOCKS_GEN, "runestone_gate_loot_keyhole", () -> new DungeonGateKeyHole(ItemRegistry.runestone_loot_key, BlockBehaviour.Properties.copy(runestone.get())));
    public static final RegistryObject<Block> runestone_lamp = regBlockItem(BLOCKS_GEN, "runestone_lamp", () -> new Block(BlockBehaviour.Properties.copy(runestone.get()).lightLevel((state) -> 15)));
    public static final RegistryObject<Block> runestone_smooth = regBlockItem(BLOCKS_GEN, "runestone_smooth", () -> new Block(BlockBehaviour.Properties.copy(runestone.get())));
    public static final RegistryObject<Block> runestone_stairs = regBlockItem(BLOCKS, "runestone_stairs", () -> new StairBlock(() -> runestone.get().defaultBlockState(), BlockBehaviour.Properties.copy(runestone.get())));

    // Natural
    public static final RegistryObject<Block> aurorian_cobblestone = regBlockItem(BLOCKS_GEN, "aurorian_cobblestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> aurorian_dirt = regBlockItem(BLOCKS_GEN, "aurorian_dirt", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistryObject<Block> aurorian_grass = regBlockItem(BLOCKS, "aurorian_grass", () -> new AurorianGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> aurorian_stone = regBlockItem(BLOCKS_GEN_NL, "aurorian_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> aurorian_deepslate = regBlockItem(BLOCKS_GEN, "aurorian_deepslate", () -> new AurorianDeepslateBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).randomTicks()));
    public static final RegistryObject<Block> silentwood_leaves = regBlockItem(BLOCKS_GEN_NL, "silentwood_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LEAVES)));
    public static final RegistryObject<Block> silentwood_log = regBlockItemWithBurntime(BLOCKS, "silentwood_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LOG)), 300);
    public static final RegistryObject<Block> silentwood_planks = regBlockItemWithBurntime(BLOCKS_GEN, "silentwood_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SPRUCE_PLANKS)), 300);
    public static final RegistryObject<Block> silentwood_sapling = regBlockItemWithBurntime(BLOCKS_GEN_NL_PLANT, "silentwood_sapling", () -> new SaplingBlock(new SilentwoodTreeFeature(), BlockBehaviour.Properties.copy(Blocks.SPRUCE_SAPLING)), 100);

    // Plants
    public static final RegistryObject<Block> aurorian_tallgrass = regBlockItem(BLOCKS_GEN_NL_PLANT, "aurorian_tallgrass", () -> new TallGrassBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)));
    public static final RegistryObject<Block> lavender_block = regBlockItem(BLOCKS_GEN_NL_PLANT, "lavender_block", () -> new BushBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)));
    public static final RegistryObject<Block> petunia = regBlockItem(BLOCKS_GEN_NL_PLANT, "petunia", () -> new BushBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)));
    public static final RegistryObject<Block> silkberry_block = regBlockItem(BLOCKS_GEN_NL_PLANT, "silkberry_block", () -> new BushBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)));
    public static final RegistryObject<Block> bright_bulb = regBlockItem(BLOCKS_GEN_NL_PLANT, "bright_bulb", () -> new BushBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS).lightLevel((state) -> 10)));

    // Ores
    public static final RegistryObject<Block> cerulean_ore = regBlockItem(BLOCKS_GEN, "cerulean_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> moonstone_ore = regBlockItem(BLOCKS_GEN, "moonstone_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> deepslate_cerulean_ore = regBlockItem(BLOCKS_GEN, "deepslate_cerulean_ore", () -> new Block(BlockBehaviour.Properties.copy(cerulean_ore.get())));
    public static final RegistryObject<Block> deepslate_moonstone_ore = regBlockItem(BLOCKS_GEN, "deepslate_moonstone_ore", () -> new Block(BlockBehaviour.Properties.copy(moonstone_ore.get())));
    public static final RegistryObject<Block> geode = regBlockItem(BLOCKS_GEN_NL, "geode", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> aurorian_coal_ore = regBlockItem(BLOCKS_GEN_NL, "aurorian_coal_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_ORE)));

    // Machines
    public static final RegistryObject<Block> scrapper = regBlockItem(BLOCKS, "scrapper", () -> new ScrapperBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> moonlight_forge = regBlockItem(BLOCKS, "moonlight_forge", () -> new MoonlightForgeBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));
    public static final RegistryObject<Block> aurorian_furnace = regBlockItem(BLOCKS, "aurorian_furnace", () -> new AurorianFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.FURNACE)));
    public static final RegistryObject<Block> silentwood_crafting_table = regBlockItem(BLOCKS, "silentwood_crafting_table", () -> new SilentwoodCraftingTableBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));
    public static final RegistryObject<Block> boss_spawner = regBlockItem(BLOCKS, "boss_spawner", () -> new BossSpawnerBlock(BlockBehaviour.Properties.copy(Blocks.SPAWNER)));

    // Other
    public static final RegistryObject<Block> fog_wall = regBlockItem(BLOCKS, "fog_wall", () -> new FogWallBlock(BlockBehaviour.Properties.copy(runestone.get()).noCollission().noOcclusion().lightLevel((state) -> 10)));
    public static final RegistryObject<Block> moon_gem = regBlockItem(BLOCKS, "moon_gem", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));
    public static final RegistryObject<Block> chimney = regBlockItem(BLOCKS, "chimney", () -> new ChimneyBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));
    public static final RegistryObject<Block> crystal = regBlockItem(BLOCKS, "crystal", () -> new CrystalBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().lightLevel((state) -> 15).sound(SoundType.GLASS)));

    public static void register(IEventBus bus) {
        BLOCKS_GEN.register(bus);
        BLOCKS.register(bus);
        BLOCKS_GEN_NL.register(bus);
        BLOCKS_GEN_NL_PLANT.register(bus);
    }

    /**
     * Registers a BlockItem while registering the block.
     */
    private static <I extends Block> RegistryObject<I> regBlockItem(DeferredRegister<Block> registry, final String id, final Supplier<? extends I> supplier) {
        return regBlockItemWithBurntime(registry, id, supplier, 0);
    }

    private static <I extends Block> RegistryObject<I> regBlockItemWithBurntime(DeferredRegister<Block> registry, final String id, final Supplier<? extends I> supplier, int burnTime) {
        RegistryObject<I> createdBlock = registry.register(id, supplier);
        ItemRegistry.ITEMS.register(id, () -> new BlockItem(createdBlock.get(), ItemRegistry.defaultProp()) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return burnTime == 0 ? super.getBurnTime(itemStack, recipeType) : burnTime;
            }

            @Override
            public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
                super.appendHoverText(pStack, pLevel, TooltipUtil.tryAddDesc(pStack, pTooltipComponents), pIsAdvanced);
            }
        });
        return createdBlock;
    }
}
