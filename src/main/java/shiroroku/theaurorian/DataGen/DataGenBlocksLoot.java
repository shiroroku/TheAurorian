package shiroroku.theaurorian.DataGen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.Tags;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.Registry.ItemRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class DataGenBlocksLoot extends LootTableProvider {

    public DataGenBlocksLoot(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
        map.forEach((location, lootTable) -> LootTables.validate(validationtracker, location, lootTable));
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return List.of(Pair.of(Blocks::new, LootContextParamSets.BLOCK));
    }

    private static class Blocks extends BlockLoot {

        // mojang pls
        private static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
        private static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS));// sickles will only work with this :c they reference shears item instead of tag like what???
        private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
        private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();

        @Override
        protected void addTables() {
            // AUTO GENERATED
            BlockRegistry.BLOCKS_GEN.getEntries().stream().map(Supplier::get).forEach(this::dropSelf);

            // CUSTOM
            this.add(BlockRegistry.aurorian_grass.get(), (block) -> createSingleItemTableWithSilkTouch(block, BlockRegistry.aurorian_dirt.get()));
            this.add(BlockRegistry.aurorian_stone.get(), (block) -> createSingleItemTableWithSilkTouch(block, BlockRegistry.aurorian_cobblestone.get()));
            this.add(BlockRegistry.aurorian_tallgrass.get(), dropWithSickleOrShears(ItemRegistry.plant_fiber.get()));
            this.add(BlockRegistry.lavender_block.get(), dropWithSickleOrShears(ItemRegistry.lavender.get()));
            this.dropSelf(BlockRegistry.runestone_bars.get());
            this.dropSelf(BlockRegistry.runestone_stairs.get());
            this.add(BlockRegistry.bright_bulb.get(), dropWithSickleOrShears(BlockRegistry.bright_bulb.get()));
            this.add(BlockRegistry.petunia.get(), dropWithSickleOrShears(BlockRegistry.petunia.get()));
            this.add(BlockRegistry.silkberry_block.get(), dropWithSickleOrShears(ItemRegistry.silkberry.get()));
            this.add(BlockRegistry.silentwood_leaves.get(), (block) -> createSelfDropDispatchTable(BlockRegistry.silentwood_leaves.get(), HAS_SHEARS_OR_SILK_TOUCH,
                    applyExplosionCondition(BlockRegistry.silentwood_leaves.get(), LootItem.lootTableItem(BlockRegistry.silentwood_sapling.get()))
                            .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.05F, 0.0625F, 0.083333336F, 0.1F)))
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .when(HAS_NO_SHEARS_OR_SILK_TOUCH)
                            .add(applyExplosionDecay(BlockRegistry.silentwood_leaves.get(), LootItem.lootTableItem(ItemRegistry.silentwood_stick.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                    .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F)))));
            this.dropSelf(BlockRegistry.moon_gem.get());
            this.dropSelf(BlockRegistry.moonlight_forge.get());
            this.dropSelf(BlockRegistry.scrapper.get());
            this.dropSelf(BlockRegistry.silentwood_chest.get());
            this.dropSelf(BlockRegistry.aurorian_furnace.get());
            this.dropSelf(BlockRegistry.silentwood_log.get());
            this.dropSelf(BlockRegistry.silentwood_sapling.get());
            this.dropSelf(BlockRegistry.chimney.get());
            this.dropSelf(BlockRegistry.crystal.get());
            this.dropSelf(BlockRegistry.silentwood_crafting_table.get());
            this.add(BlockRegistry.geode.get(), (block) -> createOreDrop(block, BlockRegistry.crystal.get().asItem()));
            this.add(BlockRegistry.aurorian_coal_ore.get(), (block) -> createOreDrop(block, ItemRegistry.aurorian_coal.get()));
            // ! dont forget to add to function below too <3
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            // AUTO GENERATED
            List<Block> gen = new ArrayList<>(BlockRegistry.BLOCKS_GEN.getEntries().stream().map(Supplier::get).toList());

            // CUSTOM
            gen.add(BlockRegistry.aurorian_grass.get());
            gen.add(BlockRegistry.aurorian_stone.get());
            gen.add(BlockRegistry.aurorian_tallgrass.get());
            gen.add(BlockRegistry.geode.get());
            gen.add(BlockRegistry.lavender_block.get());
            gen.add(BlockRegistry.moon_gem.get());
            gen.add(BlockRegistry.moonlight_forge.get());
            gen.add(BlockRegistry.petunia.get());
            gen.add(BlockRegistry.runestone_bars.get());
            gen.add(BlockRegistry.runestone_stairs.get());
            gen.add(BlockRegistry.scrapper.get());
            gen.add(BlockRegistry.silentwood_leaves.get());
            gen.add(BlockRegistry.silentwood_log.get());
            gen.add(BlockRegistry.silentwood_sapling.get());
            gen.add(BlockRegistry.silkberry_block.get());
            gen.add(BlockRegistry.aurorian_furnace.get());
            gen.add(BlockRegistry.chimney.get());
            gen.add(BlockRegistry.crystal.get());
            gen.add(BlockRegistry.silentwood_crafting_table.get());
            gen.add(BlockRegistry.bright_bulb.get());
            gen.add(BlockRegistry.aurorian_coal_ore.get());
            gen.add(BlockRegistry.silentwood_chest.get());
            return gen;
        }

        private Function<Block, LootTable.Builder> dropWithSickleOrShears(ItemLike drops) {
            return (block) -> LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_SHEARS).add(LootItem.lootTableItem(drops)));
        }
    }
}
