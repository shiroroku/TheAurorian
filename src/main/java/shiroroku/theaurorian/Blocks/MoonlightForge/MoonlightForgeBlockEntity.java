package shiroroku.theaurorian.Blocks.MoonlightForge;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import shiroroku.theaurorian.Blocks.AbstractCrafterBlockEntity;
import shiroroku.theaurorian.Recipe.DoubleRecipeInput;
import shiroroku.theaurorian.Registry.BlockEntityRegistry;
import shiroroku.theaurorian.Registry.RecipeRegistry;
import shiroroku.theaurorian.Util.ModUtil;

public class MoonlightForgeBlockEntity extends AbstractCrafterBlockEntity<MoonlightForgeRecipe, DoubleRecipeInput> {

    public MoonlightForgeBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistry.moonlight_forge.get(), pPos, pBlockState, RecipeRegistry.moonlight_forge);
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
                return slot != 2;
            }
        };
    }

    @Override
    public int getCraftingTime(MoonlightForgeRecipe cachedRecipe) {
        int heightMin = 0; // below is 1x
        int heightMax = 300; // above is 5x
        int height = Math.max(heightMin, Math.min(heightMax, this.getBlockPos().getY()));
        float multiplier = ((float) height / heightMax) * 4f + 1;
        return (int) (800 / multiplier);
    }

    @Override
    public boolean isMissingPrerequisites() {
        return !canSeeMoon() || getItemHandler().getStackInSlot(0).isEmpty() || getItemHandler().getStackInSlot(1).isEmpty();
    }

    public boolean canSeeMoon() {
        return level.canSeeSky(this.getBlockPos().above()) && (level.getTimeOfDay(1) > 0.25 && level.getTimeOfDay(1) < 0.75);
    }

    @Override
    public boolean isRecipeValid(MoonlightForgeRecipe recipe) {
        return recipe.matches(makeRecipeInput(), level) && ModUtil.canItemsStack(recipe.output(), getItemHandler().getStackInSlot(2));
    }

    @Override
    public DoubleRecipeInput makeRecipeInput() {
        return new DoubleRecipeInput(getItemHandler().getStackInSlot(0), getItemHandler().getStackInSlot(1));
    }

    @Override
    public void finishCraft(MoonlightForgeRecipe recipe) {
        // shrink inputs, and insert outputs
        getItemHandler().getStackInSlot(0).shrink(1);
        getItemHandler().getStackInSlot(1).shrink(1);
        ModUtil.setAndMergeStack(getItemHandler(), 2, recipe.getResultItem());
        resetCrafting();
    }
}
