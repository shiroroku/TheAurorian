package shiroroku.theaurorian.Entities.AurorianArrow;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import shiroroku.theaurorian.Registry.EntityRegistry;
import shiroroku.theaurorian.Registry.ItemRegistry;

public class CrystalArrowEntity extends AurorianArrowEntity {

    private static final Item item = ItemRegistry.crystal_arrow.get();
    private static final float damage = 3f;
    private static final float weight = 0.2f;

    public CrystalArrowEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, item, damage, weight);
    }

    public CrystalArrowEntity(Level pLevel, double pX, double pY, double pZ) {
        super(EntityRegistry.crystal_arrow.get(), pLevel, pX, pY, pZ, item, damage, weight);
    }

    public CrystalArrowEntity(Level pLevel, LivingEntity pShooter) {
        super(EntityRegistry.crystal_arrow.get(), pLevel, pShooter, item, damage, weight);
    }
}
