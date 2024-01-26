package shiroroku.theaurorian.DataGen;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.TheAurorian;

public class DataGenBlocksTags extends TagsProvider<Block> {

    public static final TagKey<Block> DUNGEON_BRICKS = BlockTags.create(new ResourceLocation(TheAurorian.MODID, "dungeon_bricks"));
    public static final TagKey<Block> DUNGEON_GATES = BlockTags.create(new ResourceLocation(TheAurorian.MODID, "dungeon_gates"));
    public static final TagKey<Block> MOONSTONE_ORE = BlockTags.create(new ResourceLocation(TheAurorian.MODID, "moonstone_ore"));
    public static final TagKey<Block> CERULEAN_ORE = BlockTags.create(new ResourceLocation(TheAurorian.MODID, "cerulean_ore"));

    @SuppressWarnings("deprecation")
    protected DataGenBlocksTags(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, Registry.BLOCK, TheAurorian.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(BlockTags.DEEPSLATE_ORE_REPLACEABLES).add(BlockRegistry.aurorian_deepslate.get());
        this.tag(BlockTags.DIRT).add(BlockRegistry.aurorian_dirt.get());
        this.tag(BlockTags.DIRT).add(BlockRegistry.aurorian_grass.get());
        this.tag(BlockTags.FLOWERS).add(BlockRegistry.petunia.get());
        this.tag(BlockTags.FLOWERS).add(BlockRegistry.bright_bulb.get());
        this.tag(BlockTags.LEAVES).add(BlockRegistry.silentwood_leaves.get());
        this.tag(BlockTags.LOGS_THAT_BURN).add(BlockRegistry.silentwood_log.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(BlockRegistry.silentwood_crafting_table.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(BlockRegistry.silentwood_log.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(BlockRegistry.silentwood_planks.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BlockRegistry.aurorian_cobblestone.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BlockRegistry.aurorian_deepslate.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BlockRegistry.aurorian_furnace.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BlockRegistry.aurorian_stone.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BlockRegistry.cerulean_ore.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BlockRegistry.geode.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BlockRegistry.moon_gem.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BlockRegistry.moonlight_forge.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BlockRegistry.moonstone_ore.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BlockRegistry.scrapper.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(BlockRegistry.aurorian_dirt.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(BlockRegistry.aurorian_grass.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(BlockRegistry.cerulean_ore.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(BlockRegistry.geode.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(BlockRegistry.moonstone_ore.get());
        this.tag(BlockTags.PLANKS).add(BlockRegistry.silentwood_planks.get());
        this.tag(BlockTags.REPLACEABLE_PLANTS).add(BlockRegistry.aurorian_tallgrass.get());
        this.tag(BlockTags.REPLACEABLE_PLANTS).add(BlockRegistry.lavender_block.get());
        this.tag(BlockTags.SAPLINGS).add(BlockRegistry.silentwood_sapling.get());
        this.tag(BlockTags.STAIRS).add(BlockRegistry.runestone_stairs.get());
        this.tag(BlockTags.STONE_ORE_REPLACEABLES).add(BlockRegistry.aurorian_stone.get());
        this.tag(DUNGEON_BRICKS).add(BlockRegistry.runestone.get());
        this.tag(DUNGEON_BRICKS).add(BlockRegistry.runestone_bars.get());
        this.tag(DUNGEON_BRICKS).add(BlockRegistry.runestone_gate.get());
        this.tag(DUNGEON_BRICKS).add(BlockRegistry.runestone_gate_keyhole.get());
        this.tag(DUNGEON_BRICKS).add(BlockRegistry.runestone_gate_loot_keyhole.get());
        this.tag(DUNGEON_BRICKS).add(BlockRegistry.runestone_lamp.get());
        this.tag(DUNGEON_BRICKS).add(BlockRegistry.runestone_smooth.get());
        this.tag(DUNGEON_BRICKS).add(BlockRegistry.runestone_stairs.get());
        this.tag(DUNGEON_GATES).add(BlockRegistry.runestone_gate.get());
        this.tag(Tags.Blocks.ORES).add(BlockRegistry.geode.get());
        this.tag(CERULEAN_ORE).add(BlockRegistry.cerulean_ore.get());
        this.tag(MOONSTONE_ORE).add(BlockRegistry.moonstone_ore.get());
        this.tag(CERULEAN_ORE).add(BlockRegistry.deepslate_cerulean_ore.get());
        this.tag(MOONSTONE_ORE).add(BlockRegistry.deepslate_moonstone_ore.get());
        this.tag(Tags.Blocks.ORES).addTag(MOONSTONE_ORE);
        this.tag(Tags.Blocks.ORES).addTag(CERULEAN_ORE);
    }
}
