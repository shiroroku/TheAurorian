package shiroroku.theaurorian.Entities.DungeonKeeper;

import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.monster.AbstractSkeleton;

public class DungeonKeeperModel extends SkeletonModel<AbstractSkeleton> {
    public DungeonKeeperModel(ModelPart pRoot) {
        super(pRoot);
    }

    @Override
    public void setupAnim(AbstractSkeleton pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
//        ItemStack itemstack = pEntity.getMainHandItem();
//        if (pEntity.isAggressive() && (itemstack.isEmpty() || !itemstack.is(Items.BOW))) {
//            float f = Mth.sin(this.attackTime * (float) Math.PI);
//            float f1 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float) Math.PI);
//            this.rightArm.zRot = 0.0F;
//            this.leftArm.zRot = 0.0F;
//            this.rightArm.yRot = -(0.1F - f * 0.6F);
//            this.leftArm.yRot = 0.1F - f * 0.6F;
//            this.rightArm.xRot = (-(float) Math.PI / 2F);
//            this.leftArm.xRot = (-(float) Math.PI / 2F);
//            this.rightArm.xRot -= f * 1.2F - f1 * 0.4F;
//            this.leftArm.xRot -= f * 1.2F - f1 * 0.4F;
//            AnimationUtils.bobArms(this.rightArm, this.leftArm, pAgeInTicks);
//        }

    }
}
