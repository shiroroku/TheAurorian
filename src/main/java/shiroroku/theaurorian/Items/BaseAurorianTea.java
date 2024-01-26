package shiroroku.theaurorian.Items;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import shiroroku.theaurorian.Registry.ItemRegistry;

import java.awt.*;

public class BaseAurorianTea extends BaseAurorianItem {

    public final int color;

    public BaseAurorianTea(Color color, Properties pProperties) {
        super(pProperties);
        this.color = color.getRGB();
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    public static FoodProperties.Builder foodProperties() {
        return new FoodProperties.Builder().nutrition(0).fast().saturationMod(0);
    }

    public static Properties properties() {
        return ItemRegistry.defaultProp().stacksTo(16);
    }
}
