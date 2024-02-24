package shiroroku.theaurorian.Enchantments;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import shiroroku.theaurorian.DataGen.DataGenItemsTags;
import shiroroku.theaurorian.Registry.EnchantRegistry;

import java.util.Map;
import java.util.stream.StreamSupport;

public class LightningEnchant extends Enchantment {

    public LightningEnchant() {
        super(Enchantment.Rarity.COMMON, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    public static void handleOnDamage(LivingDamageEvent event) {
        if (!event.getEntity().level.isClientSide && event.getSource().getEntity() instanceof LivingEntity attacker) {
            int metal_armor_pieces = (int) StreamSupport.stream(event.getEntity().getArmorSlots().spliterator(), false).filter((stack) -> !stack.isEmpty() && !stack.is(DataGenItemsTags.LIGHTNING_IMMUNE)).count();
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(attacker.getItemInHand(InteractionHand.MAIN_HAND));
            if (enchantments.containsKey(EnchantRegistry.lightning.get())) {
                int lightning_level = enchantments.get(EnchantRegistry.lightning.get());
                // l1 = x1.5
                // l2 = x2
                // l3 = x2.5
                // l4 = x3
                float multiplier = 1 + 0.5f * Math.min(metal_armor_pieces, lightning_level);
                event.setAmount(event.getAmount() * multiplier);
            }
        }
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

}
