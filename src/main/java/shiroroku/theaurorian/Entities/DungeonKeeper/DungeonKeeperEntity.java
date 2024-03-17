package shiroroku.theaurorian.Entities.DungeonKeeper;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import shiroroku.theaurorian.Entities.DungeonKeeper.AI.KeeperBarrageGoal;
import shiroroku.theaurorian.Entities.DungeonKeeper.AI.KeeperMeleeGoal;
import shiroroku.theaurorian.Entities.DungeonKeeper.AI.KeeperRangedGoal;
import shiroroku.theaurorian.Registry.BlockRegistry;
import shiroroku.theaurorian.Registry.EnchantRegistry;
import shiroroku.theaurorian.Registry.ItemRegistry;

public class DungeonKeeperEntity extends AbstractSkeleton {

    private final ServerBossEvent bossEvent = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

    public DungeonKeeperEntity(EntityType<? extends AbstractSkeleton> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 350;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 175.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ARMOR, 4.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.1)
                .add(Attributes.FOLLOW_RANGE, 50);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new KeeperBarrageGoal<>(this));
        this.goalSelector.addGoal(3, new KeeperMeleeGoal(this));
        this.goalSelector.addGoal(4, new KeeperRangedGoal<>(this, 1.0D, 10, 15.0F));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }

    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        ItemStack sword = new ItemStack(ItemRegistry.moonstone_sword.get());
        sword.enchant(Enchantments.KNOCKBACK, 2);
        sword.enchant(EnchantRegistry.lightning.get(), 3);
        this.setItemInHand(InteractionHand.MAIN_HAND, sword);
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void reassessWeaponGoal() {
        // we do this ourselves
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
        ItemEntity amulet = this.spawnAtLocation(ItemRegistry.keepers_amulet.get());
        ItemEntity key = this.spawnAtLocation(ItemRegistry.darkstone_key.get());
        if (amulet != null) {
            amulet.setExtendedLifetime();
        }
        if (key != null) {
            key.setExtendedLifetime();
        }
    }

    @Override
    public void remove(RemovalReason pReason) {
        if (pReason == RemovalReason.KILLED) {
            int distance = 50;
            for (int x = 0; x <= distance; x++) {
                for (int y = 0; y <= distance; y++) {
                    for (int z = 0; z <= distance; z++) {
                        int offs = distance / 2;
                        BlockPos p = new BlockPos(x + this.position().x() - offs, y + this.position().y() - offs, z + this.position().z() - offs);
                        if (this.level.getBlockState(p).getBlock() == BlockRegistry.fog_wall.get()) {
                            this.level.destroyBlock(p, false);
                        }
                    }
                }
            }
        }
        super.remove(pReason);
    }

    @Override
    public void startSeenByPlayer(ServerPlayer pPlayer) {
        super.startSeenByPlayer(pPlayer);
        this.bossEvent.addPlayer(pPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer pPlayer) {
        super.stopSeenByPlayer(pPlayer);
        this.bossEvent.removePlayer(pPlayer);
    }

    @Override
    public void checkDespawn() {
        if (this.level.getDifficulty() == Difficulty.PEACEFUL && this.shouldDespawnInPeaceful()) {
            this.discard();
        } else {
            this.noActionTime = 0;
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_DEATH;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.STRAY_STEP;
    }
}
