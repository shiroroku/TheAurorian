package shiroroku.theaurorian.Entities.AurorianArrow;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class AurorianArrowEntity extends AbstractArrow {

    private final Item arrowItem;
    private final Float weight;

    public AurorianArrowEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel, Item arrowItem, float damage, Float weight) {
        super(pEntityType, pLevel);
        this.arrowItem = arrowItem;
        this.weight = weight;
        this.setBaseDamage(damage);
    }

    public AurorianArrowEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel, double pX, double pY, double pZ, Item arrowItem, float damage, Float weight) {
        super(pEntityType, pX, pY, pZ, pLevel);
        this.arrowItem = arrowItem;
        this.weight = weight;
        this.setBaseDamage(damage);
    }

    public AurorianArrowEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel, LivingEntity pShooter, Item arrowItem, float damage, Float weight) {
        super(pEntityType, pShooter, pLevel);
        this.arrowItem = arrowItem;
        this.weight = weight;
        this.setBaseDamage(damage);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.isNoGravity() && !this.isNoPhysics()) {
            Vec3 vel = this.getDeltaMovement();
            this.setDeltaMovement(vel.x, vel.y - weight, vel.z);
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(arrowItem);
    }
}
