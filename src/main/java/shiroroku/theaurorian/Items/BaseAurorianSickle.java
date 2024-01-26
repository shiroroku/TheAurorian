package shiroroku.theaurorian.Items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import shiroroku.theaurorian.Util.TooltipUtil;

import java.util.List;

public class BaseAurorianSickle extends ShearsItem {

    private int burnTime = 0;

    public BaseAurorianSickle(Tier pTier, Properties pProperties, int burnTime) {
        super(pProperties.durability(pTier.getUses()));
        this.burnTime = burnTime;
    }

    public BaseAurorianSickle(Tier pTier, Properties pProperties) {
        super(pProperties.durability(pTier.getUses()));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, TooltipUtil.tryAddDesc(pStack, pTooltipComponents), pIsAdvanced);
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        return TooltipUtil.getBarColor();
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return burnTime == 0 ? super.getBurnTime(itemStack, recipeType) : burnTime;
    }
}
