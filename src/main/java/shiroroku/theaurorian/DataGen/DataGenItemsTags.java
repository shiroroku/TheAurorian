package shiroroku.theaurorian.DataGen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.Registry.ItemRegistry;
import shiroroku.theaurorian.TheAurorian;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class DataGenItemsTags extends ItemTagsProvider {

    public static final TagKey<Item> ABSORPTION_ORB_REPAIRABLE = ItemTags.create(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "absorption_orb_repairable"));
    public static final TagKey<Item> AURORIAN_STONES = ItemTags.create(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "aurorian_stones"));
    public static final TagKey<Item> CERULEAN_ORE = ItemTags.create(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "cerulean_ore"));
    public static final TagKey<Item> CRYSTALLINE_PICKAXE_TREASURE = ItemTags.create(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "crystalline_treasure"));
    public static final TagKey<Item> CRYSTALLINE_SHIELD_REPAIRABLE = ItemTags.create(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "crystalline_shield_repairable"));
    public static final TagKey<Item> KEYS = ItemTags.create(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "keys"));
    public static final TagKey<Item> LIGHTNING_IMMUNE = ItemTags.create(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "lightning_immune"));
    public static final TagKey<Item> MOONSTONE_ORE = ItemTags.create(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "moonstone_ore"));
    public static final TagKey<Item> PORTAL_LIGHTERS = ItemTags.create(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "portal_lighters"));
    public static final TagKey<Item> SCRAP = ItemTags.create(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "scrap"));
    public static final TagKey<Item> SPECTRAL_ARMOR = ItemTags.create(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "spectral_armor"));
    public static final TagKey<Item> TEA = ItemTags.create(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "tea"));

    @SuppressWarnings("deprecation")
    protected DataGenItemsTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> blockTags, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, TheAurorian.MODID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        ItemRegistry.ITEMS_GEN_SHIELD.getEntries().stream().map(Supplier::get).forEach(shield -> this.tag(Tags.Items.TOOLS_SHIELD).add(shield));
        ItemRegistry.ITEMS_GEN_KEY.getEntries().stream().map(Supplier::get).forEach(key -> this.tag(KEYS).add(key));
        this.tag(ABSORPTION_ORB_REPAIRABLE).addTags(Tags.Items.TOOLS, Tags.Items.ARMORS, Tags.Items.TOOLS_SHEAR);
        this.tag(AURORIAN_STONES)
                .add(BlockRegistry.aurorian_cobblestone.get().asItem())
                .add(BlockRegistry.aurorian_deepslate.get().asItem());
        this.tag(CERULEAN_ORE)
                .add(BlockRegistry.cerulean_ore.get().asItem())
                .add(BlockRegistry.deepslate_cerulean_ore.get().asItem());
        this.tag(CRYSTALLINE_PICKAXE_TREASURE)
                .add(BlockRegistry.cerulean_ore.get().asItem())
                .add(BlockRegistry.crystal.get().asItem())
                .add(BlockRegistry.moonstone_ore.get().asItem())
                .add(Items.AMETHYST_SHARD)
                .add(Items.FLINT)
                .add(Items.RAW_GOLD)
                .add(Items.RAW_IRON);
        this.tag(CRYSTALLINE_SHIELD_REPAIRABLE).addTags(Tags.Items.TOOLS, Tags.Items.ARMORS, Tags.Items.TOOLS_SHEAR);
        this.tag(ItemTags.ARROWS).add(ItemRegistry.cerulean_arrow.get(), ItemRegistry.crystal_arrow.get());
        this.tag(ItemTags.COALS).add(ItemRegistry.aurorian_coal.get());
        this.tag(LIGHTNING_IMMUNE).add(Items.LEATHER_BOOTS, Items.LEATHER_CHESTPLATE, Items.LEATHER_HELMET, Items.LEATHER_LEGGINGS);
        this.tag(MOONSTONE_ORE)
                .add(BlockRegistry.deepslate_moonstone_ore.get().asItem())
                .add(BlockRegistry.moonstone_ore.get().asItem());
        this.tag(PORTAL_LIGHTERS).add(Items.FLINT_AND_STEEL);
        this.tag(SCRAP)
                .add(ItemRegistry.aurorianite_scrap.get())
                .add(ItemRegistry.crystalline_scrap.get())
                .add(ItemRegistry.umbra_scrap.get());
        this.tag(SPECTRAL_ARMOR).add(ItemRegistry.spectral_helmet.get(), ItemRegistry.spectral_chestplate.get(), ItemRegistry.spectral_leggings.get(), ItemRegistry.spectral_boots.get());
        this.tag(TEA).add(ItemRegistry.bright_bulb_tea.get(), ItemRegistry.lavender_tea.get(), ItemRegistry.petunia_tea.get(), ItemRegistry.silkberry_tea.get());
        this.tag(ItemTags.FOOT_ARMOR).add(ItemRegistry.cerulean_boots.get(), ItemRegistry.spectral_boots.get(), ItemRegistry.aurorian_steel_boots.get());
        this.tag(ItemTags.CHEST_ARMOR).add(ItemRegistry.cerulean_chestplate.get(), ItemRegistry.spectral_chestplate.get(), ItemRegistry.umbra_chestplate.get(), ItemRegistry.aurorian_steel_chestplate.get());
        this.tag(ItemTags.HEAD_ARMOR).add(ItemRegistry.cerulean_helmet.get(), ItemRegistry.spectral_helmet.get(), ItemRegistry.spectral_helmet.get());
        this.tag(ItemTags.LEG_ARMOR).add(ItemRegistry.cerulean_leggings.get(), ItemRegistry.spectral_leggings.get(), ItemRegistry.aurorian_steel_leggings.get());
        this.tag(Tags.Items.CHESTS_WOODEN).add(BlockRegistry.silentwood_chest.get().asItem());
        this.tag(Tags.Items.GEMS).add(BlockRegistry.crystal.get().asItem());
        this.tag(Tags.Items.INGOTS)
                .add(ItemRegistry.aurorian_steel_ingot.get())
                .add(ItemRegistry.aurorianite_ingot.get())
                .add(ItemRegistry.cerulean_ingot.get())
                .add(ItemRegistry.crystalline_ingot.get())
                .add(ItemRegistry.moonstone_ingot.get())
                .add(ItemRegistry.umbra_ingot.get());
        this.tag(Tags.Items.NUGGETS)
                .add(ItemRegistry.aurorian_coal_nugget.get())
                .add(ItemRegistry.cerulean_nugget.get())
                .add(ItemRegistry.moonstone_nugget.get())
                .add(ItemRegistry.aurorian_steel_nugget.get());
        this.tag(Tags.Items.ORES)
                .add(BlockRegistry.geode.get().asItem(), BlockRegistry.aurorian_coal_ore.get().asItem())
                .addTags(CERULEAN_ORE, MOONSTONE_ORE);
        this.tag(Tags.Items.RODS_WOODEN)
                .add(ItemRegistry.silentwood_stick.get());
        this.tag(Tags.Items.TOOLS_SHEAR)
                .add(ItemRegistry.aurorian_stone_sickle.get())
                .add(ItemRegistry.moonstone_sickle.get())
                .add(ItemRegistry.silentwood_sickle.get())
                .add(ItemRegistry.plant_fiber.get());
        this.tag(ItemTags.AXES)
                .add(ItemRegistry.aurorian_steel_axe.get())
                .add(ItemRegistry.aurorian_stone_axe.get())
                .add(ItemRegistry.aurorianite_axe.get())
                .add(ItemRegistry.moonstone_axe.get())
                .add(ItemRegistry.silentwood_axe.get());
        this.tag(Tags.Items.TOOLS_BOW).add(ItemRegistry.silentwood_bow.get());
        this.tag(ItemTags.HOES)
                .add(ItemRegistry.aurorian_steel_hoe.get())
                .add(ItemRegistry.aurorian_stone_hoe.get())
                .add(ItemRegistry.moonstone_hoe.get())
                .add(ItemRegistry.silentwood_hoe.get());
        this.tag(ItemTags.PICKAXES)
                .add(ItemRegistry.aurorian_steel_pickaxe.get())
                .add(ItemRegistry.aurorian_stone_pickaxe.get())
                .add(ItemRegistry.aurorianite_pickaxe.get())
                .add(ItemRegistry.crystalline_pickaxe.get())
                .add(ItemRegistry.moonstone_pickaxe.get())
                .add(ItemRegistry.silentwood_pickaxe.get())
                .add(ItemRegistry.umbra_pickaxe.get());
        this.tag(ItemTags.SHOVELS)
                .add(ItemRegistry.aurorian_steel_shovel.get())
                .add(ItemRegistry.aurorian_stone_shovel.get())
                .add(ItemRegistry.moonstone_shovel.get())
                .add(ItemRegistry.silentwood_shovel.get())
                .add(ItemRegistry.aurorianite_shovel.get());
        this.tag(ItemTags.SWORDS)
                .add(ItemRegistry.aurorian_steel_sword.get())
                .add(ItemRegistry.aurorian_stone_sword.get())
                .add(ItemRegistry.aurorianite_sword.get())
                .add(ItemRegistry.crystalline_sword.get())
                .add(ItemRegistry.moonstone_sword.get())
                .add(ItemRegistry.silentwood_sword.get())
                .add(ItemRegistry.umbra_greatsword.get());
    }
}
