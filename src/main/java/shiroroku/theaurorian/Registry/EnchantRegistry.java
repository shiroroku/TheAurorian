package shiroroku.theaurorian.Registry;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import shiroroku.theaurorian.Enchantments.LightningEnchant;
import shiroroku.theaurorian.TheAurorian;

public class EnchantRegistry {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, TheAurorian.MODID);

    public static final RegistryObject<Enchantment> lightning = ENCHANTMENTS.register("lightning", LightningEnchant::new);

}
