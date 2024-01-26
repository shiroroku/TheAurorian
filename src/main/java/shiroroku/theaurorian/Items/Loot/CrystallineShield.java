package shiroroku.theaurorian.Items.Loot;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import shiroroku.theaurorian.Configuration;
import shiroroku.theaurorian.DataGen.DataGenItemsTags;
import shiroroku.theaurorian.Items.BaseAurorianShield;
import shiroroku.theaurorian.Registry.ItemRegistry;

import java.util.function.Consumer;

public class CrystallineShield extends BaseAurorianShield {

    public CrystallineShield(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T pEntity, Consumer<T> onBroken) {
        Player player = (Player) pEntity;
        ItemStack mainhandItem = player.getMainHandItem();
        ItemStack shieldItem = player.getOffhandItem();

        // check if shield is offhand, and there isnt one in mainhand
        if (!shieldItem.is(ItemRegistry.crystalline_shield.get()) || mainhandItem.is(ItemRegistry.crystalline_shield.get())) {
            return amount;
        }

        // if its not damageable or is damaged, return
        if (mainhandItem.isEmpty() || !mainhandItem.isDamageableItem() || !mainhandItem.isDamaged()) {
            return amount;
        }

        // check if its repairable, then repair
        if (Configuration.crystalline_shield_repairs_all.get() || player.getMainHandItem().is(DataGenItemsTags.CRYSTALLINE_SHIELD_REPAIRABLE)) {
            amount++;
            mainhandItem.setDamageValue(mainhandItem.getDamageValue() - 1);
        }
        return amount;
    }
}
