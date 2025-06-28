package shiroroku.theaurorian.Items.AurorianSteel;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import shiroroku.theaurorian.Config.CommonConfig;
import shiroroku.theaurorian.Util.TooltipUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AurorianSteel {

    public static List<Component> appendHoverText(List<Component> pTooltipComponents, ItemStack pStack) {
        Map<Holder<Enchantment>, Integer> upgradable_enchantments = getUpgradableEnchantments(pStack);
        if (!upgradable_enchantments.isEmpty()) {
            final Optional<Map.Entry<Holder<Enchantment>, Integer>> selected_enchant = upgradable_enchantments.entrySet().stream().findFirst();
            pTooltipComponents.add(Component.translatable("string.theaurorian.tooltip.aurorian_steel.level", getXP(pStack), (int) (100 * getMultiplier(pStack))).withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add((Component.translatable("string.theaurorian.tooltip.aurorian_steel.next_enchant").append(Enchantment.getFullname(selected_enchant.get().getKey(), selected_enchant.get().getValue() + 1))).withStyle(ChatFormatting.GOLD));
        }
        return TooltipUtil.shiftMoreInfo(pTooltipComponents, Component.translatable("string.theaurorian.tooltip.aurorian_steel").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
    }

    public static <T extends LivingEntity> int onItemDamage(ItemStack stack, T entity, int amount) {
        // we gotta be enchanted up yo
        if (!stack.isEnchanted()) {
            return amount;
        }

        Map<Holder<Enchantment>, Integer> upgradable_enchantments = getUpgradableEnchantments(stack);
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


        final Optional<Map.Entry<Holder<Enchantment>, Integer>> selected_enchant = upgradable_enchantments.entrySet().stream().findFirst();

        // do the levelling up
        selected_enchant.ifPresent(enchant -> {
            EnchantmentHelper.updateEnchantments(stack, enchantments -> {
                enchantments.set(enchant.getKey(), enchant.getValue() + 1);
                nextLevel(stack);
                entity.level().playSound(null, entity, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1, 1);
            });
        });

        return amount;
    }

    private static Map<Holder<Enchantment>, Integer> getUpgradableEnchantments(ItemStack aurorian_steel_item) {
        return aurorian_steel_item.getTagEnchantments().entrySet().stream().filter((e) -> e.getValue() < e.getKey().value().getMaxLevel()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Clears level, and increases cost for next level up
     */
    private static void nextLevel(ItemStack aurorian_steel_item) {
        // level cost increases by 25% every time you enchant
        setMultiplier(aurorian_steel_item, (float) (getMultiplier(aurorian_steel_item) * CommonConfig.aurorian_steel_level_multiplier.get()));
        setXP(aurorian_steel_item, 0);
    }

    private static boolean isMaxXP(ItemStack aurorian_steel_item) {
        return getXP(aurorian_steel_item) >= CommonConfig.aurorian_steel_base_level.get() * getMultiplier(aurorian_steel_item);
    }

    private static void setMultiplier(ItemStack aurorian_steel_item, float amt) {
        aurorian_steel_item.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, data -> data.update(nbt -> nbt.putFloat("multiplier", amt)));
    }

    private static float getMultiplier(ItemStack aurorian_steel_item) {
        return Math.max(1, aurorian_steel_item.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getFloat("multiplier"));
    }

    private static void setXP(ItemStack aurorian_steel_item, int amt) {
        aurorian_steel_item.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, data -> data.update(nbt -> nbt.putInt("xp", amt)));
    }

    private static int getXP(ItemStack aurorian_steel_item) {
        return aurorian_steel_item.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getInt("xp");
    }

    public static int getBarColor() {
        return FastColor.ARGB32.color(255, 189, 168, 252);
    }
}
