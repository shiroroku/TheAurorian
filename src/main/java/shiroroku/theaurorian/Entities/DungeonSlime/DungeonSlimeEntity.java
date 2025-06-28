package shiroroku.theaurorian.Entities.DungeonSlime;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.CommonHooks;

import javax.annotation.Nullable;

public class DungeonSlimeEntity extends Slime {

    public DungeonSlimeEntity(EntityType<? extends Slime> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.35F).add(Attributes.ATTACK_DAMAGE, 2);
    }

    @Override
    protected boolean isDealsDamage() {
        return this.isEffectiveAi();
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData) {
        SpawnGroupData data = super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData);
        this.setSize(pDifficulty.getDifficulty() == Difficulty.EASY ? 1 : pLevel.getRandom().nextInt(3), true);
        return data;
    }

    @Override
    public void setSize(int pSize, boolean pResetHealth) {
        super.setSize(pSize, pResetHealth);
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.35F);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2);
    }

    @Override
    protected void dealDamage(LivingEntity livingEntity) {
        if (this.isAlive()) {
            int i = this.getSize();
            var damageSource = damageSources().mobAttack(this);
            if (this.distanceToSqr(livingEntity) < 0.8D * (double) i * 0.8D * (double) i && this.hasLineOfSight(livingEntity) && livingEntity.hurt(damageSource, this.getAttackDamage())) {
                this.playSound(SoundEvents.SLIME_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                if (level() instanceof ServerLevel serverLevel) {
                    EnchantmentHelper.doPostAttackEffects(serverLevel, livingEntity, damageSource);
                }
            }
        }
    }

    @Override
    public void jumpFromGround() {
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.x * 1.2, this.getJumpPower() * 1.2, vec3.z * 1.2);
        this.hasImpulse = true;
        CommonHooks.onLivingJump(this);
    }

    @Override
    protected int getJumpDelay() {
        return 1;
    }

    @Override
    protected boolean spawnCustomParticles() {
        return true;
    }
}
