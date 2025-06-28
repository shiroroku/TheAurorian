package shiroroku.theaurorian.Items.Loot;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.Level;
import shiroroku.theaurorian.Items.BaseAurorianArmor;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class UmbraChestplate extends BaseAurorianArmor {

    public UmbraChestplate(Holder<ArmorMaterial> pMaterial, Properties pProperties) {
        super(pMaterial, Type.CHESTPLATE, pProperties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if ((Set.of(36, 37, 38, 39)).contains(slotId)) { // In armor slots
            if (entity instanceof Player player) {
                EnchantmentHelper.updateEnchantments(stack, enchantments -> {
                    if (player.isCrouching()) {
                        if (enchantments.keySet().stream().noneMatch(it -> it.is(Enchantments.THORNS))) {
                            enchantments.set(level.registryAccess().registry(Registries.ENCHANTMENT).orElseThrow().getHolderOrThrow(Enchantments.THORNS), 3);
                        }
                    } else {
                        enchantments.removeIf(it -> it.is(Enchantments.THORNS));
                    }
                });
            }
        }
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return !enchantment.is(Enchantments.THORNS) && super.supportsEnchantment(stack, enchantment);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return book.getTagEnchantments().entrySet().stream().noneMatch(e -> e.getKey().is(Enchantments.THORNS)) && super.isBookEnchantable(stack, book);
    }
}
