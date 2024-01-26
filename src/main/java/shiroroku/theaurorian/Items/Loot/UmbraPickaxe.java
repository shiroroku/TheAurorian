package shiroroku.theaurorian.Items.Loot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import shiroroku.theaurorian.Configuration;
import shiroroku.theaurorian.Items.BaseAurorianPickaxe;

import javax.annotation.Nullable;
import java.util.List;

public class UmbraPickaxe extends BaseAurorianPickaxe {

    public UmbraPickaxe(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
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
    public void appendHoverText(ItemStack pStack, @org.jetbrains.annotations.Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        Block selectedBlock = getSelectedBlock(pStack);
        if (selectedBlock != Blocks.AIR) {
            pTooltipComponents.add(Component.translatable("item.theaurorian.umbra_pickaxe.selected", Component.translatable(selectedBlock.getDescriptionId())).withStyle(ChatFormatting.GOLD));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        return pState.is(getSelectedBlock(pStack)) ? (float) (this.speed * Configuration.umbra_pickaxe_speed_multiplier.get()) : super.getDestroySpeed(pStack, pState);
    }

    @Nullable
    public static Block getSelectedBlock(ItemStack stack) {
        return ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryParse(stack.getOrCreateTag().getString("selected_block")));
    }

    private static void clearSelectedBlock(ItemStack stack) {
        stack.getOrCreateTag().remove("selected_block");
    }

    private static void setSelectedBlock(ItemStack stack, Block block, Player player, InteractionHand hand) {
        if (getSelectedBlock(stack) != block) {
            stack.getOrCreateTag().putString("selected_block", ForgeRegistries.BLOCKS.getKey(block).toString());
            stack.hurtAndBreak(Configuration.umbra_pickaxe_selection_cost.get(), player, (p) -> p.broadcastBreakEvent(hand));
            player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1F, 2F);
        }
    }
}
