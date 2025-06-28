package shiroroku.theaurorian.Enchantments;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import shiroroku.theaurorian.DataGen.DataGenItemsTags;
import shiroroku.theaurorian.Registry.EnchantRegistry;

import java.util.stream.StreamSupport;

public class LightningEnchant {
    public static void handleOnDamage(LivingDamageEvent.Pre event) {
        if (!event.getEntity().level().isClientSide && event.getSource().getEntity() instanceof LivingEntity attacker) {
            EntityType.LIGHTNING_BOLT.spawn((ServerLevel) event.getEntity().level(), it -> it.setVisualOnly(true), event.getEntity().getOnPos(), MobSpawnType.TRIGGERED, false, false);
            int metal_armor_pieces = (int) StreamSupport.stream(event.getEntity().getArmorSlots().spliterator(), false).filter(
                    stack -> !stack.isEmpty() && !stack.is(DataGenItemsTags.LIGHTNING_IMMUNE) && stack.getTagEnchantments().keySet().stream().noneMatch(it -> it.equals(EnchantRegistry.LIGHTNING_RESISTANCE))).count();
            var enchantments = attacker.getItemInHand(InteractionHand.MAIN_HAND).getTagEnchantments();
            if (enchantments.keySet().stream().anyMatch(it -> it.unwrapKey().get().equals(EnchantRegistry.LIGHTNING))) {
                int lightning_level = enchantments.getLevel(CommonHooks.resolveLookup(Registries.ENCHANTMENT).getOrThrow(EnchantRegistry.LIGHTNING_RESISTANCE));
                // l1 = x1.5
                // l2 = x2
                // l3 = x2.5
                // l4 = x3
                float multiplier = 1 + 0.5f * Math.min(metal_armor_pieces, lightning_level);
                event.setNewDamage(event.getNewDamage() * multiplier);
            }
        }
    }

}
