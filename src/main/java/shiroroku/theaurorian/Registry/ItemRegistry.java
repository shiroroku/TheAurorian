package shiroroku.theaurorian.Registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import shiroroku.theaurorian.Entities.AurorianArrow.CeruleanArrowEntity;
import shiroroku.theaurorian.Entities.AurorianArrow.CrystalArrowEntity;
import shiroroku.theaurorian.Items.AurorianSteel.*;
import shiroroku.theaurorian.Items.*;
import shiroroku.theaurorian.Items.Loot.*;
import shiroroku.theaurorian.Items.Moonstone.*;
import shiroroku.theaurorian.Items.Spectral.SpectralArmor;
import shiroroku.theaurorian.TheAurorian;

import java.awt.*;
import java.util.function.Supplier;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheAurorian.MODID); // no datagen
    public static final DeferredRegister<Item> ITEMS_GEN = DeferredRegister.create(ForgeRegistries.ITEMS, TheAurorian.MODID); // basic generated item model
    public static final DeferredRegister<Item> ITEMS_GEN_HANDHELD = DeferredRegister.create(ForgeRegistries.ITEMS, TheAurorian.MODID); // item with handheld model (tools, sticks)
    public static final DeferredRegister<Item> ITEMS_GEN_SHIELD = DeferredRegister.create(ForgeRegistries.ITEMS, TheAurorian.MODID); // adds blocking functionality and models
    public static final DeferredRegister<Item> ITEMS_SPAWN_EGGS = DeferredRegister.create(ForgeRegistries.ITEMS, TheAurorian.MODID);
    public static final DeferredRegister<Item> ITEMS_GEN_TEA = DeferredRegister.create(ForgeRegistries.ITEMS, TheAurorian.MODID); // tea

    // Ingredients
    public static final RegistryObject<Item> aurorian_steel_ingot = ITEMS_GEN.register("aurorian_steel_ingot", basicItem());
    public static final RegistryObject<Item> aurorianite_ingot = ITEMS_GEN.register("aurorianite_ingot", basicItem());
    public static final RegistryObject<Item> aurorianite_scrap = ITEMS_GEN.register("aurorianite_scrap", basicItem());
    public static final RegistryObject<Item> cerulean_ingot = ITEMS_GEN.register("cerulean_ingot", basicItem());
    public static final RegistryObject<Item> cerulean_nugget = ITEMS_GEN.register("cerulean_nugget", basicItem());
    public static final RegistryObject<Item> crystalline_ingot = ITEMS_GEN.register("crystalline_ingot", basicItem());
    public static final RegistryObject<Item> crystalline_scrap = ITEMS_GEN.register("crystalline_scrap", basicItem());
    public static final RegistryObject<Item> lavender = ITEMS_GEN.register("lavender", basicItem());
    public static final RegistryObject<Item> lockpicks = ITEMS_GEN.register("lockpicks", basicItem(defaultProp().durability(16)));
    public static final RegistryObject<Item> moonstone_ingot = ITEMS_GEN.register("moonstone_ingot", basicItem());
    public static final RegistryObject<Item> moonstone_nugget = ITEMS_GEN.register("moonstone_nugget", basicItem());
    public static final RegistryObject<Item> plant_fiber = ITEMS_GEN.register("plant_fiber", basicItem());
    public static final RegistryObject<Item> runestone_key = ITEMS_GEN.register("runestone_key", basicItem(defaultProp().durability(3).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> runestone_loot_key = ITEMS_GEN.register("runestone_loot_key", basicItem(defaultProp().durability(1).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> moon_temple_key = ITEMS_GEN.register("moon_temple_key", basicItem(defaultProp().durability(3).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> moon_temple_key_fragment = ITEMS_GEN.register("moon_temple_key_fragment", basicItem());
    public static final RegistryObject<Item> moon_temple_interior_key = ITEMS_GEN.register("moon_temple_interior_key", basicItem(defaultProp().durability(1).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> silentwood_stick = ITEMS_GEN_HANDHELD.register("silentwood_stick", basicItemWithBurntime(defaultProp(), 100));
    public static final RegistryObject<Item> umbra_ingot = ITEMS_GEN.register("umbra_ingot", basicItem());
    public static final RegistryObject<Item> umbra_scrap = ITEMS_GEN.register("umbra_scrap", basicItem());
    public static final RegistryObject<Item> spectral_silk = ITEMS_GEN.register("spectral_silk", basicItem(defaultProp().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> aurorian_coal = ITEMS_GEN.register("aurorian_coal", basicItemWithBurntime(defaultProp(), 1600));
    public static final RegistryObject<Item> aurorian_coal_nugget = ITEMS_GEN.register("aurorian_coal_nugget", basicItemWithBurntime(defaultProp(), 200));
    public static final RegistryObject<Item> cup = ITEMS_GEN.register("cup", basicItemWithBurntime(defaultProp(), 100));

    // Foodstuff
    public static final RegistryObject<Item> silkberry = ITEMS_GEN.register("silkberry", basicItem(defaultProp().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20, 0), 0.5F).build())));
    public static final RegistryObject<Item> silkberry_jam = ITEMS_GEN.register("silkberry_jam", basicItem(defaultProp().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.2F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20, 0), 1F).build())));
    public static final RegistryObject<Item> lavender_bread = ITEMS_GEN.register("lavender_bread", basicItem(defaultProp().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).fast().build())));
    public static final RegistryObject<Item> silkberry_jam_sandwich = ITEMS_GEN.register("silkberry_jam_sandwich", basicItem(defaultProp().food(new FoodProperties.Builder().nutrition(7).saturationMod(0.4F).build())));
    public static final RegistryObject<Item> strange_meat = ITEMS_GEN.register("strange_meat", () -> new StrangeMeat(defaultProp().durability(10).food(new FoodProperties.Builder().nutrition(8).saturationMod(0.9F).build())));
    public static final RegistryObject<Item> silkberry_tea = ITEMS_GEN_TEA.register("silkberry_tea", () -> new BaseAurorianTea(new Color(71, 193, 249), BaseAurorianTea.properties().food(BaseAurorianTea.foodProperties().effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600, 1), 1F).build())));
    public static final RegistryObject<Item> lavender_tea = ITEMS_GEN_TEA.register("lavender_tea", () -> new BaseAurorianTea(new Color(166, 138, 249), BaseAurorianTea.properties().food(BaseAurorianTea.foodProperties().effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 1), 1F).build())));
    public static final RegistryObject<Item> petunia_tea = ITEMS_GEN_TEA.register("petunia_tea", () -> new BaseAurorianTea(new Color(255, 200, 214), BaseAurorianTea.properties().food(BaseAurorianTea.foodProperties().effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 1), 1F).build())));
    public static final RegistryObject<Item> bright_bulb_tea = ITEMS_GEN_TEA.register("bright_bulb_tea", () -> new BaseAurorianTea(new Color(247, 213, 92), BaseAurorianTea.properties().food(BaseAurorianTea.foodProperties().effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 1), 1F).build())));

    // Tools & Armor
    public static final RegistryObject<Item> silentwood_axe = ITEMS_GEN_HANDHELD.register("silentwood_axe", () -> new BaseAurorianAxe(MaterialTiers.SILENTWOOD, 6.0F, -3.2F, defaultProp(), 200));
    public static final RegistryObject<Item> silentwood_hoe = ITEMS_GEN_HANDHELD.register("silentwood_hoe", () -> new BaseAurorianHoe(MaterialTiers.SILENTWOOD, 0, -3.0F, defaultProp(), 200));
    public static final RegistryObject<Item> silentwood_pickaxe = ITEMS_GEN_HANDHELD.register("silentwood_pickaxe", () -> new BaseAurorianPickaxe(MaterialTiers.SILENTWOOD, 1, -2.8F, defaultProp(), 200));
    public static final RegistryObject<Item> silentwood_shovel = ITEMS_GEN_HANDHELD.register("silentwood_shovel", () -> new BaseAurorianShovel(MaterialTiers.SILENTWOOD, 1.5F, -3.0F, defaultProp(), 200));
    public static final RegistryObject<Item> silentwood_sickle = ITEMS_GEN_HANDHELD.register("silentwood_sickle", () -> new BaseAurorianSickle(MaterialTiers.SILENTWOOD, defaultProp(), 200));
    public static final RegistryObject<Item> silentwood_sword = ITEMS_GEN_HANDHELD.register("silentwood_sword", () -> new BaseAurorianSword(MaterialTiers.SILENTWOOD, 3, -2.4F, defaultProp(), 200));
    public static final RegistryObject<Item> silentwood_bow = ITEMS.register("silentwood_bow", () -> new BaseAurorianBow(MaterialTiers.SILENTWOOD, defaultProp().durability(150), 200));

    public static final RegistryObject<Item> aurorian_stone_axe = ITEMS_GEN_HANDHELD.register("aurorian_stone_axe", () -> new BaseAurorianAxe(MaterialTiers.AURORIAN_STONE, 7.0F, -3.2F, defaultProp()));
    public static final RegistryObject<Item> aurorian_stone_hoe = ITEMS_GEN_HANDHELD.register("aurorian_stone_hoe", () -> new BaseAurorianHoe(MaterialTiers.AURORIAN_STONE, -1, -2.0F, defaultProp()));
    public static final RegistryObject<Item> aurorian_stone_pickaxe = ITEMS_GEN_HANDHELD.register("aurorian_stone_pickaxe", () -> new BaseAurorianPickaxe(MaterialTiers.AURORIAN_STONE, 1, -2.8F, defaultProp()));
    public static final RegistryObject<Item> aurorian_stone_shovel = ITEMS_GEN_HANDHELD.register("aurorian_stone_shovel", () -> new BaseAurorianShovel(MaterialTiers.AURORIAN_STONE, 1.5F, -3.0F, defaultProp()));
    public static final RegistryObject<Item> aurorian_stone_sickle = ITEMS_GEN_HANDHELD.register("aurorian_stone_sickle", () -> new BaseAurorianSickle(MaterialTiers.AURORIAN_STONE, defaultProp()));
    public static final RegistryObject<Item> aurorian_stone_sword = ITEMS_GEN_HANDHELD.register("aurorian_stone_sword", () -> new BaseAurorianSword(MaterialTiers.AURORIAN_STONE, 3, -2.4F, defaultProp()));

    public static final RegistryObject<Item> moonstone_axe = ITEMS_GEN_HANDHELD.register("moonstone_axe", () -> new MoonstoneAxe(MaterialTiers.MOONSTONE, 6.0F, -3.1F, defaultProp()));
    public static final RegistryObject<Item> moonstone_hoe = ITEMS_GEN_HANDHELD.register("moonstone_hoe", () -> new MoonstoneHoe(MaterialTiers.MOONSTONE, -2, -1.0F, defaultProp()));
    public static final RegistryObject<Item> moonstone_pickaxe = ITEMS_GEN_HANDHELD.register("moonstone_pickaxe", () -> new MoonstonePickaxe(MaterialTiers.MOONSTONE, 1, -2.8F, defaultProp()));
    public static final RegistryObject<Item> moonstone_shield = ITEMS_GEN_SHIELD.register("moonstone_shield", () -> new MoonstoneShield(MaterialTiers.MOONSTONE, defaultProp()));
    public static final RegistryObject<Item> moonstone_shovel = ITEMS_GEN_HANDHELD.register("moonstone_shovel", () -> new MoonstoneShovel(MaterialTiers.MOONSTONE, 1.5F, -3.0F, defaultProp()));
    public static final RegistryObject<Item> moonstone_sickle = ITEMS_GEN_HANDHELD.register("moonstone_sickle", () -> new MoonstoneSickle(MaterialTiers.MOONSTONE, defaultProp()));
    public static final RegistryObject<Item> moonstone_sword = ITEMS_GEN_HANDHELD.register("moonstone_sword", () -> new MoonstoneSword(MaterialTiers.MOONSTONE, 3, -2.4F, defaultProp()));

    public static final RegistryObject<Item> aurorian_steel_boots = ITEMS_GEN.register("aurorian_steel_boots", () -> new AurorianSteelArmor(MaterialTiers.AURORIAN_STEEL_ARMOR, EquipmentSlot.FEET, defaultProp().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> aurorian_steel_chestplate = ITEMS_GEN.register("aurorian_steel_chestplate", () -> new AurorianSteelArmor(MaterialTiers.AURORIAN_STEEL_ARMOR, EquipmentSlot.CHEST, defaultProp().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> aurorian_steel_helmet = ITEMS_GEN.register("aurorian_steel_helmet", () -> new AurorianSteelArmor(MaterialTiers.AURORIAN_STEEL_ARMOR, EquipmentSlot.HEAD, defaultProp().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> aurorian_steel_leggings = ITEMS_GEN.register("aurorian_steel_leggings", () -> new AurorianSteelArmor(MaterialTiers.AURORIAN_STEEL_ARMOR, EquipmentSlot.LEGS, defaultProp().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> aurorian_steel_axe = ITEMS_GEN_HANDHELD.register("aurorian_steel_axe", () -> new AurorianSteelAxe(MaterialTiers.AURORIAN_STEEL, 5.0F, -3.0F, defaultProp().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> aurorian_steel_hoe = ITEMS_GEN_HANDHELD.register("aurorian_steel_hoe", () -> new AurorianSteelHoe(MaterialTiers.AURORIAN_STEEL, -3, 0.0F, defaultProp().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> aurorian_steel_pickaxe = ITEMS_GEN_HANDHELD.register("aurorian_steel_pickaxe", () -> new AurorianSteelPickaxe(MaterialTiers.AURORIAN_STEEL, 1, -2.8F, defaultProp().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> aurorian_steel_shovel = ITEMS_GEN_HANDHELD.register("aurorian_steel_shovel", () -> new AurorianSteelShovel(MaterialTiers.AURORIAN_STEEL, 1.5F, -3.0F, defaultProp().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> aurorian_steel_sword = ITEMS_GEN_HANDHELD.register("aurorian_steel_sword", () -> new AurorianSteelSword(MaterialTiers.AURORIAN_STEEL, 3, -2.4F, defaultProp().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> aurorianite_axe = ITEMS_GEN_HANDHELD.register("aurorianite_axe", () -> new AurorianiteAxe(MaterialTiers.AURORIANITE, 5.0F, -3.0F, defaultProp().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> aurorianite_pickaxe = ITEMS_GEN_HANDHELD.register("aurorianite_pickaxe", () -> new AurorianitePickaxe(MaterialTiers.AURORIANITE, 1, -2.8F, defaultProp().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> aurorianite_sword = ITEMS_GEN_HANDHELD.register("aurorianite_sword", () -> new AurorianiteSword(MaterialTiers.AURORIANITE, 3, -2.4F, defaultProp().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> crystalline_shield = ITEMS_GEN_SHIELD.register("crystalline_shield", () -> new CrystallineShield(MaterialTiers.CRYSTALLINE, defaultProp().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> crystalline_pickaxe = ITEMS_GEN_HANDHELD.register("crystalline_pickaxe", () -> new CrystallinePickaxe(MaterialTiers.CRYSTALLINE, 1, -2.8F, defaultProp().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> crystalline_sword = ITEMS.register("crystalline_sword", () -> new CrystallineSword(MaterialTiers.CRYSTALLINE, 3, -2.4F, defaultProp().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> umbra_greatsword = ITEMS.register("umbra_greatsword", () -> new UmbraGreatsword(MaterialTiers.UMBRA, 7, -3.75F, defaultProp().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> umbra_pickaxe = ITEMS_GEN_HANDHELD.register("umbra_pickaxe", () -> new UmbraPickaxe(MaterialTiers.UMBRA, 1, -2.8F, defaultProp().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> umbra_shield = ITEMS_GEN_SHIELD.register("umbra_shield", () -> new UmbraShield(MaterialTiers.UMBRA, defaultProp().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> umbra_chestplate = ITEMS_GEN.register("umbra_chestplate", () -> new UmbraChestplate(MaterialTiers.UMBRA_ARMOR, defaultProp().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> cerulean_boots = ITEMS_GEN.register("cerulean_boots", () -> new BaseAurorianArmor(MaterialTiers.CERULEAN_ARMOR, EquipmentSlot.FEET, defaultProp()));
    public static final RegistryObject<Item> cerulean_chestplate = ITEMS_GEN.register("cerulean_chestplate", () -> new BaseAurorianArmor(MaterialTiers.CERULEAN_ARMOR, EquipmentSlot.CHEST, defaultProp()));
    public static final RegistryObject<Item> cerulean_helmet = ITEMS_GEN.register("cerulean_helmet", () -> new BaseAurorianArmor(MaterialTiers.CERULEAN_ARMOR, EquipmentSlot.HEAD, defaultProp()));
    public static final RegistryObject<Item> cerulean_leggings = ITEMS_GEN.register("cerulean_leggings", () -> new BaseAurorianArmor(MaterialTiers.CERULEAN_ARMOR, EquipmentSlot.LEGS, defaultProp()));
    public static final RegistryObject<Item> cerulean_shield = ITEMS_GEN_SHIELD.register("cerulean_shield", () -> new BaseAurorianShield(MaterialTiers.CERULEAN, defaultProp()));

    public static final RegistryObject<Item> spectral_boots = ITEMS_GEN.register("spectral_boots", () -> new SpectralArmor(MaterialTiers.SPECTRAL_ARMOR, EquipmentSlot.FEET, defaultProp().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> spectral_chestplate = ITEMS_GEN.register("spectral_chestplate", () -> new SpectralArmor(MaterialTiers.SPECTRAL_ARMOR, EquipmentSlot.CHEST, defaultProp().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> spectral_helmet = ITEMS_GEN.register("spectral_helmet", () -> new SpectralArmor(MaterialTiers.SPECTRAL_ARMOR, EquipmentSlot.HEAD, defaultProp().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> spectral_leggings = ITEMS_GEN.register("spectral_leggings", () -> new SpectralArmor(MaterialTiers.SPECTRAL_ARMOR, EquipmentSlot.LEGS, defaultProp().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> knight_boots = ITEMS_GEN.register("knight_boots", () -> new BaseAurorianArmor(MaterialTiers.KNIGHT_ARMOR, EquipmentSlot.FEET, defaultProp().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> knight_chestplate = ITEMS_GEN.register("knight_chestplate", () -> new BaseAurorianArmor(MaterialTiers.KNIGHT_ARMOR, EquipmentSlot.CHEST, defaultProp().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> knight_helmet = ITEMS_GEN.register("knight_helmet", () -> new BaseAurorianArmor(MaterialTiers.KNIGHT_ARMOR, EquipmentSlot.HEAD, defaultProp().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> knight_leggings = ITEMS_GEN.register("knight_leggings", () -> new BaseAurorianArmor(MaterialTiers.KNIGHT_ARMOR, EquipmentSlot.LEGS, defaultProp().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> absorption_orb = ITEMS_GEN.register("absorption_orb", () -> new AbsorptionOrb(defaultProp().durability(250).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> living_divining_rod = ITEMS_GEN_HANDHELD.register("living_divining_rod", () -> new LivingDiviningRod(defaultProp().durability(100).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> spawn_egg_hollow = ITEMS_SPAWN_EGGS.register("spawn_egg_hollow", () -> new ForgeSpawnEggItem(EntityRegistry.hollow, 8117755, 3363951, defaultProp()));
    public static final RegistryObject<Item> spawn_egg_undead_knight = ITEMS_SPAWN_EGGS.register("spawn_egg_undead_knight", () -> new ForgeSpawnEggItem(EntityRegistry.undead_knight, 8117755, 3363951, defaultProp()));
    public static final RegistryObject<Item> spawn_egg_dungeon_slime = ITEMS_SPAWN_EGGS.register("spawn_egg_dungeon_slime", () -> new ForgeSpawnEggItem(EntityRegistry.dungeon_slime, 8117755, 3363951, defaultProp()));

    public static final RegistryObject<Item> cerulean_arrow = ITEMS_GEN.register("cerulean_arrow", () -> new BaseAurorianArrow(defaultProp()) {
        @Override
        public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
            return new CeruleanArrowEntity(pLevel, pShooter);
        }
    });
    public static final RegistryObject<Item> crystal_arrow = ITEMS_GEN.register("crystal_arrow", () -> new BaseAurorianArrow(defaultProp()) {
        @Override
        public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
            return new CrystalArrowEntity(pLevel, pShooter);
        }
    });

    public static Item.Properties defaultProp() {
        return new Item.Properties().tab(TheAurorian.CREATIVETAB);
    }

    /**
     * Items made here will have a aurorian colored durability bar and if they have a ".desc" in lang, then it adds it to tooltip
     */
    private static Supplier<Item> basicItem() {
        return basicItem(defaultProp());
    }

    private static Supplier<Item> basicItem(Item.Properties properties) {
        return basicItemWithBurntime(properties, 0);
    }

    private static Supplier<Item> basicItemWithBurntime(Item.Properties properties, int burnTime) {
        return () -> new BaseAurorianItem(properties.tab(TheAurorian.CREATIVETAB)) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return burnTime == 0 ? super.getBurnTime(itemStack, recipeType) : burnTime;
            }
        };
    }

    public static void register(IEventBus bus) {
        ItemRegistry.ITEMS.register(bus);
        ItemRegistry.ITEMS_GEN.register(bus);
        ItemRegistry.ITEMS_GEN_HANDHELD.register(bus);
        ItemRegistry.ITEMS_GEN_SHIELD.register(bus);
        ItemRegistry.ITEMS_SPAWN_EGGS.register(bus);
        ItemRegistry.ITEMS_GEN_TEA.register(bus);
    }
}
