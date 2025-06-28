package shiroroku.theaurorian.Registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;
import shiroroku.theaurorian.Entities.AurorianArrow.CeruleanArrowEntity;
import shiroroku.theaurorian.Entities.AurorianArrow.CrystalArrowEntity;
import shiroroku.theaurorian.Items.AurorianSteel.*;
import shiroroku.theaurorian.Items.*;
import shiroroku.theaurorian.Items.Loot.*;
import shiroroku.theaurorian.Items.MirrorOfGuidance.MirrorOGItem;
import shiroroku.theaurorian.Items.Moonstone.*;
import shiroroku.theaurorian.Items.Spectral.SpectralArmor;
import shiroroku.theaurorian.TheAurorian;

import java.awt.*;
import java.util.Arrays;
import java.util.function.Supplier;

public class ItemRegistry {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TheAurorian.MODID); // no datagen
    public static final DeferredRegister.Items ITEMS_GEN = DeferredRegister.createItems(TheAurorian.MODID); // basic generated item model
    public static final DeferredRegister.Items ITEMS_GEN_HANDHELD = DeferredRegister.createItems(TheAurorian.MODID); // item with handheld model (tools, sticks)
    public static final DeferredRegister.Items ITEMS_GEN_SHIELD = DeferredRegister.createItems(TheAurorian.MODID); // adds blocking functionality and models
    public static final DeferredRegister.Items ITEMS_SPAWN_EGGS = DeferredRegister.createItems(TheAurorian.MODID);
    public static final DeferredRegister.Items ITEMS_GEN_KEY = DeferredRegister.createItems(TheAurorian.MODID);
    public static final DeferredRegister.Items ITEMS_GEN_TEA = DeferredRegister.createItems(TheAurorian.MODID); // tea

    // Ingredients
    public static final DeferredItem<Item> aurorian_coal = ITEMS_GEN.register("aurorian_coal", basicItemWithBurntime(defaultProp(), 1600));
    public static final DeferredItem<Item> aurorian_coal_nugget = ITEMS_GEN.register("aurorian_coal_nugget", basicItemWithBurntime(defaultProp(), 200));
    public static final DeferredItem<Item> aurorian_steel_ingot = ITEMS_GEN.register("aurorian_steel_ingot", basicItem());
    public static final DeferredItem<Item> aurorian_steel_nugget = ITEMS_GEN.register("aurorian_steel_nugget", basicItem());
    public static final DeferredItem<Item> aurorianite_ingot = ITEMS_GEN.register("aurorianite_ingot", basicItem());
    public static final DeferredItem<Item> aurorianite_scrap = ITEMS_GEN.register("aurorianite_scrap", basicItem());
    public static final DeferredItem<Item> cerulean_ingot = ITEMS_GEN.register("cerulean_ingot", basicItem());
    public static final DeferredItem<Item> cerulean_nugget = ITEMS_GEN.register("cerulean_nugget", basicItem());
    public static final DeferredItem<Item> crystalline_ingot = ITEMS_GEN.register("crystalline_ingot", basicItem());
    public static final DeferredItem<Item> crystalline_scrap = ITEMS_GEN.register("crystalline_scrap", basicItem());
    public static final DeferredItem<Item> cup = ITEMS_GEN.register("cup", basicItemWithBurntime(defaultProp(), 100));
    public static final DeferredItem<Item> darkstone_key = ITEMS_GEN_KEY.register("darkstone_key", basicItem(defaultProp().durability(3).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> lavender = ITEMS_GEN.register("lavender", basicItem());
    public static final DeferredItem<Item> lockpicks = ITEMS_GEN.register("lockpicks", basicItem(defaultProp().durability(16)));
    public static final DeferredItem<Item> moon_temple_interior_key = ITEMS_GEN_KEY.register("moon_temple_interior_key", basicItem(defaultProp().durability(1).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> moon_temple_key = ITEMS_GEN_KEY.register("moon_temple_key", basicItem(defaultProp().durability(3).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> moon_temple_key_fragment = ITEMS_GEN.register("moon_temple_key_fragment", basicItem());
    public static final DeferredItem<Item> moonstone_ingot = ITEMS_GEN.register("moonstone_ingot", basicItem());
    public static final DeferredItem<Item> moonstone_nugget = ITEMS_GEN.register("moonstone_nugget", basicItem());
    public static final DeferredItem<Item> plant_fiber = ITEMS_GEN.register("plant_fiber", basicItem());
    public static final DeferredItem<Item> runestone_key = ITEMS_GEN_KEY.register("runestone_key", basicItem(defaultProp().durability(3).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> runestone_loot_key = ITEMS_GEN_KEY.register("runestone_loot_key", basicItem(defaultProp().durability(1).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> silentwood_stick = ITEMS_GEN_HANDHELD.register("silentwood_stick", basicItemWithBurntime(defaultProp(), 100));
    public static final DeferredItem<Item> spectral_silk = ITEMS_GEN.register("spectral_silk", basicItem(defaultProp().rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> umbra_ingot = ITEMS_GEN.register("umbra_ingot", basicItem());
    public static final DeferredItem<Item> umbra_scrap = ITEMS_GEN.register("umbra_scrap", basicItem());

    // Foodstuff
    public static final DeferredItem<Item> bright_bulb_tea = ITEMS_GEN_TEA.register("bright_bulb_tea", () -> new BaseAurorianTea(new Color(247, 213, 92), BaseAurorianTea.properties().food(BaseAurorianTea.foodProperties().effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 1), 1F).build())));
    public static final DeferredItem<Item> lavender_bread = ITEMS_GEN.register("lavender_bread", basicItem(defaultProp().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.2F).fast().build())));
    public static final DeferredItem<Item> lavender_tea = ITEMS_GEN_TEA.register("lavender_tea", () -> new BaseAurorianTea(new Color(166, 138, 249), BaseAurorianTea.properties().food(BaseAurorianTea.foodProperties().effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 1), 1F).build())));
    public static final DeferredItem<Item> petunia_tea = ITEMS_GEN_TEA.register("petunia_tea", () -> new BaseAurorianTea(new Color(255, 200, 214), BaseAurorianTea.properties().food(BaseAurorianTea.foodProperties().effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 1), 1F).build())));
    public static final DeferredItem<Item> silkberry = ITEMS_GEN.register("silkberry", basicItem(defaultProp().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20, 0), 0.5F).build())));
    public static final DeferredItem<Item> silkberry_jam = ITEMS_GEN.register("silkberry_jam", basicItem(defaultProp().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.2F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20, 0), 1F).build())));
    public static final DeferredItem<Item> silkberry_jam_sandwich = ITEMS_GEN.register("silkberry_jam_sandwich", basicItem(defaultProp().food(new FoodProperties.Builder().nutrition(7).saturationModifier(0.4F).build())));
    public static final DeferredItem<Item> silkberry_tea = ITEMS_GEN_TEA.register("silkberry_tea", () -> new BaseAurorianTea(new Color(71, 193, 249), BaseAurorianTea.properties().food(BaseAurorianTea.foodProperties().effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600, 1), 1F).build())));
    public static final DeferredItem<Item> strange_meat = ITEMS_GEN.register("strange_meat", () -> new StrangeMeat(defaultProp().durability(10).food(new FoodProperties.Builder().nutrition(8).saturationModifier(0.9F).build())));

    // Tools
    public static final DeferredItem<Item> aurorian_steel_hoe = ITEMS_GEN_HANDHELD.register("aurorian_steel_hoe", () -> new AurorianSteelHoe(MaterialTiers.AURORIAN_STEEL, defaultProp().rarity(Rarity.EPIC).attributes(HoeItem.createAttributes(MaterialTiers.AURORIAN_STEEL, -3, 0.0F))));
    public static final DeferredItem<Item> aurorian_steel_leggings = ITEMS_GEN.register("aurorian_steel_leggings", () -> new AurorianSteelArmor(MaterialTiers.AURORIAN_STEEL_ARMOR, ArmorItem.Type.LEGGINGS, defaultProp().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> aurorian_steel_pickaxe = ITEMS_GEN_HANDHELD.register("aurorian_steel_pickaxe", () -> new AurorianSteelPickaxe(MaterialTiers.AURORIAN_STEEL, defaultProp().rarity(Rarity.EPIC).attributes(PickaxeItem.createAttributes(MaterialTiers.AURORIAN_STEEL, 1, -2.8F))));
    public static final DeferredItem<Item> aurorian_steel_shovel = ITEMS_GEN_HANDHELD.register("aurorian_steel_shovel", () -> new AurorianSteelShovel(MaterialTiers.AURORIAN_STEEL, defaultProp().rarity(Rarity.EPIC).attributes(ShovelItem.createAttributes(MaterialTiers.AURORIAN_STEEL, 1.5F, -3.0F))));
    public static final DeferredItem<Item> aurorian_steel_sword = ITEMS_GEN_HANDHELD.register("aurorian_steel_sword", () -> new AurorianSteelSword(MaterialTiers.AURORIAN_STEEL, defaultProp().rarity(Rarity.EPIC).attributes(SwordItem.createAttributes(MaterialTiers.AURORIAN_STEEL, 3, -2.4F))));
    public static final DeferredItem<Item> aurorian_stone_axe = ITEMS_GEN_HANDHELD.register("aurorian_stone_axe", () -> new BaseAurorianAxe(MaterialTiers.AURORIAN_STONE, defaultProp().attributes(AxeItem.createAttributes(MaterialTiers.AURORIAN_STONE, 7.0F, -3.2F))));
    public static final DeferredItem<Item> aurorian_stone_hoe = ITEMS_GEN_HANDHELD.register("aurorian_stone_hoe", () -> new BaseAurorianHoe(MaterialTiers.AURORIAN_STONE, defaultProp().attributes(HoeItem.createAttributes(MaterialTiers.AURORIAN_STONE, -1, -2.0F))));
    public static final DeferredItem<Item> aurorian_stone_pickaxe = ITEMS_GEN_HANDHELD.register("aurorian_stone_pickaxe", () -> new BaseAurorianPickaxe(MaterialTiers.AURORIAN_STONE, defaultProp().attributes(PickaxeItem.createAttributes(MaterialTiers.AURORIAN_STONE, 1, -2.8F))));
    public static final DeferredItem<Item> aurorian_stone_shovel = ITEMS_GEN_HANDHELD.register("aurorian_stone_shovel", () -> new BaseAurorianShovel(MaterialTiers.AURORIAN_STONE, defaultProp().attributes(ShovelItem.createAttributes(MaterialTiers.AURORIAN_STONE, 1.5F, -3.0F))));
    public static final DeferredItem<Item> aurorian_stone_sickle = ITEMS_GEN_HANDHELD.register("aurorian_stone_sickle", () -> new BaseAurorianSickle(MaterialTiers.AURORIAN_STONE, defaultProp()));
    public static final DeferredItem<Item> aurorian_stone_sword = ITEMS_GEN_HANDHELD.register("aurorian_stone_sword", () -> new BaseAurorianSword(MaterialTiers.AURORIAN_STONE, defaultProp().attributes(SwordItem.createAttributes(MaterialTiers.AURORIAN_STONE, 3, -2.4F))));
    public static final DeferredItem<Item> moonstone_axe = ITEMS_GEN_HANDHELD.register("moonstone_axe", () -> new MoonstoneAxe(MaterialTiers.MOONSTONE, defaultProp().attributes(AxeItem.createAttributes(MaterialTiers.MOONSTONE, 6.0F, -3.1F))));
    public static final DeferredItem<Item> moonstone_hoe = ITEMS_GEN_HANDHELD.register("moonstone_hoe", () -> new MoonstoneHoe(MaterialTiers.MOONSTONE, defaultProp().attributes(HoeItem.createAttributes(MaterialTiers.MOONSTONE, -2, -1.0F))));
    public static final DeferredItem<Item> moonstone_pickaxe = ITEMS_GEN_HANDHELD.register("moonstone_pickaxe", () -> new MoonstonePickaxe(MaterialTiers.MOONSTONE, defaultProp().attributes(PickaxeItem.createAttributes(MaterialTiers.MOONSTONE, 1, -2.8F))));
    public static final DeferredItem<Item> moonstone_shield = ITEMS_GEN_SHIELD.register("moonstone_shield", () -> new MoonstoneShield(MaterialTiers.MOONSTONE, defaultProp()));
    public static final DeferredItem<Item> moonstone_shovel = ITEMS_GEN_HANDHELD.register("moonstone_shovel", () -> new MoonstoneShovel(MaterialTiers.MOONSTONE, defaultProp().attributes(ShovelItem.createAttributes(MaterialTiers.MOONSTONE, 1.5F, -3.0F))));
    public static final DeferredItem<Item> moonstone_sickle = ITEMS_GEN_HANDHELD.register("moonstone_sickle", () -> new MoonstoneSickle(MaterialTiers.MOONSTONE, defaultProp()));
    public static final DeferredItem<Item> moonstone_sword = ITEMS_GEN_HANDHELD.register("moonstone_sword", () -> new MoonstoneSword(MaterialTiers.MOONSTONE, defaultProp().attributes(SwordItem.createAttributes(MaterialTiers.MOONSTONE, 3, -2.4F))));
    public static final DeferredItem<Item> silentwood_axe = ITEMS_GEN_HANDHELD.register("silentwood_axe", () -> new BaseAurorianAxe(MaterialTiers.SILENTWOOD, defaultProp().attributes(AxeItem.createAttributes(MaterialTiers.SILENTWOOD, 6.0F, -3.2F)), 200));
    public static final DeferredItem<Item> silentwood_bow = ITEMS.register("silentwood_bow", () -> new BaseAurorianBow(MaterialTiers.SILENTWOOD, defaultProp().durability(150), 200));
    public static final DeferredItem<Item> silentwood_hoe = ITEMS_GEN_HANDHELD.register("silentwood_hoe", () -> new BaseAurorianHoe(MaterialTiers.SILENTWOOD, defaultProp().attributes(HoeItem.createAttributes(MaterialTiers.SILENTWOOD, 0, -3.0F)), 200));
    public static final DeferredItem<Item> silentwood_pickaxe = ITEMS_GEN_HANDHELD.register("silentwood_pickaxe", () -> new BaseAurorianPickaxe(MaterialTiers.SILENTWOOD, defaultProp().attributes(PickaxeItem.createAttributes(MaterialTiers.SILENTWOOD, 1, -2.8F)), 200));
    public static final DeferredItem<Item> silentwood_shovel = ITEMS_GEN_HANDHELD.register("silentwood_shovel", () -> new BaseAurorianShovel(MaterialTiers.SILENTWOOD, defaultProp().attributes(ShovelItem.createAttributes(MaterialTiers.SILENTWOOD, 1.5F, -3.0F)), 200));
    public static final DeferredItem<Item> silentwood_sickle = ITEMS_GEN_HANDHELD.register("silentwood_sickle", () -> new BaseAurorianSickle(MaterialTiers.SILENTWOOD, defaultProp(), 200));
    public static final DeferredItem<Item> silentwood_sword = ITEMS_GEN_HANDHELD.register("silentwood_sword", () -> new BaseAurorianSword(MaterialTiers.SILENTWOOD, defaultProp().attributes(SwordItem.createAttributes(MaterialTiers.SILENTWOOD, 3, -2.4F)), 200));

    // Armor
    public static final DeferredItem<Item> aurorian_steel_axe = ITEMS_GEN_HANDHELD.register("aurorian_steel_axe", () -> new AurorianSteelAxe(MaterialTiers.AURORIAN_STEEL, defaultProp().attributes(AxeItem.createAttributes(MaterialTiers.AURORIAN_STEEL, 5.0F, -3.0F)).rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> aurorian_steel_boots = ITEMS_GEN.register("aurorian_steel_boots", () -> new AurorianSteelArmor(MaterialTiers.AURORIAN_STEEL_ARMOR, ArmorItem.Type.BOOTS, defaultProp().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> aurorian_steel_chestplate = ITEMS_GEN.register("aurorian_steel_chestplate", () -> new AurorianSteelArmor(MaterialTiers.AURORIAN_STEEL_ARMOR, ArmorItem.Type.CHESTPLATE, defaultProp().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> aurorian_steel_helmet = ITEMS_GEN.register("aurorian_steel_helmet", () -> new AurorianSteelArmor(MaterialTiers.AURORIAN_STEEL_ARMOR, ArmorItem.Type.HELMET, defaultProp().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> cerulean_boots = ITEMS_GEN.register("cerulean_boots", () -> new BaseAurorianArmor(MaterialTiers.CERULEAN_ARMOR, ArmorItem.Type.BOOTS, defaultProp()));
    public static final DeferredItem<Item> cerulean_chestplate = ITEMS_GEN.register("cerulean_chestplate", () -> new BaseAurorianArmor(MaterialTiers.CERULEAN_ARMOR, ArmorItem.Type.CHESTPLATE, defaultProp()));
    public static final DeferredItem<Item> cerulean_helmet = ITEMS_GEN.register("cerulean_helmet", () -> new BaseAurorianArmor(MaterialTiers.CERULEAN_ARMOR, ArmorItem.Type.HELMET, defaultProp()));
    public static final DeferredItem<Item> cerulean_leggings = ITEMS_GEN.register("cerulean_leggings", () -> new BaseAurorianArmor(MaterialTiers.CERULEAN_ARMOR, ArmorItem.Type.LEGGINGS, defaultProp()));
    public static final DeferredItem<Item> cerulean_shield = ITEMS_GEN_SHIELD.register("cerulean_shield", () -> new BaseAurorianShield(MaterialTiers.CERULEAN, defaultProp()));
    public static final DeferredItem<Item> knight_boots = ITEMS_GEN.register("knight_boots", () -> new BaseAurorianArmor(MaterialTiers.KNIGHT_ARMOR, ArmorItem.Type.BOOTS, defaultProp().rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> knight_chestplate = ITEMS_GEN.register("knight_chestplate", () -> new BaseAurorianArmor(MaterialTiers.KNIGHT_ARMOR, ArmorItem.Type.CHESTPLATE, defaultProp().rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> knight_helmet = ITEMS_GEN.register("knight_helmet", () -> new BaseAurorianArmor(MaterialTiers.KNIGHT_ARMOR, ArmorItem.Type.HELMET, defaultProp().rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> knight_leggings = ITEMS_GEN.register("knight_leggings", () -> new BaseAurorianArmor(MaterialTiers.KNIGHT_ARMOR, ArmorItem.Type.LEGGINGS, defaultProp().rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> spectral_boots = ITEMS_GEN.register("spectral_boots", () -> new SpectralArmor(MaterialTiers.SPECTRAL_ARMOR, ArmorItem.Type.BOOTS, defaultProp().rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> spectral_chestplate = ITEMS_GEN.register("spectral_chestplate", () -> new SpectralArmor(MaterialTiers.SPECTRAL_ARMOR, ArmorItem.Type.CHESTPLATE, defaultProp().rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> spectral_helmet = ITEMS_GEN.register("spectral_helmet", () -> new SpectralArmor(MaterialTiers.SPECTRAL_ARMOR, ArmorItem.Type.HELMET, defaultProp().rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> spectral_leggings = ITEMS_GEN.register("spectral_leggings", () -> new SpectralArmor(MaterialTiers.SPECTRAL_ARMOR, ArmorItem.Type.LEGGINGS, defaultProp().rarity(Rarity.UNCOMMON)));

    // Aurorianite
    public static final DeferredItem<Item> aurorianite_axe = ITEMS_GEN_HANDHELD.register("aurorianite_axe", () -> new AurorianiteAxe(MaterialTiers.AURORIANITE, defaultProp().attributes(AxeItem.createAttributes(MaterialTiers.AURORIANITE, 5.0F, -3.0F)).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> aurorianite_pickaxe = ITEMS_GEN_HANDHELD.register("aurorianite_pickaxe", () -> new AurorianitePickaxe(MaterialTiers.AURORIANITE, defaultProp().attributes(PickaxeItem.createAttributes(MaterialTiers.AURORIANITE, 1, -2.8F)).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> aurorianite_sword = ITEMS_GEN_HANDHELD.register("aurorianite_sword", () -> new AurorianiteSword(MaterialTiers.AURORIANITE, defaultProp().attributes(SwordItem.createAttributes(MaterialTiers.AURORIANITE, 3, -2.4F)).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> aurorianite_shovel = ITEMS_GEN_HANDHELD.register("aurorianite_shovel", () -> new AurorianiteShovel(MaterialTiers.AURORIANITE, defaultProp().attributes(ShovelItem.createAttributes(MaterialTiers.AURORIANITE, 1.5F, -3.0F)).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> living_divining_rod = ITEMS_GEN_HANDHELD.register("living_divining_rod", () -> new LivingDiviningRod(defaultProp().durability(100).rarity(Rarity.RARE)));

    // Umbra
    public static final DeferredItem<Item> umbra_chestplate = ITEMS_GEN.register("umbra_chestplate", () -> new UmbraChestplate(MaterialTiers.UMBRA_ARMOR, defaultProp().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> umbra_greatsword = ITEMS.register("umbra_greatsword", () -> new UmbraGreatsword(MaterialTiers.UMBRA, defaultProp().attributes(SwordItem.createAttributes(MaterialTiers.UMBRA, 7, -3.75F)).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> umbra_pickaxe = ITEMS_GEN_HANDHELD.register("umbra_pickaxe", () -> new UmbraPickaxe(MaterialTiers.UMBRA, defaultProp().attributes(PickaxeItem.createAttributes(MaterialTiers.UMBRA, 1, -2.8F)).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> umbra_shield = ITEMS_GEN_SHIELD.register("umbra_shield", () -> new UmbraShield(MaterialTiers.UMBRA, defaultProp().rarity(Rarity.RARE)));

    // Crystalline
    public static final DeferredItem<Item> absorption_orb = ITEMS_GEN.register("absorption_orb", () -> new AbsorptionOrb(defaultProp().durability(250).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> crystalline_pickaxe = ITEMS_GEN_HANDHELD.register("crystalline_pickaxe", () -> new CrystallinePickaxe(MaterialTiers.CRYSTALLINE, defaultProp().attributes(PickaxeItem.createAttributes(MaterialTiers.CRYSTALLINE, 1, -2.8F)).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> crystalline_shield = ITEMS_GEN_SHIELD.register("crystalline_shield", () -> new CrystallineShield(MaterialTiers.CRYSTALLINE, defaultProp().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> crystalline_sword = ITEMS.register("crystalline_sword", () -> new CrystallineSword(MaterialTiers.CRYSTALLINE, defaultProp().attributes(SwordItem.createAttributes(MaterialTiers.CRYSTALLINE, 3, -2.4F)).rarity(Rarity.RARE)));

    // Trinkets
    public static final DeferredItem<Item> amulet_of_chroma = ITEMS_GEN.register("amulet_of_chroma", () -> new BaseAurorianCurio(defaultProp().rarity(Rarity.EPIC), Arrays.asList(
            new BaseAurorianCurio.SimpleAttibuteModifier(Attributes.MAX_HEALTH.value(), AttributeModifier.Operation.ADD_VALUE, 2),
            new BaseAurorianCurio.SimpleAttibuteModifier(Attributes.MOVEMENT_SPEED.value(), AttributeModifier.Operation.ADD_MULTIPLIED_BASE, 0.05),
            new BaseAurorianCurio.SimpleAttibuteModifier(Attributes.KNOCKBACK_RESISTANCE.value(), AttributeModifier.Operation.ADD_VALUE, 0.1))
    ));
    public static final DeferredItem<Item> emerald_amulet = ITEMS_GEN.register("emerald_amulet", () -> new BaseAurorianCurio(defaultProp().rarity(Rarity.EPIC), Attributes.KNOCKBACK_RESISTANCE.value(), AttributeModifier.Operation.ADD_VALUE, 0.2));
    public static final DeferredItem<Item> keepers_amulet = ITEMS_GEN.register("keepers_amulet", () -> new BaseAurorianCurio(defaultProp().rarity(Rarity.EPIC), Attributes.ATTACK_KNOCKBACK.value(), AttributeModifier.Operation.ADD_VALUE, 1));
    public static final DeferredItem<Item> mirror_of_guidance = ITEMS_GEN.register("mirror_of_guidance", () -> new MirrorOGItem(defaultProp().rarity(Rarity.EPIC).stacksTo(1)));
    public static final DeferredItem<Item> ruby_amulet = ITEMS_GEN.register("ruby_amulet", () -> new BaseAurorianCurio(defaultProp().rarity(Rarity.EPIC), Attributes.MAX_HEALTH.value(), AttributeModifier.Operation.ADD_VALUE, 4));
    public static final DeferredItem<Item> sapphire_amulet = ITEMS_GEN.register("sapphire_amulet", () -> new BaseAurorianCurio(defaultProp().rarity(Rarity.EPIC), Attributes.MOVEMENT_SPEED.value(), AttributeModifier.Operation.ADD_MULTIPLIED_BASE, 0.1));

    // Misc
    public static final DeferredItem<Item> spawn_egg_dungeon_keeper = ITEMS_SPAWN_EGGS.register("spawn_egg_dungeon_keeper", () -> new DeferredSpawnEggItem(EntityRegistry.dungeon_keeper, 8117755, 3363951, defaultProp()));
    public static final DeferredItem<Item> spawn_egg_dungeon_slime = ITEMS_SPAWN_EGGS.register("spawn_egg_dungeon_slime", () -> new DeferredSpawnEggItem(EntityRegistry.dungeon_slime, 8117755, 3363951, defaultProp()));
    public static final DeferredItem<Item> spawn_egg_hollow = ITEMS_SPAWN_EGGS.register("spawn_egg_hollow", () -> new DeferredSpawnEggItem(EntityRegistry.hollow, 8117755, 3363951, defaultProp()));
    public static final DeferredItem<Item> spawn_egg_undead_knight = ITEMS_SPAWN_EGGS.register("spawn_egg_undead_knight", () -> new DeferredSpawnEggItem(EntityRegistry.undead_knight, 8117755, 3363951, defaultProp()));

    public static final DeferredItem<Item> cerulean_arrow = ITEMS_GEN.register("cerulean_arrow", () -> new BaseAurorianArrow(defaultProp()) {
        @Override
        public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon) {
            return new CeruleanArrowEntity(level, shooter);
        }
    });
    public static final DeferredItem<Item> crystal_arrow = ITEMS_GEN.register("crystal_arrow", () -> new BaseAurorianArrow(defaultProp()) {
        @Override
        public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon) {
            return new CrystalArrowEntity(level, shooter);
        }
    });

    public static Item.Properties defaultProp() {
        return new Item.Properties();
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
        return () -> new BaseAurorianItem(properties) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return burnTime == 0 ? super.getBurnTime(itemStack, recipeType) : burnTime;
            }
        };
    }

    public static void register(IEventBus bus) {
        ItemRegistry.ITEMS.register(bus);
        ItemRegistry.ITEMS_GEN.register(bus);
        ItemRegistry.ITEMS_GEN_KEY.register(bus);
        ItemRegistry.ITEMS_GEN_HANDHELD.register(bus);
        ItemRegistry.ITEMS_GEN_SHIELD.register(bus);
        ItemRegistry.ITEMS_SPAWN_EGGS.register(bus);
        ItemRegistry.ITEMS_GEN_TEA.register(bus);
    }
}
