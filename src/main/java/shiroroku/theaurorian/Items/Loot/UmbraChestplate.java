package shiroroku.theaurorian.Items.Loot;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import shiroroku.theaurorian.Items.BaseAurorianArmor;

import java.util.Map;

public class UmbraChestplate extends BaseAurorianArmor {

    public UmbraChestplate(ArmorMaterial pMaterial, Properties pProperties) {
        super(pMaterial, EquipmentSlot.CHEST, pProperties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(stack);
        if (player.isCrouching()) {
            if (!enchants.containsKey(Enchantments.THORNS)) {
                enchants.put(Enchantments.THORNS, 3);
                EnchantmentHelper.setEnchantments(enchants, stack);
            }
        } else {
            if (enchants.containsKey(Enchantments.THORNS)) {
                enchants.remove(Enchantments.THORNS);
                EnchantmentHelper.setEnchantments(enchants, stack);
            }
        }
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment != Enchantments.THORNS && super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return EnchantmentHelper.getEnchantments(book).entrySet().stream().noneMatch(e -> e.getKey() == Enchantments.THORNS) && super.isBookEnchantable(stack, book);
    }
}
