package shiroroku.theaurorian.Blocks.AurorianFurnace;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import shiroroku.theaurorian.Configuration;
import shiroroku.theaurorian.Registry.BlockEntityRegistry;
import shiroroku.theaurorian.Registry.BlockRegistry;

public class AurorianFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

    public AurorianFurnaceBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistry.aurorian_furnace.get(), pPos, pBlockState, RecipeType.SMELTING);
    }

    protected Component getDefaultName() {
        return Component.translatable("block.theaurorian.aurorian_furnace");
    }

    protected int getBurnDuration(ItemStack pFuel) {
        if (level == null) {
            return super.getBurnDuration(pFuel);
        }

        int maxChimneys = Configuration.chimney_max.get();
        int chimneyCount = 0;
        BlockPos mutPos = this.getBlockPos().above();
        while (level.getBlockState(mutPos).is(BlockRegistry.chimney.get())) {
            chimneyCount++;
            mutPos = mutPos.above();
        }
        chimneyCount = Math.min(maxChimneys, chimneyCount);
        float multi = (float) Math.max(1, ((float) chimneyCount / maxChimneys) * Configuration.chimney_multiplier.get());
        return (int) (super.getBurnDuration(pFuel) * multi);
    }

    protected AbstractContainerMenu createMenu(int pId, Inventory pPlayer) {
        return new FurnaceMenu(pId, pPlayer, this, this.dataAccess);
    }
}
