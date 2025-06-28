package shiroroku.theaurorian.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Simple inventory with a single timer and single recipe type.
 */
public abstract class AbstractCrafterBlockEntity<R extends Recipe<I>, I extends RecipeInput> extends AbstractInventoryBlockEntity {

    public int craftingProgress = -1;
    public R cachedRecipe = null;
    public final Supplier<RecipeType<R>> recipeType;

    public AbstractCrafterBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, Supplier<RecipeType<R>> recipeType) {
        super(pType, pPos, pBlockState);
        this.recipeType = recipeType;
    }

    /**
     * Checks this before checking recipes, stuff like block position or empty slots should check here.
     */
    public abstract boolean isMissingPrerequisites();

    public abstract int getCraftingTime(R cachedRecipe);

    /**
     * Consume inputs and set outputs here, the recipe is already validated beforehand
     */
    public abstract void finishCraft(R recipe);

    /**
     * Check If this recipe can be crafted. Stuff like input matching and if outputs can stack.
     */
    public abstract boolean isRecipeValid(R recipe);

    public abstract I makeRecipeInput();

    public boolean isCrafting() {
        return craftingProgress != -1;
    }

    /**
     * Stops crafting, clears cached recipe
     */
    public void resetCrafting() {
        craftingProgress = -1;
        cachedRecipe = null;
        updateClient();
    }

    /**
     * Attempts to begin crafting, does nothing if we are already crafting
     */
    public void tryStartCraft() {
        // if we are already crafting, we cant start again
        if (isCrafting()) {
            return;
        }

        // try to get a recipe, then start the craft
        Optional<RecipeHolder<R>> recipe = tryGetRecipe();
        if (recipe.isEmpty()) {
            return;
        }
        updateClient();
        cachedRecipe = recipe.get().value();
        craftingProgress = 0;
    }

    /**
     * Register this to getTicker in your block. handles timers, recipe validation and final crafting
     */
    public static <T extends BlockEntity> void updateCraft(Level level, BlockPos pos, BlockState blockState, T t) {
        if (t instanceof AbstractCrafterBlockEntity crafter) {
            // check if the current cached recipe is valid, if we are crafting then increment until finish.
            // if we are not crafting, try to craft every 1 second
            crafter.validateCachedRecipe();
            if (crafter.isCrafting()) {
                crafter.craftingProgress++;
                int craftingTime = crafter.getCraftingTime(crafter.cachedRecipe);
                if (craftingTime == 0) {
                    crafter.resetCrafting();
                }
                if (crafter.craftingProgress >= craftingTime) {
                    // we check that our cached recipe still is valid
                    // and we only want the server to set stacks
                    if (crafter.validateCachedRecipe() || level.isClientSide) {
                        return;
                    }
                    crafter.finishCraft(crafter.cachedRecipe);
                    crafter.resetCrafting();
                }
            } else {
                if (level.getGameTime() % 20 == 0) {
                    crafter.tryStartCraft();
                }
            }
        }
    }

    /**
     * Checks if the cached recipe is still valid, if not then stop crafting, returns true if crafting was stopped
     */
    public boolean validateCachedRecipe() {
        if (isCrafting() && cachedRecipe != null) {
            if (isMissingPrerequisites() || !isRecipeValid(cachedRecipe)) {
                resetCrafting();
                return true;
            }
        }
        return false;
    }

    private Optional<RecipeHolder<R>> tryGetRecipe() {
        if (isMissingPrerequisites()) {
            return Optional.empty();
        }

//        for (final RecipeHolder<? extends Recipe<? extends RecipeInput>> recipe : level.getRecipeManager().getAllRecipesFor(recipeType.get())) {
//            if (isRecipeValid(recipe.value())) {
//                return Optional.of(recipe.value());
//            }
//        }
//        return Optional.empty();

        return level.getRecipeManager().getRecipeFor(recipeType.get(), makeRecipeInput(), level);

    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        if (tag.contains("crafting_progress")) {
            craftingProgress = tag.getInt("crafting_progress");
        }
        super.loadAdditional(tag, registries);
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putInt("crafting_progress", craftingProgress);
        super.saveAdditional(tag, registries);
    }
}
