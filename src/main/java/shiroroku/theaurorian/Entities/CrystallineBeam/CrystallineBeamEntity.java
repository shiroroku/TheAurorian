package shiroroku.theaurorian.Entities.CrystallineBeam;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import shiroroku.theaurorian.Configuration;
import shiroroku.theaurorian.Registry.EntityRegistry;

public class CrystallineBeamEntity extends Projectile {

    public CrystallineBeamEntity(Level pLevel, Entity owner) {
        super(EntityRegistry.crystalline_beam.get(), pLevel);
        setOwner(owner);
        setPos(owner.getX(), owner.getEyeY() - 0.3, owner.getZ());
    }

    public CrystallineBeamEntity(EntityType<? extends CrystallineBeamEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public void tick() {
        super.tick();

        // despawn
        if (tickCount > 600) {
            this.discard();
            return;
        }

        Vec3 vec3 = this.getDeltaMovement();

        HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
            this.onHit(hitresult);
        }

        double xo = this.getX() + vec3.x;
        double yo = this.getY() + vec3.y;
        double zo = this.getZ() + vec3.z;
        this.updateRotation();

        if (this.level.getBlockStates(this.getBoundingBox()).noneMatch(BlockBehaviour.BlockStateBase::isAir) || this.isInWaterOrBubble()) {
            this.discard();
            return;
        }

        //trail
        this.level.broadcastEntityEvent(this, (byte) 1);

        this.setPos(xo, yo, zo);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        switch (pId) {
            default:
            case 0:// hit particles
                for (int i = 0; i < 8; ++i) {
                    this.level.addParticle(ParticleTypes.POOF, this.getX(), this.getY() + 0.15, this.getZ(), 0.0D, 0.0D, 0.0D);
                }
                break;
            case 1:// trail particles
                if (this.tickCount > 1) {
                    Vec3 vec3 = this.getDeltaMovement();
                    this.level.addParticle(ParticleTypes.WAX_OFF, this.getX() + this.random.nextDouble() * 0.1, this.getY() + 0.15 + this.random.nextDouble() * 0.1, this.getZ() + this.random.nextDouble() * 0.1, vec3.x * -10, vec3.y * -10, vec3.z * -10);
                }
                break;
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (this.getOwner() instanceof LivingEntity livingEntity) {
            this.level.broadcastEntityEvent(this, (byte) 0);
            pResult.getEntity().hurt(DamageSource.indirectMobAttack(this, livingEntity).setProjectile(), Configuration.cystalline_sword_beam_damage.get().floatValue());
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte) 0);
            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.FIRE_EXTINGUISH, this.getSoundSource(), 1F, 1.0F);
            this.discard();
        }
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket pPacket) {
        super.recreateFromPacket(pPacket);
        // syncs velocity
        this.setDeltaMovement(pPacket.getXa(), pPacket.getYa(), pPacket.getZa());
    }
}
