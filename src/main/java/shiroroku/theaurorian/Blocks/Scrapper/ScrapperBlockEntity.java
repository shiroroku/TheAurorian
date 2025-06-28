package shiroroku.theaurorian.Blocks.Scrapper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import shiroroku.theaurorian.Blocks.AbstractCrafterBlockEntity;
import shiroroku.theaurorian.Config.CommonConfig;
import shiroroku.theaurorian.Registry.BlockEntityRegistry;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.Registry.RecipeRegistry;
import shiroroku.theaurorian.Util.ModUtil;

public class ScrapperBlockEntity extends AbstractCrafterBlockEntity<ScrapperRecipe, SingleRecipeInput> {

    public ScrapperBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistry.scrapper.get(), pPos, pBlockState, RecipeRegistry.scrapper);
    }

    @Override
    public ItemStackHandler createItemHandler() {
        return new ItemStackHandler(3) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                tryStartCraft();
            }

            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                return switch (slot) {
                    case 0 -> stack.is(BlockRegistry.crystal.get().asItem());
                    case 2 -> false;
                    default -> true;
                };
            }
        };
    }

    @Override
    public int getCraftingTime(ScrapperRecipe cachedRecipe) {
        return (int) (CommonConfig.scrapper_base_craft_duration.get() * (level.getBlockState(getBlockPos().above()).is(BlockRegistry.crystal.get()) ? CommonConfig.scrapper_crystal_speed_discount.get() : 1));
    }

    @Override
    public boolean isMissingPrerequisites() {
        // Check crystals, and that there is an item to process
        return !getItemHandler().getStackInSlot(0).is(BlockRegistry.crystal.get().asItem()) || getItemHandler().getStackInSlot(1).isEmpty();
    }

    @Override
    public boolean isRecipeValid(ScrapperRecipe recipe) {
        // Check if output can fit
        return recipe.matches(makeRecipeInput(), level) && ModUtil.canItemsStack(recipe.output(), getItemHandler().getStackInSlot(2));
    }

    @Override
    public SingleRecipeInput makeRecipeInput() {
        return new SingleRecipeInput(getItemHandler().getStackInSlot(1));
    }

    @Override
    public void finishCraft(ScrapperRecipe recipe) {
        // chance to not output a result depending on damage
        // if item is more than 25% damaged, then that percent of the time itll output nothing
        // ex: 80% damaged = 80% that there will be no output, 15% damaged = 100% will output
        ItemStack inputStack = getItemHandler().getStackInSlot(1);
        float discardChance = (float) inputStack.getDamageValue() / inputStack.getMaxDamage();
        discardChance = discardChance < 0.25f ? 0 : discardChance;

        // shrink inputs, and insert outputs
        getItemHandler().getStackInSlot(0).shrink(1);
        getItemHandler().getStackInSlot(1).shrink(1);
        if (discardChance == 0 || !ModUtil.randomChanceOf(this.level.getRandom(), (double) discardChance)) {
            ModUtil.setAndMergeStack(getItemHandler(), 2, cachedRecipe.getResultItem());
        }

        if (level.getBlockState(getBlockPos().above()).is(BlockRegistry.crystal.get()) && ModUtil.randomChanceOf(this.level.getRandom(), CommonConfig.scrapper_crystal_break_chance.get())) {
            level.destroyBlock(getBlockPos().above(), false);
        }
    }

}
