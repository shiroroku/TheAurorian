package shiroroku.theaurorian.DataGen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.Registry.ItemRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DataGenLoot extends LootTableProvider {

    public DataGenLoot(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Set.of(), List.of(
                new SubProviderEntry(Blocks::new, LootContextParamSets.BLOCK)
        ), registries);
    }

    @Override
    protected void validate(WritableRegistry<LootTable> writableregistry, ValidationContext validationcontext, ProblemReporter.Collector problemreporter$collector) {
        return;
    }

    private static class Blocks extends BlockLootSubProvider {

        private final HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        public Blocks(HolderLookup.Provider provider) {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
        }

        @Override
        protected void generate() {
            // AUTO GENERATED
            BlockRegistry.BLOCKS_GEN.getEntries().stream().map(Supplier::get).forEach(this::dropSelf);

            // CUSTOM
            this.add(BlockRegistry.aurorian_coal_ore.get(), block -> createOreDrop(block, ItemRegistry.aurorian_coal.get()));
            this.add(BlockRegistry.aurorian_cobblestone_slab.get(), this::createSlabItemTable);
            this.add(BlockRegistry.aurorian_deepslate_slab.get(), this::createSlabItemTable);
            this.add(BlockRegistry.aurorian_grass_block.get(), block -> createSingleItemTableWithSilkTouch(block, BlockRegistry.aurorian_dirt.get()));
            this.add(BlockRegistry.aurorian_stone.get(), block-> createSingleItemTableWithSilkTouch(block, BlockRegistry.aurorian_cobblestone.get()));
            this.add(BlockRegistry.aurorian_tallgrass.get(), dropWithSickleOrShears(ItemRegistry.plant_fiber.get()));
            this.add(BlockRegistry.bright_bulb.get(), dropWithSickleOrShears(BlockRegistry.bright_bulb.get()));
            this.add(BlockRegistry.geode.get(), block -> createOreDrop(block, BlockRegistry.crystal.get().asItem()));
            this.add(BlockRegistry.lavender_block.get(), dropWithSickleOrShears(ItemRegistry.lavender.get()));
            this.add(BlockRegistry.petunia.get(), dropWithSickleOrShears(BlockRegistry.petunia.get()));
            this.add(BlockRegistry.silentwood_slab.get(), this::createSlabItemTable);
            this.add(BlockRegistry.silkberry_block.get(), dropWithSickleOrShears(ItemRegistry.silkberry.get()));
            this.dropSelf(BlockRegistry.aurorian_cobblestone_stairs.get());
            this.dropSelf(BlockRegistry.aurorian_deepslate_stairs.get());
            this.dropSelf(BlockRegistry.aurorian_furnace.get());
            this.dropSelf(BlockRegistry.chimney.get());
            this.dropSelf(BlockRegistry.crystal.get());
            this.dropSelf(BlockRegistry.darkstone_stairs.get());
            this.dropSelf(BlockRegistry.moon_gem.get());
            this.dropSelf(BlockRegistry.moonlight_forge.get());
            this.dropSelf(BlockRegistry.runestone_bars.get());
            this.dropSelf(BlockRegistry.runestone_stairs.get());
            this.dropSelf(BlockRegistry.scrapper.get());
            this.dropSelf(BlockRegistry.silentwood_chest.get());
            this.dropSelf(BlockRegistry.silentwood_crafting_table.get());
            this.dropSelf(BlockRegistry.silentwood_fence.get());
            this.add(BlockRegistry.silentwood_leaves.get(), block -> createSelfDropDispatchTable(BlockRegistry.silentwood_leaves.get(), hasShearsOrSilkTouch(),
                    applyExplosionCondition(BlockRegistry.silentwood_leaves.get(), LootItem.lootTableItem(BlockRegistry.silentwood_sapling.get()))
                            .when(BonusLevelTableCondition.bonusLevelFlatChance(enchantments.getOrThrow(Enchantments.FORTUNE), 0.05F, 0.0625F, 0.083333336F, 0.1F)))
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .when(doesNotHaveShearsOrSilkTouch())
                            .add(applyExplosionDecay(BlockRegistry.silentwood_leaves.get(), LootItem.lootTableItem(ItemRegistry.silentwood_stick.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                    .when(BonusLevelTableCondition.bonusLevelFlatChance(enchantments.getOrThrow(Enchantments.FORTUNE), 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F)))));
            this.dropSelf(BlockRegistry.silentwood_log.get());
            this.dropSelf(BlockRegistry.silentwood_sapling.get());
            this.dropSelf(BlockRegistry.silentwood_stairs.get());
            this.dropSelf(BlockRegistry.aurorian_deepslate_wall.get());
            this.dropSelf(BlockRegistry.aurorian_cobblestone_wall.get());
            // ! dont forget to add to function below too <3

        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            // AUTO GENERATED
            List<Block> gen = new ArrayList<>(BlockRegistry.BLOCKS_GEN.getEntries().stream().map(Supplier::get).toList());

            // CUSTOM
            gen.add(BlockRegistry.aurorian_coal_ore.get());
            gen.add(BlockRegistry.aurorian_cobblestone_slab.get());
            gen.add(BlockRegistry.aurorian_cobblestone_stairs.get());
            gen.add(BlockRegistry.aurorian_cobblestone_wall.get());
            gen.add(BlockRegistry.aurorian_deepslate_slab.get());
            gen.add(BlockRegistry.aurorian_deepslate_stairs.get());
            gen.add(BlockRegistry.aurorian_deepslate_wall.get());
            gen.add(BlockRegistry.aurorian_furnace.get());
            gen.add(BlockRegistry.aurorian_grass_block.get());
            gen.add(BlockRegistry.aurorian_stone.get());
            gen.add(BlockRegistry.aurorian_tallgrass.get());
            gen.add(BlockRegistry.bright_bulb.get());
            gen.add(BlockRegistry.chimney.get());
            gen.add(BlockRegistry.crystal.get());
            gen.add(BlockRegistry.darkstone_stairs.get());
            gen.add(BlockRegistry.geode.get());
            gen.add(BlockRegistry.lavender_block.get());
            gen.add(BlockRegistry.moon_gem.get());
            gen.add(BlockRegistry.moonlight_forge.get());
            gen.add(BlockRegistry.petunia.get());
            gen.add(BlockRegistry.runestone_bars.get());
            gen.add(BlockRegistry.runestone_stairs.get());
            gen.add(BlockRegistry.scrapper.get());
            gen.add(BlockRegistry.silentwood_chest.get());
            gen.add(BlockRegistry.silentwood_crafting_table.get());
            gen.add(BlockRegistry.silentwood_fence.get());
            gen.add(BlockRegistry.silentwood_leaves.get());
            gen.add(BlockRegistry.silentwood_log.get());
            gen.add(BlockRegistry.silentwood_sapling.get());
            gen.add(BlockRegistry.silentwood_slab.get());
            gen.add(BlockRegistry.silentwood_stairs.get());
            gen.add(BlockRegistry.silkberry_block.get());
            return gen;
        }

        private LootItemCondition.Builder hasShearsOrSilkTouch() {
            return HAS_SHEARS.or(this.hasSilkTouch());
        }

        private LootItemCondition.Builder doesNotHaveShearsOrSilkTouch() {
            return this.hasShearsOrSilkTouch().invert();
        }

        private Function<Block, LootTable.Builder> dropWithSickleOrShears(ItemLike drops) {
            return block -> LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_SHEARS).add(LootItem.lootTableItem(drops)));
        }
    }
}
