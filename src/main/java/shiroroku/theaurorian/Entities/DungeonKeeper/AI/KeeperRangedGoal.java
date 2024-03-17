package shiroroku.theaurorian.Entities.DungeonKeeper.AI;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.item.ItemStack;
import shiroroku.theaurorian.Entities.DungeonKeeper.DungeonKeeperEntity;
import shiroroku.theaurorian.Registry.ItemRegistry;

public class KeeperRangedGoal<T extends DungeonKeeperEntity> extends RangedBowAttackGoal<T> {

    private final DungeonKeeperEntity keeper;

    public KeeperRangedGoal(DungeonKeeperEntity pMob, double pSpeedModifier, int pAttackIntervalMin, float pAttackRadius) {
        super(pMob, pSpeedModifier, pAttackIntervalMin, pAttackRadius);
        keeper = pMob;
    }

    @Override
    public void start() {
        super.start();
        keeper.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ItemRegistry.silentwood_bow.get()));
    }

    @Override
    public void tick() {
        super.tick();
    }
}
