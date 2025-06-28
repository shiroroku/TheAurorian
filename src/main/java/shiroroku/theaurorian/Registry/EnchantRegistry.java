package shiroroku.theaurorian.Registry;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import shiroroku.theaurorian.TheAurorian;

public class EnchantRegistry {

    public static final ResourceKey<Enchantment> LIGHTNING = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "lightning"));
    public static final ResourceKey<Enchantment> LIGHTNING_RESISTANCE = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(TheAurorian.MODID, "lightning_resistance"));

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<DamageType> damageTypes = context.lookup(Registries.DAMAGE_TYPE);
        HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> items = context.lookup(Registries.ITEM);
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);

        context.register(LIGHTNING, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                5,
                4,
                Enchantment.dynamicCost(1, 11),
                Enchantment.dynamicCost(12, 11),
                1,
                EquipmentSlotGroup.MAINHAND)
        ).build(LIGHTNING.location()));

        context.register(LIGHTNING_RESISTANCE, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                2,
                1,
                Enchantment.constantCost(1),
                Enchantment.constantCost(41),
                4,
                EquipmentSlotGroup.ARMOR
        )).build(LIGHTNING_RESISTANCE.location()));
    }
}
