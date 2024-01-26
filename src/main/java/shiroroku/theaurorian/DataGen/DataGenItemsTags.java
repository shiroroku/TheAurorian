package shiroroku.theaurorian.DataGen;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.Registry.ItemRegistry;
import shiroroku.theaurorian.TheAurorian;

import java.util.function.Supplier;

public class DataGenItemsTags extends TagsProvider<Item> {

    public static final TagKey<Item> TEA = ItemTags.create(new ResourceLocation(TheAurorian.MODID, "tea"));
    public static final TagKey<Item> KEYS = ItemTags.create(new ResourceLocation(TheAurorian.MODID, "keys"));
    public static final TagKey<Item> SCRAP = ItemTags.create(new ResourceLocation(TheAurorian.MODID, "scrap"));
    public static final TagKey<Item> ABSORPTION_ORB_REPAIRABLE = ItemTags.create(new ResourceLocation(TheAurorian.MODID, "absorption_orb_repairable"));
    public static final TagKey<Item> CRYSTALLINE_SHIELD_REPAIRABLE = ItemTags.create(new ResourceLocation(TheAurorian.MODID, "crystalline_shield_repairable"));
    public static final TagKey<Item> SPECTRAL_ARMOR = ItemTags.create(new ResourceLocation(TheAurorian.MODID, "spectral_armor"));
    public static final TagKey<Item> AURORIAN_STONES = ItemTags.create(new ResourceLocation(TheAurorian.MODID, "aurorian_stones"));
    public static final TagKey<Item> CRYSTALLINE_PICKAXE_TREASURE = ItemTags.create(new ResourceLocation(TheAurorian.MODID, "crystalline_treasure"));
    public static final TagKey<Item> MOONSTONE_ORE = ItemTags.create(new ResourceLocation(TheAurorian.MODID, "moonstone_ore"));
    public static final TagKey<Item> CERULEAN_ORE = ItemTags.create(new ResourceLocation(TheAurorian.MODID, "cerulean_ore"));

    @SuppressWarnings("deprecation")
    protected DataGenItemsTags(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, Registry.ITEM, TheAurorian.MODID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags() {
        ItemRegistry.ITEMS_GEN_SHIELD.getEntries().stream().map(Supplier::get).forEach((shield) -> this.tag(Tags.Items.TOOLS_SHIELDS).add(shield));

        this.tag(ABSORPTION_ORB_REPAIRABLE).addTags(Tags.Items.TOOLS, Tags.Items.ARMORS, Tags.Items.SHEARS);
        this.tag(AURORIAN_STONES).add(BlockRegistry.aurorian_cobblestone.get().asItem());
        this.tag(AURORIAN_STONES).add(BlockRegistry.aurorian_deepslate.get().asItem());
        this.tag(CRYSTALLINE_PICKAXE_TREASURE).add(BlockRegistry.cerulean_ore.get().asItem());
        this.tag(CRYSTALLINE_PICKAXE_TREASURE).add(BlockRegistry.crystal.get().asItem());
        this.tag(CRYSTALLINE_PICKAXE_TREASURE).add(BlockRegistry.moonstone_ore.get().asItem());
        this.tag(CRYSTALLINE_PICKAXE_TREASURE).add(Items.AMETHYST_SHARD);
        this.tag(CRYSTALLINE_PICKAXE_TREASURE).add(Items.FLINT);
        this.tag(CRYSTALLINE_PICKAXE_TREASURE).add(Items.RAW_GOLD);
        this.tag(CRYSTALLINE_PICKAXE_TREASURE).add(Items.RAW_IRON);
        this.tag(CRYSTALLINE_SHIELD_REPAIRABLE).addTags(Tags.Items.TOOLS, Tags.Items.ARMORS, Tags.Items.SHEARS);
        this.tag(KEYS).add(ItemRegistry.runestone_key.get());
        this.tag(KEYS).add(ItemRegistry.runestone_loot_key.get());
        this.tag(KEYS).add(ItemRegistry.moon_temple_key.get());
        this.tag(KEYS).add(ItemRegistry.moon_temple_interior_key.get());
        this.tag(SCRAP).add(ItemRegistry.aurorianite_scrap.get());
        this.tag(SCRAP).add(ItemRegistry.crystalline_scrap.get());
        this.tag(SCRAP).add(ItemRegistry.umbra_scrap.get());
        this.tag(SPECTRAL_ARMOR).add(ItemRegistry.spectral_helmet.get(), ItemRegistry.spectral_chestplate.get(), ItemRegistry.spectral_leggings.get(), ItemRegistry.spectral_boots.get());
        this.tag(Tags.Items.ARMORS_BOOTS).add(ItemRegistry.cerulean_boots.get(), ItemRegistry.spectral_boots.get(), ItemRegistry.aurorian_steel_boots.get());
        this.tag(Tags.Items.ARMORS_CHESTPLATES).add(ItemRegistry.cerulean_chestplate.get(), ItemRegistry.spectral_chestplate.get(), ItemRegistry.umbra_chestplate.get(), ItemRegistry.aurorian_steel_chestplate.get());
        this.tag(Tags.Items.ARMORS_HELMETS).add(ItemRegistry.cerulean_helmet.get(), ItemRegistry.spectral_helmet.get(), ItemRegistry.spectral_helmet.get());
        this.tag(Tags.Items.ARMORS_LEGGINGS).add(ItemRegistry.cerulean_leggings.get(), ItemRegistry.spectral_leggings.get(), ItemRegistry.aurorian_steel_leggings.get());
        this.tag(Tags.Items.INGOTS).add(ItemRegistry.aurorian_steel_ingot.get());
        this.tag(Tags.Items.INGOTS).add(ItemRegistry.aurorianite_ingot.get());
        this.tag(Tags.Items.INGOTS).add(ItemRegistry.cerulean_ingot.get());
        this.tag(Tags.Items.INGOTS).add(ItemRegistry.crystalline_ingot.get());
        this.tag(Tags.Items.INGOTS).add(ItemRegistry.moonstone_ingot.get());
        this.tag(Tags.Items.INGOTS).add(ItemRegistry.umbra_ingot.get());
        this.tag(Tags.Items.NUGGETS).add(ItemRegistry.cerulean_nugget.get());
        this.tag(Tags.Items.NUGGETS).add(ItemRegistry.moonstone_nugget.get());
        this.tag(Tags.Items.RODS_WOODEN).add(ItemRegistry.silentwood_stick.get());
        this.tag(Tags.Items.SHEARS).add(ItemRegistry.aurorian_stone_sickle.get());
        this.tag(Tags.Items.SHEARS).add(ItemRegistry.moonstone_sickle.get());
        this.tag(Tags.Items.SHEARS).add(ItemRegistry.silentwood_sickle.get());
        this.tag(Tags.Items.STRING).add(ItemRegistry.plant_fiber.get());
        this.tag(Tags.Items.TOOLS_AXES).add(ItemRegistry.aurorian_steel_axe.get());
        this.tag(Tags.Items.TOOLS_AXES).add(ItemRegistry.aurorian_stone_axe.get());
        this.tag(Tags.Items.TOOLS_AXES).add(ItemRegistry.aurorianite_axe.get());
        this.tag(Tags.Items.TOOLS_AXES).add(ItemRegistry.moonstone_axe.get());
        this.tag(Tags.Items.TOOLS_AXES).add(ItemRegistry.silentwood_axe.get());
        this.tag(Tags.Items.TOOLS_HOES).add(ItemRegistry.aurorian_steel_hoe.get());
        this.tag(Tags.Items.TOOLS_HOES).add(ItemRegistry.aurorian_stone_hoe.get());
        this.tag(Tags.Items.TOOLS_HOES).add(ItemRegistry.moonstone_hoe.get());
        this.tag(Tags.Items.TOOLS_HOES).add(ItemRegistry.silentwood_hoe.get());
        this.tag(Tags.Items.TOOLS_PICKAXES).add(ItemRegistry.aurorian_steel_pickaxe.get());
        this.tag(Tags.Items.TOOLS_PICKAXES).add(ItemRegistry.aurorian_stone_pickaxe.get());
        this.tag(Tags.Items.TOOLS_PICKAXES).add(ItemRegistry.aurorianite_pickaxe.get());
        this.tag(Tags.Items.TOOLS_PICKAXES).add(ItemRegistry.crystalline_pickaxe.get());
        this.tag(Tags.Items.TOOLS_PICKAXES).add(ItemRegistry.moonstone_pickaxe.get());
        this.tag(Tags.Items.TOOLS_PICKAXES).add(ItemRegistry.umbra_pickaxe.get());
        this.tag(Tags.Items.TOOLS_PICKAXES).add(ItemRegistry.silentwood_pickaxe.get());
        this.tag(Tags.Items.TOOLS_SHOVELS).add(ItemRegistry.aurorian_steel_shovel.get());
        this.tag(Tags.Items.TOOLS_SHOVELS).add(ItemRegistry.aurorian_stone_shovel.get());
        this.tag(Tags.Items.TOOLS_SHOVELS).add(ItemRegistry.moonstone_shovel.get());
        this.tag(Tags.Items.TOOLS_SHOVELS).add(ItemRegistry.silentwood_shovel.get());
        this.tag(Tags.Items.TOOLS_SWORDS).add(ItemRegistry.aurorian_steel_sword.get());
        this.tag(Tags.Items.TOOLS_SWORDS).add(ItemRegistry.aurorian_stone_sword.get());
        this.tag(Tags.Items.TOOLS_SWORDS).add(ItemRegistry.aurorianite_sword.get());
        this.tag(Tags.Items.TOOLS_SWORDS).add(ItemRegistry.moonstone_sword.get());
        this.tag(Tags.Items.TOOLS_SWORDS).add(ItemRegistry.crystalline_sword.get());
        this.tag(Tags.Items.TOOLS_SWORDS).add(ItemRegistry.umbra_greatsword.get());
        this.tag(Tags.Items.TOOLS_SWORDS).add(ItemRegistry.silentwood_sword.get());
        this.tag(Tags.Items.TOOLS_BOWS).add(ItemRegistry.silentwood_bow.get());
        this.tag(CERULEAN_ORE).add(BlockRegistry.cerulean_ore.get().asItem());
        this.tag(CERULEAN_ORE).add(BlockRegistry.deepslate_cerulean_ore.get().asItem());
        this.tag(MOONSTONE_ORE).add(BlockRegistry.moonstone_ore.get().asItem());
        this.tag(MOONSTONE_ORE).add(BlockRegistry.deepslate_moonstone_ore.get().asItem());
        this.tag(Tags.Items.ORES).addTags(CERULEAN_ORE, MOONSTONE_ORE);
        this.tag(Tags.Items.ORES).add(BlockRegistry.geode.get().asItem());
        this.tag(ItemTags.ARROWS).add(ItemRegistry.cerulean_arrow.get(), ItemRegistry.crystal_arrow.get());
        this.tag(TEA).add(ItemRegistry.bright_bulb_tea.get(), ItemRegistry.lavender_tea.get(), ItemRegistry.petunia_tea.get(), ItemRegistry.silkberry_tea.get());
    }
}
