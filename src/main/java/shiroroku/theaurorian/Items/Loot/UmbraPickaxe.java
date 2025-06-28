package shiroroku.theaurorian.Items.Loot;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import shiroroku.theaurorian.Config.CommonConfig;
import shiroroku.theaurorian.Items.BaseAurorianPickaxe;

import java.util.List;

public class UmbraPickaxe extends BaseAurorianPickaxe {

    public UmbraPickaxe(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getPlayer().isShiftKeyDown()) {
            clearSelectedBlock(pContext.getItemInHand());
        } else {
            setSelectedBlock(pContext.getItemInHand(), pContext.getLevel().getBlockState(pContext.getClickedPos()).getBlock(), pContext.getPlayer(), pContext.getHand());
        }
        return super.useOn(pContext);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        Block selectedBlock = getSelectedBlock(stack);
        if (selectedBlock != Blocks.AIR) {
            tooltipComponents.add(Component.translatable("item.theaurorian.umbra_pickaxe.selected", Component.translatable(selectedBlock.getDescriptionId())).withStyle(ChatFormatting.GOLD));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        return pState.is(getSelectedBlock(pStack)) ? (float) (pStack.get(DataComponents.TOOL).getMiningSpeed(pState) * CommonConfig.umbra_pickaxe_speed_multiplier.get()) : super.getDestroySpeed(pStack, pState);
    }

    public static @NotNull Block getSelectedBlock(ItemStack stack) {
        return BuiltInRegistries.BLOCK.get(ResourceLocation.tryParse(stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getString("selected_block")));
    }

    private static void clearSelectedBlock(ItemStack stack) {
        stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, data -> data.update(nbt -> nbt.remove("selected_block")));
    }

    private static void setSelectedBlock(ItemStack stack, Block block, Player player, InteractionHand hand) {
        if (getSelectedBlock(stack) != block) {
            stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, data -> data.update(nbt -> nbt.putString("selected_block", BuiltInRegistries.BLOCK.getKey(block).toString())));
            stack.hurtAndBreak(CommonConfig.umbra_pickaxe_selection_cost.get(), player, LivingEntity.getSlotForHand(hand));
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1F, 2F);
        }
    }
}
