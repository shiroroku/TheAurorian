package shiroroku.theaurorian.Registry;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import shiroroku.theaurorian.TheAurorian;

import java.util.List;
import java.util.function.Supplier;

public class MaterialTiers {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, TheAurorian.MODID);

    public static final SimpleTier AURORIANITE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 500, 8f, 3f, 20, () -> Ingredient.of(ItemRegistry.aurorianite_ingot.get()));
    public static final SimpleTier AURORIAN_STEEL = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1500, 8.5f, 3.5f, 10, () -> Ingredient.of(ItemRegistry.aurorian_steel_ingot.get()));
    public static final SimpleTier AURORIAN_STONE = new SimpleTier(BlockTags.INCORRECT_FOR_STONE_TOOL, 131, 4.5f, 1.5f, 14, () -> Ingredient.of(BlockRegistry.aurorian_cobblestone.get()));
    public static final SimpleTier CERULEAN = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 150, 7, 2.5f, 20, () -> Ingredient.of(ItemRegistry.cerulean_ingot.get()));
    public static final SimpleTier CRYSTALLINE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 500, 8f, 3f, 20, () -> Ingredient.of(ItemRegistry.crystalline_ingot.get()));
    public static final SimpleTier MOONSTONE = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 300, 7, 2.5f, 14, () -> Ingredient.of(ItemRegistry.moonstone_ingot.get()));
    public static final SimpleTier SILENTWOOD = new SimpleTier(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 3, 0, 20, () -> Ingredient.of(BlockRegistry.silentwood_planks.get()));
    public static final SimpleTier UMBRA = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 500, 8f, 3f, 20, () -> Ingredient.of(ItemRegistry.umbra_ingot.get()));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> AURORIAN_STEEL_ARMOR = armorBuilder(AURORIAN_STEEL, "aurorian_steel", new int[]{4, 7, 8, 4, 7}, SoundEvents.ARMOR_EQUIP_NETHERITE, 1.0F, 0.1F);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> CERULEAN_ARMOR = armorBuilder(CERULEAN, "cerulean", new int[]{3, 5, 6, 3, 5}, SoundEvents.ARMOR_EQUIP_DIAMOND, 1.0F, 0.0F);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> KNIGHT_ARMOR = armorBuilder("knight", new int[]{3, 5, 6, 3, 5}, 5, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SPECTRAL_ARMOR = armorBuilder("spectral", new int[]{4, 6, 6, 4, 6}, 25, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(ItemRegistry.spectral_silk.get()));
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> UMBRA_ARMOR = armorBuilder(UMBRA, "umbra", new int[]{4, 6, 6, 4, 6}, SoundEvents.ARMOR_EQUIP_DIAMOND, 0.0F, 0.2F);

    private static DeferredHolder<ArmorMaterial, ArmorMaterial> armorBuilder(Tier pTier, String pName, int[] pSlotProtections, Holder<SoundEvent> pSound, float pToughness, float pKnockbackResistance) {
        return armorBuilder(pName, pSlotProtections, pTier.getEnchantmentValue(), pSound, pToughness, pKnockbackResistance, pTier::getRepairIngredient);
    }

    private static DeferredHolder<ArmorMaterial, ArmorMaterial> armorBuilder(String pName, int[] pSlotProtections, int pEnchantmentValue, Holder<SoundEvent> pSound, float pToughness, float pKnockbackResistance, Supplier<Ingredient> pRepairIngredient) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, pName)));
        return ARMOR_MATERIALS.register(pName, () -> new ArmorMaterial(
                Util.make(Maps.newEnumMap(ArmorItem.Type.class), (map) -> {
                    map.put(ArmorItem.Type.BOOTS, pSlotProtections[0]);
                    map.put(ArmorItem.Type.LEGGINGS, pSlotProtections[1]);
                    map.put(ArmorItem.Type.CHESTPLATE, pSlotProtections[2]);
                    map.put(ArmorItem.Type.HELMET, pSlotProtections[3]);
                    map.put(ArmorItem.Type.BODY, pSlotProtections[4]);
                }),
                pEnchantmentValue,
                pSound,
                pRepairIngredient,
                list,
                pToughness,
                pKnockbackResistance
        ));
    }
}
