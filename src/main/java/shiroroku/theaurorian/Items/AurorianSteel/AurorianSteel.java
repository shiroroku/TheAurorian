package shiroroku.theaurorian.Items.AurorianSteel;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import shiroroku.theaurorian.Configuration;
import shiroroku.theaurorian.Util.TooltipUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AurorianSteel {

    public static List<Component> appendHoverText(List<Component> pTooltipComponents, ItemStack pStack) {
        Map<Enchantment, Integer> upgradable_enchantments = getUpgradableEnchantments(pStack);
        if (!upgradable_enchantments.isEmpty()) {
            final Optional<Map.Entry<Enchantment, Integer>> selected_enchant = upgradable_enchantments.entrySet().stream().findFirst();
            pTooltipComponents.add(Component.translatable("string.theaurorian.tooltip.aurorian_steel.level", getXP(pStack), (int) (100 * getMultiplier(pStack))).withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add((Component.translatable("string.theaurorian.tooltip.aurorian_steel.next_enchant").append(selected_enchant.get().getKey().getFullname(selected_enchant.get().getValue() + 1))).withStyle(ChatFormatting.GOLD));
        }
        return TooltipUtil.shiftMoreInfo(pTooltipComponents, Component.translatable("string.theaurorian.tooltip.aurorian_steel").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
    }

    public static <T extends LivingEntity> int onItemDamage(ItemStack stack, T entity, int amount) {
        // we gotta be enchanted up yo
        if (!stack.isEnchanted()) {
            return amount;
        }

        Map<Enchantment, Integer> upgradable_enchantments = getUpgradableEnchantments(stack);
        // if no enchants can be upgraded then return
        if (upgradable_enchantments.isEmpty()) {
            return amount;
        }

        // we take damage so we get an xp
        // we only gain xp when theres an enchantment which can level up
        setXP(stack, getXP(stack) + 1);

        // gotta be at max xp
        if (!isMaxXP(stack)) {
            return amount;
        }

        // do the levelling up
        Map<Enchantment, Integer> held_enchantments = EnchantmentHelper.getEnchantments(stack);
        final Optional<Map.Entry<Enchantment, Integer>> selected_enchant = upgradable_enchantments.entrySet().stream().findFirst();
        if (selected_enchant.isPresent()) {
            held_enchantments.put(selected_enchant.get().getKey(), selected_enchant.get().getValue() + 1);
            EnchantmentHelper.setEnchantments(held_enchantments, stack);
            nextLevel(stack);
            entity.level.playSound(null, entity, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1, 1);
        }

        return amount;
    }

    private static Map<Enchantment, Integer> getUpgradableEnchantments(ItemStack aurorian_steel_item) {
        return aurorian_steel_item.getAllEnchantments().entrySet().stream().filter((e) -> e.getValue() < e.getKey().getMaxLevel()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Clears level, and increases cost for next level up
     */
    private static void nextLevel(ItemStack aurorian_steel_item) {
        // level cost increases by 25% every time you enchant
        setMultiplier(aurorian_steel_item, (float) (getMultiplier(aurorian_steel_item) * Configuration.aurorian_steel_level_multiplier.get()));
        setXP(aurorian_steel_item, 0);
    }

    private static boolean isMaxXP(ItemStack aurorian_steel_item) {
        return getXP(aurorian_steel_item) >= Configuration.aurorian_steel_base_level.get() * getMultiplier(aurorian_steel_item);
    }

    private static void setMultiplier(ItemStack aurorian_steel_item, float amt) {
        aurorian_steel_item.getOrCreateTag().putFloat("multiplier", amt);
    }

    private static float getMultiplier(ItemStack aurorian_steel_item) {
        return Math.max(1, aurorian_steel_item.getOrCreateTag().getFloat("multiplier"));
    }

    private static void setXP(ItemStack aurorian_steel_item, int amt) {
        aurorian_steel_item.getOrCreateTag().putInt("xp", amt);
    }

    private static int getXP(ItemStack aurorian_steel_item) {
        return aurorian_steel_item.getOrCreateTag().getInt("xp");
    }

    public static int getBarColor() {
        return FastColor.ARGB32.color(255, 189, 168, 252);
    }
}
