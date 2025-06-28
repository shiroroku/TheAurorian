package shiroroku.theaurorian.Items.Loot;

import net.minecraft.core.Holder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import shiroroku.theaurorian.Config.CommonConfig;
import shiroroku.theaurorian.DataGen.DataGenItemsTags;
import shiroroku.theaurorian.Items.BaseAurorianItem;
import shiroroku.theaurorian.Registry.ItemRegistry;

import java.util.List;

public class AbsorptionOrb extends BaseAurorianItem {

    public AbsorptionOrb(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        Player player = (Player) pEntity;
        ItemStack mainhandItem = player.getMainHandItem();
        ItemStack orbItem = player.getOffhandItem();

        // check if orb is offhand, and there isnt one in mainhand
        if (!orbItem.is(ItemRegistry.absorption_orb.get()) || mainhandItem.is(ItemRegistry.absorption_orb.get())) {
            return;
        }

        // if its not damageable or is damaged, return
        if (mainhandItem.isEmpty() || !mainhandItem.isDamageableItem() || !mainhandItem.isDamaged()) {
            return;
        }

        // check if its repairable, then repair
        if (CommonConfig.absorption_orb_repairs_all.get() || player.getMainHandItem().is(DataGenItemsTags.ABSORPTION_ORB_REPAIRABLE)) {
            orbItem.hurtAndBreak(1, player, EquipmentSlot.OFFHAND);
            mainhandItem.setDamageValue(mainhandItem.getDamageValue() - 1);
        }
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }
}
