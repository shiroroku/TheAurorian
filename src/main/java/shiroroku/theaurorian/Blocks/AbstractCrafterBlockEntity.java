package shiroroku.theaurorian.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
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
public abstract class AbstractCrafterBlockEntity extends AbstractInventoryBlockEntity {

    public int craftingProgress = -1;
    public Recipe<Container> cachedRecipe = null;
    public final Supplier<RecipeType<? extends Recipe<Container>>> recipeType;

    public AbstractCrafterBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, Supplier<RecipeType<? extends Recipe<Container>>> recipeType) {
        super(pType, pPos, pBlockState);
        this.recipeType = recipeType;
    }

    /**
     * Checks this before checking recipes, stuff like block position or empty slots should check here.
     */
    public abstract boolean isMissingPrerequisites();

    /**
     * How many total ticks until the current craft is finished. if you use the recipe, be sure to validateCachedRecipe() before using it. returning 0 resets the craft
     */
    public abstract int getCraftingTime(Recipe<Container> cachedRecipe);

    /**
     * Consume inputs and set outputs here, the recipe is already validated beforehand
     */
    public abstract void finishCraft(Recipe<Container> recipe);

    /**
     * Check If this recipe can be crafted. Stuff like input matching and if outputs can stack.
     */
    public abstract boolean isRecipeValid(Recipe<Container> recipe);

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
        Optional<Recipe<Container>> recipe = tryGetRecipe();
        if (recipe.isEmpty()) {
            return;
        }
        updateClient();
        cachedRecipe = recipe.get();
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

    private Optional<Recipe<Container>> tryGetRecipe() {
        if (isMissingPrerequisites()) {
            return Optional.empty();
        }

        for (final Recipe<Container> recipe : level.getRecipeManager().getAllRecipesFor(recipeType.get())) {
            if (isRecipeValid(recipe)) {
                return Optional.of(recipe);
            }
        }
        return Optional.empty();
    }

    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("crafting_progress")) {
            craftingProgress = tag.getInt("crafting_progress");
        }
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.putInt("crafting_progress", craftingProgress);
        super.saveAdditional(tag);
    }
}
