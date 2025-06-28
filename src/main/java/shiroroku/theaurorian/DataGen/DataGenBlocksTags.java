package shiroroku.theaurorian.DataGen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.TheAurorian;

import java.util.concurrent.CompletableFuture;

public class DataGenBlocksTags extends BlockTagsProvider {

    public static final TagKey<Block> CERULEAN_ORE = BlockTags.create(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "cerulean_ore"));
    public static final TagKey<Block> DUNGEON_BRICKS = BlockTags.create(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "dungeon_bricks"));
    public static final TagKey<Block> DUNGEON_GATES = BlockTags.create(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "dungeon_gates"));
    public static final TagKey<Block> MOONSTONE_ORE = BlockTags.create(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "moonstone_ore"));

    @SuppressWarnings("deprecation")
    protected DataGenBlocksTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TheAurorian.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
                .add(BlockRegistry.aurorian_deepslate.get());
        this.tag(BlockTags.DIRT)
                .add(BlockRegistry.aurorian_dirt.get())
                .add(BlockRegistry.aurorian_grass_block.get());
        this.tag(BlockTags.FLOWERS)
                .add(BlockRegistry.bright_bulb.get())
                .add(BlockRegistry.petunia.get());
        this.tag(BlockTags.LEAVES)
                .add(BlockRegistry.silentwood_leaves.get());
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(BlockRegistry.silentwood_log.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(BlockRegistry.silentwood_chest.get())
                .add(BlockRegistry.silentwood_crafting_table.get())
                .add(BlockRegistry.silentwood_fence.get())
                .add(BlockRegistry.silentwood_log.get())
                .add(BlockRegistry.silentwood_planks.get())
                .add(BlockRegistry.silentwood_slab.get())
                .add(BlockRegistry.silentwood_stairs.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlockRegistry.aurorian_coal_ore.get())
                .add(BlockRegistry.aurorian_cobblestone.get())
                .add(BlockRegistry.aurorian_cobblestone_slab.get())
                .add(BlockRegistry.aurorian_cobblestone_stairs.get())
                .add(BlockRegistry.aurorian_cobblestone_wall.get())
                .add(BlockRegistry.aurorian_deepslate.get())
                .add(BlockRegistry.aurorian_deepslate_slab.get())
                .add(BlockRegistry.aurorian_deepslate_stairs.get())
                .add(BlockRegistry.aurorian_deepslate_wall.get())
                .add(BlockRegistry.aurorian_furnace.get())
                .add(BlockRegistry.aurorian_portal_frame.get())
                .add(BlockRegistry.aurorian_stone.get())
                .add(BlockRegistry.cerulean_ore.get())
                .add(BlockRegistry.chimney.get())
                .add(BlockRegistry.deepslate_cerulean_ore.get())
                .add(BlockRegistry.deepslate_moonstone_ore.get())
                .add(BlockRegistry.geode.get())
                .add(BlockRegistry.moon_gem.get())
                .add(BlockRegistry.moonlight_forge.get())
                .add(BlockRegistry.moonstone_ore.get()).add(BlockRegistry.scrapper.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(BlockRegistry.aurorian_dirt.get())
                .add(BlockRegistry.aurorian_grass_block.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(BlockRegistry.cerulean_ore.get())
                .add(BlockRegistry.geode.get())
                .add(BlockRegistry.moonstone_ore.get());
        this.tag(BlockTags.PLANKS)
                .add(BlockRegistry.silentwood_planks.get());
        this.tag(BlockTags.REPLACEABLE_BY_TREES)
                .add(BlockRegistry.aurorian_tallgrass.get())
                .add(BlockRegistry.lavender_block.get());
        this.tag(BlockTags.SAPLINGS)
                .add(BlockRegistry.silentwood_sapling.get());
        this.tag(BlockTags.SLABS)
                .add(BlockRegistry.aurorian_cobblestone_slab.get())
                .add(BlockRegistry.aurorian_deepslate_slab.get());
        this.tag(BlockTags.STAIRS)
                .add(BlockRegistry.aurorian_cobblestone_stairs.get())
                .add(BlockRegistry.aurorian_deepslate_stairs.get())
                .add(BlockRegistry.runestone_stairs.get());
        this.tag(BlockTags.STONE_ORE_REPLACEABLES)
                .add(BlockRegistry.aurorian_stone.get());
        this.tag(BlockTags.WALLS)
                .add(BlockRegistry.aurorian_cobblestone_wall.get())
                .add(BlockRegistry.aurorian_deepslate_wall.get());
        this.tag(BlockTags.WOODEN_FENCES)
                .add(BlockRegistry.silentwood_fence.get());
        this.tag(BlockTags.WOODEN_SLABS)
                .add(BlockRegistry.silentwood_slab.get());
        this.tag(BlockTags.WOODEN_STAIRS)
                .add(BlockRegistry.silentwood_stairs.get());
        this.tag(CERULEAN_ORE)
                .add(BlockRegistry.cerulean_ore.get())
                .add(BlockRegistry.deepslate_cerulean_ore.get());
        this.tag(DUNGEON_BRICKS)
                .add(BlockRegistry.darkstone.get())
                .add(BlockRegistry.darkstone_chipped.get())
                .add(BlockRegistry.darkstone_gate.get())
                .add(BlockRegistry.darkstone_gate_keyhole.get())
                .add(BlockRegistry.darkstone_lamp.get())
                .add(BlockRegistry.darkstone_pillar.get())
                .add(BlockRegistry.darkstone_stairs.get())
                .add(BlockRegistry.moon_temple_bars.get())
                .add(BlockRegistry.moon_temple_bricks.get())
                .add(BlockRegistry.moon_temple_bricks_smooth.get())
                .add(BlockRegistry.moon_temple_gate.get())
                .add(BlockRegistry.moon_temple_gate_keyhole.get())
                .add(BlockRegistry.moon_temple_interior_gate.get())
                .add(BlockRegistry.moon_temple_interior_gate_keyhole.get())
                .add(BlockRegistry.moon_temple_lamp.get())
                .add(BlockRegistry.runestone.get())
                .add(BlockRegistry.runestone_bars.get())
                .add(BlockRegistry.runestone_gate.get())
                .add(BlockRegistry.runestone_gate_keyhole.get())
                .add(BlockRegistry.runestone_gate_loot_keyhole.get())
                .add(BlockRegistry.runestone_lamp.get())
                .add(BlockRegistry.runestone_smooth.get())
                .add(BlockRegistry.runestone_stairs.get());
        this.tag(DUNGEON_GATES)
                .add(BlockRegistry.darkstone_gate.get())
                .add(BlockRegistry.moon_temple_gate.get())
                .add(BlockRegistry.runestone_gate.get());
        this.tag(MOONSTONE_ORE)
                .add(BlockRegistry.deepslate_moonstone_ore.get())
                .add(BlockRegistry.moonstone_ore.get());
        this.tag(Tags.Blocks.CHESTS_WOODEN)
                .add(BlockRegistry.silentwood_chest.get());
        this.tag(Tags.Blocks.FENCES_WOODEN)
                .add(BlockRegistry.silentwood_fence.get());
        this.tag(Tags.Blocks.ORES)
                .add(BlockRegistry.aurorian_coal_ore.get())
                .add(BlockRegistry.geode.get())
                .addTag(CERULEAN_ORE)
                .addTag(MOONSTONE_ORE);
        this.tag(Tags.Blocks.ORES_COAL)
                .add(BlockRegistry.aurorian_coal_ore.get());
    }
}
